package com.company;
import java.util.ArrayList;
public class Schedule {


    RuleParser parser = new RuleParser();

    /**
     * Generates the schedule for given amount of days
     *
     * @param days
     * @return schedule as ArrayList<Shift>
     */
    public ArrayList<Shift> generate(int days) {

        ArrayList<Employee> employees;
        ArrayList<Shift> schedule = new ArrayList<>();
        ArrayList<Shift> thisDayShifts;
        ArrayList<Shift> thisWeekShifts = new ArrayList<>();

        schedule.add(new Shift(-1, -1, -1, -1));    //adding dummy shift

        for (int day = 0; day < days; day++) {

            employees = new EmployeeParser().shuffleEmployees(new EmployeeParser().parse());

            thisDayShifts = new ArrayList<>();
            if (day - (day / 7) * 7 == 0) {
                thisWeekShifts = new ArrayList<>();
            }

            for (int shiftType = 0; shiftType < 3; shiftType++) {

                Shift shift = new Shift(day / 28, day / 7, day, shiftType);

                for (Employee employee : employees) {
                    if (rule1(shiftType, schedule.get(schedule.size() - 1), employee)
                            && rule2(employee, shift)
                            && rule3(employee, thisDayShifts)
                            && rule4(employee, thisWeekShifts)
                            && rule5(shift)) {
                        shift.addEmployee(employee);


                    }
                }

                thisDayShifts.add(shift);
                thisWeekShifts.add(shift);
                schedule.add(shift);
            }
        }

        schedule.remove(0); //removing dummy shift
        return schedule;
    }

    private boolean rule1(int currentType, Shift prevShift, Employee employee) {
        boolean flag = false;
        for (Employee employeeTmp : prevShift.getEmployees()) {
            if (employee.getId() == employeeTmp.getId()) flag = true;
        }
        return !(parser.parseFlag(1)
                && currentType == Shift.MORNING
                && flag);
    }

    private boolean rule2(Employee employee, Shift currentShift) {
        return !(parser.parseFlag(2)
                && currentShift.getEmployees().contains(employee));
    }

    private boolean rule3(Employee employee, ArrayList<Shift> thisDayShifts) {
        if (parser.parseFlag(3)) {
            for (Shift shift : thisDayShifts) {
                if (shift.getEmployees().contains(employee)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rule4(Employee employee, ArrayList<Shift> thisWeekShifts) {

        int countShifts = 0;
        if (parser.parseFlag(4)) {
            for (Shift shift : thisWeekShifts) {
                if (shift.getEmployees().contains(employee)) {
                    countShifts++;

                }
            }
            if ((countShifts * 8) >= employee.getWeeklyHours()) {
                return false;
            }
        }
        return true;
    }

    // elegxei an i vardia exei gemisei me ipallilous
    private boolean rule5(Shift current) {
        return !(parser.parseFlag(5)
                && current.getEmployees().size() >= Shift.shiftEmployees.get(current.shift));
    }


}
