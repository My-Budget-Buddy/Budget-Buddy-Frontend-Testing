package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LandingPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SDLanding {

    private WebDriver driver;
    private WebDriverWait wait;
    private Navigator navigator;
    private LandingPage page;
    private User user = new User(UserType.PERSISTANT, "joseph.sam@gmail.com", "password1");
    public static final int NUM_OF_FEATURES = 3;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        navigator = new Navigator(driver);
        // page = new LandingPage(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
        wait = null;
        navigator = null;
    }

    /**
     * The Following are Implemented in GenericStepDefinitions.java
     *      @Given("I am on {string} page")
     *      @When("I click the {string} button")
     *      @Then("I am redirected to {string} page")
     */

    @Given("I am logged out on Landing Page")
    public void iAmLoggedOutOnLanding() { 
        navigator.navigateTo(Navigator.PGNAME_LANDING);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LandingPage.BTN_GET_STARTED_ID)));
        
        // check to see if logout button exists
        Navbar navbar = new LandingNavbar(driver);
        if (navbar.getWebElement(LandingNavbar.BTN_LOGOUT_NAME) != null) {
            // and click it if it does
            navbar.clickButton(LandingNavbar.BTN_LOGOUT_NAME);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LandingPage.BTN_GET_STARTED_ID)));
        } 

        // if we find the login button, then we're already logged out
        Assert.assertTrue(
            navbar.getWebElement(LandingNavbar.BTN_LOGIN_NAME) != null,
        "Could not find either the login or logout button on the navbar"
        );  
 
    }

    @Given("I am logged in on Landing Page")
    public void iAmLoggedIn() {
        //login
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);  
        
        //navigate to landing page
        navigator.navigateTo(Navigator.PGNAME_LANDING);
        
        //make sure we're logged in
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LandingNavbar.BTN_LOGOUT_ID)));
    }

    @Then("I can see general info about Budget Buddy")
    public void iCanSeeGeneralInfoAboutBudgetBuddy() {
        page = new LandingPage(driver);
        List<WebElement> features = page.getFeatures();

        Assert.assertTrue(
            features.size() == NUM_OF_FEATURES,
            "Could not find any features on the landing page"
        );
    }

    @Then("I Can see the Logged Out Navbar")
    public void iCanSeeTheLoggedOutNavbar() {
        LandingNavbar navbar = new LandingNavbar(driver);
        Assert.assertTrue(
            navbar.getWebElement(LandingNavbar.BTN_LOGIN_NAME) != null, 
            "Could not find the logout button on the navbar"
        );
    }    
    
}
