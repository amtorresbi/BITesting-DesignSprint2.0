package libraries;

import helpers.Bi_helper;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {
    public void writeCellValue(String filepath, String sheetName, int rowNumber, int cellNumber, String resultText)
            throws IOException {

        File file = new File(filepath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);

        XSSFSheet newSheet = newWorkbook.getSheet(sheetName);

        XSSFRow row = newSheet.getRow(rowNumber);

        @SuppressWarnings("unused")
        XSSFCell firstCell = row.getCell(cellNumber - 1);

        XSSFCell nextCell = row.createCell(cellNumber);

        nextCell.setCellValue(resultText);

        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(file);

        newWorkbook.write(outputStream);

        outputStream.close();
        newWorkbook.close();

    }

    public void writeBitacora2(String filepath, int rowNumber, String autorizacion, String monedaD,
                               String tipoCuentaDebito, String cuentaDebito, String montoD, String monedaC, String tipoCuentaCredito,
                               String cuentaCredito, String montoC, String tipoCambio) throws IOException {

        String resultString = "";
        String resultString2 = "";
        String montoC2 = montoC;

        File file = new File(filepath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);

        XSSFSheet newSheet = newWorkbook.getSheetAt(0);

        newSheet.shiftRows(rowNumber, newSheet.getLastRowNum(), 1, true, false);

        // XSSFRow row = newSheet.getRow(rowNumber);
        XSSFRow row = newSheet.createRow(rowNumber);

        XSSFCellStyle cellStyle = newWorkbook.createCellStyle();

        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        XSSFCell nextCell = row.createCell(1);
        nextCell.setCellValue(autorizacion);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(2);
        nextCell.setCellValue(Bi_helper.Hoy(1));
        nextCell.setCellStyle(cellStyle);

        if (monedaD.startsWith("BIQ") || monedaD.startsWith("BI Q")) resultString = "Q.";
        else if (monedaD.startsWith("BI$") || monedaD.startsWith("BI $")) resultString = "$.";
        ;
        nextCell = row.createCell(3);
        nextCell.setCellValue(resultString);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(4);
        nextCell.setCellValue(tipoCuentaDebito + " " + resultString);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(5);
        nextCell.setCellValue(cuentaDebito);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(6);
        nextCell.setCellValue(montoD);
        nextCell.setCellStyle(cellStyle);

        if (monedaC.startsWith("BIQ") || monedaC.startsWith("BI Q")) resultString2 = "Q.";
        else if (monedaC.startsWith("BI$") || monedaC.startsWith("BI $")) resultString2 = "$.";
        nextCell = row.createCell(7);
        nextCell.setCellValue(resultString2);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(8);
        nextCell.setCellValue(tipoCuentaCredito + " " + resultString2);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(9);
        nextCell.setCellValue(cuentaCredito);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(10);
        if (montoC.contains("Q") || montoC.contains("$")) {
            montoC2 = montoC.substring(2);
        }
        nextCell.setCellValue(montoC2);
        nextCell.setCellStyle(cellStyle);

        nextCell = row.createCell(11);
        if (tipoCambio == "N/A") {
            nextCell.setCellValue(tipoCambio);
        } else {
            nextCell.setCellValue(tipoCambio.substring(2));
        }
        nextCell.setCellStyle(cellStyle);

        FileOutputStream outputStream = new FileOutputStream(file);

        newWorkbook.write(outputStream);

        // outputStream.close();
        newWorkbook.close();

    }
}
