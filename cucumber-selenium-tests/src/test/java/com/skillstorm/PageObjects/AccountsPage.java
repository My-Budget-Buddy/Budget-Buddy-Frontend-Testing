package com.skillstorm.PageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.PageObjects.Components.Navbar.DashboardNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class AccountsPage extends Page {
//#region Static fields

    //Names
    public static final String NAME_CMP_DASHBOARD_NAVBAR = "Dashboard Navbar";
    public static final String NAME_ACCORDION_CHECKING_BTN = "Checkings";
    public static final String NAME_ACCORDION_SAVINGS_BTN = "Savings";
    public static final String NAME_ACCORDION_CREDIT_CARDS_BTN = "Credit Cards"; 
    public static final String NAME_ACCORDION_INVESTMENTS_BTN = "Investments";  
    public static final String NAME_CHECKING_TABLE = "Checkings List";
    public static final String NAME_SAVINGS_TABLE = "Savings List";
    public static final String NAME_CREDIT_CARDS_TABLE = "Credit Card List";
    public static final String NAME_INVESTMENTS_TABLE = "Investments List";
    public static final String NAME_BTN_ADD_ACCOUNT = "Add Account";
    public static final String NAME_BTN_GET_REPORT = "Get Report";

    // Button IDs
    private final String BTN_ADD_ACCOUNT_LOCATOR = "button[aria-controls='account-modal']";
    private final String BTN_GET_REPORT_LOCATOR = "button[aria-controls='credit-score-modal'']";
    private final String ACCORDION_CHECKING_LOCATOR = "button[data-testid='accordionButton_checking']";
    private final String ACCORDION_SAVINGS_LOCATOR = "button[data-testid='accordionButton_savings']";
    private final String ACCORDION_CREDIT_CARDS_LOCATOR = "button[data-testid='accordionButton_credit']";
    private final String ACCORDION_INVESTMENTS_LOCATOR = "button[data-testid='accordionButton_investment']";

    // Table IDs
    private final String ACCORDION_LIST_CHECKING_ID = "Checking";
    private final String ACCORDION_LIST_SAVINGS_ID = "savings";
    private final String ACCORDION_LIST_CREDIT_CARDS_ID = "credit-cards";
    private final String ACCORDION_LIST_INVESTMENTS_TABLE_ID = "investments";

    // Other WebElement IDs

//#END Static Fields

//#region Attributes

    Map<String, WebElement> nameElementMap = new HashMap<String, WebElement>();

    //Web Elements - Buttons
    @FindBy(css = ACCORDION_CHECKING_LOCATOR)
    private WebElement btnAccordianChecking;

    @FindBy(css = ACCORDION_SAVINGS_LOCATOR)
    private WebElement btnAccordianSavings;
    
    @FindBy(css = ACCORDION_CREDIT_CARDS_LOCATOR)
    private WebElement btnAccordianCreditCards;
    
    @FindBy(css = ACCORDION_INVESTMENTS_LOCATOR)
    private WebElement btnAccordianInvestments;

    @FindBy(css = BTN_ADD_ACCOUNT_LOCATOR)
    private WebElement btnAddAccount;

    @FindBy(css = BTN_GET_REPORT_LOCATOR)
    private WebElement btnGetReport;

    //Web Elements - Hidden Elements
    @FindBy(id = ACCORDION_LIST_CHECKING_ID)
    private WebElement tableChecking;

    @FindBy(id = ACCORDION_LIST_SAVINGS_ID)
    private WebElement tableSavings;

    @FindBy(id = ACCORDION_LIST_CREDIT_CARDS_ID)
    private WebElement tableCreditCards;

    @FindBy(id = ACCORDION_LIST_INVESTMENTS_TABLE_ID)
    private WebElement tableInvestments;


    //Web Elements - Charts

    //Child Components
    DashboardNavbar navbar;

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////
    public AccountsPage(WebDriver driver) {
        super(driver);

        // Initialize Child Componenets
        navbar = new DashboardNavbar(driver);

        // Initialize WebElements
        PageFactory.initElements(driver, this);

        // Map WebElements
        nameElementMap.put(NAME_ACCORDION_CHECKING_BTN, btnAccordianChecking);
        nameElementMap.put(NAME_ACCORDION_SAVINGS_BTN, btnAccordianSavings);
        nameElementMap.put(NAME_ACCORDION_CREDIT_CARDS_BTN, btnAccordianCreditCards);
        nameElementMap.put(NAME_ACCORDION_INVESTMENTS_BTN, btnAccordianInvestments);
        nameElementMap.put(NAME_CHECKING_TABLE, tableChecking);
        nameElementMap.put(NAME_SAVINGS_TABLE, tableSavings);
        nameElementMap.put(NAME_CREDIT_CARDS_TABLE, tableCreditCards);
        nameElementMap.put(NAME_INVESTMENTS_TABLE, tableInvestments);
        nameElementMap.put(NAME_BTN_ADD_ACCOUNT, btnAddAccount);
        nameElementMap.put(NAME_BTN_GET_REPORT, btnGetReport);
        
    }


    /////////// FUNCTIONS /////////////////////




    ////////////// OVERRIDES ///////////////////////
    /**
     * Maps the button names in the static fields to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);

        if(button == null) throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        button.click();
    }

    /**
     * Retrieves all Components directly under this Component.
     */
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(navbar);
    }

    /**
     * Retrieves a component given a name. Does not search child components.
     * @param name  Name of the component to retrieve.
     * @return      Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        if(name.equals(NAME_CMP_DASHBOARD_NAVBAR)) return navbar;  // Directly exists here

        // Couldn't find.
        return null;
    }

    /**
     * Retrieves all WebElements in this component and child components.
     */
    @Override
    public List<WebElement> getWebElements() {
        // Get all web elements in this component
        List<WebElement> webElements = new ArrayList<WebElement>();
        for (WebElement webElement : nameElementMap.values()) {
            webElements.add(webElement);
        }

        // Add child components' web elements
        for (Component component : getChildComponents()) {
            if (component != null)
            for (WebElement webElement : component.getWebElements()) {
                webElements.add(webElement);
            }
        }

        return webElements;
    }


    /**
     * Retrieves a WebElement given a name. Searches child components as well.
     * @param name  Name of the WebElement to search for.
     * @return      WebElement tied to that name, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        // Check in sub-components
        for (Component component : getChildComponents()) {
            if(component != null) {
                WebElement found = component.getWebElement(name);
                if(found != null) return found;
            }
        }

        //check in main componenets
        return nameElementMap.get(name);
    }

    /**
     * Returns all buttons directly inside of this component.
     */
    @Override
    public List<WebElement> getButtons() {
        List<WebElement> buttons = Arrays.asList(
            btnAccordianChecking,
            btnAccordianSavings,
            btnAccordianCreditCards,
            btnAccordianInvestments,
            btnAddAccount
        );
        
        return buttons;
    }

    
}
