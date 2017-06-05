package com.sci.testamigo.usermanagement.util;

import static com.sci.testamigo.usermanagement.common.Constants.XML;
import static com.sci.testamigo.usermanagement.common.Constants.XML_REPOSITORY;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.report.FeatureDetails;
import com.sci.testamigo.usermanagement.report.ScenarioDetails;

import cucumber.api.java.After;

public class AppSetup {

  WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
  ResourceBundle resourceBundle = null;
  RunnerSetup birunnersetup = new RunnerSetup();
  protected transient final Log log = LogFactory.getLog(getClass());
  FeatureDetails featureDeatils = new FeatureDetails();
  ScenarioDetails scenarioDeatils = new ScenarioDetails();

  // ==========================================================================

  public static String getXMLFilePath(String xmlFileName) {
    StringBuffer xmlFilePath = new StringBuffer("");
    xmlFilePath.append(System.getProperty("user.dir"));
    xmlFilePath.append(XML_REPOSITORY);
    xmlFilePath.append(xmlFileName);
    xmlFilePath.append(XML);
    return xmlFilePath.toString();
  }

  // ==========================================================================

  @After
  // Embed a screenshot in test report if test is marked as failed
  public byte[] CaptureScreen() {
    driver = PageCommonWD.getOpenBrowserHelp().getDriver();
    TakesScreenshot oScn = (TakesScreenshot) driver;
    byte[] screenshot = oScn.getScreenshotAs(OutputType.BYTES);
    return screenshot;
  }

  public void driverwait(int time) throws InterruptedException {
    Thread.sleep(time);
  }

  public String getFeatureName(String featureName) throws InterruptedException {
    String actualFeatureName = null;
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    File directory = new File("target/generated-test-sources/cucumber");
    File[] listofFiles = directory.listFiles();

    if (listofFiles != null) {
      for (int i = 0; i < listofFiles.length; i++) {
        if (listofFiles[i].getName().contains(featureName.toLowerCase() + ".feature")
            || listofFiles[i].getName().contains(featureName + ".feature")) {
          actualFeatureName = listofFiles[i].getName();
        }
      }
    }
    //System.out.println(">>>>>>>>>"+actualFeatureName);
    return actualFeatureName;
  }

  // ==========================================================================

  public static String getMethodName() {
    return Thread.currentThread().getStackTrace()[2].getMethodName();
  }

  // ===========================================================================
  
  public static String getScreenShotsFileName(String className, String type) {
	    StringBuffer screenShotFileName = new StringBuffer("");
	    screenShotFileName.append(className);
	    screenShotFileName.append(Constants.UNDER_SCORE);
	    screenShotFileName.append(type);
	    screenShotFileName.append(Constants.UNDER_SCORE);
	    screenShotFileName.append(Constants.time);
	    screenShotFileName.append(Constants.PNG);
	    return screenShotFileName.toString();
	  }

	  // ===========================================================================

  public void setClipboardData(String string) {
    try {
      // StringSelection is a class that can be used for copy and paste
      // operations.
      StringSelection stringSelection = new StringSelection(string);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

      Robot robot = new Robot();

      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  // ===========================================================================

  public String getModefiedStep(String stepName) {
    String[] stepWords = stepName.split(" ");
    stepName = "";
    for (int i = 0; i < stepWords.length; i++) {
      if (!stepWords[i].contains("\"")) {
        stepName = stepName + stepWords[i] + " ";
      }
    }
    return stepName;
  }
}
