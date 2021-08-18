package goods.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.vo.Board;
import board.model.vo.Image;
import goods.model.service.GoodsService;
import goods.model.vo.Goods;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectMoreGoodsServlet
 */
@WebServlet("/selectMoreGoods.sl")
public class SelectMoreGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMoreGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int startNum = Integer.parseInt(request.getParameter("startNum"));
		int mNo = 0;
		if(request.getSession().getAttribute("loginUser") != null) {
			mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();	
		}
		
		GoodsService service = new GoodsService();
		ArrayList<Board> bList = service.selectMList(1, startNum); // 게시판 리스트
		ArrayList<Goods> gList = service.selectMList(2, startNum); // 굿즈 리스트
		ArrayList<Image> iList = service.selectTList(3); // 이미지 리스트
		ArrayList<Likey> lList = null;
		if(mNo != 0) {
			lList = new LikeService().selectLikeList(mNo);			
		}
		HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
		content.put("bList", bList);
		content.put("gList", gList);
		content.put("iList", iList);
		if(lList != null) {
			content.put("lList", lList);
		}
		
//		if(bList != null && gList != null && iList != null) {
			response.setContentType("application/json; charset=UTF-8"); 
			Gson gson = new Gson();
			gson.toJson(content, response.getWriter());
//		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
