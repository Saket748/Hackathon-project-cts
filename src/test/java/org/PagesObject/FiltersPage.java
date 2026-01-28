package org.PagesObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPage extends BasePage{
    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(FiltersPage.class);

    // All Filters button
    @FindBy(xpath = "//div[text()='ALL FILTERS']")
    private WebElement allFiltersBtn;
    // Storage Type section
    @FindBy(xpath = "//span[text()='Storage Type']")
    private WebElement storageTypeSection;
    // Open Storage checkbox
    @FindBy(xpath = "//div[text()='Open Storage']")
    private WebElement openStorageOption;
    // Apply Filter button
    @FindBy(xpath = "//span[text()='Apply Filter']")
    private WebElement applyFilterBtn;

    @FindBy(xpath = "//div[contains(@class,'xmdLL')]")
private List<WebElement> products;
    // Click on ALL FILTERS
    public void openAllFilters() {
        allFiltersBtn.click();
        logger.info("Clicked on ALL FILTERS button");
    }
    // Expand Storage Type filter
    public void openStorageTypeFilter() {
        storageTypeSection.click();
        logger.info("Expanded Storage Type filter section");
    }
    // Select Open Storage
    public void selectOpenStorage() {
        openStorageOption.click();
        logger.info("Selected 'Open Storage' option");
    }
    // Click Apply Filter
    public void applyFilters() {
        applyFilterBtn.click();
        logger.info("Clicked on Apply Filter button");
    }

    // Full reusable flow
    public void applyOpenStorageFilter() {
        logger.info("Starting flow: Apply Open Storage Filter");
        openAllFilters();
        openStorageTypeFilter();
        selectOpenStorage();
        applyFilters();
        logger.info("Completed flow: Apply Open Storage Filter");
    }

    public void printFirstThreeProducts() {
   int count = Math.min(3, products.size());
   for (int i = 0; i < count; i++) {
       WebElement product = products.get(i);
       String name = product.findElement(By.xpath(".//h3")).getText();
       String price = product.findElement(
               By.xpath(".//div[contains(@class,'ug1_C')]//span")
       ).getText();
       System.out.println("Product " + (i + 1));
       System.out.println("Name  : " + name);
       System.out.println("Price : " + price);
       System.out.println("-------------------------");
   }
}
}
