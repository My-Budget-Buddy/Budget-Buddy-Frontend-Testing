package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.ErrorPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SDError {
    WebDriver driver;
    WebDriverWait wait;
    ErrorPage page;
    Navigator navigator;
    User user;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        page = new ErrorPage(driver);
        navigator = new Navigator(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Then("I can see the error page message")
    public void iCanSeeTheErrorPageMessage() {
        wait.until(ExpectedConditions.visibilityOf(page.getWebElement("errorHeader")));
        Assert.assertTrue(page.getWebElement("errorHeader").isDisplayed());
    }
}