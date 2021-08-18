package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Image;
import goods.model.service.GoodsService;
import goods.model.vo.Goods;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;
import service.model.service.ServiceService;

/**
 * Servlet implementation class SelectMainServlet
 */
@WebServlet("/selectMain.bo")
public class SelectMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mNo = 0;
		if(request.getSession().getAttribute("loginUser") != null) {
			mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();	
		}
		
		double x = Double.parseDouble(request.getParameter("lat"));
		double y = Double.parseDouble(request.getParameter("lon"));
		request.getSession().setAttribute("userX", x);
		request.getSession().setAttribute("userY", y);
		
		Board board = new BoardService().selectMainBoard();
		
		ArrayList<Image> bIList = new BoardService().selectIList();
		
		GoodsService gservice = new GoodsService();
		ArrayList<Board> gBList = gservice.selectTList(1); // 게시판 리스트
		ArrayList<Goods> gList = gservice.selectTList(2); // 굿즈 리스트
		ArrayList<Image> gIList = gservice.selectTList(3); // 이미지 리스트
		ArrayList<Likey> lList = null;
		ArrayList<Board> serviceBList = new ServiceService().selectBList(x, y);
		
//		System.out.println(gBList);
//		System.out.println(bIList);
//		System.out.println(board);
//		System.out.println(gList);
//		System.out.println(gIList);
//		System.out.println(lList);
		
		if(mNo != 0) {
			lList = new LikeService().selectLikeList(mNo);			
		}
		
		// 자유게시판
		request.setAttribute("board", board);
		request.setAttribute("bIList", bIList);
		request.setAttribute("serviceBList", serviceBList);
		
		
		// 굿즈
		request.setAttribute("gBList", gBList);
		request.setAttribute("gList", gList);
		request.setAttribute("gIList", gIList);
		if(lList != null) {
			request.setAttribute("lList", lList);
		}
		
		request.setAttribute("check", 1);
		request.getRequestDispatcher("WEB-INF/views/common/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
