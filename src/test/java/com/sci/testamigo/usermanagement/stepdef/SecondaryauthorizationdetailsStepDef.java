package com.sci.testamigo.usermanagement.stepdef;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.sci.testamigo.usermanagement.common.Constants;
import com.sci.testamigo.usermanagement.exceptions.TestAmigoException;
import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.pageobjects.Secondaryauthorizationdeatils;
import com.sci.testamigo.usermanagement.pageobjects.Signin;
import com.sci.testamigo.usermanagement.util.AppSetup;
import com.sci.testamigo.usermanagement.util.JXMLParser;
import com.sci.testamigo.usermanagement.util.PageCommonWD;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SecondaryauthorizationdetailsStepDef {
	
	WebDriver driver = PageCommonWD.getOpenBrowserHelp().getDriver();
	  AppSetup appsetup = new AppSetup();
	  Signin login = new Signin();
	  protected transient final Log log = LogFactory.getLog(getClass());
	  ResourceBundle resourceBundle = null;

	  private List<WebElementDetails> webElementsList = JXMLParser.getInstance()
	      .getWebElements(Secondaryauthorizationdeatils.class.getSimpleName());
	  Secondaryauthorizationdeatils secondaryauthorizationdeatils = new Secondaryauthorizationdeatils();
	  String customMessage = null;
	  
	@Given("^user is on update secondary authorization details screen$")
	public void user_is_on_update_secondary_authorization_details_screen() throws Throwable {
		try {
			Thread.sleep(2000);
			WebElementDetails userNameBtnObj = webElementsList.get(0);
			secondaryauthorizationdeatils.userName(userNameBtnObj).click();
			
			WebElementDetails updateSecondaryAuthObj = webElementsList.get(1);
			secondaryauthorizationdeatils.updateSecondaryAuthrization(updateSecondaryAuthObj).click();
			
			Thread.sleep(1000);
			Assert.assertEquals("Update Secondary Authorization Details", driver.findElement(By.tagName("h3")).getText());
		    } catch (Exception e) {
		      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
		    } catch (java.lang.AssertionError e) {
		      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
		    }
	}

	@When("^i fill the authorization Details$")
	public void i_fill_the_authorization_Details() throws Throwable {
		try{
		Thread.sleep(1000);
		WebElementDetails firstQstionObj = webElementsList.get(2);
		new Select(secondaryauthorizationdeatils.firstQuestion(firstQstionObj)).selectByVisibleText(firstQstionObj.getData());
		
		WebElementDetails firstAnswerObj = webElementsList.get(3);
		secondaryauthorizationdeatils.firstAnswer(firstAnswerObj).sendKeys(firstAnswerObj.getData());
		
		WebElementDetails secondQstionObj = webElementsList.get(4);
		new Select(secondaryauthorizationdeatils.secondQuestion(secondQstionObj)).selectByVisibleText(secondQstionObj.getData());
		
		WebElementDetails secondAnswerObj = webElementsList.get(5);
		secondaryauthorizationdeatils.secondAnswer(secondAnswerObj).sendKeys(secondAnswerObj.getData());
		
	    } catch (Exception e) {
	      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
	    } catch (java.lang.AssertionError e) {
	      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
	    }
	}

	@Then("^i should be able to save the details successfully$")
	public void i_should_be_able_to_save_the_details_successfully() throws Throwable {
		try{
			
			WebElementDetails updateBtnObj = webElementsList.get(6);
			secondaryauthorizationdeatils.update(updateBtnObj).click();
			
		    } catch (Exception e) {
		      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
		    } catch (java.lang.AssertionError e) {
		      new TestAmigoException().handleException(e, customMessage, AppSetup.getMethodName());
		    }
	}

	@When("^i validate the authorization fields$")
	public void i_validate_the_authorization_fields() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^i should display the error messages on-screen$")
	public void i_should_display_the_error_messages_on_screen() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
