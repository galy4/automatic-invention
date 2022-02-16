package com.luxoft.web;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPage {

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(id = "form_CONTACT_NAME")
    WebElement firstNameField;

    @FindBy(id = "form_CONTACT_LAST_NAME")
    WebElement lastNameField;

    @FindBy(xpath = "//div[@class='inner__info']/h4")
    WebElement smallHeader;

    public ContactPage setName(String firstName, String lastName){
        firstNameField.clear();
        firstNameField.sendKeys(Keys.CONTROL + "v");
        lastNameField.sendKeys(lastName);
        return this;
    }

    public ContactPage verifyData(String firstName, String lastName){
        System.out.println(firstNameField.getCssValue("font-size"));
        System.out.println(smallHeader.getText());
        Assertions.assertAll(
                ()->assertEquals(firstNameField.getAttribute("value"), firstName),
                ()->assertEquals(lastNameField.getAttribute("value"), lastName),
                ()->assertTrue(lastNameField.getAttribute("data-validation-length").equalsIgnoreCase("max60"))
        );
        return this;
    }

}
