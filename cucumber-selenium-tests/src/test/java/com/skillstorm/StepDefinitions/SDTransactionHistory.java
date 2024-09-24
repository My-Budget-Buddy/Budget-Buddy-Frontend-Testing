package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.TransactionHistoryPage;
import com.skillstorm.PageObjects.TransactionPage;
import com.skillstorm.Utilities.Authenticator;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

/**
 * Step Definitions for Transaction History feature.
 */
public class SDTransactionHistory {
    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    private TransactionHistoryPage transactionHistory;
    private TransactionPage transactionPage;
    private String[] transactionHistoryContent;

    /**
     * Set up the ChromeDriver and initialize TransactionHistoryPage before each test scenario tagged with @transactionhistory.
     */
    @Before("@transactionhistory")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);
        transactionHistory = new TransactionHistoryPage(driver);
        transactionPage = new TransactionPage(driver);
        transactionHistoryContent = new String[3]; // To store transaction details
    }

    /**
     * Tear down the WebDriver after each test scenario tagged with @transactionhistory.
     */
    @After("@transactionhistory")
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    // ===================== General Step Definitions =====================

    /**
     * Navigate to the Transaction History page.
     */
    @And("I am on the Transaction History page")
    public void iAmOnTheTransactionHistoryPage() {
        transactionHistory.getCurrentUrl();
        Assert.assertTrue(transactionHistory.bothTableAndGraphAreVisible());
    }

    /**
     * Log into the web application.
     */
    @Given("I login into the webapp")
    public void login() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User(UserType.PERSISTANT, Authenticator.USERNAME_PERSISTENT, Authenticator.PASSWORD_PERSISTENT);
        loginPage.login(user);
    }

    // ===================== Navigation Step Definitions =====================

    /**
     * Navigate to the Transactions page.
     */
    @And("I start on the Transactions page")
    public void iStartOnTheTransactionPage() {
        transactionPage.clickTab();
    }

    /**
     * Verify that the "Transaction Detailed Information" title is displayed.
     */
    @And("I can see Transaction Detailed Information")
    public void iCanSeeTransactionDetailedInformation() {
        String givenTitle = transactionHistory.confirmDetailedInfoTitle();
        String expectedTitle = "Transaction Detailed Information";
        Assert.assertEquals(givenTitle, expectedTitle, "The titles don't match!");
    }

    /**
     * Verify that the list of past transactions is displayed for a specific transaction.
     */
    @And("I can see the list of all my past transactions for a specific transaction")
    public void iCanSeeTheListOfAllMyPastTransactionsForASpecificTransaction() {
        Assert.assertTrue(transactionHistory.displayTransactionHistoryTable(), "Transaction history table is not displayed!");
    }

    // ===================== Create Transaction Step Definitions =====================

    /**
     * Fill in account, amount, and category fields for creating a transaction.
     */
    @And("I fill in the {string}, {string}, and {string}")
    public void iFillInThe(String account, String amount, String category) {
        transactionHistory.setAccount(account);
        transactionHistory.setAmount(amount);
        transactionHistory.setCategory(category);
    }

    /**
     * Verify that the new transaction is added to the list of past transactions.
     */
    @Then("I can see the new transaction in my past history list")
    public void iCanSeeTheNewTransactionInMyList() {
        String createdTransaction = transactionHistory.confirmCreation();
        System.out.println(createdTransaction);
        Assert.assertTrue(createdTransaction.contains("$300.00"), "Amount not found in the list!");
        Assert.assertTrue(createdTransaction.contains("Transportation"), "Category not found in the list!");
    }

    // ===================== Read Transaction Step Definitions =====================

    /**
     * Verify that the list of all past transactions is displayed.
     */
    @Then("I can see the list of all my past transactions")
    public void iCanSeeTheListOfAllMyPastTransactions() {
        Assert.assertTrue(transactionHistory.bothTableAndGraphAreVisible(), "Table and graph are not visible!");
    }

    // ===================== Update Transaction Step Definitions =====================

    @When("I click the edit button")
    public void clickEditBtn() {
        transactionHistory.clickEditBtn();
    }

    /**
     * Update account, amount, and category fields for a transaction.
     */
    @And("I update the {string}, {string}, and {string}")
    public void iUpdateThe(String account, String amount, String category) {
        transactionHistory.updateAccount(account);
        transactionHistory.updateAmount(amount);
        transactionHistory.updateCategory(category);
    }

    @When("I click the edit submit button")
    public void clickEditSubmitBtn() {
        transactionHistory.clickEditSubmitBtn();
    }

    /**
     * Verify that the updated transaction is reflected in the list of past transactions.
     */
    @Then("I can see the updated transaction in my past history list")
    public void iCanSeeTheUpdatedTransactionInMyList() {
        String updatedTransaction = transactionHistory.confirmUpdation();
        Assert.assertTrue(updatedTransaction.contains("Income"), "Income not found in the updated transaction!");
        Assert.assertTrue(updatedTransaction.contains("$3,107.42"), "Updated amount not found in the transaction!");
    }

    // ===================== Delete Transaction Step Definitions =====================

    /**
     * Click on the Trash icon to delete a transaction.
     */
    @When("I click on the Trash Icon button")
    public void iClickOnTheTrashIconButton() {
        transactionHistory.clickTrashIcon();
    }

    /**
     * Verify that the transaction is removed from the list of past transactions.
     */
    @Then("the transaction is not in the list of past transactions")
    public void theTransactionIsNotInTheList() {
        Assert.assertTrue(transactionHistory.transactionIsNotInList(), "Transaction is still present in the list!");
    }

    // ===================== Graphical Summary Step Definitions =====================

    /**
     * Verify that a graphical summary of the transaction history is displayed.
     */
    @Then("I can see a graphical summary of my transaction history")
    public void iCanSeeAGraphicalSummaryOfMyTransactionHistory() {
        Assert.assertTrue(transactionHistory.viewGraphicalSummary(), "Graphical summary is not displayed!");
    }
}