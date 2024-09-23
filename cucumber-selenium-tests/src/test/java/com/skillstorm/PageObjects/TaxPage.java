package com.skillstorm.PageObjects;




import java.time.Duration;
import java.util.List;




import com.skillstorm.PageObjects.Interfaces.Component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class TaxPage extends Page {
    private WebDriver driver;

    public TaxPage(WebDriver driver) {
        super(driver); // Explicitly invoke the constructor of the superclass
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    //temporarily logging in untill landingPage is created
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/main/div/div[2]/div/div/form/fieldset/button[2]")
    private WebElement loginButton;

    @FindBy(linkText = "Tax")
    private WebElement taxLink;

    //webelements from tax page 
    @FindBy(id = "edit-button")
    private WebElement MainEditButton;

    @FindBy(id = "delete-button")
    private WebElement MainDeleteButton;

    //webelement that appears with tax estimator form but stay the same for each setp
    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "prev")
    private WebElement previousButton;

    //webelements from tax estimator form - personal Identification step

    @FindBy(name = "firstName")
    private WebElement PIFirstNameField;

    @FindBy(name = "lastName")
    private WebElement PILastNameField;

    @FindBy(name = "address")
    private WebElement PIStreetNameField;

    @FindBy(name = "city")
    private WebElement PICityNameField;

    @FindBy(name = "state")
    private WebElement PIStateNameField;

    @FindBy(name = "zip")
    private WebElement PIZipcodeField;

    @FindBy(name = "dateOfBirth")
    private WebElement PIDOBField;

    @FindBy(name = "ssn")
    private WebElement PISSNField;

    @FindBy(id = "pi-save-button")
    private WebElement PISaveButton;

    //webelements from tax estimator form - File w2s

    @FindBy(id = "w2-add-button")
    private WebElement w2AddButton;

    @FindBy(id = "w2-submit-button")
    private WebElement w2SubmitButton;

    @FindBy(id = "w2-edit-button")
    private WebElement w2EditButton;

    @FindBy(id = "w2-delete-button")
    private WebElement w2DeleteButton;

    @FindBy(id = "w2-save-button")
    private WebElement w2SaveButton;

    @FindBy(name = "w2state")
    private WebElement w2StateField;

    @FindBy(name = "w2employer")
    private WebElement w2EmployerField;

    @FindBy(name = "w2wages")
    private WebElement w2WageField;

    @FindBy(name = "w2federalIncomeTaxWithheld")
    private WebElement w2FederalIncomeTaxWithheldField;

    @FindBy(name = "w2federalIncomeTaxWithheld")
    private WebElement w2StateIncomeTaxWithheldField;

    @FindBy(name = "w2socialSecurityTaxWithheld")
    private WebElement w2SocialSecurityTaxWithheldField;

    @FindBy(name = "w2medicareTaxWithheld")
    private WebElement w2medicareTaxWithheldField;

    //webelements from tax estimator form - Other Income

    @FindBy(id = "oi-save-button")
    private WebElement OISaveButton;

    @FindBy(name = "oilongTermCapitalGains")
    private WebElement OILongTermCaptialGainsField;

    @FindBy(name = "oishortTermCapitalGains")
    private WebElement OIShortTermCaptialGainsField;

    @FindBy(name = "oiotherInvestmentIncome")
    private WebElement OIOtherInvestmentIncomeField;

    @FindBy(name = "oinetBusinessIncome")
    private WebElement OINetBusinessIncomeField;

    @FindBy(name = "oiadditionalIncome")
    private WebElement OIAdditionalIncomeField;

    //webelements from tax estimator form - Deductions

    @FindBy(id = "deduction-save-button")
    private WebElement deductSaveButton;

    @FindBy(id = "deduction-edit-button")
    private WebElement deductEditButton;

    @FindBy(id = "deduction-delete-button")
    private WebElement deductDeleteButton;

    @FindBy(id = "deduction-add-button")
    private WebElement deductAddButton;

    @FindBy(id = "deduction-submit-button")
    private WebElement deductSubmitButton;

    @FindBy(name = "deddeduction")
    private WebElement deductDeductionTypeField;

    @FindBy(name = "dedamountSpent")
    private WebElement deductDeductionAmountField;

    //webelements from tax estimator form - Review and submit


    @FindBy(id = "dreview-w2-prev-button")
    private WebElement reviewW2PreviousButton;

    @FindBy(id = "review-w2-next-button")
    private WebElement reviewW2NextButton;

    @FindBy(id = "review-deduct-prev-button")
    private WebElement reviewDeductPreviousButton;

    @FindBy(id = "review-deduct-next-button")
    private WebElement reviewDeductNextButton;

    @FindBy(id = "review-submit-button")
    private WebElement reviewSubmitButton;

    //Buttons across top of tax page

    @FindBy(id = "file-taxes")
    private WebElement fileTaxesButton;

    @FindBy(id = "estimate-refund")
    private WebElement estimateRefundButton;

    @FindBy(id = "document-checklist")
    private WebElement documentChecklistButton;

    @FindBy(id = "refund-planning")
    private WebElement refundPlanningButton;



    //Login Page
    // private static final String url = "http://localhost:5173/login";

    // public void getMain() {
    //     try {
    //         Thread.sleep(1000);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     this.driver.get(url);
    // }

    //login to site
    // public void login() {
    //     try {
    //         Thread.sleep(1000);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     emailField.sendKeys("joseph.sam@gmail.com");
    //     passwordField.sendKeys("password1");
    //     loginButton.click();

    // }
    // //Navigate to tax page
    // public void NavigateToTaxPage() {
    //     try {
    //         Thread.sleep(1000);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
        
    //     taxLink.click();
    // }

    public boolean checkForListOfExistingTaxEstimationRecords() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return driver.findElements(By.xpath("//td[contains(text(),'SINGLE')]")).size() > 0;

    }

    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);

        if(button == null) throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    wait.until(d -> button.isDisplayed());
    try {
        Thread.sleep(300);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        button.click();
        
    }

    public void clickDeleteOrEdit(String button){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickButton(button);

    }

    public void assureTaxRecordAppears(){
        clickButton("fileTaxesButton");
        clickButton("estimateRefundButton");
        clickButton("documentChecklistButton");
        clickButton("refundPlanningButton");
        driver.navigate().refresh();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public boolean checkIfDeleteWorked(){
        driver.navigate().refresh();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElements(By.xpath("//td[contains(text(),'SINGLE')]")).size() > 0;
    }

    public void clickMainEditButton(String buttonName){
        clickButton(buttonName);
    }

    public void fillOutPersonalInformation(String firstName, String lastName, String streetAddress,
                                            String city,String zipcode, String DOB, String SSN,String state){
            PIFirstNameField.sendKeys(firstName);
            PILastNameField.sendKeys(lastName);
            PIStreetNameField.sendKeys(streetAddress);
            PICityNameField.sendKeys(city);
            PIZipcodeField.sendKeys(zipcode);
            PIStateNameField.sendKeys(state);
            PIDOBField.sendKeys(DOB);
            PISSNField.sendKeys(SSN);
            
    }

    public void clickPIsubmitButton(String pisave, String next){
        clickButton(pisave);
            clickButton(next);
    }

    public void clickW2StarterButtons(String w2add, String w2edit){
        clickButton(w2add);
        clickButton(w2edit);
    }
    

    public void fillOutW2Information(String state,String employer,String wages,String federalTax,String stateTax,String SSNtax,
                                         String medicareTax){
            w2StateField.sendKeys(state);
            w2EmployerField.sendKeys(employer);
            w2WageField.sendKeys(wages);
            w2FederalIncomeTaxWithheldField.sendKeys(federalTax);
            w2StateIncomeTaxWithheldField.sendKeys(stateTax);
            w2SocialSecurityTaxWithheldField.sendKeys(SSNtax);
            w2medicareTaxWithheldField.sendKeys(medicareTax);
            
    }

    public void clickW2submitButton(String w2submit, String next){
        clickButton(w2submit);
        clickButton(next);
    }

    public void fillOutOtherIncomeInformation(String longTermCapitalGains,String shortTermGains,String otherInvestments,
                                                String netBusinessIncome,String additionalIncome){
                
            OILongTermCaptialGainsField.sendKeys(longTermCapitalGains);
            OIShortTermCaptialGainsField.sendKeys(shortTermGains);
            OINetBusinessIncomeField.sendKeys(netBusinessIncome);
            OIOtherInvestmentIncomeField.sendKeys(otherInvestments);
            OIAdditionalIncomeField.sendKeys(additionalIncome);
     }

     public void clickOtherIncomeSubmitButton(String oisave, String next){
        clickButton(oisave);
        clickButton(next);
     }

     public void clickDeductionsStarterButtons(String deductAdd, String deductEdit){
        clickButton(deductAdd);
        clickButton(deductEdit);
     }

     public void fillOutDeductions(String deductionType,String deductionAmount ){
                deductDeductionTypeField.sendKeys(deductionType);
                deductDeductionAmountField.sendKeys(deductionAmount);
     }

     public void clickDeductionsSubmitButton(String deductSubmit, String next){
        clickButton(deductSubmit);
        clickButton(next);
     }

     public void clickReviewAndSubmit(String reviewSubmit){
        clickButton(reviewSubmit);
     }

    public boolean CheckForEstimationResultsPage(){
        
        return driver.findElements(By.xpath("//div[contains(text(),'Congratulations')]")).size() > 0;

        
    }


    @Override
    public WebElement getWebElement(String name) {
        Map<String, WebElement> buttonElements = new HashMap<>();
        buttonElements.put("mainEditButton", MainEditButton);
        buttonElements.put("mainDeleteButton", MainDeleteButton);
        buttonElements.put("nextButton", nextButton);
        buttonElements.put("previousButton", previousButton);
        buttonElements.put("PISaveButton", PISaveButton);
        buttonElements.put("w2AddButton", w2AddButton);
        buttonElements.put("w2SubmitButton", w2SubmitButton);
        buttonElements.put("w2EditButton", w2EditButton);
        buttonElements.put("w2DeleteButton", w2DeleteButton);
        buttonElements.put("w2SaveButton", w2SaveButton);
        buttonElements.put("OISaveButton", OISaveButton);
        buttonElements.put("deductSaveButton", deductSaveButton);
        buttonElements.put("deductEditButton", deductEditButton);
        buttonElements.put("deductDeleteButton", deductDeleteButton);
        buttonElements.put("deductAddButton", deductAddButton);
        buttonElements.put("deductSubmitButton", deductSubmitButton);
        buttonElements.put("reviewW2PreviousButton", reviewW2PreviousButton);
        buttonElements.put("reviewW2NextButton", reviewW2NextButton);
        buttonElements.put("reviewDeductPreviousButton", reviewDeductPreviousButton);
        buttonElements.put("reviewDeductNextButton", reviewDeductNextButton);
        buttonElements.put("reviewSubmitButton", reviewSubmitButton);
        buttonElements.put("fileTaxesButton", fileTaxesButton);
        buttonElements.put("estimateRefundButton", estimateRefundButton);
        buttonElements.put("documentChecklistButton", documentChecklistButton);
        buttonElements.put("refundPlanningButton", refundPlanningButton);


        return buttonElements.get(name);
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
    public List<WebElement> getButtons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getButtons'");
    }

    @Override
    public List<WebElement> getWebElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWebElements'");
    }

    

}
