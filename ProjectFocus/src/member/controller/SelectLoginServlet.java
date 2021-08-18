package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectLoginServlet
 */
@WebServlet("/selectLogin.me")
public class SelectLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		Member m = new Member(userId, userPw);
		
		Member loginUser = new MemberService().selectMember(m);
		
		if(loginUser != null) {
			double x = Double.parseDouble(request.getParameter("user_XLocation"));
			double y = Double.parseDouble(request.getParameter("user_YLocation"));
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("userX", x);
			session.setAttribute("userY", y);
			
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("fail", "아이디 또는 비밀번호를 확인해주세요.");
			request.getRequestDispatcher("WEB-INF/views/member/1_login.jsp").forward(request, response);
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
