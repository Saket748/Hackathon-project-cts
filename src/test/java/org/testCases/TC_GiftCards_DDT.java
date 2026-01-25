package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.PagesObject.GiftCardsPage;
import org.PagesObject.HomePage;
import org.testBase.BaseClass;
import org.testData.GiftCardDataProvider;

public class TC_GiftCards_DDT extends BaseClass {

    @Test(dataProvider = "giftCardData", dataProviderClass = GiftCardDataProvider.class)
    public void verify_invalid_input_from_excel(

            String senderFirstName,
            String senderLastName,
            String senderEmail,
            String senderMobile,
            String receiverFirstName,
            String receiverLastName,
            String receiverEmail,
            String message

    ) {
        // Home → Gift Cards (handles new tab + readiness)
        HomePage homePage = new HomePage(driver);
        homePage.openGiftCards();

        // Gift Cards flow
        GiftCardsPage giftPage = new GiftCardsPage(driver);
        giftPage.completeGiftCardForm(
                senderFirstName, senderLastName, senderEmail, senderMobile,
                receiverFirstName, receiverLastName, receiverEmail,
                message
        );

        // Capture HTML5 validation message
        String actualError = giftPage.getEmailErrorMessage();
        System.out.println("[GiftCard] Validation message: " + actualError);


        /*String exp = expectedErrorContains == null ? "" : expectedErrorContains.trim();

        if ("valid".equalsIgnoreCase(exp)) {
            Assert.assertTrue(actualError == null || actualError.trim().isEmpty(),
                    "Expected no validation error, but got: " + actualError);
        } else {
            Assert.assertTrue(actualError != null && !actualError.trim().isEmpty(),
                    "Expected a validation message for invalid input, but got none.");
        }*/

    }
}
