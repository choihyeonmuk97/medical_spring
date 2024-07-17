package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.EmpDto;

@Mapper
public interface EmpDao {

	ArrayList<EmpDto> selectAll();	// emp 가져오기





}
