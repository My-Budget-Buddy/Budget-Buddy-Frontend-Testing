package com.skillstorm.PageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.skillstorm.WebDriverSingleton;

/**
 * This class represents the Page Object Model for the Transaction History Page.
 * It provides methods to interact with various elements on the page.
 */
public class TransactionHistoryPage {
    WebDriver driver = WebDriverSingleton.getDriver();

    // ===================== WebElement Locators =====================
    // Page title and buttons
    @FindBy(className = "usa-logo__text")
    private WebElement transactionPageTitle;

    @FindBy(id = "clearFilterBtn")
    private WebElement clearFilterBtn;

    @FindBy(id = "sortByDropdown")
    private WebElement sortByDropdown;

    @FindBy(id = "directionDropdown")
    private WebElement directionDropdown;

    @FindBy(id = "addTransactionModal")
    private WebElement addTransactionModal;

    // Modal Fields (for creating transactions)
    @FindBy(name = "vendorName")
    private WebElement vendorNameField;

    @FindBy(id = "create-transaction-account")
    private WebElement accountDropdown;

    @FindBy(id = "create-transaction-amount")
    private WebElement amountField;

    @FindBy(id = "create-transaction-category")
    private WebElement categoryDropdown;

    @FindBy(id = "addTransactionBtn")
    private WebElement submitBtn;

    // Filter Dropdowns (for filtering transactions)
    @FindBy(id = "allCategoriesDropDown")
    private WebElement allCategoriesDropDown;

    @FindBy(id = "allAccountDropDown")
    private WebElement allAccountDropDown;

    @FindBy(id = "allAmountsDropDown")
    private WebElement allAmountsDropDown;

    @FindBy(id = "allDatesDropDown")
    private WebElement allDatesDropDown;

    // Edit Transaction Elements
    @FindBy(id = "editBtn")
    private WebElement editBtn;

    @FindBy(id = "editTransactionBtn")
    private WebElement editSubmitBtn;

    @FindBy(id = "transaction-name")
    private WebElement editVendorNameField;

    @FindBy(id = "transaction-account")
    private WebElement editAccountField;

    @FindBy(id = "transaction-amount")
    private WebElement editAmountField;

    @FindBy(id = "transaction-category")
    private WebElement editCategoryField;

    @FindBy(id = "editTransactionModal")
    private WebElement triggerEditTransactionModal;

    // Delete Transaction Elements
    @FindBy(id = "deleteBtn")
    private WebElement deleteBtn;

