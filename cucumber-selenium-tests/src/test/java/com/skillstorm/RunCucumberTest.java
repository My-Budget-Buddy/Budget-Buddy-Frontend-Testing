package com.skillstorm;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// cucumberoptions used for testng
@CucumberOptions(
    features = "classpath:com/skillstorm/TransactionHistory.feature", 
    glue = "com.skillstorm",
    plugin = {"pretty", "html:target/cucumber-reports.html"} 
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{
}
