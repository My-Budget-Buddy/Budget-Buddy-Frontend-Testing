/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents the footer at the bottom of the logged-out
 *      pages.
 */

package com.skillstorm.PageObjects.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer implements Component {
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
}
