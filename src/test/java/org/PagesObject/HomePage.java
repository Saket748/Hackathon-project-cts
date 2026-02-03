package org.PagesObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    private static Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "searchInput")
    private WebElement searchBox;

    public void openBookshelves(String searchItem) {
        logger.info("Opening Bookshelves via search: {}", searchItem);

        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(searchItem);

        // press ENTER
        searchBox.sendKeys(Keys.ENTER);

        // press ENTER again (simple trick for sites that need confirm)
        //searchBox.sendKeys(Keys.ENTER);

        logger.debug("Search submitted successfully for {}", searchItem);

    }


    @FindBy(xpath = "//a[contains(text(),'Gift Cards')]")
    private WebElement giftCardsLink;

    public void openGiftCards() {
        logger.info("Clicking on 'Gift Cards' link");
        giftCardsLink.click();
        logger.debug("'Gift Cards' link clicked successfully");
    }
}