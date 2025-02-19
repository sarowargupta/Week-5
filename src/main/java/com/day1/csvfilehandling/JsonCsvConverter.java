package com.day1.csvfilehandling;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Students {
    private int id;
    private String name;
    private int age;
    private String email;

    //  Default Constructor (Important for Jackson)
    public Students() {}

    //  Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

public class JsonCsvConverter {

    //  Convert JSON to CSV
    public static void jsonToCsv(String jsonFile, String csvFile) {
        try {
            File file = new File(jsonFile);
            if (!file.exists()) {
                System.out.println(" Error: JSON file not found at " + file.getAbsolutePath());
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            List<Students> students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Students.class));

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {
                csvWriter.writeNext(new String[]{"ID", "Name", "Age", "Email"});

                for (Students student : students) {
                    csvWriter.writeNext(new String[]{
                            String.valueOf(student.getId()),
                            student.getName(),
                            String.valueOf(student.getAge()),
                            student.getEmail()
                    });
                }
                System.out.println(" JSON to CSV conversion completed: " + csvFile);
            }
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  Convert CSV to JSON
    public static void csvToJson(String csvFile, String jsonFile) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));
            List<String[]> rows = csvReader.readAll();

            List<Students> students = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Students student = new Students();
                student.setId(Integer.parseInt(row[0]));
                student.setName(row[1]);
                student.setAge(Integer.parseInt(row[2]));
                student.setEmail(row[3]);
                students.add(student);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFile), students);
            System.out.println(" CSV to JSON conversion completed: " + jsonFile);
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  Main Method
    public static void main(String[] args) {
        String jsonFile = "src/main/resources/students.json";
        String csvFile = "src/main/resources/student_.csv";

        try {
            jsonToCsv(jsonFile, csvFile);
            csvToJson(csvFile, "students_output.json");
        } catch (Exception e) {
            System.out.println(" Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
