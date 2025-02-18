package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.*;

public class SortCsvRecord {

    public static void main(String[] args) {
        //file path for the input CSV
        String inputFilePath = "src/main/resources/salary.csv";

        // List to store employee records
        List<String[]> records;

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            // Read all records from the CSV
            records = reader.readAll();

            // Skip the header and process the employee data
            List<String[]> data = records.subList(1, records.size());

            // Sort the data based on the Salary (column 3) in descending order
            data.sort((record1, record2) -> {
                double salary1 = Double.parseDouble(record1[3]);
                double salary2 = Double.parseDouble(record2[3]);
                // descending order
                return Double.compare(salary2, salary1);
            });

            // Print the top 5 highest-paid employees
            System.out.println("Top 5 Highest-Paid Employees:");
            // Print header
            System.out.println(String.join(", ", records.get(0)));

            for (int i = 0; i < 5 && i < data.size(); i++) {
                String[] record = data.get(i);
                System.out.println(String.join(", ", record));
            }

        } catch (IOException | CsvException e) {
            System.out.println("error occurred: " + e.getMessage());
        }
    }
}
