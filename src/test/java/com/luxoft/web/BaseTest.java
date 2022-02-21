package com.luxoft.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    static WebDriver driver;
    public static WebDriverWait waitVar;
    public static JavascriptExecutor javascriptExecutor;

    @BeforeAll
    static void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        waitVar = new WebDriverWait(driver, 15);
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.navigate().to("https://www.luxoft.com/");
    }

    @AfterAll
    static void tearDown(){
        driver.close();
    }

}
