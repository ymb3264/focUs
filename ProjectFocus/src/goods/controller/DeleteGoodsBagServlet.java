package goods.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import goods.model.vo.GoodsBag;

/**
 * Servlet implementation class DeleteGoodsBagServlet
 */
@WebServlet("/deleteGoodsBag.sl")
public class DeleteGoodsBagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodsBagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int gNo = Integer.parseInt(request.getParameter("gNo"));
		
		ArrayList<GoodsBag> bagList = (ArrayList<GoodsBag>)request.getSession().getAttribute("bagList");
//		System.out.println(bagList);
		
		bagList.remove(gNo);
		
//		System.out.println(bagList);
		
		response.setContentType("application/json; charset=UTF-8"); 
		Gson gson = new Gson();
		gson.toJson(bagList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
