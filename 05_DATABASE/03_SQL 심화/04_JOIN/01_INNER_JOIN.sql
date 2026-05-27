/*
SELECT 컬럼, ...
FROM 테이블 A
INNER JOIN 테이블 B ON 테이블 A.컬럼 = 테이블B.컬럼

INNER JOIN
- ON 조건에 매칭되는 행끼리 결합
- 가장 일반적으로 사용되는 JOIN (INNER JOIN = JOIN)

*/
use employees;

select emp_no, dept_no, from_date, to_date
from dept_emp
limit 5;

select emp_no, first_name, last_name
from employees
limit 5;

select employees.emp_no, employees.first_name, employees.last_name, dept_emp.dept_no, dept_emp.from_date, dept_emp.to_date
from employees
inner join dept_emp on employees.emp_no = dept_emp.emp_no
limit 5;

select e.emp_no, e.first_name, e.last_name, d.dept_no, d.from_date, d.to_date
from employees as e
inner join dept_emp as d on d.emp_no = d.emp_no
limit 5;


select e.emp_no, e.first_name, e.last_name, d.dept_no, d.from_date, d.to_date
from employees as e
inner join dept_emp as d on d.emp_no = d.emp_no
limit 5;

