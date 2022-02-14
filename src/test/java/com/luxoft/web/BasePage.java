package com.luxoft.web;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    abstract WebElement getElement(String name);

    public void checkElementDisplayed(String name){
        Assertions.assertTrue(getElement(name).isDisplayed());
    }
}
