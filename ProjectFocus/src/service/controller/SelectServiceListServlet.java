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
import common.PageInfo;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Service;


/**
 * Servlet implementation class SelectServiceListServlet
 */
@WebServlet("/selectServiceList.bo")
public class SelectServiceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServiceListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceService service = new ServiceService();
		
		double x = Double.parseDouble(request.getSession().getAttribute("userX").toString());
		double y = Double.parseDouble(request.getSession().getAttribute("userY").toString());
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();

		ServiceService pService = new ServiceService();
		
		ArrayList<Board> bList = service.selectBList(x, y); // 게시판 리스트 가져오기
		ArrayList<Image> iList = service.selectTList(2);	// 이미지 리스트 가져오기 
		ArrayList<Service> sList = service.selectTList(3); // 서비스 리스트 가져오기
		ArrayList<Likey> lList = new LikeService().selectLikeList(mNo);
		
		String page = null;
		if(bList != null && iList != null && sList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("iList", iList);
			request.setAttribute("sList", sList); 
			request.setAttribute("lList", lList);
			
			page = "WEB-INF/views/service/3_service.jsp";
			
//			request.getRequestDispatcher("WEB-INF/views/service/3_service.jsp").forward(request, response);
		} else {
//			request.getRequestDispatcher("WEB-INF/views/common/mainPage.jsp").forward(request, response);
			request.setAttribute("msg", "사진 게시판 조회에 실패하였습니다.");
			page = "WEB-INF/views/common/main.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


