package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontController {
	
	// get 
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	// get, post 둘 다 사용 가능
	@RequestMapping("/login") // 약식
	public String login() {
		return "login";
	}
	
	// get
	@GetMapping("/board/board")
	public String board() {
		return "board/board";
	}
	
	// post
	@PostMapping("/doBoard")
	public String doBoard() {
		return "doBoard";
	}
	
	@RequestMapping("/board/form")
	public String form() {
		return "board/form";
	}
	
	@RequestMapping("/board/doForm")
	public String doForm(HttpServletRequest requset, Model model) {
		System.out.println("bno : "+requset.getParameter("bno"));
		System.out.println("btitle : "+requset.getParameter("btitle"));
		model.addAttribute("bno",requset.getParameter("bno"));
		model.addAttribute("btitle",requset.getParameter("btitle"));
		return "board/doForm";
	}

	/*-----------------------------------------------------------------------*/
	
	@RequestMapping("/board/form2")
	public String form2() {
		return "board/form2";
	}

	@RequestMapping("/board/doForm2")
	public ModelAndView doForm2(HttpServletRequest requset) {
		System.out.println("bno : "+requset.getParameter("bno"));
		System.out.println("btitle : "+requset.getParameter("btitle"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bno",requset.getParameter("bno"));
		mv.addObject("btitle",requset.getParameter("btitle"));
		mv.setViewName("board/doForm2");
		
		return mv;
	}
	
	/*---------------------------------------------------------------------------*/
	
	
	
}
