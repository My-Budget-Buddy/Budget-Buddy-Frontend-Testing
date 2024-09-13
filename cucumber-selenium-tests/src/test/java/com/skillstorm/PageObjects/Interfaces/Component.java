/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Serves as the base of the POM architecture and provides the common
 *      functions for all components.
 */

package com.skillstorm.PageObjects.Interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface Component {
    List<Component> getChildComponents();
    Component getChildComponent(String name);

    List<WebElement> getWebElements();
    WebElement getWebElement(String name);
}