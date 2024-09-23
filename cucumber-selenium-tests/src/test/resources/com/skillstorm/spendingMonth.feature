@spending-month-page
Feature: Dashboard to analyze monthly earnings and spendings

    Scenario: Navigate to "Spending" page when clicking "Back to Annual Spending Overview" button
        Given SpendingMonth: I am logged in
        And I am on the SpendingMonth page
        When I click the Back to Annual Spending Overview button
        Then I am redirected to the Spending page

    Scenario: See all expected displays on the SpendingMonth page when values exist
        Given SpendingMonth: I am logged in
        And I am on the SpendingMonth page
        Then I can see the total monthly spending
        And I can see the spending month bar chart
        And I can see the category spending month pie chart
        And I can see the category spending table

    Scenario: See specific month spending on bar graph when selecting from dropdown
        Given SpendingMonth: I am logged in
        And I am on the SpendingMonth page
        When I select "March" from the month selection dropdown
        Then I can see the SpendingMonth page display March spending values
