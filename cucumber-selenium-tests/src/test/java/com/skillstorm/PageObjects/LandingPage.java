package com.skillstorm.PageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.PageObjects.Components.Footer;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;

public class LandingPage extends Page {
//#region Static fields

    //Naming System
    public static final String CMP_LANDING_NAVBAR_NAME = "Landing Navbar";    
    public static final String CMP_FOOTER_NAME = "Footer";

    // Button IDs
    public static final String BTN_GET_STARTED_ID = "Get Started";

    // Other WebElement IDs


//#END Static Fields

//#region Attributes
    @FindBy(id = BTN_GET_STARTED_ID)
    private WebElement btnGetStarted;

    //Child Components
    private Footer footer;
    private LandingNavbar landingNavbar;

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////
    public LandingPage(WebDriver driver) {
        super(driver);
        footer = new Footer();
        landingNavbar = new LandingNavbar(driver);
        PageFactory.initElements(driver, this);
    }
    
    /////////// FUNCTIONS /////////////////////
    private void clickBtnGetStarted() {
        btnGetStarted.click();
    }

    public void reloadElements() {
        PageFactory.initElements(driver, this);
    }

    /////////// OVERRIDE IMPLEMENTATIONS //////////////
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(
            footer, 
            landingNavbar
        );
    }

    @Override
    public Component getChildComponent(String name) {
        switch (name) {
            case CMP_FOOTER_NAME:
                return footer;
            case CMP_LANDING_NAVBAR_NAME:
                return landingNavbar;
            default:
                throw new IllegalArgumentException("Component'" + name + "' does not exist.");
        }
    }

    @Override
    public List<WebElement> getWebElements() {
        List<WebElement> webElements = new ArrayList<>();
        webElements.add(btnGetStarted);
        return webElements;
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_GET_STARTED_ID:
                return btnGetStarted;
            default:
                throw new IllegalArgumentException("WebElement'" + name + "' does not exist.");
        }
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnGetStarted);
    }

    @Override
    public void clickButton(String name) {
        switch (name) {
            case BTN_GET_STARTED_ID:
                clickBtnGetStarted();
                break;
            default:
                throw new IllegalArgumentException("Button'" + name + "' does not exist.");
        }
    }
    
}
