package com.markany.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.markany.mysite.exception.GuestbookRepositoryException;
import com.markany.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	
	public List<GuestBookVo> findAll() throws GuestbookRepositoryException{
		List<GuestBookVo> list = new ArrayList<>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = " select no, name, password, message, reg_date " + " from guestbook " + " order by no desc ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String message = rs.getString(4);
				String reg_date = rs.getString(5);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setReg_date(reg_date);

				list.add(vo);
			}
		} catch (SQLException e) {
			throw new GuestbookRepositoryException();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insert(GuestBookVo vo) throws GuestbookRepositoryException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0; 

		try {
			conn = getConnection(); // sqlexception 여기서 처리
			// 3-1. SQL 준비
			String sql = " insert " + " into guestbook " + " values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			// 5. sql문 실행
			count = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new GuestbookRepositoryException();
		}
		return count;
	}
	
	public int delete(GuestBookVo vo) throws GuestbookRepositoryException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection(); 
			// delete from guestbook where no=10 and password = '1234'
			String sql = " delete " + " from guestbook " + " where no="+vo.getNo()+" and password= '"+vo.getPassword()+"'";
			pstmt = conn.prepareStatement(sql);
			count = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new GuestbookRepositoryException();
		} 
		return count;
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");

		// 2. 연결하기-> driverManager 클래스
		String url = "jdbc:mysql://192.168.90.219:3307/webdb?characterEncoding=utf8";
		conn = DriverManager.getConnection(url, "webdb", "apfhd123"); // webdb, password
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;
	}
}
