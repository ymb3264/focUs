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
import goods.model.service.GoodsService;
import goods.model.vo.GoodsBag;
import member.model.service.MemberService;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Reserve;
import service.model.vo.Service;

/**
 * Servlet implementation class SelectMyPageServlet
 */
@WebServlet("/myPageSelect.me")
public class SelectMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		Member m = new MemberService().selectMember(loginUser);
		
		request.getSession().setAttribute("loginUser", m);
		
		String mNick = ((Member)request.getSession().getAttribute("loginUser")).getmNick();
		Service s = new ServiceService().selectServiceDetail(mNo);
		Reserve r = new ServiceService().selectReserveDetail(mNo);
		ArrayList<Board> bList = new BoardService().selectMyBoard(mNo);
		int count = bList.size();
		
		ArrayList<GoodsBag> bagList = new GoodsService().selectGoodsPay(mNo);
	
		if (s.getbNo() == 0 && r == null) {
//			request.setAttribute("loginUser", loginUser);
			request.setAttribute("m", m);
			request.setAttribute("bagList", bagList);
			request.setAttribute("count", count);
//			request.setAttribute("s", s);
			request.getRequestDispatcher("WEB-INF/views/member/1_myPage.jsp").forward(request, response);
		} else if (s.getmNick() != null && s.getmNick().equals(mNick)) {
//			request.getSession().setAttribute("loginUser", loginUser);
			request.setAttribute("m", m);
			request.setAttribute("bagList", bagList);
			request.setAttribute("s", s);
			request.setAttribute("count", count);
			request.getRequestDispatcher("WEB-INF/views/member/1_myPage.jsp").forward(request, response);
		} else if (r.getrUserNo() == mNo) {
//			request.getSession().setAttribute("loginUser", loginUser);
			request.setAttribute("m", m);
			request.setAttribute("bagList", bagList);
			request.setAttribute("r", r);
			request.setAttribute("count", count);
			request.getRequestDispatcher("WEB-INF/views/member/1_myPage.jsp").forward(request, response);
		} else {
			// 실패
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
