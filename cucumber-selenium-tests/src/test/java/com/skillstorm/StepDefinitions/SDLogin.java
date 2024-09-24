package com.skillstorm.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDLogin {
    private WebDriver driver;
    private LoginPage loginPage;
    private Navigator navigator;

    @Before("@login-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        loginPage = new LoginPage(driver);
    }

    @After("@login-page")
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

    @Given("Login: I am on the login page")
    public void iAmOnTheLoginPage() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
    }

    @And("I enter valid information")
    public void iEnterValidLoginInformation() {
        loginPage.iEnterValidLoginInformation("frontend.tests@gmail.com", "password1");
    }

    @When("I click the Login submit button")
    public void iClickTheLoginSubmitButton() {
        loginPage.clickButton(LoginPage.BTN_SUBMIT_NAME);
    }

    @Then("I am logged in")
    public void  iAmRedirectedToTheDashboardPage() {
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:5173/dashboard"));
    }
}
