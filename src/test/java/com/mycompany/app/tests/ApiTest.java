package com.mycompany.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import com.mycompany.app.utils.ExcelUtils;
import java.util.Map;

public class ApiTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @DataProvider(name = "apiData")
    public Object[][] apiData() {
        String filePath = "testdata/api_testdata.xlsx";
        String sheetName = "Test Data";
        return ExcelUtils.getTestDataAsMap(filePath, sheetName);
    }

    @Test(dataProvider = "apiData", description = "Automated API test for multiple endpoints")
    public void testApiEndpoints(Map<String, String> data) {
        String method = data.get("method");
        String endpoint = data.get("endpoint");
        String body = data.get("body");
        int expectedStatus = Integer.parseInt(data.get("expectedStatus"));
        String description = data.get("description");

        System.out.println("Running test: " + description);
        System.out.println("Endpoint: " + endpoint);

        Response response = null;

        switch (method.toUpperCase()) {
            case "GET":
                response = RestAssured.get(BASE_URL + endpoint);
                break;

            case "POST":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .body(body)
                        .post(BASE_URL + endpoint);
                break;

            case "PUT":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .body(body)
                        .put(BASE_URL + endpoint);
                break;

            case "PATCH":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .body(body)
                        .patch(BASE_URL + endpoint);
                break;

            case "DELETE":
                response = RestAssured.delete(BASE_URL + endpoint);
                break;

            default:
                throw new IllegalArgumentException("Unsupported method: " + method);
        }

        // Print to console for debug/log
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody().asString());
        System.out.println("--------------------------------------------");

        // Assertion
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Expected: " + expectedStatus + ", Actual: " + response.getStatusCode());
    }
}
