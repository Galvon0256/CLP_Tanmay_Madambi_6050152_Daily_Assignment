package org.example.springcore.beans.service;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmployeeService service = ac.getBean(EmployeeService.class);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee CRUD Menu ===");
            System.out.println("1. Create Employee");
            System.out.println("2. Get Employee by ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Employee newEmp = readEmployeeInput(sc);
                    Employee created = service.create(newEmp);
                    System.out.println(created != null ? "Created: " + created : "Employee ID already exists");
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    int readId = sc.nextInt();
                    Employee found = service.fetchById(readId);
                    System.out.println(found != null ? found : "Employee not found");
                    break;

                case 3:
                    List<Employee> employees = service.fetchAll();
                    if (employees.isEmpty()) {
                        System.out.println("No employees found");
                    } else {
                        employees.forEach(System.out::println);
                    }
                    break;

                case 4:
                    Employee updatedInput = readEmployeeInput(sc);
                    Employee updated = service.update(updatedInput);
                    System.out.println(updated != null ? "Updated: " + updated : "Employee not found");
                    break;

                case 5:
                    System.out.print("Enter Employee ID: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = service.deleteById(deleteId);
                    System.out.println(deleted ? "Employee deleted" : "Employee not found");
                    break;

                case 6:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Employee readEmployeeInput(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Employee Name: ");
        String name = sc.next();
        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        return new Employee(id, name, salary);
    }
}
