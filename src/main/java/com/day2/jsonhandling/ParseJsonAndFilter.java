package com.day2.jsonhandling;
import org.json.*;
import java.util.*;

public class ParseJsonAndFilter {
    public static void main(String[] args) {
        // Define a JSON string containing an array of objects
        String jsonString = "[" +
                "{\"name\": \"Alice\", \"age\": 30}," +
                "{\"name\": \"Bob\", \"age\": 22}," +
                "{\"name\": \"Charlie\", \"age\": 27}" +
                "]";

        // Parse the JSON string into a JSONArray
        JSONArray jsonArray = new JSONArray(jsonString);

        // Create a list to store filtered JSON objects
        List<JSONObject> filteredList = new ArrayList<>();

        // Iterate through the JSONArray
        for (int i = 0; i < jsonArray.length(); i++) {
            // Retrieve each JSONObject from the array
            JSONObject obj = jsonArray.getJSONObject(i);

            // Check if the "age" field is greater than 25
            if (obj.getInt("age") > 25) {
                // Add the matching object to the filtered list
                filteredList.add(obj);
            }
        }

        // Print the filtered JSON objects
        System.out.println("Filtered JSON:");
        System.out.println(filteredList.toString());
    }
}

