@transaction
Feature: Features on the transactions page

#-----------------------------CREATE---------------------------#

    Scenario Outline:
        Given I am on the "Transactions" page
        When I click the "Add Transactions" button
        And I fill in the "<name>", "<account>", "<amount>", and "<category>"
        And I click the "Submit" button
        Then I can see the new transaction in my list
    Examples:
        |name|account|amount|category|
        |Crunchyroll| 1 | 10.99 |Entertainment|

#-----------------------------READ---------------------------#
    
    Scenario:
        Given I am on the "Transactions" page
        Then I can see the list of all my transactions

#-----------------------------UPDATE---------------------------#

    Scenario Outline:
        Given I am on the "Transactions" page
        When I click the "Pencil Icon" button
        And I fill in the "<name>", "<account>", "<amount>", and "<category>"
        And I click the "Submit" button
        Then I can see the updated transaction in my list
    Examples:
        |name|account|amount|category|
        |Bojangles| 2 | 10.99 |Dining|

#-----------------------------DELETE---------------------------#
    
    Scenario:
        Given I am on the “Transactions” page
        When I click the “Trash Icon” button
        Then the transaction is not in the list

#-----------------------------CATEGORY---------------------------#
    Scenario:
        Given I am on the “Transactions” page
        When I click the “All Categories” button
        And I click a "<category>" to filter based on
        Then only transactions with that category should be visible
    Examples:
        |category|
        |Groceries|
        |Entertainment|
        |Dining|
        |Transportation|
        |Healthcare|
        |Living Expenses|
        |Shopping|
        |Income|
        |Misc|


    


