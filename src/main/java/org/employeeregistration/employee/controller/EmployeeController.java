package org.employeeregistration.employee.controller;

import jakarta.validation.Valid;
import org.employeeregistration.employee.entity.Employee;
import org.employeeregistration.employee.exception.ResourceNotFoundException;
import org.employeeregistration.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("v1")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

//    private final EmployeeService employeeService;
//
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }


//    @GetMapping("/{employeesId}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeesId) {
//        Employee employee = employeeService.getEmployeeById(employeesId);
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }

    @GetMapping("v1")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

//    @DeleteMapping("/{employeesId}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeesId) {
//        employeeService.deleteEmployee(employeesId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
//                .stream()
//                .collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
