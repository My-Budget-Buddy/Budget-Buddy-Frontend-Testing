package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class SpendingPage extends Page {
//#region Static Fields

    // Components
    public static final String CMP_NAVBAR_NAME = "Navbar";

    // IDs
    public static final String BTN_SEECURRENTMONTH_ID = "see-current-month-button";

    // Names
    public static final String BTN_SEECURRENTMONTH_NAME = "See Current Month";

//#endregion

    @FindBy(id = BTN_SEECURRENTMONTH_ID)
    private WebElement btnSeeCurrentMonth;

    @FindBy(xpath = "/html/body/div/div[1]/div/div/a[4]")
    private WebElement spendingsTab;

    // Child Component
    private Navbar navbar;

    /**
     * Initializes the driver and sets an implicit wait 
     */
    public SpendingPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickTab() {
        waitForElement(spendingsTab, 10).click();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(navbar);
    }

    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case CMP_NAVBAR_NAME:
                return navbar;
            default:
                return null;
        }
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
        
        if(button != null) {
            waitForElement(button, 10).click();
        }
        else {
            throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }

        // give time for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}