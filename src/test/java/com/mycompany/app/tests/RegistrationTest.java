package com.mycompany.app.tests;

import com.mycompany.app.base.BaseTest;
import com.mycompany.app.pages.RegistrationPage;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(description = "Site has no register")
    public void testRegistration() {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.openRegistration("https://www.saucedemo.com/");
        rp.register("test_user@example.com", "test_user", "Password123!");
    }
}