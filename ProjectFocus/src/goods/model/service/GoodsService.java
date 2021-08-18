package goods.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.Image;
import goods.model.dao.GoodsDAO;
import goods.model.vo.Goods;
import goods.model.vo.GoodsBag;
import goods.model.vo.Reply;

public class GoodsService {

	public int insertGoods(Board b, Goods g, ArrayList<Image> fileList, ArrayList<Image> fileList2) {
		Connection conn = getConnection();
		
		int result1 = new GoodsDAO().insertGoodsB(conn, b);
		int result2 = 0;
		int result3 = 0;
		if(result1 == 1) {
			int bNo = new GoodsDAO().selectBNo(conn);			
			result2 = new GoodsDAO().insertGoodsG(conn, g, bNo);
			if(result2 == 1) {
				result3 = new GoodsDAO().insertGoodsF(conn, fileList, bNo);				
				new GoodsDAO().insertGoodsFTwo(conn, fileList2, bNo);				
			}
		}
		int result = result1+result2+result3;
	
		if(result >= 3) {
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
		
		GoodsDAO dao = new GoodsDAO();
		if(i == 1) {
			list = dao.selectBList(conn);
		} else if(i == 2) {
			list = dao.selectGList(conn);
		} else {
			list = dao.selectIList(conn);
		}
		
		close(conn);
		
		return list;
	}

	public Board selectBoard(int bNo) {
		Connection conn = getConnection();
		
		GoodsDAO gDAO = new GoodsDAO();
		
		int result = gDAO.updateCount(conn, bNo);
		
		Board b = null;
		if(result > 0) {
			b = gDAO.selectBoard(conn, bNo);
			
			if(b != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
	}

	public Goods selectGoods(int bNo) {
		Connection conn = getConnection();
		
		Goods g = new GoodsDAO().selectGoods(conn, bNo);
	
		close(conn);
		
		return g;
	}

	public ArrayList<Image> selectImage(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Image> iList = new GoodsDAO().selectImage(conn, bNo);
		
		close(conn);
		
		return iList;
	}

	public int updateGoods(Board b, Goods g, ArrayList<Image> fileList) {
		Connection conn = getConnection();
		
		int result1 = new GoodsDAO().updateGoodsB(conn, b);	
		int result2 = new GoodsDAO().updateGoodsG(conn, g);
		int result3 = new GoodsDAO().updateGoodsF(conn, fileList);				
		int result = result1+result2+result3;
	
		if(result >= 2) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteGoods(int bNo) {
		Connection conn = getConnection();
		
		int result = new GoodsDAO().deleteGoods(conn, bNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList selectMList(int i, int startNum) {	
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		GoodsDAO dao = new GoodsDAO();
		if(i == 1) {
			list = dao.selectMoreBList(conn, startNum);
		} else if(i == 2) {
			list = dao.selectMoreGList(conn, startNum);
		}
		
		close(conn);
		
		return list;
	}

	public int insertGoodsPay(ArrayList<GoodsBag> bagList, int mNo) {
		Connection conn = getConnection();
		
		int result = new GoodsDAO().insertGoodsPay(conn, bagList, mNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
 		
		close(conn);
		
		return result;
	}

	public ArrayList<GoodsBag> selectGoodsPay(int mNo) {
		Connection conn = getConnection();
		
		ArrayList<GoodsBag> bagList = new GoodsDAO().selectGoodsPay(conn, mNo);
		
		close(conn);
		
		return bagList;
	}

	public int insertGoodsReply(Reply r, ArrayList<Image> fileList) {
		Connection conn = getConnection();
		
		int result1 = new GoodsDAO().insertGoodsReply(conn, r);
		int result2 = 0;
		
		if(result1 == 1) {
			int rNo = new GoodsDAO().selectRNo(conn);
			result2 = new GoodsDAO().insertGoodsReplyImg(conn, fileList, r.getbNo(), rNo);		
		}
		int result = result1+result2;
	
		if(result >= 2) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Reply> selectGoodsReply(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Reply> rList = new GoodsDAO().selectGoodsReply(conn, bNo);
		
		close(conn);
		
		return rList;
	}

	public ArrayList<Image> selectGoodsReplyImg(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Image> iList = new GoodsDAO().selectGoodsReplyImg(conn, bNo);
		
		close(conn);
		
		return iList;
	}
	

}
