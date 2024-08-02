package org.employeeregistration.employee.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^(\\+91)?[6789]\\d{9}$", message = "Contact number should be valid and 10 digits")
    private String contactNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @Past(message = "Date of Birth should be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Position is required")
    private String position;
}
