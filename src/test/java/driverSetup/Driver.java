package driverSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public static WebDriver DriverSetup() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
