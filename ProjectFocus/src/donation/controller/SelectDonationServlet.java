package donation.controller;

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
import donation.model.service.DonationService;
import donation.model.vo.Donation;

/**
 * Servlet implementation class SelectDonationServlet
 */
@WebServlet("/selectDonationList.bo")
public class SelectDonationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDonationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonationService ds = new DonationService();
		
		// 페이지정보
		int listCount;
		int currentPage;
		int pageLimit;
		int boardLimit;
		int maxPage;
		int startPage;
		int endPage;
		
		listCount = ds.getListCount();
		
		currentPage = 1;
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
				
		pageLimit = 10;
		boardLimit  = 9;
				
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		startPage = ((currentPage-1)/pageLimit) * pageLimit + 1;
		endPage = startPage + pageLimit -1;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		
		ArrayList<Board> bList = ds.selectAllBoard(pi); // 게시판테이블 전체정보
		ArrayList<Donation> dList = ds.selectAllDonation(pi); // 후원테이블 전체정보
		ArrayList<Image> IList = ds.selectAllImage(pi); // 이미지테이블 전체정보
		
		int percent = 0;
		
			for(int i = 0; i < bList.size(); i++){	
				Board b = bList.get(i); 
				Donation d = dList.get(i); 
				if(b.getbNo() == d.getbNo()) {
					
					percent = (int)Math.floor((double)((double)b.getpBtotalpay() / (double)d.getdPay()) * 100);
				}
				d.setPercent(percent);
			}
		
		
		if(bList != null && dList != null && IList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("dList", dList);
			request.setAttribute("IList", IList);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("WEB-INF/views/donation/2_donation.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/views/common/mainPage.jsp").forward(request, response);
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
