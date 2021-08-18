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
import board.model.vo.Hashtag;
import board.model.vo.Image;

/**
 * Servlet implementation class SelectBoardHashtagServlet
 */
@WebServlet("/selectHashTag.bo")
public class SelectBoardHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardHashtagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("tagName");
		double x = Double.parseDouble(request.getSession().getAttribute("userX").toString());
		double y = Double.parseDouble(request.getSession().getAttribute("userY").toString());
		
		BoardService service = new BoardService();
		ArrayList<Hashtag> tList = service.selectTagList(tag);
		ArrayList<Board> bList = service.selectBList(x, y);
		ArrayList<Image> iList = service.selectIList();
		ArrayList<Hashtag> tagList = service.selectHList();
		HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
		content.put("tList", tList);
		content.put("bList", bList);
		content.put("iList", iList);
		content.put("tagList", tagList);
		
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
