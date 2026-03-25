package com.cg.assgn.repository;

import com.cg.assgn.dto.EmployeeDTO;
import com.cg.assgn.dto.EmployeeDepartmentDTO;
import com.cg.assgn.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    

    
    @Query("SELECT e FROM Employee e JOIN FETCH e.department d WHERE d.name = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // Using JOIN FETCH as per requirements "Use Join fetch for performance Optimization"
    // Also "Use DTO classes for Mapping result of complex query wherever required"
    // For requirement 5: Fetch complete department details of an employee along with employee Id and name based on mobile number
    @Query("SELECT new com.cg.assgn.dto.EmployeeDepartmentDTO(d.id, d.name, d.managerName, e.id, e.name) " +
           "FROM Employee e JOIN e.department d JOIN e.mobileNumbers m WHERE m = :mobileNumber")
    List<EmployeeDepartmentDTO> findDepartmentDetailsByMobileNumber(@Param("mobileNumber") String mobileNumber);

    boolean existsByMobileNumbersContains(String mobileNumber);

    @Query("SELECT DISTINCT e FROM Employee e JOIN FETCH e.department d LEFT JOIN FETCH e.mobileNumbers")
    List<Employee> findAllWithDepartmentAndMobileNumbers();
}

