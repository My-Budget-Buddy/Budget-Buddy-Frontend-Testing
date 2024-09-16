/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents a form with any number of input fields
 *      and one submit button that may be on a page.
 */

package com.skillstorm.PageObjects.Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.skillstorm.PageObjects.Interfaces.Component;

import javafx.util.Pair;

public class Form implements Component{
    private WebDriver driver;

    private HashMap<String, WebElement> inputs = new HashMap<>();
    private Pair<String, WebElement> btnSubmit;

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
        return btnSubmit.getValue();
    } 

    /**
     * Sets the submit button.
     * @param btnSubmit New submit button.
     */
    public void setBtnSubmit(WebElement btnSubmit, String name){
        this.btnSubmit = new Pair<String,WebElement>(name, btnSubmit);
    }

    /**
     * Submits the form.
     */
    public void submit(){
        btnSubmit.getValue().click();
    }

    /**
     * Returns null since Form has no child components.
     */
    @Override
    public List<Component> getChildComponents() {
        return null;    // Form only has WebElements, no Components
    }

    /**
     * Returns null since Form has no child components.
     */
    @Override
    public Component getChildComponent(String name) {
        return null;    // Form only has WebElements, no Components
    }

    /**
     * Returns the form's WebElements. 
     */
    @Override
    public List<WebElement> getWebElements() {
        // Local webelements
        List<WebElement> webElements = new ArrayList<WebElement>(inputs.values());
        webElements.add(getBtnSubmit());

        return webElements;
    }

    /**
     * Returns a named WebElement.
     */
    @Override
    public WebElement getWebElement(String name) {
        // Check this component
        if(inputs.containsKey(name)) return inputs.get(name);
        if(name.equals(btnSubmit.getKey())) return btnSubmit.getValue();

        // Not found.
        return null;
    }
}