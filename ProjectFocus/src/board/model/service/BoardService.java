package board.model.service;

import java.sql.Connection;

import java.util.ArrayList;
import static common.JDBCTemplate.*;

import board.model.dao.BoardDAO;
import board.model.vo.BReply;
import board.model.vo.Board;
import board.model.vo.Hashtag;
import board.model.vo.Image;

public class BoardService {

	public int insertBoard(Board b, ArrayList<Image> fileList, String tag) {
		Connection conn = getConnection();
		
		BoardDAO bDAO = new BoardDAO();
		
		int result1 = bDAO.insertBoardB(conn, b);
		int result2 = 0;
		int result3 = 0;
		
		if(result1 == 1) {
			int bNo= bDAO.selectBNo(conn);
			result2 = bDAO.insertBoardF(conn,fileList,bNo);
			result3 = bDAO.insertHashtag(conn, tag, bNo);
			
		}
		int result = result1 + result2 + result3;
		
		if(result > 2) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	
		return result;
	}

	public ArrayList<Board> selectBList(double x, double y) {
		Connection conn = getConnection();
		ArrayList<Board> result1 = new BoardDAO().selectBList(conn, x, y);
		
		close(conn);
		return result1;
	}

	public ArrayList<Image> selectIList() {
		Connection conn = getConnection();
		
		ArrayList<Image> result2 =  new BoardDAO().selectIList(conn);
	
		close(conn);
		return result2;
	}

	public Board selectBoardDetail(int bNo) {
		Connection conn = getConnection();
		Board  board = new BoardDAO().selectBoardDetail(conn, bNo);
		
		close(conn);
		return board;
	}

	public ArrayList<Image> selectImageDetail(int bNo) {
		Connection conn = getConnection();
		ArrayList<Image> image = new BoardDAO().selectImageDetail(conn,bNo);
		
		close(conn);
		return image;
	}

	public int deleteBoard(int bNo) {
		Connection conn = getConnection();
		int result= new BoardDAO().deleteBoard(conn, bNo);
		
		if(result > 0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		
		return result;
	}

	public int UpdateBoard(Board b, ArrayList<Image> iList) {
		Connection conn = getConnection();
		int result1 = new BoardDAO().UpdateBoard(conn, b);
		int result2 = new BoardDAO().UpdateBoard(conn, iList);
		int result = result1 + result2;
				
		if(result > 0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public ArrayList<Hashtag> selectHList() {
		Connection conn = getConnection();
		
		ArrayList<Hashtag> hList = new BoardDAO().selectHList(conn);
		
		close(conn);
		
		return hList;
	}

	public ArrayList<Hashtag> selectTagList(String tag) {
		Connection conn = getConnection();
		
		ArrayList<Hashtag> tList = new BoardDAO().selectTagList(conn, tag);
		
		close(conn);
		
		return tList;
	}
	
	public ArrayList<BReply> insertReply(BReply r) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		int result= dao.insertReply(conn,r);
		 
		ArrayList<BReply> rList = new ArrayList<BReply>();
		 
		 if(result > 0) {
			 commit(conn);
			 rList= dao.selectReplyList(conn, r.getbNo());
		 }else {
			 rollback(conn);
		 }
		close(conn);
		return rList;
	}

	public ArrayList<BReply> selectReplyList(int bNo) {
		Connection conn = getConnection();
		ArrayList<BReply> rList = new BoardDAO().selectReplyList(conn, bNo);
		
		close(conn);
		
		return rList;
	}

	public ArrayList<Hashtag> selectHList(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Hashtag> hList = new BoardDAO().selectHList(conn, bNo);
		
		close(conn);
		
		return hList;
	}

	public ArrayList<Board> selectMList(int startNum, double x, double y) {
		 Connection conn = getConnection();
		 
		 ArrayList bList = new BoardDAO().selectMoreBList(conn,startNum, x, y);

		 close(conn);

		 return bList;
	}

	public Board selectMainBoard() {
		Connection conn = getConnection();
		
		Board b = new BoardDAO().selectMainBoard(conn);
		
		close(conn);
		
		return b;
	}

	public ArrayList<Board> selectMyBoard(int mNo) {
		Connection conn = getConnection();
		
		ArrayList<Board> bList = new BoardDAO().selectMyBoard(conn, mNo);
		
		close(conn);
		
		return bList;
	}


}
