package org.employeeregistration.service;

import org.employeeregistration.entity.DTOEmployee;
import org.employeeregistration.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(DTOEmployee dtoemployee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer employeeId);
    Employee updateEmployee(Integer employeeId, DTOEmployee dtoemployee);
    void deleteEmployee(Integer employeeId);
}