    // Login Elements
    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "logInBtn")
    private WebElement logInBtn;

    @FindBy(xpath = "//*[@id='root']/div[1]/div/div[1]/a[5]")
    private WebElement transactionsTab;

    // Transaction history table
    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table")
    private WebElement transactionHistoryTable;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table/tbody/tr[1]")
    private WebElement transactionHistoryTableFirstRow;

    // Summary Section
    @FindBy(xpath = "//*[@id='summaryDiv']")
    private WebElement summaryDiv;

    // Bar chart in summary section
    @FindBy(xpath = "//*[@id='summaryDiv']/div")
    private WebElement barChart;

    @FindBy(xpath = "//*[@id='summaryDiv']/div/svg/g[2]/rect[1]")
    private WebElement bar;

    @FindBy(xpath = "//*[@id='summaryDiv']/span[1]")
    private WebElement spent;

    @FindBy(xpath = "//*[@id='summaryDiv']/span[2]")
    private WebElement earned;

    @FindBy(xpath = "//*[@id='summaryDiv']/span[3]")
    private WebElement sum;

    // Transaction history page button
    @FindBy(id = "btnTransactionArrow")
    private WebElement transactionHistoryBtn;

    @FindBy(id = "transactionDetailedInfoHeading")
    private WebElement transactionDetailedInfoHeading;

    private int transactionHistoryTableBeforeDeletion;

    // ===================== Constructor =====================

    /**
     * Constructor to initialize WebElement locators using PageFactory.
     */
    public TransactionHistoryPage() {
        PageFactory.initElements(driver, this);
    }

    // ===================== Helper Methods =====================

    /**
     * Wait for a specific WebElement to be visible on the page.
     * 
     * @param element - the WebElement to wait for
     * @param timeoutInSeconds - the duration to wait for visibility
     * @return the WebElement once it's visible
     */
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Pauses execution for a specified amount of time.
     * 
     * @param milliseconds - the amount of time in milliseconds to pause
     */
    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ===================== General Methods =====================

    /**
     * Get the current URL of the page.
     * 
     * @return the URL of the current page
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    /**
     * Click the "Transactions" tab.
     */
    public void clickTab() {
        waitForElement(transactionsTab, 10).click();
        pause(2000); // For waiting purposes
    }

    /**
     * Confirm the detailed transaction info page title.
     * 
     * @return the page title
     */
    public String confirmDetailedInfoTitle() {
        pause(2000);
        return transactionDetailedInfoHeading.getText();
    }

    /**
     * Verify the transaction history table is displayed.
     * 
     * @return true if the table is displayed, false otherwise
     */
    public Boolean displayTransactionHistoryTable() {
        pause(2000);
        return transactionHistoryTable.isDisplayed();
    }

    // ===================== Create Transaction Methods =====================

    /**
     * Set the account for a new transaction.
     * 
     * @param account - the account to select
     * @return the selected account
     */
    public String setAccount(String account) {
        if (account != null && !account.isEmpty()) {
            pause(2000);
            waitForElement(accountDropdown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='create-transaction-account']/option[text()='" + account + "']"));
            waitForElement(option, 10).click();
        }
        return account;
    }

    /**
     * Set the amount for a new transaction.
     * 
     * @param amount - the amount to input
     * @return the entered amount
     */
    public String setAmount(String amount) {
        pause(2000);
        waitForElement(amountField, 10).clear();
        waitForElement(amountField, 10).sendKeys(amount);
        return amount;
    }

    /**
     * Set the category for a new transaction.
     * 
     * @param category - the category to select
     * @return the selected category
     */
    public String setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            pause(2000);
            waitForElement(categoryDropdown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='create-transaction-category']/option[text()='" + category + "']"));
            pause(2000);
            waitForElement(option, 10).click();
        }
        return category;
    }

    /**
     * Confirm the creation of a transaction by retrieving the first row of the transaction table.
     * 
     * @return the text of the first row
     */
    public String confirmCreation() {
        pause(2000);
        return waitForElement(transactionHistoryTableFirstRow, 10).getText();
    }

    // ===================== Read Transaction Methods =====================

    /**
     * Verify both the transaction table and graphical summary are visible.
     * 
     * @return true if both are visible, false otherwise
     */
    public Boolean bothTableAndGraphAreVisible() {
        return transactionHistoryTable.isDisplayed() && summaryDiv.isDisplayed();
    }

    // ===================== Update Transaction Methods =====================

    /**
     * Update the account of an existing transaction.
     * 
     * @param account - the new account to set
     * @return the updated account
     */
    public String updateAccount(String account) {
        //waitForElement(editAccountField, 10).clear();
        if (account != null && !account.isEmpty()) {
            pause(2000);
            waitForElement(editAccountField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-account']/option[text()='" + account + "']"));
            waitForElement(option, 10).click();
        }
        return account;
    }

    public String updateAmount(String amount) {
        pause(2000);
        waitForElement(editAmountField, transactionHistoryTableBeforeDeletion).clear();
        waitForElement(editAmountField, 10).sendKeys(amount);
        return amount;
    }

    /**
     * Update the category of an existing transaction.
     * 
     * @param category - the new category to set
     * @return the updated category
     */
    public String updateCategory(String category) {
       // waitForElement(editCategoryField, 10).clear();
        if (category != null && !category.isEmpty()) {
            pause(2000);
            waitForElement(editCategoryField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-category']/option[text()='" + category + "']"));
            waitForElement(option, 10).click();
        }
        pause(2000);
        return category;
    }


    /**
     * Confirm the update of a transaction by retrieving the first row of the transaction table.
     * 
     * @return the text of the first row
     */
    public String confirmUpdation() {
        pause(2000);
        return waitForElement(transactionHistoryTableFirstRow, 10).getText();
    }

    // ===================== Delete Transaction Methods =====================

    /**
     * Click the trash icon to delete a transaction and store the number of rows before deletion.
     */
    public void clickTrashIcon() {
        pause(2000);
        transactionHistoryTableBeforeDeletion = transactionHistoryTable.findElements(By.tagName("tr")).size();
        waitForElement(deleteBtn, 10).click();
    }

    /**
     * Verify that the transaction has been deleted by checking if the number of rows in the table has decreased.
     * 
     * @return true if a row has been deleted, false otherwise
     */
    public Boolean transactionIsNotInList() {
        pause(2000);
        int transactionHistoryTableAfterDeletion = transactionHistoryTable.findElements(By.tagName("tr")).size();
        return transactionHistoryTableAfterDeletion < transactionHistoryTableBeforeDeletion;
    }

    // ===================== Graphical Summary Methods =====================

    /**
     * View the graphical summary including the bar chart and statistics for spending, earnings, and sum.
     * 
     * @return true if all elements of the graphical summary are displayed, false otherwise
     */
    public Boolean viewGraphicalSummary() {
        pause(2000);
        return barChart.isDisplayed() && spent.isDisplayed() && earned.isDisplayed() && sum.isDisplayed();
    }


}