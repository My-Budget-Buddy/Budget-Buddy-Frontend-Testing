package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.DashboardPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSD {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Navigator navigator;
    private DashboardPage page;
    private User seasonedUser = new User(UserType.PERSISTANT, "frontend.tests@gmail.com", "password1");
    //Number of Budget Items this user has
    private final int NUM_BUDGET_ITEMS = 2;

    @Before
    public void scenarioSetUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);

        //make sure user is logged out to start with
        navigator.navigateTo(Navigator.PGNAME_LANDING);
        LandingNavbar navbar = new LandingNavbar(driver);
        if (navbar.getWebElement(LandingNavbar.BTN_LOGOUT_NAME) != null) {
            navbar.clickButton(LandingNavbar.BTN_LOGOUT_NAME);
        }
    }

    @After
    public void scenarioTearDown() {
        WebDriverSingleton.quitDriver();
        page = null;
        wait = null;
        navigator = null;
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
        //Load "seasoned" user who has lots of accounts
        navigator.navigateTo(Navigator.PGNAME_LOGIN);       
        LoginPage loginPage = new LoginPage(driver); 
        loginPage.login(seasonedUser);
    }

    /**
     * This method will load a user who has a budget plan
     */
    @Given("I have a budget plan")
    public void iHaveABudgetPlan() {

        //Load "seasoned" user who has a budget plan
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(seasonedUser);

    }

    /**
     * This method will load a user who has recent transactions
     */
    @Given("I have recent transactions")
    public void iHaveRecentTransactions() {
        
        //load "seasoned" user who has recent transactions
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(seasonedUser);

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

        //check to make sure the accordian table is currently not displayed
        String tableName = page.getTableNameFromButtonName(accordianBtnName);
        Assert.assertFalse(page.getWebElement(tableName).isDisplayed());

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
    @Then("I can see a the {string} table")
    public void iCanSeeAListOfMy(String obj) {
        wait.until(ExpectedConditions.visibilityOf(
            page.getWebElement(obj)
        ));
    }

    /**
     * This method maks sure that the spending chart is created and displayed
     */
    @Then("I see a Current Spending Table")
    public void iSeeACurrentSpendingTable() {
        page = new DashboardPage(driver);
        wait.until(ExpectedConditions.visibilityOf(
            page.getWebElement(DashboardPage.NAME_CURRENT_SPENDING_CHART)
        ));
    }

    /**
     * This method checks that the spending number on the chart is greater than 0
     *     for a user who has transactions in the given month
     */
    @And("The spending line reflects my spending")
    public void theSpendingLineReflectsMySpending() {
        page = new DashboardPage(driver);
        Assert.assertTrue(
            page.getCurrentSpending() > 0.0,
            "Current Spending is not greater than 0.0"
        );
    }

    /**
     * This test is to make sure that a transaction pop up has appeared
     */
    @Then("A pop up of the transaction appears")
    public void aPopUpOfTheTransactionAppears() {
        page = new DashboardPage(driver);
        //Check to see if the Transaction Modal is displayed Over the page
        wait.until(ExpectedConditions.visibilityOf(
            page.getWebElement(DashboardPage.NAME_TRANSACTION_ARROW_OVERLAY)
        ));
    }

    /**
     * This test is to make sure that the budget information is displayed
     *      for a user who has a budget plan
     */
    @Then("I can see budget information")
    public void iCanSeeBudgetInformation() {
        page = new DashboardPage(driver);

        //Check to see if the Budget Circle Chart is displayed
        WebElement budgetCircleChart = page.getWebElement(DashboardPage.NAME_BUDGET_CIRCLE_CHART);
        Assert.assertTrue(
            budgetCircleChart != null,
            "Budget Circle Chart cannot be found"
        );


        //Check to see if the Budget Breakdown Information is displayed
        List<WebElement> budgetItems = page.getAllBudgetItems();
        Assert.assertTrue(
            budgetItems.size() == NUM_BUDGET_ITEMS,
            "Budget Breakdown cannot be found"
        );
    }


}
