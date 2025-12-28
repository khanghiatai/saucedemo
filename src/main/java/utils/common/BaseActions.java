package utils.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.Driver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BaseActions {
    private static WebDriverWait getWait() {
        return new WebDriverWait(
                Driver.getWebDriver(),
                Duration.of(30, ChronoUnit.SECONDS)
        );
    }

    private static WebElement findElement(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void click(By locator) {
        findElement(locator).click();
    }

    public static void sendKeys(By locator, CharSequence sequence) {
        findElement(locator).clear();
        findElement(locator).sendKeys(sequence);
    }

    public static String getText(By locator) {
        return findElement(locator).getText();
    }
}
