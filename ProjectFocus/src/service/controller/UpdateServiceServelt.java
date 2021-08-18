package service.controller;

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

import board.model.vo.Board;
import board.model.vo.Image;
import common.MyFileRenamePolicy;
import member.model.vo.Member;
import service.model.service.ServiceService;
import service.model.vo.Service;

/**
 * Servlet implementation class UpdateServiceServelt
 */
@WebServlet("/updateService.bo")
public class UpdateServiceServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServiceServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "service_uploadFiles/";
			
//			System.out.println(savePath);
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
			MultipartRequest multipartRequest
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();
			Enumeration<String> files = multipartRequest.getFileNames(); // 이름을 가져와서 이너머레이션에 넘겨준다라..
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
//				System.out.println(multipartRequest.getFilesystemName(name));
				
				if(multipartRequest.getFilesystemName(name) != null) { // 여기서 실제 파일 이름을 가져오는 명령어
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
			
			int bNo = Integer.parseInt(multipartRequest.getParameter("bNo"));
			int writer = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			String location = multipartRequest.getParameter("location");
			String time = multipartRequest.getParameter("time");
			String etc = multipartRequest.getParameter("etc");
			int category = Integer.parseInt(multipartRequest.getParameter("category"));
			double xlocation = Double.parseDouble(multipartRequest.getParameter("xlocation"));
			double ylocation = Double.parseDouble(multipartRequest.getParameter("ylocation"));
//			String catename =multipartRequest.getParameter("catename");
			
			String[] iNo = multipartRequest.getParameterValues("iNo");
			

			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setmNo(writer);
			b.setbType(3);
			b.setbETC(etc);
			b.setbNo(bNo);
			b.setxAddress(xlocation);
			b.setyAddress(ylocation);
			
			Service s = new Service();
			s.setbType(3);
			s.setsCategory(category);
			s.setsTime(time);
			s.setsLocation(location);
			s.setbNo(bNo);
//			s.setsCatename(catename);
			

		
			
			// selectSList=SELECT * FROM SERVICE WHERE S_CATEGORY=? AND S_TIME=?
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			for(int i = originFiles.size()-1; i >= 0; i--) {
				Image a = new Image();
				a.setiPath(savePath);
				a.setiOrigin(originFiles.get(i));
				a.setiChange(saveFiles.get(i));
				a.setbNo(bNo);
				if(originFiles.size() == 4) {
					switch(i) {
					case 3: a.setiNo(Integer.parseInt(iNo[0]));
							break;
					case 2: if(iNo.length >= 2) {
								a.setiNo(Integer.parseInt(iNo[1]));
							}
							break;
					case 1: if(iNo.length >= 3) {
								a.setiNo(Integer.parseInt(iNo[2]));
							}
							break;
					case 0: if(iNo.length >= 4) {
								a.setiNo(Integer.parseInt(iNo[3]));
							}
							break;
					}
				} else if(originFiles.size() == 3) {
					switch(i) {
					case 2: a.setiNo(Integer.parseInt(iNo[0]));
							break;
					case 1: if(iNo.length >= 2) {
								a.setiNo(Integer.parseInt(iNo[1]));
							}
							break;
					case 0: if(iNo.length >= 3) {
								a.setiNo(Integer.parseInt(iNo[2]));
							}
							break;
					}
				} else if(originFiles.size() == 2) {
					switch(i) {
					case 1: a.setiNo(Integer.parseInt(iNo[0]));
							break;
					case 0: if(iNo.length >= 2) {
								a.setiNo(Integer.parseInt(iNo[1]));
							}
							break;
					}
				} else {
					switch(i) {
					case 0: a.setiNo(Integer.parseInt(iNo[0]));
							break;
					}
				}
			
				if(i == originFiles.size()-1) {
					a.setiLevel(0);
				} else {
					a.setiLevel(1);
				}
				
				fileList.add(a);
			}
			
			int result = new ServiceService().updateService(b, s, fileList);
			
			if(result >= 2) {
				response.sendRedirect("selectServiceDetail.bo?bNo="+bNo);
			} else { 
				for(int i = 0; i < saveFiles.size(); i++) {
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete();
				}
				
				request.setAttribute("msg", "게시글 등록실패");
				request.getRequestDispatcher("WEB-INF/views/common/main.jsp").forward(request, response);
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
