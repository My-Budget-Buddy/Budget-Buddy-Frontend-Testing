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
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class DashboardPage extends Page {
//#region Static fields

    //Names
    public static final String NAME_CMP_DASHBOARD_NAVBAR = "Landing Navbar";
    public static final String NAME_TRANSACTION_ARROW = "Transaction Arrow";
    public static final String NAME_ACCORDION_CHECKING_BTN = "Checkings";
    public static final String NAME_ACCORDION_SAVINGS_BTN = "Savings";
    public static final String NAME_ACCORDION_CREDIT_CARDS_BTN = "Credit Cards"; 
    public static final String NAME_ACCORDION_INVESTMENTS_BTN = "Investments";  
    public static final String NAME_TRANSACTION_ARROW_OVERLAY = "Recent Transaction"; 
    public static final String NAME_CURRENT_SPENDING_CHART_TITLE = "Current Spending Chart Title";
    public static final String NAME_CURRENT_SPENDING_CHART = "Current Spending Chart";
    public static final String NAME_BUDGET_CIRCLE_CHART = "Budget Circle Chart";
    public static final String NAME_BUDGET_BREAKDOWN = "Budget Breakdown";
    public static final String NAME_CHECKING_TABLE = "Checkings List";
    public static final String NAME_SAVINGS_TABLE = "Savings List";
    public static final String NAME_CREDIT_CARDS_TABLE = "Credit Card List";
    public static final String NAME_INVESTMENTS_TABLE = "Investments List";

    // Button IDs
    private final String ACCORDION_CHECKING_LOCATOR = "button[data-testid='accordionButton_checking']";
    private final String ACCORDION_SAVINGS_LOCATOR = "button[data-testid='accordionButton_savings']";
    private final String ACCORDION_CREDIT_CARDS_LOCATOR = "button[data-testid='accordionButton_credit']";
    private final String ACCORDION_INVESTMENTS_LOCATOR = "button[data-testid='accordionButton_investment']";
    private final String BTN_TRANSCATION_ARROWS_ID = "btnTransactionArrow";

    // Table IDs
    private final String ACCORDION_LIST_CHECKING_ID = "checking";
    private final String ACCORDION_LIST_SAVINGS_ID = "savings";
    private final String ACCORDION_LIST_CREDIT_CARDS_ID = "credit";
    private final String ACCORDION_LIST_INVESTMENTS_TABLE_ID = "investment";
    private final String CURRENT_SPENDING_CHART_TITLE_ID = "current-spending-chart-header";
    private final String CURRENT_SPENDING_CHART_ID = "chart-container";
    private final String BUDGET_CIRCLE_CHART_ID = "budget-gauge";
    private final String BUDGET_BREAKDOWN_ID = "budget-items";

    // Other WebElement IDs
    private final String TRANSACTION_ARROW_OVERLAY_ID = "transaction-info-modal";

//#END Static Fields

//#region Attributes

    Map<String, WebElement> nameElementMap = new HashMap<String, WebElement>();

    //Web Elements - Account Accordion Buttons
    @FindBy(css = ACCORDION_CHECKING_LOCATOR)
    private WebElement btnAccordianChecking;

    @FindBy(css = ACCORDION_SAVINGS_LOCATOR)
    private WebElement btnAccordianSavings;
    
    @FindBy(css = ACCORDION_CREDIT_CARDS_LOCATOR)
    private WebElement btnAccordianCreditCards;
    
    @FindBy(css = ACCORDION_INVESTMENTS_LOCATOR)
    private WebElement btnAccordianInvestments;

    //Web Elements - Hidden Elements
    @FindBy(id = ACCORDION_LIST_CHECKING_ID)
    private WebElement tableChecking;

    @FindBy(id = ACCORDION_LIST_SAVINGS_ID)
    private WebElement tableSavings;

    @FindBy(id = ACCORDION_LIST_CREDIT_CARDS_ID)
    private WebElement tableCreditCards;

    @FindBy(id = ACCORDION_LIST_INVESTMENTS_TABLE_ID)
    private WebElement tableInvestments;

    @FindBy(id = TRANSACTION_ARROW_OVERLAY_ID)
    private WebElement transactionArrowOverlay;

    //Web Elements - Transaction Arrow Buttons
    @FindBy(id = BTN_TRANSCATION_ARROWS_ID)
    private List<WebElement> btnTransactionButtons;


    //Web Elements - Charts
    @FindBy(id = CURRENT_SPENDING_CHART_TITLE_ID)
    private WebElement currentSpendingChartTitle;

    @FindBy(id = CURRENT_SPENDING_CHART_ID)
    private WebElement currentSpendingChart;

    @FindBy(id = BUDGET_CIRCLE_CHART_ID)
    private WebElement budgetCircleChart;

    @FindBy(id = BUDGET_BREAKDOWN_ID)
    private List<WebElement> budgetBreakdownItems;

    //Child Components
    DashboardNavbar navbar;

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////   
    public DashboardPage(WebDriver driver) {
        super(driver);
        //initialize WebElements
        PageFactory.initElements(driver, this);

        //load components
        navbar = new DashboardNavbar(driver);

        //Map WebElements
        nameElementMap.put(NAME_ACCORDION_CHECKING_BTN, btnAccordianChecking);
        nameElementMap.put(NAME_ACCORDION_SAVINGS_BTN, btnAccordianSavings);
        nameElementMap.put(NAME_ACCORDION_CREDIT_CARDS_BTN, btnAccordianCreditCards);
        nameElementMap.put(NAME_ACCORDION_INVESTMENTS_BTN, btnAccordianInvestments);
        nameElementMap.put(NAME_CHECKING_TABLE, tableChecking);
        nameElementMap.put(NAME_SAVINGS_TABLE, tableSavings);
        nameElementMap.put(NAME_CREDIT_CARDS_TABLE, tableCreditCards);
        nameElementMap.put(NAME_INVESTMENTS_TABLE, tableInvestments); 
        nameElementMap.put(NAME_TRANSACTION_ARROW_OVERLAY, transactionArrowOverlay);
        nameElementMap.put(NAME_CURRENT_SPENDING_CHART_TITLE, currentSpendingChartTitle);
        nameElementMap.put(NAME_CURRENT_SPENDING_CHART, currentSpendingChart);
        nameElementMap.put(NAME_BUDGET_CIRCLE_CHART, budgetCircleChart);
        nameElementMap.put(NAME_BUDGET_BREAKDOWN, budgetBreakdownItems.get(0));
        nameElementMap.put(NAME_TRANSACTION_ARROW, btnTransactionButtons.get(0)); //just the first arrow button
        //NOTE: btnTransactionButtons are a list, so that needs to be accounted for in "get" methods
    }

    ////////// UNIQUE METHODS ////////////////////

    /**
     * Retrieves the list name associated with its accordion header name.
     * @param btnAccordionName - name of the button to that displays the accordian list.
     * @return - name of the accordian list associated with the button.
     */
    public String getListName(String btnAccordionName) {
        switch(btnAccordionName) {
            case NAME_ACCORDION_CHECKING_BTN:
                return NAME_CHECKING_TABLE;
            case NAME_ACCORDION_SAVINGS_BTN:
                return NAME_SAVINGS_TABLE;
            case NAME_ACCORDION_CREDIT_CARDS_BTN:
                return NAME_CREDIT_CARDS_TABLE;
            case NAME_ACCORDION_INVESTMENTS_BTN:
                return NAME_INVESTMENTS_TABLE;
            default:
                return null;
        }
    }

    public double getCurrentSpending() {
        //Get the title of the current spending chart
        String title = currentSpendingChartTitle.getText();
        //Get the number from the title and remove all the commas, keeping the decimal
        String spending = title.substring(title.lastIndexOf("$") + 1).replaceAll(",","").trim();
        
        return Double.parseDouble(spending);
    }

    public List<WebElement> getAllBudgetItems() {
        return budgetBreakdownItems;
    }


    ////////// OVERRIDDEN METHODS ///////////////
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
        return new ArrayList<Component>(); //Arrays.asList(navbar);???
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
        //Add all transaction buttons that were found
        List<WebElement> buttons = btnTransactionButtons;

        //Add accordion buttons
        buttons.add(btnAccordianChecking);
        buttons.add(btnAccordianSavings);
        buttons.add(btnAccordianCreditCards);
        buttons.add(btnAccordianInvestments);
        
        return buttons;
    }

}
