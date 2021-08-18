package donation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import donation.model.service.DonationService;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class GoodsPayServlet
 */
@WebServlet("/donationKakaoPay.go")
public class GoDonationPayKakaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoDonationPayKakaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String title = request.getParameter("title");
//		String dPay = request.getParameter("dPay");
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		String dPay = request.getParameter("dPay");
		dPay = dPay.replaceAll(",", "");
		
		URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "KakaoAK e82b5c393d303a79b9c7f8ff528f6688");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("cid", "TC0ONETIME");
		params.put("partner_order_id", "partner_order_id");
		params.put("partner_user_id", "partner_user_id");
		params.put("item_name", title);
		params.put("quantity", "1");
		params.put("total_amount", dPay);
		params.put("tax_free_amount", "0");
		params.put("approval_url", "http://localhost:7075/focus/myPageSelect.me");
		params.put("cancel_url", "http://localhost:7075/focus");
		params.put("fail_url", "http://localhost:7075/focus");
		
		String string_params = new String();
			for(Map.Entry<String, String> elem : params.entrySet()) {
				string_params += (elem.getKey() + "=" + elem.getValue() + "&");
			}
			
		OutputStream out = conn.getOutputStream();
		out.write(string_params.getBytes());
		out.flush();
		out.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String successUrl = null;
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(in);
			
			successUrl = (String)obj.get("next_redirect_pc_url");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(successUrl);
//		System.out.println(successUrl);
		if(successUrl != null) {
			int result = new DonationService().insertDonationPay(mNo, bNo, title, dPay);
			
			if(result == 3) {
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
