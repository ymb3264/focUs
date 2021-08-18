package service.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import board.model.vo.Image;
import common.PageInfo;
import member.model.vo.Member;
import service.model.dao.ServiceDAO;
import service.model.vo.Reply;
import service.model.vo.Reserve;
import service.model.vo.Service;




public class ServiceService {


	public int insertService(Board b, Service s, Reserve r, ArrayList<Image> fileList) {
		Connection conn = getConnection();
		
		int result1 = new ServiceDAO().insertServiceB(conn, b);
		int bNo = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
			if(result1 == 1) {
				bNo = new ServiceDAO().selectBNo(conn);
				result2 = new ServiceDAO().insertServiceS(conn, s, bNo);
				result3 = new ServiceDAO().insertReserve(conn, r, bNo);
				if(result2 == 1 && result3 == 1) {
					result3 = new ServiceDAO().insertServiceF(conn, fileList, bNo);
				}
			}
		
		int result = result1 + result2 + result3;
		
		
		if(result > 2) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public ArrayList selectTList(int i) {
		
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		ServiceDAO dao = new ServiceDAO();
		if(i == 1) {
			list = dao.selectBList(conn );
		} else if (i == 2){
			list = dao.selectIList(conn);
		} else if(i == 3){
			list = dao.selectSList(conn);
		} else {
			list = dao.selectLList(conn);
		}
		
		// 넘겨야 할거대로, 3개면 3개, <>를 정할수없는게 넘겨야할게 많아서 사용불가능.
		// 배열은 종류상관없이 넘길수있다.
		close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectAllBoard() {
		Connection conn = getConnection();
		
		ArrayList<Board>bList = new ServiceDAO().selectAllBoard(conn);
		
		close(conn);
		return bList;
	}
	
	public ArrayList<Service> selectAllService() {
		Connection conn = getConnection();
		
		ArrayList<Service>sList = new ServiceDAO().selectAllService(conn);
		
		close(conn);
		return sList;
	}
	
	
	
	public ArrayList<Image> selectAllImage() {
		Connection conn = getConnection();
		
		ArrayList<Image>iList = new ServiceDAO().selectAllImage(conn);
		
		close(conn);
		return iList;
	}
	
	
	public Board selectDetailBoard(int bNo) {
		Connection conn = getConnection();
		
		Board b = new ServiceDAO().selectDetailBoard(conn, bNo);
		
		close(conn);
		return b;
	}
	
	public Service selectDetailService(int bNo) {
		Connection conn = getConnection();
		
		Service s = new ServiceDAO().selectDetailService(conn, bNo);
		
		close(conn);
		return s;
	}


	public ArrayList<Image> selectDetailImage(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Image> iList = new ServiceDAO().selectDetailImage(conn, bNo);
		
		close(conn);
		return iList;
	}

	public int updateService(Board b, Service s, ArrayList<Image> fileList) {
		Connection conn = getConnection();
		
		int result1 = new ServiceDAO().updateServiceB(conn, b);	
		int result2 = new ServiceDAO().updateServiceS(conn, s);
		int result3 = new ServiceDAO().updateServiceF(conn, fileList);				
		int result = result1+result2+result3;
	
		if(result >= 2) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteService(int bNo) {
		Connection conn = getConnection();
		
		int result = new ServiceDAO().deleteService(conn, bNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		return result;
	}

	public ArrayList<Board> selectCatenameB(String tagName) {
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new ServiceDAO().selectCatenameB(conn, tagName);
		
		close(conn);
		
		return bList;
	}

	public ArrayList<Image> selectCatenameI(int i) {
		Connection conn = getConnection();
		
		ArrayList<Image> iList = new ServiceDAO().selectIList(conn);
		
		close(conn);
		
		
		return iList;
	}

	public ArrayList<Service> selectCatenameS(String tagName) {
		Connection conn = getConnection();
		
		ArrayList<Service> sList = new ServiceDAO().selectCatenameS(conn, tagName);
		
		close(conn);
		
		
		return sList;
	}
	
	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		ServiceDAO dao = new ServiceDAO();
		
		int result = dao.insertReply(conn, r);
		
		ArrayList<Reply> rList = null;
		if(result > 0) {
			commit(conn);
			rList = dao.selectReplyList(conn, r.getbNo());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return rList;
	}

	public ArrayList<Reply> selectReplyList(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Reply> rList = new ServiceDAO().selectReplyList(conn, bNo);
		
		close(conn);
		
		return rList;
	}

	public int lastBoard() {
		Connection conn = getConnection();
		
		int No = new ServiceDAO().lastBoard(conn);
		
		close(conn);
		
		return No;
	}
	
	public int insertReservePeople(Member m, int bNo, String catename) {
		Connection conn = getConnection();
		
		int result2 = new ServiceDAO().insertReservePeople(conn, m, bNo, catename);
		
		if(result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result2;
	}

	public ArrayList<Reserve> SelectReserveList(int userNo) {
		Connection conn = getConnection();
		
		ArrayList<Reserve> list = new ServiceDAO().selectReserveList(conn, userNo);
		
		return list;
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new ServiceDAO().getListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new ServiceDAO().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public Service selectServiceDetail(int mNo) {
		Connection conn = getConnection();
		
		Service s = new ServiceDAO().selectServiceDetail(conn, mNo);
		
		close(conn);
		
		return s;
	}

	
	public Reserve selectReserveDetail(int mNo) {
		Connection conn = getConnection();
		
		Reserve r = new ServiceDAO().selectReserveDetail(conn, mNo);
		 
		close(conn);
		return r; 
		
	}

	public ArrayList<Board> selectBList(double x, double y) {
		Connection conn = getConnection();
		ArrayList<Board> result1 = new ServiceDAO().selectBList(conn, x, y);
		
		close(conn);
		return result1;
	}
	
	public ArrayList<Reserve> selectRList(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Reserve> rList = new ServiceDAO().selectRList(conn, bNo);
		
		close(conn);
		return rList;
	}

	public int updateSpButtonYes(int rNo) {
		Connection conn = getConnection();
		
		int result = new ServiceDAO().updateSpButtonYes(conn, rNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public Reserve selectSpButtonYes(int rNo) {
		Connection conn = getConnection();
		
		Reserve r = new ServiceDAO().selectSpButtonYes(conn, rNo);
		 
		close(conn);
		return r; 
	}

	
}
