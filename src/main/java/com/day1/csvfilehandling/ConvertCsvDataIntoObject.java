package com.day1.csvfilehandling;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;

class Student {
    private String ID;
    private String name;
    private String age;

    // Constructor
    public Student(String ID, String name, String age) {
        this.ID = ID;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}


public class ConvertCsvDataIntoObject {
    public static void main(String[] args) {
        //file path
        String csvFile = "src/main/resources/students.csv";

        // List to store Student objects
        List<Student> students = new ArrayList<>();

        // Read CSV file and convert each row into a Student object
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] nextRecord;

            // Skip the header
            csvReader.readNext();

            // Read each record (line) from the CSV
            while ((nextRecord = csvReader.readNext()) != null) {
                // Assuming CSV columns are ID, Name, Age (index 0, 1, 2)
                String ID = nextRecord[0].trim();
                String name = nextRecord[1].trim();
                String age = nextRecord[2].trim();

                // Create Student object and add it to the list
                Student student = new Student(ID, name, age);
                students.add(student);
            }

            // Print all Student objects
            for (Student student : students) {
                System.out.println(student);
            }

        } catch (IOException e) {
            //handle error
            System.out.println("Error occurred: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
