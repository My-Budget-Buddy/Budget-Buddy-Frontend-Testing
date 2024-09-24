package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.AccountsPage;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDAccounts {
    private WebDriver driver;
    private WebDriverWait wait;
    private Navigator navigator;
    private AccountsPage page;
    private Map<String, String> accountInfo;

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

        //initialize accountInfo
        accountInfo = new HashMap<>();
    }

    /**
     * This method closes the web driver after each test.
     */
    @After
    public void scenarioTearDown() {
        // quit the driver
        WebDriverSingleton.quitDriver();
        page = null;
        wait = null;
        navigator = null;
        accountInfo = null;
    }

    /**********************************************************************
     *     GIVEN STEPS
     * *********************************************************************/ 

    /**
     * NOTE: This method is implemented in SDDashboard.java
     */
    // @Given("I have multiple {string}")
    
    /**********************************************************************
     *     WHEN STEPS
     * *********************************************************************/

    
    /**
     * This method finds and clicks on an accordion button on the Accounts page.
     * @param accordianBtnName
     */
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

    /**
     * This method finds and clicks on a button on the Accounts page that cannot be found by id.
     * @param btnName
     */
    @When("I click the {string} button on Accounts")
    public void iClickTheButtonOnAccounts (String btnName) {
        page = new AccountsPage(driver);
        page.clickButton(btnName);
    }

    /**
     * This method attempts to delete an account on the Accounts page.
     */
    @When("I attempt to delete the account")
    public void iAttemptToDeleteA() {
        // Load the page
        page = new AccountsPage(driver);

        // Load the account we would like to delete
        String accountType = accountInfo.get("accountType");
        String institutionName = accountInfo.get("institutionName");
        String accountNumber = accountInfo.get("accountNumber");
        String balance = accountInfo.get("balance");

        // Find the Account and Attempt to delete it
        WebElement account = page.checkForAccount( accountType, institutionName, accountNumber, balance);
        Assert.assertTrue(account != null, "Account to delete not found");

        page.deleteAccount(account);
    }

    /**
     * This method makes a credit account that is more than the net cash value.
     * This is to change the color of the net cash bar to red.
     */
    @When("my debt exceeds my Assets")
    public void myDebtExceedsMyAssets() {
        page = new AccountsPage(driver);

        //Get the net cash value
        double netCash = page.getNetCash();

        //Pull up the Add Account Form
        page.clickButton(AccountsPage.NAME_BTN_ADD_ACCOUNT);

        //Add and credit account with more debt than than the net cash value
        String accountType = AccountsPage.ACCOUNT_TYPE_OPTIONS.get(3); //this is Credit Card Account 
        String institutionName = "Checking Net Cash Gauge Bank";
        String accountNumber = "123456789";
        String routingNumber = "0";
        // create more debt than net cash
        String balance = new Double(netCash*1.5).toString();
        page.addAccount(accountType, institutionName, accountNumber, routingNumber, balance);
        
        //Save the Information of that Account for Future Steps
        saveAccountInfo(accountType, institutionName, accountNumber, routingNumber, balance);

        //Check to make sure credit account is added
        Assert.assertTrue(page.checkForAccount( accountType, institutionName, accountNumber, balance) != null);
    }



    /**********************************************************************
     *    THEN STEPS
     * *********************************************************************/
    /**
     * This method checks to see if the account is created or deleted.
     * @param status
     */
    @Then("that specific account is {string}")
    public void thatSpecificAccountIs(String status) {

        Assert.assertTrue( status.equals("Created") || status.equals("Deleted") );
        // Load the page
        page = new AccountsPage(driver);

        // Get the account information for the last modified account
        String accountType = accountInfo.get("accountType");
        String institutionName = accountInfo.get("institutionName");
        String accountNumber = accountInfo.get("accountNumber");
        String balance = accountInfo.get("balance");


        // Check to see if the last modified account is in the list
        WebElement account = page.checkForAccount(accountType, institutionName, accountNumber, balance);
        if (status.equals("Created")) {
            Assert.assertTrue( account != null );
            // Clean up if account was created
            page.deleteAccount(account);
        } else if (status.equals("Deleted")) {
            Assert.assertTrue( account == null );
        } else {
            Assert.fail("Invalid Status");
        }
    }

    /**
     * This method checks to make sure the credit score popup appears.
     */
    @Then("a pop up appears of my credit score")
    public void aPopUpAppearsOfMyCreditScore() {
        page = new AccountsPage(driver);

        //Check to make sure this popup is the correct one
        WebElement popup = page.getWebElement(AccountsPage.NAME_CREDIT_SCORE_POPUP);
        Assert.assertEquals(popup.getText(),AccountsPage.NAME_CREDIT_SCORE_POPUP_TITLE);    

        //Check to make sure the popup is displayed
        Assert.assertTrue(page.getWebElement(AccountsPage.NAME_CREDIT_SCORE_POPUP).isDisplayed());
    }

    /**
     * This method checks to make sure the net cash bar is red.
     */
    @Then("my net cash bar turns red")
    public void myNetCashBarTurnsRed() {
        page = new AccountsPage(driver);

        //Check if the net cash is larger than 0 - this is because net is calculated as absoulte value
        Assert.assertTrue(page.getNetCash() > 0.0);
        //Check if the net cash bar is red
        Assert.assertEquals(page.getNetCashBarColor(), AccountsPage.NETCASH_BAR_RED);

        // Try to clean up the credit account that was created
        WebElement account = page.checkForAccount(
            accountInfo.get("accountType"),
            accountInfo.get("institutionName"),
            accountInfo.get("accountNumber"),
            accountInfo.get("balance")
        );
        if (account != null) page.deleteAccount(account);
    }

    /**
     * This method checks to make sure the accordian list is displayed on the Accounts page after the button was clicked.
     * @param listName - the name of the accordian list
     */
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

    /**
     * This method creates an account with the following information
     * @param accountType - the type of account
     * @param institutionName - The name of the bank or institution
     * @param accountNumber - the account number
     * @param routingNumber - the routing number
     * @param balance - the balance of the account
     */
    @And("I add a {string} account with the information: {string}, {string}, {string}, {string}")
    public void iAddAAccountWithTheInformation(String accountType, String institutionName, String accountNumber, String routingNumber, String balance) {
        // Load the page
        page = new AccountsPage(driver);

        // Add account and save the information
        page.clickButton(AccountsPage.NAME_BTN_ADD_ACCOUNT);
        page.addAccount(accountType, institutionName, accountNumber, routingNumber, balance);
        saveAccountInfo(accountType, institutionName, accountNumber, routingNumber, balance);

        // Check to make sure the account was added
        Assert.assertTrue( page.checkForAccount( accountType, institutionName, accountNumber, balance) != null );
    }

    /**
     * This method checks to make sure the net cash bar is green.
     */
    @And("my net cash bar is green")
    public void myNetCashBarIsGreen() {
        page = new AccountsPage(driver);

        //Check if the net cash is positive
        Assert.assertTrue(page.getNetCash() > 0.0);
        //Check if the net cash bar is green
        Assert.assertEquals(page.getNetCashBarColor(), AccountsPage.NETCASH_BAR_GREEN);
    }


    /**********************************************************************
     *    EXTRA FUNCTIONS
     * *********************************************************************/
    /**
     * This method saves the account information to a map for future use.
     * @param accountType - the type of account
     * @param institutionName - The name of the bank or institution
     * @param accountNumber - the account number
     * @param routingNumber - the routing number
     * @param balance - the balance of the account
     */
    private void saveAccountInfo(String accountType, String institutionName, String accountNumber, String routingNumber, String balance) {
        accountInfo.put("accountType", accountType);
        accountInfo.put("institutionName", institutionName);
        accountInfo.put("accountNumber", accountNumber);
        accountInfo.put("routingNumber", routingNumber);
        accountInfo.put("balance", balance);
    }

}
