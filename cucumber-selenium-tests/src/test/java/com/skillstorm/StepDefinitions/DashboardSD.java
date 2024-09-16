package com.skillstorm.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.Button;

public class DashboardSD {
    



        /**
         * This methods signs into a user account that has mulptle objects
         * @param ojbects
         * 
         *      | objects           | option        |
         *      | Checking Accounts | Checkings     |
         *      | Savings Accounts  | Savings       |
         *      | Credit Cards      | Credit Cards  |
         *      | Investments       | Investments   |
         * 
         */
        @Given("I have multiple {string}")
        public void iHaveMulitple(String ojbects) {

        }

        /**
         * This method will take a user and create a budget plan for them
         */
        @Given("I have a budget plan")
        public void iHaveABudgetPlan() {

        }

        /**
         * This method will create some recent transactions for user
         */
        @Given("I have recent transactions")
        public void iHaveRecentTransactions() {

        }

        /**
         * This method will click on one of the accordion options on the page
         * @param option - The name of the accordion "list"
         * 
         *      | objects           | option        |
         *      | Checking Accounts | Checkings     |
         *      | Savings Accounts  | Savings       |
         *      | Credit Cards      | Credit Cards  |
         *      | Investments       | Investments   |
         * 
         */
        @When("I click on {string} option")
        public void iClickOnOption (String option) {
            
        }

        
        /**
         * This method makes sure that the accordian list appeared
         * @param ojbects
         * 
         *      | objects           | option        |
         *      | Checking Accounts | Checkings     |
         *      | Savings Accounts  | Savings       |
         *      | Credit Cards      | Credit Cards  |
         *      | Investments       | Investments   |
         * 
         */
        @Then("I can see a list of my {string}")
        public void iCanSeeAListOfMy(String objects) {

        }

        @Then("I see a Current Spending Table")
        public void iSeeACurrentSpendingTable() {

        }

        @Then("The spending line reflects my spending")
        public void theSpendingLineReflectsMySpending() {

        }

        @Then("A pop up of the transaction appears")
        public void aPopUpOfTheTransactionAppears() {

        }

        @Then("I can see budget information")
        public void iCanSeeBudgetInformation() {

        }


}
