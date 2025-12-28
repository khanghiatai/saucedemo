package page.checkout;

import org.openqa.selenium.By;
import utils.common.BaseActions;

public class CartPage {
    // Locators
    private final By backpackAddToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");
    private final By checkoutBtn = By.id("checkout");

    public void addBackpackToCart() {
        BaseActions.click(backpackAddToCartBtn);
    }

    public void proceedToCheckout() {
        BaseActions.click(cartLink);
        BaseActions.click(checkoutBtn);
    }
}