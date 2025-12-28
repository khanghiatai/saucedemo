package testcase;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import utils.config.TestConfig;
import utils.driver.Driver;

public class BaseTest {
    public static TestConfig config;
    public static String browser;

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void setupContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        config = context.getBean("testConfig", TestConfig.class);
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void initDriver(@Optional("chrome") String browser) {
        BaseTest.browser = browser;
        Driver.initDriver(browser);
        driverThread.set(Driver.getWebDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.quitDriver();
    }
}