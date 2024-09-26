/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Represents the signup/register page for the frontend application.
 */

package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

public class SignupPage extends Page {
//#region Static Fields

    // IDs
    public static final String IN_USERNAME_ID = "email";
    public static final String IN_PASSWORD_ID = "password";
    public static final String IN_CONFIRMPASSWORD_ID = "confirm-password";
    public static final String BTN_SHOWPASSWORD_ID = "show-password";
    public static final String BTN_CREATEACCOUNT_ID = "create-account";
    public static final String BTN_GOOGLESIGNIN_ID = "google-sign-in";
    public static final String BTN_LOGIN_ID = "link-login";
    public static final String BTN_LOGINPAGELOGINSUBMIT_ID = "btnLoginSubmit";

    // Names
    public static final String FORM_SIGNUP_NAME = "Signup Form";
    public static final String FOOTER_NAME = "Footer";
    public static final String NAVBAR_NAME = "Navbar";

    public static final String IN_USERNAME_NAME = "In Username";
    public static final String IN_PASSWORD_NAME = "In Password";
    public static final String IN_CONFIRMPASSWORD_NAME = "In Confirm Password";
    public static final String BTN_SHOWPASSWORD_NAME = "Show Password";
    public static final String BTN_CREATEACCOUNT_NAME = "Submit";
    public static final String BTN_GOOGLESIGNIN_NAME = "Sign In With Google";
    public static final String BTN_LOGIN_NAME = "Log In";

//#endregion

    // Components
    private Form signupForm;
    private Footer footer;

    // WebElements
    @FindBy(id = BTN_SHOWPASSWORD_ID)
    private WebElement btnShowPassword;

    @FindBy(id = BTN_GOOGLESIGNIN_ID)
    private WebElement btnGoogleSignIn;

    @FindBy(id = BTN_LOGIN_ID)
    private WebElement btnLogin;

    @FindBy(id = BTN_CREATEACCOUNT_ID)
    private WebElement btnCreateAccountSubmit;

    @FindBy(id = BTN_LOGINPAGELOGINSUBMIT_ID)
    private WebElement btnLoginSubmit;

    // Modal fields
    @FindBy(id = IN_USERNAME_ID)
    private WebElement emailField;

    @FindBy(id = IN_PASSWORD_ID)
    private WebElement passwordField;

    @FindBy(id = IN_CONFIRMPASSWORD_ID)
    private WebElement confirmPasswordField;

    // --- CONSTRUCTORS ---

    /**
     * Initializes all necessary parts of the page.
     * @param driver Driver
     */
    public SignupPage(WebDriver driver) {
        super(driver);

        // Initialize navbar
        navbar = new LandingNavbar(driver);

        // Initialize form
        signupForm = new Form(driver);
        signupForm.setInput(IN_USERNAME_ID, IN_USERNAME_NAME);
        signupForm.setInput(IN_PASSWORD_ID, IN_PASSWORD_NAME);
        signupForm.setInput(IN_CONFIRMPASSWORD_ID, IN_PASSWORD_NAME);
        WebElement btnSubmit = driver.findElement(By.id(BTN_CREATEACCOUNT_ID));
        signupForm.setBtnSubmit(btnSubmit, BTN_CREATEACCOUNT_NAME);

        // Initialize footer
        footer = new Footer(driver);

        // Page initialization
        PageFactory.initElements(driver, this);
    }

    // --- UNIQUE METHODS ---

    /**
     * Sends valid signup information to the email address, password, and confirm password fields
     */
    public void iEnterValidSignupInformation() {
        String randomizedEmail = generateRandomString() + "@gmail.com";
        waitForElement(emailField,10).sendKeys(randomizedEmail);
        waitForElement(passwordField,10).sendKeys("password1");
        waitForElement(confirmPasswordField,10).sendKeys("password1");
    }

    /**
     * Sends invalid signup information to the email address, password, and confirm password fields
     */
    public void iEnterInvalidSignupInformation() {
        waitForElement(emailField,10).sendKeys("notAnEmail");
        waitForElement(passwordField,10).sendKeys("password1");
        waitForElement(confirmPasswordField,10).sendKeys("password1");
    }

    /**
     * Sends existing signup information to the email address, password, and confirm password fields
     */
    public void iEnterExistingSignupInformation() {
        waitForElement(emailField,10).sendKeys("joseph.sam@gmail.com");
        waitForElement(passwordField,10).sendKeys("password1");
        waitForElement(confirmPasswordField,10).sendKeys("password1");
    }

    /**
     * Performs the process of signing up.
     * @param user  User to sign up.
     * @return      Results of the signup process.
     */
    public boolean signup(User user){
        // Fill fields and submit.
        signupForm.sendInput(IN_USERNAME_NAME, user.getUsername());
        signupForm.sendInput(IN_PASSWORD_NAME, user.getPassword());
        signupForm.sendInput(IN_CONFIRMPASSWORD_NAME, user.getPassword());
        signupForm.submit();

        // Check results
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

    // --- INTERFACE METHODS ---

    /**
     * Returns a list of direct child components.
     */
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(signupForm, footer, navbar);
    }

    /**
     * Finds a direct child given a name.
     */
    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case FORM_SIGNUP_NAME:
                return signupForm;
            case NAVBAR_NAME:
                return navbar;
            case FOOTER_NAME:
                return footer;
            default:
                throw new IllegalArgumentException("Component '" + name + "' does not exist.");
        }
    }

    /**
     * Returns a List of all WebElements within this component. Searches sub-components as well.
     */
    @Override
    public List<WebElement> getWebElements() {
        // Add from here
        ArrayList<WebElement> webElements = new ArrayList<WebElement>();
        webElements.add(btnGoogleSignIn);
        webElements.add(btnLogin);
        webElements.add(btnShowPassword);

        // Add from child components
        for(Component component : getChildComponents()){
            webElements.addAll(component.getWebElements());
        }

        return webElements;
    }

    /**
     * Returns a WebElement given a name. Searches sub-components as well.
     * @return WebElement requested, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_CREATEACCOUNT_NAME:
                return btnCreateAccountSubmit;
            case BTN_LOGIN_NAME:
                return btnLogin;
            case BTN_SHOWPASSWORD_NAME:
                return btnShowPassword;
            default:
                for (Component component : getChildComponents()) {
                    WebElement webElement = component.getWebElement(name);
                    if(webElement != null) return webElement;
                }
                break;
        }

        return null;
    }

    /**
     * Returns a list of all buttons directly under this component.
     */
    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnCreateAccountSubmit, btnGoogleSignIn, btnLogin, btnShowPassword);
    }

    /**
     * Clicks a button given a name.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        
        if(button != null) {
            waitForElement(button, 10).click();
        }
        else {
            throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }

    // Checks if the password field is displayed on signup page
    public boolean isSignupPasswordShown() {
        return passwordField.isDisplayed() && confirmPasswordField.isDisplayed();
    }

    // Helper method to wait for signup submit to redirect to login page
    public void waitForLoginPageNavigation() {
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("login"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    // Helper method to generate a random string for email registration
    private String generateRandomString() {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < 6) {
            int index = (int) (rnd.nextFloat() * validChars.length());
            stringBuilder.append(validChars.charAt(index));
        }
        return stringBuilder.toString();

    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
