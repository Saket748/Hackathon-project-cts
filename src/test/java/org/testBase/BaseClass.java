package org.testBase;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;

import org.testng.annotations.*;

public class BaseClass {

    protected static WebDriver driver;
    private final String baseUrl = "https://www.urbanladder.com/";

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        openBaseUrlWithRescue(baseUrl);
    }

    @BeforeMethod(alwaysRun = true)
    public void goHomeBeforeEachTest() {
        openBaseUrlWithRescue(baseUrl);   // now driver will not be null
    }

    private void openBaseUrlWithRescue(String url) {
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            try {
                ((JavascriptExecutor) driver).executeScript("window.stop();");
            } catch (Exception ignored) {}
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null) driver.quit();

    }
}