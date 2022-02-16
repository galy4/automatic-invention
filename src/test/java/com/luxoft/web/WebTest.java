package com.luxoft.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class WebTest extends BaseTest{
    private final HomePage homePage = new HomePage(driver);


    @Test
    @Disabled
    void firstTest() {

        WebElement menu = driver.findElement(By.id("menu-switcher"));
        Assertions.assertAll(
                () -> Assertions.assertTrue(menu.isDisplayed()),
                () -> Assertions.assertTrue(driver.findElement(By.className("header__menu")).isDisplayed()),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[@class='icomoon-search']"))
                        .isDisplayed()),
                () -> Assertions.assertTrue(driver.findElement(By.linkText("HOME")).isDisplayed()),
                () -> Assertions.assertTrue(driver.findElement(By.partialLinkText("HO")).isDisplayed())
        );

        List<WebElement> menuItems = driver.findElement(By.className("header__menu"))
                .findElements(By.xpath("./ul/li/a"));
        assertThat(menuItems.size(), equalTo(6));

    }

    @Test
    @Disabled
    void secondTest(){
        homePage.checkSearchButtonDisplayed();
        Assertions.assertTrue(homePage.verifyMainMenuItems(
                Arrays.asList("HOME","INSIGHTS","SERVICES","INDUSTRIES","CAREERS","CONTACT US")
        ));
    }

//    @Test
//    void thirdTest(){
//        homePage.checkElementDisplayed("headerMenu");
//        homePage.checkElementDisplayed("menuSwitcher");
//    }

    @Test
    void checkLink() throws InterruptedException {
        homePage.clickContactLink()
            .acceptCookies()
            .isViolationDisplayed();
    }

    @Test
    void sendTest() throws InterruptedException {
        homePage.clickContactLink();
        ContactPage contactPage = homePage.isViolationDisplayed();
        contactPage.setName("Edward", "Latypov");
        contactPage.verifyData("INTERVAL", "Latypov");
    }

    @AfterEach
    void goMain(){
        driver.get("https://www.luxoft.com/");
    }


}
