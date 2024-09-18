package com.skillstorm.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.Utilities.Navigator;


//This class implements some step definitions that are commonly used among our tests. You must pass in the web driver you're using as a parameter.
public class GenericStepDefinitions{

    WebDriver driver;
    WebDriverWait wait;
    Navigator navigator;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigator = new Navigator(driver);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    /*
     * General use definition for page loading step. Uses navigator class in Utilities package
     * -------------------------------------------------------------------------------------------------------------
     * Parameters:
     * pageName - the page name you'd want to navigate to. It must match Navigator.java naming convention.
     * -------------------------------------------------------------------------------------------------------------
     * If you haven't already, you must redefine the method for navigation for your respective page and set the URL for your page as well.
     */

    @Given("I am on the {string} page")
    public void iAmOnThePage(String pageName){
        navigator.navigateTo(pageName);
    }


    /*
     * General use definition for button click step.
     * -------------------------------------------------------------------------------------------------------------
     * Parameters:
     * buttonId - pass in the buttonId for the button you'd like to press.
     * -------------------------------------------------------------------------------------------------------------
     */
    
    
    @When("I click the {string} button")
    public void iClickTheButton(String buttonId){
        //WebElement button = driver.findElement(By.id(buttonId));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));
        button.click();
    }

    /*
     * General use definition for selecting an option from a dropdown
     * -------------------------------------------------------------------------------------------------------------
     * Parameters:
     * option - the text for the option you'd like to select
     * dropdownId - the ID of the dropdown element you want to select for.
     * -------------------------------------------------------------------------------------------------------------
     * Notes:
     * This assumes that each option is dynamically loaded, so it uses the actual text of the option rather than any specific ID
     * This also assumes your dropdown element has an associated ID
     */

    @When("I select {string} from the {string} selection")
    public void iSelectFromTheSelection(String option, String dropdownId){

        //Wait until the dropdown is fully loaded and enabled and set it to variable: dropdown.
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(dropdownId)));

        //Select the correct option in the dropdown, ensure desired option is available
        Select select = new Select(dropdown);
        wait.until(ExpectedConditions.textToBePresentInElement(dropdown, option));
        select.selectByVisibleText(option);

    }

    /*
     * General use definition for checking page redirection.
     * -------------------------------------------------------------------------------------------------------------
     * Parameters:
     * pageName - the name of the page you have redirected to
     * -------------------------------------------------------------------------------------------------------------
     * Notes:
     * The page name must match the way it is written in Navigator.java
     * Grabs the url mapped to that pageName key as defined in Navigator.java
     */
    @Then("I am redirected to the {string} page")
    public void iAmRedirectedToThePage(String pageName){
        Assert.assertTrue(driver.getCurrentUrl().equals(navigator.getURL(pageName)));
    }
}
