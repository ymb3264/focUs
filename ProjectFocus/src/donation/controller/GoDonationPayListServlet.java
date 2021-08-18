package donation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import donation.model.service.DonationService;
import donation.model.vo.DPayment;
import member.model.vo.Member;

/**
 * Servlet implementation class GoDonationPayListServlet
 */
@WebServlet("/donationPayList.go")
public class GoDonationPayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoDonationPayListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		
		ArrayList<DPayment> pList = new DonationService().selectPList(mNo);
		
		
//		request.setAttribute("pList", pList);
//		request.getRequestDispatcher("WEB-INF/views/member/1_mypage.jsp").forward(request, response);
		
		response.setContentType("application/json; charset=UTF-8");
		
		GsonBuilder gd = new GsonBuilder();
		GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
		Gson gson = dateGd.create();
		gson.toJson(pList, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
