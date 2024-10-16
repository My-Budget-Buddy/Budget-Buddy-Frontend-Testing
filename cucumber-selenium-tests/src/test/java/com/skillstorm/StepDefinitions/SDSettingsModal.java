package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.Utilities.Authenticator;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;
import com.skillstorm.WebDriverSingleton;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDSettingsModal {
    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;
    User user = new User(
        UserType.NONPERSISTANT,
        Authenticator.USERNAME_NONPERSIST_TWO,
        Authenticator.PASSWORD_NONPERSIST_TWO
    );

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        navigator = new Navigator(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    /**
     * SM1 - using language settings
     */
    @When("I click the \"Languages\" tab")
    public void iClickOnTheLanguagesButton() {
        WebElement languagesButton = driver.findElement(By.cssSelector("[data-testid='languages']"));
        languagesButton.click();
    }

    @And("I select \"Spanish\"")
    public void iSelectSpanish() {
        WebElement spanishRadioButton = driver.findElement(By.xpath("//label[text()='Español']"));
        spanishRadioButton.click();
    }

    @Then("I should see the UI in Spanish")
    public void iShouldSeeMyUIInSpanish() {
        String html = driver.getPageSource();
        // modal changed to Spanish
        Assert.assertTrue(html.contains("Configuración"));
        Assert.assertTrue(html.contains("Idiomas"));
        // navbar changed to Spanish
        Assert.assertTrue(html.contains("Tablero"));
        Assert.assertTrue(html.contains("Transacciones"));
    }

    @Given("I am logged in as a non-persistent user")
    public void iAmLoggedIn() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
    }

    /**
     * SM2 - changing my first and last name
     */
    @When("I fill out the form to change my first and last name")
    public void iFillOutTheFormToChangeMyFirstAndLastName() {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.clear();
        firstNameField.sendKeys("Dave");

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys("Melancon");
    }

    @Then("I should see the changes reflected on the dashboard")
    public void iShouldSeeTheChangesReflectedOnTheDashboard() {
        WebElement anywhere = driver.findElement(By.id("root"));
        // should be found in the bottom left
        // will not be a false positive from the inputs because we're searching both the first and last name space-separated
        wait.until(ExpectedConditions.textToBePresentInElement(anywhere, "Dave Melancon"));
    }

    /**
     * SM3 - changing my password
     */
    @When("I fill out the form to change my password")
    public void iFillOutTheFormToChangeMyPassword() {
        WebElement newPasswordField = driver.findElement(By.id("new-password"));
        newPasswordField.sendKeys("thenewpassword");

        WebElement confirmPasswordField = driver.findElement(By.id("confirm-password"));
        confirmPasswordField.sendKeys("thenewpassword");
    }

    @And("I click the submit buttom in the settings modal")
    public void iClickSubmit() {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Save']"));
        submitButton.click();
    }

    @And("I log out via the button in the settings modal")
    public void iLogOut() {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Log out']"));
        submitButton.click();
    }

    @Then("I should be able to log in with my new password")
    public void iShouldBeAbleToLogInWithMyNewPassword() {
        navigator.navigateTo(Navigator.PGNAME_LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        User newUser = new User(
            UserType.NONPERSISTANT,
            Authenticator.USERNAME_NONPERSIST_TWO,
            "thenewpassword"
        );
        loginPage.login(newUser);
        String currentURL = driver.getCurrentUrl();
        // login must have been successful
        Assert.assertTrue(currentURL.contains(Navigator.URL_DASHBOARD));
    }
}
