<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, goods.model.vo.Goods, like.model.vo.Likey" %>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	ArrayList<Goods> gList = (ArrayList<Goods>)request.getAttribute("gList");
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
	ArrayList<Likey> lList = null;
	if(request.getAttribute("lList") != null) {
		lList = (ArrayList<Likey>)request.getAttribute("lList");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/goods.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>
<body>

	<%@ include file="../common/header.jsp" %>
	
	<%
		int mNo = 0;
		if(loginUser != null) {
			mNo = loginUser.getmNo();
		}
	%>
	 
	<div class="dv"></div>
	<div class="main_poster_container">
		<div class="board_main_image_box">
			<img style="width: 100%; height: 100%;" src="image/focus_goods.jpg">
		</div>
	</div>
	
	<main class="goods_main">
		<form class="search_form">
			<input type="text" placeholder="검색어를 입력하세요" class="input_search">
			<input type="submit" value="검색" class="btn_sty">
			<% if(loginUser != null && loginUser.getmAdmin().equals("Y")) { %>
			<input type="button" value="상품 등록" class="btn_sty" style="margin-left: 10px;"onclick="location.href='<%= request.getContextPath() %>/goodsWrite.go'">
			<% } %>
		</form>	
		
		<section class="content_box goods_font" id="content_box">
			<% for(int i = 0; i < bList.size(); i++) { %>
				<% Board b = bList.get(i); Goods g = gList.get(i); %>
				<div class="content">
					<input type="hidden" value="<%= b.getbNo() %>">
					<% for(int j = 0; j < iList.size(); j++) { %>
						<% Image img = iList.get(j); %>
						<% if(b.getbNo() == img.getbNo()) { %>
							<div><img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= img.getiChange() %>" class="img_sty2"></div>
						<% } %>
					<% } %>
					<div class="goods_product_name"><%= b.getbTitle() %></div>
					<div class="goods_price">
						<span><%= g.getgPrice() %>원</span>
						<div class="goods_like">
							<% if(lList == null) { %>
								<div class="like"><i class="far fa-heart goods_heart"></i></div>
							<% } else { %>
								<% for(int k = 0; k < lList.size(); k++) { %>
									<% Likey like = lList.get(k); %>
									<% if(b.getbNo() == like.getbNo()) { %>
										<div class="like"><i class="fas fa-heart goods_heart"></i></div>
									<% break; } %>
									<% if(k == lList.size()-1) { %>
										<div class="like"><i class="far fa-heart goods_heart"></i></div>
									<% } %>
								<% } %>
							<% } %>
							<span class="totalLike"><%= b.getbLike() %></span>
						</div>
					</div>
					<div style="font-size: 14px;"><%= g.getgCompany() %></div>			
				</div>
				<% if(i == 14) break; %>
			<% } %>
		</section>
		<% if(bList.size() > 15) { %>
			<button class="more_btn">더보기</button>
		<% } %>
	</main>
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
		$(function() {
			$('.more_btn').on('click', function(){
				var startNum = $('.content').length + 1;
				
				$.ajax({
					url: 'selectMoreGoods.sl',
					data: {startNum:startNum},
					success: function(data) {
// 						console.log(data["bList"]);
// 						console.log(data["gList"]);
// 						console.log(data["iList"]);
// 						console.log(data["lList"]);
						var bList = data["bList"];
						var gList = data["gList"];
						var iList = data["iList"];
						var lList = data["lList"];
						
						if(bList.length < 15) {
							$('.more_btn').remove();
						}
						
							for(var i = 0; i < bList.length; i++) {
								var b = bList[i];
								var g = gList[i];
								var addHtml = "";
								
								addHtml += "<div class='content'>";
								addHtml += "<input type='hidden', value='" + b.bNo + "'>";
								for(var j = 0; j < iList.length; j++) {
									var img = iList[j];
									if(b.bNo == img.bNo) {
										addHtml += "<div><img src='${pageContext.request.contextPath}/goods_uploadFiles/" + img.iChange + "'" +
										"class='img_sty2'></div>";
									}
								}
								addHtml += "<div class='goods_product_name'><span>" + b.bTitle + "</span></div>";
								addHtml += "<div class='goods_price'>";
								addHtml += "<span>" + g.gPrice + "원</span>";
								addHtml += "<div class='goods_like'>";
									if(lList == null) {
										addHtml += "<div class='like'><i class='far fa-heart goods_heart'></i></div>"
									} else {
										for(var k = 0; k < lList.length; k++) {
											var like = lList[i];
											if(b.bNo == like.bNo) {
												addHtml += "<div class='like'><i class='fas fa-heart goods_heart'></i></div>";
												break;
											}
											if(k == lList.length - 1) {
												addHtml += "<div class='like'><i class='far fa-heart goods_heart'></i></div>";
											}
										}
									}
								addHtml += "<span class='totalLike'>" + b.bLike + "</span>";
								addHtml += "</div>";
								addHtml += "</div>";
								addHtml += "<div style='font-size: 14px;'>" + g.gCompany + "</div>";
								addHtml += "</div>";
								
								$('#content_box').append(addHtml);
							}
						}
					});
			});
		});
		
		
		
		$(document).on('click', '.like', function(e){
			var bNo = $(this).parent().parent().parent().children().eq(0).val();
			
			if(<%= mNo %> == 0) {
				alert("로그인 후 이용해주세요");
				location.href="<%= request.getContextPath() %>/login.go";
			} else {
				if($(this).children().attr('class') == 'far fa-heart goods_heart') {
					$.ajax({
						url: 'updatePlusLike.bo',
						data: {bNo:bNo},
						context: this,
						success: function(data) {
							if(data[0] == 1) {		
								$(this).children().attr('class', 'fas fa-heart goods_heart');
								$(this).parent().children().eq(1).text(data[1]);
							}
						}
					});
				} else {
					$.ajax({
						url: 'updateMinusLike.bo',
						data: {bNo:bNo},
						context: this,
						success: function(data) {
							if(data[0] == 1) {		
								$(this).children().attr('class', 'far fa-heart goods_heart');		
								$(this).parent().children().eq(1).text(data[1]);
							}
						}
					});
				}
			}
			
			e.stopPropagation();
		});
		
		$(document).on('click', '.content', function(){
			var bNo = $(this).children().eq(0).val();
			location.href="<%= request.getContextPath() %>/selectGoodsDetail.sl?bNo=" + bNo;
		});
		
		
	</script>
</body>
</html>

























