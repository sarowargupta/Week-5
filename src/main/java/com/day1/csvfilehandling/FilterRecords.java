package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class FilterRecords {
    public static void main(String[] args)  {

        //try with resource
        try(CSVReader reader = new CSVReader(new FileReader("src/main/resources/filter.csv"))){
            String[] nextLine;

            //read the data from csv file
            while ((nextLine = reader.readNext()) != null) {
                    //convert numeric string into integer
                    int marks = Integer.parseInt(nextLine[3]);
                    if (marks > 80) {

                        //print the details of students whose marks is greater than 80
                        System.out.println("ID:" + nextLine[0] + ",Name:" + nextLine[1] + ",age:" + nextLine[2] + ",Marks:" + nextLine[3]);
                    }
            }
        } catch (IOException | CsvValidationException e) {

            //handle the exception
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
