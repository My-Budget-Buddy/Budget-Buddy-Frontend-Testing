package com.skillstorm.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



import org.openqa.selenium.WebDriver;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.TaxPage;
import static org.testng.Assert.assertTrue;
/**
 * This class represents the step definitions for the TaxPage feature.
 * It contains the implementation of the test steps related to the TaxPage functionality.
 */
public class SDTax {
    private WebDriver driver;
    private TaxPage taxPage;
    
    @Before
    public void before(){
        driver = WebDriverSingleton.getDriver();
        this.taxPage = new TaxPage(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
    

    @Then("I should see the tax estimation form deleted")
    public void i_should_see_the_tax_estimation_form_deleted() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(taxPage.checkIfDeleteWorked());
    }

    @Then("I should see the existing tax estimation form")
    public void i_should_see_the_existing_tax_estimation_form() {
        taxPage.assureTaxRecordAppears();
        assertTrue(taxPage.checkForListOfExistingTaxEstimationRecords());
    }

    @When("i enter the following personal information {string} {string} {string} {string} {string} {string} {string} {string}")
    public void i_enter_the_following_personal_information_john_doe_main_st_tampa_fl(String firstName, String lastName, String streetAddress, String city, String state, String zip, String DOB, String SSN) {
        taxPage.fillOutPersonalInformation(firstName, lastName, streetAddress, city, state, zip, DOB, SSN);
    }
    @When("I click the {string} button followed by the {string} button")
    public void i_click_the_pi_save_button_button_followed_by_the_next_button_button(String button, String button2) {
        taxPage.clickButton(button);
        taxPage.clickButton(button2);
    }

    @When("I enter the following W2 information {string} {string} {string} {string} {string} {string} {string}")
    public void i_enter_the_following_w2_information_fl_publix(String state, String employer, String wages, String federalTax,String stateTax, String SSNTax, String medicareTax) {
        taxPage.fillOutW2Information(state, employer, wages, federalTax, stateTax, SSNTax, medicareTax);
    }

    @When("I enter valid other income information such as {string} {string} {string} {string} {string}")
    public void i_enter_valid_other_income_information_such_as(String longTermCapitalGains, String shortTermCapitalGains, String netBusinessIncome,String otherInvestments, String additionalIncome) {
        taxPage.fillOutOtherIncomeInformation(longTermCapitalGains, shortTermCapitalGains, netBusinessIncome, otherInvestments, additionalIncome);
    }


    @When("I enter valid deductions information such as {string} {string}")
    public void i_enter_valid_deductions_information_such_as_health_savings_account(String deductionType, String deductionAmount) {
        taxPage.fillOutDeductions(deductionType, deductionAmount);
    }

    @When("I review and and click the {string} button")
    public void i_review_and_and_click_the_review_submit_button_button(String reviewSubmit) {
        taxPage.clickReviewAndSubmit(reviewSubmit);
    }
    @Then("I should see the updated tax estimation")
    public void i_should_see_the_updated_tax_estimation() {
        assertTrue(taxPage.CheckForEstimationResultsPage());
    }


}

