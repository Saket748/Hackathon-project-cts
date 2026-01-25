package org.testBase;

import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new"); // if needed
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);      // only wait DOMContentLoaded

        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // Open Gift Cards page directly (recommended for this test)
        String Url = "https://www.urbanladder.com/";

        openBaseUrlWithRescue(Url);
    }

    private void openBaseUrlWithRescue(String url) {
        try {
            driver.get(url);
        } catch (org.openqa.selenium.TimeoutException e) {
            // Stop pending loads (ads/trackers) so the test can proceed
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.stop();");
            } catch (Exception ignored) {}
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

