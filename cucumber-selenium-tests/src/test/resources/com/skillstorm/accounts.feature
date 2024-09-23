Feature:

    Scenario: AC1 - looking at drop down options when logged in
        Given I have multiple "<objects>"  
        And I am on the "Accounts" page
        When I click on the "<option>" option on Accounts
        Then I can see the "<list>" on Accounts

        Examples:
        | objects           | option        | list             |
        | Checking Accounts | Checkings     | Checkings List   |
        | Savings Accounts  | Savings       | Savings List     |
        | Credit Cards      | Credit Cards  | Credit Card List |
        | Investments       | Investments   | Investments List |

    Scenario: AC2 - Creating a new account
        Given I am on the "Accounts" page
        And I have a "<accountType>" account with the information: "<name>", "<accountNum>", "<routing>", "<balance>"
        Then that specific account is "Created"

        Examples:
        | accountType  | name                        | accountNum | routing   | balance |
        | Checkings    | Checking Test Bank - Create | 123456789  | 987654321 | 4567.89 |
        | Savings      | Savings Test Bank - Create  | 456789012  | 654321098 | 1234.56 |
        | Credit Cards | Credit Test Bank - Create   | 789012345  | 0         | 7890.12 |
        | Investments  | Investment Test - Create    | 012345678  | 098765432 | 3456.78 |

    Scenario: AC3 - Deleting an account
        Given I am on the "Accounts" page
        And I have a "<accountType>" account with the information: "<name>", "<accountNum>", "<routing>", "<balance>"
        When I attempt to delete the account
        Then that specific account is "Deleted"

        Examples:
        | accountType  | name                          | accountNum | routing      | balance   |
        | Checkings    | Checking Test Bank - Delete | 738276593  | 73628191923  | 56382.12  |
        | Savings      | Savings Test Bank - Delete  | 456789012  | 65432109876  | 123456.78 |
        | Credit Cards | Credit Test Bank - Delete   | 785739242  | 0            | 4867.12   |
        | Investments  | Investment Test - Delete    | 012345678  | 98765432109  | 234567.89 |

    Scenario: AC4 - Viewing account information
        Given I am on the "Accounts" page
        When I click the "Get Report" button on Accounts
        Then a pop up appears of my credit score

    Scenario: AC5 - Checking Readability of Account Bar
        Given I am on the "Accounts" page
        And my net cash bar is green
        When my debt exceeds my Assets
        Then my net cash bar turns red

