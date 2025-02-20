package com.day2.jsonhandling;
import org.json.JSONObject;
import org.json.XML;

public class ConvertJsonToXml {
    public static void main(String[] args) {
        // Sample JSON data
        String jsonString = """
        {
            "name": "John",
            "age": 30,
            "city": "New York",
            "email": "john.doe@example.com"
        }
        """;

        // Convert JSON string to JSONObject
        JSONObject json = new JSONObject(jsonString);

        // Convert JSONObject to XML format
        String xml = XML.toString(json);

        // Print XML output
        System.out.println(xml);
    }
}

