package com.day1.csvfilehandling;
import com.opencsv.CSVWriter;
import java.io.*;
import java.sql.*;

public class GenerateCsvReportFromDatabase {

    public static void main(String[] args) {
        // Database connection details
        String url = "src/main/resources/employee_db.sql";
        String username = "root";  // Replace with your database username
        String password = "password";  // Replace with your database password

        // SQL query to fetch employee records
        String query = "SELECT employee_id, name, department, salary FROM employees";

        // Path to the CSV file where report will be saved
        String csvFile = "src/main/resources/report.csv";  // Replace with your file path

        // Create a connection and write to the CSV file
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {

            // Write the header row in the CSV file
            csvWriter.writeNext(new String[]{"Employee ID", "Name", "Department", "Salary"});

            // Write employee records to the CSV file
            while (resultSet.next()) {
                // Fetch employee details from the result set
                String employeeId = resultSet.getString("employee_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");

                // Write record to CSV file
                csvWriter.writeNext(new String[]{employeeId, name, department, String.valueOf(salary)});
            }

            System.out.println("CSV report generated successfully at: " + csvFile);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
