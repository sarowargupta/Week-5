package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriteDataCsvFileTest {
    private static final String filePath = "src/main/resources/employee.csv";

    @Test
    void testFileExists() {
        File file = new File(filePath);
        assertTrue(file.exists(), "CSV file should be created");
    }

    @Test
    void testCsvContent() {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();

            assertNotNull(records, "CSV file should not be empty");
            assertEquals(6, records.size());
            assertArrayEquals(new String[]{"ID", "Name", "Department", "Salary"}, records.get(0), "Header should match");
            assertArrayEquals(new String[]{"101", "Alice Williams", "Finance", "62000"}, records.get(1), "First row should match");
        } catch (IOException | CsvException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }


}
