
package org.excel;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

    public static XSSFWorkbook workbook() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/java/org/excel/Hackathonexcel.xlsx");
        return new XSSFWorkbook(fis);
    }

    public static String searchItem() throws IOException {
        XSSFWorkbook wb = workbook();
        XSSFSheet sheet = wb.getSheet("Search Item");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        String value = cell.toString().trim();
        wb.close();
        return value;
    }
    public static String getAmount() throws IOException {
        XSSFWorkbook wb = workbook();
        XSSFSheet sheet = wb.getSheet("Denomination");
        Row row = sheet.getRow(1);        // row 2 (0-based index)
        Cell cell = row.getCell(0);       // column A
        String v = cell.toString().trim();
        wb.close();
        return v;
    }

    public static String getQuantity() throws IOException {
        XSSFWorkbook wb = workbook();
        XSSFSheet sheet = wb.getSheet("Denomination");
        Row row = sheet.getRow(1);        // row 2
        Cell cell = row.getCell(1);       // column B
        String v = cell.toString().trim();
        wb.close();
        return v;
    }

    public static String getSenderFirstName() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Sender Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(0);
        return cell.toString().trim();
    }

    public static String getSenderLastName() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Sender Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(1);
        return cell.toString().trim();
    }
    public static String getSenderEmail() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Sender Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(2);
        return cell.toString().trim();
    }

    public static String getSenderPhone() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Sender Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(3);
        return cell.toString().trim();
    }


    public static String getReceiverFirstName() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Receiver Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(0);
        return cell.toString().trim();
    }

    public static String getReceiverLastName() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Receiver Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(1);
        return cell.toString().trim();
    }

    public static String getReceiverEmail() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Receiver Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(2);
        return cell.toString().trim();
    }

    public static String getMessage() throws IOException {
        XSSFWorkbook wb = workbook();

        XSSFSheet SENDER_SHEET = wb.getSheet("Receiver Details");
        Row row = SENDER_SHEET.getRow(1);
        Cell cell = row.getCell(3);
        return cell.toString().trim();
    }

}


