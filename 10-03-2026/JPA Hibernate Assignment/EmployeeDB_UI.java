package cg.demo.jpahibernate.activity;

import java.util.*;

@SuppressWarnings("unused")
public class EmployeeDB_UI {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EmployeesDAO dao = new EmployeesDAO();

		int choice = 0;

		while (choice != 9) {

			System.out.println("\n1 Insert Employee");
			System.out.println("2 View All Employees");
			System.out.println("3 Fetch Employee By ID");
			System.out.println("4 Update Salary");
			System.out.println("5 Delete Employee");
			System.out.println("6 Count Employees By Dept");
			System.out.println("7 Fetch Employee By Mobile");
			System.out.println("8 Fetch Employee By Salary");
			System.out.println("9 Exit");

			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			switch (choice) {

			// INSERT
			case 1:

				System.out.print("Name: ");
				String name = sc.next();

				System.out.print("Salary: ");
				double sal = sc.nextDouble();

				System.out.print("Dept: ");
				String dept = sc.next();

				System.out.print("How many mobile numbers: ");
				int n = sc.nextInt();

				List<Long> mobiles = new ArrayList<>();

				for (int i = 0; i < n; i++) {
					System.out.print("Enter Mobile " + (i + 1) + ": ");
					mobiles.add(sc.nextLong());
				}

				Employees emp = new Employees(name, sal, dept, mobiles);

				dao.insertEmployee(emp);

				System.out.println("Employee Inserted");

				break;

			// VIEW ALL
			case 2:

				dao.fetchAll().forEach(System.out::println);

				break;

			// FETCH BY ID
			case 3:

				System.out.print("Enter Employee ID: ");
				int fid = sc.nextInt();

				Employees fetched = dao.fetchById(fid);

				if (fetched != null)
					System.out.println(fetched);
				else
					System.out.println("Employee Not Found");

				break;

			// UPDATE SALARY
			case 4:

				System.out.print("Enter Employee ID: ");
				int uid = sc.nextInt();

				System.out.print("New Salary: ");
				double ns = sc.nextDouble();

				dao.updateSalary(uid, ns);

				System.out.println("Salary Updated");

				break;

			// DELETE
			case 5:

				System.out.print("Enter Employee ID: ");
				int did = sc.nextInt();

				dao.deleteEmployee(did);

				System.out.println("Employee Deleted");

				break;

			// COUNT EMPLOYEES BY DEPT
			case 6:

				List<Object[]> list = dao.countEmployeesByDept();

				for (Object[] row : list) {
					System.out.println("Dept: " + row[0] + " Count: " + row[1]);
				}

				break;

			// FETCH BY MOBILE
			case 7:

				System.out.print("Enter Mobile Number: ");
				long mob = sc.nextLong();

				Employees e = dao.fetchByMobile(mob);

				if (e != null)
					System.out.println(e);
				else
					System.out.println("Employee Not Found");

				break;

			case 8:

				System.out.print("Enter Salary: ");
				double s = sc.nextDouble();

				List<Employees> list1 = dao.fetchBySalary(s);

				if (list1.isEmpty())
					System.out.println("No Employees Found");
				else
					list1.forEach(System.out::println);

				break;

			case 9:

				dao.close();
				System.out.println("Exited");

				break;

			default:
				System.out.println("Invalid Choice");

			}
		}

		sc.close();
	}
}