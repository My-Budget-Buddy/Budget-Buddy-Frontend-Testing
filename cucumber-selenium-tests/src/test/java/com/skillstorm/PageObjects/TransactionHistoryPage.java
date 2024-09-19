package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.Components.Navbar.DashboardNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;

/**
 * This class represents the Page Object Model for the Transaction History Page.
 * It provides methods to interact with various elements on the page.
 */
public class TransactionHistoryPage  extends Page{
    WebDriver driver = WebDriverSingleton.getDriver();

    //static final strings for IDs
    private static final String accountDropdownId = "create-transaction-account";

    private static final String amountFieldId = "create-transaction-amount";

    private static final String categoryDropdownId = "create-transaction-category";

    private static final String editAccountFieldId = "transaction-account";

    private static final String editAmountFieldId = "transaction-amount";

    private static final String editCategoryFieldId = "transaction-category";

    private static final String transactionDetailedInfoHeadingId = "transactionDetailedInfoHeading";

    private static final String summaryDivId = "summaryDiv";

    private static final String deleteBtn = "deleteBtn";

    

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


    @FindBy(id = "editTransactionModal")
    private WebElement triggerEditTransactionModal;


    // Transaction history table
    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table")
    private WebElement transactionHistoryTable;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table/tbody/tr[1]")
    private WebElement transactionHistoryTableFirstRow;



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


    private int transactionHistoryTableBeforeDeletion;

    // ===================== Constructor =====================

    /**
     * Constructor to initialize WebElement locators using PageFactory.
     */
    public TransactionHistoryPage(WebDriver driver) {
        super(driver);
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
        pause(1000);
        DashboardNavbar navbar = new DashboardNavbar(driver);
        navbar.clickButton(DashboardNavbar.BTN_TRANSACTIONS_NAME);
        pause(500);
    }

    /**
     * Confirm the detailed transaction info page title.
     * 
     * @return the page title
     */
    public String confirmDetailedInfoTitle() {
        pause(500);
        return getWebElement(transactionDetailedInfoHeadingId).getText();
    }

    /**
     * Verify the transaction history table is displayed.
     * 
     * @return true if the table is displayed, false otherwise
     */
    public Boolean displayTransactionHistoryTable() {
        pause(500);
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
            pause(500);
            clickButton(accountDropdownId);
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
        pause(500);
        WebElement createAmountField = waitForElement(getWebElement(amountFieldId), 10);
        createAmountField.clear();
        createAmountField.sendKeys(amount);
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
            pause(500);
            clickButton(categoryDropdownId);
            WebElement option = driver.findElement(By.xpath("//*[@id='create-transaction-category']/option[text()='" + category + "']"));
            pause(500);
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
        pause(500);
        List<WebElement> rows = transactionHistoryTable.findElements(By.tagName("tr"));
        int rowCounter = rows.size();

        if(rowCounter >= 2) {
            WebElement secondToLastRow = rows.get(rowCounter - 2);
            return waitForElement(secondToLastRow, 10).getText();
        }
        return waitForElement(transactionHistoryTableFirstRow, 10).getText();
    }

    // ===================== Read Transaction Methods =====================

    /**
     * Verify both the transaction table and graphical summary are visible.
     * 
     * @return true if both are visible, false otherwise
     */
    public Boolean bothTableAndGraphAreVisible() {
        return transactionHistoryTable.isDisplayed() && getWebElement(summaryDivId).isDisplayed();
    }

    // ===================== Update Transaction Methods =====================

    public void clickEditBtn() {
        pause(1500);
        waitForElement(getWebElement("editBtn"), 10).click();
    }

    /**
     * Update the account of an existing transaction.
     * 
     * @param account - the new account to set
     * @return the updated account
     */
    public String updateAccount(String account) {
        //waitForElement(editAccountField, 10).clear();
        if (account != null && !account.isEmpty()) {
            pause(500);
            clickButton(editAccountFieldId);
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-account']/option[text()='" + account + "']"));
            waitForElement(option, 10).click();
        }
        return account;
    }

    public String updateAmount(String amount) {
        pause(500);
        WebElement editAmountField = waitForElement(getWebElement(editAmountFieldId), 10);
        editAmountField.clear();
        editAmountField.sendKeys(amount);
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
            pause(500);
            clickButton(editCategoryFieldId);
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-category']/option[text()='" + category + "']"));
            waitForElement(option, 10).click();
        }
        pause(500);
        return category;
    }


    /**
     * Confirm the update of a transaction by retrieving the first row of the transaction table.
     * 
     * @return the text of the first row
     */
    public String confirmUpdation() {
        pause(500);
        return waitForElement(transactionHistoryTableFirstRow, 10).getText();
    }

    // ===================== Delete Transaction Methods =====================

    /**
     * Click the trash icon to delete a transaction and store the number of rows before deletion.
     */
    public void clickTrashIcon() {
        pause(500);
        transactionHistoryTableBeforeDeletion = transactionHistoryTable.findElements(By.tagName("tr")).size();
        waitForElement(getWebElement(deleteBtn), 10).click();
    }

    /**
     * Verify that the transaction has been deleted by checking if the number of rows in the table has decreased.
     * 
     * @return true if a row has been deleted, false otherwise
     */
    public Boolean transactionIsNotInList() {
        pause(500);
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
        pause(500);
        return barChart.isDisplayed() && spent.isDisplayed() && earned.isDisplayed() && sum.isDisplayed();
    }

    @Override
    public List<Component> getChildComponents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChildComponents'");
    }

    @Override
    public Component getChildComponent(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChildComponent'");
    }

    @Override
    public List<WebElement> getWebElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWebElements'");
    }

    @Override
    public WebElement getWebElement(String id) {
        return driver.findElement(By.id(id));
    }

    @Override
    public List<WebElement> getButtons() {
        return driver.findElements(By.tagName("select"));
    }

    @Override
    public void clickButton(String name) {
        for (WebElement selectElement : getButtons()) {
            if (selectElement.getText().equalsIgnoreCase(name)) {
                selectElement.click();
                break;
            }
        }
    }


}