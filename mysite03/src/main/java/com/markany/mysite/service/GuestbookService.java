package com.markany.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markany.mysite.repository.GuestBookRepository;
import com.markany.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestBookRepository guestbookRepository;

	public List<GuestBookVo> getMessageList() {
		return guestbookRepository.findAll();
	}

	public boolean writeMessage(GuestBookVo vo) {
		int count = guestbookRepository.insert(vo);
		return count == 1;
	}

	public boolean deleteMessage(GuestBookVo vo) {
		int count = guestbookRepository.delete(vo);
		return count == 1;
	}
	
	
}
