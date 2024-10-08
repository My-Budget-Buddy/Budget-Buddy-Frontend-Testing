package com.skillstorm.Utilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.skillstorm.PageObjects.AccountsPage;
import com.skillstorm.PageObjects.DashboardPage;
import com.skillstorm.PageObjects.LoginPage;
import com.skillstorm.PageObjects.Components.Navbar.DashboardNavbar;
import com.skillstorm.PageObjects.Components.Navbar.LandingNavbar;
import com.skillstorm.Utilities.UserData.User;
import com.skillstorm.Utilities.UserData.UserType;

public class Navigator {
    // #region Static fields
    public static final String PGNAME_LANDING = "Landing";
    public static final String PGNAME_LOGIN = "Login";
    public static final String PGNAME_SIGNUP = "Signup";
    public static final String PGNAME_DASHBOARD = "Dashboard";
    public static final String PGNAME_ACCOUNTS = "Accounts";
    public static final String PGNAME_BUDGET = "Budgets";
    public static final String PGNAME_SPENDING = "Spending";
    public static final String PGNAME_SPENDINGMONTH = "SpendingMonth";
    public static final String PGNAME_TRANSACTIONS = "Transactions";
    public static final String PGNAME_TRANSACTIONSHISTORY = "TransactionsHistory";
    public static final String PGNAME_TAX = "Tax";
    public static final String PGNAME_TAXEDITVIEW = "TaxEditView";
    public static final String PGNAME_TAXRESULTS = "TaxResults";
    public static final String PGNAME_ERROR = "Error";

    public static final String URL = System.getProperty("frontendUrl", "https://staging.frontend.skillstorm-congo.com");
    public static final String URL_LANDING = URL;
    public static final String URL_LOGIN = URL + "/login";
    public static final String URL_SIGNUP = URL + "/register";
    public static final String URL_DASHBOARD = URL + "/dashboard";
    public static final String URL_ACCOUNTS = URL + "/dashboard/accounts";
    public static final String URL_BUDGET = URL + "/dashboard/budgets";
    public static final String URL_SPENDING = URL + "/dashboard/spending";
    public static final String URL_SPENDINGMONTH = URL + "/dashboard/spending/May";
    public static final String URL_TRANSACTIONS = URL + "/dashboard/transactions";
    public static final String URL_TRANSACTIONSHISTORY = "";
    public static final String URL_TAX = URL + "/dashboard/tax";
    public static final String URL_TAXEDITVIEW = "";
    public static final String URL_TAXRESULTS = "";
    public static final String URL_ERROR = URL + "/error";
    // #endregion

    Map<String, String> pageUrlMap = new HashMap<>();
    private WebDriver driver;
    private WebDriverWait wait;

    public Navigator(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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
     * 
     * @param pageName Name of the page to navigate to. Must be one of the static
     *                 PGNAME Strings.
     */
    public void navigateTo(String pageName) {
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

    public String getURL(String pageName) {
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
        driver.get(URL_LOGIN);
        User user = new User(UserType.NONPERSISTANT, Authenticator.USERNAME_NONPERSIST,
                Authenticator.PASSWORD_NONPERSIST);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(URL_TAX);
        wait.until(ExpectedConditions.urlMatches(URL_TAX));
    }

    private void navigateToSpendingMonth() {
        driver.get(URL_SPENDINGMONTH);
    }

    private void navigateToSpending() {
        driver.get(URL_SPENDING);
    }

    private void navigateToSignup() {
        driver.get(URL_SIGNUP);
    }

    private void navigateToLogin() {
        driver.get(URL_LOGIN);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LandingNavbar.BTN_LANDING_ID)));
    }

    private void navigateToLanding() {
        driver.get(URL_LANDING);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LandingNavbar.BTN_LANDING_ID)));
        ;
    }

    private void navigateToError() {
        driver.get(URL_ERROR);
    }

    private void navigateToDashboard() {
        try {
            driver.get(URL_DASHBOARD);
            wait.until(ExpectedConditions.urlMatches(URL_DASHBOARD));
        } catch (TimeoutException e) {
            // load user
            navigateToLogin();
            User user = new User(UserType.PERSISTANT, Authenticator.USERNAME_PERSISTENT,
                    Authenticator.PASSWORD_PERSISTENT);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(user);
        }

        driver.get(URL_DASHBOARD);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(DashboardPage.BTN_VIEW_ALL_TRANSACTIONS_ID)));
        } catch (TimeoutException e) {
            Assert.assertTrue(false, "Dashboard Not Loaded, Current Page: " + driver.getCurrentUrl());
        }

    }

    private void navigateToBudget() {
        driver.get(URL_BUDGET);
    }

    private void navigateToAccounts() {
        if (!driver.getCurrentUrl().equals(URL_DASHBOARD)) {
            User user = new User(UserType.PERSISTANT, Authenticator.USERNAME_PERSISTENT,
                    Authenticator.PASSWORD_PERSISTENT);
            navigateTo(PGNAME_LOGIN);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(user);
            driver.get(URL_ACCOUNTS);
        } else {
            driver.get(URL_ACCOUNTS);
        }
        wait.until(ExpectedConditions.urlMatches(URL_ACCOUNTS));
    }
}