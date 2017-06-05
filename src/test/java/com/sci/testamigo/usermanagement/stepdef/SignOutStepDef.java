/**
 * @author Karthik
 *
 */
 
package com.sci.testamigo.usermanagement.stepdef;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sci.testamigo.usermanagement.exceptions.TestAmigoException;
import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.pageobjects.Signout;
import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.JXMLParser;
import com.sci.testamigo.usermanagement.util.PageCommonWD;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SignOutStepDef {
  WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
  protected transient final Log log = LogFactory.getLog(getClass());
  Signout signout = new Signout();
  private List<WebElementDetails> webElementsList = JXMLParser.getInstance()
      .getWebElements(Signout.class.getSimpleName());
  String customMessage = null;

  // ==================================================================================

  @Given("^click on Logout button$")
  public void click_on_Logout_button() throws Throwable {
    try {
      customMessage = "Clicking on Super User button";
      WebElementDetails superUserBtnObj = webElementsList.get(0);
      signout.superUser(superUserBtnObj).click();
      Thread.sleep(1000);
      customMessage = "Clicking on Logout button";
      WebElementDetails logoutBtnObj = webElementsList.get(1);
      signout.signoutBtn(logoutBtnObj).click();
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  // ==================================================================================

  @Then("^verify Login page title$")
  public void verify_Login_page_title() throws Throwable {
    try {
      driver = PageCommonWD.getOpenBrowserHelp().getDriver();
      customMessage = "Verifying the logout message";
      Assert.assertTrue(
          driver.findElement(By.id("user_logout_msg")).getText().equals("You have been successfully logout"));
      log.info("Successfully Loggedout from the Application");
      driver.quit();
      log.info("Broswer Closed");
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }
}