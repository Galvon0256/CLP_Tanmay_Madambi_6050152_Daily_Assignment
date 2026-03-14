package com.demo.jdbc;

import java.util.*;

public class EmployeeDB_UI {

    public static void main(String[] args) {
    	try {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO db = new EmployeeDAO();
        int choice = 0;

        while (choice != 8) {
            System.out.println("\n1. View All Employees");
            System.out.println("2. Insert Employee");
            System.out.println("3. Update Salary");
            System.out.println("4. Fetch Employee by ID");
            System.out.println("5. Delete Employee");
            System.out.println("6. Create Table if Not Exists");
            System.out.println("7. Drop Table");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    List<Employee> all = db.fetchAll();
                    for (Employee e : all) {
                        System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary() + " " + e.getDepartment() + " " + e.getPhoneno());
                    }
                    break;
                case 2:
                    Employee emp = new Employee();
                    System.out.println("ID: "); emp.setId(sc.nextInt());
                    System.out.println("Name: "); emp.setName(sc.next());
                    System.out.println("Salary: "); emp.setSalary(sc.nextDouble());
                    System.out.println("Dept: "); emp.setDepartment(sc.next());
                    System.out.println("Phone: "); emp.setPhoneno(sc.next());
                    Employee inserted = db.insertEmployee(emp);
                    if (inserted != null) System.out.println("Inserted: " + inserted.getId());
                    break;
                case 3:
                    try {
                        System.out.print("ID: "); int uid = sc.nextInt();
                        Employee empToUpdate = db.fetchById(uid);
                        if (empToUpdate != null) {
                            System.out.print("New Salary: "); double newSal = sc.nextDouble();
                            Employee updated = db.updateSalary(uid, newSal);
                            if (updated != null) {
                                Employee updatedEmp = db.fetchById(uid);
                                System.out.println(updatedEmp.getId() + " " + updatedEmp.getName() + " " + updatedEmp.getSalary() + " " + updatedEmp.getDepartment() + " " + updatedEmp.getPhoneno());
                            } else System.out.println("Salary update failed");
                        } else System.out.println("Employee Not Found");
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 4:
                    System.out.print("ID: "); int fid = sc.nextInt();
                    Employee fetched = db.fetchById(fid);
                    if (fetched != null)
                        System.out.println(fetched.getId() + " " + fetched.getName() + " " + fetched.getSalary() + " " + fetched.getDepartment() + " " + fetched.getPhoneno());
                    else
                        System.out.println("Employee Not Found");
                    break;
                case 5:
                    System.out.print("ID: "); int did = sc.nextInt();
                    Employee deleted = db.deleteById(did);
                    if (deleted != null) System.out.println("Deleted: " + deleted.getId() + " " + deleted.getName());
                    else System.out.println("Employee Not Found");
                    break;
                case 6:
                    if (db.createTableIfNotExists()) System.out.println("Table created or already exists");
                    break;
                case 7:
                    if (db.dropTable()) System.out.println("Table dropped successfully");
                    break;
                case 8:
                    db.close();
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }

        sc.close();
    	} catch(Exception e) {

    	    System.out.println("Error: " + e.getMessage());

    	}
    }
}