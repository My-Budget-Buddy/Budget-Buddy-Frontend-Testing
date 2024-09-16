package com.skillstorm.Utilities.Navigator;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class Navigator {
//#region Static fields
    public static final String PGNAME_LANDING= "Landing";
    public static final String PGNAME_LOGIN= "Login";
    public static final String PGNAME_SIGNUP= "Signup";
    public static final String PGNAME_DASHBOARD= "Dashboard";
    public static final String PGNAME_ACCOUNTS= "Accounts";
    public static final String PGNAME_BUDGET= "Budget";
    public static final String PGNAME_SPENDING= "Spending";
    public static final String PGNAME_SPENDINGMONTH= "SpendingMonth";
    public static final String PGNAME_TRANSACTIONS= "Transactions";
    public static final String PGNAME_TRANSACTIONSHISTORY= "TransactionsHistory";
    public static final String PGNAME_TAX= "Tax";
    public static final String PGNAME_TAXEDITVIEW= "TaxEditView";
    public static final String PGNAME_TAXRESULTS= "TaxResults";
    public static final String PGNAME_ERROR= "Error";

    public static final String URL = "http://localhost:5173";

    public static final String URL_LANDING= URL + "/";
    public static final String URL_LOGIN= URL + "/login";
    public static final String URL_SIGNUP= URL + "/register";
    public static final String URL_DASHBOARD= URL + "/dashboard";
    public static final String URL_ACCOUNTS= URL + "/accounts";
    public static final String URL_BUDGET= URL + "/budgets";
    public static final String URL_SPENDING= URL + "/spending";
    public static final String URL_SPENDINGMONTH= URL + "/spending/:month";                   // HAS VARIABLE ":month"
    public static final String URL_TRANSACTIONS= URL + "/transactions";
    public static final String URL_TRANSACTIONSHISTORY= URL + "/transactions/:id";            // HAS VARIABLE ":id"
    public static final String URL_TAX= URL + "/tax";
    private static final String URL_TAX_ID = URL + "/tax/:returnId";                          // Note: not sure what this is supposed to be.
    public static final String URL_TAXEDITVIEW= URL + "/tax/:returnId/:formType/:formId";     // HAS VARIABLES
    public static final String URL_TAXRESULTS= URL + "/tax/:returnId/results";                // HAS VARIABLE ":returnId"
    public static final String URL_ERROR= URL + "/error"; // The error page is a catch-all for any bad path, this is a placeholder.
//#endregion

    Map<String, String> pageUrlMap = new HashMap<String, String>();
    Map<String, INavigate> pageMethodMap = new HashMap<String, INavigate>();
    private WebDriver driver;

    // --- CONSTRUCTORS --- 

    public Navigator(WebDriver driver){
        this.driver = driver;

        // Map page names to urls.
        pageUrlMap.put(PGNAME_ACCOUNTS, URL_ACCOUNTS);
        pageUrlMap.put(PGNAME_LOGIN, URL_LOGIN);
        pageUrlMap.put(PGNAME_LANDING, URL_LANDING);
        pageUrlMap.put(PGNAME_SIGNUP, URL_SIGNUP);
        pageUrlMap.put(PGNAME_DASHBOARD, URL_DASHBOARD);
        pageUrlMap.put(PGNAME_BUDGET, URL_BUDGET);
        pageUrlMap.put(PGNAME_SPENDING, URL_SPENDING);
        pageUrlMap.put(PGNAME_SPENDINGMONTH, URL_SPENDINGMONTH);
        pageUrlMap.put(PGNAME_TRANSACTIONS, URL_TRANSACTIONS);
        pageUrlMap.put(PGNAME_TRANSACTIONSHISTORY, URL_TRANSACTIONSHISTORY);
        pageUrlMap.put(PGNAME_TAX, URL_TAX);
        pageUrlMap.put(PGNAME_TAXEDITVIEW, URL_TAXEDITVIEW);
        pageUrlMap.put(PGNAME_TAXRESULTS, URL_TAXRESULTS);
        pageUrlMap.put(PGNAME_ERROR, URL_ERROR);

        // Map page names to navigation methods.
        pageMethodMap.put(PGNAME_ACCOUNTS, () -> navigateToAccounts());
        pageMethodMap.put(PGNAME_LOGIN, () -> navigateToLogin());
        pageMethodMap.put(PGNAME_LANDING, () -> navigateToLanding());
        pageMethodMap.put(PGNAME_SIGNUP, () -> navigateToSignup());
        pageMethodMap.put(PGNAME_DASHBOARD, () -> navigateToDashboard());
        pageMethodMap.put(PGNAME_BUDGET, () -> navigateToBudget());
        pageMethodMap.put(PGNAME_SPENDING, () -> navigateToSpending());
        pageMethodMap.put(PGNAME_SPENDINGMONTH, () -> navigateToSpendingMonth());
        pageMethodMap.put(PGNAME_TRANSACTIONS, () -> navigateToTransactions());
        pageMethodMap.put(PGNAME_TRANSACTIONSHISTORY, () -> navigateToTransactionsHistory());
        pageMethodMap.put(PGNAME_TAX, () -> navigateToTax());
        pageMethodMap.put(PGNAME_TAXEDITVIEW, () -> navigateToTaxEditView());
        pageMethodMap.put(PGNAME_TAXRESULTS, () -> navigateToTaxResults());
        pageMethodMap.put(PGNAME_ERROR, () -> navigateToError());
    }

    // --- METHODS ---

    /**
     * Resolves a page name into a navigation method. The page specified
     * should always be loaded after this method is called.
     * @param pageName Name of the page to navigate to. Must be one of the static PGNAME Strings.
     */
    public void navigateTo(String pageName){
        if(pageMethodMap.containsKey(pageName)) pageMethodMap.get(pageName).navigate();
        else throw new IllegalArgumentException("Page '" + pageName + "' does not exist.");
    }

    /**
     * Calls driver.get for a specified page.
     * @param pageName  Name of the page to load.
     */
    public void loadPageWithURL(String pageName){
        driver.get(getURL(pageName));
    }

    /**
     * Retrieves a URL from the page name to URL mapping.
     * @param pageName  Name of the page.
     * @return          URL of the page.
     */
    public String getURL(String pageName){
        return pageUrlMap.get(pageName);
    }

//#region Logged out page navigation.

    private void navigateToSignup() {
        loadPageWithURL(PGNAME_SIGNUP); // Navigate
    }

    private void navigateToLogin() {
        loadPageWithURL(PGNAME_LOGIN);  // Navigate
    }

    // NOTE: THERE'S A LOGGED-IN VERSION OF LANDING. MAYBE HAVE A SECOND METHOD.
    private void navigateToLanding() {
        loadPageWithURL(PGNAME_LANDING);    // Navigate
    }

    private void navigateToError() {
        loadPageWithURL(PGNAME_ERROR);  // Navigate
    }

//#endregion

//#region Logged in page navigation.

    private void navigateToLoginAndLogin(){

    }

    private void navigateToTransactionsHistory() {
        
        // Login
        // Navigate to Transactions
        // Navigate to History of a transaction.

        throw new UnsupportedOperationException("Unimplemented method 'navigateToTransactionsHistory'");
    }

    private void navigateToTransactions() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToTransactions'");
    }

    private void navigateToTaxResults() {
        
        // Login
        // Navigate to taxes
        // Navigate to the result of taxes

        throw new UnsupportedOperationException("Unimplemented method 'navigateToTaxResults'");
    }

    private void navigateToTaxEditView() {
        
        // Login
        // Navigate to taxes
        // Navigate to editing taxes.

        throw new UnsupportedOperationException("Unimplemented method 'navigateToTaxEditView'");
    }

    private void navigateToTax() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToTax'");
    }

    private void navigateToSpendingMonth() {
        
        // Login
        // Navigate to spending
        // Select specific month

        throw new UnsupportedOperationException("Unimplemented method 'navigateToSpendingMonth'");
    }

    private void navigateToSpending() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToSpending'");
    }

    private void navigateToDashboard() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToDashboard'");
    }

    private void navigateToBudget() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToBudget'");
    }

    private void navigateToAccounts() {
        
        // Login
        // Navigate

        throw new UnsupportedOperationException("Unimplemented method 'navigateToAccounts'");
    }

//#endregion
}