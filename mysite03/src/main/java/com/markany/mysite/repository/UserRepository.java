package com.markany.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.markany.mysite.exception.UserRepositoryException;
import com.markany.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	public UserVo findByNo(Long userNo) throws UserRepositoryException{
		UserVo userVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();

			// 3. SQL 준비
			String sql = " select no, name, email, gender" + " from user" + " where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, userNo);

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new UserRepositoryException();
		} 
		return userVo;
	}

	public UserVo findByEmailAndPassword(UserVo vo) throws UserRepositoryException {
		UserVo userVo = null;

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection(); // sqlexception 여기서 처리
			// 3-1. SQL 준비
			String sql = " select no, name " + " from user " + " where email=?" + " and password = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			if (rs.next()) { // 한개 나오니까 if!
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			throw new UserRepositoryException();
		} 
		return userVo;
	}

	public int insert(UserVo userVo) throws UserRepositoryException {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection(); // sqlexception 여기서 처리
			// 3-1. SQL 준비
			String sql = "insert" + " into user" + " values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			// 5. sql문 실행
			count = pstmt.executeUpdate();
			
			// 6. 자원 정리
			pstmt.close();
			conn.close();
		} catch (SQLException ex) {
			throw new UserRepositoryException();
		}
		return count;
	}

	public int update(UserVo vo) throws UserRepositoryException {
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			if (vo.getPassword() == null || "".equals(vo.getPassword())) {
				String sql = " update user set name=?, gender=? where no=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());

			} else {
				String sql = " update user set name=?, password=? , gender=? where no=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
			}

			count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new UserRepositoryException();
		} 
		return count;
	}
}
