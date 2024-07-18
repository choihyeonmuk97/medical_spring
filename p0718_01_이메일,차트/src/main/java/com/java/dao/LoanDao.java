package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.LoanDto;

@Mapper
public interface LoanDao {

	ArrayList<LoanDto> selectLoan(); // region

	ArrayList<LoanDto> selectLoan2(); // period

}
