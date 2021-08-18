package service.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import service.model.vo.Service;

/**
 * Servlet implementation class GoServiceUpdateServlet
 */
@WebServlet("/serviceUpdate.go")
public class GoServiceUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoServiceUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String etc = request.getParameter("etc");
		double xlocation = Double.parseDouble(request.getParameter("xLocation"));
		double ylocation = Double.parseDouble(request.getParameter("yLocation"));
		
//		int category = Integer.parseInt(request.getParameter("category"));
		String catename = request.getParameter("catename");
		String location = request.getParameter("location");
		String time = request.getParameter("time");
		
		String[] iChange = request.getParameterValues("iChange");
		String[] iOrigin = request.getParameterValues("iOrigin");
		String[] iNo = request.getParameterValues("iNo");
		
		Board b = new Board();
		b.setbNo(bNo);
		b.setbTitle(title);
		b.setbContent(content);
		b.setbETC(etc);
		b.setxAddress(xlocation);
		b.setyAddress(ylocation);
		
		
		Service s = new Service();
//		s.setsCategory(category);
		s.setsCatename(catename);
		s.setsLocation(location);
		s.setsTime(time);
		
		request.setAttribute("b", b);
		request.setAttribute("s", s);
		request.setAttribute("iChange", iChange);
		request.setAttribute("iOrigin", iOrigin);
		request.setAttribute("iNo", iNo);
		request.getRequestDispatcher("WEB-INF/views/service/3_serviceUpdate.jsp").forward(request, response);
		
//		System.out.println(title);
//		System.out.println(content);
//		System.out.println(etc);
//		System.out.println(bNo);
//		System.out.println(category);
//		System.out.println(time);
//		System.out.println(location);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
