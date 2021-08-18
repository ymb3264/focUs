package goods.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goods.model.vo.GoodsBag;

/**
 * Servlet implementation class GoGoodsBagServlet
 */
@WebServlet("/goGoodsBag.sl")
public class GoGoodsBagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoGoodsBagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String img = request.getParameter("thumbnailImg");
		int amount = Integer.parseInt(request.getParameter("goods_amount"));
		int price = Integer.parseInt(request.getParameter("totalPrice"));
		int goBag = Integer.parseInt(request.getParameter("goBag"));
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random() * 10000);
		long buyNum = Long.parseLong(sdf.format(new Date(currentTime)) + ranNum);
		
		GoodsBag bag = new GoodsBag(title, img, amount, price, buyNum);
		
		HttpSession session = request.getSession();
		
		ArrayList<GoodsBag> bagList = (ArrayList<GoodsBag>)session.getAttribute("bagList");
		
		if(session.getAttribute("bagList") == null) {
			bagList = new ArrayList<GoodsBag>();
		}
		
		bagList.add(bag);
		
		session.setAttribute("bagList", bagList);
		
		if(goBag == 1 ) {
			request.getRequestDispatcher("WEB-INF/views/goods/5_goodsBag.jsp").forward(request, response);			
		} else {
			response.sendRedirect("selectGoodsDetail.sl?bNo=" + bNo);
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
