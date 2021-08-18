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
import service.model.vo.Reserve;
import service.model.vo.Service;

/**
 * Servlet implementation class InsertServiceServlet
 */
@WebServlet("/insertService.bo")
public class InsertServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServiceServlet() {
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
				
				if(multipartRequest.getFilesystemName(name) != null) { // 여기서 실제 파일 이름을 가져오는 명령어
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
			
			
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			String location = multipartRequest.getParameter("location");
			String time = multipartRequest.getParameter("time");
			String etc = multipartRequest.getParameter("etc");
			String catename = multipartRequest.getParameter("catename");
			int category = Integer.parseInt(multipartRequest.getParameter("category"));
			double xlocation = Double.parseDouble(multipartRequest.getParameter("xlocation"));
			double ylocation = Double.parseDouble(multipartRequest.getParameter("ylocation"));
			String bwriter = ((Member)request.getSession().getAttribute("loginUser")).getmNick();
			
			
			int writer = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			String writerNick = ((Member)request.getSession().getAttribute("loginUser")).getmNick();
			String writerPhone = ((Member)request.getSession().getAttribute("loginUser")).getmPhone();
			
			
		
			Member m = new Member();
			m.setmNick(writerNick);
			m.setmPhone(writerPhone);
			
			
			
			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setmNo(writer);
			b.setbType(3);
			b.setbETC(etc);
			b.setbLocation(location);
			b.setxAddress(xlocation);
			b.setyAddress(ylocation);
			b.setbWriter(bwriter);
			
			
			Service s = new Service();
			s.setbType(3);
			s.setsCategory(category);
			s.setsTime(time);
//			s.setsLocation(location);
			s.setsCatename(catename);
			
			Reserve r = new Reserve();
			r.setrTitle(title);
			r.setrWriter(bwriter);
			r.setrLocation(location);
			r.setrTime(time);
			
			
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			for(int i = originFiles.size()-1; i >= 0; i--) {
				Image a = new Image();
				a.setiPath(savePath);
				a.setiOrigin(originFiles.get(i));
				a.setiChange(saveFiles.get(i));
				
				if(i == originFiles.size()-1) { 
					a.setiLevel(0);
				} else {
					a.setiLevel(1);
				}
				
				fileList.add(a);
			}
			
			int result = new ServiceService().insertService(b, s, r, fileList);
			
			if(result > 2) {
				response.sendRedirect("selectServiceList.bo");
				request.setAttribute("m", m);
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
