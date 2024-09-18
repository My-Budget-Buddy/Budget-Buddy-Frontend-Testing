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

public class TransactionPage {

    WebDriver driver = WebDriverSingleton.getDriver();
    private static final String transactionsUrl = "http://localhost:5173/dashboard/transactions";

    // Locators for Transaction Page Elements
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

    // Modal Fields
    @FindBy(name = "vendorName")
    private WebElement vendorNameField;

    @FindBy(name = "accountId")
    private WebElement accountDropdown;

    @FindBy(name = "amount")
    private WebElement amountField;

    @FindBy(name = "category")
    private WebElement categoryDropdown;

    @FindBy(id = "addTransactionBtn")
    private WebElement submitBtn;

    // Filter Dropdowns
    @FindBy(id = "allCategoriesDropDown")
    private WebElement allCategoriesDropDown;

    @FindBy(id = "allAccountDropDown")
    private WebElement allAccountDropDown;

    @FindBy(id = "allAmountsDropDown")
    private WebElement allAmountsDropDown;

    @FindBy(id = "allDatesDropDown")
    private WebElement allDatesDropDown;

    // Transaction Table Elements
    @FindBy(id = "listOfTransactionsTitle")
    private WebElement transactionsTableTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table")
    private WebElement transactionsTable;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/tbody/tr[1]")
    private WebElement transactionsTableFirstRow;

    // Table Headers
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[1]")
    private WebElement dateHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[2]")
    private WebElement nameHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[3]")
    private WebElement categoryHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[4]")
    private WebElement actionsHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[3]/div/table/thead/tr/th[5]")
    private WebElement amountHeader;

    // Edit Transaction Elements
    @FindBy(id = "editBtn")
    private WebElement editBtn;

    @FindBy(id = "editTransactionBtn")
    private WebElement editSubmitBtn;

    @FindBy(id = "edit-transaction-vendorName")
    private WebElement editTransactionNameField;

    @FindBy(id = "edit-transaction-account")
    private WebElement editTransactionAccountField;

    @FindBy(id = "edit-transaction-amount")
    private WebElement editTransactionAmountField;

    @FindBy(id = "edit-transaction-category")
    private WebElement editTransactionCategoryField;

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

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div/div[1]/a[5]")
    private WebElement transactionsTab;

    private int tableSizeBeforeDeletion;

    // Constructor
    public TransactionPage() {
        PageFactory.initElements(driver, this);
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void logIn() {
        waitForElement(emailField, 10).sendKeys("joseph.sam@gmail.com");
        waitForElement(passwordField, 10).sendKeys("password1");
        waitForElement(logInBtn, 10).click();
    }

    public void clickTab() {
        waitForElement(transactionsTab, 10).click();
        pause(2000);
    }

    // Navigation Methods
    public void getTransactionsUrl() {
        driver.navigate().to(transactionsUrl);
    }

    // General methods
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // Create Transaction Methods
    public String setName(String name) {
        waitForElement(vendorNameField, 10).sendKeys(name);
        return name;
    }

    public String setAccount(String account) {
        if (account != null && !account.isEmpty()) {
            waitForElement(accountDropdown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
        return account;
    }

    public String setAmount(String amount) {
        waitForElement(amountField, 10).clear();
        amountField.sendKeys(amount);
        return amount;
    }

    public String setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            waitForElement(categoryDropdown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-category']/option[text()='" + category + "']"));
            option.click();
        }
        return category;
    }

    public String verifyTransactionDetails() {
        return waitForElement(transactionsTableFirstRow, 10).getText();
    }

    // Read Transaction Methods
    public Boolean printTransactionTable() {
        System.out.println(waitForElement(transactionsTable, 10).getText());
        return transactionsTable.isDisplayed();
    }

    // Update Transaction Methods

    public void updateName(String name) {
        waitForElement(editTransactionNameField, 10).clear();
        editTransactionNameField.sendKeys(name);
    }

    public void updateAccount(String account) {
        if (account != null && !account.isEmpty()) {
            waitForElement(editTransactionAccountField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
    }

    public void updateAmount(String amount) {
        waitForElement(editTransactionAmountField, 10).clear();
        editTransactionAmountField.sendKeys(amount);
    }

    public void updateCategory(String category) {
        if (category != null && !category.isEmpty()) {
            waitForElement(editTransactionCategoryField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-category']/option[text()='" + category + "']"));
            option.click();
        }
    }

    // Delete Transaction Methods
    public void clickDeleteBtn() {
        tableSizeBeforeDeletion = transactionsTable.getText().length();
        System.out.println(tableSizeBeforeDeletion + " before");
        waitForElement(deleteBtn, 10).click();
    }

    public Boolean confirmDeletion() {
        int tableSizeAfterDeletion = transactionsTable.getText().length();
        System.out.println(tableSizeAfterDeletion + " after");
        return tableSizeBeforeDeletion > tableSizeAfterDeletion;
    }

    // Category Methods

    public void selectACategory(String category) {
        if (category != null && !category.isEmpty()) {
            waitForElement(allCategoriesDropDown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='allCategoriesDropDown']/option[text()='" + category + "']"));
            option.click();
        }
    }
    public void printOutCategoryColumn() {
        List<WebElement> categoryColumnValues = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div/div[3]/div/table//tr/td[3]"));
        for (WebElement cell : categoryColumnValues) {
            System.out.println(cell.getText());
        }
    }

    /**
     * Utility method to pause execution for a specified amount of time.
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
}
