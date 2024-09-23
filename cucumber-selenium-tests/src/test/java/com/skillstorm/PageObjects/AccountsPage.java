package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.beust.ah.A;
import com.skillstorm.PageObjects.Components.Form;
import com.skillstorm.PageObjects.Components.Navbar.DashboardNavbar;
import com.skillstorm.PageObjects.Interfaces.Component;


public class AccountsPage extends Page {
//#region Static fields

    //Names
    public static final String NAME_CMP_DASHBOARD_NAVBAR = "Dashboard Navbar";
    public static final String NAME_ACCORDION_CHECKING_BTN = "Checkings";
    public static final String NAME_ACCORDION_SAVINGS_BTN = "Savings";
    public static final String NAME_ACCORDION_CREDIT_CARDS_BTN = "Credit Cards"; 
    public static final String NAME_ACCORDION_INVESTMENTS_BTN = "Investments";  
    public static final String NAME_CHECKING_TABLE = "Checkings List";
    public static final String NAME_SAVINGS_TABLE = "Savings List";
    public static final String NAME_CREDIT_CARDS_TABLE = "Credit Card List";
    public static final String NAME_INVESTMENTS_TABLE = "Investments List";
    public static final String NAME_BTN_ADD_ACCOUNT = "Add Account";
    public static final String NAME_BTN_GET_REPORT = "Get Report";
    public static final String NAME_NETCASH_BAR = "Net Cash Bar";
    public static final String NAME_CREDIT_SCORE_POPUP = "Credit Score Pop Up";
    public static final String NAME_CREDIT_SCORE_POPUP_TITLE = "Credit Score Report";
    

    // Button IDs
    private final String BTN_ADD_ACCOUNT_LOCATOR = "button[aria-controls='account-modal']";
    private final String BTN_GET_REPORT_LOCATOR = "button[aria-controls='credit-score-modal']";
    private final String ACCORDION_CHECKING_LOCATOR = "button[data-testid='accordionButton_Checking']";
    private final String ACCORDION_SAVINGS_LOCATOR = "button[data-testid='accordionButton_savings']";
    private final String ACCORDION_CREDIT_CARDS_LOCATOR = "button[data-testid='accordionButton_credit-cards']";
    private final String ACCORDION_INVESTMENTS_LOCATOR = "button[data-testid='accordionButton_investments']";

    // Table IDs
    private final String ACCORDION_LIST_CHECKING_ID = "Checking";
    private final String ACCORDION_LIST_SAVINGS_ID = "savings";
    private final String ACCORDION_LIST_CREDIT_CARDS_ID = "credit-cards";
    private final String ACCORDION_LIST_INVESTMENTS_TABLE_ID = "investments";

    // Form IDs
    private final String FORM_ADD_ACCOUNT_ID = "form-add-account"; 
    private final String FORM_INSTITUTION_NAME_ID = "account-name";
    private final String FORM_ACCOUNT_TYPE_ID = "account-type";
    private final String FORM_ACCOUNT_NUMBER_ID = "account-num";
    private final String FORM_ROUTING_NUMBER_ID = "routing-num";
    private final String FORM_BALANCE_ID = "account-balance";
    private final String FORM_SUMBIT_BTN_ID = "submit-add-account";

    // Other WebElement IDs
    public static final String NETCASH_BAR_ID = "netCash-gauge";
    public static final String CREDIT_SCORE_POPUP_ID = "credit-score-modal-heading";

    //Element Attributes
    public static final String NETCASH_BAR_GREEN = "rgb(82, 178, 2)";
    public static final String NETCASH_BAR_RED = "rgb(178, 2, 2)";
    public static final List<String> ACCOUNT_TYPE_OPTIONS = Arrays.asList(
        "- Select -",
         NAME_ACCORDION_CHECKING_BTN,
         NAME_ACCORDION_SAVINGS_BTN, 
         NAME_ACCORDION_CREDIT_CARDS_BTN, 
         NAME_ACCORDION_INVESTMENTS_BTN
    );

//#END Static Fields

//#region Attributes

    Map<String, WebElement> nameElementMap = new HashMap<String, WebElement>();

    //Web Elements - Buttons
    @FindBy(css = ACCORDION_CHECKING_LOCATOR)
    private WebElement btnAccordianChecking;

    @FindBy(css = ACCORDION_SAVINGS_LOCATOR)
    private WebElement btnAccordianSavings;
    
    @FindBy(css = ACCORDION_CREDIT_CARDS_LOCATOR)
    private WebElement btnAccordianCreditCards;
    
    @FindBy(css = ACCORDION_INVESTMENTS_LOCATOR)
    private WebElement btnAccordianInvestments;

    @FindBy(css = BTN_ADD_ACCOUNT_LOCATOR)
    private WebElement btnAddAccount;

    @FindBy(css = BTN_GET_REPORT_LOCATOR)
    private WebElement btnGetReport;

    //Web Elements - Hidden Tables/Lists
    @FindBy(id = ACCORDION_LIST_CHECKING_ID)
    private WebElement tableChecking;

