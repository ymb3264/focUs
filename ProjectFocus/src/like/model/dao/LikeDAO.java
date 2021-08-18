package like.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import like.model.vo.Likey;

import static common.JDBCTemplate.close;

public class LikeDAO {
	
	private Properties prop = new Properties();
	
	public LikeDAO() {
		String fileName = LikeDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Likey selectLike(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Likey like = null;
		
		String query = prop.getProperty("selectLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			pstmt.setInt(2, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				like = new Likey(rset.getInt("l_no"),
								 rset.getInt("m_no"),
								 rset.getInt("b_no"),
								 rset.getString("l_status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return like;
	}
	
	public int selectLikeExist(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectLikeExist");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			pstmt.setInt(2, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int insertLike(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int plusBoardLike(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("plusBoardLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Likey> selectLikeList(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Likey> lList = new ArrayList<Likey>();
		
		String query = prop.getProperty("selectLikeList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Likey like = new Likey(rset.getInt("l_no"),
									   rset.getInt("m_no"),
									   rset.getInt("b_no"),
									   rset.getString("l_status"));
				lList.add(like);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return lList;
	}

	public int updateMinusLike(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMinusLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updatePlusLike(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePlusLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int minusBoardLike(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("minusBoardLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectTotalLike(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalLike = 0;
		
		String query = prop.getProperty("selectTotalLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalLike = rset.getInt("b_like");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalLike;
	}

	

}













