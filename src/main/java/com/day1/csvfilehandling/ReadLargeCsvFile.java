package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class ReadLargeCsvFile {

    public static void main(String[] args) {
        // Path to the large CSV file
        String largeCsvFile = "src/main/resources/large.csv";

        // Buffer size to read chunks of 100 lines at a time
        int chunkSize = 100;

        try (CSVReader csvReader = new CSVReader(new FileReader(largeCsvFile))) {
            String[] nextRecord;
            int recordCount = 0;
            int chunkCount = 0;
            List<String[]> chunkRecords = new ArrayList<>();

            // Read each record (line) from the CSV file
            while ((nextRecord = csvReader.readNext()) != null) {
                chunkRecords.add(nextRecord);
                recordCount++;

                // Process the chunk when we have reached the chunk size
                if (chunkRecords.size() == chunkSize) {
                    chunkCount++;
                    processChunk(chunkRecords, chunkCount);  // Process the chunk
                    chunkRecords.clear();  // Clear the chunk after processing
                }
            }

            // Process any remaining records in the last chunk if less than chunk size
            if (!chunkRecords.isEmpty()) {
                chunkCount++;
                processChunk(chunkRecords, chunkCount);
            }

            System.out.println("Total records processed: " + recordCount);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processChunk(List<String[]> chunkRecords, int chunkCount) {
        System.out.println("Processing chunk " + chunkCount + " with " + chunkRecords.size() + " records.");
        for (String[] record : chunkRecords) {
            //printing the record as an example
            System.out.println(Arrays.toString(record));
        }
    }
}
