/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Represents the Navbar that appears when a user is logged out.
 */

package com.skillstorm.PageObjects.Components.Navbar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedOutNavbar extends Navbar{
//#region Static fields
    public static final String BTN_LANDING_NAME = "Navbar Budget Buddy Icon";
    public static final String BTN_LOGIN_NAME = "Navbar Log In";
    public static final String BTN_REGISTER_NAME = "Navbar Register";
//#endregion

    private WebElement btnLanding;
    private WebElement btnLogin;
    private WebElement btnRegister;

    public LoggedOutNavbar(WebDriver driver) {
        super(driver);
    }

    /**
     * Maps a given button name to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        switch (name) {
            case BTN_LANDING_NAME:
                clickBtnLanding();
                break;
            case BTN_LOGIN_NAME:
                clickBtnLogin();
                break;
            case BTN_REGISTER_NAME:
                clickBtnRegister();
                break;
            default:
                throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }

    private void clickBtnLanding(){
        btnLanding.click();
    }

    private void clickBtnLogin(){
        btnLogin.click();
    }

    private void clickBtnRegister(){
        btnRegister.click();
    }
}
