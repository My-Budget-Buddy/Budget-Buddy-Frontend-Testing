package com.skillstorm.StepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SDLanding {

    private WebDriver driver;

    @Before
    public void setup() {
        /** TODO: implement setup methods
         *      1. set up driver
         *      2. set up waiter?
         *      3. set up users/databases?
         */
        
    }

    @After
    public void teardown() {
        /** TODO: implement teardown methods
         *      1. set up driver
         *      2. set up waiter
         *      3. set up users/databases?
         */
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
