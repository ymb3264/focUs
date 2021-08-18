package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertJoinServlet
 */
@WebServlet("/insert.me")
public class InsertJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String x = request.getParameter("xAddress");
		double xAddress = Double.parseDouble(request.getParameter("xAddress"));   
		double yAddress = Double.parseDouble(request.getParameter("yAddress"));   
		
		Member m = new Member(id, pw, name, nick, phone, email, address, postcode, address2);
		m.setxAddress(xAddress);
		m.setyAddress(yAddress);
		
		int result = new MemberService().insertMember(m);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath());
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
