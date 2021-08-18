package board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.vo.Member;

/**
 * Servlet implementation class GoUpdateBoardServlet
 */
@WebServlet("/updateBoard.go")
public class GoBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//보드객체, 이미지 값을 가져오고 보내서 수정페이지에 뿌려주고, 수정내용을 db에 넣어주자
		
		request.setCharacterEncoding("UTF-8");
		String title= request.getParameter("title");
		String content= request.getParameter("content");
		//Date date = request.getParameter("date");
		String etc = request.getParameter("etc");
		String youtube = request.getParameter("youtube");
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String location = request.getParameter("location");
		String[] tag = request.getParameterValues("tag");
		
		
		Board b = new Board();
		b.setbTitle(title);
		b.setbContent(content);
		//b.setbDate(date);
		b.setbETC(etc);
		b.setbYoutube(youtube);
		b.setbNo(bNo);
		b.setbLocation(location);
		
		request.setAttribute("b",b);
		
		
		String[] imgChange = request.getParameterValues("imgChange");
		String[] imgOrigin = request.getParameterValues("imgOrigin");
		String[] imgNo = request.getParameterValues("imgNo");
		
		request.setAttribute("imgChange", imgChange);
		request.setAttribute("imgOrigin", imgOrigin);
		request.setAttribute("imgNo", imgNo);
		request.setAttribute("tag", tag);
		
		
		Date dat = new Date(new GregorianCalendar().getTimeInMillis()); // 오늘날짜 받아주기
		request.setAttribute("dat", dat);	
		
		request.getRequestDispatcher("WEB-INF/views/board/4_boardUpdate.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
