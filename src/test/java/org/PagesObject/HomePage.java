package org.Pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

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

        searchBox.sendKeys(value);

    }





    public void openGiftCards() {

        giftCardsLink.click();

    }

}
