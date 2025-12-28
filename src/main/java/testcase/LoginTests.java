package testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.login.LoginPage;
import utils.driver.Driver;

import java.util.Objects;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "loginData", priority = 1, description = "Test login for all user types")
    public void testLoginScenarios(String scenario, String user, String pass, String expectedUrl, String expectedError) {
        LoginPage loginPage = new LoginPage();
        Driver.getWebDriver().get(config.getBaseUrl());

        // Step 1: Login
        loginPage.login(user, pass);

        // Step 2: Verification
        if (expectedUrl != null) {
            String currentUrl = Driver.getWebDriver().getCurrentUrl();
            Assert.assertTrue(Objects.requireNonNull(currentUrl).contains(expectedUrl),
                    "[%s] Failed! Expected URL to contain: %s but got: %s".formatted(scenario, expectedUrl, currentUrl));
        }

        if (expectedError != null) {
            String actualError = loginPage.getError();
            Assert.assertEquals(actualError, expectedError,
                    "[%s] Error message mismatch!".formatted(scenario));
        }
    }

    @Test(priority = 2, description = "Verify Problem User edge case - broken images")
    public void testProblemUserInventory() {
        LoginPage loginPage = new LoginPage();
        Driver.getWebDriver().get(config.getBaseUrl());

        loginPage.login(config.getProblemUser(), config.getPassword());

        // Verify if an image is broken (common with problem_user)
        String imageSrc = Driver.getWebDriver().findElement(By.xpath("//img[@alt='Sauce Labs Backpack']")).getAttribute("src");
        Assert.assertTrue(Objects.requireNonNull(imageSrc).contains("sl-404"), "Problem user should see broken image paths!");
    }

    @Test(priority = 3, description = "Performance Glitch User edge case - loading delay")
    public void testPerformanceUserTiming() {
        LoginPage loginPage = new LoginPage();
        Driver.getWebDriver().get(config.getBaseUrl());

        long startTime = System.currentTimeMillis();
        loginPage.login(config.getPerformanceUser(), config.getPassword());
        long endTime = System.currentTimeMillis();

        // Check if login took more than 3 seconds (typical for this user)
        Assert.assertTrue((endTime - startTime) > 3000, "Performance glitch user should experience delay");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                // Format: {Scenario Name, Username, Password, Expected URL, Expected Error}
                {"Standard User", config.getStandardUser(), config.getPassword(), "inventory.html", null},
                {"Locked Out User", config.getLockedOutUser(), config.getPassword(), null, "Epic sadface: Sorry, this user has been locked out."},
                {"Problem User", config.getProblemUser(), config.getPassword(), "inventory.html", null},
                {"Performance Glitch User", config.getPerformanceUser(), config.getPassword(), "inventory.html", null},
                {"Error User", config.getErrorUser(), config.getPassword(), "inventory.html", null},
                {"Visual User", config.getVisualUser(), config.getPassword(), "inventory.html", null},
                {"Invalid Password", config.getStandardUser(), "wrong_pass", null, "Epic sadface: Username and password do not match any user in this service"}
        };
    }
}