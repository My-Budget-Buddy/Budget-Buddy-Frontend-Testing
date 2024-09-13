package com.skillstorm.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionPage {
    //get all locators

    //finding transactions page title
    @FindBy(className = "usa-logo__text")
    private WebElement transactionPageTitle;

    //finding clear filter button
    @FindBy(id = "clearFilterBtn")
    private WebElement clearFilterBtn;

    //finding sort by date/amount dropdown
    @FindBy(id = "sortByDropdown")
    private WebElement sortByDropdown;

    //finding descending/ascending dropdown
    @FindBy(id = "directionDropdown")
    private WebElement directionDropdown;

    /*
     * finding add transaction button, which actually just triggers a modal
    */ 
    @FindBy(id = "addTransactionModal")
    private WebElement addTransactionModal;

    //finding submit button
    @FindBy(id = "addTransactionBtn")
    private WebElement submitBtn;

    //finding all categories dropdown
    @FindBy(id = "allCategoriesDropDown")
    private WebElement allCategoriesDropDown;

    //finding all accounts dropdown
    @FindBy(id = "allAccountDropDown")
    private WebElement allAccountDropDown;

    //finding all amounts dropdown
    @FindBy(id = "allAmountsDropDown")
    private WebElement allAmountsDropDown;

    //finding all dates dropdown
    @FindBy(id = "allDatesDropDown")
    private WebElement allDatesDropDown;

    //finding transaction table title
    @FindBy(id = "listOfTransactionsTitle")
    private WebElement transactionsTableTitle;

    //finding transactions table
    @FindBy(className = "usa-table usa-table--borderless width full")
    private WebElement transactionsTable;

    
    //finding date header
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[1]")
    private WebElement dateHeader;

    //finding name header
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[2]")
    private WebElement nameHeader;

    //finding category header
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[3]")
    private WebElement categoryHeader;

    //finding actions header
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[4]")
    private WebElement actionsHeader;

    //finding amount header
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[5]")
    private WebElement amountHeader;

    //finding edit icon
    @FindBy(id = "editBtn")
    private WebElement editBtn;

    //finding delete icon
    @FindBy(id = "deleteBtn")
    private WebElement deleteBtn;

}
