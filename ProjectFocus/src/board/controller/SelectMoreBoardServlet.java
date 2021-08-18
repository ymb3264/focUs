package board.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Image;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectMoreBoardServlet
 */
@WebServlet("/SelectMoreBoard.bo")
public class SelectMoreBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMoreBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int startNum = Integer.parseInt(request.getParameter("startNum"));
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		double x = Double.parseDouble(request.getSession().getAttribute("userX").toString());
		double y = Double.parseDouble(request.getSession().getAttribute("userY").toString());
		
		BoardService service = new BoardService();
		ArrayList<Board> bList = service.selectMList(startNum, x, y);
		ArrayList<Image> iList = service.selectIList();
		ArrayList<Likey> lList = new LikeService().selectLikeList(mNo);
		
//		System.out.println(startNum);
//		System.out.println(bList);
//		System.out.println(iList);
//		System.out.println(lList);
		
		
		
		HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
		content.put("bList", bList);
		content.put("iList", iList);
		content.put("lList", lList);
		
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
