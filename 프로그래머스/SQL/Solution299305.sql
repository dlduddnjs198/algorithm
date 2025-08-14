-- 대장균들의 자식의 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/299305

SELECT A.ID as ID, COUNT(B.ID) as CHILD_COUNT
FROM ECOLI_DATA as A LEFT JOIN ECOLI_DATA as B ON A.ID = B.PARENT_ID
GROUP BY A.ID