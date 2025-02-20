package com.day2.jsonhandling;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class IplAndCensorAnalyzer {

    // Process JSON file by applying censorship rules
    public static void processJson(String inputFile, String outputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(inputFile));

        if (rootNode.isArray()) {
            for (JsonNode match : rootNode) {
                ((ObjectNode) match).put("team1", maskTeamName(match.get("team1").asText()));
                ((ObjectNode) match).put("team2", maskTeamName(match.get("team2").asText()));
                ((ObjectNode) match).put("player_of_match", "REDACTED");
            }
        }

        objectMapper.writeValue(new File(outputFile), rootNode);
    }

    // Process CSV file by applying censorship rules
    public static void processCsv(String inputFile, String outputFile) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            // Process CSV file by applying censorship rules
            List<String[]> allRows = reader.readAll();
            if (allRows.isEmpty()) return;

            String[] headers = allRows.get(0);

            // Write headers to the new CSV file
            writer.writeNext(headers);

            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                if (row.length < 7) continue; // Ensure row has enough columns to avoid ArrayIndexOutOfBoundsException
                row[1] = maskTeamName(row[1]); // Assuming team1 is at index 1
                row[2] = maskTeamName(row[2]); // Assuming team2 is at index 2
                row[6] = "REDACTED"; // Assuming player_of_match is at index 6
                writer.writeNext(row);
            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    // Mask the last word in the team name with "***"
    private static String maskTeamName(String teamName) {
        String[] parts = teamName.split(" ");
        if (parts.length > 1) {
            parts[parts.length - 1] = "***";
        }
        return String.join(" ", parts);
    }

    public static void main(String[] args) {
        try {
            processJson("src/main/resources/ipl_data.json", "src/main/resources/censored_ipl_data.json");
            processCsv("src/main/resources/ipl_data.csv", "src/main/resources/censored_ipl_data.csv");
            System.out.println("Censorship complete. Check output files.");
        } catch (IOException e) {

            //handle error
            e.printStackTrace();
        }
    }
}