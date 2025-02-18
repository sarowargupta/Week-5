package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCsvFile {
    public static void main(String[] args)  {

        //try with resource
        try(CSVReader reader = new CSVReader(new FileReader("src/main/resources/student.csv"))){
            String[] nextLine;

            //read the data from csv file
            while ((nextLine = reader.readNext()) != null){

                //print the data from csv file
                System.out.println("ID:" + nextLine[0] + ",Name:" + nextLine[1] + ",age:" + nextLine[2] + ",Marks:" + nextLine[3]);
            }
        } catch (IOException | CsvValidationException e) {

            //handle the exception
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
