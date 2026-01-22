package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPage extends BasePage{
    public FiltersPage(WebDriver driver) {
        super(driver);
    }
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
    // Click on ALL FILTERS
    public void openAllFilters() {
        allFiltersBtn.click();
    }
    // Expand Storage Type filter
    public void openStorageTypeFilter() {
        storageTypeSection.click();
    }
    // Select Open Storage
    public void selectOpenStorage() {
        openStorageOption.click();
    }
    // Click Apply Filter
    public void applyFilters() {
        applyFilterBtn.click();
    }

    // Full reusable flow
    public void applyOpenStorageFilter() {
        openAllFilters();
        openStorageTypeFilter();
        selectOpenStorage();
        applyFilters();
    }
}
