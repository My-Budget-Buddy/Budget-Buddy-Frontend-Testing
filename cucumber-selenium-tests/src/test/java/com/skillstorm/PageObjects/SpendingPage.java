package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.PageObjects.Interfaces.Component;

public class SpendingPage extends Page {
//#region Static Fields

    // IDs
    public static final String BTN_SEECURRENTMONTH_ID = "see-current-month-button";

    // Names
    public static final String BTN_SEECURRENTMONTH_NAME = "See Current Month";

//#endregion

    @FindBy(id=BTN_SEECURRENTMONTH_ID)
    private WebElement btnSeeCurrentMonth;

    /**
     * Initializes the driver and sets an implicit wait 
     */
    public SpendingPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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

    /**
     * Returns a List of all WebElements within this component. Searches sub-components as well.
     */
    @Override
    public List<WebElement> getWebElements() {
        ArrayList<WebElement> webElements = new ArrayList<WebElement>();
        webElements.add(btnSeeCurrentMonth);

        for(Component component : getChildComponents()){
            webElements.addAll(component.getWebElements());
        }

        return webElements;
    }
    
    /**
     * Returns a WebElement given a name. Searches sub-components as well.
     * return WebElement requested, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_SEECURRENTMONTH_NAME:
                return btnSeeCurrentMonth;
            default:
                for (Component component : getChildComponents()) {
                    WebElement webElement = component.getWebElement(name);
                    if(webElement != null) return webElement;
                }
                break;
        }

        return null;
    }

    /**
     * Returns a list of all buttons directly under this component.
     */
    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnSeeCurrentMonth);
    }

    /**
     * Clicks a button given a name.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        
        if(button != null) button.click();
        else throw new IllegalArgumentException("Button '" + name + "' does not exist.");
    }
}