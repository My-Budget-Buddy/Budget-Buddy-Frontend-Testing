package com.skillstorm.PageObjects;




import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaxPage {
    private WebDriver driver;

    public TaxPage(WebDriver driver) {
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
    private Select PIStateNameField;

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

    @FindBy(id = "deduction-Add-button")
    private WebElement deductAddButton;

    @FindBy(id = "deduction-submit-button")
    private WebElement deductSubmitButton;

    @FindBy(name = "deddeduction")
    private Select deductDeductionTypeField;

    @FindBy(name = "dedamountSpent")
    private WebElement deductDeductionAmountField;

    //webelements from tax estimator form - Review and submit


    @FindBy(id = "deduction-save-button")
    private WebElement reviewW2PreviousButton;

    @FindBy(id = "deduction-save-button")
    private WebElement reviewW2NextButton;

    @FindBy(id = "deduction-save-button")
    private WebElement reviewDeductPreviousButton;

    @FindBy(id = "deduction-save-button")
    private WebElement reviewDeductNextButton;

    @FindBy(id = "deduction-save-button")
    private WebElement reviewSaveButton;


    //Login Page
    private static final String url = "http://localhost:5173/login";

    public void getMain() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    //login to site
    public void login() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailField.sendKeys("joseph.sam@gmail.com");
        passwordField.sendKeys("password1");
        loginButton.click();

    }
    //Navigate to tax page
    public void NavigateToTaxPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        taxLink.click();
    }

    public void checkForListOfExistingTaxEstimationRecords() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        taxLink.click();

    }


    public boolean checkIfDeleteWorked(){
        return driver.findElements(By.xpath("//div[text()='SINGLE']")).size() > 0;
    }

    public void fillOutPersonalInformation(String firstName, String lastName, String streetAddress,
                                            String city,String zipcode, String DOB, String SSN,String state){
            PIFirstNameField.sendKeys(firstName);
            PILastNameField.sendKeys(lastName);
            PIStreetNameField.sendKeys(streetAddress);
            PICityNameField.sendKeys(city);
            PIZipcodeField.sendKeys(zipcode);
            PIStateNameField.selectByValue(state);
            PIDOBField.sendKeys(DOB);
            PIStateNameField.selectByValue(SSN);
            PISaveButton.click();
            nextButton.click();
    }

    public void fillOutW2Information(String state,String employer,String wages,String federalTax,String stateTax,String SSNtax,
                                         String medicareTax){
            w2AddButton.click();
            w2EditButton.click();
            w2StateField.sendKeys(state);
            w2EmployerField.sendKeys(employer);
            w2WageField.sendKeys(wages);
            w2FederalIncomeTaxWithheldField.sendKeys(federalTax);
            w2StateIncomeTaxWithheldField.sendKeys(stateTax);
            w2SocialSecurityTaxWithheldField.sendKeys(SSNtax);
            w2medicareTaxWithheldField.sendKeys(medicareTax);
            w2SaveButton.click();
            nextButton.click();
    }

    public void fillOutOtherIncomeInformation(String longTermCapitalGains,String shortTermGains,String otherInvestments,
                                                String netBusinessIncome,String additionalIncome){
                
            OILongTermCaptialGainsField.sendKeys(longTermCapitalGains);
            OIShortTermCaptialGainsField.sendKeys(shortTermGains);
            OIOtherInvestmentIncomeField.sendKeys(netBusinessIncome);
            OIAdditionalIncomeField.sendKeys(additionalIncome);
            OISaveButton.click();
            nextButton.click();
     }

     public void fillOutDeductions(String deductionType,String deductionAmount ){
                deductAddButton.click();
                deductEditButton.click();
                deductDeductionTypeField.selectByValue(deductionAmount);
                deductDeductionAmountField.sendKeys(deductionAmount);
                deductSaveButton.click();
                nextButton.click();
     }

    public boolean CheckForEstimationResultsPage(){

        return driver.findElements(By.xpath("//div[text()='SINGLE']")).size() > 0;
    }

}
