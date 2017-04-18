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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sci.testamigo.usermanagement.exceptions.TestAmigoException;
import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.pageobjects.Users;
import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.JXMLParser;
import com.sci.testamigo.usermanagement.util.PageCommonWD;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class UserStepDef {
  WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
  protected transient final Log log = LogFactory.getLog(getClass());
  Users users = new Users();
  AppSetup appsetup = new AppSetup();
  private List<WebElementDetails> webElementsList = JXMLParser.getInstance()
      .getWebElements(Users.class.getSimpleName());
  String customMessage = null;

  // ==================================================================================

  @Given("^Registering a user by Admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
  public void registering_a_user_by_Admin(String user, String pwd, String cpwd, String role, String fname, String mname,
      String lname, String email, String mobile, String add1, String add2, String city, String state,
      String country, String zip) throws Throwable {
    try {
      customMessage = "Clicking on Menu User button";
      WebElementDetails menuUsersBtnObj = webElementsList.get(0);
      users.menuUsersBtn(menuUsersBtnObj).click();

      Thread.sleep(1000);
      customMessage = "Clicking on Add User button";
      WebElementDetails addUserBtnObj = webElementsList.get(1);
      users.addUserBtn(addUserBtnObj).click();
      Thread.sleep(1000);

      customMessage = "Enter Username";
      WebElementDetails userNameTxtBoxObj = webElementsList.get(2);
      users.userNameTxtBox(userNameTxtBoxObj).sendKeys(user);

      Thread.sleep(1000);
      customMessage = "Enter Password";
      WebElementDetails paswordTxtBoxObj = webElementsList.get(3);
      users.paswordTxtBox(paswordTxtBoxObj).sendKeys(pwd);

      Thread.sleep(1000);
      customMessage = "Enter Confirm Password";
      WebElementDetails confirmPaswordTxtBoxObj = webElementsList.get(4);
      users.confirmPaswordTxtBox(confirmPaswordTxtBoxObj).sendKeys(cpwd);

      customMessage = "Select Role";
      WebElementDetails roleDropdownObj = webElementsList.get(5);
      new Select(users.roleDropdown(roleDropdownObj)).selectByVisibleText(role);

      customMessage = "Enter First Name";
      WebElementDetails firstNameTxtBoxObj = webElementsList.get(6);
      users.firstNameTxtBox(firstNameTxtBoxObj).sendKeys(fname);

      customMessage = "Enter Middle Name";
      WebElementDetails middletNameTxtBoxObj = webElementsList.get(7);
      users.middletNameTxtBox(middletNameTxtBoxObj).sendKeys(mname);

      Thread.sleep(1000);
      customMessage = "Enter Last Name";
      WebElementDetails lastNameTxtBoxObj = webElementsList.get(8);
      users.lastNameTxtBox(lastNameTxtBoxObj).sendKeys(lname);

      customMessage = "Enter Email";
      WebElementDetails emailTxtBoxObj = webElementsList.get(9);
      users.emailTxtBox(emailTxtBoxObj).sendKeys(email);

      customMessage = "Enter Mobile Number";
      WebElementDetails mobileNOTxtBoxObj = webElementsList.get(10);
      users.mobileNOTxtBox(mobileNOTxtBoxObj).sendKeys(mobile);

      customMessage = "Enter Address line1";
      WebElementDetails address1TxtBoxObj = webElementsList.get(11);
      users.address1TxtBox(address1TxtBoxObj).sendKeys(add1);

      customMessage = "Enter Address line2";
      WebElementDetails address2TxtBoxObj = webElementsList.get(12);
      users.address2TxtBox(address2TxtBoxObj).sendKeys(add2);

      customMessage = "Enter City";
      WebElementDetails cityTxtBoxObj = webElementsList.get(13);
      users.cityTxtBox(cityTxtBoxObj).sendKeys(city);

      customMessage = "Enter State";
      WebElementDetails stateTxtBoxObj = webElementsList.get(14);
      users.stateTxtBox(stateTxtBoxObj).sendKeys(state);

      customMessage = "Select Country";
      WebElementDetails countryDropdownObj = webElementsList.get(15);
      new Select(users.countryDropdown(countryDropdownObj)).selectByVisibleText(country);

      customMessage = "Enter Posta Code";
      WebElementDetails postalTXtBoxObj = webElementsList.get(16);
      users.postalTXtBox(postalTXtBoxObj).sendKeys(zip);

      customMessage = "Clicking on Save button";
      WebElementDetails saveBtnObj = webElementsList.get(17);
      users.saveBtn(saveBtnObj).click();
      
      Thread.sleep(1000);
      customMessage = "Mandaroty fields are displaying";
      WebElementDetails errorMessagesObj = webElementsList.get(24);
      
      if (driver.findElements(By.cssSelector(errorMessagesObj.getCssSelector())).size()!=0) {
        log.info("Madatory Fields are: " + users.errorMessages(errorMessagesObj).getText());
        Assert.assertTrue(users.errorMessages(errorMessagesObj).isDisplayed());
      }

      /*
       * customMessage = "Clicking on Rest button"; WebElementDetails
       * resetBtnObj = webElementsList.get(18);
       * users.resetBtn(resetBtnObj).click();
       */
      appsetup.driverwait(2000);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  // ==================================================================================

  @And("^Modify and Deletes the \"([^\"]*)\" user and perform \"([^\"]*)\" and using details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
  public void modify_and_Deletes_the_user_and_perform_and_using_details(String Customer, String Action, String fname, String mname, String lname,
      String email, String mobile, String add1, String add2, String city, String state, String country,
      String zip) throws Throwable {
    try {

      customMessage = "Clicking on Menu User button";
      WebElementDetails menuUsersBtnObj = webElementsList.get(0);
      users.menuUsersBtn(menuUsersBtnObj).click();

      customMessage = "Clicking on Users button";
      WebElementDetails usersBtnObj = webElementsList.get(19);
      users.usersBtn(usersBtnObj).click();
      appsetup.driverwait(2000);

      customMessage = "Getting the table data of Edit, Deactivate, Reset & Activate";
      WebElementDetails userTableViewObj = webElementsList.get(23);
      WebElement table = users.userTableView(userTableViewObj);
      WebElement tbody = table.findElement(By.tagName("tbody"));
      List<WebElement> rows = tbody.findElements(By.tagName("tr"));
      List<WebElement> cells = null;
      int j = 0;

      for (int i = 1; i < rows.size(); i++) {
        cells = rows.get(i).findElements(By.tagName("td"));
        String orderid = cells.get(0).getText();
        if (Customer.equalsIgnoreCase(orderid)) {
          Customer = orderid;
          j = i;
          break;
        }
      }

      switch (Action) {
      case "edit":
        cells = rows.get(j).findElements(By.tagName("td"));
        customMessage = "Clicking on edit site";
        cells.get(7).findElement(By.cssSelector(".fa.fa-pencil-square-o.editSite")).click();
        appsetup.driverwait(1000);

        customMessage = "Enter First Name";
        WebElementDetails firstNameTxtBoxObj = webElementsList.get(6);
        users.firstNameTxtBox(firstNameTxtBoxObj).clear();
        users.firstNameTxtBox(firstNameTxtBoxObj).sendKeys(fname);

        customMessage = "Enter Middle Name";
        WebElementDetails middletNameTxtBoxObj = webElementsList.get(7);
        users.middletNameTxtBox(middletNameTxtBoxObj).clear();
        users.middletNameTxtBox(middletNameTxtBoxObj).sendKeys(mname);

        customMessage = "Enter Last Name";
        WebElementDetails lastNameTxtBoxObj = webElementsList.get(8);
        users.lastNameTxtBox(lastNameTxtBoxObj).clear();
        users.lastNameTxtBox(lastNameTxtBoxObj).sendKeys(lname);

        customMessage = "Enter Email";
        WebElementDetails emailTxtBoxObj = webElementsList.get(9);
        users.emailTxtBox(emailTxtBoxObj).clear();
        users.emailTxtBox(emailTxtBoxObj).sendKeys(email);

        customMessage = "Enter Mobile Number";
        WebElementDetails mobileNOTxtBoxObj = webElementsList.get(26);
        users.mobileNOTxtBox(mobileNOTxtBoxObj).clear();
        users.mobileNOTxtBox(mobileNOTxtBoxObj).sendKeys(mobile);

        customMessage = "Enter Address line1";
        WebElementDetails address1TxtBoxObj = webElementsList.get(11);
        users.address1TxtBox(address1TxtBoxObj).clear();
        users.address1TxtBox(address1TxtBoxObj).sendKeys(add1);

        customMessage = "Enter Address line2";
        WebElementDetails address2TxtBoxObj = webElementsList.get(12);
        users.address2TxtBox(address2TxtBoxObj).clear();
        users.address2TxtBox(address2TxtBoxObj).sendKeys(add2);

        customMessage = "Enter City";
        WebElementDetails cityTxtBoxObj = webElementsList.get(13);
        users.cityTxtBox(cityTxtBoxObj).clear();
        users.cityTxtBox(cityTxtBoxObj).sendKeys(city);

        customMessage = "Enter State";
        WebElementDetails stateTxtBoxObj = webElementsList.get(14);
        users.stateTxtBox(stateTxtBoxObj).clear();
        users.stateTxtBox(stateTxtBoxObj).sendKeys(state);

        customMessage = "Select Country";
        WebElementDetails countryDropdownObj = webElementsList.get(15);
        new Select(users.countryDropdown(countryDropdownObj)).selectByVisibleText(country);

        customMessage = "Enter Posta Code";
        WebElementDetails postalTXtBoxObj = webElementsList.get(16);
        users.postalTXtBox(postalTXtBoxObj).clear();
        users.postalTXtBox(postalTXtBoxObj).sendKeys(zip);

        customMessage = "Click on Update button";
        WebElementDetails updateBtnObj = webElementsList.get(25);
        users.updateBtn(updateBtnObj).click();

        customMessage = "Verifying user updation message";
        Assert.assertEquals("User updated successfully",
            driver.findElement(By.id("user_updated_msg")).getText());
        break;
      case "deactivate":
        cells = rows.get(j).findElements(By.tagName("td"));
        customMessage = "Clicking on Deactivate button";
        cells.get(7).findElements(By.tagName("a")).get(2).click();
        driver.findElement(By.id("confirm_deactivate_user_btn")).click();

        WebDriverWait waitDeactivate = new WebDriverWait(driver, 15);
        waitDeactivate.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_deactivated_msg")));

        customMessage = "Verifying user deactivation message";
        Assert.assertEquals("User Deactivated successfully",
            driver.findElement(By.id("user_deactivated_msg")).getText());
        break;
      case "reset":
        cells = rows.get(j).findElements(By.tagName("td"));
        customMessage = "Clicking on Reset button";
        cells.get(7).findElement(By.cssSelector(".fa.fa-refresh.editSite")).click();
        driver.findElement(By.id("confirm_reset_password_btn")).click();

        WebDriverWait waitReset = new WebDriverWait(driver, 15);
        waitReset.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_reset_msg")));

        customMessage = "Verifying user reset message";
        Assert.assertEquals("Password reset successfully",
            driver.findElement(By.id("user_password_reset_msg")).getText());
        break;
      case "activate":
        cells = rows.get(j).findElements(By.tagName("td"));
        customMessage = "Clicking on User Acivate button";
        cells.get(7).findElement(By.cssSelector(".fa.fa-user.userActivate")).click();
        appsetup.driverwait(1000);
        driver.findElement(By.id("confirm_activate_user_btn")).click();
        WebDriverWait waitActivate = new WebDriverWait(driver, 15);
        waitActivate.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_activated_msg")));

        customMessage = "Verifying user activation message";
        Assert.assertEquals("User Activated successfully",
            driver.findElement(By.id("user_activated_msg")).getText());
        break;
      default:
        log.info("User Modified Succesfuuly");
        break;
      }
      appsetup.driverwait(5000);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }
}