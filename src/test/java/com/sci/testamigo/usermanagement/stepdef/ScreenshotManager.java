/**
 * @author Karthik
 *
 */
 
package com.sci.testamigo.usermanagement.stepdef;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.util.AppSetup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ScreenshotManager {
  protected transient final Log log = LogFactory.getLog(getClass());
  WebDriver driver;
  AppSetup appsetup = new AppSetup();

  public static int count = 0;

  @Before
  public void beforeScenario(Scenario scenario) {
    Constants.SCEANRIO_NAME = scenario.getName();
    for (int i = 0; i < Constants.FEATUREDETAILS.getScenarios().size(); i++) {
      if (Constants.FEATUREDETAILS.getScenarios().get(i).getScenarioName().trim()
          .equals(scenario.getName().trim())) {
        Constants.FEATUREDETAILS.getScenarios().get(i).setStartTimeStamp(System.currentTimeMillis());
      }
    }
  }

  @After
  /**
   * Embed a screenshot in test report if test is marked as failed
   */
  public void embedScreenshot(Scenario scenario) {
    log.info(scenario.getName() + " is :" + scenario.getStatus());
    for (int i = 0; i < Constants.FEATUREDETAILS.getScenarios().size(); i++) {
      if (Constants.FEATUREDETAILS.getScenarios().get(i).getScenarioName().trim()
          .equals(scenario.getName().trim())) {
        if (Constants.FEATUREDETAILS.getScenarios().get(i).isExecute() == '1') {
          Constants.FEATUREDETAILS.getScenarios().get(i).setEndTimeStamp(System.currentTimeMillis());
        }
        if (scenario.getStatus().equals("passed")) {
          Constants.FEATUREDETAILS.getScenarios().get(i).setStatus("PASS");
        } else {
          Constants.FEATUREDETAILS.getScenarios().get(i).setStatus("FAIL");
        }
        if (scenario.getStatus().equals("passed")) {
          for (int j = 0; j < Constants.FEATUREDETAILS.getScenarios().get(i).getStepDetails().size(); j++) {
            Constants.FEATUREDETAILS.getScenarios().get(i).getStepDetails().get(j).setStatus("PASS");
          }
        }
      }
    }
  }
}