package org.employeeregistration.employee.service;

import org.employeeregistration.employee.entity.Employee;

import java.util.List;


public interface EmployeeService {
    Employee saveEmployee(Employee employee);
//    Employee getEmployeeById(Long employeesId);
    List<Employee> getAllEmployees();
//    void deleteEmployee(Long employeesId);
}

