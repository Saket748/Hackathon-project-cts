package org.PagesObject;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class GiftCardsPage extends BasePage {

    public GiftCardsPage(WebDriver driver) {

        super(driver);

    }

    /* ================= LOCATORS ================= */

    // Happy Birthday EGV card

    @FindBy(xpath = "//img[contains(@class, 'border-secondary')]")

    private WebElement happyBirthdayCard;

    // Sender details

    @FindBy(id = "firstname")

    private WebElement senderFirstName;

    @FindBy(id = "lastname")

    private WebElement senderLastName;

    @FindBy(id = "email")

    private WebElement senderEmail;

    @FindBy(id = "telephone")

    private WebElement senderMobile;

    // Receiver details

    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='firstname']")

    private WebElement receiverFirstName;

    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='lastname']")

    private WebElement receiverLastName;

    @FindBy(xpath = "(//input[@id='email'])[2]")

    private WebElement receiverEmail;

    // Message box

    @FindBy(id = "giftMessage")

    private WebElement messageBox;

    // Preview E-Gift Card button

    @FindBy(xpath = "//button[contains(text(),'PREVIEW E-GIFT CARD')]")

    private WebElement previewEGiftCardBtn;

    /* ================= ACTION METHODS ================= */

    // Scroll until Happy Birthday card is visible

    public void scrollToBirthdayCard() {

        ((JavascriptExecutor) driver)

                .executeScript("arguments[0].scrollIntoView(true);", happyBirthdayCard);

    }

    // Click Happy Birthday card

    public void selectHappyBirthdayCard() {

        happyBirthdayCard.click();

    }

    // Enter sender details

    public void enterSenderDetails(String fName, String lName, String email, String mobile) {

        senderFirstName.sendKeys(fName);

        senderLastName.sendKeys(lName);

        senderEmail.sendKeys(email);

        senderMobile.sendKeys(mobile);

    }

    // Enter receiver details

    public void enterReceiverDetails(String fName, String lName,String email) {

        receiverFirstName.sendKeys(fName);

        receiverLastName.sendKeys(lName);

        receiverEmail.sendKeys(email);

    }

    // Enter gift message

    public void enterMessage(String message) {

        messageBox.sendKeys(message);

    }

    // Click Preview E-Gift Card

    public void clickPreviewEGiftCard() {

        previewEGiftCardBtn.click();

    }


    /* ================= COMPLETE FLOW ================= */

    public void completeGiftCardForm(

            String sFName, String sLName, String sEmail, String sMobile,

            String rFName, String rLName, String rEmail, String message) {

        scrollToBirthdayCard();

        selectHappyBirthdayCard();

        enterSenderDetails(sFName, sLName, sEmail, sMobile);

        enterReceiverDetails(rFName, rLName, rEmail);

        enterMessage(message);

        clickPreviewEGiftCard();

    }



    /** Returns native HTML5 validation message from the email inputs (sender first, then receiver). */
    public String getEmailErrorMessage() {
        String msg = getValidationMessage(senderEmail);
        if (hasText(msg)) return msg;

        msg = getValidationMessage(receiverEmail);
        if (hasText(msg)) return msg;

        try {
            WebElement active = driver.switchTo().activeElement();
            msg = getValidationMessage(active);
            if (hasText(msg)) return msg;
        } catch (Exception ignored) {}

        return "";
    }

    private String getValidationMessage(WebElement el) {
        try { return el.getAttribute("validationMessage"); }
        catch (Exception e) { return ""; }
    }

    private boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }


}
