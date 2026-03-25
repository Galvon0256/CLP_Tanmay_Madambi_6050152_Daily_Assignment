package com.cg.assgn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String managerName;
    private Long employeeId;
    private String employeeName;
}

