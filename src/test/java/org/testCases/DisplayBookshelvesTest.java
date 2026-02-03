package org.testCases;

import org.PagesObject.HomePage;
import org.PagesObject.FiltersPage;
import org.testBase.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

import org.excel.ExcelRead;
import org.extentReports.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// ✅ LOGGER IMPORTS
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayBookshelvesTest extends BaseClass {

    // ✅ LOGGER INSTANCE
    private static final Logger logger = LogManager.getLogger(DisplayBookshelvesTest.class);

    @Test
    public void verify_bookshelves_open_storage_print_first3() throws IOException {

        // START REPORT (simple)
        ExtentReports extent = ExtentReport.getInstance();
        ExtentTest test = extent.createTest("Display Bookshelves Test");

        test.info("Test Started");
        logger.info("Test Started");

        // 1) Read Search Item
        String searchItem = ExcelRead.searchItem();
        test.info("Read item from Excel: " + searchItem);
        logger.info("Read item from Excel: {}", searchItem);

        // 2) Open Bookshelves Page
        HomePage homePage = new HomePage(driver);
        homePage.openBookshelves(searchItem);
        test.info("Navigated to Bookshelves Page");
        logger.info("Navigated to Bookshelves Page");

        // 3) Apply Filters
        FiltersPage filtersPage = new FiltersPage(driver);

        filtersPage.openAllFilters();
        test.info("Opened all filters");
        logger.info("Opened all filters");

        filtersPage.openStorageTypeFilter();
        test.info("Opened Storage Type Filter");
        logger.info("Opened Storage Type Filter");

        filtersPage.selectOpenStorage();
        test.info("Selected Open Storage");
        logger.info("Selected Open Storage");

        filtersPage.applyFilters();
        test.info("Applied filters");
        logger.info("Applied filters");

        // 4) Print Products
        filtersPage.printFirstThreeProducts();
        test.pass("Printed first three products successfully");
        logger.info("Printed first three products successfully");

        extent.flush();   // IMPORTANT → generates the HTML file
        logger.info("Extent report flushed successfully");
    }
}