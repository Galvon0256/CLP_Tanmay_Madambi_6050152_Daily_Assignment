package com.cg.assgn.repository;

import com.cg.assgn.dto.DepartmentEmployeeCountDTO;
import com.cg.assgn.entity.Department;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    Optional<Department> findByName(String name);
    
    @Query("SELECT new com.cg.assgn.dto.DepartmentEmployeeCountDTO(d.name, COUNT(e)) " +
           "FROM Department d LEFT JOIN d.employees e GROUP BY d.id, d.name")
    List<DepartmentEmployeeCountDTO> countEmployeesByDepartment();
}

