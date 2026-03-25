package com.cg.assgn.controller;

import com.cg.assgn.dto.EmployeeDTO;
import com.cg.assgn.dto.EmployeeDepartmentDTO;
import com.cg.assgn.entity.Employee;
import com.cg.assgn.service.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployeesWithDetails(), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable String departmentName) {
        return new ResponseEntity<>(employeeService.getEmployeesByDepartmentName(departmentName), HttpStatus.OK);
    }
    
    @GetMapping("/department-details/mobile/{mobileNumber}")
    public ResponseEntity<EmployeeDepartmentDTO> getDepartmentDetailsByMobileNumber(@PathVariable String mobileNumber) {
        return new ResponseEntity<>(employeeService.getDepartmentDetailsByMobileNumber(mobileNumber), HttpStatus.OK);
    }
}

