package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecordInCsv{

    // Method that returns search result instead of printing
    public static String searchEmployeeByName(String filepath, String searchName) {
        boolean found = false;
        StringBuilder result = new StringBuilder();

        try (CSVReader reader = new CSVReader(new FileReader(filepath))) {
            String[] nextLine;
            boolean headerSkipped = false;

            while ((nextLine = reader.readNext()) != null) {
                if (!headerSkipped) {

                    // Skip the header row
                    headerSkipped = true;
                    continue;
                }

                // Assuming Name is in the second column
                String name = nextLine[1];

                if (name.equalsIgnoreCase(searchName)) {
                    result.append("Employee Found:\n");
                    result.append("Department:").append(nextLine[2]).append("\n");
                    result.append("Salary:$").append(nextLine[3]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.append("Employee ").append(searchName).append(" not found.");
            }

        } catch (IOException | CsvValidationException e) {
            result.append("Error reading CSV: ").append(e.getMessage());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // File path
        String filepath = "src/main/resources/employees.csv";

        // Create a scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim();

        // Call the method and print result
        System.out.println(searchEmployeeByName(filepath, searchName));
    }
}
