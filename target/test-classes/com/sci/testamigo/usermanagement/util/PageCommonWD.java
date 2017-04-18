package com.sci.testamigo.usermanagement.util;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.firefox.FirefoxBinary;
import com.google.common.collect.ImmutableMap;
import com.sci.testamigo.usermanagement.common.Constants;

import org.openqa.selenium.chrome.ChromeDriverService;
import java.io.IOException;

public class PageCommonWD {
  private WebDriver driver;
  private static PageCommonWD browserHelp;
  ResourceBundle resourceBundle = null;

  // Create new web driver instance
  private PageCommonWD() {
    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    if (resourceBundle.getString(Constants.BROWSER_NAME).equalsIgnoreCase("firefox")) {
      // Setup firefox binary to start in Xvfb        
        String Xport = System.getProperty("lmportal.xvfb.id", ":1");
        final File firefoxPath = new File(System.getProperty("lmportal.deploy.firefox.path", "/usr/bin/firefox"));
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
        driver = new FirefoxDriver(firefoxBinary, null);
    	//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
    } else if (resourceBundle.getString(Constants.BROWSER_NAME).equalsIgnoreCase("chrome")) {
	    String Xport = System.getProperty("lmportal.xvfb.id", ":1");
      ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
    	        .usingDriverExecutable(new File("/usr/bin/google-chrome"))
    	        .usingAnyFreePort().withEnvironment(ImmutableMap.of("DISPLAY", Xport)).build();
    	try {
			chromeDriverService.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	driver = new ChromeDriver(chromeDriverService);
    }
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  public static PageCommonWD getOpenBrowserHelp() {
    if (null == browserHelp) {
      browserHelp = new PageCommonWD();
    }
    return browserHelp;
  }

  public WebDriver getDriver() {
    return driver;
  }

  void setDriver(WebDriver driver) {
    this.driver = driver;
  }
}
