-- UNION / UNION ALL
-- 두 개 이상의 SELECT 결과를 세로 방향으로 합치는 연산

/*
UNION
- 두 SELECT 결과를 합친 뒤 중복을 제거
- 내부적으로 자체 정렬을 수행
UNION ALL
- 두 SELECT 결과를 합침 (중복 제거 X)
- UNION보다 빠름

규칙
- 각 select의 컬럼 수가 같아야함.
- 대응하는 컬럼의 데이터 타입이 호환이 되어야함.
*/
