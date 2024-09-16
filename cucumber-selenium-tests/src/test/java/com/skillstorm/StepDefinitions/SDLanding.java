package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LandingPage;
import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.Utilities.Navigator;

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
    }

    /**
     * The Following are Implemented in GenericStepDefinitions.java
     *      @Given("I am on {string} page")
     *      @When("I click the {string} button")
     *      @Then("I am redirected to {string} page")
     */

    @And("I am logged out of Landing")
    public void iAmLoggedOutOfLanding() {
        if (page == null) {
            page = new LandingPage(driver);
        }
        Navbar navbar = (Navbar) page.getChildComponent(LandingPage.CMP_LANDING_NAVBAR_NAME);
        // Assert.assertTrue(
        //     page.getChildComponent(LandingPage.CMP_LANDING_NAVBAR_NAME)
        //     .getWebElement(null)
        // );
    }

    @And("I am logged in on Landing")
    public void iAmLoggedInOnLanding() {
        if (page == null) {
            page = new LandingPage(driver);
        }
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
