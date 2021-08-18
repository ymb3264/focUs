package like.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import like.model.service.LikeService;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertLikeServelt
 */
@WebServlet("/updatePlusLike.bo")
public class UpdatePlusLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlusLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		int result = new LikeService().updatePlusLike(bNo, mNo);
		int totalLike = new LikeService().selectTotalLike(bNo);
		int[] arr = {result, totalLike};
		
		if(result > 0) {
			response.setContentType("application/json; charset=UTF-8"); 
			Gson gson = new Gson();
			gson.toJson(arr, response.getWriter());
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
