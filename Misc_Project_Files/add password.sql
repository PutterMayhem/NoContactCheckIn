alter table employee
add username varchar(25),
add password varchar(25);

update employee
set username = "cguster"
where emp_ID = 1;

update employee
set username="mhussein"
where emp_ID = 2;
select * from employee;