-- 자동차 대여 기록 별 대여 금액 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/151141

SELECT
    B.HISTORY_ID,
    FLOOR(
            A.DAILY_FEE *
            (DATEDIFF(B.END_DATE, B.START_DATE) + 1) *
            (100 - COALESCE(D.DISCOUNT_RATE, 0)) / 100
    ) AS FEE
FROM CAR_RENTAL_COMPANY_CAR A
         JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY B
              ON A.CAR_ID = B.CAR_ID
         LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN D
                   ON A.CAR_TYPE = D.CAR_TYPE
                       AND (
                          (D.DURATION_TYPE = '7일 이상' AND DATEDIFF(B.END_DATE, B.START_DATE) + 1 >= 7 AND DATEDIFF(B.END_DATE, B.START_DATE) + 1 < 30) OR
                          (D.DURATION_TYPE = '30일 이상' AND DATEDIFF(B.END_DATE, B.START_DATE) + 1 >= 30 AND DATEDIFF(B.END_DATE, B.START_DATE) + 1 < 90) OR
                          (D.DURATION_TYPE = '90일 이상' AND DATEDIFF(B.END_DATE, B.START_DATE) + 1 >= 90)
                          )
WHERE A.CAR_TYPE = '트럭'
ORDER BY FEE DESC, B.HISTORY_ID DESC;