package com.day2.jsonhandling;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonForStudent {
    public static void main(String[] args) {

        //create JSONObject instance
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Alice");
        jsonObject.put("age", 25);

        //Create JSONArray instance for subjects
        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Chemistry");
        subjects.put("Physics");

        //put subjects into jsonObject
        jsonObject.put("subjects",subjects);

        //Convert to JSON String
        System.out.println(jsonObject.toString());

    }
}

