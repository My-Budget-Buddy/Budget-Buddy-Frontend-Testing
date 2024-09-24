package com.skillstorm.PageObjects;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
    private WebElement createSubmitBtn;

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

    @FindBy(id = "transaction-account")
    private WebElement editAccountField;

    @FindBy(id = "transaction-amount")
    private WebElement editAmountField;

    @FindBy(id = "transaction-category")
    private WebElement editCategoryField;

    @FindBy(id = "editTransactionBtn")
    private WebElement editSubmitBtn;

    @FindBy(id = "transaction-name")
    private WebElement editVendorNameField;

    @FindBy(id = "transactionDetailedInfoHeading")
    private WebElement transactionInfoHeading;

    @FindBy(id = "editTransactionModal")
    private WebElement triggerEditTransactionModal;


    // Transaction history table
    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table")
    private WebElement transactionHistoryTable;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div/div[3]/div[1]/table/tbody/tr[1]")
    private WebElement transactionHistoryTableFirstRow;

    @FindBy(id = "btnTransactionArrow")
    private WebElement transactionHistoryBtn;


    // Bar chart in summary section
    @FindBy(id = "summaryDiv")
    private WebElement summaryDiv;

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

    @FindBy(id = "deleteBtn")
    private WebElement deleteBtn;


    private int transactionHistoryTableBeforeDeletion;
    
    //child component
    DashboardNavbar navbar;

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
     * Confirm the detailed transaction info page title.
     * 
     * @return the page title
     */
    public String confirmDetailedInfoTitle() {
        return waitForElement(transactionInfoHeading, 1).getText();
    }

    /**
     * Verify the transaction history table is displayed.
     * 
     * @return true if the table is displayed, false otherwise
     */
    public Boolean displayTransactionHistoryTable() {
        return waitForElement(transactionHistoryTable,1).isDisplayed();
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
        WebElement createAmountField = waitForElement(amountField, 10);
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
            waitForElement(categoryDropdown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='create-transaction-category']/option[text()='" + category + "']"));
            waitForElement(option, 10).click();
            pause(1000);
        }
        return category;
    }

    // public void clickSubmitBtn() {
    //     WebElement submitBtn = waitForElement(createSubmitBtn, 10);
    //     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
    //     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    //     //submitBtn.click();
    // }

    /**
     * Confirm the creation of a transaction by retrieving the first row of the transaction table.
     * 
     * @return the text of the first row
     */
    public Boolean confirmCreation() {
        pause(1500);
        List<WebElement> rows = transactionHistoryTable.findElements(By.tagName("tr"));
        int rowCounter = rows.size();

        if (rowCounter < 1){
            throw new NoSuchElementException("No transactions found.");
        }

        for (int i = 1; i < rowCounter; i++) {
            WebElement row = rows.get(i);
            String rowText = waitForElement(row, 10).getText();
            if(!rowText.isEmpty() && rowText.contains("Transportation") && rowText.contains("$300.00")) {
                return true;
            }
        }
        
        // If no valid information found in the first or second row
        throw new NoSuchElementException("No valid transaction found in any rows.");
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

    public void clickEditBtn() {
        waitForElement(editBtn, 30).click();
    }

    /**
     * Update the account of an existing transaction.
     * 
     * @param account - the new account to set
     * @return the updated account
     */
    public String updateAccount(String account) {
        if (account != null && !account.isEmpty()) {
            waitForElement(editAccountField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-account']/option[text()='" + account + "']"));
            waitForElement(option, 10).click();
        }
        return account;
    }

    public String updateAmount(String amount) {
        WebElement editAmountDropdown = waitForElement(editAmountField, 10);
        editAmountDropdown.clear();
        editAmountDropdown.sendKeys(amount);
        return amount;
    }

    /**
     * Update the category of an existing transaction.
     * 
     * @param category - the new category to set
     * @return the updated category
     */
    public String updateCategory(String category) {
        pause(500);
        if (category != null && !category.isEmpty()) {
            waitForElement(editCategoryField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-category']/option[text()='" + category + "']"));
            waitForElement(option, 10).click();
            //editCategoryField.sendKeys(Keys.ENTER);
        }
        return category;
    }

    public void clickEditSubmitBtn() {
        WebElement editBtn = waitForElement(editSubmitBtn, 10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);
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
        transactionHistoryTableBeforeDeletion = waitForElement(transactionHistoryTable, 10).findElements(By.tagName("tr")).size();
        if (transactionHistoryTableBeforeDeletion == 0){
            throw new NoSuchElementException("No transactions found.");
        }
        waitForElement(deleteBtn, 10).click();
    }

    /**
     * Verify that the transaction has been deleted by checking if the number of rows in the table has decreased.
     * 
     * @return true if a row has been deleted, false otherwise
     */
    public Boolean transactionIsNotInList() {
        int transactionHistoryTableAfterDeletion = waitForElement(transactionHistoryTable, 10).findElements(By.tagName("tr")).size();
        return transactionHistoryTableAfterDeletion < transactionHistoryTableBeforeDeletion;
    }

    // ===================== Graphical Summary Methods =====================

    /**
     * View the graphical summary including the bar chart and statistics for spending, earnings, and sum.
     * 
     * @return true if all elements of the graphical summary are displayed, false otherwise
     */
    public Boolean viewGraphicalSummary() {
        Boolean barChartDisplayed = waitForElement(barChart, 10).isDisplayed();
        Boolean spentDisplayed = waitForElement(spent, 10).isDisplayed();
        Boolean earnedDisplayed = waitForElement(earned, 10).isDisplayed();
        Boolean sumDisplayed = waitForElement(sum, 10).isDisplayed();
        return barChartDisplayed && spentDisplayed && earnedDisplayed && sumDisplayed;
    }

    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(navbar);
    }

    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case "navbar":
                return navbar;
            default:
                return null;
        }
    }

    @Override
    public List<WebElement> getWebElements() {
        ArrayList<WebElement> webElements = new ArrayList<WebElement>();
        webElements.add(transactionHistoryBtn);
        
        for (Component component : getChildComponents()) {
            webElements.addAll(component.getWebElements());
        }

        return webElements;
    }


    @Override
    public WebElement getWebElement(String name) {
        for (Component component : getChildComponents()) {
            WebElement webElementlement = component.getWebElement(name);
            if (webElementlement != null) {
                return webElementlement;
            }
        }
        return null;
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(transactionHistoryBtn);
    }

    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        if(button != null) {
            button.click();
        } else {
            throw new NoSuchElementException("No button found with name: " + name);
        }
    }


}