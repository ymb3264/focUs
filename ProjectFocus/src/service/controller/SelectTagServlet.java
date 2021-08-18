package service.controller;

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
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Service;


/**
 * Servlet implementation class SelectTagServlet
 */
@WebServlet("/selectTag.bo")
public class SelectTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String tagName = request.getParameter("tagName");
		
		ServiceService service = new ServiceService();
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		ArrayList<Board> bList = service.selectCatenameB(tagName);
		ArrayList<Image> iList = service.selectCatenameI(2);
//		ArrayList<Image> iList = service.selectTList(2);
		ArrayList<Service> sList = service.selectCatenameS(tagName);
		ArrayList<Likey> lList = new LikeService().selectLikeList(mNo);
		
		HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
		content.put("bList", bList);
		content.put("iList", iList);
		content.put("sList", sList);
		content.put("lList", lList);
		
		System.out.println(lList);
		System.out.println(iList);
		System.out.println(sList);
		
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
