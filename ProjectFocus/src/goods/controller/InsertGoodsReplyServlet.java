package goods.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;

import board.model.vo.Board;
import board.model.vo.Image;
import common.MyFileRenamePolicy;
import goods.model.service.GoodsService;
import goods.model.vo.Goods;
import goods.model.vo.Reply;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertGoodsReplyServlet
 */
@WebServlet("/insertGoodsReply.sl")
public class InsertGoodsReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGoodsReplyServlet() {
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
			String savePath = root + "goods_uploadFiles/";
			
//			System.out.println(savePath);
			
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
				String name = files.nextElement();
				
//				System.out.println(multipartRequest.getFilesystemName(name));
				
				if(multipartRequest.getFilesystemName(name) != null) {
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
//			System.out.println(saveFiles.get(0));
//			System.out.println(originFiles.get(0));
			
			String content = multipartRequest.getParameter("content");
			int bNo = Integer.parseInt(multipartRequest.getParameter("bNo"));
			int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			String mNick = ((Member)request.getSession().getAttribute("loginUser")).getmNick();
			Reply r = new Reply(bNo, content, mNo, mNick);
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			
			for(int i = originFiles.size()-1; i >= 0; i--) {
				Image a = new Image();
				a.setiPath(savePath);
				a.setiOrigin(originFiles.get(i));
				a.setiChange(saveFiles.get(i));
				
				fileList.add(a);
			}
			
			GoodsService service = new GoodsService();
			int result = service.insertGoodsReply(r, fileList);
			
			if(result >= 2) {
				ArrayList<Reply> rList = service.selectGoodsReply(bNo);
				ArrayList<Image> iList = service.selectGoodsReplyImg(bNo);
				
				HashMap<String, ArrayList> list = new HashMap<String, ArrayList>();
				list.put("rList", rList);
				list.put("iList", iList);
				
				response.setContentType("application/json; charset=UTF-8");
				GsonBuilder gd = new GsonBuilder();
				GsonBuilder dateGd = gd.setDateFormat("yyyy-MM-dd");
				Gson gson = dateGd.create();
				gson.toJson(list, response.getWriter());
			} else {
				//
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
