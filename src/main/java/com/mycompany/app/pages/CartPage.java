package com.mycompany.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private WebDriver driver;
    private By itemNames = By.cssSelector(".cart_item .inventory_item_name");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getItemNames() {
        List<WebElement> els = driver.findElements(itemNames);
        return els.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}

