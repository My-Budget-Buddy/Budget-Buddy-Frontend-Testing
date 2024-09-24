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

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
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
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:5173/dashboard"));
    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:5173/login"));
    }

    @Then("I can see the password")
    public void iCanSeeThePassword() {
        Assert.assertTrue(loginPage.isPasswordShown());
    }

    @Then("I am redirected to the Signup page")
    public void iAmRedirectedToTheSignupPage() {
        loginPage.waitAfterRedirectingToSignupPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:5173/register"));
    }
}
