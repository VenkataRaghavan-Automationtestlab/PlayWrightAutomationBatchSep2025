package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * Reads an Excel sheet and returns all rows as String arrays
     * Skips the header row
     */
    public static List<String[]> readSheet(String path, String sheetName) {
        List<String[]> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) return rows;

            int firstRowNum = sheet.getFirstRowNum() + 1; // skip header
            int lastRowNum = sheet.getLastRowNum();

            for (int i = firstRowNum; i <= lastRowNum; i++) {
                Row r = sheet.getRow(i);
                if (r == null) continue;

                List<String> cells = new ArrayList<>();
                for (int j = 0; j < r.getLastCellNum(); j++) {
                    Cell c = r.getCell(j);
                    if (c == null) {
                        cells.add("");
                        continue;
                    }

                    // Convert cell to string safely
                    switch (c.getCellType()) {
                        case STRING -> cells.add(c.getStringCellValue());
                        case NUMERIC -> cells.add(String.valueOf((int) c.getNumericCellValue()));
                        case BOOLEAN -> cells.add(String.valueOf(c.getBooleanCellValue()));
                        case FORMULA -> cells.add(c.getCellFormula());
                        case BLANK, _NONE, ERROR -> cells.add("");
                        default -> cells.add(c.toString());
                    }
                }
                rows.add(cells.toArray(new String[0]));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel sheet: " + e.getMessage(), e);
        }

        return rows;
    }
}
