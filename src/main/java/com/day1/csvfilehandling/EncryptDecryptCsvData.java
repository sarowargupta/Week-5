package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptDecryptCsvData {

    // Encryption key (In practice, store this securely)
    private static final String SECRET_KEY = "1234567890123456";

    // Method to encrypt the data
    public static String encrypt(String data) {
        try {
            SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to decrypt the data
    public static String decrypt(String encryptedData) {
        try {
            SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to write data to a CSV file (with encrypted sensitive fields)
    public static void writeToCSV(String csvFile) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {
            // Writing the header row
            csvWriter.writeNext(new String[]{"Employee ID", "Name", "Email", "Salary"});

            // Sample data
            String[] employee1 = {"1", "John", "john@example.com", "50000"};
            String[] employee2 = {"2", "Alice", "alice@example.com", "55000"};
            String[] employee3 = {"3", "Bob", "bob@example.com", "60000"};

            // Encrypt the Email and Salary fields before writing to CSV
            employee1[2] = encrypt(employee1[2]);  // Encrypt Email
            employee1[3] = encrypt(employee1[3]);  // Encrypt Salary

            employee2[2] = encrypt(employee2[2]);
            employee2[3] = encrypt(employee2[3]);

            employee3[2] = encrypt(employee3[2]);
            employee3[3] = encrypt(employee3[3]);

            // Write the encrypted employee data to the CSV file
            csvWriter.writeNext(employee1);
            csvWriter.writeNext(employee2);
            csvWriter.writeNext(employee3);

            System.out.println("Encrypted CSV report generated successfully at: " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read the encrypted CSV file and decrypt sensitive fields
    public static void readFromCSV(String csvFile) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] nextRecord;

            // Skip header
            csvReader.readNext();

            // Read the encrypted data and decrypt it
            while ((nextRecord = csvReader.readNext()) != null) {
                String employeeId = nextRecord[0];
                String name = nextRecord[1];
                String email = decrypt(nextRecord[2]);  // Decrypt Email
                String salary = decrypt(nextRecord[3]);  // Decrypt Salary

                // Print the decrypted records
                System.out.println("Employee ID: " + employeeId);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Salary: " + salary);
                System.out.println("-----------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String csvFile = "src/main/resources/encrypt.csv";

        // Write encrypted data to CSV
        writeToCSV(csvFile);

        // Read and decrypt the data from the CSV
        readFromCSV(csvFile);
    }
}
