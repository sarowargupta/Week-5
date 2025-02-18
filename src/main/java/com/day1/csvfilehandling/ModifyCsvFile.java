package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyCsvFile {
    public static void main(String[] args) {
        // Define file paths
        String inputFilePath = "src/main/resources/input.csv";
        String outputFilePath = "src/main/resources/output.csv";

        // List to store updated records
        List<String[]> records = new ArrayList<>();


        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] nextLine;
            boolean headerSkipped = false;

            while ((nextLine = reader.readNext()) != null) {
                // Store header row as is
                if (!headerSkipped) {
                    records.add(nextLine);
                    headerSkipped = true;
                    continue;
                }

                // Check if the employee is from the IT department (assuming department is in the 3rd column)
                if (nextLine[2].equalsIgnoreCase("IT")) {
                    // Increase the salary (assuming salary is in the 4th column)
                    double currentSalary = Double.parseDouble(nextLine[3]);
                    double newSalary = currentSalary * 1.10; // Increase by 10%
                    nextLine[3] = String.format("%.2f", newSalary); // Update salary with 2 decimal places

                }

                // Add the updated record to the list
                records.add(nextLine);
            }

            // Write the updated records to a new CSV file
            try (CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {
                writer.writeAll(records);
            }

            System.out.println("Updated CSV file saved as " + outputFilePath);

        } catch (IOException | CsvValidationException e) {
            System.out.println("Error Occurred:" + e.getMessage());
        }
    }
}

