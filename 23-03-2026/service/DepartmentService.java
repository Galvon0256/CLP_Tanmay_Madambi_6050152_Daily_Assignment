package com.cg.assgn.service;

import com.cg.assgn.dto.DepartmentEmployeeCountDTO;
import com.cg.assgn.entity.Department;
import com.cg.assgn.exception.DepartmentNameNotFoundException;
import com.cg.assgn.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentEmployeeCountDTO> getEmployeeCountByDepartment() {
        return departmentRepository.countEmployeesByDepartment();
    }
    
    public Department findByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new DepartmentNameNotFoundException("Department with name " + name + " not found"));
    }
    
    public Department save(Department department) {
        return departmentRepository.save(department);   
    }
}

