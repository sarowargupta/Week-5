package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

// example class
class ExamplePerson {
    String name;
    int age;
    String city;

    // Constructor to initialize Person attributes
    public ExamplePerson(String name, int age, String city) {
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

public class FilterJsonData {
    public static void main(String[] args) {
        // Creating a list of Person objects
        List<ExamplePerson> people = new ArrayList<>();
        people.add(new ExamplePerson("John", 30, "New York"));
        people.add(new ExamplePerson("Jane", 25, "Paris"));
        people.add(new ExamplePerson("Smith", 35, "America"));

        // Creating a JSON array and adding only persons older than 25
        JSONArray jsonArray = new JSONArray();
        for (ExamplePerson person : people) {
            if (person.age > 25) {
                jsonArray.put(person.toJSON());
            }
        }

        // Printing the filtered JSON array in a formatted manner
        System.out.println(jsonArray.toString(4));
    }
}

