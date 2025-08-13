-- 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/284528

SELECT A.EMP_NO as EMP_NO, A.EMP_NAME as EMP_NAME,
       CASE WHEN AVG(B.SCORE) >= 96 THEN 'S'
            WHEN AVG(B.SCORE) >= 90 THEN 'A'
            WHEN AVG(B.SCORE) >= 80 THEN 'B'
            ELSE 'C'
           END AS GRADE,
       CASE WHEN AVG(B.SCORE) >= 96 THEN FLOOR((A.SAL * 0.2))
            WHEN AVG(B.SCORE) >= 90 THEN FLOOR((A.SAL * 0.15))
            WHEN AVG(B.SCORE) >= 80 THEN FLOOR((A.SAL * 0.1))
            ELSE 0
           END AS BONUS
FROM HR_EMPLOYEES as A JOIN HR_GRADE as B ON A.EMP_NO = B.EMP_NO
GROUP BY A.EMP_NO, A.EMP_NAME, A.SAL
ORDER BY A.EMP_NO ASC