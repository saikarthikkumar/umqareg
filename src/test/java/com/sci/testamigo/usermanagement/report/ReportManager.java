package com.sci.testamigo.usermanagement.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sci.testamigo.usermanagement.common.Constants;

public class ReportManager {
  ResourceBundle resourceBundle = null;
  FeatureDetails featureDetails = new FeatureDetails();
  ReportUtil reportUtil = new ReportUtil();

  public FeatureDetails populateInitialData(String featureName) throws FileNotFoundException, IOException {
    featureDetails = reportUtil.getFeatureDetails(featureName);
    String findStr = "Scenario";
    int lastIndex = 0;
    int count = 0;

    while (lastIndex != -1) {
      lastIndex = featureDetails.getFeatureDefinition().indexOf(findStr, lastIndex);
      if (lastIndex != -1) {
        count++;
        lastIndex += findStr.length();
      }
    }
    List<ScenarioDetails> scenario = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      List<StepDetails> steps = new ArrayList<>();
      scenario.add(reportUtil.getScenarioDetails(featureName, i));
      if (scenario.get(i).isExecute() == '1') {
        featureDetails.setExecute(Constants.TRUE);
      }
      for (int j = 0; j < reportUtil.getStepCount(featureName, scenario.get(i).getScenarioName()); j++) {
        steps.add(reportUtil.getStepDetails(featureName, j, scenario.get(i).getScenarioName()));
      }
      scenario.get(i).setStepDetails(steps);
      steps = null;
    }
    featureDetails.setScenarios(scenario);
    return featureDetails;
  }
}