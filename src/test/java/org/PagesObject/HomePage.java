package org.PagesObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {

        super(driver);

    }

    @FindBy(id = "inputSearch")
    private WebElement searchBox;

    @FindBy(xpath = "//a[contains(text(),'Bookshelves')]")
    private WebElement bookshelvesLink;

    @FindBy(xpath = "//a[contains(text(),'Gift Cards')]")
    private WebElement giftCardsLink;

    public void searchProduct(String value) {

        logger.info("Entering product name '{}' in search box", value);
        searchBox.sendKeys(value);
        logger.debug("Search box element located and value entered successfully");

    }


    public void openBookshelves() {

        logger.info("Clicking on 'Bookshelves' link");
        bookshelvesLink.click();
        logger.debug("'Bookshelves' link clicked successfully");

    }


    public void openGiftCards() {

        logger.info("Clicking on 'Gift Cards' link");
        giftCardsLink.click();
        logger.debug("'Gift Cards' link clicked successfully");

    }

}
