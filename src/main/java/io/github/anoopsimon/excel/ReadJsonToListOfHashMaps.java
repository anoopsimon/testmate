package io.github.anoopsimon.excel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadJsonToListOfHashMaps {
    public static void main(String[] args) throws IOException, JSONException {
        getData();
    }

    public static HashMap<String, List<HashMap<String, String>>> getData() throws IOException {
        String inputDirectory = System.getProperty("user.dir")+"/src/test/resources/testdata/";

        String jsonFilePath = inputDirectory + "input.json"; // Replace with the actual path to your JSON file

        // Read JSON file content
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        // Parse JSON array
        JSONArray jsonArray = new JSONArray(jsonContent);

        // Create a hashmap to store all sheets data
        HashMap<String, List<HashMap<String, String>>> jsonData = new HashMap<>();

        // Iterate through JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject sheetObject = jsonArray.getJSONObject(i);

            // Iterate through the keys (sheet names) in the JSON object
            for (String sheetName : sheetObject.keySet()) {
                List<HashMap<String, String>> rowsList = new ArrayList<>();

                JSONArray sheetArray = sheetObject.getJSONArray(sheetName);

                // Iterate through JSON array for rows
                for (int j = 0; j < sheetArray.length(); j++) {
                    JSONObject rowObject = sheetArray.getJSONObject(j);
                    HashMap<String, String> rowMap = new HashMap<>();

                    // Iterate through keys in row object
                    for (String key : rowObject.keySet()) {
                        rowMap.put(key, rowObject.getString(key));
                    }

                    rowsList.add(rowMap);
                }

                // Add the sheet data to the jsonData hashmap
                jsonData.put(sheetName, rowsList);
            }
        }

        // Print the jsonData hashmap
        for (String sheetName : jsonData.keySet()) {
            System.out.println("Sheet Name: " + sheetName);
            for (HashMap<String, String> row : jsonData.get(sheetName)) {
                System.out.println("Row Data: " + row);
            }
        }


        return jsonData;
    }

}
