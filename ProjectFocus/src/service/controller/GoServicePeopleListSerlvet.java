package service.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class GoServicePeopleListSerlvet
 */
@WebServlet("/servicePeopleList.go")
public class GoServicePeopleListSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoServicePeopleListSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
		ArrayList<Reserve>rList = new ServiceService().selectRList(bNo);

		response.setContentType("application/json; charset=UTF-8");
		
		GsonBuilder gd = new GsonBuilder();
		GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
		Gson gson = dateGd.create();
		gson.toJson(rList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
