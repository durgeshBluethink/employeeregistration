package org.employeeregistration.employee.service;

import org.employeeregistration.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long employeeId);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long employeeId, Employee employeeDetails);
    void deleteEmployee(Long employeeId);
}
