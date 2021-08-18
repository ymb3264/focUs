package donation.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import donation.model.vo.Donation;

/**
 * Servlet implementation class GoDonationUpdateWriteServlet
 */
@WebServlet("/DonationUpdateWrite.go")
public class GoDonationUpdateWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoDonationUpdateWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int hiddenBNo = Integer.parseInt(request.getParameter("hiddenBNo"));
		String hiddenCatename  = request.getParameter("hiddenCatename");
		String hiddenBTitle  = request.getParameter("hiddenBTitle");
		int hiddenDPay = Integer.parseInt(request.getParameter("hiddenDPay"));
		String hiddenDPeriod  = request.getParameter("hiddenDPeriod");
		String hiddenBContent  = request.getParameter("hiddenBContent");
		String hiddenDEtc1  = request.getParameter("hiddenDEtc1");
		String hiddenDEtc2  = request.getParameter("hiddenDEtc2");
		String[] imgChange = request.getParameterValues("imgChange");
		String[] imgOrigin = request.getParameterValues("imgOrigin");
		String[] imgNo = request.getParameterValues("imgNo");
		
		
		Board b = new Board();
		b.setbNo(hiddenBNo);
		b.setbTitle(hiddenBTitle);
		b.setbContent(hiddenBContent);
		
		Donation d = new Donation();
		d.setdCatename(hiddenCatename);
		d.setdPay(hiddenDPay);
		d.setdEtc1(hiddenDEtc1);
		d.setdEtc2(hiddenDEtc2);
		
		request.setAttribute("dPeriod", hiddenDPeriod);
		request.setAttribute("b", b);
		request.setAttribute("d", d);
		request.setAttribute("imgChange", imgChange);
		request.setAttribute("imgOrigin", imgOrigin);
		request.setAttribute("imgNo", imgNo);
		request.getRequestDispatcher("WEB-INF/views/donation/2_donationUpdate.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
