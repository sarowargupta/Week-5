package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

//person class
class Person {
    String name;
    int age;

    //constructor to initialize attribute
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        return jsonObject;
    }
}

public class ConvertListIntoJsonArray {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 22));
        people.add(new Person("Charlie", 27));


        // Convert the list to a JSON array
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }

        // Print the resulting JSON array
        System.out.println("JSON Array:");
        System.out.println(jsonArray.toString(4));
    }
}

