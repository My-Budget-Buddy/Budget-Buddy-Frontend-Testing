/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents the footer at the bottom of the logged-out
 *      pages.
 */

package com.skillstorm.PageObjects.Components;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public class Footer implements Component, ButtonContainer {
    private WebDriver driver;
    
    private WebElement btnAboutUs;
    private WebElement btnContact;
    private WebElement btnServices;
    private WebElement btnSupport;
    private WebElement btnPhone;
    private WebElement btnEmail;

    @Override
    public void clickButton(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clickButton'");
    }

    @Override
    public List<WebElement> getButtons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getButtons'");
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

    @Override
    public List<WebElement> getWebElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWebElements'");
    }

    @Override
    public WebElement getWebElement(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWebElement'");
    }
}
