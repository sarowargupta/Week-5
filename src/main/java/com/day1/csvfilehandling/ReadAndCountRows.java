package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRows {
    public static void main(String[] args) {

        //file path
        String filepath = "src/main/resources/count.csv";

        //try-with-resource
        try (CSVReader reader = new CSVReader(new FileReader(filepath))) {
            int count =0;
            boolean headerSkiped = false;
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                //skip header row
                if(headerSkiped!=true){
                    headerSkiped=true;
                    continue;
                }
                //print the records from csv file
                System.out.println("ID:" + nextLine[0] + ",Name:" + nextLine[1] + ",Department:" + nextLine[2] + ",Salary:" + nextLine[3]);
                count++;
            }

            //print total number of records
            System.out.println("Total number of records: " + count);

        } catch (IOException | CsvValidationException e) {

            //handle the exception
            System.out.println("Exception occurred while reading CSV: " + e.getMessage());
        }

    }
}
