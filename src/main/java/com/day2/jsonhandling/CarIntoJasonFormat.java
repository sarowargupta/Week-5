package com.day2.jsonhandling;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Car class
class Car {
    private String name;
    private String color;
    private int year;

    // Constructor
    public Car(String name, String color, int year) {
        this.name = name;
        this.color = color;
        this.year = year;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setMake(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setModel(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

public class CarIntoJasonFormat {
    public static void main(String[] args) {
        // Create a Car object
        Car car = new Car("Thar", "Black", 2022);

        // Convert Car object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(car);
            System.out.println("Car in JSON format: " + jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

