-- 날짜 내장함수
use employees;

select emp_no
from employees
limit 10;

select
    emp_no,
    hire_date,
    year(hire_date), # 연도 추출
    month(hire_date), # 월 추출
    day(hire_date) # 일 추출
from employees
limit 10;

/*
DATEDIFF(date1, date2)
- date1 에서 date2를 뺀 일수 차이를 구함
*/

select
    emp_no,
    hire_date,
    datediff(curdate(), hire_date)
from employees
limit 10;

/*
DATE_FORMAT(date, format)
- %Y : 4자리 연도, %m : 2자리 월, %d : 2자리 일
- %y : 2자리 연도, %M : 영문 월 이름, %W : 영문 요일 이름
*/

select
    emp_no,
    hire_date,
    date_format(hire_date, '%Y년 %m월 %d일')
from employees
limit 10;

