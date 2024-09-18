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

public class TransactionPage extends Page{

    WebDriver driver = WebDriverSingleton.getDriver();

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

    // Edit Transaction Elements
    @FindBy(id = "editBtn")
    private WebElement editBtn;

    @FindBy(id = "editTransactionBtn")
    private WebElement editSubmitBtn;

    private static final String editTransactionNameField = "edit-transaction-vendorName";

    @FindBy(id = "edit-transaction-account")
    private WebElement editTransactionAccountField;


    private static final String editTransactionAmountFieldId = "edit-transaction-amount";

    @FindBy(id = "edit-transaction-category")
    private WebElement editTransactionCategoryField;

    // Delete Transaction Elements
    @FindBy(id = "deleteBtn")
    private WebElement deleteBtn;

    private int tableSizeBeforeDeletion;

    // Constructor
    public TransactionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void clickTab() {
        DashboardNavbar navbar = new DashboardNavbar(driver);
        navbar.clickButton(DashboardNavbar.BTN_TRANSACTIONS_NAME);
        pause(2000);
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
            clickButton(account);
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
            clickButton(category);
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
        waitForElement(getWebElement(editTransactionNameField), 10).clear();
        getWebElement(editTransactionNameField).sendKeys(name);
    }

    public void updateAccount(String account) {
        if (account != null && !account.isEmpty()) {
            clickButton(account);
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
    }

    public void updateAmount(String amount) {
        waitForElement(getWebElement(editTransactionAmountFieldId), 10).clear();
        getWebElement(editTransactionAmountFieldId).sendKeys(amount);
    }

    public void updateCategory(String category) {
        if (category != null && !category.isEmpty()) {
            clickButton(category);
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-category']/option[text()='" + category + "']"));
            option.click();
        }
    }

    // Delete Transaction Methods
    public void clickDeleteBtn() {
        tableSizeBeforeDeletion = transactionsTable.getText().length();
        waitForElement(deleteBtn, 10).click();
    }

    public Boolean confirmDeletion() {
        int tableSizeAfterDeletion = transactionsTable.getText().length();
        return tableSizeBeforeDeletion > tableSizeAfterDeletion;
    }

    // Category Methods

    public void selectACategory(String category) {
        if (category != null && !category.isEmpty()) {
            clickButton(category);
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
