package com.skillstorm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver(String type){
        if(type.equals("firefox")){
            return getFirefoxDriver();
        }else if(type.equals("chrome")){
            return getChromeDriver();
        }else{
            throw new RuntimeException("Unsupported browser type");
        }
    }

    //To be used in each Step Definition as the one WebDriver for all cucumber/selenium tests
    public static WebDriver getDriver() {
        return getChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        //Options are used to allow running ChromeDrivers in Jenkins pipeline
        //options.addArguments("-headless");
        //options.addArguments("-no-sandbox");
        //options.addArguments("-disable-dev-shm-usage");
        if (driver == null) {
            driver = new FirefoxDriver(options);
        }
        return driver;
    }

    private static WebDriver getChromeDriver(){
        //Options are used to allow running ChromeDrivers in Jenkins pipeline
        String headlessEnv = System.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(headlessEnv);
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-debugging-pipe");
        }
        if (driver == null) {
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    //To be used in each Step Definition to tear down the WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
