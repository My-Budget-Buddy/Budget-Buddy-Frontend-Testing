package com.skillstorm.StepDefinitions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.TransactionPage;
import com.skillstorm.Utilities.Navigator;

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
    private String[] transactionContent;

    /**
     * Set up the ChromeDriver and initialize TransactionPage.
     */
    @Before("@transaction")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);

        this.transactions = new TransactionPage(driver);

        transactionContent =  new String[4];
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
    // @Given("I am on the login page")
    // public void logInPage() {
    //     this.transactions.loginPage();
    // }

    @And("I login")
    public void login() {
        this.transactions.logIn();
    }

    /**
     * General Scenario Definitions
     */
    @Given("I am on the Transactions page")
    public void iAmOnTheTranactionsPage() {
        this.transactions.clickTab();
        String expectedUrl = "http://localhost:5173/dashboard/transactions";
        String actualUrl = this.transactions.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // @And("I click the Submit button")
    // public void iClickTheSubmitButton() {
    //     this.transactions.clickSubmitBtn();
    // }

    /**
     * Create Transaction Scenario Definitions
     */
    @When("I click the Add Transactions button")
    public void iClickTheAddTransactionButton() {
        this.transactions.triggerAddTransactionModal();
    }

    @And("I fill in the {string}, {string}, {string}, and {string}")
    public void andIFillInThe(String name, String account, String amount, String category) {
        transactionContent[0] = this.transactions.setName(name);
        transactionContent[1] = this.transactions.setAccount(account);
        transactionContent[2] = this.transactions.setAmount(amount);
        transactionContent[3] = this.transactions.setCategory(category);
    }

    @Then("I can see the new transaction in my list")
    public void iCanSeeTheNewTransactionInMyList() {
        String actualTransaction = this.transactions.verifyTransactionDetails();
        System.out.println(actualTransaction);
    }

    /**
     * Read Transaction Scenario Definitions
     */
    @Then("I can see the list of all my transactions")
    public void iCanSeeTheListOfAllMyTransactions() {
        this.transactions.printTransactionTable();
        Assert.assertTrue(this.transactions.printTransactionTable());
    }

    /**
     * Update Transaction Scenario Definitions
     */
    @When("I click the Pencil Icon button")
    public void iClickThePencilIconButton() {
        this.transactions.clickEditIcon();
    }

    @And("I update the {string}, {string}, {string}, and {string}")
    public void andIUpdateThe(String name, String account, String amount, String category) {
        this.transactions.updateName(name);
        this.transactions.updateAccount(account);
        this.transactions.updateAmount(amount);
        this.transactions.updateCategory(category);
    }

    @And("I click the edit Submit button")
    public void iClickTheEditSubmitButton() {
        this.transactions.clickEditSubmitBtn();
    }

    /**
     * Delete Transaction Scenario Definitions
     */
    @When("I click the Trash Icon button")
    public void iClickTheTrashIconButton() {
        this.transactions.clickDeleteBtn();
    }

    @Then("the transaction is not in the list")
    public void theTransactionIsNotInTheList() {
        this.transactions.confirmDeletion();
        Assert.assertTrue(this.transactions.confirmDeletion());
    }

    /**
     * Category Filter Scenario Definitions
     */
    @When("I click the All Categories button")
    public void iClickTheAllCategoriesButton() {
        this.transactions.clickAllCategories();
    }

    @And("I click a {string} to filter based on")
    public void iClickToFilterBasedOn(String category) {
        this.transactions.selectACategory(category);
    }

    @Then("only transactions with that category should be visible")
    public void onlyTransactionsWithThatCategoryShouldBeVisible() {
        this.transactions.printOutCategoryColumn();
    }
}