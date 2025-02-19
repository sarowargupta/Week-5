package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.regex.*;

public class ValidateCsvData {

    // Regular expression for email validation
    private static final String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(regex);

    // Regular expression for phone number validation (exactly 10 digits)
    private static final String PHONE_REGEX = "^\\d{10}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    public static void main(String[] args) {
        // Path to the CSV file
        String csvFile = "src/main/resources/Validate.csv";

        // Read the CSV file using OpenCSV
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] nextRecord;
            int lineNumber = 0;

            // Skip the header row
            csvReader.readNext();

            // Read each record (line) from the CSV
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length < 3) {
                    System.out.println("Skipping invalid row at line " + (lineNumber + 1));
                    continue;
                }

                lineNumber++;

                // Assuming the "Email" is in the second column and "Phone Number" is in the third column
                String email = nextRecord[1].trim();
                String phoneNumber = nextRecord[2].trim();

                // Validate Email
                if (!isValidEmail(email)) {
                    System.out.println("Invalid Email in line " + lineNumber + ": " + email);
                }

                // Validate Phone Number
                if (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid Phone Number in line " + lineNumber + ": " + phoneNumber);
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    // Method to validate email using regex
    private static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate phone number using regex
    private static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
