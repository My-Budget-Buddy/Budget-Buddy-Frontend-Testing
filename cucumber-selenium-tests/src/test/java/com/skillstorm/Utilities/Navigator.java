package com.skillstorm.Utilities;

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

    public static final String URL = "";
    public static final String URL_LANDING= "";
    public static final String URL_LOGIN= "http://localhost:5173/login";
    public static final String URL_SIGNUP= "";
    public static final String URL_DASHBOARD= "";
    public static final String URL_ACCOUNTS= "";
    public static final String URL_BUDGET= "";
    public static final String URL_SPENDING= "";
    public static final String URL_SPENDINGMONTH= "";
    public static final String URL_TRANSACTIONS= "http://localhost:5173/dashboard/transactions";
    public static final String URL_TRANSACTIONSHISTORY= "";
    public static final String URL_TAX= "";
    public static final String URL_TAXEDITVIEW= "";
    public static final String URL_TAXRESULTS= "";
    public static final String URL_ERROR= "";
//#endregion

    Map<String, String> pageUrlMap = new HashMap<>();
    private WebDriver driver;

    public Navigator(WebDriver driver){
        this.driver = driver;
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
    }

    /**
     * Resolves a page name into a navigation method. The page specified
     * should always be loaded after this method is called.
     * @param pageName Name of the page to navigate to. Must be one of the static PGNAME Strings.
     */
    public void navigateTo(String pageName){
        switch (pageName) {
            case PGNAME_ACCOUNTS:
                navigateToAccounts();
                break;
            case PGNAME_BUDGET:
                navigateToBudget();
                break;
            case PGNAME_DASHBOARD:
                navigateToDashboard();
                break;
            case PGNAME_ERROR:
                navigateToError();
                break;
            case PGNAME_LANDING:
                navigateToLanding();
                break;
            case PGNAME_LOGIN:
                navigateToLogin();
                break;
            case PGNAME_SIGNUP:
                navigateToSignup();
                break;
            case PGNAME_SPENDING:
                navigateToSpending();
                break;
            case PGNAME_SPENDINGMONTH:
                navigateToSpendingMonth();
                break;
            case PGNAME_TAX:
                navigateToTax();
                break;
            case PGNAME_TAXEDITVIEW:
                navigateToTaxEditView();
                break;
            case PGNAME_TAXRESULTS:
                navigateToTaxResults();
                break;
            case PGNAME_TRANSACTIONS:
                navigateToTransactions();
                break;
            case PGNAME_TRANSACTIONSHISTORY:
                navigateToTransactionsHistory();
                break;
            default:
                throw new IllegalArgumentException("Page '" + pageName + "' does not exist.");
        }
    }

    public String getURL(String pageName){
        return pageUrlMap.get(pageName);
    }

    private void navigateToTransactionsHistory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToTransactionsHistory'");
    }

    private void navigateToTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToTransactions'");
    }

    private void navigateToTaxResults() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToTaxResults'");
    }

    private void navigateToTaxEditView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToTaxEditView'");
    }

    private void navigateToTax() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToTax'");
    }

    private void navigateToSpendingMonth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToSpendingMonth'");
    }

    private void navigateToSpending() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToSpending'");
    }

    private void navigateToSignup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToSignup'");
    }

    private void navigateToLogin() {
        this.driver.get(URL_LOGIN);
    }

    private void navigateToLanding() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToLanding'");
    }

    private void navigateToError() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToError'");
    }

    private void navigateToDashboard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToDashboard'");
    }

    private void navigateToBudget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToBudget'");
    }

    private void navigateToAccounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToAccounts'");
    }
}