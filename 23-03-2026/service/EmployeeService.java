package com.cg.assgn.service;

import com.cg.assgn.dto.EmployeeDTO;
import com.cg.assgn.dto.EmployeeDepartmentDTO;
import com.cg.assgn.entity.Department;
import com.cg.assgn.entity.Employee;
import com.cg.assgn.exception.DepartmentNameNotFoundException;
import com.cg.assgn.exception.MobileNumberDoesNotExistsForEmployeeException;
import com.cg.assgn.repository.DepartmentRepository;
import com.cg.assgn.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee saveEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getName() != null) {
            String deptName = employee.getDepartment().getName();
            Department dept = departmentRepository.findByName(deptName)
                    .orElseGet(() -> departmentRepository.save(employee.getDepartment()));
            employee.setDepartment(dept);
        } else if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
             Department dept = departmentRepository.findById(employee.getDepartment().getId())
                     .orElseThrow(() -> new DepartmentNameNotFoundException("Department ID not found"));
             employee.setDepartment(dept);
        }
        return employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getAllEmployeesWithDetails() {
        return employeeRepository.findAllWithDepartmentAndMobileNumbers().stream()
                .map(e -> new EmployeeDTO(
                        e.getId(),
                        e.getName(),
                        e.getSalary(),
                        e.getMobileNumbers(),
                        e.getDepartment() != null ? e.getDepartment().getName() : null,
                        e.getDepartment() != null ? e.getDepartment().getManagerName() : null
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        Department dept = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new DepartmentNameNotFoundException("Department with name " + departmentName + " not found"));
        
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public EmployeeDepartmentDTO getDepartmentDetailsByMobileNumber(String mobileNumber) {
        List<EmployeeDepartmentDTO> result = employeeRepository.findDepartmentDetailsByMobileNumber(mobileNumber);
        if (result.isEmpty()) {
            throw new MobileNumberDoesNotExistsForEmployeeException("Mobile number " + mobileNumber + " does not belong to any employee");
        }
        return result.get(0);
    }
}
