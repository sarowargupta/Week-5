package com.day2.jsonhandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ConvertCsvDataIntoJson{
    public static void main(String[] args) {

        // Path to CSV file
        String csvFile = "src/main/resources/data.csv";
        JSONArray jsonArray = new JSONArray();

        //try with resource
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> rows = reader.readAll();
            if (rows.isEmpty()) {
                System.out.println("CSV file is empty");
                return;
            }

            // First row as headers
            String[] headers = rows.get(0);

            for (int i = 1; i < rows.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                String[] data = rows.get(i);

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], data[j]);
                }

                jsonArray.put(jsonObject);
            }
        } catch (IOException | CsvException e) {
            //handle exception
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        // Print JSON output
        System.out.println(jsonArray.toString(4));
    }
}

