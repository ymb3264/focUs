package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;

import member.model.vo.Member;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmPw());
			pstmt.setString(3, m.getmName());
			pstmt.setString(4, m.getmNick());
			pstmt.setString(5, m.getmPhone());
			pstmt.setString(6, m.getmEmail());
			pstmt.setString(7, m.getmAddress());
			pstmt.setString(8, m.getmPost());
			pstmt.setString(9, m.getmAddress2());
			pstmt.setDouble(10, m.getxAddress());
			pstmt.setDouble(11, m.getyAddress());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member(rset.getInt("m_no"),
									   rset.getString("m_id"),
									   rset.getString("m_pw"),
									   rset.getString("m_name"),
									   rset.getString("m_nick"),
									   rset.getString("m_phone"),
									   rset.getString("m_email"),
									   rset.getString("m_address"),
									   rset.getString("m_status"),
									   rset.getString("m_admin"),
									   rset.getString("m_post"),
									   rset.getString("m_address2"),
									   rset.getInt("p_mtotalpay")
									   );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getmName());
			pstmt.setString(2, m.getmNick());
			pstmt.setString(3, m.getmPhone());
			pstmt.setString(4, m.getmEmail());
			pstmt.setString(5, m.getmAddress());
			pstmt.setString(6, m.getmId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String loginUser) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginUser);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkId(Connection conn, String inputId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputId);
			
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

}
























