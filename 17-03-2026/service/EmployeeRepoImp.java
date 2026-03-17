package org.example.springcore.beans.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepoImp implements EmployeeRepo {

    private final Map<Integer, Employee> mp = new HashMap<>();

    public EmployeeRepoImp() {
        mp.put(1, new Employee(1, "Shubham", 100000));
        mp.put(2, new Employee(2, "Raj", 50000));
        mp.put(3, new Employee(3, "Ganja", 65000));
    }

    @Override
    public Employee create(Employee employee) {
        if (employee == null || mp.containsKey(employee.getId())) {
            return null;
        }
        mp.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee fetchById(int id) {
        return mp.get(id);
    }

    @Override
    public List<Employee> fetchAll() {
        return new ArrayList<>(mp.values());
    }

    @Override
    public Employee update(Employee employee) {
        if (employee == null || !mp.containsKey(employee.getId())) {
            return null;
        }
        mp.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public boolean deleteById(int id) {
        return mp.remove(id) != null;
    }
}
