package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanOption = new Scanner(System.in);

        Display display = new Display();
        EmployeeParser parseEmployees = new EmployeeParser();

        while (true) {
            display.menu();

            int choice = scanOption.nextInt();

            if (choice == 1) {
                display.showAll(parseEmployees.parse());
            } else if (choice == 2) {
                System.out.println("Provide proper ID to the system:");
                int id = new Scanner(System.in).nextInt();
                display.showEmployee(id, parseEmployees.parse());
            } else if (choice == 3) {
                    display.addEmployee(parseEmployees.parse());
            } else if (choice == 4) {
                display.deleteEmployee(parseEmployees.parse());
            } else if (choice == 5) {
                display.displaySchedule(new Schedule().generate(7));
            } else if (choice == 6) {
                display.displaySchedule(new Schedule().generate(28));
            }else if (choice == 7) {
                display.displaySchedule(new Schedule().generate(168));
            }
            else if (choice == 8) {
                System.exit(0);
            }
        }
    }
}
