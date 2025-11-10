package com.mycompany.app.tests;

import com.mycompany.app.base.BaseTest;
import com.mycompany.app.pages.LoginPage;
import com.mycompany.app.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class LoginTest extends BaseTest {
    private Map<String,String> user;

    private Object[][] getDataFromSheet(String filePath, String sheetName) {
        List<Map<String, String>> list = ExcelUtils.getLoginData(filePath, sheetName);
        Object[][] arr = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            arr[i][0] = list.get(i);
        }
        return arr;
    }

    @BeforeClass
    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return getDataFromSheet("testdata/users.xlsx", "Valid User");
    }

    @BeforeClass
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return getDataFromSheet("testdata/users.xlsx", "Invalid User");
    }


    @Test(dataProvider = "validLoginData", description = "Valid login should redirect to inventory page")
    public void testValidLogin(Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");

        LoginPage lp = new LoginPage(driver);
        lp.open("https://www.saucedemo.com/") ;
        lp.login(username, password);
        lp.waiting();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed or user not redirected to inventory page.");
    }

    @Test(dataProvider = "invalidLoginData",description = "Invalid login shows error message")
    public void testInvalidLogin(Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");

        LoginPage lp = new LoginPage(driver);
        lp.open("https://www.saucedemo.com/") ;
        lp.login(username, password);
        lp.waiting();
        String err = lp.getErrorMessage();
        Assert.assertTrue(err.contains("Epic sadface"), "Expected error message not shown");
    }
}