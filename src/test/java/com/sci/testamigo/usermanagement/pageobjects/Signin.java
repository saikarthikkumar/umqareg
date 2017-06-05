/**
 * @author Karthik
 *
 */

package com.sci.testamigo.usermanagement.pageobjects;

import org.openqa.selenium.WebElement;

import com.sci.testamigo.usermanagement.model.WebElementDetails;
import com.sci.testamigo.usermanagement.util.JWebElement;

public class Signin {

  // ==================================================================================

  public WebElement Username(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }

  // ==================================================================================

  public WebElement Password(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }

  // ==================================================================================

  public WebElement loginbtn(WebElementDetails webElementDetails) {
    return JWebElement.getWebElement(webElementDetails);
  }
}