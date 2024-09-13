package com.skillstorm.StepDefinitions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.PageObjects.TransactionPage;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class SDTransactions {
    private TransactionPage transactions;

    @Before("@transaction")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
    }


    //start general SD
    @Given("I am on the Transactions page")
    public void iAmOnTheTranactionsPage(){
    }


    @And("I click the Submit button")
    public void iClickTheSubmitButton(){}
    //end general SD


    
    //start create
    @When("I click the Add Transactions button")
    public void iClickTheAddTransactionButton(){}

    @And("And I fill in the {string}, {string}, {string}, and {string}")
    public void andIFillInThe(String name, String account, String amount, String category){}

    @Then("I can see the new transaction in my list")
    public void iCanSeeTheNewTransactionInMyList(){}
    //end create



    //start read
    @Then("I can see the list of all my transactions")
    public void iCanSeeTheListOfAllMyTransactions(){}
    //end read

    

    //start update
    @When("I click the Pencil Icon button")
    public void iClickThePencilIconButton(){}

    @And("And I update the {string}, {string}, {string}, and {string}")
    public void andIUpdateThe(String name, String account, String amount, String category){}

    @Then ("I can see the updated transaction in my list")
    public void iCanSeeTheUpdatedTransactionInMyList(){}
    //end update



    //start delete
    @When("I click the Trash Icon button")
    public void iClickTheTrashIconButton(){
    }

    @Then("the transaction is not in the list")
    public void theTransactionIsNotInTheList(){}
    //end delete



    //start category
    @When("I click the All Categories button")
    public void iClickTheAllCategoriesButton(){}

    @And("I click a {string} to filter based on")
    public void iClickToFilterBasedOn(String category){}

    @Then("only transactions with that category should be visible")
    public void onlyTransactionsWithThatCategoryShouldBeVisible(){}
    //end category


}
