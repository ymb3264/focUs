package like.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import like.model.dao.LikeDAO;
import like.model.vo.Likey;

public class LikeService {

	public ArrayList<Likey> selectLikeList(int mNo) {
		Connection conn = getConnection();
		
		ArrayList<Likey> lList= new LikeDAO().selectLikeList(conn, mNo);
		
		return lList;
	}
	
	public Likey selectLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		Likey like = new LikeDAO().selectLike(conn, bNo, mNo);
		
		close(conn);
		
		return like;
	}

	public int updateMinusLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		LikeDAO lDAO = new LikeDAO();
		
		int result = lDAO.updateMinusLike(conn, bNo, mNo);
		int result2 = 0;
		
		if(result > 0) {
			result2 = lDAO.minusBoardLike(conn, bNo);
			
			if(result2 > 0) {
				close(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		return result2;
	}
	
	public int updatePlusLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		LikeDAO lDAO = new LikeDAO(); 
		
		int result = lDAO.selectLikeExist(conn, bNo, mNo);
		int result2 = 0;
		int result3 = 0;
		
		if(result > 0) {
			result2 = lDAO.updatePlusLike(conn, bNo, mNo);
			
			if(result2 > 0) {
				result3 = lDAO.plusBoardLike(conn, bNo);
				
				if(result3 > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
 			} else {
 				rollback(conn);
 			}
		} else {
			result2 = lDAO.insertLike(conn, bNo, mNo);
			
			if(result2 > 0) {
				result3 = lDAO.plusBoardLike(conn, bNo);
				
				if(result3 > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			}  else {
				rollback(conn);
			}
		}
	
		close(conn);
		
		return result3;
	}

	public int selectTotalLike(int bNo) {
		Connection conn = getConnection();
		
		int totalLike = new LikeDAO().selectTotalLike(conn, bNo);
		
		close(conn);
		
		return totalLike;
	}


}
