package com.mycompany.app.tests;

import com.mycompany.app.base.BaseTest;
import com.mycompany.app.pages.ProductPage;
import com.mycompany.app.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(description = "Add first product to cart")
    public void testAddToCart() {
        com.mycompany.app.pages.LoginPage lp = new com.mycompany.app.pages.LoginPage(driver);
        lp.open("https://www.saucedemo.com/") ;
        lp.login("standard_user", "secret_sauce");

        ProductPage pp = new ProductPage(driver);
        pp.addFirstProductToCart();
        pp.openCart();
        CartPage cp = new CartPage(driver);
        Assert.assertTrue(cp.getItemNames().size() > 0, "Cart should contain at least one item");
    }
}
