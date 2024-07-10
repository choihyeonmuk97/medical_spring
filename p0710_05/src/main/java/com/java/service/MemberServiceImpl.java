package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public MemberDto selectLogin(MemberDto mdto) {
		System.out.println(mdto.getId());
		System.out.println(mdto.getPw());
		
		// DB 연결해서 1개 객체 가져오기
		MemberDto memberDto = memberMapper.selectLogin(mdto);
		System.out.println(memberDto.getId());
		System.out.println(memberDto.getPw());
		System.out.println(memberDto.getName());
		
		return memberDto;
	}


}
