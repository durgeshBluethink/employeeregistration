package org.employeeregistration.repository;

import org.employeeregistration.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmailOrContactNumber(String email, String contactNumber);
}
