package com.java.service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public ArrayList<BoardDto> selectList() {

        // mybatis 연결해서 list 가져오기
        ArrayList<BoardDto> list = boardDao.selectList();

        return list;
    } // selectList

	@Override
	public BoardDto selectOne(BoardDto bdto) {
		boardDao.updateBhit(bdto); // 조회 수 1씩 증가
		BoardDto boardDto = boardDao.selectOne(bdto);
		
		return boardDto;
	} // selectOne

	@Override
	public void insertBoard(BoardDto bdto) {
		boardDao.insertBoard(bdto);
	} // insertBoard

	@Override
	public void deleteBoard(BoardDto bdto) {
		boardDao.deleteBoard(bdto);
	} // delete

	@Override
	public BoardDto updateBoard(BoardDto bdto) {
		BoardDto boardDto = boardDao.selectOne(bdto);
		return boardDto;
	} // modify

	@Override
	public void doUpdateBoard(BoardDto bdto) {
		boardDao.updateBoard(bdto);
	} // modify_save

	@Override
	public void insertReply(BoardDto bdto) {
		boardDao.updateBstep(bdto); // 부모보다 큰 step 1씩 증가
		boardDao.insertReply(bdto);
	} // repaly_save
    
    
    
    
}
