package org.employeeregistration.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.employeeregistration.employee.entity.Employee;
import org.employeeregistration.employee.exception.ResourceNotFoundException;
import org.employeeregistration.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        Employee employee = Employee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .department("Engineering")
                .position("Software Engineer")
                .build();

        given(employeeService.createEmployee(any(Employee.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/v1/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(employee.getContactNumber())))
                .andExpect(jsonPath("$.address", is(employee.getAddress())))
                .andExpect(jsonPath("$.dateOfBirth", is(employee.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.department", is(employee.getDepartment())))
                .andExpect(jsonPath("$.position", is(employee.getPosition())));
    }

    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .department("Engineering")
                .position("Software Engineer")
                .build());
        listOfEmployees.add(Employee.builder()
                .firstName("Priya")
                .lastName("Singh")
                .email("priya.singh@mail.com")
                .contactNumber("+918765432109")
                .address("456 Park Avenue, Delhi NCR")
                .dateOfBirth(LocalDate.of(1990, 8, 25))
                .department("HR")
                .position("HR Manager")
                .build());

        given(employeeService.getAllEmployees()).willReturn(listOfEmployees);

        ResultActions response = mockMvc.perform(get("/v1/api/employees"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listOfEmployees.size())));
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Raj")
                .lastName("Kumar")
                .email("raj.kumar@mail.com")
                .contactNumber("+917654321098")
                .address("789 Elm Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1982, 12, 10))
                .department("Finance")
                .position("Financial Analyst")
                .build();

        given(employeeService.getEmployeeById(employeeId)).willReturn(employee);

        ResultActions response = mockMvc.perform(get("/v1/api/employees/{employeeId}", employeeId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(employee.getContactNumber())))
                .andExpect(jsonPath("$.address", is(employee.getAddress())))
                .andExpect(jsonPath("$.dateOfBirth", is(employee.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.department", is(employee.getDepartment())))
                .andExpect(jsonPath("$.position", is(employee.getPosition())));
    }

    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnNotFound() throws Exception {
        long employeeId = 1L;

        given(employeeService.getEmployeeById(employeeId)).willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId));

        ResultActions response = mockMvc.perform(get("/v1/api/employees/{employeeId}", employeeId));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() throws Exception {
        long employeeId = 1L;
        Employee savedEmployee = Employee.builder()
                .firstName("Raj")
                .lastName("Kumar")
                .email("raj.kumar@mail.com")
                .contactNumber("+917654321098")
                .address("789 Elm Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1982, 12, 10))
                .department("Finance")
                .position("Financial Analyst")
                .build();

        Employee updatedEmployee = Employee.builder()
                .firstName("Rajeev")
                .lastName("Kumar")
                .email("rajeeve.kumar@mail.com")
                .contactNumber("+917654321099")
                .address("790 Elm Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1982, 12, 11))
                .department("Finance")
                .position("Senior Financial Analyst")
                .build();

        given(employeeService.getEmployeeById(employeeId)).willReturn(savedEmployee);
        given(employeeService.updateEmployee(any(Long.class), any(Employee.class)))
                .willAnswer(invocation -> updatedEmployee);

        ResultActions response = mockMvc.perform(put("/v1/api/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(updatedEmployee.getContactNumber())))
                .andExpect(jsonPath("$.address", is(updatedEmployee.getAddress())))
                .andExpect(jsonPath("$.dateOfBirth", is(updatedEmployee.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.department", is(updatedEmployee.getDepartment())))
                .andExpect(jsonPath("$.position", is(updatedEmployee.getPosition())));
    }

    @Test
    public void givenInvalidEmployeeId_whenUpdateEmployee_thenReturnNotFound() throws Exception {
        long employeeId = 1L;
        Employee updatedEmployee = Employee.builder()
                .firstName("Rajeev")
                .lastName("Kumar")
                .email("rajeeve.kumar@mail.com")
                .contactNumber("+917654321099")
                .address("790 Elm Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1982, 12, 11))
                .department("Finance")
                .position("Senior Financial Analyst")
                .build();

        given(employeeService.getEmployeeById(employeeId)).willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId));
        given(employeeService.updateEmployee(any(Long.class), any(Employee.class)))
                .willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId));

        ResultActions response = mockMvc.perform(put("/v1/api/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNoContent() throws Exception {
        long employeeId = 1L;

        willDoNothing().given(employeeService).deleteEmployee(employeeId);

        ResultActions response = mockMvc.perform(delete("/v1/api/employees/{employeeId}", employeeId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void whenInvalidEmployeeData_thenReturnBadRequest() throws Exception {
        Employee employee = Employee.builder()
                .firstName("") // Invalid data
                .lastName("kumar")
                .email("kumar") // Invalid email
                .contactNumber("123") // Invalid contact number
                .address("123 Main Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .department("Engineering")
                .position("Developer")
                .build();

        ResultActions response = mockMvc.perform(post("/v1/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is("First name is required")))
                .andExpect(jsonPath("$.email", is("Email should be valid")))
                .andExpect(jsonPath("$.contactNumber", is("Contact number should be valid")));
    }
}