    @FindBy(id = ACCORDION_LIST_SAVINGS_ID)
    private WebElement tableSavings;

    @FindBy(id = ACCORDION_LIST_CREDIT_CARDS_ID)
    private WebElement tableCreditCards;

    @FindBy(id = ACCORDION_LIST_INVESTMENTS_TABLE_ID)
    private WebElement tableInvestments;


    //Web Elements - Other
    @FindBy(id = NETCASH_BAR_ID)
    private WebElement netCashBar;

    @FindBy(id = CREDIT_SCORE_POPUP_ID)
    private WebElement creditScorePopUp;

    //Child Components
    DashboardNavbar navbar;

//#END Attributes

    /////////// CONSTRUCTORS //////////////////////////
    public AccountsPage(WebDriver driver) {
        super(driver);

        // Initialize Child Componenets
        navbar = new DashboardNavbar(driver);

        // Initialize WebElements
        PageFactory.initElements(driver, this);

        // Map WebElements
        nameElementMap.put(NAME_ACCORDION_CHECKING_BTN, btnAccordianChecking);
        nameElementMap.put(NAME_ACCORDION_SAVINGS_BTN, btnAccordianSavings);
        nameElementMap.put(NAME_ACCORDION_CREDIT_CARDS_BTN, btnAccordianCreditCards);
        nameElementMap.put(NAME_ACCORDION_INVESTMENTS_BTN, btnAccordianInvestments);
        nameElementMap.put(NAME_CHECKING_TABLE, tableChecking);
        nameElementMap.put(NAME_SAVINGS_TABLE, tableSavings);
        nameElementMap.put(NAME_CREDIT_CARDS_TABLE, tableCreditCards);
        nameElementMap.put(NAME_INVESTMENTS_TABLE, tableInvestments);
        nameElementMap.put(NAME_BTN_ADD_ACCOUNT, btnAddAccount);
        nameElementMap.put(NAME_BTN_GET_REPORT, btnGetReport);
        nameElementMap.put(NAME_NETCASH_BAR, netCashBar);
        nameElementMap.put(NAME_CREDIT_SCORE_POPUP, creditScorePopUp);
        
    }


    /////////// FUNCTIONS /////////////////////

    /**
     * Retrieves the list name associated with its accordion header name.
     * @param btnAccordionName - name of the button to that displays the accordian list.
     * @return - name of the accordian list associated with the button.
     */
    public String getListName(String btnAccordionName) {
        if (btnAccordionName == null) return null;
        switch(btnAccordionName) {
            case NAME_ACCORDION_CHECKING_BTN:
                return NAME_CHECKING_TABLE;
            case NAME_ACCORDION_SAVINGS_BTN:
                return NAME_SAVINGS_TABLE;
            case NAME_ACCORDION_CREDIT_CARDS_BTN:
                return NAME_CREDIT_CARDS_TABLE;
            case NAME_ACCORDION_INVESTMENTS_BTN:
                return NAME_INVESTMENTS_TABLE;
            default:
                return null;
        }
    }

    public String getNetCashBarColor() {
        //Grab the container surrounding the visual element of the net cash bar
        WebElement netCashBar_container = netCashBar.findElements(By.xpath("./child::*")).get(0);

        //Inside the container is the following elements: title, desc, path, path, g
        List<WebElement> components = netCashBar_container.findElements(By.xpath("./child::*"));
        //The Second path element is the one that contains the part of the gauge that has color

        try {
            //Try to find that color
            WebElement barWithColor = components.get(3);
            if (barWithColor.getTagName().equals("path")) {
                return barWithColor.getCssValue("fill");
            } else { return "Color Not Found"; }
        } catch (Exception e) {
            //If the color is not found, return null
            return "Something Went Wrong Finding Color: " + e.getMessage();
        }
    }

    public Double getNetCash () {
        //Get the title of the current spending chart
        String netCashText = netCashBar.getText();
        //Get the number from the Net Cash Element and remove all the commas, keeping the decimal
        netCashText = netCashText.substring(netCashText.lastIndexOf("$") + 1).replaceAll(",","").trim();
        return Double.parseDouble(netCashText);
    }

    public void addAccount(String accountType, String institutionName, String accountNumber, String routing, String balance ) {
        //Wait for the modal to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FORM_ADD_ACCOUNT_ID)));
        
        // Load in the form
        WebElement form = driver.findElement(By.id(FORM_ADD_ACCOUNT_ID));
        
        //Set the input fields
        Select accountTypeDropdown = new Select(form.findElement(By.id(FORM_ACCOUNT_TYPE_ID)));
        WebElement inputInstitutionName = form.findElement(By.id(FORM_INSTITUTION_NAME_ID));
        WebElement inputAccountNumber = form.findElement(By.id(FORM_ACCOUNT_NUMBER_ID));
        WebElement inputBalance = form.findElement(By.id(FORM_BALANCE_ID));

        //Fill in the input fields
        accountTypeDropdown.selectByIndex(ACCOUNT_TYPE_OPTIONS.indexOf(accountType));
        inputInstitutionName.sendKeys(institutionName);
        inputAccountNumber.sendKeys(accountNumber);
        inputBalance.sendKeys(balance);

        //Handle Routing Number if Present
        if (!routing.equals("0") && !accountType.equals("Credit Card")) {
            WebElement inputRoutingNumber = form.findElement(By.id(FORM_ROUTING_NUMBER_ID));
            inputRoutingNumber.sendKeys(routing);
        }
        //Click the submit button
        form.findElement(By.id(FORM_SUMBIT_BTN_ID)).click();
    }

