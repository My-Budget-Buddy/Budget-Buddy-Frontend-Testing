package com.skillstorm.StepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.BudgetsPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.Utilities.Navigator;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDBudgets {
    WebDriver driver;
    WebDriverWait wait;
    BudgetsPage page;
    Navigator navigator;
    User user;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        page = new BudgetsPage(driver);
        navigator = new Navigator(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Given("there is a {string} budget of {string} in the budget list")
    public void thereIsABudgetInTheBudgetList(String budgetCategory, String budgetedAmount) {
        // Check if the budget category is in the budget list
        WebElement budgetTable = page.getWebElement("budgetsTable");
        wait.until(ExpectedConditions.visibilityOf(budgetTable));

        boolean isBudgetPresent = wait.until(ExpectedConditions
                .textToBePresentInElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/main/table[1]"), budgetCategory));
        page.setGroceryActionButtons(budgetCategory, budgetedAmount);
        Assert.assertTrue(isBudgetPresent,
                "The budget category " + budgetCategory + " is not present in the budget list.");
    }

    @Given("there is a {string} savings bucket of {string} in the savings bucket table")
    public void thereIsASavingsBucketInTheSavingsBucketTable(String bucketName, String amountRequired) {
        // Check if the savings bucket is in the savings bucket table
        WebElement savingsBucketTable = page.getWebElement("savingsBucketTable");
        wait.until(ExpectedConditions.visibilityOf(savingsBucketTable));

        boolean isSavingsBucketPresent = wait.until(ExpectedConditions
                .textToBePresentInElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/main/table[2]"), bucketName));
        page.setSavingsBucketActionButtons(bucketName, amountRequired);
        Assert.assertTrue(isSavingsBucketPresent,
                "The savings bucket " + bucketName + " is not present in the savings bucket table.");
    }

    @When("I enter {string} for the budget amount")
    public void iEnterForTheBudgetAmount(String amount) {
        page.getWebElement("budgetedInput").sendKeys(amount);
    }

    @When("I click the Edit Budget button")
    public void iClickTheEditBudgetButton() {
        //page.getWebElement("editBudgetButton").click();
        page.clickButton("editBudgetButton");
        page.setEditBudgetModalElements();
    }

    @When("I click the Delete Budget button")
    public void iClickTheDeleteBudgetButton() {
        //page.getWebElement("deleteBudgetButton").click();
        page.clickButton("deleteBudgetButton");
    }

    @When("I enter {string} for new the budget amount")
    public void iEnterForTheNewBudgetAmount(String amount) {
        WebElement inputField = page.getWebElement("editedBudgetedInput");
        inputField.clear();
        inputField.sendKeys(amount);
    }

    @When("I save the new budget amount")
    public void iSaveTheNewBudgetAmount() {
        //page.getWebElement("saveBudgetButton").click();
        page.clickButton("saveBudgetButton");
    }

    @When("I click Delete on the delete budget confirmation dialog")
    public void iClickDeleteOnTheConfirmationDialog() {
        page.setDeleteButtonForGroceriesBudget();
        //page.getWebElement("confirmDeleteButton").click();
        page.clickButton("confirmDeleteButton");
    }

    @When("I enter {string} for the Budget Name")
    public void iEnterForTheBudgetName(String name) {
        WebElement inputField = page.getWebElement("budgetNameInput");
        inputField.clear();
        inputField.sendKeys(name);
    }

    @When("I enter {string} for the Required Amount")
    public void iEnterForTheRequiredAmount(String amount) {
        WebElement inputField = page.getWebElement("amountRequiredInput");
        inputField.clear();
        inputField.sendKeys(amount);
    }

    @When("I click the Edit Savings Bucket button")
    public void iClickTheEditSavingsBucketButton() {
        //page.getWebElement("editSavingsBucketButton").click();
        page.clickButton("editSavingsBucketButton");
        page.setEditSavingsBucketModalElements();
    }

    @When("I enter {string} for the new Required Amount")
    public void iEnterForTheNewRequiredAmount(String amount) {
        WebElement inputField = page.getWebElement("editedAmountRequiredInput");
        inputField.clear();
        inputField.sendKeys(amount);
    }

    @When("I save the new Required Amount")
    public void iSaveTheNewRequiredAmount() {
        //page.getWebElement("saveSavingsBucketButton").click();
        page.clickButton("saveSavingsBucketButton");
    }

    @When("I click the Delete Savings Bucket button")
    public void iClickTheDeleteSavingsBucketButton() {
        //page.getWebElement("deleteSavingsBucketButton").click();
        page.clickButton("deleteSavingsBucketButton");
    }

    @When("I click Delete on the delete savings bucket confirmation dialog")
    public void iClickDeleteOnTheDeleteSavingsBucketConfirmationDialog() {
        page.setDeleteButtonForHouseRepairsSavingsBucket();
        //page.getWebElement("confirmDeleteSavingsBucketButton").click();
        page.clickButton("confirmDeleteSavingsBucketButton");
    }

    @When("I enter {string} for the new Spending Budget")
    public void iEnterForTheNewSpendingBudget(String amount) {
        WebElement inputField = page.getWebElement("totalBudgetAmountInput");
        inputField.clear();
        inputField.sendKeys(amount);
    }

    @Then("I can see the Budgets page web elements")
    public void iCanSeeThePageWebElements() {
        WebElement summaryComponentDiv = page.getWebElement("summaryComponentDiv");
        WebElement addNewBudgetButton = page.getWebElement("addNewBudgetButton");
        WebElement budgetsTable = page.getWebElement("budgetsTable");
        WebElement savingsBucketTable = page.getWebElement("savingsBucketTable");

        wait.until(ExpectedConditions.visibilityOf(summaryComponentDiv));
        wait.until(ExpectedConditions.visibilityOf(addNewBudgetButton));
        wait.until(ExpectedConditions.visibilityOf(budgetsTable));
        wait.until(ExpectedConditions.visibilityOf(savingsBucketTable));

        Assert.assertTrue(summaryComponentDiv.isDisplayed(), "Summary Component is not visible");
        Assert.assertTrue(addNewBudgetButton.isDisplayed(), "Add New Budget Button is not visible");
        Assert.assertTrue(budgetsTable.isDisplayed(), "Budgets Table is not visible");
        Assert.assertTrue(savingsBucketTable.isDisplayed(), "Savings Bucket Table is not visible");
    }

    @Then("I can see a {string} budget of {string} in the budget list")
    public void iCanSeeABudgetInTheBudgetListWithTheNewInformation(String budgetCategory, String budgetedAmount) {
        WebElement budgetTable = page.getWebElement("budgetsTable");
        wait.until(ExpectedConditions.visibilityOf(budgetTable));

        List<WebElement> rows = budgetTable.findElements(By.tagName("tr"));
        boolean found = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String category = cells.get(0).getText();
                String amount = cells.get(1).getText();
                if (budgetCategory.equals(category) && budgetedAmount.equals(amount)) {
                    found = true;
                    break;
                }
            }
        }

        Assert.assertTrue(found,
                "The budget table does not contain a row with" + budgetCategory + " and " + budgetedAmount);
    }

    @Then("I cannot see a {string} budget in the budget list")
    public void iCannotSeeABudgetInTheBudgetList(String budgetCategory) {
        WebElement budgetTable = page.getWebElement("budgetsTable");
        wait.until(ExpectedConditions.visibilityOf(budgetTable));

        List<WebElement> rows = budgetTable.findElements(By.tagName("tr"));
        boolean found = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String category = cells.get(0).getText();
                if (budgetCategory.equals(category)) {
                    found = true;
                    break;
                }
            }
        }

        Assert.assertFalse(found, "The budget table contains a row with " + budgetCategory);
    }

    @Then("I can see a {string} savings bucket of {string} in the savings bucket table")
    public void thenICanSeeASavingsBucketInTheSavingsBucketTable(String budgetName, String amountRequired) {
        WebElement savingsBucketTable = page.getWebElement("savingsBucketTable");
        wait.until(ExpectedConditions.visibilityOf(savingsBucketTable));

        List<WebElement> rows = savingsBucketTable.findElements(By.tagName("tr"));
        boolean found = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String name = cells.get(0).getText();
                String required = cells.get(1).getText();
                if (budgetName.equals(name) && amountRequired.equals(required)) {
                    found = true;
                    break;
                }
            }
        }

        Assert.assertTrue(found,
                "The savings bucket table does not contain a row with" + budgetName + " and " + amountRequired);
    }

    @Then("I cannot see a {string} savings bucket in the savings bucket table")
    public void iCannotSeeASavingsBucketInTheSavingsBucketTable(String bucketName) {
        WebElement savingsBucketTable = page.getWebElement("savingsBucketTable");
        wait.until(ExpectedConditions.visibilityOf(savingsBucketTable));

        List<WebElement> rows = savingsBucketTable.findElements(By.tagName("tr"));
        boolean found = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String name = cells.get(0).getText();
                if (bucketName.equals(name)) {
                    found = true;
                    break;
                }
            }
        }

        Assert.assertFalse(found, "The savings bucket table contains a row with " + bucketName);
    }

    @Then("I can see a Spending Budget of {string}")
    public void iCanSeeASpendingBudgetOf(String amount) {
        WebElement spendingBudgetDiv = page.getWebElement("spendingBudgetDiv");
        wait.until(ExpectedConditions.visibilityOf(spendingBudgetDiv));
        boolean budgetUpdated = wait.until(ExpectedConditions.textToBePresentInElement(spendingBudgetDiv, amount));
        Assert.assertTrue(budgetUpdated, "The spending budget is not updated to " + amount + ". Instead, it is "
                + spendingBudgetDiv.getText());
    }

}