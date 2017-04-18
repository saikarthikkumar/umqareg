/**
 * @author Karthik
 *
 */

package com.sci.testamigo.usermanagement.pageobjects;

import org.openqa.selenium.WebElement;

import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.util.JWebElement;

public class Signout {

  // ==================================================================================

  public WebElement superUser(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }

  // ==================================================================================

  public WebElement editProfile(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }

  // ==================================================================================

  public WebElement changePassword(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }

  // ==================================================================================

  public WebElement signoutBtn(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }
}