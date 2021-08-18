package donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import donation.model.service.DonationService;
import member.model.vo.Member;

/**
 * Servlet implementation class GoDonationPayServlet
 */
@WebServlet("/donationPay.go")
public class GoDonationPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoDonationPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mName = ((Member)request.getSession().getAttribute("loginUser")).getmName();
		String mPhone = ((Member)request.getSession().getAttribute("loginUser")).getmPhone();
		String mEmail = ((Member)request.getSession().getAttribute("loginUser")).getmEmail();
		
		int bNo = Integer.parseInt(request.getParameter("hiddenBNo"));
		String title = request.getParameter("hiddenBTitle");
		String dPay = request.getParameter("hiddenDPay");
		String bWriter = request.getParameter("hiddenBWriter");
		Board b = new DonationService().selectDetailBoard(bNo);
		int pBtotalpay = b.getpBtotalpay();
		
		int morePay = Integer.parseInt(dPay) - pBtotalpay;
		
		Member m = new Member();
		m.setmName(mName);
		m.setmPhone(mPhone);
		m.setmEmail(mEmail);
		
		request.setAttribute("m", m);
		request.setAttribute("bNo", bNo);
		request.setAttribute("title", title);
		request.setAttribute("dPay", dPay);
		request.setAttribute("bWriter", bWriter);
		request.setAttribute("morePay", morePay);
		
		
		request.getRequestDispatcher("WEB-INF/views/donation/2_donationPay.jsp").forward(request, response);
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
