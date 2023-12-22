-- employee view
create view employee_view 
as
select e.employee_id, e.first_name, e.last_name, e.phone_number, e.salary, e.start_date
from employee e;

-- department view
create view department_view
as
select d.id, d.name, d.description, d.location, d.manager_id
from department d;

-- project view
create view project_view
as
select p.id, p.name, p.description, p.start_date, p.department_id
from project p;




