package com.skillstorm.PageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.Component;

/**
 * The BudgetsPage class represents the page object model for the Budgets page in the Budget Buddy application.
 * It extends the Page class and provides methods to interact with various elements on the Budgets page.
 * 
 * Components:
 * - summaryComponentDiv: The summary component div element.
 * - addNewBudgetButton: The button to add a new budget.
 * - budgetedInput: The input field for the total budget amount.
 * - budgetsTable: The table displaying the budgets.
 * - savingsBucketTable: The table displaying the savings buckets.
 * - budgetNameInput: The input field for the budget name.
 * - amountRequiredInput: The input field for the amount required.
 * - submitNewSavingsBucketButton: The button to submit a new savings bucket.
 * - editSpendingBudgetButton: The button to edit the spending budget.
 * - totalBudgetAmountInput: The input field for the total budget amount.
 * - submitSpendingBudgetButton: The button to submit the spending budget.
 * - spendingBudgetDiv: The div element for the spending budget.
 * - navbar: The navigation bar component.
 * 
 * Edit and Delete Budget Modal Elements:
 * - editBudgetButton: The button to edit a budget.
 * - deleteBudgetButton: The button to delete a budget.
 * - editedBudgetedInput: The input field for the edited budget amount.
 * - saveBudgetButton: The button to save the edited budget.
 * - confirmDeleteButton: The button to confirm the deletion of a budget.
 * 
 * Edit and Delete Savings Bucket Modal Elements:
 * - editSavingsBucketButton: The button to edit a savings bucket.
 * - deleteSavingsBucketButton: The button to delete a savings bucket.
 * - editedAmountRequiredInput: The input field for the edited amount required.
 * - saveSavingsBucketButton: The button to save the edited savings bucket.
 * - confirmDeleteSavingsBucketButton: The button to confirm the deletion of a savings bucket.
 * 
 * Methods:
 * - BudgetsPage(WebDriver driver): Constructor to initialize the BudgetsPage with the given WebDriver.
 * - getChildComponents(): Returns a list of child components.
 * - getChildComponent(String name): Returns a child component by name.
 * - getWebElements(): Returns a list of web elements.
 * - getWebElement(String name): Returns a web element by name.
 * - getButtons(): Returns a list of buttons.
 * - clickButton(String name): Clicks a button by name.
 * - setGroceryActionButtons(String budgetCategory, String budgetedAmount): Sets the action buttons for a grocery budget.
 * - setEditBudgetModalElements(): Sets the elements for the edit budget modal.
 * - setDeleteButtonForGroceriesBudget(): Sets the delete button for the groceries budget.
 * - setSavingsBucketActionButtons(String bucketName, String amount): Sets the action buttons for a savings bucket.
 * - setEditSavingsBucketModalElements(): Sets the elements for the edit savings bucket modal.
 * - setDeleteButtonForHouseRepairsSavingsBucket(): Sets the delete button for the house repairs savings bucket.
 */

public class BudgetsPage extends Page {
    // Components
    @FindBy(id = "summary-component")
    private WebElement summaryComponentDiv;

    @FindBy(id = "Add-New-Budget")
    private WebElement addNewBudgetButton;

    @FindBy(id = "totalAmount")
    private WebElement budgetedInput;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/table[1]")
    private WebElement budgetsTable;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/table[2]")
    private WebElement savingsBucketTable;

    @FindBy(id = "Budget-Name-Input")
    private WebElement budgetNameInput;

    @FindBy(id = "Amount-Required-Input")
    private WebElement amountRequiredInput;

    @FindBy(id = "Submit-New-Savings-Bucket")
    private WebElement submitNewSavingsBucketButton;

    @FindBy(id = "Edit-Spending-Budget")
    private WebElement editSpendingBudgetButton;

    @FindBy(id = "totalBudgetAmount")
    private WebElement totalBudgetAmountInput;

    @FindBy(id = "Submit-Spending-Budget")
    private WebElement submitSpendingBudgetButton;

    @FindBy(id = "Spending-Budget-Div")
    private WebElement spendingBudgetDiv;

    private Navbar navbar;

