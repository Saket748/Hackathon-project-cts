package org.PagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GiftCardsPage extends BasePage {

    public GiftCardsPage(WebDriver driver) {
        super(driver);
    }

    /* ================= LOCATORS ================= */

    //Happy Birthday card
    private final By birthdayImgBy = By.cssSelector("img.img-fluid.rounded.border.border-secondary.p-1");

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
    @FindBy(xpath = "//button[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PREVIEW')]")
    private WebElement previewEGiftCardBtn;

    /* ================= ACTION METHODS ================= */

    //Used the locators inside the method to avoid StaleElementReferenceException
    public void setDenominationAndDelivery(String amount, String qty) {

        // --- fresh locate amount & qty before using them (prevents stale) ---
        WebElement amountEl = waitFor(10).until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(By.id("denomination"))
                )
        );
        WebElement qtyEl = waitFor(10).until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(By.id("quantity"))
                )
        );

        // scroll the FRESH element (not the @FindBy field)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", amountEl);

        amountEl.clear();
        amountEl.sendKeys(amount == null ? "" : amount.trim());

        qtyEl.clear();
        qtyEl.sendKeys(qty == null ? "" : qty.trim());

        // --- click the INPUT radios via their ids (freshly located) ---
        WebElement sendGiftInput = waitFor(5).until(
                ExpectedConditions.elementToBeClickable(By.id("radiogift")));
        sendGiftInput.click();

        WebElement todayInput = waitFor(5).until(
                ExpectedConditions.elementToBeClickable(By.id("giftNow")));
        todayInput.click();

        WebElement emailInput = waitFor(5).until(
                ExpectedConditions.elementToBeClickable(By.id("deliveryModeEMAIL")));
        emailInput.click();
    }



    public void scrollToBirthdayCard() {
        WebElement card = waitFor(20).until(ExpectedConditions.presenceOfElementLocated(birthdayImgBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        waitFor(10).until(ExpectedConditions.visibilityOfElementLocated(birthdayImgBy));
    }

    public void selectHappyBirthdayCard() {
        WebElement card = waitFor(10).until(ExpectedConditions.elementToBeClickable(birthdayImgBy));
        try {
            card.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        }
    }

    private void preScroll() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600);");
    }

    // Enter sender details
    public void enterSenderDetails(String fName, String lName, String email, String mobile) {
        senderFirstName.sendKeys(fName);
        senderLastName.sendKeys(lName);
        senderEmail.sendKeys(email);
        senderMobile.sendKeys(mobile);
    }

    // Enter receiver details
    public void enterReceiverDetails(String fName, String lName, String email) {
        receiverFirstName.sendKeys(fName);
        receiverLastName.sendKeys(lName);
        receiverEmail.sendKeys(email);
    }

    // Enter gift message
    public void enterMessage(String message) {
        messageBox.sendKeys(message);
    }

    // Click Preview E-Gift Card
    private final By previewBtnBy = By.xpath("//button[contains(@class,'preview-button')]");
    public void clickPreviewEGiftCard() {
        WebElement btn = waitFor(10).until(
                ExpectedConditions.elementToBeClickable(previewBtnBy)
        );

        // Scroll into center (avoids footer/header overlapping)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        try {
            btn.click();   // normal click
        } catch (Exception e) {
            // fallback JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    /* ================= COMPLETE FLOW ================= */
    public void completeGiftCardForm(
            String amount,String qty,
            String sFName, String sLName, String sEmail, String sMobile,
            String rFName, String rLName, String rEmail, String message) {

        // If images are lazy-loaded, pre-scroll helps
        setDenominationAndDelivery(amount, qty);
        preScroll();
        scrollToBirthdayCard();
        selectHappyBirthdayCard();

        enterSenderDetails(sFName, sLName, sEmail, sMobile);
        enterReceiverDetails(rFName, rLName, rEmail);
        enterMessage(message);
        clickPreviewEGiftCard();
    }
}
