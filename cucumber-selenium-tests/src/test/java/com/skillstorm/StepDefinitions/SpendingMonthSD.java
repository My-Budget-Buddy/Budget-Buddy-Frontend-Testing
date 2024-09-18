package com.skillstorm.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.SpendingMonthPage;
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

public class SpendingMonthSD {
    private WebDriver driver;
    private SpendingMonthPage spendingMonthPage;
    private SpendingPage spendingPage;
    private Navigator navigator;

    @Before("@spending-month-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        this.spendingMonthPage = new SpendingMonthPage(driver);
        this.spendingPage = new SpendingPage(driver);
    }

    @After("@spending-month-page")
    public void tearDown() {
        if(driver != null){
            this.driver.quit();
        }
    }

    @Given("SpendingMonth: I am logged in")
    public void iAmLoggedIn() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User(UserType.PERSISTANT, "joseph.sam@gmail.com", "password1");
        loginPage.login(user);
    }

    @And("SpendingMonth: I am on the {string} page")
    public void iAmOnTheSpendingMonthPage(String pageName) {
        this.spendingMonthPage.clickTab();
        this.spendingPage.clickButton("See Current Month");
        navigator.navigateTo(pageName);
    }

    @When("SpendingMonth: I click the {string} button")
    public void iClickTheButtonOnSpendingMonthPage(String buttonName) {
        this.spendingMonthPage.clickButton(buttonName);
    }

    @Then("SpendingMonth: I am redirected to the {string} page")
    public void iAmRedirectedToTheSpendingPage(String pageName) {
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(pageName)));
    }

    @Then("I can see the total monthly spending")
    public void iCanSeeTheTotalMonthlySpending() {
        Assert.assertTrue(driver.findElement(By.id("month-spending")).isDisplayed());
    }

    @And("I can see the spending month bar chart")
    public void iCanSeeTheSpendingMonthBarChart() {
        Assert.assertTrue(driver.findElement(By.id("spending-month-bar-chart")).isDisplayed());
    }

    @And("I can see the category spending month pie chart")
    public void iCanSeeTheCategorySpendingMonthPieChart() {
        Assert.assertTrue(driver.findElement(By.id("spending-month-pie-chart")).isDisplayed());
    }

    @And("I can see the category spending table")
    public void iCanSeeTheCategorySpendingTable() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div/div/section/div[3]/div/div[2]/table")).isDisplayed());
    }

    @When("SpendingMonth: I select {string} from the month selection dropdown")
    public void iSelectFromTheMonthSelectionDropdown(String month) {
        this.spendingMonthPage.selectMonth(month);
    }

    @Then("SpendingMonth: I can see the SpendingMonth page display March spending values")
    public void iCanSeeTheSpendingMonthPageForMarch() {
        Assert.assertTrue(driver.findElement(By.id("spending-month-title")).getText().contains("March"));
    }
}
