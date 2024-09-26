package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.SignupPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDSignup {
    private WebDriver driver;
    private SignupPage signupPage;
    private Navigator navigator;
    private WebDriverWait wait;

    @Before("@signup-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After("@signup-page")
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

    @Given("I am on the signup page")
    public void iAmOnTheSignupPage() {
        navigator.navigateTo(Navigator.PGNAME_SIGNUP);
        signupPage = new SignupPage(driver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(SignupPage.BTN_CREATEACCOUNT_ID)));
    }

    @And("I enter valid signup information")
    public void iEnterValidSignupInformation() {
        signupPage.iEnterValidSignupInformation();
    }

    @And("I enter invalid signup information")
    public void iEnterInvalidSignupInformation() {
        signupPage.iEnterInvalidSignupInformation();
    }

    @And("I enter existing signup information")
    public void iEnterExistingSignupInformation() {
        signupPage.iEnterExistingSignupInformation();
    }

    @When("I click the signup submit button")
    public void iClickTheSignupSubmitButton() {
        signupPage.clickButton(SignupPage.BTN_CREATEACCOUNT_NAME);
    }

    @When("I click the Signup Show Password button")
    public void iClickTheSignupShowPasswordButton() {
        signupPage.clickButton(SignupPage.BTN_SHOWPASSWORD_NAME);
    }

    @When("I click the Login link button")
    public void iClickTheLoginLinkButton() {
        signupPage.clickButton(SignupPage.BTN_LOGIN_NAME);
    }

    @Then("I am signed up")
    public void iAmSignedUp() {
        signupPage.waitForLoginPageNavigation();
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_LOGIN)));
    }

    @Then("I am not signed up")
    public void iAmNotSignedUp() {
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_SIGNUP)));
    }

    @Then("I can see the password on the signup form")
    public void iCanSeeThePasswordOnTheSignupForm() {
        Assert.assertTrue(signupPage.isSignupPasswordShown());
    }

    @Then("I am redirected to the Login page")
    public void iAmRedirectedToTheLoginPage() {
        signupPage.waitForLoginPageNavigation();
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(Navigator.PGNAME_LOGIN)));
    }
}
