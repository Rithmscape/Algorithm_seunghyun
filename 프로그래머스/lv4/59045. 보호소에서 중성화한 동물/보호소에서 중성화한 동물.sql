-- ANIMAL_INS 보호소에 들어온 동물의 정보
-- ANIMAL_OUTS 입양보낸 동물의 정보

-- INS에서는 중성화되지 않았지만 OUTS에서는 중성화가 된 동물 조회

SELECT OUTS.ANIMAL_ID, OUTS.ANIMAL_TYPE, OUTS.NAME
FROM ANIMAL_INS AS INS JOIN ANIMAL_OUTS AS OUTS ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE   (INS.SEX_UPON_INTAKE LIKE 'Intact%') AND
        (OUTS.SEX_UPON_OUTCOME LIKE 'Spayed%' OR OUTS.SEX_UPON_OUTCOME LIKE 'Neutered%')
ORDER BY ANIMAL_ID;
