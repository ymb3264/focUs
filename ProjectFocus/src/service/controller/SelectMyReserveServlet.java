package service.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Reserve;



/**
 * Servlet implementation class SelectMyReserveServlet
 */
@WebServlet("/reserveSuccess.bo")
public class SelectMyReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMyReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String location = request.getParameter("location");
		String time = request.getParameter("time");
		
		Reserve r = new Reserve();
		r.setrUserNo(userNo);
		r.setrBNo(bNo);
		r.setrTitle(title);
		r.setrWriter(writer);
		r.setrLocation(location);
		r.setrTime(time);
		
		String name = ((Member)request.getSession().getAttribute("loginUser")).getmName();
		String phone = ((Member)request.getSession().getAttribute("loginUser")).getmPhone();
		String email = ((Member)request.getSession().getAttribute("loginUser")).getmEmail();
		String catename = request.getParameter("catename");
		
		Member m = new Member();
		m.setmName(name);
		m.setmPhone(phone);
		m.setmEmail(email);
		m.setmNo(userNo);
		
		int result2 = new ServiceService().insertReservePeople(m, bNo, catename);
		
		if(result2 > 0) {
			response.sendRedirect("myPageSelect.me");
//			request.getRequestDispatcher("WEB-INF/views/member/1_myPage.jsp").forward(request, response);
		} else {
			
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
