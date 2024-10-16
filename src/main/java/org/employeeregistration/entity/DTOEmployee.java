package org.employeeregistration.entity;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@Data
public class DTOEmployee {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^(\\+91|0)?[6-9][0-9]{9}$", message = "Contact number should be valid")
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
