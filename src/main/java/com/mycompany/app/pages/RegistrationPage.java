package com.mycompany.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    private By email = By.id("email");
    private By username = By.id("username");
    private By password = By.id("password");
    private By registerBtn = By.id("register");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistration(String url) {
        driver.get(url);
    }

    public void register(String em, String user, String pass) {
        driver.findElement(email).sendKeys(em);
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(registerBtn).click();
    }
}
