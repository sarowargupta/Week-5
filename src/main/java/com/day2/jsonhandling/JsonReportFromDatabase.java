package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class JsonReportFromDatabase{
    public static void main(String[] args) {
        // Define database connection parameters
        String url = "src/main/resources/table.sql";
        String user = "your_username";
        String password = "your_password";

        // Define the SQL query to fetch data
        String query = "SELECT id, name, age, city FROM users";

        // Create a JSON array to store the results
        JSONArray jsonArray = new JSONArray();

        try (
                // Establish a connection to the database
                Connection conn = DriverManager.getConnection(url, user, password);

                // Create a statement to execute the SQL query
                Statement stmt = conn.createStatement();

                // Execute the query and store the result set
                ResultSet rs = stmt.executeQuery(query)
        ) {

            // Iterate through the result set
            while (rs.next()) {
                // Create a JSON object to store row data
                JSONObject jsonObject = new JSONObject();

                // Add column data to the JSON object
                jsonObject.put("id", rs.getInt("id"));
                jsonObject.put("name", rs.getString("name"));
                jsonObject.put("age", rs.getInt("age"));
                jsonObject.put("city", rs.getString("city"));

                // Add the JSON object to the JSON array
                jsonArray.put(jsonObject);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Database error: " + e.getMessage());
        }

        // Print the JSON report in a formatted structure
        System.out.println(jsonArray.toString(4));
    }
}
