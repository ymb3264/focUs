package like.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import donation.model.dao.DonationDAO;
import like.model.dao.Do_LikeDAO;
import like.model.vo.Likey;

public class Do_LikeService {

	
	
	public int selectTotalLike(int bNo) {
		Connection conn = getConnection();
		
		int totalLike = new Do_LikeDAO().selectTotalLike(conn, bNo);
		
		close(conn);
		
		return totalLike;
	}
	
	public Likey selectAllLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		Likey ly = new Do_LikeDAO().selectAllLike(conn, bNo, mNo);
		
		close(conn);
		return ly;
	}
	
	public int updatePlusLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		int result2 = 0;
		int result3 = 0;
		
		int result = new Do_LikeDAO().selectLNo(conn,bNo,mNo); // lNo 조회해서 있으면
		if(result >0) {
			result2 = new Do_LikeDAO().updateLikeY(conn,bNo,mNo); // L_STATUS -> Y로 변경하고 성공하면
				if(result2 > 0) {
				result3 = new Do_LikeDAO().updatePlusTotal(conn,bNo,mNo); // Board에 B_Like +=
				
				if(result3 >0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			}
		} else { // lNo가 없으면 
			result2 = new Do_LikeDAO().insertLike(conn,bNo,mNo); // lNO생성과 동시에 L_STATUS -> Y로 변경하고 성공하면
				if(result2 > 0) {
				result3 = new Do_LikeDAO().updatePlusTotal(conn,bNo,mNo); // Board에 B_Like +=
				
				if(result3 >0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			}
		}
		
		close(conn);
		
		return result3;
		
		
	}

	public int updateMinusLike(int bNo, int mNo) {
		Connection conn = getConnection();
		
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		
		result = new Do_LikeDAO().updateLikeN(conn,bNo,mNo); // L_STATUS N으로 쿼리 변경 성공하면
		
		if(result > 0) {
			result1 = new Do_LikeDAO().updateMinusTotal(conn,bNo,mNo); // 토탈값 - 실행
		};
		
		
		if(result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result1;
	}

	

}
