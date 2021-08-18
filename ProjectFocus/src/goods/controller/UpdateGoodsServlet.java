package goods.controller;

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
import goods.model.service.GoodsService;
import goods.model.vo.Goods;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/updateGoods.sl")
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsServlet() {
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
			
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			int price = Integer.parseInt(multipartRequest.getParameter("price"));
			String company = multipartRequest.getParameter("company");
			int amount = Integer.parseInt(multipartRequest.getParameter("amount"));
			int writer = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			int bNo = Integer.parseInt(multipartRequest.getParameter("bNo"));
			String[] imgNo = multipartRequest.getParameterValues("imgNo"); 
			
			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setmNo(writer);
			b.setbNo(bNo);
			b.setbType(5);
			
			Goods g = new Goods();
			g.setgPrice(price);
			g.setgCompany(company);
			g.setgAmount(amount); 
			g.setbNo(bNo);
			g.setbType(5);
			
//			System.out.println(imgNo[0]);
//			System.out.println(imgNo[1]);
//			System.out.println(imgNo[2]);
//			System.out.println(originFiles.get(2));
//			System.out.println(originFiles.get(1));
//			System.out.println(originFiles.get(0));
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			
			if(files != null) {
				for(int i = originFiles.size()-1; i >= 0; i--) {
					Image a = new Image();
					a.setiPath(savePath);
					a.setiOrigin(originFiles.get(i));
					a.setiChange(saveFiles.get(i));
					a.setbNo(bNo);
					if(originFiles.size() == 4) {
						switch(i) {
						case 3: a.setiNo(Integer.parseInt(imgNo[0]));
								break;
						case 2: if(imgNo.length >= 2) {
									a.setiNo(Integer.parseInt(imgNo[1]));
								}
								break;
						case 1: if(imgNo.length >= 3) {
									a.setiNo(Integer.parseInt(imgNo[2]));
								}
								break;
						case 0: if(imgNo.length >= 4) {
									a.setiNo(Integer.parseInt(imgNo[3]));
								}
								break;
						}
					} else if(originFiles.size() == 3) {
						switch(i) {
						case 2: a.setiNo(Integer.parseInt(imgNo[0]));
								break;
						case 1: if(imgNo.length >= 2) {
									a.setiNo(Integer.parseInt(imgNo[1]));
								}
								break;
						case 0: if(imgNo.length >= 3) {
									a.setiNo(Integer.parseInt(imgNo[2]));
								}
								break;
						}
					} else if(originFiles.size() == 2) {
						switch(i) {
						case 1: a.setiNo(Integer.parseInt(imgNo[0]));
								break;
						case 0: if(imgNo.length >= 2) {
									a.setiNo(Integer.parseInt(imgNo[1]));
								}
								break;
						}
					} else {
						switch(i) {
						case 0: a.setiNo(Integer.parseInt(imgNo[0]));
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
			}
			
			int result = new GoodsService().updateGoods(b, g, fileList);
//			result = 0;
			
			if(result >= 2) {
				response.sendRedirect("selectGoodsDetail.sl?bNo="+bNo);
			} else {
				for(int i = 0; i < saveFiles.size(); i++ ) {
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete();
				}
//				response.sendRedirect("goodsUpdate.go");
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
