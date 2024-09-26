@transaction
Feature: Features on the transactions page
#-----------------------------CREATE---------------------------#

  Scenario Outline:
    Given I login
    And I am on the Transactions page
    When I click the "addTransactionModal" button
    And I fill in the "<name>", "<account>", "<amount>", and "<category>"
    And I click the "addTransactionBtn" button
    Then I can see the new transaction in my list

    Examples:
      | name       | account                      | amount   | category |
      | Skillstorm | Bank of Skillstorm - Savings | 10087.99 | Income   |
#-----------------------------READ---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    Then I can see the list of all my transactions
#-----------------------------UPDATE---------------------------#

  Scenario Outline:
    Given I login
    And I am on the Transactions page
    When I click the edit icon
    And I update the "<name>", "<account>", "<amount>", and "<category>"
    And I click the "editTransactionBtn" button
    Then I can see the updated transaction in my list

    Examples:
      | name      | account       | amount | category |
      | Bojangles | Keystone Bank |  15.99 | Dining   |
#-----------------------------DELETE---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    When I click the Trash Icon button
    Then the transaction is not in the list
#-----------------------------CATEGORY---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    When I click the "allCategoriesDropDown" button
    And I click a "<category>" to filter based on
    Then only transactions with that category should be visible

    Examples:
      | category        |
      | Groceries       |
      | Entertainment   |
      | Dining          |
      | Transportation  |
      | Healthcare      |
      | Living Expenses |
      | Shopping        |
      | Income          |
      | Misc            |
