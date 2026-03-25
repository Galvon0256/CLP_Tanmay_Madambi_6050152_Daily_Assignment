package com.cg.assgn.controller;

import com.cg.assgn.dto.DepartmentEmployeeCountDTO;
import com.cg.assgn.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees-count")
    public List<DepartmentEmployeeCountDTO> getEmployeeCountPerDepartment() {
        return departmentService.getEmployeeCountByDepartment();
    }
}

