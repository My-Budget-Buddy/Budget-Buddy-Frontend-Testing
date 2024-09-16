package com.skillstorm.PageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class DashboardPage extends Page {
//#region Static fields

    //Components
    public static final String CMP_LANDING_NAVBAR_NAME = "Landing Navbar";    

    // Button IDs
    public static final String ACCORDION_CHECKING_LOCATOR = "h4[data-testid='accordionButton_checking']";
    public static final String ACCORDION_SAVINGS_LOCATOR = "h4[data-testid='accordionButton_savings]";
    public static final String ACCORDION_CREDIT_CARDS_LOCATOR = "h4[data-testid='accordionButton_credit']";
    public static final String ACCORDION_INVESTMENTS_LOCATOR = "h4[data-testid='accordionButton_investment']";
    public static final String BTN_TRANSCATION_ARROWS_ID = "btnTransactionArrow";

    // Other WebElement IDs


//#END Static Fields

//#region Attributes
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
    private LandingNavbar landingNavbar;

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////   
    public DashboardPage(WebDriver driver) {
        super(driver);
        this.landingNavbar = new LandingNavbar(driver);
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
        return Arrays.asList(landingNavbar);
    }

    /**
     * Retrieves a component given a name. Does not search child components.
     * @param name  Name of the component to retrieve.
     * @return      Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case CMP_LANDING_NAVBAR_NAME:
                return landingNavbar;
            default:
                return null;
        }
    }

    /**
     * Retrieves all WebElements in this component and child components.
     */
    @Override
    public List<WebElement> getWebElements() {
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
        switch (name) {
            case ACCORDION_CHECKING_LOCATOR:
                return btnAccordianChecking;
            case ACCORDION_SAVINGS_LOCATOR:
                return btnAccordianSavings;
            case ACCORDION_CREDIT_CARDS_LOCATOR:
                return btnAccordianCreditCards;
            case ACCORDION_INVESTMENTS_LOCATOR:
                return btnAccordianInvestments;
            case BTN_TRANSCATION_ARROWS_ID:
                return btnTransactionButtons.get(0);
            default:
                break;
        }

        // Check in sub-components
        for (Component component : getChildComponents()) {
            if(component != null) {
                WebElement found = component.getWebElement(name);
                if(found != null) return found;
            }
        }

        // Not found.
        return null;
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
