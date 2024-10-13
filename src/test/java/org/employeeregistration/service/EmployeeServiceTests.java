package org.employeeregistration.service;

import org.employeeregistration.entity.Employee;
import org.employeeregistration.entity.DTOEmployee;
import org.employeeregistration.exception.ResourceNotFoundException;
import org.employeeregistration.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnEmployee() {
        DTOEmployee dtoEmployee = DTOEmployee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .build();

        Employee savedEmployee = Employee.builder()
                .employeeId(1)
                .firstName(dtoEmployee.getFirstName())
                .lastName(dtoEmployee.getLastName())
                .email(dtoEmployee.getEmail())
                .contactNumber(dtoEmployee.getContactNumber())
                .address(dtoEmployee.getAddress())
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        Employee createdEmployee = employeeService.createEmployee(dtoEmployee);

        assertThat(createdEmployee).isNotNull();
        assertThat(createdEmployee.getEmployeeId()).isEqualTo(1);
        assertThat(createdEmployee.getFirstName()).isEqualTo(dtoEmployee.getFirstName());
    }

    @Test
    public void whenGetAllEmployees_thenReturnEmployeeList() {
        Employee employee1 = Employee.builder().employeeId(1).firstName("Amit").lastName("Sharma").build();
        Employee employee2 = Employee.builder().employeeId(2).firstName("Raj").lastName("Kumar").build();
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2)); // Use Arrays.asList

        List<Employee> employees = employeeService.getAllEmployees();

        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    public void whenGetEmployeeById_thenReturnEmployee() {
        Integer employeeId = 1;
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .firstName("Amit")
                .lastName("Sharma")
                .build();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(employeeId);

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getFirstName()).isEqualTo("Amit");
    }

    @Test
    public void whenGetEmployeeById_thenThrowResourceNotFoundException() {
        Integer employeeId = 1;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(employeeId));

        assertThat(exception.getMessage()).isEqualTo("Employee not found with id " + employeeId);
    }

    @Test
    public void givenUpdatedEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        Integer employeeId = 1;
        DTOEmployee dtoEmployee = DTOEmployee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .build();

        Employee existingEmployee = Employee.builder()
                .employeeId(employeeId)
                .firstName("Amit")
                .lastName("Sharma")
                .build();

        Employee updatedEmployee = Employee.builder()
                .employeeId(employeeId)
                .firstName(dtoEmployee.getFirstName())
                .lastName(dtoEmployee.getLastName())
                .build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(employeeId, dtoEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo(dtoEmployee.getFirstName());
    }

    @Test
    public void whenUpdateEmployee_thenThrowResourceNotFoundException() {
        Integer employeeId = 1;
        DTOEmployee dtoEmployee = DTOEmployee.builder().firstName("Amit").lastName("Sharma").build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(employeeId, dtoEmployee));

        assertThat(exception.getMessage()).isEqualTo("Employee not found with id " + employeeId);
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnVoid() {
        Integer employeeId = 1;
        Employee employee = Employee.builder().employeeId(employeeId).build();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    public void whenDeleteEmployee_thenThrowResourceNotFoundException() {
        Integer employeeId = 1;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));

        assertThat(exception.getMessage()).isEqualTo("Employee not found with id " + employeeId);
    }
}
