@transaction
Feature: Features on the transactions page
#-----------------------------CREATE---------------------------#
  # Scenario Outline:
  #   Given I login
  #   And I am on the Transactions page
  #   When I click the "addTransactionModal" button
  #   And I fill in the "<name>", "<account>", "<amount>", and "<category>"
  #   And I click the "addTransactionBtn" button
  #   Then I can see the new transaction in my list
  #   Examples:
  #     | name       | account                      | amount   | category |
  #     | Skillstorm | Bank of Skillstorm - Savings | 10087.99 | Income   |
#-----------------------------READ---------------------------#
  # Scenario:
  #   Given I login
  #   And I am on the Transactions page
  #   Then I can see the list of all my transactions
#-----------------------------UPDATE---------------------------#
  # Scenario Outline:
  #   Given I login
  #   And I am on the Transactions page
  #   When I click the edit icon
  #   And I update the "<name>", "<account>", "<amount>", and "<category>"
  #   And I click the "editTransactionBtn" button
  #   Then I can see the updated transaction in my list
  #   Examples:
  #     | name      | account       | amount | category |
  #     | Bojangles | Keystone Bank |  15.99 | Dining   |
#-----------------------------DELETE---------------------------#
  # Scenario:
  #   Given I login
  #   And I am on the Transactions page
  #   When I click the Trash Icon button
  #   Then the transaction is not in the list
#-----------------------------CATEGORY---------------------------#
  # Scenario:
  #   Given I login
  #   And I am on the Transactions page
  #   When I click the "allCategoriesDropDown" button
  #   And I click a "<category>" to filter based on
  #   Then only transactions with that category should be visible
  #   Examples:
  #     | category        |
  #     | Groceries       |
  #     | Entertainment   |
  #     | Dining          |
  #     | Transportation  |
  #     | Healthcare      |
  #     | Living Expenses |
  #     | Shopping        |
  #     | Income          |
  #     | Misc            |
#-----------------------------SORT BY AMOUNT DESCENDING---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    When I click the "sortByDropdown" button
    And I click the sort by amount option
    Then the transactions should be sorted by amount
#-----------------------------SORT BY ACCOUNT ASCENDING---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    When I click the "sortByDropdown" button
    And I click the sort by amount option
    And I click the "directionDropdown" button
    And I click the ascending option
    Then the transactions should be sorted by amount in ascending order
#-----------------------------SORT BY DATE ASCENDING---------------------------#

  Scenario:
    Given I login
    And I am on the Transactions page
    When I click the "sortByDropdown" button
    And I click a the sort by date option
    And I click the "directionDropdown" button
    And I click the ascending option
    Then the transactions should be sorted by date in ascending order
#-----------------------------CLEAR FILTER BUTTON---------------------------#

  Scenario Outline:
    Given I login
    And I am on the Transactions page
    When I click the "allCategoriesDropDown" button
    And I click the "<category>" to filter based on
    Then I click the "clearFilterBtn" button
    Then all transactions should be visible

    Examples:
      | category  |
      | Groceries |
#-----------------------------AMOUNT RANGE FILTER---------------------------#

  Scenario Outline:
    Given I login
    And I am on the Transactions page
    When I click the "allAmountsDropDown" button
    And I select the amount range option
    And I fill in the "<minAmount>" and "<maxAmount>" fields
    Then only transactions within that amount range should be visible

    Examples:
      | minAmount | maxAmount |
      |        0 |        20 |
      |        50 |       100 |
#-----------------------------DATE RANGE FILTER---------------------------#

  Scenario Outline:
    Given I login
    And I am on the Transactions page
    When I click the "allDatesDropDown" button
    And I select the date range option
    And I fill in the "<minDate>" and "<maxDate>" fields
    Then only transactions within that date range should be visible

    Examples:
      | minDate   | maxDate   |
      | 5/15/2024 | 5/17/2024 |
