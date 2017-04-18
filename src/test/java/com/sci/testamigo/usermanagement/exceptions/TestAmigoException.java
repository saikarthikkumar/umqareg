package com.sci.testamigo.usermanagement.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.report.ScenarioDetails;
import com.sci.testamigo.usermanagement.screenshots.ScreenShotManager;
import com.sci.testamigo.usermanagement.util.AppSetup;

public class TestAmigoException extends Throwable {
  protected transient final Log log = LogFactory.getLog(getClass());
  private Set m_Descriptions = new HashSet(5);
  private Exception m_Exception;
  AppSetup appSetup = new AppSetup();

  public TestAmigoException() {
    super();
  }

  // ==========================================================================

  public TestAmigoException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  // ==========================================================================

  public TestAmigoException(Throwable arg0) {
    super(arg0);
  }

  // ==========================================================================

  public TestAmigoException(PrintWriter out, Throwable exp) {
    exp.printStackTrace(out);
  }

  // ==========================================================================

  @SuppressWarnings("unchecked")
  public void addDescription(String description) {
    m_Descriptions.add(description);
  }

  // ==========================================================================

  @SuppressWarnings("unchecked")
  public String[] getDescriptions() {
    String[] desc = new String[m_Descriptions.size()];
    return (String[]) m_Descriptions.toArray(desc);
  }

  // ==========================================================================

  @SuppressWarnings("rawtypes")
  public Iterator iterator() {
    return m_Descriptions.iterator();
  }

  // ==========================================================================

  public TestAmigoException setException(Exception ex) {
    m_Exception = ex;
    return this;
  }

  // ==========================================================================

  public boolean hasException() {
    return (m_Exception != null);
  }

  // ==========================================================================

  public String getExceptionTrace() {
    if (hasException()) {
      StringWriter trace = new StringWriter();
      m_Exception.printStackTrace(new PrintWriter(trace));
      return trace.toString();
    } else {
      return "";
    }
  }

  // ==========================================================================

  public Exception getException() {
    return m_Exception;
  }

  // ==========================================================================

  public void handleException(Throwable e, String customMessage, String methodname) throws Throwable {
    String errorMsgSubString = null;
    e.printStackTrace();
    try {
      if (e.getMessage().length() > 256) {
        errorMsgSubString = e.getMessage().substring(0, 254);
      } else {
        errorMsgSubString = e.getMessage();
      }
    } catch (NullPointerException e2) {
    }
    int statusFlag = 0;
    String screenShotLocation = ScreenShotManager.getInstance().saveScreenShot(methodname, "error");
    String probleminStepName = methodname.replaceAll("_", " ");
    log.error("PROBLEM in the method " + methodname);
    for (int i = 0; i < Constants.FEATUREDETAILS.getScenarios().size(); i++) {
      ScenarioDetails scenarioDetails = Constants.FEATUREDETAILS.getScenarios().get(i);
      if (scenarioDetails.getScenarioName().trim().equals(Constants.SCEANRIO_NAME)) {
        for (int j = 0; j < scenarioDetails.getStepDetails().size(); j++) {
          String stepName = scenarioDetails.getStepDetails().get(j).getStep();
          if (appSetup.getModefiedStep(stepName).toLowerCase().contains(probleminStepName.toLowerCase())) {
            scenarioDetails.getStepDetails().get(j).setErrorMessage(errorMsgSubString);
            scenarioDetails.getStepDetails().get(j).setStatus("FAIL");
            scenarioDetails.getStepDetails().get(j).setCustomMsg(customMessage);
            scenarioDetails.getStepDetails().get(j).setScreenShotLocation(screenShotLocation);
            scenarioDetails.setErrorMsg(errorMsgSubString);
            scenarioDetails.setScreenShotLocation(screenShotLocation);
            statusFlag = 1;
          } else if ((!appSetup.getModefiedStep(stepName).toLowerCase().contains(probleminStepName.toLowerCase())) && statusFlag == 1) {
            break;
          } else {
            Constants.FEATUREDETAILS.getScenarios().get(i).getStepDetails().get(j).setStatus("PASS");
          }
        }
        break;
      }
    }
    // Now you can do whatever you need to do with it, for example copy
    // somewhere
    throw e;
  }
}