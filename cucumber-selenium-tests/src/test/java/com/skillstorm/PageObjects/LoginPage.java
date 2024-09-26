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

import org.openqa.selenium.By;
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

public class LoginPage extends Page {
    // #region Static fields
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

    // Dashboard locator
    public static final String BTN_DASHBOARDNAVBAR_ID = "navbar-dashboard";

    // Signup locator
    public static final String BTN_REGISTERACCOUNT_ID = "create-account";

    public static final String ALT_LOGIN_ID = "loginAlert";
    // #endregion

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

    @FindBy(id = BTN_REGISTERACCOUNT_ID)
    private WebElement btnRegisterAccount;

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

        formLogin.setInput(IN_USERNAME_ID, IN_USERNAME_NAME);
        formLogin.setInput(IN_PASSWORD_ID, IN_PASSWORD_NAME);
        formLogin.setBtnSubmit(driver.findElement(By.id(BTN_SUBMIT_ID)), BTN_SUBMIT_NAME);

        // Initialize Footer
        footer = new Footer(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters information for a given user into the login form and submits it.
     * 
     * @param user User data to put into the form.
     * @return Success rate of the login attempt.
     */
    public boolean login(User user) {
        // Complete the form
        formLogin.sendInput(IN_USERNAME_NAME, user.getUsername());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formLogin.sendInput(IN_PASSWORD_NAME, user.getPassword());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formLogin.submit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check for success
        return !checkForAlerts();
    }

    /**
     * Checks if an alert pops up within 10ms
     * 
     * @return Alert presence status.
     */
    public boolean checkForAlerts() {
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
     * Sends valid login information to the email address and password fields
     */
    public void iEnterValidLoginInformation(String username, String password) {
        waitForElement(loginField, 10).sendKeys(username);
        waitForElement(passwordField, 10).sendKeys(password);
    }

    /**
     * Sends invalid login information to the email address and password fields
     */
    public void iEnterInvalidLoginInformation() {
        waitForElement(loginField, 10).sendKeys("unregistered.user@gmail.com");
        waitForElement(passwordField, 10).sendKeys("badpassword");
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
     * 
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);

        if (button != null) {
            waitForElement(button, 10).click();
        } else {
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
     * 
     * @param name Name of the component to retrieve.
     * @return Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        if (name.equals(FORM_LOGIN_NAME))
            return formLogin; // Directly exists here
        if (name.equals(FOOTER_NAME))
            return footer;

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
     * 
     * @param name Name of the WebElement to search for.
     * @return WebElement tied to that name, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_SUBMIT_NAME:
                return btnLoginSubmit;
            case BTN_SHOW_PASSWORD_NAME:
                return btnShowPassword;
            case BTN_CREATE_ACCOUNT_NAME:
                return btnCreateAccount;
            default:
                for (Component component : getChildComponents()) {
                    WebElement webElement = component.getWebElement(name);
                    if (webElement != null)
                        return webElement;
                }
                break;
        }

        return null;
    }

    // Checks if the password field is displayed on login page
    public boolean isPasswordShown() {
        return passwordField.isDisplayed();
    }

    // Helper method to wait for login submit to redirect to dashboard page
    public void waitAfterValidLoginSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(btnDashboardNavbar));
    }

    // Helper method to wait for login submit to redirect to Signup page
    public void waitAfterRedirectingToSignupPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(btnRegisterAccount));
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
