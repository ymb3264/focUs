package board.controller;

import member.model.vo.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Image;
import common.MyFileRenamePolicy;

/**
 * Servlet implementation class InsertBoardServlet
 */
@WebServlet("/insertBoard.bo")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=1024*1024*10;
			String root=request.getSession().getServletContext().getRealPath("/");
			String savePath=root + "board_uploadFiles/";
			
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();
			
			Enumeration<String> files = multipartRequest.getFileNames();
			while(files.hasMoreElements()) {
				String name= files.nextElement();
				
				
				if(multipartRequest.getFilesystemName(name) != null) {
				saveFiles.add(multipartRequest.getFilesystemName(name));
				originFiles.add(multipartRequest.getOriginalFileName(name));
			}
		}
		String title= multipartRequest.getParameter("title");
		String content= multipartRequest.getParameter("content");
		int writer= ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		String etc = multipartRequest.getParameter("etc");
		String tag = multipartRequest.getParameter("tag");
		String location = multipartRequest.getParameter("location");
		double xAddress = Double.parseDouble(multipartRequest.getParameter("xAddress"));
		double yAddress = Double.parseDouble(multipartRequest.getParameter("yAddress"));
		String mNick= ((Member)request.getSession().getAttribute("loginUser")).getmNick();
		String youtube= multipartRequest.getParameter("youtube");
		
//		Hashtag t = new Hashtag();
//		t.settName(tag);
		
		Board b = new Board();
		b.setbTitle(title);
		b.setbContent(content);
		b.setmNo(writer);
		b.setbType(4);
		b.setbETC(etc);
		b.setbLocation(location);
		b.setxAddress(xAddress);
		b.setyAddress(yAddress);
		b.setbWriter(mNick);
		b.setbYoutube(youtube);
		
		ArrayList<Image> fileList = new ArrayList<Image>();
		for(int i= originFiles.size()-1;i>=0; i--) {
			Image a= new Image();
			a.setiPath(savePath);
			a.setiOrigin(originFiles.get(i));
			a.setiChange(saveFiles.get(i));
			
			if(i == originFiles.size()-1) {
				a.setiLevel(0);
			}else {
				a.setiLevel(1);
			}
		
			fileList.add(a);
		}
		
		int result = new BoardService().insertBoard(b,fileList, tag);
		
		if(result > 2) {
			response.sendRedirect("selectBoardList.bo");
		}else {
			for(int i=0; i<saveFiles.size(); i++) {
				File fail = new File(savePath + saveFiles.get(i));
				fail.delete();
			}
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/board/4_boardWrite.jsp");
		}
			
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
