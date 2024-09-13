package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LandingPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SDLanding {

    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    LandingPage page;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        navigator = new Navigator(driver);
        page = new LandingPage(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    /**
     * The Following are Implemented in GenericStepDefinitions.java
     *      @Given("I am on {string} page")
     *      @When("I click the {string} button")
     *      @Then("I am redirected to {string} page")
     */

    @Given("I am logged out")
    public void iAmLoggedOut() {
        throw new UnsupportedOperationException("Unimplemented method 'iAmLoggedOut'");
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
    throw new UnsupportedOperationException("Unimplemented method 'iAmLoggedIn'");
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
