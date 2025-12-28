package page.checkout;

import org.openqa.selenium.By;
import utils.common.BaseActions;
import utils.driver.Driver;

public class CheckoutPage {
    // Locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By successHeader = By.className("complete-header");

    public void enterInformation(String fname, String lname, String zip) {
      BaseActions.sendKeys(firstNameField,fname);
        BaseActions.sendKeys(lastNameField,lname);
        BaseActions.sendKeys(zipCodeField,zip);
        BaseActions.click(continueBtn);
    }

    public void finishCheckout() {
        Driver.getWebDriver().findElement(finishBtn).click();
    }

    public String getConfirmationMessage() {
        return Driver.getWebDriver().findElement(successHeader).getText();
    }
}