@settings-modal
Feature:

    Scenario: SM1 - using language settings
        Given I am on the "Dashboard" page
        And I click the "toggle-settings-modal-button" button
        When I click the "Languages" tab
        And I select "Spanish"
        Then I should see the UI in Spanish

    Scenario: SM2 - changing my first and last name
        Given I am logged in as a non-persistent user
        And I click the "toggle-settings-modal-button" button
        When I fill out the form to change my first and last name
        And I click the submit buttom in the settings modal
        Then I should see the changes reflected on the dashboard

    Scenario: SM3- changing my password
        Given I am logged in as a non-persistent user
        And I click the "toggle-settings-modal-button" button
        When I fill out the form to change my password
        And I click the submit buttom in the settings modal
        And I log out via the button in the settings modal
        Then I should be able to log in with my new password
