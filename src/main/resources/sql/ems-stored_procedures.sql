-- procedures on employees

-- get all employees
delimiter //
create procedure GetAllEmployees()
begin
	select * from employee;
end//
delimiter ;

-- get employees of a specific department
delimiter //
create procedure GetEmployeesOfDepartment(IN departmentId INT)
begin
	select * from employee e 
	where e.department_id=departmentId;
end//
delimiter ;

-- get employee by id
delimiter //
create procedure GetEmployeeById(IN employeeId INT)
begin
	select * from employee e
	where e.employee_id=employeeId;
end//
delimiter ;

-- add new employee
DELIMITER //
CREATE PROCEDURE AddEmployee(
    IN inFirstName VARCHAR(255),
    IN inLastName VARCHAR(255),
    IN inAddress VARCHAR(255),
    IN inPhoneNumber BIGINT,
    IN inSalary DOUBLE,
    IN inStartDate DATETIME,
    IN inDepartmentId BIGINT
)
BEGIN
    INSERT INTO employee (
        first_name, last_name, address, phone_number, salary, start_date, department_id
    ) VALUES (
        inFirstName, inLastName, inAddress, inPhoneNumber, inSalary, inStartDate, inDepartmentId
    );
END //
DELIMITER ;

-- update employee
DELIMITER //
CREATE PROCEDURE updateEmployee(
	IN inId int,
    IN inFirstName VARCHAR(255),
    IN inLastName VARCHAR(255),
    IN inAddress VARCHAR(255),
    IN inPhoneNumber BIGINT,
    IN inSalary DOUBLE,
    IN inStartDate DATETIME,
    IN inDepartmentId BIGINT
)
BEGIN
    UPDATE employee
    SET 
		first_name = inFirstName,
        last_name = inLastName,
        address = inAddress,
        phone_number = inPhoneNumber,
        salary = inSalary,
        start_date = inStartDate,
        department_id = inDepartmentId
    WHERE
        employee_id = inEmployeeId;
END //
DELIMITER ;

-- count of employee in department
delimiter //
create procedure employeeCount(IN departmentId int, out empCount int)
begin
	select count(e.employee_id) into empCount
    from employee e
    where e.department_id=departmentId;
end //
delimiter ;

-- get employees by salary between
delimiter //
create procedure getBySalaryBetween(IN up double, IN low double)
begin
	select *
    from employee e
    where e.salary between up and low;
end //
delimiter ;





