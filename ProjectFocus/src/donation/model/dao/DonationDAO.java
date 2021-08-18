package donation.model.dao;

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
import donation.model.vo.DPayment;
import donation.model.vo.DReply;
import donation.model.vo.Donation;
import goods.model.dao.GoodsDAO;

public class DonationDAO {
	
private Properties prop = new Properties();
	
	public DonationDAO() {
		String fileName = GoodsDAO.class.getResource("/sql/donation/donation-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	

	public int insertDonationB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertDonationB");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getbType());
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setInt(4, b.getmNo());
			pstmt.setString(5, b.getbWriter());
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
	
	

	public int insertDonationD(Connection conn, Donation d, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertDonationD");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, d.getbType());
			pstmt.setInt(3, d.getdCategory());
			pstmt.setInt(4, d.getdPay());
			pstmt.setDate(5, d.getdPeriod());
			pstmt.setString(6, d.getdEtc1());
			pstmt.setString(7, d.getdEtc2());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertDonationF(Connection conn, ArrayList<Image> fileList, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertDonationF");
		try {
			for(int i = 0; i < fileList.size(); i++) {
				Image a = fileList.get(i);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, a.getiOrigin());
			pstmt.setString(2, a.getiChange());
			pstmt.setString(3, a.getiPath());
			pstmt.setInt(4, a.getiLevel());
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


	public ArrayList<Board> selectAllBoard(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectAll");
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
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
				b.setmNo(rset.getInt("m_no"));
				b.setbLike(rset.getInt("b_like"));
				b.setpBtotalpay(rset.getInt("p_btotalpay"));
				
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


	public ArrayList<Donation> selectAllDonation(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Donation> dList = new ArrayList<Donation>();
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1; 
		
		String query = prop.getProperty("selectAll");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
				d.setbNo(rset.getInt("b_no"));
				d.setbType(rset.getInt("b_type"));
				d.setdCategory(rset.getInt("d_category"));
				d.setdPay(rset.getInt("d_pay"));
				d.setdPeriod(rset.getDate("d_period"));
				d.setdEtc1(rset.getString("d_etc1"));
				d.setdEtc2(rset.getString("d_etc2"));
				d.setmNick(rset.getString("m_nick"));
				
				dList.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dList;
	}


	public ArrayList<Image> selectAllImage(Connection conn, PageInfo pi) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Image> IList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectImage");
		
//		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
//		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			stmt = conn.createStatement();
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, endRow);
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
				
				IList.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return IList;
	}


	public Board selectDetailBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;;
		
		String query = prop.getProperty("selectDetailAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setbType(rset.getInt("b_type"));
				b.setbTitle(rset.getString("b_title"));
				b.setbContent(rset.getString("b_content"));
				b.setbDate(rset.getDate("b_date"));
				b.setbCount(rset.getInt("b_count"));
				b.setbStatus(rset.getString("b_status"));
				b.setmNo(rset.getInt("m_no"));
				b.setpBtotalpay(rset.getInt("P_BTOTALPAY"));
				b.setbWriter(rset.getString("b_writer"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}


	public Donation selectDetailDonation(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Donation d = null;
		
		String query = prop.getProperty("selectDetailAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				d = new Donation();
				d.setbNo(rset.getInt("b_no"));
				d.setbType(rset.getInt("b_type"));
				d.setdCategory(rset.getInt("d_category"));
				d.setdPay(rset.getInt("d_pay"));
				d.setdPeriod(rset.getDate("d_period"));
				d.setdEtc1(rset.getString("d_etc1"));
				d.setdEtc2(rset.getString("d_etc2"));
				d.setmNick(rset.getString("m_nick"));
				d.setdCatename(rset.getString("d_catename"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}


	public ArrayList<Image> selectDetailImage(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> IList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectDetailImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
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
				
				IList.add(i);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IList;
	}


	public int insertReply(Connection conn, DReply r) {
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


	public ArrayList<DReply> selectReplyList(Connection conn, int getbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DReply> rList = new ArrayList<DReply>();
		
		String query = prop.getProperty("selectReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, getbNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rList.add(new DReply(rset.getInt("R_NO"),
									rset.getInt("B_NO"),
									rset.getInt("M_NO"),
									rset.getDate("R_DATE"),
									rset.getDate("R_MODIFY"),
									rset.getString("R_CONTENT"),
									rset.getString("R_STATUS"),
									rset.getString("M_NICK")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}


	public int deleteBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
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


	public int updateDonationB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateDonationB");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setInt(3, b.getbNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int updateDonationD(Connection conn, Donation d) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateDonationD");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, d.getdCategory());
			pstmt.setInt(2, d.getdPay());
			pstmt.setDate(3, d.getdPeriod());
			pstmt.setString(4, d.getdEtc1());
			pstmt.setString(5, d.getdEtc2());
			pstmt.setInt(6, d.getbNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateDonationF(Connection conn, ArrayList<Image> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		if(fileList == null) {
			return 0;
		}
		
		String query = prop.getProperty("updateDonationF");
	
		try {
			for(int i = 0; i < fileList.size(); i++) {
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




	public ArrayList<Board> selectCatenameB(Connection conn, String cn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectCatename");
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, cn);
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
				b.setmNo(rset.getInt("m_no"));
				b.setbLike(rset.getInt("b_like"));
				b.setpBtotalpay(rset.getInt("P_BTOTALPAY"));
				
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




	public ArrayList<Donation> selectCatenameD(Connection conn, String cn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Donation> dList = new ArrayList<Donation>();
		
		String query = prop.getProperty("selectCatename");
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, cn);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
				d.setbNo(rset.getInt("b_no"));
				d.setbType(rset.getInt("b_type"));
				d.setdCategory(rset.getInt("d_category"));
				d.setdPay(rset.getInt("d_pay"));
				d.setdPeriod(rset.getDate("d_period"));
				d.setdEtc1(rset.getString("d_etc1"));
				d.setdEtc2(rset.getString("d_etc2"));
				d.setmNick(rset.getString("m_nick"));
				
				dList.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dList;
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




	public int insertDonationPay(Connection conn, int mNo, int bNo, String title, String dPay) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertDonationPay");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, mNo);
			pstmt.setString(3, title);
			pstmt.setString(4, dPay);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}




	public int updatePBtotalPay(Connection conn, int bNo, String dPay) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePBtotalPay");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dPay);
			pstmt.setInt(2, bNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}




	public int updatePMtotalPay(Connection conn, int mNo, String dPay) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePMtotalPay");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dPay);
			pstmt.setInt(2, mNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}




	public ArrayList<DPayment> selectPList(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DPayment> pList = null;
		
		String query = prop.getProperty("selectPList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			pList = new ArrayList<DPayment>();
			while(rset.next()) {
				DPayment d = new DPayment();
				d.setpNo(rset.getInt("p_no"));
				d.setbNo(rset.getInt("b_no"));
				d.setmNo(rset.getInt("m_no"));
				d.setpDate(rset.getDate("p_date"));
				d.setpTitle(rset.getString("p_title"));
				d.setpPayKakao(rset.getString("p_paykakao"));
				d.setmNick(rset.getString("m_nick"));
				d.setpMtotalpay(rset.getInt("p_mtotalpay"));
				d.setbWriter(rset.getString("b_writer"));
				d.setdCatename(rset.getString("d_catename"));
				
				pList.add(d);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}




	public ArrayList<Board> selectSearchB(Connection conn, int sc, String scString, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		String queryReal = null;
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		
		switch(sc) {
		case 1: queryReal ="selectSarchTitle"; break;
		case 2: queryReal ="selectSarchWriter"; break;
		case 3: queryReal ="selectSarchContetn"; break;
		}
		String query = prop.getProperty(queryReal);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, "%"+scString+"%");
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
				b.setmNo(rset.getInt("m_no"));
				b.setbLike(rset.getInt("b_like"));
				b.setpBtotalpay(rset.getInt("p_btotalpay"));
				
				bList.add(b);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(bList);
		return bList;
	}




	public ArrayList<Donation> selectSearchD(Connection conn, int sc, String scString, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Donation> dList = new ArrayList<Donation>();
		
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		String queryReal = null;
		
		switch(sc) {
		case 1: queryReal ="selectSarchTitle"; break;
		case 2: queryReal ="selectSarchWriter"; break;
		case 3: queryReal ="selectSarchContetn"; break;
		}
		String query = prop.getProperty(queryReal);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, "%"+scString+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
				d.setbNo(rset.getInt("b_no"));
				d.setbType(rset.getInt("b_type"));
				d.setdCategory(rset.getInt("d_category"));
				d.setdPay(rset.getInt("d_pay"));
				d.setdPeriod(rset.getDate("d_period"));
				d.setdEtc1(rset.getString("d_etc1"));
				d.setdEtc2(rset.getString("d_etc2"));
				d.setmNick(rset.getString("m_nick"));
				
				dList.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dList;
	}

}
