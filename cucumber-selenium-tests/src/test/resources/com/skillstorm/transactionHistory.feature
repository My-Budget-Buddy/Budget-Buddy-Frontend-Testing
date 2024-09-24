@transactionhistory
Feature: Features on the transaction history page
#-----------------------------NAVIGATE TO PAGE FROM Transactions---------------------------#

  Scenario:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button
    Then I can see the list of all my past transactions for a specific transaction
#-----------------------------CREATE---------------------------#

  Scenario Outline:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button

    And I am on the Transaction History page
    When I click the "addTransactionModal" button
    And I fill in the "<account>", "<amount>", and "<category>"
    And I click the create submit button
    Then I can see the new transaction in my past history list

    Examples:
      | account                | amount | category       |
      | Ascend Financial Group | 300.00 | Transportation |
#-----------------------------READ---------------------------#

  Scenario:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button

    And I am on the Transaction History page
    Then I can see the list of all my past transactions for a specific transaction
#-----------------------------UPDATE---------------------------#

  Scenario Outline:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button

    And I am on the Transaction History page
    When I click the edit button
    And I update the "<account>", "<amount>", and "<category>"
    And I click the edit submit button
    Then I can see the updated transaction in my past history list

    Examples:
      | account       | amount  | category |
      | Keystone Bank | 3107.42 | Income   |
#-----------------------------DELETE---------------------------#

  Scenario:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button

    And I am on the Transaction History page
    When I click on the Trash Icon button
    Then the transaction is not in the list of past transactions
#-----------------------------GRAPHICAL SUMMARY---------------------------#

  Scenario:
    Given I login into the webapp
    And I start on the Transactions page
    When I click the "btnTransactionArrow" button
    And I can see Transaction Detailed Information
    And I click the "viewHistoryBtn" button

    And I am on the Transaction History page
    Then I can see a graphical summary of my transaction history
