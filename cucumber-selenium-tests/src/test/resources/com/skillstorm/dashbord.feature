Feature:

    Scenario: DB1 - looking at drop down options when logged in
        Given I have multiple "<objects>"
        And I am on the "Dashboard" page
        When click on "<option>" option
        Then I can see a list of my "<objects>"

        | objects           | option        |
        | Checking Accounts | Checkings     |
        | Savings Accounts  | Savings       |
        | Credit Cards      | Credit Cards  |
        | Investments       | Investments   |
    
    
    Scenario: DB2 - looking at recent spending
        Given I have multiple "Spending Transactions"
        Given I am on the "Dashboard" page
        Then I see a Current Spending Table
        And The spending line reflects my spending

    
    Scenario: DB3 - looking at transaction info
        Given I have recent transactions
        Given I am on the "Dashboard" page
        When I click the "Transaction Arrow" button
        Then A pop up of the transaction appears


    Scenario: DB4 - checking transaction button redirection
        Given I am on the "Dashboard" page
        When I click the "View All Transactions" Button
        Then I am redirected to the "Transactions" page


    Scenario: DB5 - looking at Budget overview
        Given I have a budget plan
        Given I am on the "Dashboard" Page
        Then I can see budget information

