package service.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import board.model.vo.Image;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Service;

/**
 * Servlet implementation class ServiceReserveServlet
 */
@WebServlet("/serviceReserve.go")
public class ServiceReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceReserveServlet() {
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
		
		String writer = request.getParameter("writer");
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		String catename = request.getParameter("catename");
	
		String phone = request.getParameter("mPhone");
		String nick = request.getParameter("mNick");
		
		Board b = new Board();
		
		b.setbTitle(title);
		b.setbContent(content);
		b.setbNo(bNo);
		b.setbWriter(writer);
		
		Service s = new Service();
		
		s.setsNo(sNo);
		s.setsTime(time);
		s.setsLocation(location);
		s.setsCatename(catename);

		Member m = new Member();
		
		m.setmPhone(phone);
		m.setmNick(nick);
		
		ServiceService service = new ServiceService();
		
		Board br = service.selectDetailBoard(bNo);
		Service sr = service.selectDetailService(bNo);
		ArrayList<Image> iList = service.selectDetailImage(bNo); // 서비스 리스트 가져오기
		
		
		if (b != null && s != null && m != null && br != null && sr != null && iList != null) {
			request.setAttribute("b", b);
			request.setAttribute("m", m);
			request.setAttribute("s", s);
			request.setAttribute("br", br);
			request.setAttribute("sr", sr);
			request.setAttribute("iList", iList);

			request.getRequestDispatcher("WEB-INF/views/service/3_serviceReservation.jsp").forward(request, response);
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
