package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionPage {
    
    WebDriver driver;
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

    private int tableSizeBeforeDeletion = 0;

    // Constructor
    public TransactionPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    // Quit driver
    public void quit() {
        this.driver.quit();
    }

    // Login Methods
    public void loginPage() {
        this.driver.get("http://localhost:5173/login");
    }

    public void logIn() {
        emailField.sendKeys("joseph.sam@gmail.com");
        passwordField.sendKeys("password1");
        logInBtn.click();
    }

    public void clickTab() {
        transactionsTab.click();
    }

    // Navigation Methods
    public void getTransactionsUrl() {
        this.driver.navigate().to(transactionsUrl);
    }

    //General methods
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    // Create Transaction Methods
    public void triggerAddTransactionModal() {
        addTransactionModal.click();
    }

    public String setName(String name) {
        vendorNameField.sendKeys(name);
        return name;
    }

    public String setAccount(String account) {
        if (account != null && !account.isEmpty()) {
            accountDropdown.click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
        return account;
    }

    public String setAmount(String amount) {
        amountField.clear();
        amountField.sendKeys(amount);
        return amount;
    }

    public String setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            accountDropdown.click();
            WebElement option = driver.findElement(By.xpath("//*[@id='transaction-category']/option[text()='" + category + "']"));
            option.click();
        }
        return category;
    }

    public String verifyTransactionDetails() {
        return transactionsTableFirstRow.getText();
    }

    // Read Transaction Methods
    public Boolean printTransactionTable() {
        System.out.println(transactionsTable.getText());
        return transactionsTable.isDisplayed();
    }

    // Update Transaction Methods
    public void clickEditIcon() {
        editBtn.click();
    }

    public void updateName(String name) {
        editTransactionNameField.clear();
        editTransactionNameField.sendKeys(name);
    }

    public void updateAccount(String account) {
        if (account != null && !account.isEmpty()) {
            editTransactionAccountField.click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
    }

    public void updateAmount(String amount) {
        editTransactionAmountField.clear();
        editTransactionAmountField.sendKeys(amount);
    }

    public void updateCategory(String category) {
        if (category != null && !category.isEmpty()) {
            editTransactionCategoryField.click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-category']/option[text()='" + category + "']"));
            option.click();
        }
    }

    public void clickEditSubmitBtn() {
        editSubmitBtn.click();
    }

    // Delete Transaction Methods
    public void clickDeleteBtn() {
        tableSizeBeforeDeletion = transactionsTable.getText().length();
        deleteBtn.click();
    }

    public Boolean confirmDeletion() {
        int tableSizeAfterDeletion = transactionsTable.getText().length();
        return tableSizeBeforeDeletion > tableSizeAfterDeletion;
    }

    // Category Methods
    public void clickAllCategories() {
        allCategoriesDropDown.click();
    }

    public void selectACategory(String category) {
        if (category != null && !category.isEmpty()) {
            accountDropdown.click();
            WebElement option = driver.findElement(By.xpath("//*[@id='allCategoriesDropDown']/option[text()='" + category + "']"));
            option.click();
        }
    }

    public void printOutCategoryColumn() {
        List<WebElement> categoryColumnValues = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div/div[3]/div/table//tr/td[2]"));
        for (WebElement cell : categoryColumnValues) {
            System.out.println(cell.getText());
        }
    }
}