Feature: Landing Page Scenerios

  Scenario: LA1 - Get Started Button
    Given I am on the “Landing” page
    And I am Logged Out
    When I click the “Get Started” button
    Then I am redirected to the “Register” page

  
  Scenario: LA2 - Login Button
    Given I am on the “Landing” page
    And I am logged out
    When I click the “Login” button
    Then I am redirected to the “Login” page

  
  Scenario: LA3 - Register Button
    Given I am on the “Landing” page
    And I am logged out
    When I click the “Register” button
    Then I am redirected to the “Register” page

  
  Scenario: LA4 - See the Landing Generic Info
    Given I am on the “Landing” page
    Then I can see general info about Budget Buddy

  
  Scenario: LA5 - Log out on Landing Page
    Given I am on the “Landing” page
    And I am logged in
    When I click the “Log Out” button
    Then I should see the logged out navbar

  
  Scenario: LA6 - Go to Dashboard from Landing
    Given I am on the “Landing” page
    And I am logged in
    When I click the “Dashboard” button
    Then I am redirected to the “Dashboard” page


