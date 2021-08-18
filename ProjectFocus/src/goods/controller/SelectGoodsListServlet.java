package goods.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import board.model.vo.Image;
import goods.model.service.GoodsService;
import goods.model.vo.Goods;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectGoodsListServlet
 */
@WebServlet("/selectGoodsList.sl")
public class SelectGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsService service = new GoodsService();
		int mNo = 0;
		if(request.getSession().getAttribute("loginUser") != null) {
			mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();	
		}
		
		ArrayList<Board> bList = service.selectTList(1); // 게시판 리스트
		ArrayList<Goods> gList = service.selectTList(2); // 굿즈 리스트
		ArrayList<Image> iList = service.selectTList(3); // 이미지 리스트
		ArrayList<Likey> lList = null;
		if(mNo != 0) {
			lList = new LikeService().selectLikeList(mNo);			
		}
		
		if(bList != null && gList != null && iList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("gList", gList);
			request.setAttribute("iList", iList);
			if(lList != null) {
				request.setAttribute("lList", lList);
			}
			request.getRequestDispatcher("WEB-INF/views/goods/5_goods.jsp").forward(request, response);
		} else {
			//
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
