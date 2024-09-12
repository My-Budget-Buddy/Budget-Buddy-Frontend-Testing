@spending-page
Feature: Dashboard to analyze spending habits and income

    Scenario Outline: Navigate to "Spending Month" page when clicking "See Current Month" button
        Given I am on the <pageName> page
        When I click the <buttonName> button
        Then I will be navigated to the <navigatedPageName> page

        Examples:
            | pageName | buttonName      | navigatedPageName |
            | Spending | SeeCurrentMonth | SpendingMonth     |

    Scenario Outline: See all expected displays on the Spending page when values exist
        Given I am on the <pageName> page
        Then I can see the spendings for the week card
        And I can see the deposits for the week card
        And I can see the annual total spent for the week card
        And I can see the spendings graph for the year
        And I can see the category expenses table
        And I can see the top three cateogries of spending
        And I can see the top three purchases
        And I can see the top vendors

        Examples:
            | pageName |
            | Spending |