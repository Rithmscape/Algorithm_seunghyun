-- 코드를 입력하세요
SELECT  board.title, 
        board.board_id, 
        reply.reply_id, 
        reply.writer_id, 
        reply.contents,
        date_format(reply.created_date,'%Y-%m-%d') as created_date
FROM USED_GOODS_BOARD as board join USED_GOODS_REPLY as reply on board.board_id = reply.board_id
where   date_format(board.created_date, '%Y-%m') like '2022-10' 
order by reply.created_date asc, board.title asc;