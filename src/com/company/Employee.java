package com.company;
public class Employee {

    String fname, lname, profession;
    int id, weeklyHours,workedHours;

    public Employee(String fname, String lname, String profession, int ID, int weeklyHours) {
        this.fname = fname;
        this.lname = lname;
        this.profession = profession;
        this.id = ID;
        this.weeklyHours = weeklyHours;
        this.workedHours= workedHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getProfession() {
        return profession;
    }

    public int getId() {
        return id;
    }
}
