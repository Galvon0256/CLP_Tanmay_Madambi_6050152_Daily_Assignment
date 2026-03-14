package cg.demo.associationcriteriamapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cg.demo.associationcriteriamapping.DAOLayer;
import cg.demo.associationcriteriamapping.Department;
import cg.demo.associationcriteriamapping.Employee;

public class App {
	public static void main(String[] args) {

		System.out.println("Starting App!!!");

		Scanner sc = new Scanner(System.in);
		DAOLayer dao = new DAOLayer();

		int choice = 0;

		while (choice != 8) {

			System.out.println("\n===== MENU =====");
			System.out.println("1. Insert Employee");
			System.out.println("2. Fetch All Employees with Department");
			System.out.println("3. Fetch Employee Count By Department");
			System.out.println("4. Fetch Employees By Department Name");
			System.out.println("5. Fetch Employee and Department By Mobile");
			System.out.println("6. Fetch All Employee");
			System.out.println("7. fetch all employee whose salary greater than 50000");
			System.out.println("8. Exit");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:

				Department dept = new Department();
				System.out.print("Enter Department Name: ");
				dept.setDeptName(sc.next());

				System.out.print("Enter Manager Name: ");
				dept.setManagerName(sc.next());

				Employee emp = new Employee();
				System.out.print("Enter Employee Name: ");
				emp.setName(sc.next());

				System.out.print("Enter Salary: ");
				emp.setSalary(sc.nextDouble());

				System.out.print("How many mobile numbers: ");
				int n = sc.nextInt();

				List<Long> mobiles = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					System.out.print("Enter Mobile " + (i + 1) + ": ");
					mobiles.add(sc.nextLong());
				}
				emp.setMobileNumbers(mobiles);

				emp.setDepartment(dept);
				dept.getEmployees().add(emp);

				dao.insertEmployee(emp);
				System.out.println("Employee Inserted Successfully");

				break;

			case 2:

				List<Object[]> list1 = dao.fetchAllEmployeesWithDept();

				if (list1.isEmpty()) {
					System.out.println("No Records Found");
				} else {
					for (Object[] row : list1) {
						System.out.println(
								"ID: " + row[0] +
								" Name: " + row[1] +
								" Salary: " + row[2] +
								" Dept: " + row[3] +
								" Manager: " + row[4]
								);
					}
				}

				break;

			case 3:

				List<Object[]> list2 = dao.fetchEmpCountByDept();

				if (list2.isEmpty()) {
					System.out.println("No Records Found");
				} else {
					for (Object[] row : list2) {
						System.out.println(
								"Dept: " + row[0] +
								" Count: " + row[1]
								);
					}
				}

				break;

			case 4:

				System.out.print("Enter Department Name: ");
				String deptName = sc.next();

				List<Employee> list3 = dao.fetchEmployeesByDeptName(deptName);

				if (list3.isEmpty()) {
					System.out.println("No Employees Found");
				} else {
					list3.forEach(System.out::println);
				}

				break;

			case 5:

				System.out.print("Enter Mobile Number: ");
				long mob = sc.nextLong();

				Object[] result = dao.fetchEmpAndDeptByMobile(mob);

				if (result == null) {
					System.out.println("No Record Found");
				} else {
					System.out.println(
							"ID: " + result[0] +
							" Name: " + result[1] +
							" Department: " + result[2]
							);
				}

				break;

			case 6:

				List<Employee> list4 = dao.fetchAllEmployees();

				if (list4.isEmpty()) {
					System.out.println("No Records Found");
				} else {
					for (Employee e : list4) {
						System.out.println(
								"ID: " + e.getId() +
								" Name: " + e.getName() +
								" Salary: " + e.getSalary() +
								" Mobiles: " + e.getMobileNumbers() +
								" Dept: " + e.getDepartment().getDeptName() +
								" Manager: " + e.getDepartment().getManagerName()
								);
					}
				}

				break;

			case 8:
				dao.close();
				System.out.println("Exited");
				break;

			case 7:

				System.out.print("Enter Minimum Salary: ");
				double minSalary = sc.nextDouble();

				List<Employee> list5 = dao.fetchEmployeeGreaterThanSalary(minSalary);

				if (list5.isEmpty()) {
					System.out.println("No Records Found");
				} else {
					for (Employee e : list5) {
						System.out.println(
								"ID: " + e.getId() +
								" Name: " + e.getName() +
								" Salary: " + e.getSalary() +
								" Mobiles: " + e.getMobileNumbers() +
								" Dept: " + e.getDepartment().getDeptName() +
								" Manager: " + e.getDepartment().getManagerName()
								);
					}
				}

				break;

			default:
				System.out.println("Invalid Choice");
			}
		}

		sc.close();
	}
}