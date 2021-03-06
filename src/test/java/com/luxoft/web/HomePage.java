package com.luxoft.web;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage{

    private final WebDriver driver;

//    //-----------1st approach - classic PageObject-----------------
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By headerMenu = By.className("header__menu");
//    By searchButton = By.xpath("//span[@class='icomoon-search']");
//    By link_home = By.linkText("HOME");
//    By menuItems = By.xpath("./ul/li/a");
//
//
//    public void checkSearchButtonDisplayed(){
//        assertThat(driver.findElement(searchButton).isDisplayed(), equalTo(true));
//    }
//
//    public boolean verifyMainMenuItems(List<String> items){
//        List<WebElement> menu = driver.findElement(headerMenu).findElements(menuItems);
//        for(WebElement we:menu){
//            if(!items.contains(we.getText()))
//                return false;
//        }
//        return true;
//    }
//


    //-----------2nd approach - PageFactory---------------------------
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "header__menu")
    WebElement headerMenu;

    @FindBy(xpath = "//span[@class='icomoon-search']")
    WebElement searchButton;

    @FindBy(linkText = "HOME")
    WebElement homeLink;

    @FindBy(linkText = "CONTACT US")
    WebElement contactLink;

    @FindBy(xpath = "//a[text()='Report copyright violation']")
    WebElement violationLink;

    @FindBy(xpath = "//*[@class='header__menu']/ul/li/a")
    List<WebElement> menuItems;

    @FindAll(
            {
                    @FindBy(css = ".switchAll"),
                    @FindBy(id = "menu-switcher")
            }
    )
    WebElement menuSwitcher;

    @FindBy(id = "CybotCookiebotDialogBodyButtonAccept")
    WebElement acceptCookies;

    @FindBy(xpath = "//a[contains(text(),'About us')]")
    List<WebElement> aboutUsLinks;

    @Step("check search button")
    public void checkSearchButtonDisplayed(){
        assertThat(searchButton.isDisplayed(), equalTo(true));
        assertThat(menuSwitcher.isDisplayed(), equalTo(true));
    }

    @Step("?????????????????? ?????? ?? ???????? ???????????????????????? {items}")
    public boolean verifyMainMenuItems(List<String> items){
        for(WebElement we:menuItems){
            if(!items.contains(we.getText()))
                return false;
        }
        return true;
    }

    @Step("click contact link")
    public HomePage clickContactLink(){
        contactLink.click();
        return this;
    }

    @Step("accept cookies")
    public HomePage acceptCookies(){
        acceptCookies.click();
        return this;
    }

    @Step("violation link")
    public ContactPage isViolationDisplayed() throws InterruptedException {
        Assertions.assertTrue(violationLink.isDisplayed());
        Thread.sleep(1500);
        violationLink.click();
        System.out.println(driver.manage().getCookieNamed("VISITOR_INFO1_LIVE"));
        driver.manage().deleteCookieNamed("BX_USER_ID");
        driver.manage().deleteAllCookies();
        return new ContactPage(driver);
    }

    @Step("checkElementAppears")
    public HomePage checkElementAppears(){
        menuSwitcher.click();
        BaseTest.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//nav[@class='lux-menu__nav']/ul/li/a[contains(text(),'About us')]")));
        return this;
    }

    @Step("?????????????????? ?????????????? {0}")
    public HomePage countArrows(int size) throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(aboutUsLinks.get(0)).build().perform();
        List<WebElement> arr = aboutUsLinks.get(0).findElements(By.xpath("./span/i"));
        assertThat(arr.size(), equalTo(size));
        Thread.sleep(8000);
        return this;
    }

    @Step("?????????????? ??????????")
    public void closeAlertWindow(){
        BaseTest.javascriptExecutor.executeScript("alert('TEST ALERT!11');");
        driver.switchTo().alert().dismiss();
    }

    @Step("?????????????? ?????????? ??????????????")
    public void openNewPage(){
        BaseTest.javascriptExecutor.executeScript("window.open();");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://google.com");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    //--------3rd approach - switch or map---------
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public WebElement getElement(String name){
//        WebElement elem = null;
//        switch (name){
//            case "headerMenu":
//                elem = driver.findElement(By.className("header__menu")); break;
//            case "searchButton":
//                elem = driver.findElement(By.xpath("//span[@class='icomoon-search']")); break;
//            case "menuSwitcher":
//                elem = driver.findElement(By.xpath("//*[@class='header__menu']/ul/li/a")); break;
//        }
//        return elem;
//    }



}
