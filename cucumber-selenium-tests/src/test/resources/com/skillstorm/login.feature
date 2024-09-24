@login-page
Feature: Login Page

    Scenario: Login with valid credentials
        Given I am on the login page
        And I enter valid information
        When I click the Login submit button
        Then I am logged in

    Scenario: Do not login with invalid credentials
        Given I am on the login page
        And I enter invalid information
        Then I am not logged in

    Scenario: Show password button displays password text
        Given I am on the login page
        And I enter valid information
        When I click the Show Password button
        Then I can see the password

    Scenario: Navigate to Signup page when clicking the Create Account button
        Given I am on the login page
        When I click the Create Account button
        Then I am redirected to the Signup page