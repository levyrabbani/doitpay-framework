package com.mycompany.app.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Utility class for reading test data from Excel files.
 * Works with .xlsx format using Apache POI.
 *
 * Example usage:
 * ExcelUtils excel = new ExcelUtils("src/test/resources/testdata/users.xlsx", "LoginData");
 * String username = excel.getCellData(1, "Username");
 */
public class ExcelUtils {
    private Sheet sheet;
    private Workbook workbook;
    private Map<String, Integer> columnMap;

    /**
     * Constructor
     * @param filePath - path to Excel file
     * @param sheetName - name of the sheet to read
     */
    public ExcelUtils(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " not found in " + filePath);
            }
            mapColumns();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage(), e);
        }
    }

    private void mapColumns() {
        columnMap = new HashMap<>();
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            columnMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }
    }

    public static List<Map<String, String>> getLoginData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();

            // --- Ambil header dulu ---
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            // --- Ambil data baris demi baris ---
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    rowData.put(headers.get(i), cell.getStringCellValue());
                }

                dataList.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
