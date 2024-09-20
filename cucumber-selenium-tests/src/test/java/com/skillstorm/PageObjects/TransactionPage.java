package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    private int tableSizeBeforeDeletion;

    //child component
    DashboardNavbar navbar;

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

    /**
     * Helper method to pause execution for a specified amount of time.
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


    public void clickTab() {
        pause(500);
        navbar = new DashboardNavbar(driver);
        navbar.clickButton(DashboardNavbar.BTN_TRANSACTIONS_NAME);
        pause(500);
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
        waitForElement(amountField, 10).sendKeys(amount);
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
        pause(1000);
        return waitForElement(transactionsTableFirstRow, 10).getText();
    }

    // Read Transaction Methods
    public Boolean printTransactionTable() {
        pause(500);
        return transactionsTable.isDisplayed();
    }


    // Update Transaction Methods

    public void clickEditBtn() {
        waitForElement(editBtn, 10).click();
    }

    public String updateName(String name) {
        waitForElement(editTransactionNameField, 10).clear();
        waitForElement(editTransactionNameField, 10).sendKeys(name);
        return name;
    }

    public String updateAccount(String account) {
        if (account != null && !account.isEmpty()) {
            waitForElement(editTransactionAccountField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-account']/option[text()='" + account + "']"));
            option.click();
        }
        return account;
    }

    public String updateAmount(String amount) {
        waitForElement(editTransactionAmountField, 10).clear();
        waitForElement(editTransactionAmountField, 10).sendKeys(amount);

        return amount;
    }

    public String updateCategory(String category) {
        if (category != null && !category.isEmpty()) {
            waitForElement(editTransactionCategoryField, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='edit-transaction-category']/option[text()='" + category + "']"));
            option.click();
        }

        return category;
    }

        



    // Delete Transaction Methods
    public void clickDeleteBtn() {
        pause(2000);
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
            waitForElement(allCategoriesDropDown, 10).click();
            WebElement option = driver.findElement(By.xpath("//*[@id='allCategoriesDropDown']/option[text()='" + category + "']"));
            option.click();
        }
    }
    // Method to print and return category column values
    public List<String> getCategoryColumnValues() {
        List<WebElement> categoryColumnValues = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div/div[3]/div/table//tr/td[3]"));
        List<String> categoryValues = new ArrayList<>();
        for (WebElement cell : categoryColumnValues) {
            categoryValues.add(cell.getText());
        }
        return categoryValues;
    }

    // Method to assert selected category matches the category in the table
    public void assertCategorySelection(String expectedCategory) {
        List<String> categoryValues = getCategoryColumnValues();
        for (String value : categoryValues) {
            Assert.assertEquals(value, expectedCategory);
        }
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
        return driver.findElements(By.tagName("button"));
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
