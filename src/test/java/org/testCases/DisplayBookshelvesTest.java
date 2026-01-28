package org.testCases;

import org.PagesObject.HomePage;
import org.PagesObject.FiltersPage;
import org.testBase.BaseClass;
import org.testng.annotations.Test;

public class DisplayBookshelvesTest extends BaseClass {

    @Test
    public void verify_bookshelves_below_15000_open_storage() {
        // 1) Navigate to Bookshelves page
        HomePage homePage = new HomePage(driver);
        homePage.openBookshelves();

        // 2) Apply Open Storage filter
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.applyOpenStorageFilter();

        // 3) Print first 3 products below Rs. 15000 (excluding out of stock)
        filtersPage.printFirstThreeProducts();
    }
}
