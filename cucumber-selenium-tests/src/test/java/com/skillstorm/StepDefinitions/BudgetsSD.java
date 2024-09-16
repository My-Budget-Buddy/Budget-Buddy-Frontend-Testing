package com.skillstorm.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.WebDriverSingleton;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BudgetsSD {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @When("the Budgets page is fully loaded")
    public void theBudgetsPageIsFullyLoaded() {
        System.out.println("When ran");
    }

    @Then("I can see the Budgets page web elements")
    public void iCanSeeThePageWebElements(){
        System.out.println("Then ran");
    }

}
