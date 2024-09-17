package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.DashboardPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSD {
    
    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    DashboardPage page;
    User user = new User(UserType.PERSISTANT, "joseph.sam@gmail.com", "password1");

    @BeforeClass
    public void setUp() {
        System.out.println("Before Class has run");
    }

    @Before
    public void scenarioSetUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);
    }

    @After
    public void scenarioTearDown() {
        WebDriverSingleton.quitDriver();
        page = null;
    }


    /**
     * This methods signs into a user account that has mulptle objects
     * @param ojbects
     * 
     *      | objects           | option        |
     *      | Checking Accounts | Checkings     |
     *      | Savings Accounts  | Savings       |
     *      | Credit Cards      | Credit Cards  |
     *      | Investments       | Investments   |
     * 
     */
    @Given("I have multiple {string}")
    public void iHaveMulitple(String ojbects) {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);

        // wait.until( (p) -> {
        //     try {
        //         LoginPage testLoginPage = new LoginPage(driver);
        //         return testLoginPage.getWebElements() != null;
        //     } catch (NoSuchElementException e){
        //         return false;
        //     }
        // });
        
        LoginPage loginPage = new LoginPage(driver); 
        loginPage.login(user);
        
        
    }

    /**
     * This method will take a user and create a budget plan for them
     */
    @Given("I have a budget plan")
    public void iHaveABudgetPlan() {

        //Load "seasoned" user who has lots of transactions
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        //TODO: create a budget plan

    }

    /**
     * This method will create some recent transactions for user
     */
    @Given("I have recent transactions")
    public void iHaveRecentTransactions() {
        
        //Login with recent user
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        
        //TODO: create some transactions

    }

    /**
     * This method will click on one of the accordion options on the page
     * @param option - The name of the accordion "list"
     * 
     *      | objects           | option        |
     *      | Checking Accounts | Checkings     |
     *      | Savings Accounts  | Savings       |
     *      | Credit Cards      | Credit Cards  |
     *      | Investments       | Investments   |
     * 
     */
    @When("I click on {string} option")
    public void iClickOnOption (String accordianBtnName) {
        //Load Dashboard page (check to make sure on correct page)
        wait.until(ExpectedConditions.urlMatches(Navigator.URL_DASHBOARD));
        page = new DashboardPage(driver);

        //click on the appropriate object
        page.clickButton(accordianBtnName);
    }


    /**
     * This method makes sure that the accordian list appeared
     * @param ojbects
     * 
     *      | objects           | option        |
     *      | Checking Accounts | Checkings     |
     *      | Savings Accounts  | Savings       |
     *      | Credit Cards      | Credit Cards  |
     *      | Investments       | Investments   |
     * 
     */
    @Then("I can see a list of my {string}")
    public void iCanSeeAListOfMy(String objects) {
        
    }

    @Then("I see a Current Spending Table")
    public void iSeeACurrentSpendingTable() {

    }

    @Then("The spending line reflects my spending")
    public void theSpendingLineReflectsMySpending() {

    }

    @Then("A pop up of the transaction appears")
    public void aPopUpOfTheTransactionAppears() {

    }

    @Then("I can see budget information")
    public void iCanSeeBudgetInformation() {

    }


}
