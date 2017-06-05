package com.sci.testamigo.usermanagement.report;

import java.util.List;

import com.sci.testamigo.usermanagement.common.Constants;

public class FeatureDetails {
  private long featureDetailsId;
  private String featureDefinition;
  private String featureName;
  private char isExecute = Constants.FALSE;
  private String status;
  private long startTimeStamp;
  private long endTimeStamp;
  private List<ScenarioDetails> scenarios;

  public long getFeatureDetailsId() {
    return featureDetailsId;
  }

  public void setFeatureDetailsId(long featureDetailsId) {
    this.featureDetailsId = featureDetailsId;
  }

  public String getFeatureDefinition() {
    return featureDefinition;
  }

  public void setFeatureDefinition(String featureDefinition) {
    this.featureDefinition = featureDefinition;
  }

  public String getFeatureName() {
    return featureName;
  }

  public void setFeatureName(String featureName) {
    this.featureName = featureName;
  }

  public char isExecute() {
    return isExecute;
  }

  public void setExecute(char isExecute) {
    this.isExecute = isExecute;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getStartTimeStamp() {
    return startTimeStamp;
  }

  public void setStartTimeStamp(long startTimeStamp) {
    this.startTimeStamp = startTimeStamp;
  }

  public long getEndTimeStamp() {
    return endTimeStamp;
  }

  public void setEndTimeStamp(long endTimeStamp) {
    this.endTimeStamp = endTimeStamp;
  }

  public List<ScenarioDetails> getScenarios() {
    return scenarios;
  }

  public void setScenarios(List<ScenarioDetails> scenarios) {
    this.scenarios = scenarios;
  }
}