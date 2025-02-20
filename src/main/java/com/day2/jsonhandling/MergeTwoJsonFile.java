package com.day2.jsonhandling;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MergeTwoJsonFile {
    public static void main(String[] args) {

        // Path to first JSON file
        String filePath1 = "src/main/resources/file1.json";

        // Path to second JSON file
        String filePath2 = "src/main/resources/file2.json";

        try {
            // Read both JSON files into Strings
            String content1 = new String(Files.readAllBytes(Paths.get(filePath1)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));

            // Convert Strings to JSON Objects
            JSONObject json1 = new JSONObject(content1);
            JSONObject json2 = new JSONObject(content2);

            // Merge the two JSON objects
            for (String key : json2.keySet()) {
                json1.put(key, json2.get(key));
            }

            // Print the merged JSON object
            System.out.println(json1.toString(4));
        } catch (IOException e) {
            //handle error
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }
}
