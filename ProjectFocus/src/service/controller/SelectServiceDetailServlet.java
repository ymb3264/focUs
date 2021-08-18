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
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Reply;
import service.model.vo.Service;

/**
 * Servlet implementation class ThumbnailServiceServlet
 */
@WebServlet("/selectServiceDetail.bo")
public class SelectServiceDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServiceDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("WEB-INF/views/service/3_serviceDetail.jsp").forward(request, response);
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		ServiceService service = new ServiceService();
		
		Board b = service.selectDetailBoard(bNo);
		Service s = service.selectDetailService(bNo);
		ArrayList<Image> iList = service.selectDetailImage(bNo); // 서비스 리스트 가져오기
//		ArrayList<Reply> rList = service.selectReplyList(bNo);
		Likey like = new LikeService().selectLike(bNo, mNo);
		
		if(b != null && iList != null && s != null) {
			request.setAttribute("b", b);
			request.setAttribute("iList", iList);
//			request.setAttribute("rList", rList);
			request.setAttribute("s", s); 
			request.setAttribute("like", like);
			request.getRequestDispatcher("WEB-INF/views/service/3_serviceDetail.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "사진게시판 상세보기에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/service/main.jsp").forward(request, response);
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
