-- Insert demo data into the 'department' table
INSERT INTO department (budget, description, location, name, update_date, manager_id)
VALUES
    (100000, 'Finance Department', 'New York', 'Finance', NOW(), NULL),
    (150000, 'IT Department', 'San Francisco', 'IT', NOW(), NULL),
    (120000, 'Marketing Department', 'Los Angeles', 'Marketing', NOW(), NULL);

-- Insert demo data into the 'employee' table
INSERT INTO employee (address, first_name, last_name, phone_number, salary, start_date, update_date, department_id)
VALUES
    ('123 Main St', 'John', 'Doe', 123456789, 60000.00, '2022-01-01', NOW(), 1),
    ('456 Oak St', 'Jane', 'Smith', 987654321, 70000.00, '2022-02-01', NOW(), 2),
	('123 Main St', 'John', 'Doe', 123456789, 60000.00, '2022-01-01', NOW(), 1),
    ('456 Oak St', 'Jane', 'Smith', 987654321, 70000.00, '2022-02-01', NOW(), 2),
    ('789 Elm St', 'Bob', 'Johnson', 555666777, 80000.00, '2022-03-01', NOW(), 3),
    ('789 Elm St', 'Bob', 'Johnson', 555666777, 80000.00, '2022-03-01', NOW(), 3);

-- Insert demo data into the 'project' table
INSERT INTO project (cost, description, name, start_date, update_date, department_id)
VALUES
    (5000.00, 'Finance Software Upgrade', 'Finance Upgrade', '2022-01-15', NOW(), 1),
    (8000.00, 'IT Infrastructure Enhancement', 'IT Enhancement', '2022-02-10', NOW(), 2),
    (10000.00, 'Marketing Campaign Launch', 'Marketing Campaign', '2022-03-05', NOW(), 3),
    (5000.00, 'Finance Software Upgrade', 'Finance Upgrade', '2022-01-15', NOW(), 1),
    (8000.00, 'IT Infrastructure Enhancement', 'IT Enhancement', '2022-02-10', NOW(), 2),
    (10000.00, 'Marketing Campaign Launch', 'Marketing Campaign', '2022-03-05', NOW(), 3);

-- Assign employees to projects in the 'employee_project_map' table
INSERT INTO employee_project_map (project_id, employee_id)
VALUES
    (1, 1),
    (2, 2), 
    (3, 3), 
    (1, 4), 
    (2, 1), 
    (3, 2); 
    


