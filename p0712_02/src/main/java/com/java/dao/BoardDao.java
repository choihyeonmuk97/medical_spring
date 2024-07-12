package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardDao {

	// 게시글 리스트(검색 기능 포함) 
	ArrayList<BoardDto> selectList(int startRow, int endRow, String category, String searchWord); 

	int selectCount(String category, String searchWord); // 총 게시글 수 (검색 기능 포함)

	BoardDto selectOne(BoardDto bdto); // 선택한 게시글 1개(현재글)
	BoardDto selectOnePrev(BoardDto bdto); // 이전글
	BoardDto selectOneNext(BoardDto bdto); // 다음글

	void updateBhit(BoardDto bdto); // 조회 수 1씩 증가

	void insertBoard(BoardDto bdto); // 게시글 저장

	void deleteBoard(BoardDto bdto); // 게시글 삭제

	void updateBoard(BoardDto bdto); // 수정 게시글 저장

	void insertReply(BoardDto bdto); // 답글 저장

	void updateBstep(BoardDto bdto); // step 1씩 증가




	
}
