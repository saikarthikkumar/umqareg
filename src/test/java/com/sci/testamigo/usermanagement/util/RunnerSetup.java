package com.sci.testamigo.usermanagement.util;

import java.io.IOException;
import java.util.ResourceBundle;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.db.TestAmigoDBManagerHelper;
import com.sci.testamigo.usermanagement.report.FeatureDetails;
import com.sci.testamigo.usermanagement.report.ScenarioDetails;
import com.sci.testamigo.usermanagement.report.StepDetails;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;

public class RunnerSetup {
  ResourceBundle resourceBundle = null;
  public Runtime runtime;
  FeatureDetails featureDeatils = new FeatureDetails();
  ScenarioDetails scenarioDeatils = new ScenarioDetails();
  ScenarioDetails scenarioDetails = null;
  StepDetails stepDetails = null;

  public void UMrunnerOptions(String featureName) throws Throwable {

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      ResourceLoader resourceLoader = new MultiLoader(classLoader);
      RuntimeOptionsFactory roFactory = new RuntimeOptionsFactory(getClass());
      RuntimeOptions cucumberrun = roFactory.create();
      cucumberrun.getGlue().clear();
      //cucumberrun.addPlugin("com.cucumber.listener.ExtentCucumberFormatter");
      
      /* Gets the corresponding feature file, step definition and the @execute tag scenarios for execution */
      resourceBundle = ResourceBundle.getBundle("ApplicationResources");
      cucumberrun.getGlue().add(resourceBundle.getString(Constants.STEPDEF_PATH));
      cucumberrun.getFeaturePaths().clear();
      cucumberrun.getFeaturePaths().add(resourceBundle.getString(Constants.FEATURE_PATH) + featureName);
      if (!(featureName.contains("Signin") || featureName.contains("SignOut"))) {
        cucumberrun.getFilters().add(resourceBundle.getString(Constants.BI_USED_TAG));
      }
      ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
      Runtime runtime = new cucumber.runtime.Runtime(resourceLoader, classFinder, classLoader, cucumberrun);
      try {
        if (Constants.FEATUREDETAILS.isExecute() == '1') {
          Constants.FEATUREDETAILS.setExecute(Constants.TRUE);
          Constants.FEATUREDETAILS.setStartTimeStamp(System.currentTimeMillis());
        }
        runtime.run();
        if (Constants.FEATUREDETAILS.isExecute() == '1') {
          Constants.FEATUREDETAILS.setEndTimeStamp(System.currentTimeMillis());
        }
        try {
          boolean failFlag = true;
          for (int i = 0; i < Constants.FEATUREDETAILS.getScenarios().size(); i++) {
            scenarioDetails = Constants.FEATUREDETAILS.getScenarios().get(i);
            if (scenarioDetails.isExecute() == '1') {
              if (scenarioDetails.getStatus().trim().equals("FAIL")) {
                Constants.FEATUREDETAILS.setStatus("FAIL");
                failFlag = false;
              } else if (scenarioDetails.getStatus().trim().equals("PASS") && failFlag) {
                Constants.FEATUREDETAILS.setStatus("PASS");
              }
            }
          }
          Constants.FEATUREDETAILS = TestAmigoDBManagerHelper.getInstance()
              .saveFeatureDetails(Constants.FEATUREDETAILS);
          for (int i = 0; i < Constants.FEATUREDETAILS.getScenarios().size(); i++) {
            scenarioDetails = Constants.FEATUREDETAILS.getScenarios().get(i);
            Constants.FEATUREDETAILS.getScenarios().get(i).setScenarioDetailsId(
                TestAmigoDBManagerHelper.getInstance().saveScenarioDetails(scenarioDetails));
            for (int j = 0; j < Constants.FEATUREDETAILS.getScenarios().get(i).getStepDetails()
                .size(); j++) {
              stepDetails = scenarioDetails.getStepDetails().get(j);
              TestAmigoDBManagerHelper.getInstance().saveStepDetails(stepDetails,
                  scenarioDetails.getScenarioDetailsId());
            }
          }
          Constants.FEATUREDETAILS = null;
        } catch (Exception e) {
          e.printStackTrace();
        }
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void BIstoprunner() {
    runtime.exitStatus();
  }
}