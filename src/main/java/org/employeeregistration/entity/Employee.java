package org.employeeregistration.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String department;
    private String position;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
