package org.example.springcore.beans.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    @Override
    public Employee create(Employee employee) {
        return repo.create(employee);
    }

    @Override
    public Employee fetchById(int id) {
        return repo.fetchById(id);
    }

    @Override
    public List<Employee> fetchAll() {
        return repo.fetchAll();
    }

    @Override
    public Employee update(Employee employee) {
        return repo.update(employee);
    }

    @Override
    public boolean deleteById(int id) {
        return repo.deleteById(id);
    }
}
