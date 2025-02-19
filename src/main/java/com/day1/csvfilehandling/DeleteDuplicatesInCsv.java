package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;

public class DeleteDuplicatesInCsv {

    public static void main(String[] args) {
        // Path to the CSV file
        String csvFile = "src/main/resources/duplicate.csv";

        // A set to track unique IDs
        Set<String> uniqueIds = new HashSet<>();
        // A list to store duplicate records
        List<String[]> duplicateRecords = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] nextRecord;
            int lineNumber = 0;

            // Skip the header
            csvReader.readNext();

            // Read each record (line) from the CSV file
            while ((nextRecord = csvReader.readNext()) != null) {
                lineNumber++;

                // Assuming the "ID" is in the first column (index 0)
                String id = nextRecord[0].trim();

                // Check if the ID is already encountered (duplicate)
                if (uniqueIds.contains(id)) {
                    // If duplicate, add the record to the duplicateRecords list
                    duplicateRecords.add(nextRecord);
                } else {
                    // Otherwise, add the ID to the unique set
                    uniqueIds.add(id);
                }
            }

            // Print the duplicate records if any
            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records:");
                for (String[] record : duplicateRecords) {
                    System.out.println(Arrays.toString(record));
                }
            }
        } catch (IOException e) {
            //handle error
            e.printStackTrace();
        } catch (CsvValidationException e) {
            //handle error
            throw new RuntimeException(e);
        }
    }
}
