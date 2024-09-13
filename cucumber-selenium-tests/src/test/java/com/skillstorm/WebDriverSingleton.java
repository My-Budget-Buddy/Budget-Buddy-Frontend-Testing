package com.skillstorm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    //To be used in each Step Definition as the one WebDriver for all cucumber/selenium tests
    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        //Options are used to allow running ChromeDrivers in Jenkins pipeline
        //options.addArguments("--headless");
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
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
