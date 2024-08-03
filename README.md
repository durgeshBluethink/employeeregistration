# employeeregistration
Endpoints in EmployeeController.java
Here are the endpoints for your API:

Create Employee

POST /v1/api/employees
Get All Employees

GET /v1/api/employees
Get Employee by ID

GET /v1/api/employees/{employeeId}
Update Employee

PUT /v1/api/employees/{employeeId}
Delete Employee

DELETE /v1/api/employees/{employeeId}
Test Invalid Employee Data

POST /v1/api/employees

1. Create Employee
Method: POST
URL: http://localhost:8080/v1/api/employees
Headers:
Content-Type: application/json
Body (raw, JSON):

{
  "firstName": "Amit",
  "lastName": "Sharma",
  "email": "amit.sharma@mail.com",
  "contactNumber": "+919876543210",
  "address": "123 Main Street, Delhi NCR",
  "dateOfBirth": "1985-05-15",
  "department": "Engineering",
  "position": "Software Engineer"
}
2. Get All Employees
Method: GET
URL: http://localhost:8080/v1/api/employees
Headers: None
3. Get Employee by ID
Method: GET
URL: http://localhost:8080/v1/api/employees/{employeeId}
Headers: None
URL Parameter:
employeeId: Replace {employeeId} with the ID of the employee you want to retrieve.
4. Update Employee
Method: PUT
URL: http://localhost:8080/v1/api/employees/{employeeId}
Headers:
Content-Type: application/json
Body (raw, JSON):


{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@mail.com",
  "contactNumber": "+919876543211",
  "address": "124 Main Street, Delhi NCR",
  "dateOfBirth": "1990-01-01",
  "department": "Engineering",
  "position": "Senior Developer"
}
URL Parameter:
employeeId: Replace {employeeId} with the ID of the employee you want to update.
5. Delete Employee
Method: DELETE
URL: http://localhost:8080/v1/api/employees/{employeeId}
Headers: None
URL Parameter:
employeeId: Replace {employeeId} with the ID of the employee you want to delete.
6. Test Invalid Employee Data
Method: POST
URL: http://localhost:8080/v1/api/employees
Headers:
Content-Type: application/json
Body (raw, JSON):


{
  "firstName": "", // Invalid data
  "lastName": "Doe",
  "email": "invalid-email", // Invalid email
  "contactNumber": "123", // Invalid contact number
  "address": "123 Main Street, Delhi NCR",
  "dateOfBirth": "1990-01-01",
  "department": "Engineering",
  "position": "Developer"
}
Replace http://localhost:8080 with your actual server URL if it's different.
