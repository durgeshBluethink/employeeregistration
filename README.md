Employee Registration System

Overview

The Employee Registration System is a Spring Boot application designed to manage employee information. It provides RESTful API endpoints for creating, retrieving, updating, and deleting employee records. The system uses MySQL for data storage and includes validation for various employee attributes.


Features

Create Employee: Add new employee records with validation for fields like email, contact number, and date of birth.

Get All Employees: Retrieve a list of all employee records.

Get Employee by ID: Fetch details of a specific employee by their unique ID.

Update Employee: Modify existing employee records.

Delete Employee: Remove an employee record from the system.

Endpoints

Create Employee

POST /v1/api/employees

Description: Create a new employee record.

Request Body: JSON object with employee details.

Response: Created employee object.

Get All Employees

GET /v1/api/employees

Description: Retrieve a list of all employees.

Response: Array of employee objects.

Get Employee by ID


GET /v1/api/employees/{employeeId}

Description: Fetch details of an employee by ID.

Response: Employee object.

Update Employee


PUT /v1/api/employees/{employeeId}

Description: Update an existing employee record.

Request Body: JSON object with updated employee details.

Response: Updated employee object.

Delete Employee


DELETE /v1/api/employees/{employeeId}

Description: Delete an employee record by ID.

Response: No content.

Test Invalid Employee Data


POST /v1/api/employees

Description: Test the API with invalid employee data to ensure validation works.

Request Body: JSON object with invalid employee details.

Response: Validation errors.

Project Setup

Prerequisites

Java 1.8 

Maven

MySQL 8.0

Installation

Clone the Repository


bash

Copy code

git clone https://github.com/yourusername/your-repository.git

Navigate to the Project Directory


bash
Copy code
cd your-repository
Configure Database

Update application.properties with your MySQL database details.

Build and Run the Application



mvn clean install

mvn spring-boot:run

Running Tests

The project uses JUnit and Mockito for testing. To run unit tests:



mvn test

Unit Testing:

Unit tests are located in the src/test/java directory.

They test various components of the application including controllers, services, and repositories.

Mockito:

Mockito is used for mocking dependencies in unit tests.

Mocks are configured to simulate interactions with services and repositories without requiring actual database access.

Example Unit Test

The provided tests cover:


Creating Employee: Verifies that an employee is created successfully and the correct data is returned.

Retrieving Employees: Ensures that a list of employees is retrieved correctly.

Getting Employee by ID: Confirms that the details of a specific employee can be fetched by ID.

Updating Employee: Checks that employee details can be updated and the updated data is returned.

Deleting Employee: Verifies that an employee record can be deleted.

Handling Invalid Data: Tests that validation errors are correctly returned for invalid input data.

Contributing

Fork the repository.

Create a new branch (git checkout -b feature-branch).

Commit your changes (git commit -am 'Add new feature').

Push to the branch (git push origin feature-branch).

Create a new Pull Request.

License

This project is licensed under the MIT License - see the LICENSE file for details.

Contact

For any inquiries or issues, please contact:

Name: Durgesh Kumar
