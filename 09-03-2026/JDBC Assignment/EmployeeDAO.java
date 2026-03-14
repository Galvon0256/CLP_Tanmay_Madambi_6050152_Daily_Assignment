package com.demo.jdbc;

import java.sql.*;
import java.util.*;

public class EmployeeDAO {

    private Connection con;

    public EmployeeDAO() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employee",
                "postgres",
                "shubham"
        );
    }

    public boolean createTableIfNotExists() throws SQLException {
        Statement stmt = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS Employee (" +
                "ID INT PRIMARY KEY, " +
                "ENAME VARCHAR(50), " +
                "SALARY DOUBLE PRECISION, " +
                "DEPT VARCHAR(50), " +
                "PHONENO VARCHAR(15))";

        stmt.executeUpdate(sql);
        stmt.close();

        return true;
    }

    public boolean dropTable() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS Employee");
        stmt.close();
        return true;
    }

    public List<Employee> fetchAll() throws SQLException {

        List<Employee> list = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Employee emp = new Employee();

            emp.setId(rs.getInt("ID"));
            emp.setName(rs.getString("ENAME"));
            emp.setSalary(rs.getDouble("SALARY"));
            emp.setDepartment(rs.getString("DEPT"));
            emp.setPhoneno(rs.getString("PHONENO"));

            list.add(emp);
        }

        rs.close();
        ps.close();

        return list;
    }

    public Employee insertEmployee(Employee emp) throws SQLException {

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Employee (ID,ENAME,SALARY,DEPT,PHONENO) VALUES (?,?,?,?,?)"
        );

        ps.setInt(1, emp.getId());
        ps.setString(2, emp.getName());
        ps.setDouble(3, emp.getSalary());
        ps.setString(4, emp.getDepartment());
        ps.setString(5, emp.getPhoneno());

        int rows = ps.executeUpdate();

        ps.close();

        if (rows > 0)
            return emp;

        return null;
    }

    public Employee updateSalary(int id, double newSalary) throws SQLException {

        PreparedStatement ps = con.prepareStatement(
                "UPDATE Employee SET SALARY=? WHERE ID=?"
        );

        ps.setDouble(1, newSalary);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        ps.close();

        if (rows > 0)
            return fetchById(id);

        return null;
    }

    public Employee fetchById(int id) throws SQLException {

        Employee emp = null;

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Employee WHERE ID=?"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            emp = new Employee();

            emp.setId(rs.getInt("ID"));
            emp.setName(rs.getString("ENAME"));
            emp.setSalary(rs.getDouble("SALARY"));
            emp.setDepartment(rs.getString("DEPT"));
            emp.setPhoneno(rs.getString("PHONENO"));
        }

        rs.close();
        ps.close();

        return emp;
    }

    public Employee deleteById(int id) throws SQLException {

        Employee emp = fetchById(id);

        if (emp != null) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Employee WHERE ID=?"
            );

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
        }

        return emp;
    }

    public void close() throws SQLException {

        if (con != null)
            con.close();
    }
}