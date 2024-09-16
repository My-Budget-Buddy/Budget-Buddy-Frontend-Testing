@transactionhistory
Feature: Features on the transaction history page
#-----------------------------NAVIGATE TO PAGE FROM Transactions---------------------------#
    Scenario:
        Given I am on the "Login" page
        And I login
        Given I start on the Transactions page
        When I click the "btnTransactionArrow" button
        And I can see Transaction Detailed Information
        And I click the "viewHistoryBtn" button
        Then I can see the list of all my past transactions for a specific transaction

#-----------------------------CREATE---------------------------#

    # Scenario Outline:
    #     Given I am on the "Login" page
    #     And I login
    #     Given I am on the Transaction History page
    #     When I click the "addTransactionModal" button
    #     And I fill in the "<account>", "<amount>", and "<category>"
    #     And I click the "addTransactionBtn" button
    #     Then I can see the new transaction in my list
    # Examples:
    #     |name|account|amount|category|
    #     |Netflix| 1 | 15.99 |Entertainment|

#-----------------------------READ---------------------------#
    
    # Scenario:
    #     Given I am on the "Login" page
    #     And I login
    #     Given I am on the Transaction History page
    #     Then I can see the list of all my past transactions

#-----------------------------UPDATE---------------------------#
    
    # Scenario Outline:
    #     Given I am on the "Login" page
    #     And I login
    #     Given I am on the Transaction History page
    #     When I click on a transaction in the graph view
    #     And I click the Edit Transaction button
    #     And I update the "<account>", "<amount>", and "<category>" 
    #     And I click the Submit button
    #     Then I can see the updated transaction in my list
    # Examples:
    #     |name|account|amount|category|
    #     |Netflix| 4 | 3107.42 |Income|

#-----------------------------DELETE---------------------------#
    
    # Scenario:
    #     Given I am on the "Login" page
    #     And I login
    #     Given I am on the Transaction History page
    #     When I click the Trash Icon button
    #     Then the transaction is not in the list

#-----------------------------GRAPHICAL SUMMARY---------------------------#
    
    # Scenario:
    #     Given I am on the "Login" page
    #     And I login
    #     Given I am on the Transaction History page
    #     Then I can see a graphical summary of my transaction history
