-- 코드를 입력하세요
SELECT ICECREAM_INFO.INGREDIENT_TYPE, sum(TOTAL_ORDER) as TOTAL_ORDER
FROM FIRST_HALF join ICECREAM_INFO on FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR
group by ICECREAM_INFO.INGREDIENT_TYPE;