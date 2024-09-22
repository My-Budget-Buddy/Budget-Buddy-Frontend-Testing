package com.skillstorm.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.SpendingPage;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDSpending {
    private WebDriver driver;
    private SpendingPage spendingPage;
    private Navigator navigator;

    @Before("@spending-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        this.spendingPage = new SpendingPage(driver);
    }

    @After("@spending-page")
    public void tearDown() {
        if(driver != null){
            this.driver.quit();
        }
    }

    @Given("Spending: I am logged in")
    public void iAmLoggedIn() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User(UserType.PERSISTANT, "frontend.tests@gmail.com", "password1");
        loginPage.login(user);
    }

    @And("I am on the Spending page")
    public void iAmOnTheSpendingPage() {
        this.spendingPage.clickTab();
        navigator.navigateTo(Navigator.PGNAME_SPENDING);
    }

    @When("I click the See Current Month button")
    public void iClickTheSeeCurrentMonthButton() {
        this.spendingPage.clickButton(SpendingPage.BTN_SEECURRENTMONTH_NAME);
    }

    @Then("I am redirected to the SpendingMonth page")
    public void  iAmRedirectedToTheSpendingMonthPage() {
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_SPENDINGMONTH)));
    }

    @Then("I can see the three cards: spent this week, deposited this week, and Annual Total Spent")
    public void iCanSeeTheSpendingsForTheWeekCard() {
        Assert.assertTrue(driver.findElement(By.id("spending-header-cards")).isDisplayed());
    }
    
    @And("I can see the spendings and earned graph for the year")
    public void iCanSeeTheSpendingsEarningsYearGraph() {
        Assert.assertTrue(driver.findElement(By.id("spending-earnings-graph")).isDisplayed());
    }

    @And("I can see the pie chart of spending categories")
    public void iCanSeeThePieChartOfSpendingCategories() {
        Assert.assertTrue(driver.findElement(By.id("spending-pie-chart")).isDisplayed());
    }

    @And("I can see the spending categories table")
    public void iCanSeeTheSpendingCategoriesTable() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div/div/section/div[4]/div[1]/div[2]/table")).isDisplayed());
    }

    @And("I can see the top categories of spending, top three purchases, and top three vendors")
    public void iCanSeeTheTopCategoriesOfSpendingPurchasesAndVendors() {
        Assert.assertTrue(driver.findElement(By.id("top-categories-purchases-vendors")).isDisplayed());
    }
}
