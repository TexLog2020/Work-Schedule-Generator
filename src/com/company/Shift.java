package com.company;
import java.util.ArrayList;
import java.util.HashMap;

public class Shift {

    public static final int MORNING = 0;
    public static final int EVENING = 1;
    public static final int NIGHT = 2;

    public static final String shiftNames[] = {
        "MORNING",
        "EVENING",
        "NIGHT"
    };

    public static final String dayNames[] = {
        "MONDAY",
        "TUESDAY",
        "WEDNESDAY",
        "THURSDAY",
        "FRIDAY",
        "SATURDAY",
        "SUNDAY"
    };

    public static final HashMap<Integer , Integer> shiftEmployees = new HashMap<Integer, Integer>();

    int month, week, day, shift;
    ArrayList<Employee> employees;

    public Shift(int month, int week, int day, int shift) {
        this.month = month;
        this.week = week;
        this.day = day;
        this.shift = shift;
        this.employees = new ArrayList<>();

        shiftEmployees.put(0, 5);
        shiftEmployees.put(1, 2);
        shiftEmployees.put(2, 1);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int getMonth() {
        return month;
    }

    public String getDayName() {
        return dayNames[day - (week * 7)];
    }

    public String getShiftName() {
        return shiftNames[shift];
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
