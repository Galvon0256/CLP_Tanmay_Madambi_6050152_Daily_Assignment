package com.cg.assgn.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
    private Set<String> mobileNumbers;
    private String departmentName;
    private String managerName;
}

