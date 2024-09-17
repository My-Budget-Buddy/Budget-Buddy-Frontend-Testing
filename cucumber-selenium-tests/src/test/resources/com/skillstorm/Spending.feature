@spending-page
Feature: Dashboard to analyze spending habits and income

    Scenario: Navigate to "Spending Month" page when clicking "See Current Month" button
        Given Spending: I am logged in
        And Spending: I am on the "Spending" page
        When Spending: I click the "See Current Month" button
        Then Spending: I am redirected to the "SpendingMonth" page

    Scenario: See all expected displays on the Spending page when values exist
        Given Spending: I am logged in
        And Spending: I am on the "Spending" page
        Then I can see the spendings for the week card
        And I can see the deposits for the week card
        And I can see the annual total spent for the week card
        And I can see the spendings graph for the year
        And I can see the category expenses table
        And I can see the top three cateogries of spending
        And I can see the top three purchases
        And I can see the top vendors