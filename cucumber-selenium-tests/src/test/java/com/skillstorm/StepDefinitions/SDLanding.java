package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    LandingPage page;
    User user = new User(UserType.PERSISTANT, "joseph.sam@gmail.com", "password1");

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

    @And("I am logged out on Landing Page")
    public void iAmLoggedOut() { 

    }

    @And("I am logged in on Landing Page")
    public void iAmLoggedIn() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);        
    }

    @Then("I can see general info about Budget Buddy")
    public void iCanSeeGeneralInfoAboutBudgetBuddy() {
        throw new UnsupportedOperationException("Unimplemented method 'iCanSeeGeneralInfoAboutBudgetBuddy'");
    }

    @Then("I Can see the Logged Out Navbar")
    public void iCanSeeTheLoggedOutNavbar() {
        throw new UnsupportedOperationException("Unimplemented method 'iCanSeeTheLoggedOutNavbar'");
    }    
    
}
