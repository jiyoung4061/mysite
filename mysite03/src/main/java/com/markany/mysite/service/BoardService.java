package com.markany.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markany.mysite.repository.BoardRepository;
import com.markany.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getList() {
		return boardRepository.findAll();
	}

//	public int getMaxPage() {
//		return (boardRepository.countOfBoard()/10)+1;
//	}	
}
