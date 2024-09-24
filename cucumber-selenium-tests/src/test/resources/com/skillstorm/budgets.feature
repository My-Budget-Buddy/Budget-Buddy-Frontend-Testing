Feature: Access a list of budgets and spending on each, edit budget amounts, delete budgets, and add budgets. Be able to do the same with savings buckets.
As a user
I should be able to add, delete, and edit budgets
so I can limit and track my spending.

  Scenario: Check if the correct web elements are loaded when on the budgets page
    Given I am on the "Budgets" page
    Then I can see the Budgets page web elements

  # Assumes that the Shopping budget isn't already in the list
  Scenario: Add a new budget
    Given I am on the "Budgets" page
    When I click the "Add-New-Budget" button
    And I select "Shopping" from the "category" selection
    And I enter "500" for the budget amount
    And I click the "Submit" button
    Then I can see a "Shopping" budget of "$500.00" in the budget list

  Scenario: Edit an existing budget
    Given I am on the "Budgets" page
    And there is a "Shopping" budget of "$500.00" in the budget list
    When I click the Edit Budget button
    And I enter "600" for new the budget amount
    And I save the new budget amount
    Then I can see a "Shopping" budget of "$600.00" in the budget list

  Scenario: Delete a budget
    Given I am on the "Budgets" page
    And there is a "Shopping" budget of "$600.00" in the budget list
    When I click the Delete Budget button
    And I click Delete on the delete budget confirmation dialog
    Then I cannot see a "Shopping" budget in the budget list

  Scenario: Add a new Savings buckets
    Given I am on the "Budgets" page
    When I click the "Add-New-Savings-Bucket" button
    And I enter "House Repairs" for the Budget Name
    And I enter "700" for the Required Amount
    And I click the "Submit-New-Savings-Bucket" button
    Then I can see a "House Repairs" savings bucket of "700" in the savings bucket table

  Scenario: Edit an existing Savings bucket
    Given I am on the "Budgets" page
    And there is a "House Repairs" savings bucket of "700" in the savings bucket table
    When I click the Edit Savings Bucket button
    And I enter "800" for the new Required Amount
    And I save the new Required Amount
    Then I can see a "House Repairs" savings bucket of "800" in the savings bucket table

  Scenario: Delete a Savings bucket
    Given I am on the "Budgets" page
    And there is a "House Repairs" savings bucket of "800" in the savings bucket table
    When I click the Delete Savings Bucket button
    And I click Delete on the delete savings bucket confirmation dialog
    Then I cannot see a "House Repairs" savings bucket in the savings bucket table

  Scenario: Edit the Spending Budget
    Given I am on the "Budgets" page
    When I click the "Edit-Spending-Budget" button
    And I enter "6000" for the new Spending Budget
    And I click the "Submit-Spending-Budget" button
    Then I can see a Spending Budget of "$6,000.00"