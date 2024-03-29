-- 보호 시작일보다 입양일이 더 빠른 동물 조회
-- 보호 시작일이 빠른 순으로 조회

SELECT INS.ANIMAL_ID, INS.NAME
FROM ANIMAL_INS AS INS JOIN ANIMAL_OUTS AS OUTS ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE INS.DATETIME > OUTS.DATETIME
ORDER BY INS.DATETIME ASC;