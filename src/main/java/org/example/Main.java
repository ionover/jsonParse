package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = readString("data.json");

        List<Employee> list = jsonToList(json);

        for (Employee employee: list) {
            System.out.println(employee);
        }
    }

    public static String readString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            System.out.println("Ну смогли считать строку: " + e);
        }
        return stringBuilder.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            Gson gson = new GsonBuilder().create();

            for (Object element: jsonArray) {
                JSONObject jsonObject = (JSONObject) element;
                Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);
                employees.add(employee);
            }
        } catch (ParseException e) {
            System.out.println("Не смогли привести json: " + e);
        }

        return employees;
    }
}
