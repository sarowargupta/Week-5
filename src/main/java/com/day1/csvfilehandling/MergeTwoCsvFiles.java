package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;

public class MergeTwoCsvFiles {

    public static void main(String[] args) {
        // File paths for the two CSV files
        String students1File = "src/main/resources/student1.csv";
        String students2File = "src/main/resources/Student2.csv";

        // Path for the output file
        String mergedFile = "src/main/resources/studentMerge.csv";

        // Lists to store rows of data from both CSV files
        List<String[]> students1Data = new ArrayList<>();
        List<String[]> students2Data = new ArrayList<>();

        // Read the first CSV file (students1.csv) - Contains ID, Name, Age
        try (CSVReader reader1 = new CSVReader(new FileReader(students1File))) {
            String[] nextRecord;
            // Skip the header row
            reader1.readNext();
            while ((nextRecord = reader1.readNext()) != null) {
                students1Data.add(nextRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        // Read the second CSV file (students2.csv) - Contains ID, Marks, Grade
        try (CSVReader reader2 = new CSVReader(new FileReader(students2File))) {
            String[] nextRecord;
            // Skip the header row
            reader2.readNext();
            while ((nextRecord = reader2.readNext()) != null) {
                students2Data.add(nextRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        // Create the output CSV file (merged_students.csv)
        try (CSVWriter writer = new CSVWriter(new FileWriter(mergedFile))) {
            // Write the header row for the merged file
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"});

            // Merge the data based on the ID and write to the new file
            for (String[] student1 : students1Data) {
                String id1 = student1[0].trim();

                // Find matching data in the second CSV file based on ID
                for (String[] student2 : students2Data) {
                    String id2 = student2[0].trim();

                    if (id1.equals(id2)) {
                        // Merge the data: ID, Name, Age, Marks, Grade
                        String[] mergedStudent = new String[5];
                        mergedStudent[0] = id1;
                        mergedStudent[1] = student1[1]; // Name
                        mergedStudent[2] = student1[2]; // Age
                        mergedStudent[3] = student2[1]; // Marks
                        mergedStudent[4] = student2[2]; // Grade

                        // Write the merged data row
                        writer.writeNext(mergedStudent);
                    }
                }
            }

            System.out.println("Merged data has been written to " + mergedFile);
        } catch (IOException e) {

            //handle error
            System.out.println("Error Occurred: " + e.getMessage());
        }
    }
}


