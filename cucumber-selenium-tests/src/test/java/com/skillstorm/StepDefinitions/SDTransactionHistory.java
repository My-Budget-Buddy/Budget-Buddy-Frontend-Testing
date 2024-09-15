package com.skillstorm.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.PageObjects.TransactionHistoryPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class SDTransactionHistory {
    private TransactionHistoryPage transactionHistory;
    
    
    @Before("@transactionhistory")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
    }

    //start general
    @Given("I am on the Transaction History page")
    public void iAmOnTheTransactionHistoryPage(){}

    // @And("I click the Submit button")
    // public void iClickTheSubmitButton(){}
    //end general


    
    //start navigate to transactions page
    @Given("I start on the Transactions page")
    public void iStartOnTheTransactionPage(){}

    @When("I click the Transaction Arrow button")
    public void iClickTheTransactionArrowButton(){}

    @And("I can see Transaction Detailed Information")
    public void iCanSeeTransactionDetailedInformation(){}

    @And("I click the View History button")
    public void iClickTheViewHistoryButton(){}

    @And("I can see the list of all my past transactions for a specific transaction")
    public void iCanSeeTheListOfAllMyPastTransactionsForASpecificTransaction(){}

    //end navigate to transactions page


    //start create

    // @When("I click the Add Transactions button")
    // public void iClickTheAddTransactionButton(){}

    // @And("I fill in the {string}, {string}, {string}, and {string}")
    // public void iFillInThe(String name, String account, String amount, String category){}

    // @Then("I can see the new transaction in my list")
    // public void iCanSeeTheNewTransactionInMyList(){}

    //end create


    //start read
    @Then(" I can see the list of all my past transactions")
    public void iCanSeeTheListOfAllMyPastTransactions(){}
    //end read

    //start update
    // @When("I click on a transaction in the graph view")
    // public void iClickOnATransactionInTheGraphView(){}

    // @And("I click the Edit Transaction button")
    // public void iClickTheEditTransactionButton(){}

    // @And ("I update the {string}, {string}, {string}, and {string}")
    // public void iUpdateThe(){}

    // @Then("I can see the updated transaction in my list")
    // public void iCanSeeTheUpdatedTransactionInMyList(){}

    //end update

    //start delete
    // @When("I click the Trash Icon button")
    // public void iClickTheTrashIconButton(){
    // }

    // @Then("the transaction is not in the list")
    // public void theTransactionIsNotInTheList(){}
    //end delete

    //start graphical summary
    @Then("I can see a graphical summary of my transaction history")
    public void iCanSeeAGraphicalSummaryOfMyTransactionHistory(){}
     //end graphical summary
}
