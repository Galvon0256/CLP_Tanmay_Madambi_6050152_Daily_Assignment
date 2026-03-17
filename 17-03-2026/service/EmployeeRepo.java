package org.example.springcore.beans.service;

import java.util.List;

public interface EmployeeRepo {
    Employee create(Employee employee);

    Employee fetchById(int id);

    List<Employee> fetchAll();

    Employee update(Employee employee);

    boolean deleteById(int id);
}
