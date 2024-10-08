package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    private WebDriverWait wait;

    @Before("@login-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After("@login-page")
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(LoginPage.BTN_SUBMIT_ID)));
    }

    @And("I enter valid information")
    public void iEnterValidLoginInformation() {
        loginPage.iEnterValidLoginInformation("frontend.tests@gmail.com", "password1");
    }

    @And("I enter invalid information")
    public void iEnterInvalidLoginInformation() {
        loginPage.iEnterInvalidLoginInformation();
    }

    @When("I click the Login submit button")
    public void iClickTheLoginSubmitButton() {
        loginPage.clickButton(LoginPage.BTN_SUBMIT_NAME);
    }
    
    @When("I click the Show Password button")
    public void iClickTheShowPasswordButton() {
        loginPage.clickButton(LoginPage.BTN_SHOW_PASSWORD_NAME);
    }

    @When("I click the Create Account button")
    public void iClickTheCreateAccountButton() {
        loginPage.clickButton(LoginPage.BTN_CREATE_ACCOUNT_NAME);
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        loginPage.waitAfterValidLoginSubmit();
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_DASHBOARD)));
    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_LOGIN)));
    }

    @Then("I can see the password")
    public void iCanSeeThePassword() {
        Assert.assertTrue(loginPage.isPasswordShown());
    }

    @Then("I am redirected to the Signup page")
    public void iAmRedirectedToTheSignupPage() {
        loginPage.waitAfterRedirectingToSignupPage();
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_SIGNUP)));
    }
}
