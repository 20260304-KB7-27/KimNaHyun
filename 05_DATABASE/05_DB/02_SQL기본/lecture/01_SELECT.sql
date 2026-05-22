-- SELECT
-- 특정 테이블에서 원하는 데이터를 조회

use sqldb;

select  -- 조회해줘
    userID -- userID 컬럼을
from buybl -- buybl 테이블에서

select * from buytbl;

/*
select 단독 활용
- from 없이 단독 사용 가능
- 단순한 텍스트 출력
*/

select( 5 + 5 );
select now();  -- 데이터베이스 (MYSQL) 내장함수
select concat('bear', '안녕', 'mysql') as name; -- 문자열 합치기
select concat('bear', '안녕', 'mysql') as 'Full Name'; -- 별칭에 공백 쓰려면 따옴표 필요

select
    name,
    concat(mobile1, ' ', mobile2) as 전화번호
from usertbl;


