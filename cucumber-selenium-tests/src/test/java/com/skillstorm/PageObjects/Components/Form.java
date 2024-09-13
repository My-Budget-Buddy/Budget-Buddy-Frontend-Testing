/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents a form with any number of input fields
 *      and one submit button that may be on a page.
 */

package com.skillstorm.PageObjects.Components;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.skillstorm.PageObjects.Interfaces.Component;

public class Form implements Component{
    private WebDriver driver;

    private HashMap<String, WebElement> inputs = new HashMap<>();
    private WebElement btnSubmit;

    public Form(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Stores an element as a field, or overwrites an existing field.
     * @param element       Element to store.
     * @param fieldName     Name of the field.
     */
    public void setInput(WebElement element, String fieldName){
        inputs.put(fieldName, element);
    }

    /**
     * Stores an element as a field, or overwrites an existing field.
     * @param elementId ID of the element to store
     * @param fieldName What to store the field as.
     */
    public void setInput(String elementId, String fieldName){
        WebElement element = driver.findElement(By.id(elementId));
        setInput(element, fieldName);
    }

    /**
     * Removes a field from the stored fields.
     * @param fieldName Field to remove.
     */
    public void removeInput(String fieldName){
        inputs.remove(fieldName);
    }

    /**
     * Retrieves a stored field.
     * @param fieldName Field to retrieve.
     * @return          Field.
     */
    public WebElement getInputField(String fieldName){
        return inputs.get(fieldName);
    }

    /**
     * Sends an input sequence to a field.
     * @param fieldName Field to send to.
     * @param input     Input to send.
     */
    public void sendInput(String fieldName, String input){
        inputs.get(fieldName).sendKeys(input);
    }

    /**
     * Retrives the submit button.
     * @return  Submit button.
     */
    public WebElement getBtnSubmit(){
        return btnSubmit;
    } 

    /**
     * Sets the submit button.
     * @param btnSubmit New submit button.
     */
    public void setBtnSubmit(WebElement btnSubmit){
        this.btnSubmit = btnSubmit;
    }

    /**
     * Submits the form.
     */
    public void submit(){
        btnSubmit.click();
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
