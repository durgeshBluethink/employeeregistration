package org.employeeregistration.service;

import org.employeeregistration.entity.Employee;
import org.employeeregistration.entity.DTOEmployee; // Update import
import org.employeeregistration.exception.ResourceNotFoundException;
import org.employeeregistration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee createEmployee(DTOEmployee dtoEmployee) {
        Employee employee = Employee.builder()
                .firstName(dtoEmployee.getFirstName())
                .lastName(dtoEmployee.getLastName())
                .email(dtoEmployee.getEmail())
                .contactNumber(dtoEmployee.getContactNumber())
                .address(dtoEmployee.getAddress())
                .dateOfBirth(dtoEmployee.getDateOfBirth())
                .department(dtoEmployee.getDepartment())
                .position(dtoEmployee.getPosition())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
    }

    @Override
    public Employee updateEmployee(Integer employeeId, DTOEmployee dtoEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
        existingEmployee.setFirstName(dtoEmployee.getFirstName());
        existingEmployee.setLastName(dtoEmployee.getLastName());
        existingEmployee.setEmail(dtoEmployee.getEmail());
        existingEmployee.setContactNumber(dtoEmployee.getContactNumber());
        existingEmployee.setAddress(dtoEmployee.getAddress());
        existingEmployee.setDateOfBirth(dtoEmployee.getDateOfBirth());
        existingEmployee.setDepartment(dtoEmployee.getDepartment());
        existingEmployee.setPosition(dtoEmployee.getPosition());
        existingEmployee.setUpdatedTime(LocalDateTime.now());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
        employeeRepository.delete(employee);
    }
}
