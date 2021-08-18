package member.controller;

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
import donation.model.service.DonationService;
import donation.model.vo.Donation;
import goods.model.service.GoodsService;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Service;

/**
 * Servlet implementation class SelectMyBoardServlet
 */
@WebServlet("/selectMyBoard.bo")
public class SelectMyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMyBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		ArrayList<Board> bList = new BoardService().selectMyBoard(mNo);
		ArrayList<Image> iList = new BoardService().selectIList();
		
		if(bList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("iList", iList);
			request.getRequestDispatcher("WEB-INF/views/member/1_myBoard.jsp").forward(request, response);
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
