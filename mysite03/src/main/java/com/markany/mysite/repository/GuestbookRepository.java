package com.markany.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.markany.mysite.exception.GuestbookRepositoryException;
import com.markany.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

	public List<GuestbookVo> findAll(Long startNo) {
		return sqlSession.selectList("guestbook.findAllByNo", startNo);
	}	

	public int insert(GuestbookVo vo){
		return sqlSession.insert("guestbook.insert", vo);
	}
	
	public int delete(GuestbookVo vo) throws GuestbookRepositoryException{
		return sqlSession.delete("guestbook.delete", vo);
	}

}
