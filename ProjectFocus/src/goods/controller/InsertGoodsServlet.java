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
 * Servlet implementation class InsertImageServlet
 */
@WebServlet("/insertGoods.sl")
public class InsertGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGoodsServlet() {
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
			ArrayList<String> saveFiles2 = new ArrayList<String>();  
			ArrayList<String> originFiles2 = new ArrayList<String>();  
			Enumeration<String> files = multipartRequest.getFileNames();
			
			int num = 0;
			while(files.hasMoreElements()) {
				num++;
				String name = files.nextElement();
				
//				System.out.println(multipartRequest.getFilesystemName(name));
				
				if(num == 1 || num == 2) {
					if(multipartRequest.getFilesystemName(name) != null) {
						saveFiles2.add(multipartRequest.getFilesystemName(name));
						originFiles2.add(multipartRequest.getOriginalFileName(name));
					}
				} else {
					if(multipartRequest.getFilesystemName(name) != null) {
						saveFiles.add(multipartRequest.getFilesystemName(name));
						originFiles.add(multipartRequest.getOriginalFileName(name));
					}
				}
				
			}
//			System.out.println(saveFiles.get(0));
//			System.out.println(saveFiles.get(1));
//			System.out.println(saveFiles.get(2));
//			System.out.println(originFiles.get(0));  // 2-2
//			System.out.println(originFiles.get(1));  // 2-1
//			System.out.println(originFiles.get(2));  // 1-2
//			System.out.println(originFiles.get(3));  // 1-1
//			System.out.println(originFiles2.get(0)); // 3-1
			
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			int price = Integer.parseInt(multipartRequest.getParameter("price"));
			String company = multipartRequest.getParameter("company");
			int amount = Integer.parseInt(multipartRequest.getParameter("amount"));
			int writer = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			
			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setmNo(writer);
			b.setbType(5);
			
			Goods g = new Goods();
			g.setgPrice(price);
			g.setgCompany(company);
			g.setgAmount(amount); 
			g.setbType(5);
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			ArrayList<Image> fileList2 = new ArrayList<Image>();
			
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
			
			for(int i = originFiles2.size()-1; i >= 0; i--) {
				Image a = new Image();
				a.setiPath(savePath);
				a.setiOrigin(originFiles2.get(i));
				a.setiChange(saveFiles2.get(i));
				a.setiLevel(2);
				
				fileList2.add(a);
			}
			
			int result = new GoodsService().insertGoods(b, g, fileList, fileList2);
			
			if(result >= 3) {
				response.sendRedirect("selectGoodsList.sl");
			} else {
				for(int i = 0; i < saveFiles.size(); i++ ) {
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete();
				}
				request.setAttribute("msg", "게시글 등록실패");
				request.getRequestDispatcher("WEB-INF/views/goods/5_goodsWrite.jsp").forward(request, response);
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
