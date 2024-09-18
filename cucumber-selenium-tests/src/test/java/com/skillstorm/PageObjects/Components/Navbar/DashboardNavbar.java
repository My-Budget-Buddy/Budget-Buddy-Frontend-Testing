/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Represents the blue navbar that shows on the left of the screen when
 *      a user is signed in.
 */

package com.skillstorm.PageObjects.Components.Navbar;

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

import com.skillstorm.PageObjects.Interfaces.Component;

public class DashboardNavbar extends Navbar {
//#region Static fields

    // IDs
    public static final String BTN_LANDING_ID = "navbar-landing";
    public static final String BTN_DASHBOARD_ID = "navbar-dashboard";
    public static final String BTN_ACCOUNTS_ID = "navbar-accounts";
    public static final String BTN_BUDGETS_ID = "navbar-budgets";
    public static final String BTN_SPENDINGS_ID = "navbar-spending";
    public static final String BTN_TRANSACTIONS_ID = "navbar-transactions";
    public static final String BTN_TAX_ID = "navbar-tax";

    // Names
    public static final String BTN_LANDING_NAME = "Navbar Budget Buddy Icon";
    public static final String BTN_DASHBOARD_NAME = "Dashboard";
    public static final String BTN_ACCOUNTS_NAME = "Accounts";
    public static final String BTN_BUDGETS_NAME = "Budgets";
    public static final String BTN_SPENDINGS_NAME = "Spendings";
    public static final String BTN_TRANSACTIONS_NAME = "Transactions";
    public static final String BTN_TAX_NAME = "Tax";

//#endregion

    Map<String, WebElement> nameElementMap = new HashMap<String, WebElement>();

    // WebElements
    @FindBy(id=BTN_LANDING_ID)
    private WebElement btnLanding;
    @FindBy(id=BTN_DASHBOARD_ID)
    private WebElement btnDashboard;
    @FindBy(id=BTN_ACCOUNTS_ID)
    private WebElement btnAccounts;
    @FindBy(id=BTN_BUDGETS_ID)
    private WebElement btnBudgets;
    @FindBy(id=BTN_SPENDINGS_ID)
    private WebElement btnSpendings;
    @FindBy(id=BTN_TRANSACTIONS_ID)
    private WebElement btnTransactions;
    @FindBy(id=BTN_TAX_ID)
    private WebElement btnTax;

    public DashboardNavbar(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);

        // Map web elements.
        nameElementMap.put(BTN_LANDING_NAME, btnLanding);
        nameElementMap.put(BTN_DASHBOARD_NAME, btnDashboard);
        nameElementMap.put(BTN_ACCOUNTS_NAME, btnAccounts);
        nameElementMap.put(BTN_BUDGETS_NAME, btnBudgets);
        nameElementMap.put(BTN_SPENDINGS_NAME, btnSpendings);
        nameElementMap.put(BTN_TRANSACTIONS_NAME, btnTransactions);
        nameElementMap.put(BTN_TAX_NAME, btnTax);

    }

    /**
     * DashboardNavbar has no child components.
     */
    @Override
    public List<Component> getChildComponents() {
        return null;
    }

    /**
     * DashboardNavbar has no child components.
     */
    @Override
    public Component getChildComponent(String name) {
        return null;
    }

    /**
     * Returns all of the buttons on the navbar.
     */
    @Override
    public List<WebElement> getWebElements() {
        return new ArrayList<WebElement>(nameElementMap.values());
    }

    /**
     * Returns a specific button from the navbar, or null if it doesn't exist.
     */
    @Override
    public WebElement getWebElement(String name) {
        return nameElementMap.get(name);
    }

    /**
     * Returns all of the buttons on the navbar.
     */
    @Override
    public List<WebElement> getButtons() {
        return getWebElements();
    }

    /**
     * Clicks a given button.
     */
    @Override
    public void clickButton(String name) {
        nameElementMap.get(name).click();
    }
}
