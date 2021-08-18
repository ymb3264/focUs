package board.model.dao;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

//import com.sun.org.glassfish.external.statistics.annotations.Reset;

import board.model.vo.BReply;
import board.model.vo.Board;
import board.model.vo.Hashtag;
import board.model.vo.Image;

import static common.JDBCTemplate.*;

public class BoardDAO {
	
	private Properties prop = new Properties();

	public BoardDAO(){
	String fileName= BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
	
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	/*
	 * public int insertBoard(Connection conn, Board b, ArrayList<Image> fileList) {
	 * 
	 * PreparedStatement pstmt = null; int result= 0; String query =
	 * prop.getProperty("insertBoard"); try { pstmt = conn.prepareStatement(query);
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return result;
	 * 
	 * }
	 */

	public int insertBoardB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertBoardB");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getbType());
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setInt(4, b.getmNo());
			pstmt.setString(5, b.getbETC());
			pstmt.setString(6, b.getbLocation());
			pstmt.setDouble(7, b.getxAddress());
			pstmt.setDouble(8, b.getyAddress());
			pstmt.setString(9, b.getbWriter());
			pstmt.setString(10, b.getbYoutube());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoardF(Connection conn, ArrayList<Image> fileList, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertBoardF");
		try {
			for(int i = 0; i< fileList.size(); i++) {
				Image a= fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, a.getiOrigin());
				pstmt.setString(2, a.getiChange());
				pstmt.setString(3, a.getiPath());
				pstmt.setInt(4, a.getiLevel());
				pstmt.setInt(5, bNo);
				
				result += pstmt.executeUpdate();
			}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectBNo(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int bNo= 0;
		String query= prop.getProperty("selectBNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bNo= rset.getInt("b_no");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return bNo;
	}

	public ArrayList<Board> selectBList(Connection conn, double x, double y) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b= null;
		ArrayList <Board> bList= new ArrayList<Board>() ;
		
		String query = prop.getProperty("selectBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, x);
			pstmt.setDouble(2, x);
			pstmt.setDouble(3, y);
			pstmt.setDouble(4, y);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				b = new Board(rset.getInt("b_No"),
							rset.getInt("b_Type"),
							rset.getString("b_Title"),
							rset.getString("b_Content"),
							rset.getDate("b_Date"),
							rset.getDate("b_Modify"),
							rset.getInt("b_Count"),
							rset.getString("b_Status"),
							rset.getInt("m_number"),
							rset.getString("b_etc"),
							rset.getInt("b_like"),
							rset.getString("b_location"),
							rset.getString("b_writer"),
							rset.getString("b_youtube"));
				bList.add(b);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}


	
	public ArrayList<Image> selectIList(Connection conn) {
		Statement stmt=null;
		ResultSet rset=null;
		ArrayList<Image> IList = new ArrayList<Image>();
		Image i = null;
		String query=prop.getProperty("selectImageList");
		
		try {
			stmt= conn.createStatement();
			rset =stmt.executeQuery(query);
			
			while(rset.next()){
				i = new Image(rset.getInt("i_No"),
						rset.getString("i_Origin"),
						rset.getString("i_Change"),
						rset.getString("i_Path"),
						rset.getDate("i_Date"),
						rset.getInt("i_Level"),
						rset.getString("i_Status"),
						rset.getInt("b_No"));
				IList.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return IList;
	}

	public Board selectBoardDetail(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query= prop.getProperty("selectBoardDetail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				board = new Board(rset.getInt("b_No"),
						rset.getInt("b_Type"),
						rset.getString("b_Title"),
						rset.getString("b_Content"),
						rset.getDate("b_Date"),
						rset.getDate("b_Modify"),
						rset.getInt("b_Count"),
						rset.getString("b_Status"),
						rset.getInt("m_number"),
						rset.getString("b_etc"),
						rset.getInt("b_like"),
						rset.getString("b_location"),
						rset.getString("b_writer"),
						rset.getString("b_youtube"));
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
		
		
	}

	public ArrayList<Image> selectImageDetail(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> image = new ArrayList<Image>();
		
		String query =prop.getProperty("selectImageDetail");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset= pstmt.executeQuery();
			
			
			while(rset.next()) {
				Image i = new Image(rset.getInt("i_No"),
						rset.getString("i_Origin"),
						rset.getString("i_Change"),
						rset.getString("i_Path"),
						rset.getDate("i_Date"),
						rset.getInt("i_Level"),
						rset.getString("i_Status"),
						rset.getInt("b_No"));
				
				image.add(i);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		return image;
	}

	public int deleteBoard(Connection conn, int bNo) {
		PreparedStatement pstmt =null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int UpdateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setString(3, b.getbETC());
			pstmt.setString(4, b.getbYoutube());
			pstmt.setInt(5, b.getbNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int UpdateBoard(Connection conn, ArrayList<Image> iList) {

		PreparedStatement pstmt = null;
		int result = 0;
		if(iList == null) {
			return 0;
		}
		
		String query = prop.getProperty("updateBoardImage");
		
		try {
			for (int i = 0; i < iList.size(); i++) {
				Image a = iList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, a.getiOrigin());
				pstmt.setString(2, a.getiChange());
				pstmt.setString(3, a.getiPath());
				pstmt.setInt(4, a.getiLevel());
				pstmt.setInt(5, a.getiNo());
					
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertHashtag(Connection conn, String tag, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertHashtag");
		
		try {
			String[] tags = tag.split("#");
			int result1 = 0;
			for(int i = 1; i < tags.length; i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, tags[i]);
				pstmt.setInt(2, bNo);
				result += pstmt.executeUpdate();
			}
			
			if(result > 0 && result < 6) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Hashtag> selectHList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Hashtag> hList = new ArrayList<Hashtag>();
		
		String query = prop.getProperty("selectHList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Hashtag h = new Hashtag(rset.getInt("t_no"), 
										rset.getString("t_name"),
										rset.getInt("b_no"));
				hList.add(h);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return hList;
	}

	public ArrayList<Hashtag> selectTagList(Connection conn, String tag) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Hashtag> tList = new ArrayList<Hashtag>();
		
		String query = prop.getProperty("selectTagList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tag);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Hashtag h = new Hashtag(rset.getInt("t_no"),
										rset.getString("t_name"),
										rset.getInt("b_no"));
				tList.add(h);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return tList;
	}
	
	public int insertReply(Connection conn, BReply r) {
		PreparedStatement pstmt =null;
		int result =0;
		
		
		String query = prop.getProperty("insertReplyD");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getbNo());
			pstmt.setInt(2, r.getmNo());
			pstmt.setString(3, r.getrContent());
			pstmt.setString(4, r.getmNick());
		
			result = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<BReply> selectReplyList(Connection conn, int getbNo) {
		PreparedStatement pstmt =null;
		ResultSet rset =null;
		
		ArrayList<BReply> rList= new ArrayList<BReply>();
		
		String query = prop.getProperty("selectReply");
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, getbNo);
			rset= pstmt.executeQuery();
			
			
			while(rset.next()) {
				rList.add(new BReply(rset.getInt("r_no"),
						rset.getInt("b_no"),
						rset.getInt("m_no"),
						rset.getDate("r_date"),
						rset.getDate("r_modify"),
						rset.getString("r_content"),
						rset.getString("r_status"),
						rset.getString("m_nick")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public ArrayList<Hashtag> selectHList(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Hashtag> hList = new ArrayList<Hashtag>();
		
		String query = prop.getProperty("selectHDetailList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Hashtag h = new Hashtag(rset.getInt("t_no"), 
										rset.getString("t_name"),
										rset.getInt("b_no"));
				hList.add(h);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hList;
	}

	public ArrayList selectMoreBList(Connection conn, int startNum, double x, double y) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		String query = prop.getProperty("selectMoreBList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, x);
			pstmt.setDouble(2, x);
			pstmt.setDouble(3, y);
			pstmt.setDouble(4, y);
			pstmt.setInt(5, startNum);
			pstmt.setInt(6, startNum);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Board b = new Board(rset.getInt("b_No"),
						rset.getInt("b_Type"),
						rset.getString("b_Title"),
						rset.getString("b_Content"),
						rset.getDate("b_Date"),
						rset.getDate("b_Modify"),
						rset.getInt("b_Count"),
						rset.getString("b_Status"),
						rset.getInt("m_number"),
						rset.getString("b_etc"),
						rset.getInt("b_like"),
						rset.getString("b_location"),
						rset.getString("b_writer"),
						rset.getString("b_youtube"));
				

				bList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}

	public Board selectMainBoard(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectMainBoard");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				b = new Board(rset.getInt("b_No"),
						rset.getInt("b_Type"),
						rset.getString("b_Title"),
						rset.getString("b_Content"),
						rset.getDate("b_Date"),
						rset.getDate("b_Modify"),
						rset.getInt("b_Count"),
						rset.getString("b_Status"),
						rset.getInt("m_number"),
						rset.getString("b_etc"),
						rset.getInt("b_like"),
						rset.getString("b_location"),
						rset.getString("b_writer"),
						rset.getString("b_youtube"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return b;
	}

	public ArrayList<Board> selectMyBoard(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectMyBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbDate(rset.getDate("b_date"));
				
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}
}








