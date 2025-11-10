package com.mycompany.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;
    private By firstAddToCart = By.cssSelector(".inventory_item:first-of-type button");
    private By cartLink = By.cssSelector(".shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        driver.findElement(firstAddToCart).click();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}