    /**
     * Checks if a particualr account with specific information is present on the page
     * @param accountName - Name of the account
     * @param accountType - Type of the account
     * @param accountNumber - Account Number
     * @param balance - Balance of the account
     * @return - True if the account is present, false otherwise
     */
    public WebElement checkForAccount( String accountType, String accountName, String accountNumber, String balance) {
        // first check if the list is displayed, 
        String listName = getListName(accountType);
        Assert.assertTrue(listName != null, "Account Type not found: " + accountType);
        
        // and if not, open the correct accordion
        if (!getWebElement(listName).isDisplayed()) clickButton(accountType);
        Assert.assertTrue(getWebElement(listName).isDisplayed(), "Something went wrong displaying account list: " + listName);
      
        //Get the list of accounts by continuing to drill down into the children of the container until we find the correct divs
        WebElement listContainer = getWebElement(listName);
        while (!listContainer.getAttribute("class").contains("grid-container")) {
            //get all the child components of the current variable
            List<WebElement> temp = listContainer.findElements(By.xpath("./child::*"));
            // if the container has something else in it or nothing in it, then we have a problem
            Assert.assertTrue(temp.size() == 1, "Container has incorrect number of children: " + temp.size());
            // otherwise, set the accountList to the first child of the current accountList
            listContainer = listContainer.findElements(By.xpath("./child::*")).get(0);
        }

        //Get the list of all the acounts in the container ()
        List<WebElement> accounts = listContainer.findElements(By.xpath("./child::*"));
        
        // Modify the account info to match the format that would be shown on the webpage
        accountNumber = accountNumber.substring(accountNumber.length()-4);
        balance = String.format("$" + "%,.2f", Double.parseDouble(balance));

        //Check if the account is present
        for (WebElement account : accounts) {
            String accountText = account.getText();
            if (
                accountText.contains(accountName) && 
                accountText.contains(accountNumber) && 
                accountText.contains(balance)
            ) { return account; }
        }
        return null;
    }

    /**
     * Attempts to delete an account from the page
     * @param account - WebElement of the account to delete
     */
    public void deleteAccount(WebElement account) {
        //Get the last element in the account, which is the container for the delete button
        WebElement deleteButton = account.findElement(By.xpath("./child::*[last()]"))
        // Then get the button from the container
        .findElement(By.xpath("./child::*"));

        // Make sure this the delete button
        Assert.assertTrue(deleteButton.getTagName().equals("button"), "Delete Button not found");
        
        // Then Click it!!!
        deleteButton.click();
    }

    ////////////// OVERRIDES ///////////////////////
    /**
     * Maps the button names in the static fields to a click action.
     * @param name Name of the button to click.
     */
    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);
        if(button == null) throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        button.click();
    }

    /**
     * Retrieves all Components directly under this Component.
     */
    @Override
    public List<Component> getChildComponents() {
        return Arrays.asList(navbar);
    }

    /**
     * Retrieves a component given a name. Does not search child components.
     * @param name  Name of the component to retrieve.
     * @return      Component tied to that name, or null if not found.
     */
    @Override
    public Component getChildComponent(String name) {
        if(name.equals(NAME_CMP_DASHBOARD_NAVBAR)) return navbar;  // Directly exists here

        // Couldn't find.
        return null;
    }

    /**
     * Retrieves all WebElements in this component and child components.
     */
    @Override
    public List<WebElement> getWebElements() {
        // Get all web elements in this component
        List<WebElement> webElements = new ArrayList<WebElement>();
        for (WebElement webElement : nameElementMap.values()) {
            webElements.add(webElement);
        }

        // Add child components' web elements
        for (Component component : getChildComponents()) {
            if (component != null)
            for (WebElement webElement : component.getWebElements()) {
                webElements.add(webElement);
            }
        }

        return webElements;
    }


    /**
     * Retrieves a WebElement given a name. Searches child components as well.
     * @param name  Name of the WebElement to search for.
     * @return      WebElement tied to that name, or null if not found.
     */
    @Override
    public WebElement getWebElement(String name) {
        // Check in sub-components
        for (Component component : getChildComponents()) {
            if(component != null) {
                WebElement found = component.getWebElement(name);
                if(found != null) return found;
            }
        }

        //check in main componenets
        return nameElementMap.get(name);
    }

    /**
     * Returns all buttons directly inside of this component.
     */
    @Override
    public List<WebElement> getButtons() {
        List<WebElement> buttons = Arrays.asList(
            btnAccordianChecking,
            btnAccordianSavings,
            btnAccordianCreditCards,
            btnAccordianInvestments,
            btnAddAccount
        );
        
        return buttons;
    }

    
}
