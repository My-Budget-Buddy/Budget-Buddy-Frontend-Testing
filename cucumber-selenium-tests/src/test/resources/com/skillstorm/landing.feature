Feature: Landing Page Scenerios

  Scenario: LA1 - Get Started Button while logged out
    Given I am logged out on Landing Page
    When I click the "btnGetStarted" button
    Then I am redirected to the "Login" page

  Scenario: LA1.1 - Get Started Button while logged in
    Given I am logged in on Landing Page
    When I click the "btnGetStarted" button
    Then I am redirected to the "Dashboard" page

  
  Scenario: LA2 - Login Button
    Given I am logged out on Landing Page
    When I click the "login-link" button
    Then I am redirected to the "Login" page

  
  Scenario: LA3 - Register Button
    Given I am logged out on Landing Page
    When I click the "register-link" button
    Then I am redirected to the "Signup" page

  
  Scenario: LA4 - See the Landing Generic Info
    Given I am on the "Landing" page
    Then I can see general info about Budget Buddy

  
  Scenario: LA5 - Log out on Landing Page
    Given I am logged in on Landing Page
    When I click the "logout-button" button
    Then I Can see the Logged Out Navbar

  
  Scenario: LA6 - Go to Dashboard from Landing
    Given I am logged in on Landing Page
    When I click the "dashboard-link" button
    Then I am redirected to the "Dashboard" page


