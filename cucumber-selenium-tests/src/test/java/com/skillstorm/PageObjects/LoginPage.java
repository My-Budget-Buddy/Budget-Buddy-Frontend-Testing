/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents the login page.
 */

package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.PageObjects.Components.Form;
import com.skillstorm.PageObjects.Components.Navbar.LoggedOutNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;
import com.skillstorm.Utilities.Authenticator;
import com.skillstorm.Utilities.UserData.User;

public class LoginPage extends Page{
//#region Static fields
    // Names
    public static final String FORM_LOGIN_NAME = "Login form";

    public static final String IN_USERNAME_NAME = "Username Field";
    public static final String IN_PASSWORD_NAME = "Password Field";

    public static final String BTN_SUBMIT_NAME = "Submit";
    public static final String BTN_GOOGLE_SIGNIN_NAME = "Sign In With Google";
    public static final String BTN_CREATE_ACCOUNT_NAME = "Create Account";
    public static final String BTN_SHOW_PASSWORD_NAME = "Show Password";

    // Login form locators
    public static final String IN_USERNAME_ID = "email";
    public static final String IN_PASSWORD_ID = "password";
    public static final String BTN_SUBMIT_ID = "btnLoginSubmit";

    // Other locators
    public static final String BTN_GOOGLE_SIGNIN_ID = "btnGoogleSignIn";
    public static final String BTN_CREATE_ACCOUNT_ID = "linkRegister";
    public static final String BTN_SHOW_PASSWORD_ID = "btnShowLoginPassword";

    public static final String ALT_LOGIN_ID = "loginAlert";
//#endregion

    private Form formLogin;

    @FindBy(id = BTN_GOOGLE_SIGNIN_ID)
    private WebElement btnGoogleSignIn;

    @FindBy(id = BTN_CREATE_ACCOUNT_ID)
    private WebElement btnCreateAccount;

    @FindBy(id = BTN_SHOW_PASSWORD_ID)
    private WebElement btnShowPassword;

    public LoginPage(WebDriver driver) {
        super(driver);

        navbar = new LoggedOutNavbar(driver);

        // Initialize form and add elements
        formLogin = new Form(driver);

        formLogin.setInput(IN_USERNAME_ID, IN_USERNAME_NAME);
        formLogin.setInput(IN_PASSWORD_ID, IN_PASSWORD_NAME);
        formLogin.setBtnSubmit(driver.findElement(By.id(BTN_SUBMIT_ID)));
    }

    public boolean login(User user){
        // Complete the form
        formLogin.sendInput(IN_USERNAME_NAME, user.getUsername());
        formLogin.sendInput(IN_PASSWORD_NAME, user.getPassword());
        formLogin.submit();

        // Check for success
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }
        return foundAlert;
    }

    /**
     * Maps the button names in the static fields to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        switch (name) {
            case BTN_SUBMIT_NAME:
                formLogin.submit();
                break;
            case BTN_GOOGLE_SIGNIN_NAME:
                clickBtnGoogleSignIn();
            case BTN_CREATE_ACCOUNT_NAME:
                clickBtnCreateAccount();
            case BTN_SHOW_PASSWORD_NAME:
                clickBtnShowPassword();
            default:
                throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }

    /**
     * Clicks the show password button.
     */
    private void clickBtnShowPassword() {
        btnShowPassword.click();
    }

    /**
     * Clicks the register button.
     */
    private void clickBtnCreateAccount() {
        btnCreateAccount.click();
    }

    /**
     * Clicks the google sign in button.
     */
    private void clickBtnGoogleSignIn() {
        btnGoogleSignIn.click();
    }

    /**
     * Retrieves all Components directly under this Component.
     */
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(formLogin);
    }

    /**
     * Retrieves a component given a name. Does not search child components.
     * @param name  Name of the component to retrieve.
     * @return      Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        if(name.equals(FORM_LOGIN_NAME)) return formLogin;  // Directly exists here

        // Couldn't find.
        return null;
    }

    /**
     * Retrieves all WebElements in this component and child components.
     */
    @Override
    public List<WebElement> getWebElements() {
        List<WebElement> webElements = new ArrayList<WebElement>();

        // Add this Component's web elements
        webElements.add(btnCreateAccount);
        webElements.add(btnGoogleSignIn);
        webElements.add(btnShowPassword);

        // Add child components' web elements
        for (Component component : getChildComponents()) {
            for (WebElement webElement : component.getWebElements()) {
                webElements.add(webElement);
            }
        }

        return webElements;
    }

    /**
     * Retrieves a WebElement given a name. Searches child components as well.
     * @param name  Name of the WebElement to search for.
     * @return      WebElement tied to that name, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        // Check in this component
        if(name.equals(BTN_CREATE_ACCOUNT_NAME)) return btnCreateAccount;
        else if(name.equals(BTN_GOOGLE_SIGNIN_NAME)) return btnGoogleSignIn;
        else if(name.equals(BTN_SHOW_PASSWORD_NAME)) return btnShowPassword;

        // Check in sub-components
        for (Component component : getChildComponents()) {
            WebElement found = component.getWebElement(name);
            if(found != null) return found;
        }

        // Not found.
        return null;
    }

    /**
     * Returns all buttons directly inside of this component.
     */
    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnCreateAccount, btnGoogleSignIn, btnShowPassword);
    }
}
