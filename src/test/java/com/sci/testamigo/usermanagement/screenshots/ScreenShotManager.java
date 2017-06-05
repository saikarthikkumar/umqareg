/**
 * @author Jp
 *
 */
package com.sci.testamigo.usermanagement.screenshots;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.PageCommonWD;

public class ScreenShotManager {

  private static ScreenShotManager manager = null;
  
  //==========================================================================
  
  public static synchronized ScreenShotManager getInstance() {
    if(manager == null) {
      manager = new ScreenShotManager();
    }
    return manager;
  }
  
  //==========================================================================
  
  private ScreenShotManager() { }

  //==========================================================================
  
  public String saveScreenShot(String className, String type) {
    File scrFile = null;
    String screenShotLocation = null;
    String screenShotName = null;
    
    try {
      scrFile = ((TakesScreenshot) PageCommonWD.getOpenBrowserHelp().getDriver()).getScreenshotAs(OutputType.FILE);
      
      //screenShotLocation = AppSetup.getScreenShotsRepositoryFilePath(className, type);
      screenShotName = AppSetup.getScreenShotsFileName(className, type);
      screenShotLocation = "//34.198.120.187//sci_aws_devops_taf_ssr" + screenShotName;
      FileUtils.copyFile(scrFile, new File(screenShotLocation));
      screenShotLocation = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/testmate_app_data/screen_shots_repository/"+screenShotName;
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return screenShotLocation;
  }
  //==========================================================================
}
