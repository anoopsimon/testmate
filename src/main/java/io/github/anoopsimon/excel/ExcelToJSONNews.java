package io.github.anoopsimon.excel;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelToJSONNews {
    public static void main(String[] args) throws IOException {
        String inputDirectory = System.getProperty("user.dir") + "/src/test/resources/testdata/";
        String filepath = inputDirectory + "input.xlsx";

        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath))) {
            JsonArray jsonArray = new JsonArray();

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName(); // Get the sheet name

                JsonArray sheetArray = new JsonArray();

                for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        JsonObject rowObject = new JsonObject();

                        for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                            Cell cell = row.getCell(columnIndex);
                            if (cell != null) {
                                Cell headerCell = sheet.getRow(0).getCell(columnIndex);
                                String headerCellValue = headerCell.getStringCellValue();
                                String cellValue = cell.getStringCellValue();

                                rowObject.addProperty(headerCellValue, cellValue);
                            }
                        }

                        if (!rowObject.entrySet().isEmpty()) {
                            sheetArray.add(rowObject);
                        }
                    }
                }

                JsonObject sheetObject = new JsonObject();
                sheetObject.add(sheetName, sheetArray); // Associate the sheet name with its data
                jsonArray.add(sheetObject);
            }

            try (FileOutputStream outputStream = new FileOutputStream(inputDirectory + "/input.json")) {
                outputStream.write(jsonArray.toString().getBytes());
            }

            System.out.println(jsonArray.toString()); // Print JSON
        }
    }
}
