  package donation.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

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
import donation.model.service.DonationService;
import donation.model.vo.Donation;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateDontaionServlet
 */
@WebServlet("/updateDonation.bo")
public class UpdateDontaionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDontaionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "donation_uploadFiles/";
			
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
				
				if(multipartRequest.getFilesystemName(name) != null) {
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
			
			String dTitle = multipartRequest.getParameter("dTitle");
			int dCategory = Integer.parseInt(multipartRequest.getParameter("dCategory"));
			int dPay = Integer.parseInt(multipartRequest.getParameter("dPay"));
			String dPeriod = multipartRequest.getParameter("dPeriod");
			String dContent = multipartRequest.getParameter("dContent");
			String dEtc1 = multipartRequest.getParameter("dEtc1");
			String dEtc2 = multipartRequest.getParameter("dEtc2");
			int writer = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
			int bNo = Integer.parseInt(multipartRequest.getParameter("hiddenBNo"));
			String[] imgNo = multipartRequest.getParameterValues("imgNo");
			
			Date dat = null;
			if(dPeriod.equals("")) { // 날짜형식으로 바꾸기
				dat = new Date(new GregorianCalendar().getTimeInMillis()); // 오늘날짜 받아주기
			} else { // date값을 넣었을 경우
				String[] dateArr = dPeriod.split("-");
				int year = Integer.parseInt(dateArr[0]);
				int month = Integer.parseInt(dateArr[1])-1;
				int day = Integer.parseInt(dateArr[2]);
				
				dat = new Date(new GregorianCalendar(year, month, day).getTimeInMillis()); 
			}
			
			Board b = new Board();
			b.setbTitle(dTitle);
			b.setbContent(dContent);
			b.setmNo(writer);
			b.setbType(2);
			b.setbNo(bNo);
			
			Donation d = new Donation();
			d.setdCategory(dCategory);
			d.setdPay(dPay);
			d.setdPeriod(dat);
			d.setdEtc1(dEtc1);
			d.setdEtc2(dEtc2);
			d.setbType(2);
			d.setbNo(bNo);
			
			
			ArrayList<Image> fileList = new ArrayList<Image>();
			
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

			int result = new DonationService().updateDonation(b, d, fileList);
			
			if(result >= 2) {
				response.sendRedirect("selectDonationDetail.bo?bNo="+bNo);
			} else {
				for(int i = 0; i < saveFiles.size(); i++) { 
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete();
				}
				
				request.setAttribute("msg", "게시글 등록 실패");
				request.getRequestDispatcher("WEB-INF/views/donation/2_donationUpdate.jsp").forward(request, response);
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
