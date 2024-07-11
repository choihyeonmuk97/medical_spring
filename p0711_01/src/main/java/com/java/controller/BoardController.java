package com.java.controller;

import com.java.dto.BoardDto;
import com.java.service.BoardService;
import com.java.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService; // IOC 컨테이너에서 주입받음.

    @RequestMapping("/list") // 게시글 리스트
    public ModelAndView list(){

        ArrayList<BoardDto> list = boardService.selectList();

        ModelAndView mv = new ModelAndView();
        mv.addObject("list",list);
        mv.setViewName("board/list");
        
        return mv;
    } // list
    
    @RequestMapping("/view") // 1개의 게시글 가져오기
    public ModelAndView view (BoardDto bdto) {
    	
    	BoardDto boardDto = boardService.selectOne(bdto);
    	
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("boardDto", boardDto);
    	mv.setViewName("board/view");
    	
    	return mv;
    } // view
    
    @GetMapping("/write") // 글쓰기 화면
    public String write() {
    	return "board/write";
    } // write_view
    
    @PostMapping("/write") // 글쓰기 저장
    public String write(BoardDto bdto, @RequestPart MultipartFile files) {
    	System.out.println(files.getOriginalFilename());
    	String uFile = "";
    	
    	// 같은 이름의 파일이 존재하면 파일 이름을 바꿈 
    	if(!files.isEmpty()) {
    		long time = System.currentTimeMillis();
    		System.out.println(time);
    		
//	    	UUID uuid = UUID.randomUUID();
//	    	System.out.println(uuid);
    		
    		uFile = String.format("%d_%s", time,files.getOriginalFilename());
    		String saveUrl = "c:/upload/";
    		File f = new File(saveUrl+uFile);
    		try {
    			files.transferTo(f);
    		} catch (Exception e) {e.printStackTrace();} // 파일 업로드
    	} //if
    	
    	// 변경된 파일 이름 저장
    	bdto.setBfile(uFile);
    	boardService.insertBoard(bdto);
    	
    	return "redirect:/board/list";
    } // write_save
    
    @RequestMapping("/delete") // 게시글 삭제
    public String delete(BoardDto bdto) {
    	System.out.println(bdto.getBno());
    	boardService.deleteBoard(bdto);
    	
    	return "redirect:/board/list";
    } // delete
    
    @RequestMapping("/update") // 게시글 수정 화면
    public ModelAndView update(BoardDto bdto) {
    	BoardDto boardDto = boardService.updateBoard(bdto);
    	
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("boardDto",boardDto);
    	mv.setViewName("/board/modify");
    	
    	return mv;
    } // modify_page
    
    @RequestMapping("/doUpdate") // 수정한 게시글 저장
    public String doUpdate(BoardDto bdto, @RequestPart MultipartFile files) {
    	
    	String uFile = "";
    	
    	// 같은 이름의 파일이 존재하면 파일 이름을 바꿈 
    	if(!files.isEmpty()) {
    		long time = System.currentTimeMillis();
    		System.out.println(time);
    		
//	    	UUID uuid = UUID.randomUUID();
//	    	System.out.println(uuid);
    		
    		uFile = String.format("%d_%s", time,files.getOriginalFilename());
    		String saveUrl = "c:/upload/";
    		File f = new File(saveUrl+uFile);
    		try {
    			files.transferTo(f);
    		} catch (Exception e) {e.printStackTrace();} // 파일 업로드
    		// 변경된 파일 이름 저장
    		bdto.setBfile(uFile);
    	} //if
    	
    	
    	boardService.doUpdateBoard(bdto);
    	System.out.println(bdto.getBno());
    	
    	return "redirect:/board/list";
    } // modify_save 
    
    @RequestMapping("/reply") // 답글달기 화면
    public ModelAndView reply(BoardDto bdto) {
    	BoardDto boardDto = boardService.selectOne(bdto);
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("boardDto", boardDto);
    	mv.setViewName("board/reply");
    	
    	return mv;
    } // reply_page
    
    @RequestMapping("/doReply") // 답글 저장
    public String doReply(BoardDto bdto, @RequestPart MultipartFile files) {
    	
    	String uFile = "";
    	
    	// 같은 이름의 파일이 존재하면 파일 이름을 바꿈 
    	if(!files.isEmpty()) {
    		long time = System.currentTimeMillis();
    		System.out.println(time);
    		
    		uFile = String.format("%d_%s", time,files.getOriginalFilename());
    		String saveUrl = "c:/upload/";
    		File f = new File(saveUrl+uFile);
    		try {
    			files.transferTo(f);
    		} catch (Exception e) {e.printStackTrace();} // 파일 업로드
    		// 변경된 파일 이름 저장
    	} //if
    	
    	bdto.setBfile(uFile);
    	
    	boardService.insertReply(bdto);
    	return "redirect:/board/list";
    } // reply_save
    
    
} // class
