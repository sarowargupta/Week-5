package com.day2.jsonhandling;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ReadJsonFile {
    public static void main(String[] args) {

        //file path
        String filePath = "src/main/resources/data.json";

        try {
            // Read the JSON file into a String
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert String to JSON Object
            JSONObject jsonObject = new JSONObject(content);

            // Iterate through the keys and print values
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println(key + " : " + jsonObject.get(key));
            }
        } catch (IOException e) {
            //handle error
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }
}

