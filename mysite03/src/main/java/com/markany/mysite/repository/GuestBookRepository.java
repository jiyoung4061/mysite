package com.markany.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.markany.mysite.exception.GuestbookRepositoryException;
import com.markany.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

	public int insert(GuestBookVo vo){
		return sqlSession.insert("guestbook.insert", vo);
	}
	
	public int delete(GuestBookVo vo) throws GuestbookRepositoryException{
		return sqlSession.delete("guestbook.delete", vo);
	}	
}
