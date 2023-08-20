package io.github.anoopsimon.excel;

import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelToJsonArrayPerRow {
    public static void main(String[] args) throws IOException {
            String filepath = System.getProperty("user.dir")+"/src/test/resources/testdata/input.xlsx";

            try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath))) {
                JSONArray jsonArray = new JSONArray();

                for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                    Sheet sheet = workbook.getSheetAt(sheetIndex);
                    String sheetName = sheet.getSheetName(); // Get the sheet name

                    JSONArray sheetArray = new JSONArray();

                    for (Row row : sheet) {
                        JSONObject rowObject = new JSONObject();

                        for (Cell cell : row) {
                            rowObject.put(sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue(),
                                    cell.getStringCellValue());
                        }

                        sheetArray.put(rowObject);
                    }

                    JSONObject sheetObject = new JSONObject();
                    sheetObject.put(sheetName, sheetArray); // Associate the sheet name with its data
                    jsonArray.put(sheetObject);
                }


                Files.write(Paths.get("duke.json"), jsonArray.toString(2).getBytes());

                System.out.println(jsonArray.toString(2)); // Print formatted JSON
            }
        }
}

