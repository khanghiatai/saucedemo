package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.checkout.CartPage;
import page.checkout.CheckoutPage;
import page.login.LoginPage;
import utils.driver.Driver;

public class CheckoutTests extends BaseTest {

    @Test(description = "Verify successful checkout flow - Homework Requirement")
    public void testSuccessfulPurchase() {
        // Instantiate pages without passing driver
        LoginPage loginPage = new LoginPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        // 1. Login using Spring Config Bean
        Driver.getWebDriver().get(config.getBaseUrl());
        loginPage.login(config.getStandardUser(), config.getPassword());

        // 2. Add items to cart
        cartPage.addBackpackToCart();
        cartPage.proceedToCheckout();

        // 3. Complete Checkout info
        checkoutPage.enterInformation("QA", "Automation", "12345");
        
        // 4. Finalize and Verify
        checkoutPage.finishCheckout();
        
        String expectedMsg = "Thank you for your order!";
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), expectedMsg);
    }
}