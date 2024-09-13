/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This abstract class provides a standard way to access page
 *      objects and automatically stores the driver.
 */

package com.skillstorm.PageObjects;

import org.openqa.selenium.WebDriver;

import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public abstract class Page implements Component, ButtonContainer{
    protected WebDriver driver;
    protected Navbar navbar;

    public Page(WebDriver driver){
        this.driver = driver;
    }
}
