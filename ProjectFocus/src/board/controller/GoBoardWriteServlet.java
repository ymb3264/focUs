package board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;

/**
 * Servlet implementation class GoBoardWriteServlet
 */
@WebServlet("/boardWrite.go")
public class GoBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoBoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mAdd= ((Member)request.getSession().getAttribute("loginUser")).getmAddress();
		request.setAttribute("mAdd", mAdd);
		Date dat = new Date(new GregorianCalendar().getTimeInMillis()); // 오늘날짜 받아주기
		request.setAttribute("dat", dat);
		request.getRequestDispatcher("WEB-INF/views/board/4_boardWrite.jsp").forward(request,response);
	
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
