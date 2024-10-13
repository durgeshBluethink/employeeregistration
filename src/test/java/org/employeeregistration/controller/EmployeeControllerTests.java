package org.employeeregistration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.employeeregistration.entity.Employee;
import org.employeeregistration.entity.DTOEmployee;
import org.employeeregistration.exception.ResourceNotFoundException;
import org.employeeregistration.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        DTOEmployee dtoEmployee = DTOEmployee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .department("Engineering")
                .position("Software Engineer")
                .build();

        Employee savedEmployee = Employee.builder()
                .employeeId(1)
                .firstName(dtoEmployee.getFirstName())
                .lastName(dtoEmployee.getLastName())
                .email(dtoEmployee.getEmail())
                .contactNumber(dtoEmployee.getContactNumber())
                .address(dtoEmployee.getAddress())
                .dateOfBirth(dtoEmployee.getDateOfBirth())
                .department(dtoEmployee.getDepartment())
                .position(dtoEmployee.getPosition())
                .build();

        given(employeeService.createEmployee(any(DTOEmployee.class)))
                .willReturn(savedEmployee);

        ResultActions response = mockMvc.perform(post("/v1/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoEmployee)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(savedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(savedEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(savedEmployee.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(savedEmployee.getContactNumber())))
                .andExpect(jsonPath("$.address", is(savedEmployee.getAddress())))
                .andExpect(jsonPath("$.dateOfBirth", is(savedEmployee.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.department", is(savedEmployee.getDepartment())))
                .andExpect(jsonPath("$.position", is(savedEmployee.getPosition())));
    }

    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() throws Exception {
        Employee employee1 = Employee.builder().employeeId(1).firstName("Amit").lastName("Sharma").build();
        Employee employee2 = Employee.builder().employeeId(2).firstName("Raj").lastName("Kumar").build();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        given(employeeService.getAllEmployees()).willReturn(employees);

        ResultActions response = mockMvc.perform(get("/v1/api/employees"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(employees.size())))
                .andExpect(jsonPath("$[0].firstName", is(employee1.getFirstName())))
                .andExpect(jsonPath("$[1].firstName", is(employee2.getFirstName())));
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() throws Exception {
        Integer employeeId = 1;
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .firstName("Amit")
                .lastName("Sharma")
                .build();

        given(employeeService.getEmployeeById(employeeId)).willReturn(employee);

        ResultActions response = mockMvc.perform(get("/v1/api/employees/{employeeId}", employeeId));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())));
    }

    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnNotFound() throws Exception {
        Integer employeeId = 1;

        given(employeeService.getEmployeeById(employeeId)).willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId));

        ResultActions response = mockMvc.perform(get("/v1/api/employees/{employeeId}", employeeId));

        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Employee not found with id " + employeeId)));
    }

    @Test
    public void givenUpdatedEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        Integer employeeId = 1;
        DTOEmployee dtoEmployee = DTOEmployee.builder()
                .firstName("Amit")
                .lastName("Sharma")
                .email("amit.sharma@mail.com")
                .contactNumber("+919876543210")
                .address("123 Main Street, Delhi NCR")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .department("Engineering")
                .position("Senior Software Engineer")
                .build();

        Employee updatedEmployee = Employee.builder()
                .employeeId(employeeId)
                .firstName(dtoEmployee.getFirstName())
                .lastName(dtoEmployee.getLastName())
                .email(dtoEmployee.getEmail())
                .contactNumber(dtoEmployee.getContactNumber())
                .address(dtoEmployee.getAddress())
                .dateOfBirth(dtoEmployee.getDateOfBirth())
                .department(dtoEmployee.getDepartment())
                .position(dtoEmployee.getPosition())
                .build();

        given(employeeService.updateEmployee(eq(employeeId), any(DTOEmployee.class)))
                .willReturn(updatedEmployee);

        ResultActions response = mockMvc.perform(put("/v1/api/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoEmployee)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                .andExpect(jsonPath("$.position", is(updatedEmployee.getPosition())));
    }

    @Test
    public void givenInvalidEmployeeId_whenUpdateEmployee_thenReturnNotFound() throws Exception {
        Integer employeeId = 1;
        DTOEmployee dtoEmployee = DTOEmployee.builder().firstName("Amit").lastName("Sharma").build();

        given(employeeService.updateEmployee(eq(employeeId), any(DTOEmployee.class)))
                .willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId));

        ResultActions response = mockMvc.perform(put("/v1/api/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoEmployee)));

        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Employee not found with id " + employeeId)));
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNoContent() throws Exception {
        Integer employeeId = 1;

        willDoNothing().given(employeeService).deleteEmployee(employeeId);

        ResultActions response = mockMvc.perform(delete("/v1/api/employees/{employeeId}", employeeId));

        response.andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenInvalidEmployeeId_whenDeleteEmployee_thenReturnNotFound() throws Exception {
        Integer employeeId = 1;

        willThrow(new ResourceNotFoundException("Employee not found with id " + employeeId)).given(employeeService).deleteEmployee(employeeId);

        ResultActions response = mockMvc.perform(delete("/v1/api/employees/{employeeId}", employeeId));

        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Employee not found with id " + employeeId)));
    }
}
