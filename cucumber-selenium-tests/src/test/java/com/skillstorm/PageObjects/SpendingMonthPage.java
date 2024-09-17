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

import com.skillstorm.PageObjects.Components.Navbar.DashboardNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class SpendingMonthPage extends Page {
//#region Static Fields

    // Components
    public static final String CMP_DASHBOARDNAVBAR_NAME = "Navbar";

    // IDs
    public static final String BTN_BACKTOSPENDING_ID = "back-to-spending-btn";

    // Names
    public static final String BTN_BACKTOANNUALSPENDING_NAME = "Back to Annual Spending Overview";

//#endregion

    @FindBy(id = BTN_BACKTOSPENDING_ID)
    private WebElement btnBackToSpending;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div[1]/a[4]")
    private WebElement spendingsTab;

    // Child Component
    private DashboardNavbar dashboardNavbar;

    /**
     * Initializes the driver and sets an implicit wait 
     */
    public SpendingMonthPage(WebDriver driver){
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
        return Arrays.asList(dashboardNavbar);
    }

    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case CMP_DASHBOARDNAVBAR_NAME:
                return dashboardNavbar;
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
        webElements.add(btnBackToSpending);

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
            case BTN_BACKTOANNUALSPENDING_NAME:
                return btnBackToSpending;
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
        return Arrays.asList(btnBackToSpending);
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