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

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.PageObjects.Components.Footer;
import com.skillstorm.PageObjects.Components.Form;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;
import com.skillstorm.Utilities.UserData.User;

public class LoginPage extends Page{
//#region Static fields
    // Names
    public static final String FORM_LOGIN_NAME = "Login form";
    public static final String FOOTER_NAME = "Footer";

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
    public static final String BTN_DASHBOARDNAVBAR_ID = "navbar-dashboard";

    public static final String ALT_LOGIN_ID = "loginAlert";
//#endregion

    private Form formLogin;
    private Footer footer;

    @FindBy(id = BTN_SUBMIT_ID)
    private WebElement btnLoginSubmit;

    @FindBy(id = BTN_GOOGLE_SIGNIN_ID)
    private WebElement btnGoogleSignIn;

    @FindBy(id = BTN_CREATE_ACCOUNT_ID)
    private WebElement btnCreateAccount;

    @FindBy(id = BTN_SHOW_PASSWORD_ID)
    private WebElement btnShowPassword;

    @FindBy(id = BTN_DASHBOARDNAVBAR_ID)
    private WebElement btnDashboardNavbar;

    // Modal fields
    @FindBy(id = IN_USERNAME_ID)
    private WebElement loginField;
    
    @FindBy(id = IN_PASSWORD_ID)
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);

        // Initialize Navbar
        navbar = new LandingNavbar(driver);

        // Initialize form and add elements
        formLogin = new Form(driver);

        // Initialize Footer
        footer = new Footer(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters information for a given user into the login form and submits it.
     * @param user  User data to put into the form.
     * @return      Success rate of the login attempt.
     */
    public boolean login(User user){
        // Complete the form
        formLogin.sendInput(IN_USERNAME_NAME, user.getUsername());
        formLogin.sendInput(IN_PASSWORD_NAME, user.getPassword());
        formLogin.submit();

        // Check for success
        return !checkForAlerts();
    }

    /**
     * Checks if an alert pops up within 10ms
     * @return  Alert presence status.
     */
    public boolean checkForAlerts(){
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

    public void iEnterValidLoginInformation(String username, String password) {
        waitForElement(loginField, 10).sendKeys(username);
        waitForElement(passwordField, 10).sendKeys(password);
    }

    /**
     * Returns all buttons directly inside of this component.
     */
    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnLoginSubmit, btnCreateAccount, btnGoogleSignIn, btnShowPassword);
    }

    /**
     * Maps the button names in the static fields to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        
        if(button != null) {
            waitForElement(button, 10).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(btnDashboardNavbar));
        }
        else {
            throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
       
    }

    /**
     * Retrieves all Components directly under this Component.
     */
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(formLogin, footer);
    }

    /**
     * Retrieves a component given a name. Does not search child components.
     * @param name  Name of the component to retrieve.
     * @return      Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        if(name.equals(FORM_LOGIN_NAME)) return formLogin;  // Directly exists here
        if(name.equals(FOOTER_NAME)) return footer;

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
        webElements.add(btnLoginSubmit);
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
        switch (name) {
            case BTN_SUBMIT_NAME:
                System.out.println("Returning btnLoginSubmit");
                return btnLoginSubmit;
            default:
                for (Component component : getChildComponents()) {
                    WebElement webElement = component.getWebElement(name);
                    if(webElement != null) return webElement;
                }
                break;
        }

        return null;
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
