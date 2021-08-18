package donation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import board.model.vo.Board;
import board.model.vo.Image;
import common.PageInfo;
import donation.model.service.DonationService;
import donation.model.vo.Donation;

/**
 * Servlet implementation class GoSelectCategoryServlet
 */
@WebServlet("/selectCatename.go")
public class GoSelectCatenameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoSelectCatenameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cn = request.getParameter("catename");
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
				
		
		ArrayList<Board> bList = ds.selectCatenameB(cn, pi);
		ArrayList<Donation> dList = ds.selectCatenameD(cn, pi); 
		ArrayList<Image> IList = ds.selectAllImage(pi);
		ArrayList<PageInfo> pi2 = new ArrayList<PageInfo>();
		
		
		int percent = 0;
		
		for(int i = 0; i < bList.size(); i++){	
			Board b = bList.get(i); 
			Donation d = dList.get(i); 
			if(b.getbNo() == d.getbNo()) {
				
				percent = (int)Math.floor((double)((double)b.getpBtotalpay() / (double)d.getdPay()) * 100);
			}
			d.setPercent(percent);
		}
		
		HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
		content.put("bList", bList);
		content.put("dList", dList);
		content.put("IList", IList);
		pi2.add(pi);
		content.put("pi", pi2);
		
		response.setContentType("application/json; charset=UTF-8");
		GsonBuilder gd = new GsonBuilder();
        GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
        Gson gson = dateGd.create();
		gson.toJson(content, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
