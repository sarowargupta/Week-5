package com.day1.csvfilehandling;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataCsvFile {
    public static void main(String[] args) {

        //file path
        String filepath = "src/main/resources/employee.csv";

        //try with resource
        try(CSVWriter writer = new CSVWriter(new FileWriter(filepath))){
            String[] header = {"ID","Name","Department","Salary"};
            String[] employee1 =  {"101", "Alice Williams", "Finance", "62000"};
            String[] employee2 = {"102", "Bob Johnson", "HR", "50000"};
            String[] employee3 = {"103", "John Doe", "Marketing", "55000"};
            String[] employee4 = {"105", "Jane Smith", "Engineering", "59000"};
            String[] employee5 = {"105", "Michael Brown", "Sales", "80000"};

            //write all the information into file
            writer.writeNext(header);
            writer.writeNext(employee1);
            writer.writeNext(employee2);
            writer.writeNext(employee3);
            writer.writeNext(employee4);
            writer.writeNext(employee5);
            System.out.println("CSV file written successfully using OpenSCV!");
        } catch (IOException e){

            //handle the exception
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
