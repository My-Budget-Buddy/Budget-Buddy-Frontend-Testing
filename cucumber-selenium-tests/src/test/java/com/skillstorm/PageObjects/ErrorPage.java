package com.skillstorm.PageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.PageObjects.Interfaces.Component;

public class ErrorPage extends Page{
    

    @FindBy(id = "error-page-header")
    public WebElement errorHeader;

    @FindBy(id = "Return-to-homepage")
    public WebElement returnToHomepageButton;

    @FindBy(tagName = "body")
    public List<WebElement> webElements;

    public ErrorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        return webElements;
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case "errorHeader":
                return errorHeader;
            case "returnToHomepageButton":
                return returnToHomepageButton;
            default:
                throw new IllegalArgumentException("No such element: " + name);
        }
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(returnToHomepageButton);
    }

    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        if(button == null) throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        button.click();
    }


}