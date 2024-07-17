package com.java.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.java.dto.DepDto;
import com.java.dto.EmpDto;


public interface DepService {

	ArrayList<DepDto> selectAll();  // dep 가져오기

}
