/*
그룹화
-- 결과 집합을 특정 열의 값에 따라 그룹화 하는데 사용
-- HAVING은 GROUP BY 절과 함께 사용하여 그룹에 대한 조건 적용
*/

use employees;
select * from salaries limit 1;

-- 직원별 급여 횟수, 평균, 총합 조회
select
    emp_no,
    count(*) as 급여횟수,
    avg(salary) as 평균급여,
    sum(salary) as 총급여
from salaries
group by emp_no;

-- 평균급여가 50000달러에서 60000달러 사이인 직원 조회
select
    emp_no,
    count(*) as 급여횟수,
    avg(salary) as 평균급여,
    sum(salary) as 총급여
from salaries
group by emp_no
having 평균급여 between 50000 and 60000;


