package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.AccountsPage;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.Utilities.Authenticator;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDAccounts {
    private WebDriver driver;
    private WebDriverWait wait;
    private Navigator navigator;
    private AccountsPage page;
    private User seasonedUser = new User(UserType.PERSISTANT, Authenticator.USERNAME_PERSISTENT, Authenticator.PASSWORD_PERSISTENT);


    /**
     * This method sets up the web driver and the wait object before each test.
     */
    @Before
    public void scenarioSetUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        navigator = new Navigator(driver);

        //make sure user is logged out to start with
        navigator.navigateTo(Navigator.PGNAME_LANDING);
        LandingNavbar navbar = new LandingNavbar(driver);
        if (navbar.getWebElement(LandingNavbar.BTN_LOGOUT_NAME) != null) {
            navbar.clickButton(LandingNavbar.BTN_LOGOUT_NAME);
        }
    }

    /**
     * This method closes the web driver after each test.
     */
    @After
    public void scenarioTearDown() {
        WebDriverSingleton.quitDriver();
        page = null;
        wait = null;
        navigator = null;
    }

    /**********************************************************************
     *     GIVEN STEPS
     * *********************************************************************/ 


    @Given("I have multiple {string}")
    public void iHaveMultipleObjects(String objects) {
        // TODO: Implement this step
    }

    
    @Given("my net cash bar is green")
    public void myNetCashBarIsGreen() {
        // TODO: Implement this step
    }
    
    /**********************************************************************
     *     WHEN STEPS
     * *********************************************************************/


    @When("I click on the Accounts {string} option")
    public void iClickOnOption(String option) {
        // TODO: Implement this step
    }

    @When("I click the {string} button on Accounts page") 
    public void iClickTheButtonOnAccountsPage(String btnName) {
        // TODO: Implement this step
    }

    @When("I attempt to delete a {string}")
    public void iAttemptToDeleteA(String accountType) {
        // TODO: Implement this step
    }

    @When("my debt exceeds my Assets")
    public void myDebtExceedsMyAssets() {
        // TODO: Implement this step
    }

    @And("I enter valid {string} information")
    public void iEnterValidInformation(String accountType) {
        // TODO: Implement this step
    }


    /**********************************************************************
     *    THEN STEPS
     * *********************************************************************/
    @Then("a {string} account is Added")
    public void aAccountIsAdded(String accountType) {
        // TODO: Implement this step
    }

    @Then("that {string} is removed")
    public void thatIsRemoved(String accountType) {
        // TODO: Implement this step
    }

    @Then("a pop up appears of my credit score")
    public void aPopUpAppearsOfMyCreditScore() {
        // TODO: Implement this step
    }

    @Then("my net cash bar turns red")
    public void myNetCashBarTurnsRed() {
        // TODO: Implement this step
    }

    @Then("I can see the Accounts {string}")
    public void iCanSeeTheAccounts(String listName) {
        // TODO: Implement this step
    }

    /**********************************************************************
     *    AND STEPS
     * *********************************************************************/
    @And("I have a {string} I want to delete")
    public void iHaveAObjectIWantToDelete(String accountType) {
        // TODO: Implement this step
    }
}
