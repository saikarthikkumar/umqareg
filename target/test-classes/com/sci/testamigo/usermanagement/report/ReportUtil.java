package com.sci.testamigo.usermanagement.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sci.testamigo.usermanagement.common.Constants;

public class ReportUtil {
  ResourceBundle resourceBundle = null;

  // ================================================================
  public FeatureDetails getFeatureDetails(String featureName) throws FileNotFoundException, IOException {
    FeatureDetails featureDetails = new FeatureDetails();
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    File directory = new File(resourceBundle.getString(Constants.FEATURE_PATH));
    File[] listofFiles = directory.listFiles();
    String feature = "";
    if (listofFiles != null) {
      for (int i = 0; i < listofFiles.length; i++) {
        if (listofFiles[i].getName().contains(featureName.toLowerCase())) {
          try (BufferedReader br = new BufferedReader(
              new FileReader(directory + File.separator + listofFiles[i].getName()))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
              feature = feature.concat(sCurrentLine);
              if (sCurrentLine.trim().startsWith("Feature")) {
                featureDetails.setFeatureName(sCurrentLine.trim().substring(8));
              }
            }
          }
          break;
        }
      }
    }
    featureDetails.setFeatureDefinition(feature);
    return featureDetails;
  }

  // ================================================================
  
  public ScenarioDetails getScenarioDetails(String featureName, int ScenarioNO)
      throws FileNotFoundException, IOException {
    ScenarioDetails scenarioDeatils = new ScenarioDetails();
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    File directory = new File(resourceBundle.getString(Constants.FEATURE_PATH));
    File[] listofFiles = directory.listFiles();
    if (listofFiles != null) {
      for (int i = 0; i < listofFiles.length; i++) {
        if (listofFiles[i].getName().contains(featureName.toLowerCase())) {
          try (BufferedReader br = new BufferedReader(
              new FileReader(directory + File.separator + listofFiles[i].getName()))) {
            String sCurrentLine;
            List<String> scenarioNameList = new ArrayList<>();
            List<String> tags = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
              if (sCurrentLine.trim().startsWith("Scenario")) {
                scenarioNameList.add(sCurrentLine.trim().substring(sCurrentLine.indexOf(":")));
              }
              if (sCurrentLine.trim().startsWith("@")) {
                tags.add(sCurrentLine);
              }
            }
            scenarioDeatils.setScenarioName(scenarioNameList.get(ScenarioNO));
            if (tags.get(ScenarioNO).contains(resourceBundle.getString(Constants.BI_USED_TAG))) {
              scenarioDeatils.setExecute(Constants.TRUE);
            }
            scenarioDeatils.setTags(tags.get(ScenarioNO).trim());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return scenarioDeatils;
  }

  // ================================================================
  
  public StepDetails getStepDetails(String featureName, int StepNo, String scenarioName)
      throws FileNotFoundException, IOException {
    StepDetails stepDetails = new StepDetails();
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    File directory = new File(resourceBundle.getString(Constants.FEATURE_PATH));
    File[] listofFiles = directory.listFiles();
    if (listofFiles != null) {
      for (int i = 0; i < listofFiles.length; i++) {
        if (listofFiles[i].getName().contains(featureName.toLowerCase())) {
          try (BufferedReader br = new BufferedReader(
              new FileReader(directory + File.separator + listofFiles[i].getName()))) {
            int conditionFlag = 0;
            boolean flag = false;
            String sCurrentLine;
            String stepName = "";
            boolean backGroundFlag = false;
            while ((sCurrentLine = br.readLine()) != null) {
              if (sCurrentLine.trim().startsWith("Background")) {
                backGroundFlag = true;
              }
              if (backGroundFlag) {
                if ((sCurrentLine.trim().startsWith("Then") || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given")
                    || sCurrentLine.trim().startsWith("And")) && conditionFlag == 1) {
                  stepName = stepName.concat(sCurrentLine.trim() + ",,");
                  conditionFlag = 1;
                } else if (sCurrentLine.trim().startsWith("@")) {
                  backGroundFlag = false;
                }
                conditionFlag = 1;
              }
              if (sCurrentLine.contains(scenarioName) || conditionFlag == 2) {
                if ((sCurrentLine.trim().startsWith("Then") || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given")
                    || sCurrentLine.trim().startsWith("And")) && conditionFlag == 2) {
                  stepName = stepName.concat(sCurrentLine.trim() + ",,");
                  flag = true;
                  conditionFlag = 2;
                } else if ((!(sCurrentLine.trim().startsWith("Then")
                    || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given")
                    || sCurrentLine.trim().startsWith("And"))) && flag) {
                  break;
                }
                conditionFlag = 2;
              }
            }
            String step[] = stepName.split(",,");
            stepDetails.setStep(step[StepNo]);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return stepDetails;
  }

  // ================================================================
  
  public int getStepCount(String featureName, String scenarioName) throws FileNotFoundException, IOException {
    int stepCount = 0;
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    File directory = new File(resourceBundle.getString(Constants.FEATURE_PATH));
    File[] listofFiles = directory.listFiles();
    if (listofFiles != null) {
      for (int i = 0; i < listofFiles.length; i++) {
        if (listofFiles[i].getName().contains(featureName.toLowerCase())) {
          try (BufferedReader br = new BufferedReader(
              new FileReader(directory + File.separator + listofFiles[i].getName()))) {

            int conditionFlag = 0;
            boolean flag = false;
            String sCurrentLine;
            boolean backGroundFlag = false;
            while ((sCurrentLine = br.readLine()) != null) {

              if (sCurrentLine.trim().startsWith("Background")) {
                backGroundFlag = true;
              }
              if (backGroundFlag) {
                if ((sCurrentLine.trim().startsWith("Then") || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given")
                    || sCurrentLine.trim().startsWith("And")) && conditionFlag == 1) {
                  stepCount = stepCount + 1;
                  conditionFlag = 1;
                } else if (sCurrentLine.trim().startsWith("@")) {
                  backGroundFlag = false;
                }
                conditionFlag = 1;
              }
              if (sCurrentLine.contains(scenarioName) || conditionFlag == 2) {
                if ((sCurrentLine.trim().startsWith("Then") || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given")
                    || sCurrentLine.trim().startsWith("And")) && conditionFlag == 2) {
                  stepCount = stepCount + 1;
                  flag = true;
                  conditionFlag = 2;
                } else if ((!(sCurrentLine.trim().startsWith("Then")
                    || sCurrentLine.trim().startsWith("When")
                    || sCurrentLine.trim().startsWith("Given"))) && flag) {
                  break;
                }
                conditionFlag = 2;
              }
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return stepCount;
  }
  // ================================================================
}