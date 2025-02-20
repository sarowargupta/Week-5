package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

// Class representing a Person
class CheckPerson {
    String name;
    int age;
    String city;
    String email;

    // Constructor to initialize Person attributes
    public CheckPerson(String name, int age, String city, String email) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.email = email;
    }

    // Method to convert Person object into a JSON object
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("age", this.age);
        jsonObject.put("city", this.city);
        jsonObject.put("email", this.email);
        return jsonObject;
    }
}

public class ValidateEmail {
    public static void main(String[] args) {
        // Creating a list of Person objects
        List<CheckPerson> people = new ArrayList<>();
        people.add(new CheckPerson("Alice", 30, "New York", "Alice.doe@example.com"));
        people.add(new CheckPerson("Bob", 25, "France", "bob.smith@example.com"));
        people.add(new CheckPerson("Charlie", 35, "America", "charlie.johnson@example.com"));

        // JSON Schema for validating email field
        String schemaStr = """
        {
            "type": "object",
            "properties": {
                "email": {"type": "string", "format": "email"}
            },
            "required": ["email"]
        }
        """;

        JSONObject schemaJson = new JSONObject(schemaStr);
        Schema schema = SchemaLoader.load(schemaJson);

        // Creating a JSON array and adding only valid emails
        JSONArray jsonArray = new JSONArray();
        for (CheckPerson person : people) {
            JSONObject personJson = person.toJSON();
            try {
                // Validate email field
                schema.validate(personJson);
                jsonArray.put(personJson);
            } catch (JSONException e) {
                //handle exception
                System.out.println("Invalid email for: " + person.name);
            }
        }

        // Printing the filtered JSON array in a formatted manner
        System.out.println(jsonArray.toString(4));
    }
}

