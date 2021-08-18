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
import donation.model.service.DonationService;
import donation.model.vo.DReply;
import donation.model.vo.Donation;
import like.model.service.Do_LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectDonationDetailServlet
 */
@WebServlet("/selectDonationDetail.bo")
public class SelectDonationDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDonationDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		DonationService ds = new DonationService();
		
		Board b = ds.selectDetailBoard(bNo); // 게시판테이블 전체정보
		Donation d = ds.selectDetailDonation(bNo); // 후원테이블 전체정보
		ArrayList<Image> IList = ds.selectDetailImage(bNo); // 이미지테이블 전체정보
		ArrayList<DReply> rList = ds.selectReplyList(bNo); // 댓글 전체정보
		
		Likey ly = new Do_LikeService().selectAllLike(bNo, mNo); // 좋아요 기능
		int totalLike = new Do_LikeService().selectTotalLike(bNo); // 좋아요 전체숫자
		
		if(b != null && d != null && IList != null) {
			request.setAttribute("b", b);
			request.setAttribute("d", d);
			request.setAttribute("IList", IList);
			request.setAttribute("rList", rList);
			request.setAttribute("ly", ly);
			request.setAttribute("totalLike", totalLike);
			request.getRequestDispatcher("WEB-INF/views/donation/2_donationDetail.jsp").forward(request, response);
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
