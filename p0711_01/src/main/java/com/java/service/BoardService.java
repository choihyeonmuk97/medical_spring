package com.java.service;

import com.java.dto.BoardDto;

import java.util.ArrayList;

public interface BoardService {

    ArrayList<BoardDto> selectList(); // 게시판 리스트 가져오기

	BoardDto selectOne(BoardDto bdto); // 게시글 1개 가져오기

	void insertBoard(BoardDto bdto); // 게시글 저장하기

	void deleteBoard(BoardDto bdto); // 게시글 삭제하기

	BoardDto updateBoard(BoardDto bdto); // 게시글 수정하기

	void doUpdateBoard(BoardDto bdto); // 수정한 글 저장

	void insertReply(BoardDto bdto); // 답글 저장
	

}
