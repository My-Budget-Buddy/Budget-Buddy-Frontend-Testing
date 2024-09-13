/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents the footer at the bottom of the logged-out
 *      pages.
 */

package com.skillstorm.PageObjects.Components;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public class Footer implements Component, ButtonContainer {
//#region Static fields

    // IDs
    public static final String BTN_ABOUT_US_ID = "about-us-link";
    public static final String BTN_CONTACT_ID = "services-link";
    public static final String BTN_SERVICES_ID = "contact-link";
    public static final String BTN_SUPPORT_ID = "support-link";
    public static final String BTN_PHONE_ID = "phone-attachment";
    public static final String BTN_EMAIL_ID = "email-attachment";

    // Names
    public static final String BTN_ABOUT_US_NAME = "Footer About Us";
    public static final String BTN_CONTACT_NAME = "Footer Contact";
    public static final String BTN_SERVICES_NAME = "Footer Services";
    public static final String BTN_SUPPORT_NAME = "Footer Support";
    public static final String BTN_PHONE_NAME = "Footer Phone Number";
    public static final String BTN_EMAIL_NAME = "Footer Email";
    
//#endregion

//#region Web Elements
    private WebDriver driver;
    
    @FindBy(id = BTN_ABOUT_US_ID)
    private WebElement btnAboutUs;

    @FindBy(id = BTN_CONTACT_ID)
    private WebElement btnContact;

    @FindBy(id=BTN_SERVICES_ID)
    private WebElement btnServices;

    @FindBy(id=BTN_SUPPORT_ID)
    private WebElement btnSupport;

    @FindBy(id=BTN_PHONE_ID)
    private WebElement btnPhone;

    @FindBy(id=BTN_EMAIL_ID)
    private WebElement btnEmail;
//#endregion

    @Override
    public void clickButton(String name) {
        getWebElement(name).click();
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnAboutUs, btnContact, btnEmail, btnPhone, btnServices, btnSupport);
    }

    @Override
    public List<Component> getChildComponents() {
        return null;
    }

    @Override
    public Component getChildComponent(String name) {
        return null;
    }

    @Override
    public List<WebElement> getWebElements() {
        return Arrays.asList(btnAboutUs, btnContact, btnEmail, btnPhone, btnServices, btnSupport);
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_ABOUT_US_NAME:
                return btnAboutUs;
            case BTN_CONTACT_NAME:
                return btnContact;
            case BTN_EMAIL_NAME:
                return btnEmail;
            case BTN_PHONE_NAME:
                return btnPhone;
            case BTN_SERVICES_NAME:
                return btnServices;
            case BTN_SUPPORT_NAME:
                return btnSupport;
            default:
                throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }
}
