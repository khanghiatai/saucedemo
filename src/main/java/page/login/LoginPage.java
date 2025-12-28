package page.login;

import org.openqa.selenium.By;
import utils.common.BaseActions;

public class LoginPage {

    // Locators
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");

    public void login(String user, String pass) {
        BaseActions.sendKeys(usernameField, user);
        BaseActions.sendKeys(passwordField,pass);
        BaseActions.click(loginButton);
    }

    public String getError() {
        return BaseActions.getText(errorMessage);
    }
}