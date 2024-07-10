package com.java.controller;

import com.java.dto.BoardDto;
import com.java.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/list")
    public ModelAndView list(){

        // DB 연결
        ArrayList<BoardDto> list = boardService.selectList();
        ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.setViewName("board/list");

        return mv;
    }

}
