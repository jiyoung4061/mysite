package com.markany.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.markany.mysite.vo.BoardVo;

public class BoardRepository {

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "  select a.no, a.title, date_format(a.reg_date, \"%Y/%m/%d\"), a.hit, b.name, b.no, a.depth "
					+ " from board a, user b " + " where a.user_no = b.no "
					+ " order by a.group_no desc, a.order_no asc ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String regDate = rs.getString(3);
				Long hit = rs.getLong(4);
				String userName = rs.getString(5);
				Long userNo = rs.getLong(6);
				int depth = rs.getInt(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setUserName(userName);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setDepth(depth);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3-1. SQL 준비
			
			String sql = " insert " + " into board " + " values (null, ?, ?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setLong(4, vo.getGroupNo());
			pstmt.setLong(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());

			// 5. sql문 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
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
		return result;
	}

	public BoardVo findByNo(Long boardNo) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " select title, contents, no, user_no, hit, group_no, depth, order_no" + " from board b" + " where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, boardNo);

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no = rs.getLong(3);
				Long userNo = rs.getLong(4);
				Long hit = rs.getLong(5);
				Long groupNo = rs.getLong(6);
				int depth = rs.getInt(7);
				int orderNo = rs.getInt(8);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setDepth(depth);
				vo.setOrderNo(orderNo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
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
		return vo;
	}

	public boolean updateByVo(BoardVo vo, String type) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			if ("modify".equals(type)) {
				String sql = "update board set title=?, contents=? where no=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getNo());
			} else if("hit".equals(type)) {
				String sql = "update board set hit=? where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, vo.getHit()+1);
				pstmt.setLong(2, vo.getNo());
			}

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		return result;
	}

	public boolean delete(String no, String password, Long userNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "	delete from board" + "	where no =? and user_no in (" + "	select no	"
					+ "	from user	" + "	where password = ? and no =?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, no);
			pstmt.setString(2, password);
			pstmt.setLong(3, userNo);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
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
		return result;
	}

	public Long findMaxGroupNo() {
		Long maxGroupNo = 0L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 3-1. SQL 준비
			String sql = "select max(group_no) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxGroupNo = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
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
		return maxGroupNo;
	}
	
	public boolean setForOrderNo(BoardVo vo) {
		boolean result = false;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = getConnection();
			
			String sql = "	update board"
						+"	set order_no = order_no+1"
						+"	where group_no = ? and order_no >= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getGroupNo());
			pstmt.setLong(2, vo.getOrderNo());
			int count = pstmt.executeUpdate();
			
			result = count >= 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException {
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
