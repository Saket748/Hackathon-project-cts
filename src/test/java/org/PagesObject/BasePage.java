package org.PagesObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Renamed from wait(long) → waitFor(long) to avoid clashing with Object.wait(long)
    protected WebDriverWait waitFor(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
