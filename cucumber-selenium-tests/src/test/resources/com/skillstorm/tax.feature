Feature: update tax estimation form

  Scenario Outline: view existing tax estimation form
    Given I am on the "<page>" page
    Then I should see the existing tax estimation form

    Examples:
      | page |
      | Tax  |

  Scenario Outline: Successfully update tax estimation form
    Given I am on the "<page>" page
    When I click the "<button>" button
    And i enter the following personal information "<firstName>" "<lastName>" "<streetAddress>" "<city>" "<state>" "<zip>" "<DOB>" "<SSN>"
    And I click the "<button2>" button followed by the "<button3>" button
    And I click the "<button4>" button followed by the "<button5>" button
    And I enter the following W2 information "<state2>" "<employer>" "<wages>" "<federalTax>" "<stateTax>" "<SSNTax>" "<medicareTax>"
    And I click the "<button6>" button followed by the "<button7>" button
    And I enter valid other income information such as "<longTermCapitalGains>" "<shortTermCapitalGains>" "<netBusinessIncome>" "<otherInvestments>" "<additionalIncome>"
    And I click the "<button8>" button followed by the "<button9>" button
    And I click the "<button10>" button followed by the "<button11>" button
    And I enter valid deductions information such as "<deductionType>" "<deductionAmount>"
    And I click the "<button12>" button followed by the "<button13>" button
    And I review and and click the "<button14>" button
    Then I should see the updated tax estimation

    Examples:
      | page | button      | firstName | lastName | streetAddress | city  | state | zip   | DOB        | SSN         | button2      | button3    | button4     | button5      | state2 | employer | wages | federalTax | stateTax | SSNTax | medicareTax | button6      | button7    | longTermCapitalGains | shortTermCapitalGains | netBusinessIncome | otherInvestments | additionalIncome | button8      | button9    | button10        | button11         | deductionType | deductionAmount | button12         | button13   | button14           |
      | Tax  | edit-button | John      | Doe      |  1234 Main St | Tampa | F     | 33647 | 01/01/1990 | 123-45-6789 | PISaveButton | nextButton | w2AddButton | w2EditButton | FL     | Publix   |  4000 |      15000 |    15000 |   5000 |        1000 | w2SaveButton | nextButton |                   10 |                     5 |                10 |                0 |                5 | OISaveButton | nextButton | deductAddButton | deductEditButton | H             |            1000 | deductSaveButton | nextButton | reviewSubmitButton |

  Scenario Outline: Successfully delete tax estimation form
    Given I am on the "<page>" page
    When I click the "<button>" button
    Then I should see the tax estimation form deleted

    Examples:
      | page | button        |
      | Tax  | delete-button |
