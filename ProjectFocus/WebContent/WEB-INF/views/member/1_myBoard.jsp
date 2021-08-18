<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*" %>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/board.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>

	<main>
		<section class="my_content_box">
			<% for(int i = 0; i< bList.size(); i++) {%>
				<% Board b = bList.get(i);%>
				
				<div class="content">
					<input type="hidden" value="<%= b.getbNo()%>" id="bNo">
					<% for(int j =0; j< iList.size(); j++){%>
						<%Image img = iList.get(j); %>
						<% if(b.getbNo() == img.getbNo()){ %>
							<% if(b.getbType() == 2) { %>
								<div>
									<img src="<%= request.getContextPath() %>/donation_uploadFiles/<%= img.getiChange() %>" class="img_sty1">
								</div>												
							<% } else if(b.getbType() == 3) { %>
								<div>
									<img src="<%= request.getContextPath() %>/service_uploadFiles/<%= img.getiChange() %>" class="img_sty1">
								</div>
							<% } else if(b.getbType() == 4) { %>
								<div>
									<img src="<%= request.getContextPath() %>/board_uploadFiles/<%= img.getiChange() %>" class="img_sty1">
								</div>						
							<% } else { %>
								<div>
									<img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= img.getiChange() %>" class="img_sty1">
								</div>												
							<% } %>
						<% } %>
					<% } %>
					
					<p class="p_sty my-list-content"><%= b.getbTitle() %></p>
					<span class="gray_font my-list-date"><%= b.getbDate() %></span>
					<% if(b.getbType() == 2) { %>
						<span class="gray_font my-list-date" style="float: right;">봉사하기</span>
					<% } else if(b.getbType() == 3) { %>
						<span class="gray_font my-list-date" style="float: right;">후원하기</span>
					<% } else if(b.getbType() == 4) { %>
						<span class="gray_font my-list-date" style="float: right;">자유게시판</span>
					<% } else { %>
						<span class="gray_font my-list-date" style="float: right;">굿즈</span>
					<% } %>
				</div>
			<% } %>
		</section>

	</main>
	
		<%@ include file="../common/footer.jsp" %>
		
	<script>
		$(document).on('click', '.content', function(){
			var bNo = $(this).children().eq(0).val();
			location.href="<%= request.getContextPath() %>/selectBoardDetail.bo?bNo=" + bNo;
		});
	</script>
	
	
</body>
</html>