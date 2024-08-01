package org.employeeregistration.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeesId;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{10,13}$", message = "Contact Number should be valid")
    private String contactNumber;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Past(message = "Date of Birth should be in the past")
    @NotNull(message = "Date of Birth is mandatory")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @NotBlank(message = "Position/Role is mandatory")
    private String position;
}
