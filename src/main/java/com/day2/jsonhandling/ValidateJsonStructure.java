package com.day2.jsonhandling;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ValidateJsonStructure {
    public static void main(String[] args) {
        // Example JSON string
        String jsonString = "{" +
                "\"name\": \"Alice\", " +
                "\"age\": 30" +
                "}";

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse JSON string into JsonNode to validate structure
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // If no exception occurs, JSON is valid
            System.out.println("Valid JSON structure:");
            System.out.println(jsonNode.toPrettyString());
        } catch (JsonProcessingException e) {
            // Catch and display JSON parsing errors
            System.out.println("Invalid JSON structure: " + e.getMessage());
        }
    }
}
