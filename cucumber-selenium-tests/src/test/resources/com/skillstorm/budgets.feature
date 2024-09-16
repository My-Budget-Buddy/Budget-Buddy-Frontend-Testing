Feature: Access a list of budgets and spending on each, edit budget amounts, delete budgets, and add budgets. Be able to do the same with savings buckets.
As a user
I should be able to add, delete, and edit budgets
so I can limit and track my spending.

  Scenario: Check if the correct web elements are loaded when on the budgets page
    Given I am on the "Budgets" page
    When the Budgets page is fully loaded
    Then I can see the Budgets page web elements

  # Scenario: Add a new budget
  #   Given I am on the "Budgets" page
  #   When I click the "Add New Budget" button
  #   And I select "Groceries" from the "Category" selection
  #   And I enter "500" for the budget amount
  #   And I click the "Submit" button
  #   Then I can see a budget in the budget list with the new information

  # Scenario: Edit an existing budget
  #   Given I am on the "Budgets" page
  #   When I click the "Edit Grocery Budget" button
  #   And I enter "600" for the budget amount
  #   And I click the "Save" button
  #   Then I can see a new budget in the budget list with the new information
  #   And I cannot see a budget in the budget list with the old information

  # Scenario: Delete a budget
  #   Given I am on the "Budgets" page
  #   And there is a "Groceries" budget in the budget list
  #   When I click on the "Delete Groceries" button
  #   Then I cannot see a "Groceries" budget in the budget list

  # Scenario: Add a new Savings buckets
  #   Given I am on the "Budgets" page
  #   When I click the "Add New Savings Bucket" button
  #   And I enter the name "House Repairs"
  #   And I enter "500" for the budget amount
  #   And I click the "Submit" button
  #   Then I can see a savings bucket in the savings bucket with the new information






