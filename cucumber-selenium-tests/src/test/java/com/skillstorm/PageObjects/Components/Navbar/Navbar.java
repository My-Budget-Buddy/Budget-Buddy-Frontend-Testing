/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This abstract class provides a common class to store a Navbar
 *      in and automatically stores the WebDriver.
 */

package com.skillstorm.PageObjects.Components.Navbar;

import org.openqa.selenium.WebDriver;

import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public abstract class Navbar implements Component, ButtonContainer {
    private WebDriver driver;

    public Navbar(WebDriver driver){
        this.driver = driver;
    }
}
