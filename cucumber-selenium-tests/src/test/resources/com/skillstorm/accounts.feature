Feature:

    # Scenario: DB1 - looking at drop down options when logged in
    #     Given I have multiple "<objects>"  
    #     And I am on the "Accounts" page
    #     When I click on the "<option>" option on Accounts
    #     Then I can see the "<list>" on Accounts

    #     Examples:
    #     | objects           | option        | list             |
    #     | Checking Accounts | Checkings     | Checkings List   |
    #     | Savings Accounts  | Savings       | Savings List     |
    #     | Credit Cards      | Credit Cards  | Credit Card List |
    #     | Investments       | Investments   | Investments List |

    Scenario: AC2 - Creating a new account
        Given I am on the "Accounts" page
        When I click the "Add Account" button on Accounts
        And I enter the following information: "<accountType>", "<name>", "<accountNum>", "<routing>", "<balance>"
        Then that specific account is created

    # Examples:
    # | accountType  | name               | accountNum | routing   | balance |
    # | Checkings    | Checking Test Bank | 123456789  | 987654321 | 4567.89 |
    # | Savings      | Savings Test Bank  | 456789012  | 654321098 | 1234.56 |
    # | Credit Cards | Credit Test Bank   | 789012345  | 0         | 7890.12 |
    # | Investments  | Investment Test    | 012345678  | 098765432 | 3456.78 |

    # Scenario: AC3 - Deleting an account
    #     Given I am on the "Accounts" page
    #     And I have a "Checking Account" I want to delete
    #     When I attempt to delete a "Checking Account"
    #     Then that "Checking Account" is removed

    # Scenario: AC4 - Viewing account information
    #     Given I am on the "Accounts" page
    #     When I click the "Get Report" button on Accounts
    #     Then a pop up appears of my credit score

    # Scenario: AC5 - Checking Readability of Account Bar
    #     Given I am on the "Accounts" page
    #     And my net cash bar is green
    #     When my debt exceeds my Assets
    #     Then my net cash bar turns red

