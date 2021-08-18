package service.model.dao;

import static common.JDBCTemplate.close;

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

import board.model.vo.Board;
import board.model.vo.Image;
import common.PageInfo;
import like.model.vo.Likey;
import member.model.vo.Member;
import service.model.vo.Reply;
import service.model.vo.Reserve;
import service.model.vo.Service;

public class ServiceDAO {
	
	private Properties prop = new Properties();
	
	public ServiceDAO() {
		String fileName = ServiceDAO.class.getResource("/sql/service/service-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

//	public int insertService(Connection conn, Board b, Service s, ArrayList<Image> fileList) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		String query = prop.getProperty("insert");
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}


	public int insertServiceB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertServiceBoard");
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
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectBNo(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int bNo = 0;
		
		String query = prop.getProperty("selectBNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bNo = rset.getInt("b_no");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return bNo;
	}



	public int insertServiceS(Connection conn, Service s, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertServiceS");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, s.getbType());
			pstmt.setInt(3, s.getsCategory());
			pstmt.setString(4, s.getsTime());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int insertServiceF(Connection conn, ArrayList<Image> fileList, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		// 0이 되어야 하는건 값을 service에서  비교할때 1보다 크다면을 사용하기 때문에 0을 result에 담아줌
		
		String query = prop.getProperty("insertServiceF");
		
		try {
			for (int i = 0; i< fileList.size(); i++) {
				Image img = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, img.getiOrigin());
				pstmt.setString(2, img.getiChange());
				pstmt.setString(3, img.getiPath());
				pstmt.setInt(4, img.getiLevel());
				pstmt.setInt(5, bNo);
				
				result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList selectBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
	
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
		
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setbLike(rset.getInt("b_like"));
				b.setbETC(rset.getString("b_etc"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbLocation(rset.getString("b_location"));
			
			
			bList.add(b);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(stmt);
	}
	
	return bList;
}
	
	public ArrayList selectIList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Image> ilist = new ArrayList<Image>();
		
		String query = prop.getProperty("selectIList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Image i = new Image();
				i.setbNo(rset.getInt("b_no"));
				i.setiNo(rset.getInt("i_no"));
				i.setiChange(rset.getString("i_change"));
				
				ilist.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return ilist;
	}
	


//	public ArrayList selectSList(Connection conn) {
//		Statement stmt = null;
//		ResultSet rset = null;
//		ArrayList<Service> slist = new ArrayList<Service>();
//		
//		String query = prop.getProperty("selectAll");
//		
//		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
//			
//			while(rset.next()) {
//				slist.add(new Service(rset.getInt("b_no"), rset.getInt("b_type"), rset.getInt("s_category"),
//									 rset.getString("s_location"), rset.getString("s_time"), rset.getString("m_nick"), rset.getString("s_catename")));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(stmt);
//		}
//			
//		System.out.println(slist);
//		return slist;
//	}
	
	public ArrayList selectSList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Service> slist = new ArrayList<Service>();
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Service s = new Service();
						s.setbNo(rset.getInt("b_no"));
						s.setbType(rset.getInt("b_type"));
						s.setsCategory(rset.getInt("s_category"));
						s.setsLocation(rset.getString("b_location"));
						s.setsTime(rset.getString("s_time"));
						s.setsCatename(rset.getString("s_catename"));
				slist.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return slist;
	}
	
	public ArrayList selectLList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Likey> lList = new ArrayList<Likey>();
		
		String query = prop.getProperty("selectLike");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Likey l = new Likey();
				l.setbNo(rset.getInt("b_no"));
				l.setmNo(rset.getInt("m_no"));
				l.setlNo(rset.getInt("l_no"));
				l.setlStatus(rset.getString("l_status"));
				
				lList.add(l);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
	
		return lList;
	
	}


	
	public ArrayList<Board> selectAllBoard(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectAll");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setbETC(rset.getString("b_etc"));
				
				bList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bList;
	}
	
	public ArrayList<Service> selectAllService(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Service> sList = new ArrayList<Service>();
		
		String query = prop.getProperty("selectAll");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Service s = new Service();
				s.setbNo(rset.getInt("b_no"));
				s.setbType(rset.getInt("b_type"));
				s.setsCategory(rset.getInt("s_category"));
				s.setsLocation(rset.getString("s_location"));
				s.setsTime(rset.getString("s_time"));

				
				sList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return sList;
	}
	
	public ArrayList<Image> selectAllImage(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectImage");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Image i = new Image();
				i.setiNo(rset.getInt("I_NO"));
				i.setiOrigin(rset.getString("I_ORIGIN"));
				i.setiChange(rset.getString("I_CHANGE"));
				i.setiPath(rset.getString("I_PATH"));
				i.setiDate(rset.getDate("I_DATE"));
				i.setiLevel(rset.getInt("I_LEVEL"));
				i.setiStatus(rset.getString("I_STATUS"));
				i.setbNo(rset.getInt("B_NO"));
				
				iList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return iList;
	}



	public Board selectDetailBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectDetailAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			b = new Board();
			
			if(rset.next()) {
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setbLike(rset.getInt("b_like"));
				b.setmNo(rset.getInt("m_no"));
				b.setbETC(rset.getString("b_etc"));
				b.setxAddress(rset.getDouble("b_x_location"));
				b.setyAddress(rset.getDouble("b_y_location"));
				b.setmPhone(rset.getString("m_phone"));
				b.setmNick(rset.getString("m_nick"));
				b.setbWriter(rset.getString("b_writer"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	public Service selectDetailService(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Service s = null;
		
		String query = prop.getProperty("selectDetailAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			s = new Service();
			
			if(rset.next()) {
				s.setbNo(rset.getInt("b_no"));
				s.setbType(rset.getInt("b_type"));
				s.setsCategory(rset.getInt("s_category"));
				s.setsLocation(rset.getString("b_location"));
				s.setsTime(rset.getString("s_time"));
				s.setmNick(rset.getString("m_nick"));
				s.setsCatename(rset.getString("s_catename"));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
	}

	
	public ArrayList<Image> selectDetailImage(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = null;
		
		String query = prop.getProperty("selectDetailImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery(); // ResultSet객체의 값을 반환, SELECT 구문 실행때 사용될 함수.
			
			iList = new ArrayList<Image>();
			
			while(rset.next()) {
				Image img = new Image(); 
				img.setiNo(rset.getInt("i_no"));
				img.setiOrigin(rset.getString("i_origin"));
				img.setiChange(rset.getString("i_change"));
				img.setiPath(rset.getString("i_path"));
				img.setiDate(rset.getDate("i_date"));
				img.setiLevel(rset.getInt("i_level"));
				img.setiStatus(rset.getString("i_status"));
				img.setbNo(rset.getInt("b_no"));
				
				iList.add(img);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return iList;
	
	}



	public int updateServiceB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateServiceB");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setString(3, b.getbETC());
			pstmt.setDouble(4, b.getxAddress());
			pstmt.setDouble(5, b.getyAddress());
			pstmt.setInt(6, b.getbNo());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}



	public int updateServiceS(Connection conn, Service s) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateServiceS");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, s.getsCategory());
			pstmt.setString(2, s.getsLocation());
			pstmt.setString(3, s.getsTime());
			pstmt.setInt(4, s.getbNo());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}



	public int updateServiceF(Connection conn, ArrayList<Image> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		if(fileList == null) {
			return 0;
		}
		
		String query = prop.getProperty("updateServiceF");
		
		try {
			for (int i = 0; i < fileList.size(); i++) {
				Image a = fileList.get(i);
				
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
	
	public int deleteService(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteService");
		
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



	public ArrayList<Board> selectCatenameB(Connection conn, String tagName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectTagname");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tagName);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Board b = new Board();
				
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setmNo(rset.getInt("m_no"));
				b.setbLike(rset.getInt("b_like"));
				
				System.out.println(b);
				
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



	public ArrayList<Image> selectCatenameI(Connection conn, String tagName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tagName);
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Image i = new Image();
				i.setiNo(rset.getInt("I_NO")); 
				i.setiOrigin(rset.getString("I_ORIGIN"));
				i.setiChange(rset.getString("I_CHANGE"));
				i.setiPath(rset.getString("I_PATH"));
				i.setiDate(rset.getDate("I_DATE"));
				i.setiLevel(rset.getInt("I_LEVEL"));
				i.setiStatus(rset.getString("I_STATUS"));
				i.setbNo(rset.getInt("B_NO"));
				
				iList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return iList;
	
	}



	public ArrayList<Service> selectCatenameS(Connection conn, String tagName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Service> sList = new ArrayList<Service>();
		
		String query = prop.getProperty("selectDetailAll2");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tagName);
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Service s = new Service();
				s.setbNo(rset.getInt("b_no"));
				s.setbType(rset.getInt("b_type"));
				s.setsCategory(rset.getInt("s_category"));
				s.setsLocation(rset.getString("s_location"));
				s.setsTime(rset.getString("s_time"));
				s.setmNick(rset.getString("m_nick"));
				s.setsCatename(rset.getString("s_catename"));
				
				sList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return sList;
	}



	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getbNo());
			pstmt.setInt(2, r.getmNo());
			pstmt.setString(3, r.getrContent());
			pstmt.setString(4, r.getmNick());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



	public ArrayList<Reply> selectReplyList(Connection conn, int getbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> rList = new ArrayList<Reply>();
		
		String query = prop.getProperty("selectReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, getbNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				rList.add(new Reply(rset.getInt("r_no"),
									rset.getInt("b_no"),
									rset.getInt("m_no"),
									rset.getDate("r_date"),
									rset.getDate("r_modify"),
									rset.getString("r_content"),
									rset.getString("r_status"),
									rset.getString("m_nick")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 close(rset);
			 close(pstmt);
		}
		return rList;
	}



	public int lastBoard(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int No = 0;
		
		String query = prop.getProperty("newInsertBoard");
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if (rset.next()) {
				No = rset.getInt("b_no");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return No;
	}



	public int insertReserve(Connection conn, Reserve r, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReserve");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, r.getrTitle());
			pstmt.setString(3, r.getrWriter());
			pstmt.setString(4, r.getrLocation());
			pstmt.setString(5, r.getrTime());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReservePeople(Connection conn, Member m, int bNo, String catename) {
		PreparedStatement pstmt = null;
		int result2 = 0;
		
		String query = prop.getProperty("insertReservePeople");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, m.getmNo());
			pstmt.setString(3, m.getmName());
			pstmt.setString(4, m.getmPhone());
			pstmt.setString(5, m.getmEmail());
			pstmt.setString(6, catename);
			
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			
		return result2;
	}



	public ArrayList<Reserve> selectReserveList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		
		String query = prop.getProperty("selectReserveList");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Reserve r = new Reserve();
				
				r.setrUserNo(rset.getInt("r_no"));
				r.setrBNo(rset.getInt("b_number"));
				r.setrTitle(rset.getString("b_title"));
				r.setrWriter(rset.getString("b_writer"));
				r.setrLocation(rset.getString("s_location"));
				r.setrTime(rset.getString("s_time"));
				
				list.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}



	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}



	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		String query = prop.getProperty("selectAll");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setbLike(rset.getInt("b_like"));
				b.setbETC(rset.getString("b_etc"));
			
			
			list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
			return list;
	}
	
	public Reserve selectReserveDetail(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reserve r = null;
		
		String query = prop.getProperty("selectReserveDetail");
		
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Reserve();
				r.setrUserNo(rset.getInt("m_no"));
				r.setrBNo(rset.getInt("b_number"));
				r.setrWriter(rset.getString("b_writer"));
				r.setrTitle(rset.getString("b_title"));
				r.setrLocation(rset.getString("s_location"));
				r.setrTime(rset.getString("s_time"));
				r.setrStatus(rset.getString("r_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
	
	public Service selectServiceDetail(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		Service s = null;
		ResultSet rset = null;
		
		
		String qeury = prop.getProperty("selectServiceDetail");
		
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			s = new Service();
			if(rset.next()) {
				s.setbNo(rset.getInt("b_no"));
				s.setbTitle(rset.getString("b_title"));
				s.setsLocation(rset.getString("b_location"));
				s.setsTime(rset.getString("s_time"));
				s.setmNick(rset.getString("b_writer"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
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
							rset.getInt("m_no"),
							rset.getString("b_etc"),
							rset.getInt("b_like"),
							rset.getString("b_location"),
							rset.getString("b_writer"));
				
				b.setxAddress(rset.getDouble("b_x_location"));
				b.setyAddress(rset.getDouble("b_y_location"));
				
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
	
	public ArrayList<Reserve> selectRList(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reserve> rList = new ArrayList<Reserve>();
		
		String query =  prop.getProperty("selectRList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reserve r = new Reserve();
				r.setrBNo(rset.getInt("b_number"));
				r.setrNo(rset.getInt("r_no"));
				r.setrName(rset.getString("m_name"));
				r.setrPhone(rset.getString("m_phone"));
				r.setrEmail(rset.getString("m_email"));
				r.setrStatus(rset.getString("R_status"));
				
				rList.add(r);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public int updateSpButtonYes(Connection conn, int rNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateSpButtonYes");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public Reserve selectSpButtonYes(Connection conn, int rNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reserve r = null;
		
		String query =  prop.getProperty("selectSpButtonYes");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNo);
			rset = pstmt.executeQuery();
			
			r = new Reserve();
			if(rset.next()) {
				r.setrBNo(rset.getInt("b_number"));
				r.setrNo(rset.getInt("r_no"));
				r.setrName(rset.getString("m_name"));
				r.setrPhone(rset.getString("m_phone"));
				r.setrEmail(rset.getString("m_email"));
				r.setrStatus(rset.getString("r_status"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return r;

		
	}

}
