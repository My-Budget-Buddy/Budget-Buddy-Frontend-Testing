/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Represents the Navbar that appears when a user is logged out.
 */

package com.skillstorm.PageObjects.Components.Navbar;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.skillstorm.PageObjects.Interfaces.Component;

public class LandingNavbar extends Navbar{
//#region Static fields
    // IDs
    public static final String BTN_LANDING_ID = "";
    public static final String BTN_LOGIN_ID = "";
    public static final String BTN_REGISTER_ID = "";

    // Names
    public static final String BTN_LANDING_NAME = "Navbar Budget Buddy Icon";
    public static final String BTN_LOGIN_NAME = "Navbar Log In";
    public static final String BTN_REGISTER_NAME = "Navbar Register";
//#endregion

    private WebElement btnLanding;
    private WebElement btnLogin;
    private WebElement btnRegister;

    public LandingNavbar(WebDriver driver) {
        super(driver);
    }

    /**
     * Maps a given button name to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        getWebElement(name).click();
    }

    @Override
    public List<Component> getChildComponents() {
        return null;
    }

    @Override
    public Component getChildComponent(String name) {
        return null;
    }

    @Override
    public List<WebElement> getWebElements() {
        return getButtons();
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_LANDING_NAME:
                return btnLanding;
            case BTN_LOGIN_NAME:
                return btnLogin;
            case BTN_REGISTER_NAME:
                return btnRegister;
            default:
                throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnLanding, btnLogin, btnRegister);
    }
}
