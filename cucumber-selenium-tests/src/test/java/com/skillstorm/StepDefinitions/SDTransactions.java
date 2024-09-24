package com.skillstorm.StepDefinitions;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.TransactionPage;
import com.skillstorm.Utilities.Authenticator;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class SDTransactions {

    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    private TransactionPage transactions;
    private String selectedCategory;

    /**
     * Set up the ChromeDriver and initialize TransactionPage.
     */
    @Before("@transaction")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);

        transactions = new TransactionPage(driver);

    }

    /**
     * Tear down the WebDriver after each test.
     */
    @After("@transaction")
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    /**
     * Login Scenario Definitions
     */
    @Given("I login")
    public void login() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User(UserType.PERSISTANT, Authenticator.USERNAME_PERSISTENT, Authenticator.PASSWORD_PERSISTENT);
        loginPage.login(user);
    }

    /**
     * General Scenario Definitions
     */
    @And("I am on the Transactions page")
    public void iAmOnTheTranactionsPage() {
        transactions.clickTab();
        String expectedUrl = Navigator.URL_TRANSACTIONS;
        String actualUrl = this.transactions.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    /**
     * Create Transaction Scenario Definitions
     */

    @And("I fill in the {string}, {string}, {string}, and {string}")
    public void andIFillInThe(String name, String account, String amount, String category) {
        transactions.setName(name);
        transactions.setAccount(account);
        transactions.setAmount(amount);
        transactions.setCategory(category);
    }

    @Then("I can see the new transaction in my list")
    public void iCanSeeTheNewTransactionInMyList() {
        Boolean actualTransaction = transactions.verifyTransactionCreated();
        Assert.assertTrue(actualTransaction);
    }

    /**
     * Read Transaction Scenario Definitions
     */
    @Then("I can see the list of all my transactions")
    public void iCanSeeTheListOfAllMyTransactions() {
        transactions.printTransactionTable();
        Assert.assertTrue(this.transactions.printTransactionTable());
    }

    /**
     * Update Transaction Scenario Definitions
     */

    @When("I click the edit icon")
    public void iClickTheEditButton() {
        transactions.clickEditBtn();
    }
    @And("I update the {string}, {string}, {string}, and {string}")
    public void andIUpdateThe(String name, String account, String amount, String category) {
        transactions.updateName(name);
        transactions.updateAccount(account);
        transactions.updateAmount(amount);
        transactions.updateCategory(category);
    }

    @Then("I can see the updated transaction in my list")
    public void iCanSeeTheUpdatedTransactionInMyList() {
        String actualTransaction = this.transactions.verifyTransactionDetails();
        Assert.assertTrue(actualTransaction.contains("Bojangles"));
        Assert.assertTrue(actualTransaction.contains("Dining"));
        Assert.assertTrue(actualTransaction.contains("$15.99"));
    }

    /**
     * Delete Transaction Scenario Definitions
     */
    @When("I click the Trash Icon button")
    public void iClickTheTrashIconButton() {
        transactions.clickDeleteBtn();
    }

    @Then("the transaction is not in the list")
    public void theTransactionIsNotInTheList() {
        transactions.confirmDeletion();
        Assert.assertTrue(this.transactions.confirmDeletion());
    }

    /**
     * Category Filter Scenario Definitions
     */

    @And("I click a {string} to filter based on")
    public void iClickToFilterBasedOn(String category) {
        selectedCategory = category;
        transactions.selectACategory(category);
    }

    @Then("only transactions with that category should be visible")
    public void onlyTransactionsWithThatCategoryShouldBeVisible() {
        transactions.getCategoryColumnValues();
        transactions.assertCategorySelection(selectedCategory);
    }
}