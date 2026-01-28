package org.testCases;

import org.testng.annotations.Test;
import org.PagesObject.GiftCardsPage;
import org.PagesObject.HomePage;
import org.testBase.BaseClass;


public class TC_GiftCards_DDT extends BaseClass {

    @Test(dataProvider = "giftCardData", dataProviderClass = org.testData.GiftCardDataProvider.class)
    public void verify_invalid_input_from_excel(
            String amount,
            String qty,
            String senderFirstName,
            String senderLastName,
            String senderEmail,
            String senderMobile,
            String receiverFirstName,
            String receiverLastName,
            String receiverEmail,
            String message
    ) {
        // 1) Navigate and open Gift Cards
        HomePage homePage = new HomePage(driver);
        homePage.openGiftCards();

        // 2) Switch to the new tab (if opened)
        switchToNewestTab();

        // 3) Complete the form (the page object handles scroll/click/waits)
        GiftCardsPage giftPage = new GiftCardsPage(driver);
        giftPage.completeGiftCardForm(
                amount,qty,senderFirstName, senderLastName, senderEmail, senderMobile,
                receiverFirstName, receiverLastName, receiverEmail, message
        );

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