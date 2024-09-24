Feature:

    Scenario: DB1 - looking at drop down options when logged in
        Given I have multiple "<objects>"
        And I am on the "Dashboard" page
        When I click on the "<option>" option on Dashboard
        Then I can see the "<list>" on Dashboard

        Examples:
        | objects           | option        | list             |
        | Checking Accounts | Checkings     | Checkings List   |
        | Savings Accounts  | Savings       | Savings List     |
        | Credit Cards      | Credit Cards  | Credit Card List |
        | Investments       | Investments   | Investments List |
    
    
    Scenario: DB2 - looking at recent spending
        Given I have multiple "Spending Transactions"
        Given I am on the "Dashboard" page
        Then I see a Current Spending Table
        And The spending line reflects my spending

    
    Scenario: DB3 - looking at transaction info
        Given I have recent transactions
        Given I am on the "Dashboard" page
        When I click the "btnTransactionArrow" button
        Then A pop up of the transaction appears


    Scenario: DB4 - checking transaction button redirection
        Given I am on the "Dashboard" page
        When I click the "btnViewAllTransactions" button
        Then I am redirected to the "Transactions" page


    Scenario: DB5 - looking at Budget overview
        Given I have a budget plan
        Given I am on the "Dashboard" page
        Then I can see budget information

