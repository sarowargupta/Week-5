package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadAndCountRowsTest {
    String fileName = "src/main/resources/count.csv";
    @Test
    void testCsvReading() {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            boolean headSkipped = false;
            while ((nextLine = reader.readNext()) != null) {
                if(!headSkipped){
                    headSkipped = true;
                    continue;
                }
                records.add(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Exception occurred while reading CSV: " + e.getMessage());
        }

        assertEquals(5, records.size());
        assertArrayEquals(new String[]{"101","Alice Williams","Finance","62000"}, records.get(0));
        assertArrayEquals(new String[]{"102","Bob Johnson","HR","50000"}, records.get(1));
        assertArrayEquals(new String[]{"103","John Doe","Marketing","55000"}, records.get(2));
      }
}
