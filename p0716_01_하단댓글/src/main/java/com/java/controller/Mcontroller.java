package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class Mcontroller {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	@GetMapping("/login") // 로그인 페이지
	public String login() {
		return "/member/login";
	}

	@PostMapping("/login") // 로그인 확인
	public ModelAndView login(MemberDto memberDto) {
		System.out.println("controller id : "+memberDto.getId());
		System.out.println("controller pw : "+memberDto.getPw());
		
		// 로그인 확인
		int chkLogin = 0;
		MemberDto mDto = memberService.selectLogin(memberDto);
		
		if(mDto != null) { // session 추가
			chkLogin = 1;
			System.out.println("controller name : "+mDto.getName());
			session.setAttribute("sessionId", mDto.getId());
			session.setAttribute("sessionName", mDto.getName());
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("chkLogin",chkLogin);
		mv.setViewName("member/doLogin");
		
		return mv;
	}
	
	@RequestMapping("/logout") // 로그아웃
	public ModelAndView logout() {
		int chkLogin = 3;
		
		session.invalidate(); // 세션을 종료	
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("chkLogin",chkLogin);
		mv.setViewName("member/doLogin");
		return mv;
	}
	
}
