package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.AccountsPage;
import com.skillstorm.PageObjects.DashboardPage;
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

    /**
     * NOTE: This method is implemented in SDDashboard.java
     */
    // @Given("I have multiple {string}")
    // public void iHaveMultipleObjects(String objects) {
    // }
    
    /**********************************************************************
     *     WHEN STEPS
     * *********************************************************************/

 
    @When("I click on the {string} option on Accounts")
    public void iClickOnTheOptionOnAccounts (String accordianBtnName) {
        //Load Dashboard page (check to make sure on correct page)
        wait.until(ExpectedConditions.urlMatches(Navigator.URL_ACCOUNTS));
        page = new AccountsPage(driver);

        //check to make sure the accordian table is currently not displayed
        String listName = page.getListName(accordianBtnName);
        Assert.assertTrue(listName != null);
        Assert.assertFalse(page.getWebElement(listName).isDisplayed());

        //click on the appropriate object
        page.clickButton(accordianBtnName);
    }

    @When("I click the {string} button on Accounts")
    public void iClickTheButtonOnAccounts (String accordianBtnName) {
    }

    @When("I attempt to delete a {string}")
    public void iAttemptToDeleteA(String accountType) {
        // TODO: Implement this step
    }

    @When("my debt exceeds my Assets")
    public void myDebtExceedsMyAssets() {
        page = new AccountsPage(driver);

        //Get the net cash value
        double netCash = page.getNetCash();
        //Add and credit account with more debt than than the net cash value
        String accountType = "Credit Card";
        String institutionName = "Test Bank";
        page.addAccount(accountType, institutionName, 123456789, netCash*1.5);
        
        //Check to make sure credit account is added
        //Assert.assertTrue(page.checkForCreditCard("Test Credit Card") != null);
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
        page = new AccountsPage(driver);

        //Check if the net cash is negative
        Assert.assertTrue(page.getNetCash() < 0.0);
        //Check if the net cash bar is red
        Assert.assertEquals(page.getNetCashBarColor(), AccountsPage.NETCASH_BAR_RED);
    }


    @Then("I can see the {string} on Accounts")
    public void iCanSeeTheListOnAccounts(String listName) {
        page = new AccountsPage(driver);
        wait.until(ExpectedConditions.visibilityOf(
            page.getWebElement(listName)
        ));
    }

    /**********************************************************************
     *    AND STEPS
     * *********************************************************************/
    @And("I have a {string} I want to delete")
    public void iHaveAObjectIWantToDelete(String accountType) {
        // TODO: Implement this step
    }

    @And("my net cash bar is green")
    public void myNetCashBarIsGreen() {
        page = new AccountsPage(driver);

        //Check if the net cash is positive
        Assert.assertTrue(page.getNetCash() > 0.0);
        //Check if the net cash bar is green
        Assert.assertEquals(page.getNetCashBarColor(), AccountsPage.NETCASH_BAR_GREEN);
    }
}