    // Edit and Delete Budget Modal Elements
    private WebElement editBudgetButton;
    private WebElement deleteBudgetButton;
    private WebElement editedBudgetedInput;
    private WebElement saveBudgetButton;
    private WebElement confirmDeleteButton;
    // Edit and Delete Savings Bucket Modal Elements
    private WebElement editSavingsBucketButton;
    private WebElement deleteSavingsBucketButton;
    private WebElement editedAmountRequiredInput;
    private WebElement saveSavingsBucketButton;
    private WebElement confirmDeleteSavingsBucketButton;

    private Map<String, WebElement> elementMap;
    private List<WebElement> buttonList = new ArrayList<>();

    public BudgetsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        elementMap = new HashMap<>();
        elementMap.put("summaryComponentDiv", summaryComponentDiv);
        elementMap.put("addNewBudgetButton", addNewBudgetButton);
        elementMap.put("budgetedInput", budgetedInput);
        elementMap.put("budgetsTable", budgetsTable);
        elementMap.put("savingsBucketTable", savingsBucketTable);
        elementMap.put("budgetNameInput", budgetNameInput);
        elementMap.put("amountRequiredInput", amountRequiredInput);
        elementMap.put("submitNewSavingsBucketButton", submitNewSavingsBucketButton);
        elementMap.put("editBudgetButton", editBudgetButton);
        elementMap.put("deleteBudgetButton", deleteBudgetButton);
        elementMap.put("editedBudgetedInput", editedBudgetedInput);
        elementMap.put("saveBudgetButton", saveBudgetButton);
        elementMap.put("confirmDeleteButton", confirmDeleteButton);
        elementMap.put("editSavingsBucketButton", editSavingsBucketButton);
        elementMap.put("deleteSavingsBucketButton", deleteSavingsBucketButton);
        elementMap.put("editedAmountRequiredInput", editedAmountRequiredInput);
        elementMap.put("saveSavingsBucketButton", saveSavingsBucketButton);
        elementMap.put("confirmDeleteSavingsBucketButton", confirmDeleteSavingsBucketButton);
        elementMap.put("editSpendingBudgetButton", editSpendingBudgetButton);
        elementMap.put("totalBudgetAmountInput", totalBudgetAmountInput);
        elementMap.put("submitSpendingBudgetButton", submitSpendingBudgetButton);
        elementMap.put("spendingBudgetDiv", spendingBudgetDiv);
    }

    @Override
    public List<Component> getChildComponents() {
        ArrayList<Component> components = new ArrayList<Component>();
        components.add(navbar);
        return new ArrayList<Component>(Arrays.asList(navbar));
    }

    @Override
    public Component getChildComponent(String name) {
        if(name.equals("navbar")) return navbar;
        return null;
    }

    @Override
    public List<WebElement> getWebElements() {
        List<WebElement> webElements = new ArrayList<>(elementMap.values());

        System.out.println("Web elements: " + webElements);

        return webElements;
    }

    @Override
    public WebElement getWebElement(String name) {
        WebElement element = elementMap.get(name);
        if (element == null) {
            throw new IllegalArgumentException("No such element: " + name);
        }
        return element;
    }

    @Override
    public List<WebElement> getButtons() {
        return buttonList;
    }

    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);

        if(button == null) throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        button.click();
    }

    public void setGroceryActionButtons(String budgetCategory, String budgetedAmount) {
        List<WebElement> rows = budgetsTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String category = cells.get(0).getText();
                String amount = cells.get(1).getText();
                if (budgetCategory.equals(category) && budgetedAmount.equals(amount)) {
                    this.editBudgetButton = cells.get(5).findElement(By.id("Edit-Budget"));
                    elementMap.put("editBudgetButton", editBudgetButton);
                    this.deleteBudgetButton = cells.get(5).findElement(By.id("Delete-Budget"));
                    elementMap.put("deleteBudgetButton", deleteBudgetButton);
                    return;
                }
            }
        }
        throw new IllegalArgumentException(
                "No " + budgetCategory + " budget found with " + budgetedAmount + " budgeted");
    }

    public void setEditBudgetModalElements() {
        List<WebElement> dialogs = driver.findElements(By.cssSelector("div[role='dialog']"));

        for (WebElement dialog : dialogs) {
            WebElement h2 = dialog.findElement(By.tagName("h2"));
            List<WebElement> inputs = dialog.findElements(By.tagName("input"));

            if (h2 != null && "Edit Budget".equals(h2.getText().trim())) {
                for (WebElement input : inputs) {
                    if ("Groceries".equals(input.getAttribute("value"))) {
                        this.editedBudgetedInput = dialog.findElement(By.id("totalAmount"));
                        elementMap.put("editedBudgetedInput", editedBudgetedInput);
                        this.saveBudgetButton = dialog.findElement(By.xpath(".//button[text()='Save']"));
                        elementMap.put("saveBudgetButton", saveBudgetButton);
                        return;
                    }
                }
            }
        }
        throw new IllegalArgumentException("No dialog found with the specified inputs.");
    }

    public void setDeleteButtonForGroceriesBudget() {
        List<WebElement> dialogs = driver.findElements(By.cssSelector("div[role='dialog']"));

        for (WebElement dialog : dialogs) {
            List<WebElement> divs = dialog.findElements(By.tagName("div"));
            for (WebElement div : divs) {
                if ("Are you sure you want to delete the Groceries budget?".equals(div.getText().trim())) {
                    this.confirmDeleteButton = dialog.findElement(By.xpath(".//button[text()='Delete']"));
                    elementMap.put("confirmDeleteButton", confirmDeleteButton);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No dialog found with the specified text.");
    }

    public void setSavingsBucketActionButtons(String bucketName, String amount) {
        List<WebElement> rows = savingsBucketTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) {
                String name = cells.get(0).getText();
                String bucketAmount = cells.get(1).getText();
                if (bucketName.equals(name) && amount.equals(bucketAmount)) {
                    this.editSavingsBucketButton = cells.get(4).findElement(By.id("Edit-Savings-Bucket"));
                    elementMap.put("editSavingsBucketButton", editSavingsBucketButton);
                    this.deleteSavingsBucketButton = cells.get(4).findElement(By.id("Delete-Savings-Bucket"));
                    elementMap.put("deleteSavingsBucketButton", deleteSavingsBucketButton);
                    return;
                }
            }
        }
        throw new IllegalArgumentException(
                "No " + bucketName + " savings bucket found with " + amount + " budgeted");
    }

    public void setEditSavingsBucketModalElements() {
        List<WebElement> dialogs = driver.findElements(By.cssSelector("div[role='dialog']"));

        for (WebElement dialog : dialogs) {
            WebElement h2 = dialog.findElement(By.tagName("h2"));
            if (h2 != null && "Add New Savings Bucket".equals(h2.getText().trim())) {
                List<WebElement> inputs = dialog.findElements(By.tagName("input"));

                WebElement nameInputCandidate = null;
                WebElement amountRequiredInputCandidate = null;

                for (WebElement input : inputs) {
                    String value = input.getAttribute("value");
                    if ("House Repairs".equals(value)) {
                        nameInputCandidate = input;
                    } else if ("700".equals(value)) {
                        amountRequiredInputCandidate = input;
                    }
                }

                if (nameInputCandidate != null && amountRequiredInputCandidate != null) {
                    this.editedAmountRequiredInput = amountRequiredInputCandidate;
                    elementMap.put("editedAmountRequiredInput", editedAmountRequiredInput);
                    this.saveSavingsBucketButton = dialog.findElement(By.xpath(".//button[text()='Save']"));
                    elementMap.put("saveSavingsBucketButton", saveSavingsBucketButton);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No dialog found with the specified inputs.");
    }

    public void setDeleteButtonForHouseRepairsSavingsBucket() {
        List<WebElement> dialogs = driver.findElements(By.cssSelector("div[role='dialog']"));

        for (WebElement dialog : dialogs) {
            List<WebElement> divs = dialog.findElements(By.tagName("div"));
            for (WebElement div : divs) {
                if ("Are you sure you want to delete the House Repairs bucket?".equals(div.getText().trim())) {
                    this.confirmDeleteSavingsBucketButton = dialog.findElement(By.xpath(".//button[text()='Delete']"));
                    elementMap.put("confirmDeleteSavingsBucketButton", confirmDeleteSavingsBucketButton);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No dialog found with the specified text.");
    }
}
