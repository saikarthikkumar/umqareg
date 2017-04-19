/**
 * @author Karthik
 *
 */
 
package com.sci.testamigo.pqt.stepdef;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.sci.testamigo.pqt.exceptions.TestAmigoException;
import com.sci.testamigo.pqt.model.WebElementDetails;
import com.sci.testamigo.pqt.pageobjects.EditProfile;
import com.sci.testamigo.pqt.util.AppSetup;
import com.sci.testamigo.pqt.util.JXMLParser;
import com.sci.testamigo.pqt.util.PageCommonWD;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class EditProfileStepDef {
  WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
  protected transient final Log log = LogFactory.getLog(getClass());
  EditProfile editprofile = new EditProfile();
  AppSetup appsetup = new AppSetup();
  private List<WebElementDetails> webElementsList = JXMLParser.getInstance()
      .getWebElements(EditProfile.class.getSimpleName());
  String customMessage = null;

  // ==================================================================================

  @Given("^user can update profile details$")
  public void user_can_update_profile_details() throws Throwable {
    try {
      customMessage = "Clicking on Menu SCI User button";
      WebElementDetails menuSCIUserBtnObj = webElementsList.get(0);
      editprofile.menuSCIUserBtn(menuSCIUserBtnObj).click();

      customMessage = "Clicking on Edit Profile button";
      WebElementDetails editProfileBtnObj = webElementsList.get(1);
      editprofile.editProfileBtn(editProfileBtnObj).click();
      Thread.sleep(1000);

      customMessage = "Enter First Name";
      WebElementDetails firstNameTxtBoxObj = webElementsList.get(3);
      editprofile.firstNameTxtBox(firstNameTxtBoxObj).clear();
      editprofile.firstNameTxtBox(firstNameTxtBoxObj).sendKeys(firstNameTxtBoxObj.getData());

      customMessage = "Enter Middle Name";
      WebElementDetails middletNameTxtBoxObj = webElementsList.get(4);
      editprofile.middletNameTxtBox(middletNameTxtBoxObj).clear();
      editprofile.middletNameTxtBox(middletNameTxtBoxObj).sendKeys(middletNameTxtBoxObj.getData());

      customMessage = "Enter Last Name";
      WebElementDetails lastNameTxtBoxObj = webElementsList.get(5);
      editprofile.lastNameTxtBox(lastNameTxtBoxObj).clear();
      editprofile.lastNameTxtBox(lastNameTxtBoxObj).sendKeys(lastNameTxtBoxObj.getData());

      customMessage = "Enter Email";
      WebElementDetails emailTxtBoxObj = webElementsList.get(6);
      editprofile.emailTxtBox(emailTxtBoxObj).clear();
      editprofile.emailTxtBox(emailTxtBoxObj).sendKeys(emailTxtBoxObj.getData());

      customMessage = "Enter Mobile Number";
      WebElementDetails mobileNOTxtBoxObj = webElementsList.get(7);
      editprofile.mobileNOTxtBox(mobileNOTxtBoxObj).clear();
      editprofile.mobileNOTxtBox(mobileNOTxtBoxObj).sendKeys(mobileNOTxtBoxObj.getData());

      customMessage = "Enter Address line1";
      WebElementDetails address1TxtBoxObj = webElementsList.get(8);
      editprofile.address1TxtBox(address1TxtBoxObj).clear();
      editprofile.address1TxtBox(address1TxtBoxObj).sendKeys(address1TxtBoxObj.getData());

      customMessage = "Enter Address line2";
      WebElementDetails address2TxtBoxObj = webElementsList.get(9);
      editprofile.address2TxtBox(address2TxtBoxObj).clear();
      editprofile.address2TxtBox(address2TxtBoxObj).sendKeys(address2TxtBoxObj.getData());

      customMessage = "Enter City";
      WebElementDetails cityTxtBoxObj = webElementsList.get(10);
      editprofile.cityTxtBox(cityTxtBoxObj).clear();
      editprofile.cityTxtBox(cityTxtBoxObj).sendKeys(cityTxtBoxObj.getData());

      customMessage = "Enter State";
      WebElementDetails stateTxtBoxObj = webElementsList.get(11);
      editprofile.stateTxtBox(stateTxtBoxObj).clear();
      editprofile.stateTxtBox(stateTxtBoxObj).sendKeys(stateTxtBoxObj.getData());

      customMessage = "Select Country";
      WebElementDetails countryDropdownObj = webElementsList.get(12);
      new Select(editprofile.countryDropdown(countryDropdownObj))
          .selectByVisibleText(countryDropdownObj.getData());

      customMessage = "Enter Posta Code";
      WebElementDetails postalTXtBoxObj = webElementsList.get(13);
      editprofile.postalTXtBox(postalTXtBoxObj).clear();
      editprofile.postalTXtBox(postalTXtBoxObj).sendKeys(postalTXtBoxObj.getData());

      customMessage = "Clicking on update button";
      WebElementDetails updateBtnObj = webElementsList.get(14);
      editprofile.updateBtn(updateBtnObj).click();
      appsetup.driverwait(2000);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  // ==================================================================================

  @Then("^verify the updated message$")
  public void verify_the_updated_message() throws Throwable {
    try {
      customMessage = "Mandaroty fields are displaying";
      WebElementDetails errorMessageObj = webElementsList.get(17);
      WebElementDetails updateMessageObj = webElementsList.get(16);
      if (editprofile.errorMessage(errorMessageObj).isDisplayed()) {
        log.info("Madatory Fields are: " + editprofile.errorMessage(errorMessageObj).getText());
        Assert.assertTrue(editprofile.errorMessage(errorMessageObj).isDisplayed());
      } else if (editprofile.updateMessage(updateMessageObj).isDisplayed()) {
        Assert.assertEquals(editprofile.updateMessage(updateMessageObj).getText(),
            "Profile updated successfully");
      }
      appsetup.driverwait(1500);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  @Then("^Enter Valid Credentials in old,new and confirm password fields$")
  public void enter_Valid_Credentials_in_old_new_and_confirm_password_fields() throws Throwable {
    try {
      customMessage = "Clicking on Menu SCI User button";
      WebElementDetails menuSCIUserBtnObj = webElementsList.get(0);
      editprofile.menuSCIUserBtn(menuSCIUserBtnObj).click();

      customMessage = "Clicking on Change Password button";
      WebElementDetails changePwdBtnObj = webElementsList.get(2);
      editprofile.changePwdBtn(changePwdBtnObj).click();
      Thread.sleep(1000);

      customMessage = "Enter Old Password";
      WebElementDetails oldPwdTxtBoxObj = webElementsList.get(18);
      editprofile.oldPwdTxtBox(oldPwdTxtBoxObj).sendKeys(oldPwdTxtBoxObj.getData());

      customMessage = "Enter New Password";
      WebElementDetails newPwdTxtBoxObj = webElementsList.get(19);
      editprofile.newPwdTxtBox(newPwdTxtBoxObj).sendKeys(newPwdTxtBoxObj.getData());

      customMessage = "Enter Confirm New Password";
      WebElementDetails confirmNewPwdObj = webElementsList.get(20);
      editprofile.confirmNewPwd(confirmNewPwdObj).sendKeys(confirmNewPwdObj.getData());
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  @Then("^click on change passowrd button$")
  public void click_on_change_passowrd_button() throws Throwable {
    try {
      customMessage = "Click on Change Password save button";
      WebElementDetails changePwdSaveBtnObj = webElementsList.get(21);
      editprofile.changePwdSaveBtn(changePwdSaveBtnObj).click();
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }

  @Then("^Verify the status message$")
  public void verify_the_status_message() throws Throwable {
    try {
      customMessage = "Verify the Password status messages";
      WebElementDetails errorMessageObj = webElementsList.get(17);
      WebElementDetails updateMessageObj = webElementsList.get(22);
      if (editprofile.errorMessage(errorMessageObj).isDisplayed()) {
        log.info("Password Madatory Fields are: " + editprofile.errorMessage(errorMessageObj).getText());
        Assert.assertTrue(editprofile.errorMessage(errorMessageObj).isDisplayed());
      } else if (editprofile.updateMessage(updateMessageObj).isDisplayed()) {
        Assert.assertEquals(editprofile.updateMessage(updateMessageObj).getText(),
            "Password changed successfully");
        log.info("Password Changed successfully");
      }
      appsetup.driverwait(1500);
    } catch (Exception e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    } catch (java.lang.AssertionError e) {
      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
    }
  }
}