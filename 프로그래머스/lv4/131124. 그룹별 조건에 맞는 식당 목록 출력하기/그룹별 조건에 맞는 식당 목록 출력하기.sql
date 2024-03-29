# 식당의 정보         REST_INFO
# 식당의 리뷰 정보     REST_REVIEW

-- 리뷰를 가장 많이 작성한 회원의 리뷰들을 조회
-- 리뷰 작성일을 기준으로 오름차순, 리뷰 텍스트를 기준으로 오름차순 

SELECT  MEMBER_PROFILE.MEMBER_NAME,
        REST_REVIEW.REVIEW_TEXT, 
        DATE_FORMAT(REST_REVIEW.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
        
FROM    MEMBER_PROFILE
        JOIN REST_REVIEW ON MEMBER_PROFILE.MEMBER_ID = REST_REVIEW.MEMBER_ID
        
WHERE MEMBER_PROFILE.MEMBER_ID LIKE
(
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    ORDER BY COUNT(MEMBER_ID) DESC
    LIMIT 1
)
ORDER BY REST_REVIEW.REVIEW_DATE, REST_REVIEW.REVIEW_TEXT;
