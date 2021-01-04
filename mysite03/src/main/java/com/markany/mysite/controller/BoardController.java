package com.markany.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.markany.mysite.service.BoardService;
import com.markany.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index( Model model) {
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/index";
	}
}
