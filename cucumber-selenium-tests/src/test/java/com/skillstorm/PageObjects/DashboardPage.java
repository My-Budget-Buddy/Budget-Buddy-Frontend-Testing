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

import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class DashboardPage extends Page {
//#region Static fields

    //Components
    public static final String CMP_LANDING_NAVBAR_NAME = "Landing Navbar";
    public static final String NAME_TRANSACTION_ARROW = "Transaction Arrow";
    public static final String NAME_ACCORDION_CHECKING = "Checkings";
    public static final String NAME_ACCORDION_SAVINGS = "Savings";
    public static final String NAME_ACCORDION_CREDIT_CARDS = "Credit Cards"; 
    public static final String NAME_ACCORDION_INVESTMENTS = "Investments";   

    // Button IDs
    private final String ACCORDION_CHECKING_LOCATOR = "button[data-testid='accordionButton_checking']";
    private final String ACCORDION_SAVINGS_LOCATOR = "button[data-testid='accordionButton_savings']";
    private final String ACCORDION_CREDIT_CARDS_LOCATOR = "button[data-testid='accordionButton_credit']";
    private final String ACCORDION_INVESTMENTS_LOCATOR = "button[data-testid='accordionButton_investment']";
    private final String BTN_TRANSCATION_ARROWS_ID = "btnTransactionArrow";

    // Other WebElement IDs


//#END Static Fields

//#region Attributes

    Map<String, WebElement> nameElementMap = new HashMap<String, WebElement>();

    //Web Elements
    @FindBy(css = ACCORDION_CHECKING_LOCATOR)
    private WebElement btnAccordianChecking;

    @FindBy(css = ACCORDION_SAVINGS_LOCATOR)
    private WebElement btnAccordianSavings;
    
    @FindBy(css = ACCORDION_CREDIT_CARDS_LOCATOR)
    private WebElement btnAccordianCreditCards;
    
    @FindBy(css = ACCORDION_INVESTMENTS_LOCATOR)
    private WebElement btnAccordianInvestments;

    @FindBy(id = BTN_TRANSCATION_ARROWS_ID)
    private List<WebElement> btnTransactionButtons;

    //Child Components
    //TODO: Navbar???

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////   
    public DashboardPage(WebDriver driver) {
        super(driver);
        //initialize WebElements
        PageFactory.initElements(driver, this);

        //load components
        //TODO: Navbar??

        //Map WebElements
        nameElementMap.put(NAME_ACCORDION_CHECKING, btnAccordianChecking);
        nameElementMap.put(NAME_ACCORDION_SAVINGS, btnAccordianSavings);
        nameElementMap.put(NAME_ACCORDION_CREDIT_CARDS, btnAccordianCreditCards);
        nameElementMap.put(NAME_ACCORDION_INVESTMENTS, btnAccordianInvestments);
        nameElementMap.put(NAME_TRANSACTION_ARROW, btnTransactionButtons.get(0)); //just the first arrow button
        //NOTE: btnTransactionButtons are a list, so that needs to be accounted for in "get" methods
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
        return null;
        //TODO: Navbar???
    }

    /**
     * Retrieves all WebElements in this component and child components.
     */
    @Override
    public List<WebElement> getWebElements() {
        // Get all buttons
        List<WebElement> webElements = getButtons();

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

    public void printElements() {
        List<WebElement> allElements = getWebElements();
        System.out.print("[ ");
        for (WebElement element : allElements) {
            System.out.print(element + ", ");
        }
        System.out.print(" ]\n");
    }
}
