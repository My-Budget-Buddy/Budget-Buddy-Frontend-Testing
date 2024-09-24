@signup-page
Feature: Signup Page

    Scenario: Register with valid credentials
        Given I am on the signup page
        And I enter valid signup information
        When I click the signup submit button
        Then I am signed up

    Scenario: Do not register with invalid credentials
        Given I am on the signup page
        And I enter invalid signup information
        When I click the signup submit button
        Then I am not signed up

    Scenario: Do not register with existing credentials
        Given I am on the signup page
        And I enter existing signup information
        When I click the signup submit button
        Then I am not signed up

    Scenario: Show password button displays password text on Signup page
        Given I am on the signup page
        And I enter valid signup information
        When I click the Signup Show Password button
        Then I can see the password on the signup form

    Scenario: Navigate to Login page when clicking the Login link button
        Given I am on the signup page
        When I click the Login link button
        Then I am redirected to the Login page