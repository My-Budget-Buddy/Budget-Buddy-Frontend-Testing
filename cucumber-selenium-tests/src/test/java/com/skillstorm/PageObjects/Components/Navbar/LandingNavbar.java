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
import com.skillstorm.Utilities.UserData.LoginStatus;

public class LandingNavbar extends Navbar{
//#region Static fields
    // IDs
    public static final String BTN_LANDING_ID = "";
    public static final String BTN_LOGIN_ID = "";
    public static final String BTN_REGISTER_ID = "";
    public static final String BTN_DASHBOARD_ID = "";
    public static final String BTN_LOGOUT_ID = "";

    // Names
    public static final String BTN_LANDING_NAME = "Navbar Budget Buddy Icon";
    public static final String BTN_LOGIN_NAME = "Navbar Log In";
    public static final String BTN_REGISTER_NAME = "Navbar Register";
    public static final String BTN_DASHBOARD_NAME = "";
    public static final String BTN_LOGOUT_NAME = "";
//#endregion

    private LoginStatus loginStatus;

    // WebElements
    private WebElement btnLanding;

    private WebElement btnLogin;
    private WebElement btnRegister;

    private WebElement btnDashboard;
    private WebElement btnLogout;

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

    /**
     * LandingNavbar has no child components.
     * @return null.
     */
    @Override
    public List<Component> getChildComponents() {
        return null;
    }

    /**
     * LandingNavbar has no child components.
     * @return null.
     */
    @Override
    public Component getChildComponent(String name) {
        return null;
    }

    /**
     * Returns the buttons existent in LandingNavbar
     */
    @Override
    public List<WebElement> getWebElements() {
        return getButtons();
    }

    /**
     * Returns a web element based on the name given.
     */
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

    /**
     * Returns the buttons existent in LandingNavbar
     */
    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnLanding, btnLogin, btnRegister);
    }
}
