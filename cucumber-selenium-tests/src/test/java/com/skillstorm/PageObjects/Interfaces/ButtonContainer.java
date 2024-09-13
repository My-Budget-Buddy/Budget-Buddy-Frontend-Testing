package com.skillstorm.PageObjects.Interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface ButtonContainer {
    List<WebElement> getButtons();
    void clickButton(String name);
}
