package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.DepDto;
import com.java.dto.EDDto;
import com.java.dto.EmpDepDto;
import com.java.dto.EmpDto;
import com.java.service.DepService;
import com.java.service.EDService;
import com.java.service.EmpDepService;
import com.java.service.EmpService;

@Controller
public class FrontController {

	@Autowired
	EmpService empService;
	
	@Autowired
	DepService depService;
	
	@Autowired
	EmpDepService empDepService;
	
	@Autowired
	EDService edService;	// 객체 사용
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
		
	}
	
	@RequestMapping("/emp_list")	// 사원
	public ModelAndView emp_list() {
		ArrayList<EmpDto> list = empService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("emp_list");

		return mv;
		
	}
	
	@RequestMapping("/dep_list")	// 부서
	public ModelAndView dep_list() {
		ArrayList<DepDto> list = depService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dep_list", list);
		mv.setViewName("dep_list");
		
		return mv;
	}

	@RequestMapping("/empDep_list")		// 사원+부서
	public ModelAndView empDep_list() {
		ArrayList<EmpDepDto> list = empDepService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("empDep_list", list);
		mv.setViewName("empDep_list");
		
		return mv;
	}
	
	@RequestMapping("/ed_list")		// 사원+부서
	public ModelAndView ed_list() {
		ArrayList<EDDto> list = edService.selectAll();
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("ed_list", list);
		mv.setViewName("ed_list");
		
		return mv;
	}
	
	
	

	
}
