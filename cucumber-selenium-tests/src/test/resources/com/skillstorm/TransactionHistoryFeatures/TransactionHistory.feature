@transactionhistory
Feature: Features on the transaction history page
#-----------------------------NAVIGATE TO PAGE FROM Transactions---------------------------#
    Scenario:
        Given I am on the Transactions page
        When I click the Transaction Arrow button
        And I can see Transaction Detailed Information
        And I click the View History button
        Then I can see the list of all my past transactions for a specific transaction

#-----------------------------CREATE---------------------------#

    Scenario Outline:
        Given I am on the "Transaction History" page
        When I click the "Add Transactions" button
        And I fill in the "<name>", "<account>", "<amount>", and "<category>" #might make it so its default the name you picked
        And I click the "Submit" button
        Then I can see the new transaction in my list
    Examples:
        |name|account|amount|category|
        |Netflix| 1 | 15.99 |Entertainment|

#-----------------------------READ---------------------------#
    
    Scenario:
        Given I am on the "Transaction History" page
        Then I can see the list of all my transactions

#-----------------------------UPDATE---------------------------#
    
    Scenario Outline:
        Given I am on the "Transaction History" page
        When I click on a transaction in the graph view
        And I click the "Edit Transaction" button
        And I fill in the "<name>", "<account>", "<amount>", and "<category>" #might make it so its default the name you picked
        And I click the "Submit" button
        Then I can see the updated transaction in my list
    Examples:
        |name|account|amount|category|
        |Netflix| 4 | 3107.42 |Income|

#-----------------------------DELETE---------------------------#
    
    Scenario:
        Given I am on the "Transaction History" page
        When I click the "Trash Icon" button
        Then the transaction is not in the list

#-----------------------------GRAPHICAL SUMMARY---------------------------#
    
    Scenario:
        Given I am on the "Transaction History" page
        Then I can see a graphical summary of my transaction history
