package goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import goods.model.vo.Goods;

/**
 * Servlet implementation class GoGoodsUpdateServlet
 */
@WebServlet("/goodsUpdate.go")
public class GoGoodsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoGoodsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int price = Integer.parseInt(request.getParameter("price"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String company = request.getParameter("company");
//		int imgSize = Integer.parseInt(request.getParameter("imgSize"));
//		String img0 = request.getParameter("img0");
//		String img1 = request.getParameter("img1");
//		String img2 = request.getParameter("img2");
//		String img3 = request.getParameter("img3");
		String[] imgChange = request.getParameterValues("imgChange");
		String[] imgOrigin = request.getParameterValues("imgOrigin");
		String[] imgNo = request.getParameterValues("imgNo");
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
//		System.out.println(imgOrigin[0]);
//		System.out.println(imgOrigin[1]);
//		System.out.println(imgOrigin[2]);
//		System.out.println(imgNo[0]);
//		System.out.println(imgNo[1]);
//		System.out.println(imgNo[2]);
		
		Board b = new Board();
		Goods g = new Goods();
		b.setbNo(bNo);
		b.setbTitle(title);
		b.setbContent(content);
		g.setgPrice(price);
		g.setgAmount(amount);
		g.setgCompany(company);
		
		request.setAttribute("b", b);
		request.setAttribute("g", g);
		request.setAttribute("imgChange", imgChange);
		request.setAttribute("imgOrigin", imgOrigin);
		request.setAttribute("imgNo", imgNo);
		request.getRequestDispatcher("WEB-INF/views/goods/5_goodsUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
