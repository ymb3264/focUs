package goods.model.dao;

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
import goods.model.vo.Goods;
import goods.model.vo.GoodsBag;
import goods.model.vo.Reply;
import like.model.vo.Likey;

public class GoodsDAO {
	
	private Properties prop = new Properties();
	
	public GoodsDAO() {
		String fileName = GoodsDAO.class.getResource("/sql/goods/goods-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertGoodsB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsB");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getbType());
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setInt(4, b.getmNo());
			
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
	
	public int insertGoodsG(Connection conn, Goods g, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsG");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, g.getbType());
			pstmt.setInt(3, g.getgPrice());
			pstmt.setInt(4, g.getgAmount());
			pstmt.setString(5, g.getgCompany());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertGoodsF(Connection conn, ArrayList<Image> fileList, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsF");
		
		try {
			for (int i = 0; i < fileList.size(); i++) {
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

	public ArrayList selectBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectBList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getInt("b_type"),
									rset.getString("b_title"), 
									rset.getString("b_content"), 
									rset.getDate("b_date"), 
									rset.getDate("b_modify"), 
									rset.getInt("b_count"), 
									rset.getString("b_status"), 
									rset.getInt("m_no"),
									rset.getString("b_etc"),
									rset.getInt("b_like"));
				
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

	public ArrayList selectGList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Goods> gList = new ArrayList<Goods>();
		
		String query = prop.getProperty("selectBList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Goods g = new Goods(rset.getInt("b_no"), 
									rset.getInt("b_type"), 
									rset.getInt("g_no"),
									rset.getInt("g_price"),
									rset.getInt("g_amount"),
									rset.getString("g_company"));
				
				gList.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return gList;
	}

	public ArrayList selectIList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectIList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Image i = new Image();
				i.setbNo(rset.getInt("b_no"));
				i.setiNo(rset.getInt("i_no"));
				i.setiChange(rset.getString("i_change"));
				
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

	public int updateCount(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
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

	public Board selectBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("b_no"),
						rset.getInt("b_type"),
						rset.getString("b_title"), 
						rset.getString("b_content"), 
						rset.getDate("b_date"), 
						rset.getDate("b_modify"), 
						rset.getInt("b_count"), 
						rset.getString("b_status"), 
						rset.getInt("m_no"), 
						rset.getString("b_etc"),
						rset.getInt("b_like"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public Goods selectGoods(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Goods g = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				g = new Goods(rset.getInt("b_no"), 
						rset.getInt("b_type"), 
						rset.getInt("g_no"),
						rset.getInt("g_price"),
						rset.getInt("g_amount"),
						rset.getString("g_company"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return g;
	}

	public ArrayList<Image> selectImage(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Image i = new Image(rset.getInt("i_no"),
									rset.getString("i_origin"),
									rset.getString("i_change"), 
									rset.getString("i_path"), 
									rset.getDate("i_date"),
									rset.getInt("i_level"),
									rset.getString("i_status"),
									rset.getInt("b_no"));
				
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

	public int updateGoodsB(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateGoodsB");
		
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

	public int updateGoodsG(Connection conn, Goods g) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateGoodsG");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, g.getgPrice());
			pstmt.setInt(2, g.getgAmount());
			pstmt.setString(3, g.getgCompany());
			pstmt.setInt(4, g.getbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateGoodsF(Connection conn, ArrayList<Image> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		if(fileList == null) {
			return 0;
		}
		
		String query = prop.getProperty("updateGoodsF");
		
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

	public int deleteGoods(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteGoods");
		
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

	public ArrayList<Board> selectMoreBList(Connection conn, int startNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = new ArrayList<Board>();
		
		String query = prop.getProperty("selectMoreBList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, startNum);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getInt("b_type"),
									rset.getString("b_title"), 
									rset.getString("b_content"), 
									rset.getDate("b_date"), 
									rset.getDate("b_modify"), 
									rset.getInt("b_count"), 
									rset.getString("b_status"), 
									rset.getInt("m_no"),
									rset.getString("b_etc"),
									rset.getInt("b_like"));
				
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

	public ArrayList<Goods> selectMoreGList(Connection conn, int startNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Goods> gList = new ArrayList<Goods>();
		
		String query = prop.getProperty("selectMoreBList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, startNum);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Goods g = new Goods(rset.getInt("b_no"), 
									rset.getInt("b_type"), 
									rset.getInt("g_no"),
									rset.getInt("g_price"),
									rset.getInt("g_amount"),
									rset.getString("g_company"));
				
				gList.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return gList;
	}

	public int insertGoodsPay(Connection conn, ArrayList<GoodsBag> bagList, int mNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsPay");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			for (int i = 0; i < bagList.size(); i++) {
				GoodsBag bag = bagList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setLong(1, bag.getBuyNum());
				pstmt.setInt(2, mNo);
				pstmt.setString(3, bag.getTitle());
				pstmt.setInt(4, bag.getAmount());
				pstmt.setInt(5, bag.getPrice());
				pstmt.setString(6, bag.getThumbnailImg());
					
				result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<GoodsBag> selectGoodsPay(Connection conn, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<GoodsBag> bagList = new ArrayList<GoodsBag>();
		
		String query = prop.getProperty("selectGoodsPay");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				GoodsBag bag = new GoodsBag(rset.getString("gp_title"),
											rset.getString("gp_thumbnailimg"), 
										    rset.getInt("gp_amount"), 
										    rset.getInt("gp_price"), 
										    rset.getLong("gp_no"),
										    rset.getDate("gp_date"));
				bagList.add(bag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bagList;
	}

	public int insertGoodsReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getbNo());
			pstmt.setInt(2, r.getmNo());
			pstmt.setString(3, r.getContent());
			pstmt.setString(4, r.getmNick());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertGoodsReplyImg(Connection conn, ArrayList<Image> fileList, int getbNo, int rNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsReplyImg");
		
		try {
			Image a = fileList.get(0);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, a.getiOrigin());
			pstmt.setString(2, a.getiChange());
			pstmt.setString(3, a.getiPath());
			pstmt.setInt(4, getbNo);
			pstmt.setInt(5, rNo);

			result += pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Reply> selectGoodsReply(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> rList = new ArrayList<Reply>();
		
		String query = prop.getProperty("selectGoodsReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply bag = new Reply(rset.getInt("r_no"),
									  rset.getInt("b_No"), 
									  rset.getString("r_content"),
									  rset.getInt("m_No"),
									  rset.getDate("r_date"),
									  rset.getString("m_nick"));
				
				rList.add(bag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public ArrayList<Image> selectGoodsReplyImg(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> iList = new ArrayList<Image>();
		
		String query = prop.getProperty("selectGoodsReplyImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Image i = new Image(rset.getInt("ri_no"),
									rset.getString("ri_origin"),
									rset.getString("ri_change"), 
									rset.getString("ri_path"), 
									rset.getDate("ri_date"),
									rset.getString("i_status"),
									rset.getInt("b_no"),
									rset.getInt("r_no"));
				
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

	public int selectRNo(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int rNo = 0;
		
		String query = prop.getProperty("selectRNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				rNo = rset.getInt("r_no");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rNo;
	}

	public int insertGoodsFTwo(Connection conn, ArrayList<Image> fileList2, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGoodsFTwo");
		
		try {
			for (int i = 0; i < fileList2.size(); i++) {
				Image a = fileList2.get(i);
				
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

	

	

}




















