package com.java.service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

import oracle.jdbc.Const;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override // 게시판 리스트 가져오기 (검색 기능 포함)
    public Map<String, Object> selectList(int page, String category, String searchWord) {
    	
    	// 하단 넘버링
    	int countPerPage = 10; // 한 페이지의 게시글 수
    	int bottomPerNum = 10; // 하단 페이지 넘버의 개수
    	int countAll = boardDao.selectCount(category,searchWord); // 총 게시글 수
//    	System.out.println(countAll);
    	
    	
    	int maxPage = (int)Math.ceil((double)countAll/countPerPage); // 최대 페이지
    	int startPage = ((page-1)/bottomPerNum)*bottomPerNum+1;
    	int endPage = (startPage+bottomPerNum)-1; 
    	
    	if(endPage>maxPage) endPage = maxPage; // 마지막 넘버링이 최대 페이지의 숫자보다 크면 마지막 넘버링을 최대 페이지로 함
    	
    	int startRow = (page-1)*countPerPage+1;
    	int endRow = startRow+countPerPage-1;
    	
        // MyBatis 연결해서 list 가져오기 (검색 기능 포함)
        ArrayList<BoardDto> list = boardDao.selectList(startRow,endRow,category,searchWord);
        
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("countAll", countAll);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("maxPage", maxPage);
        map.put("page", page);
        map.put("category", category);
        map.put("searchWord", searchWord);

        return map;
    } // selectList

	@Override
	public Map<String, Object> selectOne(BoardDto bdto) {
		boardDao.updateBhit(bdto); // 조회 수 1씩 증가
		BoardDto boardDto = boardDao.selectOne(bdto);
		BoardDto prevDto = boardDao.selectOnePrev(bdto);
		BoardDto nextDto = boardDao.selectOneNext(bdto);
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardDto", boardDto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);
		
		return map;
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
