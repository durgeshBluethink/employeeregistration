package org.employeeregistration.employee.service;

import org.employeeregistration.employee.entity.Employee;
import org.employeeregistration.employee.exception.ResourceNotFoundException;
import org.employeeregistration.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

//    @Override
//    public Employee getEmployeeById(Long employeesId) {
//        Optional<Employee> employee = employeeRepository.findById(employeesId);
//        if (employee.isPresent()) {
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee not found with ID: " + employeesId);
//        }
//    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

//    @Override
//    public void deleteEmployee(Long employeesId) {
//        if (employeeRepository.existsById(employeesId)) {
//            employeeRepository.deleteById(employeesId);
//        } else {
//            throw new ResourceNotFoundException("Employee not found with ID: " + employeesId);
//        }
//    }
}
