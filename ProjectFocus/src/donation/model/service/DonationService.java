package donation.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.Image;
import common.PageInfo;
import donation.model.dao.DonationDAO;
import donation.model.vo.DPayment;
import donation.model.vo.DReply;
import donation.model.vo.Donation;

public class DonationService {

	public int insertDonation(Board b, Donation d, ArrayList<Image> fileList) {
		Connection conn = getConnection();
		
		int result1 = new DonationDAO().insertDonationB(conn, b);
		int bNo = 0;
		int result2 = 0;
		int result3 = 0;
		if(result1 == 1) {
			bNo = new DonationDAO().selectBNo(conn);
			result2 = new DonationDAO().insertDonationD(conn, d, bNo);
			if (result2 == 1) {
				result3 = new DonationDAO().insertDonationF(conn, fileList, bNo);
			}
		}
		int result = result1 +  result2 + result3;
		
		
		if(result > 2) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Board> selectAllBoard(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board>bList = new DonationDAO().selectAllBoard(conn, pi);
		
		close(conn);
		return bList;
	}

	public ArrayList<Donation> selectAllDonation(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Donation>dList = new DonationDAO().selectAllDonation(conn, pi);
		
		close(conn);
		return dList;
	}

	public ArrayList<Image> selectAllImage(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Image>IList = new DonationDAO().selectAllImage(conn, pi);
		
		close(conn);
		return IList;
	}

	public Board selectDetailBoard(int bNo) {
		Connection conn = getConnection();
		
		Board b = new DonationDAO().selectDetailBoard(conn, bNo);
		
		close(conn);
		return b;
	}

	public Donation selectDetailDonation(int bNo) {
		Connection conn = getConnection();
		
		Donation d = new DonationDAO().selectDetailDonation(conn, bNo);
		
		close(conn);
		return d;
	}

	public ArrayList<Image> selectDetailImage(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Image>IList = new DonationDAO().selectDetailImage(conn, bNo);
		
		close(conn);
		return IList;
	}

	public ArrayList<DReply> selectReplyList(int bNo) {
		Connection conn = getConnection();
		ArrayList<DReply> rList = new DonationDAO().selectReplyList(conn, bNo);
		close(conn);
		return rList;
	}
	
public ArrayList<DReply> insertReply(DReply r) {
		Connection conn = getConnection();
		
		DonationDAO dao = new DonationDAO();
		
		int result = dao.insertReply(conn, r);
		
		ArrayList<DReply> rList = null;
		if(result>0) {
			commit(conn);
			rList = dao.selectReplyList(conn, r.getbNo());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return rList;
	}

public int deleteBoard(int bNo) {
	Connection conn = getConnection();
	
	int result = new DonationDAO().deleteBoard(conn, bNo);
	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	return result;
}

public int updateDonation(Board b, Donation d, ArrayList<Image> fileList) {
	Connection conn = getConnection();
	
	int result1 = new DonationDAO().updateDonationB(conn, b);
	int result2 = new DonationDAO().updateDonationD(conn, d);
	int result3 = new DonationDAO().updateDonationF(conn, fileList);
	int result = result1 +  result2 + result3;
	
	
	if(result >= 2) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public ArrayList<Board> selectCatenameB(String cn, PageInfo pi) {
	Connection conn = getConnection();
	
	ArrayList<Board> bList = null;
	
	DonationDAO dd = new DonationDAO();
	if(cn.equals("전체")) {
		bList = dd.selectAllBoard(conn, pi);
	} else {
		bList = dd.selectCatenameB(conn, cn, pi);
	}
	
	close(conn);
	return bList;
}

public ArrayList<Donation> selectCatenameD(String cn, PageInfo pi) {
	Connection conn = getConnection();
	
	ArrayList<Donation> dList = null;
	
	DonationDAO dd = new DonationDAO();
	if(cn.equals("전체")) {
		dList = dd.selectAllDonation(conn, pi);
	} else {
		dList = dd.selectCatenameD(conn, cn, pi);
	}
	
	close(conn);
	return dList;
}

public int getListCount() {
	Connection conn = getConnection();
	
	int listCount = new DonationDAO().getListCount(conn);
	
	close(conn);
	
	return listCount;
}

public int insertDonationPay(int mNo, int bNo, String title, String dPay) {
	Connection conn = getConnection();
	
	int result1 = new DonationDAO().insertDonationPay(conn, mNo, bNo, title, dPay);
	int result2 = 0;
	int result3 = 0;
	
	if(result1 > 0) {
		result2 = new DonationDAO().updatePBtotalPay(conn, bNo, dPay);
		result3 = new DonationDAO().updatePMtotalPay(conn, mNo, dPay);
	}
	
	int result = result1 + result2 + result3;
	
	if(result == 3) {
		commit(conn);
	} else {
		rollback(conn);
	}
	return result;
}

public ArrayList<DPayment> selectPList(int mNo) {
	Connection conn = getConnection();
	
	ArrayList<DPayment> pList = new DonationDAO().selectPList(conn, mNo);
	
	close(conn);
	return pList;
	
}

public ArrayList<Board> selectSearchB(int sc, String scString, PageInfo pi) {
	Connection conn = getConnection();
	
	ArrayList<Board> bList = new DonationDAO().selectSearchB(conn, sc, scString, pi);
	close(conn);
	return bList;
}

public ArrayList<Donation> selectSearchD(int sc, String scString, PageInfo pi) {
	Connection conn = getConnection();
	
	ArrayList<Donation> dList = new DonationDAO().selectSearchD(conn, sc, scString, pi);
	
	close(conn);
	return dList;
}




}
