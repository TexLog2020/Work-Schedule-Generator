package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String	RED= "\u001B[31m";
    public Display() {
    }

    public void menu() {
        System.out.println("\n-Work Schedule Generator-\n\n"
                + "1) Show all employees\n"
                + "2) Show a specific employ\n"
                + "3) Add employee\n"
                + "4) Delete employee\n"
                + "5) Generate weekly schedule.\n"
                + "6) Generate monthly schedule.\n"
                + "7) Generate semester schedule.\n"
                + "8) Exit");
    }

    public void showAll(ArrayList<Employee> employees) {
        employees = new EmployeeParser().sortById(employees);
            System.out.println("ID"+"\t"+"First Name"+"\t\t"+"Last Name"+"\t\t"+"Profession"+"\t\t"+"Weekly Hours\n"
                              +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Employee employee : employees) {
            System.out.println(employee.getId()
                    +"\t"+ employee.getFname()
                    +"\t\t\t"+ employee.getLname()
                    +"\t\t\t"+ employee.getProfession()
                    +"\t\t\t"+ employee.getWeeklyHours());
        }
    }

    public void showEmployee(int id, ArrayList<Employee> employees) {
        boolean error = true;
        System.out.println("ID"+"\t"+"First Name"+"\t\t"+"Last Name"+"\t\t"+"Profession"+"\t\t"+"Weekly Hours\n"
                +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Employee employee : employees) {
            if (id == employee.getId()) {
                System.out.println(employee.getId()
                        +"\t"+ employee.getFname()
                        +"\t\t\t"+ employee.getLname()
                        +"\t\t\t"+ employee.getProfession()
                        +"\t\t\t"+ employee.getWeeklyHours());
                error = false;
            }
        }
        if (error) {
            System.out.println("Employee not found!");
        }
    }

    public void addEmployee(ArrayList<Employee> employees) {
        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);

        System.out.print("Enter ID of the new employee: ");
        int id = scanInt.nextInt();
        System.out.println();

        for (Employee employee : employees) {
            if (id == employee.getId()) {
                System.out.println("This id already exists!");
                return;
            }
        }

        System.out.println("Enter First Name:");
        String fname = scanString.nextLine();
        System.out.println("Enter Last Name:");
        String lname = scanString.nextLine();
        System.out.println("Enter Weekly Working Hours:");
        int weeklyHours = scanInt.nextInt();
        System.out.println("Enter Profession:");
        String profession = scanString.nextLine();
        System.out.println(ANSI_CYAN+"New employee added successfully!"+ANSI_RESET);

        Employee newEmployee = new Employee(fname, lname, profession, id, weeklyHours);
        new EmployeeParser().saveData(newEmployee, employees);
    }

    public void deleteEmployee(ArrayList<Employee> employees) {
        Scanner scanInt = new Scanner(System.in);

        System.out.print("Enter ID of the employee: ");
        int id = scanInt.nextInt();
        System.out.println();

        ArrayList<Employee> finalEmployees = employees;
        for (Employee employee : employees) {
            if (id == employee.getId()) {
                System.out.println(ANSI_CYAN+"Employee   "+ANSI_RESET+employee.getFname()+"  "+employee.getLname()
                        +"\tWith ID:"+employee.getId()+ANSI_CYAN+"   has been successfully removed."+ANSI_RESET);
                finalEmployees.remove(employee);
                break;
            }
        }

        new EmployeeParser().saveData(finalEmployees);
    }

    public void displaySchedule(ArrayList<Shift> schedule) {

        int weekNum = 1, month = 1;
        int shiftCounter = 0;
        try {
            File fout = new File("Work Schedule.txt");
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (Shift shift : schedule) {


                    //Show dayname once
                    if ((shiftCounter % 3) == 0) {
                        //Show current Month
                        if((shiftCounter % 84)==0) {
                            System.out.println(RED + "\n\t\t\t MONTH: " + month + ANSI_RESET);
                            bw.write("\nMONTH :"+month+"\n");
                            month++;
                        }
                        //Show Current Week
                        if ((shiftCounter % 21) == 0) {
                            System.out.println(RED + "\t\t\t WEEK: " + weekNum + ANSI_RESET);
                            bw.write("\nWEEK : "+weekNum);
                            weekNum++;
                        }

                        System.out.println(MAGENTA + "\n\t\t\t~" + shift.getDayName() + "~\n" + ANSI_RESET +
                                            ANSI_CYAN + "\t\t-------------------------" + ANSI_RESET);
                        bw.write("\n\t\t\t~" + shift.getDayName() + "~\n" + "\t\t-------------------------" + "\n");


                        System.out.println(ANSI_CYAN + shift.getShiftName() + ANSI_RESET);
                        bw.write(shift.getShiftName() + "\n");


                        for (Employee employee : shift.getEmployees()) {
                            System.out.print(employee.getFname() + " " + employee.getLname()  + ANSI_CYAN + " | " + ANSI_RESET);
                            bw.write(employee.getFname() + " " + employee.getLname() + " | ");

                        }

                    } else {
                        System.out.println("\n" + ANSI_CYAN + shift.getShiftName() + ANSI_RESET);
                        bw.write("\n" + shift.getShiftName() + "\n");
                        for (Employee employee : shift.getEmployees()) {
                            System.out.print(employee.getFname() + " " + employee.getLname()  + ANSI_CYAN + " | " + ANSI_RESET);
                            bw.write(employee.getFname() + " " + employee.getLname() + " | ");

                        }
                    }
                    shiftCounter++;

                    System.out.println();
                    bw.write("\n");
            }

            bw.close();
        }
            catch (FileNotFoundException e) {
                // File was not found
                e.printStackTrace();
            } catch (IOException e) {
                // Problem when writing to the file
                e.printStackTrace();
            }


    }
}





