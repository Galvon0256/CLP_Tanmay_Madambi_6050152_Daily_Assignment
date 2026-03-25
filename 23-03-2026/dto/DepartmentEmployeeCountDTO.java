package com.cg.assgn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEmployeeCountDTO {
    private String departmentName;
    private Long employeeCount;
}

