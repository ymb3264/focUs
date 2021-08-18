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

import service.model.service.ServiceService;
import service.model.vo.Reserve;

/**
 * Servlet implementation class GoUpdateSpButtonYes
 */
@WebServlet("/updateSpButtonYes.go")
public class GoUpdateSpButtonYes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoUpdateSpButtonYes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int rNo = Integer.parseInt(request.getParameter("rNo"));
		
		int result = new ServiceService().updateSpButtonYes(rNo);
		Reserve r  = new ServiceService().selectSpButtonYes(rNo);
		
//		/*
//		 * HashMap<String, ArrayList> content = new HashMap<String, ArrayList>();
//		content.put("bList", bList);
//		content.put("dList", dList);
//		content.put("IList", IList);
//		pi2.add(pi);
//		content.put("pi", pi2);*/
//		
//		HashMap<String, Object> content = new HashMap<String, Object>();
		
		response.setContentType("application/json; charset=UTF-8");
		
		GsonBuilder gd = new GsonBuilder();
		GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
		Gson gson = dateGd.create();
		gson.toJson(r, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
