/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Represents the white navbar that appears when the user is either:
 *          - Logged out
 *          - On the landing page.
 */

package com.skillstorm.PageObjects.Components.Navbar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.PageObjects.Interfaces.Component;
import com.skillstorm.Utilities.UserData.LoginStatus;

import io.cucumber.java.it.Ma;

public class LandingNavbar extends Navbar{
//#region Static fields
    // IDs
    public static final String BTN_LANDING_ID = "landing-link";
    public static final String BTN_LOGIN_ID = "login-link";
    public static final String BTN_REGISTER_ID = "register-link";
    public static final String BTN_DASHBOARD_ID = "dashboard-link";
    public static final String BTN_LOGOUT_ID = "logout-button";

    // Names
    public static final String BTN_LANDING_NAME = "Navbar Budget Buddy Icon";
    public static final String BTN_LOGIN_NAME = "Navbar Log In";
    public static final String BTN_REGISTER_NAME = "Navbar Register";
    public static final String BTN_DASHBOARD_NAME = "Navbar Dashboard";
    public static final String BTN_LOGOUT_NAME = "Navbar Logout";
//#endregion

    private LoginStatus loginStatus;

    // WebElements
    @FindBy(id = BTN_LANDING_ID)
    private WebElement btnLanding;

    @FindBy(id = BTN_LOGIN_ID)
    private WebElement btnLogin;

    @FindBy(id = BTN_REGISTER_ID)
    private WebElement btnRegister;

    @FindBy(id = BTN_DASHBOARD_ID)
    private WebElement btnDashboard;

    @FindBy(id = BTN_LOGOUT_ID)
    private WebElement btnLogout;

    public LandingNavbar(WebDriver driver) {
        super(driver);

        loginStatus = LoginStatus.Unknown; // Just a default value.

        PageFactory.initElements(driver, this);
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
        checkLoginStatus();
        if(name.equals(BTN_LANDING_NAME)) return btnLanding;

        if(loginStatus == LoginStatus.LoggedIn){
            if(name.equals(BTN_DASHBOARD_NAME)) return btnDashboard;
            if(name.equals(BTN_LOGOUT_NAME)) return btnLogout;
        }else if(loginStatus == LoginStatus.LoggedOut){
            if(name.equals(BTN_LOGIN_NAME)) return btnLogin;
            if(name.equals(BTN_REGISTER_NAME)) return btnRegister;
        }else{
            throw new RuntimeException("Login status was Unknown.");
        }

        return null;
    }

    /**
     * Returns the buttons existent in LandingNavbar
     */
    @Override
    public List<WebElement> getButtons() {
        checkLoginStatus();
        return (loginStatus == LoginStatus.LoggedIn ? 
            Arrays.asList(btnLanding, btnLogin, btnRegister) :
            Arrays.asList(btnLanding, btnDashboard, btnLogout));
    }

    /**
     * Sets the login status and loads appropriate elements.
     */
    private void checkLoginStatus(){
        loginStatus = LoginStatus.Unknown;  // In case something crazy goes wrong.

        btnLanding = driver.findElement(By.id(BTN_LANDING_ID));

        try{
            // Logged in
            btnLogin = driver.findElement(By.id(BTN_LOGIN_ID));
            btnRegister = driver.findElement(By.id(BTN_REGISTER_ID));
            loginStatus = LoginStatus.LoggedOut;
        }catch(NoSuchElementException e){
            // Logged out.
            btnDashboard = driver.findElement(By.id(BTN_DASHBOARD_ID));
            btnLogout = driver.findElement(By.id(BTN_LOGOUT_ID));
            loginStatus = LoginStatus.LoggedIn;
        }
    }
}
