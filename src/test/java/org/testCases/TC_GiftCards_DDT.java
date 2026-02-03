package org.testCases;

import org.PagesObject.GiftCardsPage;
import org.PagesObject.HomePage;
import org.testBase.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

import org.excel.ExcelRead;
import org.extentReports.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//LOGGER IMPORTS (added)
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_GiftCards_DDT extends BaseClass {

    //LOGGER (added)
    private static final Logger logger = LogManager.getLogger(TC_GiftCards_DDT.class);

    @Test
    public void verify_invalid_input_from_excel() throws IOException {

        // START REPORT (simple)
        ExtentReports extent = ExtentReport.getInstance();
        ExtentTest test = extent.createTest("Gift Cards Test");

        test.info("Test Started");
        logger.info("Gift Cards scenario execution started");

        // 1) Read Gift Card Data from Excel
        String amount = ExcelRead.getAmount();
        String qty = ExcelRead.getQuantity();

        String senderFirstName = ExcelRead.getSenderFirstName();
        String senderLastName = ExcelRead.getSenderLastName();
        String senderEmail = ExcelRead.getSenderEmail();
        String senderMobile = ExcelRead.getSenderPhone();

        String receiverFirstName = ExcelRead.getReceiverFirstName();
        String receiverLastName = ExcelRead.getReceiverLastName();
        String receiverEmail = ExcelRead.getReceiverEmail();
        String message = ExcelRead.getMessage();

        test.info("Read Gift Card data from Excel");
        logger.info("Input values loaded from Excel for Gift Card form");

        // 2) Navigate and open Gift Cards
        HomePage homePage = new HomePage(driver);
        homePage.openGiftCards();
        test.info("Opened Gift Cards page");
        logger.info("Navigated to Gift Cards page successfully");

        // 3) Switch to the new tab (if opened)
        switchToNewestTab();
        test.info("Switched to newest tab (if any)");
        logger.info("Window focus moved to the latest browser tab (if available)");

        // 4) Complete the form
        GiftCardsPage giftPage = new GiftCardsPage(driver);
        giftPage.completeGiftCardForm(
                amount, qty,
                senderFirstName, senderLastName, senderEmail, senderMobile,
                receiverFirstName, receiverLastName, receiverEmail,
                message
        );
        test.info("Gift Card form filled");
        logger.info("Gift Card form fields populated with Excel data");

        // 5) Screenshot
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.pass("Screenshot after validation",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        logger.info("Captured screenshot after completing validation step");

        extent.flush();   // IMPORTANT → generates the HTML file
        logger.info("Extent report written to disk (flush completed)");
    }

    /** Switch to newest tab if openGiftCards() launched a new one. */
    private void switchToNewestTab() {
        String cur = driver.getWindowHandle();
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(cur)) {
                driver.switchTo().window(h);
                break;
            }
        }
    }
}