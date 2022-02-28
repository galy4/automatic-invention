package com.luxoft.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Название эпика")
@Story("Сторя")
public class WebTest extends BaseTest{
    private final HomePage homePage = new HomePage(driver);


    @Test
    @DisplayName("первый тест")
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
        assertThat(menuItems.size(), equalTo(16));

    }

    @Test
    @DisplayName("второй тест")
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
    @DisplayName("третий тест")
    void checkLink() throws InterruptedException {
        homePage.clickContactLink()
            .acceptCookies()
            .isViolationDisplayed();
    }

    @Test
    @Disabled
    @DisplayName("четвертый тест")
    void sendTest() throws InterruptedException {
        homePage.clickContactLink();
        ContactPage contactPage = homePage.isViolationDisplayed();
        contactPage.setName("Edward", "Latypov");
        contactPage.verifyData("INTERVAL", "Latypov");
    }

    @Test
    @Disabled
    void waitsTest() throws InterruptedException {
        homePage
                .checkElementAppears()
                .countArrows(3);

    }

    @Test
    void newTabTest(){
        homePage.closeAlertWindow();
        homePage.openNewPage();
    }

    @AfterEach
    void goMain(){
        driver.get("https://www.luxoft.com/");
    }


}
