package org.PagesObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FiltersPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(FiltersPage.class);
    private final WebDriverWait wait;

    public FiltersPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // All Filters button
    @FindBy(xpath = "//div[contains(@class,'qJoGr') and normalize-space()='ALL FILTERS']")
    private WebElement allFiltersBtn;

    // Storage Type section
    @FindBy(xpath = "//div[contains(@class,'OEFJM') and .//span[normalize-space()='Storage Type']]")
    private WebElement storageTypeSection;

    // Open Storage checkbox
    @FindBy(xpath = "//div[normalize-space(text())='Open Storage']")
    private WebElement openStorageOption;

    // Apply Filter button
    @FindBy(xpath = "//span[text()='Apply Filter']")
    private WebElement applyFilterBtn;

    // Product card locator (kept your locator)
    private final By productCardBy = By.xpath("//div[contains(@class,'xmdLL')]");

    // Click on ALL FILTERS
    public void openAllFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn)).click();
        logger.info("Clicked on ALL FILTERS button");
    }

    // Expand Storage Type filter
    public void openStorageTypeFilter() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", storageTypeSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", storageTypeSection);
    }

    // Select Open Storage

    public void selectOpenStorage() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", openStorageOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", openStorageOption);
        logger.info("Clicked Open Storage option");
    }


    // Click Apply Filter
    public void applyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(applyFilterBtn)).click();
        logger.info("Clicked on Apply Filter button");

        // wait for results to load / refresh
        wait.until(ExpectedConditions.visibilityOfElementLocated(productCardBy));
        logger.info("Products loaded after applying filter");
    }

    // Print first 3 products after Open Storage filter (NO extra filtering)
    public void printFirstThreeProducts() {

        // IMPORTANT: find elements AFTER filter applied (avoid stale list)
        List<WebElement> products = driver.findElements(productCardBy);

        int count = Math.min(3, products.size());

        for (int i = 0; i < count; i++) {
            WebElement product = products.get(i);

            String name = "";
            String price = "";

            try {
                name = product.findElement(By.xpath(".//h3")).getText().trim();
            } catch (Exception e) {
                name = "Name not found";
            }

            try {
                price = product.findElement(By.xpath(".//div[contains(@class,'ug1_C')]//span")).getText().trim();
            } catch (Exception e) {
                price = "Price not found";
            }

            System.out.println("Product " + (i + 1));
            System.out.println("Name  : " + name);
            System.out.println("Price : " + price);
            System.out.println("-------------------------");
        }

        if (products.size() == 0) {
            System.out.println("No products found after applying Open Storage filter.");
        }
    }
}