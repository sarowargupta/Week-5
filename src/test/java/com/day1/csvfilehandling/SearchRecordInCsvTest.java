package com.day1.csvfilehandling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchRecordInCsvTest {
    String filepath = "src/main/resources/employees.csv";

    @Test
    public void testSearchEmployeeExists() {
        String output = SearchRecordInCsv.searchEmployeeByName(filepath, "Alice");
        assertEquals("Employee Found:\nDepartment:Finance\nSalary:$62000", output.trim());
    }

    @Test
    public void testSearchEmployeeNotFound() {
        String output = SearchRecordInCsv.searchEmployeeByName(filepath, "Michael Brown");
        assertEquals("Employee Michael Brown not found.", output.trim());
    }

}
