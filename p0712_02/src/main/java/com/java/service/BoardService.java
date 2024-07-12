package com.java.service;

import com.java.dto.BoardDto;

import java.util.ArrayList;
import java.util.Map;

public interface BoardService {

	// 게시판 리스트 가져오기, 검색 기능 포함
	Map<String, Object> selectList(int page, String category, String searchWord); 

	// 게시글 1개 가져오기(현재,이전,다음글)
	Map<String, Object> selectOne(BoardDto bdto); 

	void insertBoard(BoardDto bdto); // 게시글 저장하기

	void deleteBoard(BoardDto bdto); // 게시글 삭제하기

	BoardDto updateBoard(BoardDto bdto); // 게시글 수정하기

	void doUpdateBoard(BoardDto bdto); // 수정한 글 저장

	void insertReply(BoardDto bdto); // 답글 저장
	

}
