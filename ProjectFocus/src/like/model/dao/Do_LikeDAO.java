package like.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.*;

import goods.model.dao.GoodsDAO;
import like.model.vo.Likey;

public class Do_LikeDAO {
	
	private Properties prop = new Properties();
	
	public Do_LikeDAO() {
		String fileName = GoodsDAO.class.getResource("/sql/like/like-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public int selectTotalLike(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
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
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return totalLike;
	}


	public Likey selectAllLike(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Likey ly = null;

		String query = prop.getProperty("selectAllLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, mNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ly = new Likey();
				ly.setlNo(rset.getInt("L_NO"));
				ly.setmNo(rset.getInt("M_NO"));
				ly.setbNo(rset.getInt("B_NO"));
				ly.setlStatus(rset.getString("L_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ly;
	}

	public int selectLNo(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		Likey like = new Likey();
		
		String query = prop.getProperty("selectLNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, mNo);
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

	public int updateLikeY(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateLikeY");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, mNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePlusTotal(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePlusTotal");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateLikeN(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateLikeN");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, mNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int updateMinusTotal(Connection conn, int bNo, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMinusTotal");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
