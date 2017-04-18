package com.sci.testamigo.usermanagement.report;

import java.util.List;

import com.sci.testamigo.usermanagement.common.Constants;

public class ScenarioDetails {
  private String scenarioName;
  private String tags;
  private char isExecute = Constants.FALSE;
  private String status;
  private String errorMsg;
  private String screenShotLocation;
private long startTimeStamp ;
  private long endTimeStamp;
  private List<StepDetails> stepDetails;
  private long featureDetailsID;
  private long scenarioDetailsId;

  public String getScenarioName() {
    return scenarioName;
  }

  public void setScenarioName(String scenarioName) {
    this.scenarioName = scenarioName;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
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

  public List<StepDetails> getStepDetails() {
    return stepDetails;
  }

  public void setStepDetails(List<StepDetails> stepDetails) {
    this.stepDetails = stepDetails;
  }

  public long getFeatureDetailsID() {
    return featureDetailsID;
  }

  public void setFeatureDetailsID(long featureDetailsID) {
    this.featureDetailsID = featureDetailsID;
  }

  public long getScenarioDetailsId() {
    return scenarioDetailsId;
  }

  public void setScenarioDetailsId(long scenarioDetailsId) {
    this.scenarioDetailsId = scenarioDetailsId;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  public String getScreenShotLocation() {
		return screenShotLocation;
	}

	public void setScreenShotLocation(String screenShotLocation) {
		this.screenShotLocation = screenShotLocation;
	}
}