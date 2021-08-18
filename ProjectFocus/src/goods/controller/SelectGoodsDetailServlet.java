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
import goods.model.vo.Reply;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectGoodsDetailServlet
 */
@WebServlet("/selectGoodsDetail.sl")
public class SelectGoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGoodsDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = 0;
		if(request.getSession().getAttribute("loginUser") != null) {
			mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		}
		
		GoodsService gService = new GoodsService();
		Board b = gService.selectBoard(bNo);
		Goods g = gService.selectGoods(bNo);
		ArrayList<Image> iList = gService.selectImage(bNo);
		ArrayList<Reply> rList = gService.selectGoodsReply(bNo);
		ArrayList<Image> rIList = gService.selectGoodsReplyImg(bNo);
		Likey like = null;
		if(mNo != 0) {
			like = new LikeService().selectLike(bNo, mNo);			
		}
		
		
		if(iList != null) {
			request.setAttribute("b", b);
			request.setAttribute("g", g);
			request.setAttribute("iList", iList);
			request.setAttribute("like", like);
			request.setAttribute("rList", rList);
			request.setAttribute("rIList", rIList);
			request.getRequestDispatcher("WEB-INF/views/goods/5_goodsDetail.jsp").forward(request, response);
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
