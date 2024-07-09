package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.Students;

@Controller
@RequestMapping("/students")
public class StuController {
	
	@RequestMapping("/students")
	public String students() {
		return "students/students";
	}
	
	@RequestMapping("/doStudents")
	public ModelAndView doStudents(Students stu) {

		stu.setTotal(stu.getKor()+stu.getEng()+stu.getMath());
		stu.setAvg(stu.getTotal()/3.0);
		stu.setHobby(Arrays.toString(stu.getHobbys()));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", stu);
		mv.setViewName("students/doStudents");
		
		return mv ;
	}
	
	
	@RequestMapping("/stuUpdate")
	public ModelAndView stuUpdate(Students stu) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", stu);
		mv.setViewName("students/stuUpdate");
		return mv;
	}
	
	
}
