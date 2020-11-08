package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeParser {

    private String fname, lname, profession;
    private int id, weeklyHours;
    private Random shuffler;

    public EmployeeParser() {
    }

    public ArrayList<Employee> parse() {
        ArrayList<Employee> temp = new ArrayList<>();;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Employees.json")) {

            JSONObject jsonObj = (JSONObject) jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) jsonObj.get("employees");

            for (int i = 0; i <= employeeList.size() - 1; i++) {
                JSONObject jsonObj2 = (JSONObject) employeeList.get(i);

                id = (int) (long) jsonObj2.get("id");
                fname = (String) jsonObj2.get("fname");
                lname = (String) jsonObj2.get("lname");
                weeklyHours = (int) (long) jsonObj2.get("weeklyHours");
                profession = (String) jsonObj2.get("profession");

                temp.add(new Employee(fname, lname, profession, id, weeklyHours));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *xrisimopoieitai stin delete
     * @param employees
     */
    public void saveData(ArrayList<Employee> employees) {
        JSONArray jsonArray = new JSONArray();

        for (Employee temp : employees) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", temp.getId());
            jsonObj.put("fname", temp.getFname());
            jsonObj.put("lname", temp.getLname());
            jsonObj.put("weeklyHours", temp.getWeeklyHours());
            jsonObj.put("profession", temp.getProfession());
            jsonArray.add(jsonObj);
        }
        JSONObject finalJsonObj = new JSONObject();
        finalJsonObj.put("employees", jsonArray);
        FileWriter file;
        try {
            file = new FileWriter("Employees.json");
            file.write(finalJsonObj.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param employee
     * @param employees
     */
    public void saveData(Employee employee, ArrayList<Employee> employees) {
        employees.add(employee);

        JSONArray jsonArray = new JSONArray();

        for (Employee temp : employees) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", temp.getId());
            jsonObj.put("fname", temp.getFname());
            jsonObj.put("lname", temp.getLname());
            jsonObj.put("weeklyHours", temp.getWeeklyHours());
            jsonObj.put("profession", temp.getProfession());
            jsonArray.add(jsonObj);
        }
        JSONObject finalJsonObj = new JSONObject();
        finalJsonObj.put("employees", jsonArray);
        FileWriter file;
        try {
            file = new FileWriter("Employees.json");
            file.write(finalJsonObj.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> sortById(ArrayList<Employee> employees) {
        Employee temp;
        for (int i = 0; i < employees.size() - 1; i++) {
            if (employees.get(i).getId() > employees.get(i+1).getId()) {
                temp = employees.get(i+1);
                employees.set(i + 1, employees.get(i));
                employees.set(i, temp);
            }
        }
        return employees;
    }

    public ArrayList<Employee> shuffleEmployees(ArrayList<Employee> employees){
        int newIndex;
        ArrayList<Employee> randEmployees = new ArrayList<>();
        List<Integer> indexArray = new ArrayList<>();

        shuffler = new Random(System.currentTimeMillis());

        while (indexArray.size() < employees.size()) {
            newIndex = shuffler.nextInt(employees.size());
            if (!indexArray.contains(newIndex)){
                indexArray.add(newIndex);
            }
        }

        for (int i=0; i <= employees.size() - 1; i++){
            randEmployees.add(employees.get(indexArray.get(i)));
        }
        return randEmployees;
    }
}
