package com.day2.jsonhandling;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeTwoJson {
    public static void main(String[] args) {
        // Example JSON strings
        String jsonString1 = "{\"name\": \"Alice\", \"age\": 30}";
        String jsonString2 = "{\"city\": \"New York\", \"country\": \"USA\"}";

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse JSON strings into JsonNode objects
            JsonNode jsonNode1 = objectMapper.readTree(jsonString1);
            JsonNode jsonNode2 = objectMapper.readTree(jsonString2);

            // Merge JSON objects
            ObjectNode mergedJson = objectMapper.createObjectNode();
            mergedJson.setAll((ObjectNode) jsonNode1);
            mergedJson.setAll((ObjectNode) jsonNode2);

            // Print merged JSON object
            System.out.println("Merged JSON:");
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));
        } catch (Exception e) {
            // Handle parsing exceptions
            System.out.println("Error merging JSON objects: " + e.getMessage());
        }
    }
}
