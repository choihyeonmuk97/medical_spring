package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;
import com.java.dto.LoanDto;
import com.java.service.BoardService;
import com.java.service.EmailService;
import com.java.service.LoanService;


@Controller
public class FController {
	
	@Autowired BoardService boardService;

	@Autowired LoanService loanService;

	@Autowired EmailService emailService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/email")
	public String email() {
		return "email";
	}

	@PostMapping("/email_send") // 이메일 전송
	@ResponseBody
	public String email_send(String name, String email) {
		System.out.println(name);
		System.out.println(email);
		
//		emailService.email_send(name,email);
		emailService.email_send2(name,email);
		
		return "1";
	}

	
	@GetMapping("/chart")
	public String chart() {
		return "chart";
	}

	@GetMapping("/notice_list")
	public ModelAndView notice_list() {
		// board 테이블의 데이터를 가져와서 출력

		ArrayList<BoardDto> list = boardService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("/notice_list");
		
		return mv;
	}

	@PostMapping("/chart_data") // ajax chart 데이터 전송
	@ResponseBody
	public ArrayList<LoanDto> chart_data(String data) {
		
		ArrayList<LoanDto> list = new ArrayList<>();
		
		if(data.equals("region")) list = loanService.selectLoan();
		else list = loanService.selectLoan2();
		
		for(int i=0; i<list.size(); i++) {
			if(data.equals("region")) System.out.println(list.get(i).getRegion()+","+list.get(i).getAmt());
			else System.out.println(list.get(i).getPeriod()+","+list.get(i).getAmt());
		}
		return list;
	}

	
	
}
