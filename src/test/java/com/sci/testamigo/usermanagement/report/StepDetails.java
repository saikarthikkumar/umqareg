package com.sci.testamigo.usermanagement.report;

public class StepDetails {
  private String step;
  private String status;
  private String errorMessage;
  private String screenShotLocation;
  private String customMsg;

  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getScreenShotLocation() {
    return screenShotLocation;
  }

  public void setScreenShotLocation(String screenShotLocation) {
    this.screenShotLocation = screenShotLocation;
  }

  public String getCustomMsg() {
    return customMsg;
  }

  public void setCustomMsg(String customMsg) {
    this.customMsg = customMsg;
  }
}