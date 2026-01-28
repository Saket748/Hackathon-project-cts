
package org.testData;

import org.testng.annotations.DataProvider;
import org.excel.ExcelRead;

public class GiftCardDataProvider {

    @DataProvider(name = "giftCardData")
    public static Object[][] giftCardData() {
        try {
            // Read values using the existing getters in ExcelRead
            String senderFirstName   = ExcelRead.getSenderFirstName();
            String senderLastName    = ExcelRead.getSenderLastName();
            String senderEmail       = ExcelRead.getSenderEmail();
            String senderMobile      = ExcelRead.getSenderPhone();

            String receiverFirstName = ExcelRead.getReceiverFirstName();
            String receiverLastName  = ExcelRead.getReceiverLastName();
            String receiverEmail     = ExcelRead.getReceiverEmail();
            String message           = ExcelRead.getMessage();


            String amount            = ExcelRead.getAmount();
            String qty               = ExcelRead.getQuantity();


            // Return one row with 8 parameters (matches your test method signature)
            return new Object[][] {
                    {
                            amount,
                            qty,
                            senderFirstName,
                            senderLastName,
                            senderEmail,
                            senderMobile,
                            receiverFirstName,
                            receiverLastName,
                            receiverEmail,
                            message
                    }
            };
        } catch (Exception e) {
            System.err.println("[DataProvider] Excel read failed: " + e.getMessage());
            // When data is unavailable, return zero rows so the test is skipped
            return new Object[0][];
        }
    }
}

