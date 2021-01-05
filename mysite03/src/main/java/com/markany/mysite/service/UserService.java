package com.markany.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markany.mysite.repository.UserRepository;
import com.markany.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}
	public UserVo getUser(UserVo vo) {
		userRepository.findByEmail(vo.getEmail());
		return userRepository.findByEmailAndPassword(vo);
	}
	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}
	public boolean updateUser(UserVo vo) {
		int count = userRepository.update(vo);
		return count == 1;
	}
}

