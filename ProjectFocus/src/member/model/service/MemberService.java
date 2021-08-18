package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Member selectMember(Member m) {
		Connection conn = getConnection();
		
		Member loginUser = new MemberDAO().selectMember(conn, m);
		
		close(conn);
		
		return loginUser;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteMember(String loginUser) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().deleteMember(conn, loginUser);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int checkId(String inputId) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().checkId(conn, inputId);
		
		close(conn);
		
		return result;
	}

	
}





















