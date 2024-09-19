Feature: When entering an invalid URL, the user should see an error page and have the option to be redirected to the landing page
As a user
I should be able to see an error page when entering an invalid URL and be redirected to the landing page
so that I can navigate the website without any issues

  Scenario: Check that the error page is displayed when entering an invalid URL
    Given I am on the "Error" page
    Then I can see the error page message

  Scenario: Check that the user can be redirected to the landing page from the error page
    Given I am on the "Error" page
    When I click the "Return-to-homepage" button
    Then I am redirected to the "Landing" page
