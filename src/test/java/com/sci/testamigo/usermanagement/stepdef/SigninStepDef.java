package com.sci.testamigo.usermanagement.stepdef;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.exceptions.TestAmigoException;
import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.pageobjects.Signin;
import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.JXMLParser;
import com.sci.testamigo.usermanagement.util.PageCommonWD;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class SigninStepDef {
  WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
  AppSetup appsetup = new AppSetup();
  Signin login = new Signin();
  protected transient final Log log = LogFactory.getLog(getClass());
  ResourceBundle resourceBundle = null;

  private List<WebElementDetails> webElementsList = JXMLParser.getInstance()
      .getWebElements(Signin.class.getSimpleName());
  String customMessage = null;
  // ==================================================================================

  @BeforeClass
  public void setup() {
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  // ==================================================================================

  @Given("^user is redirected to Login page$")
  public void user_is_redirected_to_Login_page() throws Throwable {
    try {
      log.info("Start of the Scenario Login to User Management application as a user");
      customMessage = "Navigating to URL";
      resourceBundle = ResourceBundle.getBundle("ApplicationResources");
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      driver.get(resourceBundle.getString(Constants.APPLICATION_URL));
      Assert.assertTrue("Landing page failed", driver.getTitle().equals("User Management"));
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  // ==================================================================================

  @And("^enters valid credentials and click on Signin button$")
  public void enters_valid_credentials_and_click_on_Signin_button() throws Throwable {
    try {
      log.info("Start of the Scenario Sign in Page title Verification");
      Assert.assertEquals("Sign in Page title Verification","Sign in", driver.findElement(By.className("panel-title")).getText());
      log.info("End of the Scenario Sign in Page title Verification");
      customMessage = "Enter Username";
      WebElementDetails userNameTextBoxObj = webElementsList.get(0);
      login.Username(userNameTextBoxObj).sendKeys(userNameTextBoxObj.getData());

      customMessage = "Enter Password";
      WebElementDetails passwordTextBoxObj = webElementsList.get(1);
      login.Password(passwordTextBoxObj).sendKeys(passwordTextBoxObj.getData());

      customMessage = "Clicking on Signin Button";
      WebElementDetails loginBtnObj = webElementsList.get(2);
      login.loginbtn(loginBtnObj).click();
      
      Thread.sleep(12000);
      customMessage = "Verifying the login";
      log.info("Start of the Scenario Home Page title Verification");
      //Assert.assertEquals("Home Page title Verification","Welcome to the Demo EPA User", driver.findElement(By.xpath(".//*[@id='wrap']/div/div[2]/div/div/div/h1")).getText());
      Assert.assertEquals("Home Page title Verification Failed","Welcome to the Demo EPA User", driver.findElement(By.id("welcome_msg_lbl")).getText());
      log.info("End of the Scenario Home Page title:Welcome to the Demo EPA User Verification Successful");
      Assert.assertEquals("Login Failed", true, driver.findElements(By.id("user_details_tab")).size()>0);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  // ==================================================================================
}
