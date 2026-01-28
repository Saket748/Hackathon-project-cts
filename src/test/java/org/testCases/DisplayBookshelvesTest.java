package org.testCases;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import org.PagesObject.HomePage;

import org.PagesObject.FiltersPage;

public class DisplayBookshelvesTest {

    WebDriver driver;

    HomePage homePage;

    FiltersPage filtersPage;

    @BeforeClass

    public void setup() {

        // Launch browser (NOT headless)

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.urbanladder.com");

        homePage = new HomePage(driver);

        filtersPage = new FiltersPage(driver);

    }

    @Test

    public void displayBookshelves() {

        // Step 1: Search Bookshelves under 15000

        homePage.searchItem("Bookshelves under 15000");

        // Step 2: Apply Filters

        filtersPage.openAllFilters();

        filtersPage.selectStorageType();

        filtersPage.selectOpenStorage();

        filtersPage.applyFilters();

        // Step 3: Get first 3 products

        filtersPage.getFirstThreeProducts();

    }

    @AfterClass

    public void tearDown() {

        driver.quit();

    }

}
 