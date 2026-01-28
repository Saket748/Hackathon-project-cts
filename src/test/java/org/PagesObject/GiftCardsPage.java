package org.PagesObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GiftCardsPage extends BasePage {

    private static final Logger log = LogManager.getLogger(GiftCardsPage.class);

    public GiftCardsPage(WebDriver driver) {
        super(driver);
        log.info("GiftCardsPage initialized with driver: {}", driver);
    }

    /* ================= LOCATORS ================= */
    @FindBy(xpath = "//img[contains(@class,'border-secondary')]")
    private WebElement happyBirthdayCard;

    @FindBy(id = "firstname")
    private WebElement senderFirstName;

    @FindBy(id = "lastname")
    private WebElement senderLastName;

    @FindBy(id = "email")
    private WebElement senderEmail;

    @FindBy(id = "telephone")
    private WebElement senderMobile;

    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='firstname']")
    private WebElement receiverFirstName;

    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='lastname']")
    private WebElement receiverLastName;

    @FindBy(xpath = "(//input[@id='email'])[2]")
    private WebElement receiverEmail;

    @FindBy(id = "giftMessage")
    private WebElement messageBox;

    @FindBy(xpath = "//button[contains(text(),'PREVIEW E-GIFT CARD')]")
    private WebElement previewEGiftCardBtn;

    /* ================= ACTION METHODS ================= */

    public void scrollToBirthdayCard() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.debug("Scrolling to Happy Birthday card...");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", happyBirthdayCard);
        log.info("Scrolled to Happy Birthday card.");
    }

    public void selectHappyBirthdayCard() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.debug("Clicking Happy Birthday card...");
        happyBirthdayCard.click();
        log.info("Happy Birthday card selected.");
    }

    public void enterSenderDetails(String fName, String lName, String email, String mobile) {
        log.debug("Entering sender details: {} {} | {} | {}", fName, lName, email, mobile);
        senderFirstName.sendKeys(fName);
        senderLastName.sendKeys(lName);
        senderEmail.sendKeys(email);
        senderMobile.sendKeys(mobile);
        log.info("Sender details entered successfully.");
    }

    public void enterReceiverDetails(String fName, String lName, String email) {
        log.debug("Entering receiver details: {} {} | {}", fName, lName, email);
        receiverFirstName.sendKeys(fName);
        receiverLastName.sendKeys(lName);
        receiverEmail.sendKeys(email);
        log.info("Receiver details entered successfully.");
    }

    public void enterMessage(String message) {
        log.debug("Entering gift message: {}", message);
        messageBox.sendKeys(message);
        log.info("Gift message entered.");
    }

    public void clickPreviewEGiftCard() {
        log.debug("Clicking Preview E-Gift Card button...");
        previewEGiftCardBtn.click();
        log.info("Preview E-Gift Card button clicked.");
    }

    public void completeGiftCardForm(
            String sFName, String sLName, String sEmail, String sMobile,
            String rFName, String rLName, String rEmail, String message) {

        log.info("Starting complete gift card form flow...");
        scrollToBirthdayCard();
        selectHappyBirthdayCard();
        enterSenderDetails(sFName, sLName, sEmail, sMobile);
        enterReceiverDetails(rFName, rLName, rEmail);
        enterMessage(message);
        clickPreviewEGiftCard();
        log.info("Gift card form completed successfully.");
    }

    public String getEmailErrorMessage() {
        log.debug("Checking email validation messages...");
        String msg = getValidationMessage(senderEmail);
        if (hasText(msg)) {
            log.warn("Sender email validation error: {}", msg);
            return msg;
        }

        msg = getValidationMessage(receiverEmail);
        if (hasText(msg)) {
            log.warn("Receiver email validation error: {}", msg);
            return msg;
        }

        try {
            WebElement active = driver.switchTo().activeElement();
            msg = getValidationMessage(active);
            if (hasText(msg)) {
                log.warn("Active element validation error: {}", msg);
                return msg;
            }
        } catch (Exception e) {
            log.error("Error while fetching active element validation message", e);
        }

        log.info("No email validation errors found.");
        return "";
    }

    private String getValidationMessage(WebElement el) {
        try {
            return el.getAttribute("validationMessage");
        } catch (Exception e) {
            log.error("Failed to get validation message from element: {}", el, e);
            return "";
        }
    }

    private boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
