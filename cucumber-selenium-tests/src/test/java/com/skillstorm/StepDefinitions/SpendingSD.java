package com.skillstorm.StepDefinitions;

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

public class SpendingSD {
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
        User user = new User(UserType.PERSISTANT, "joseph.sam@gmail.com", "password1");
        loginPage.login(user);
    }

    @And("Spending: I am on the {string} page")
    public void iAmOnTheSpendingPage(String pageName) {
        this.spendingPage.clickTab();
        navigator.navigateTo(pageName);
    }

    @When("Spending: I click the {string} button")
    public void iClickTheButtonOnSpendingPage(String buttonName) {
        this.spendingPage.clickButton(buttonName);
    }

    @Then("Spending: I am redirected to the {string} page")
    public void  iAmRedirectedToTheSpendingMonthPage(String pageName) {
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(pageName)));
    }
}
