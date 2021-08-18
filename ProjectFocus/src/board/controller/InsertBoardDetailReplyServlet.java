package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import member.model.vo.Member;
import board.model.service.BoardService;
import board.model.vo.BReply;

/**
 * Servlet implementation class InsertBoardReplyServlet
 */
@WebServlet("/insertBoardDetailReply.bo")
public class InsertBoardDetailReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardDetailReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		int bNo= Integer.parseInt(request.getParameter("bNo"));
		String content = request.getParameter("content");
		String mNick = ((Member)request.getSession().getAttribute("loginUser")).getmNick();
		
		BReply r = new BReply();
		r.setmNo(mNo);
		r.setbNo(bNo);
		r.setrContent(content);
		r.setmNick(mNick);
		
		ArrayList<BReply> rList = new BoardService().insertReply(r);
		
		response.setContentType("application/json; charset=UTF-8");
		
		GsonBuilder gd= new GsonBuilder();
		GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
		Gson gson = dateGd.create();
		gson.toJson(rList, response.getWriter());
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
