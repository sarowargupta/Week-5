package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReadCsvFileTest {

    String fileName = "src/main/resources/student.csv";
    @Test
    void testCsvReading() {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                records.add(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Exception occurred while reading CSV: " + e.getMessage());
        }

        assertEquals(3, records.size());
        assertArrayEquals(new String[]{"101", "John Doe", "23", "90"}, records.get(0));
        assertArrayEquals(new String[]{"102", "Jane Smith", "22", "89"}, records.get(1));
        assertArrayEquals(new String[]{"103", "Michael Brown", "21", "85"}, records.get(2));
    }
}
