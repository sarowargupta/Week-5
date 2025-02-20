package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

// person class
class Persons {
    String name;
    int age;
    String city;

    // Constructor to initialize Person attributes
    public Persons(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // Method to convert Person object into a JSON object
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("age", this.age);
        jsonObject.put("city", this.city);
        return jsonObject;
    }
}

public class JavaObjectIntoJsonArray {
    public static void main(String[] args) {
        // Creating a list of Person objects
        List<Persons> people = new ArrayList<>();
        people.add(new Persons("Alice", 23, "New York"));
        people.add(new Persons("Bob", 25, "USA"));
        people.add(new Persons("Charlie", 24, "France"));

        // Creating a JSON array and adding each Person object as JSON
        JSONArray jsonArray = new JSONArray();
        for (Persons person : people) {
            jsonArray.put(person.toJSON());
        }

        // Printing the JSON array in a formatted manner
        System.out.println(jsonArray.toString(4));
    }
}

