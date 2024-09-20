@spending-page
Feature: Dashboard to analyze spending habits and income

    Scenario: Navigate to "Spending Month" page when clicking "See Current Month" button
        Given Spending: I am logged in
        And I am on the Spending page
        When I click the See Current Month button
        Then I am redirected to the SpendingMonth page

    Scenario: See all expected displays on the Spending page when values exist
        Given Spending: I am logged in
        And I am on the Spending page
        Then I can see the three cards: spent this week, deposited this week, and Annual Total Spent
        And I can see the spendings and earned graph for the year
        And I can see the pie chart of spending categories
        And I can see the spending categories table
        And I can see the top categories of spending, top three purchases, and top three vendors