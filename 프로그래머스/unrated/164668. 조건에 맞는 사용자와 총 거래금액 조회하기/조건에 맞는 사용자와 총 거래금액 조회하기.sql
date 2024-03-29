# 중고 거래 게시판 정보 USED_GOODS_BOARD
# 중고 거래 게시판 첨부파일 정보 USED_GOODS_FILE

-- 완료된 중고 거래의 총금액이 70만 원 이상인 사람의 회원 ID, 닉네임, 총거래금액을 조회
--  총거래금액을 기준으로 오름차순

SELECT  USER_ID,
        NICKNAME,
        SUM(PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD AS BOARD JOIN USED_GOODS_USER  AS USER ON BOARD.WRITER_ID = USER.USER_ID
WHERE BOARD.STATUS = 'DONE'
GROUP BY BOARD.WRITER_ID
HAVING SUM(PRICE) >= 700000
ORDER BY TOTAL_SALES ASC;