<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, board.model.vo.Hashtag, like.model.vo.Likey" %>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList"); 
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
	ArrayList<Hashtag> hList = (ArrayList<Hashtag>)request.getAttribute("hList");
	Board board= (Board)request.getAttribute("board");
	ArrayList<Likey> lList = (ArrayList<Likey>)request.getAttribute("lList");
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

	<div class="main_poster_container">
		<div class="board_main_image_box">
			<img style="width: 100%; height: 100%;" src="image/focus_board.gif">
		</div>
	</div>
	<main>
		<div>
		</div>
		<div class="hashtag_box1">
			<div class="hashtag_span_box">
				<span style="color: #ffc34d; font-size: 35px;">#</span>
				<span class="hashtag_span">해시태그로 빠르게 검색하세요 !</span>
			</div>
			<div class="hashtag_and_search">
				<div class="hashtag_btn_box">
					<input type="button" class="hashtag_btn" value="#댕댕이">
					<input type="button" class="hashtag_btn" value="#산책">
					<input type="button" class="hashtag_btn" value="#동네친구">
					<input type="button" class="hashtag_btn" value="#고양이">
					<input type="button" class="hashtag_btn" value="#나눔">
					<input type="button" class="hashtag_btn" value="#유기견">
				</div>
				<form>
					<input type="text" placeholder="검색어를 입력하세요" class="input_search">
					<input type="submit" value="검색" class="btn_sty">
				</form>
			</div>
		</div>
		<section class="content_box">
			<% for(int i = 0; i< bList.size(); i++) {%>
				<% Board b = bList.get(i);%>
				
				<div class="content">
					<% for(int j =0; j< iList.size(); j++){%>
						<%Image img = iList.get(j); %>
					<% if(b.getbNo() == img.getbNo()){ %>
					<div>
						<img src="<%= request.getContextPath() %>/board_uploadFiles/<%= img.getiChange() %>" class="img_sty1">
					</div>
						<% } %>
					<% } %>
					
					<div class="hashtag_box2">
						<% for(int k = 0; k < hList.size(); k++) { %>
							<% Hashtag h = hList.get(k); %>
							<% if(h.getbNo() == b.getbNo()) { %>
								<span>#<%= h.gettName() %></span>
							<% } %>
						<% } %>
					</div>
					
					<div class= "board_titleLike_box">
						<input type="hidden" value="<%= b.getbNo()%>" id="bNo">
						<span class="gray_font list-address"><%= b.getbLocation() %></span>
						<div class="board_like_box">
						<% if (lList.size() == 0 ){ %>
							<div class="like"><i class="far fa-heart board_heart"></i></div>
						
						<% }else{ %>
						
						<% for(int h = 0; h< lList.size(); h++) {%>
						<% Likey l = lList.get(h); %>
						<% if( b.getbNo() == l.getbNo()){ %>
							<div class="like"><i class="fas fa-heart board_heart"></i></div>
						
						<% break; } %>
						<% if(h == lList.size()-1){ %>
						<div class="like"><i class="far fa-heart board_heart"></i></div>
						<% } %>
						<% } %>
					<% } %>
							<span style="font-size: 16px;"><%= b.getbLike() %></span>
						</div>
					</div>
					<p class="p_sty list-content"><%= b.getbTitle() %></p>
					<span class="gray_font list-date"><%= b.getbDate() %></span>
				
			</div>
			<% if( i == 8) break; %>
			<% } %>
		</section>

		<div class="board-writeBtn-wrap">
			<div class="board-write-btn" onclick="location.href='<%= request.getContextPath() %>/boardWrite.go'">글쓰기</div>
<%-- 			<input type="button" value="글쓰기" class="btn_sty btn" onclick="location.href='<%= request.getContextPath() %>/boardWrite.go'"> --%>
		</div>
		
       	<% if(bList.size() > 9) { %>
			<button class="more_btn">더보기</button>
		<% } %>
	</main>
	
		<%@ include file="../common/footer.jsp" %>
		
	<script>
		$('.like').on('click', function(e){
			var bNo = $(this).parent().parent().children().eq(0).val();
			
			if($(this).children().attr('class') == 'far fa-heart board_heart') {
				$.ajax({
					url: 'updatePlusLike.bo',
					data: {bNo:bNo},
					context: this,
					success: function(data) {
// 						console.log(data[1]);
						if(data[0] == 1) {		
							$(this).children().attr('class', 'fas fa-heart board_heart');
							$(this).parent().children().eq(1).text(data[1]);
						}
					}
				});
					}else {
						$.ajax({
							url: 'updateMinusLike.bo',
							data: {bNo:bNo},
							context: this,
							success: function(data) {
								if(data[0] == 1) {		
									$(this).children().attr('class', 'far fa-heart board_heart');
									$(this).parent().children().eq(1).text(data[1]);
								}
							}
						});
					}
				
					e.stopPropagation();
			});
	
		$(document).on('click', '.hashtag_btn', function(){
			$('.hashtag_btn').removeClass('hover');
			$(this).addClass('hover');
		});
		
		$('.content_box2').click(function(){
			$("#detail").css({"z-index":"10", "display":"block"});
		});
		
		$('#close').click(function(){
			$('#detail').css('display', 'none');
		});
		
		
		$(function(){
			$('.hashtag_btn').on('click', function(){
				$('.content_box').html('');
				var tags = $(this).val();
				var tagName = tags.substr(1, tags.length-1);
				
				$.ajax({
					url: 'selectHashTag.bo',
					data: {tagName:tagName},
					success: function(data) {
						var bList = data["bList"];
						var tList = data["tList"];
						var iList = data["iList"];
						var tagList = data["tagList"];
						
						for(var i = 0; i < bList.length; i++) {
							var b = bList[i];
							var addHtml = "";
							
							for(var j = 0; j < tList.length; j++) {
								var t = tList[j];
								if(b.bNo == t.bNo) {
									addHtml += "<div class='content'>";
									addHtml += "<input type='hidden', value='" + b.bNo + "'>";
									
									for(var k = 0; k < iList.length; k++) {
										var img = iList[k];
										if(b.bNo == img.bNo) {
											addHtml += "<div><img src='${pageContext.request.contextPath}/board_uploadFiles/" + img.iChange + "'" +
											"class='img_sty1'></div>";
										}
									}
									
									addHtml += "<span class='gray_font'>" + b.bLocation  + "</span>";
									addHtml += "<div class='hashtag_box2'>";
									
									for(var s = 0; s < tagList.length; s++) {
										var tag = tagList[s];
										if(b.bNo == tag.bNo) {
											addHtml += "<span>#" + tag.tName + " </span>";
										}
									}
									
									addHtml += "</div>";
									addHtml += "<p class='p_sty'>" + b.bTitle + "</p>";
									addHtml += "<span class='gray_font'>" + b.bDate + "</span>";
									addHtml += "</div>";
									
									$('.content_box').append(addHtml);
								}
							}
						} 
						
					}
				});
				
			});
		});
		
		$(function(){
			$('.more_btn').on('click', function(){
				var startNum = $('.content').length + 1;
				
				$.ajax({
					url: 'SelectMoreBoard.bo',
					data: {startNum:startNum},
					success: function(data) {
						console.log(data["bList"]);
						console.log(data["iList"]);
						console.log(data["lList"]);
							
						var bList = data["bList"];
						var iList = data["iList"];
						var lList = data["lList"];
						
						if(bList.length < 9) {
							$('.more_btn').remove();
						}
						
							for(var i = 0; i < bList.length; i++) {
								var b = bList[i];
								var addHtml = "";
								
								addHtml += "<div class='content'>";
// 								addHtml += "<input type='hidden', value='" + b.bNo + "'>";
								for(var j = 0; j < iList.length; j++) {
									var img = iList[j];
									if(b.bNo == img.bNo) {
										addHtml += "<div><img src='${pageContext.request.contextPath}/board_uploadFiles/" + img.iChange + "'" +
										"class='img_sty1'></div>";
									}
								}
								addHtml += "<div class='hashtag_box2'>";
								addHtml += "<span>#유기견 </span><span>#강아지 </span><span>#도와줘 </span></div>";
							
								addHtml += "<div class='board_titleLike_box'>";
								addHtml += "<input type='hidden' value='" + b.bNo + "' id='bNo'>";
	 							addHtml += "<span class='gray_font list-address'>"+ b.bLocation +"</span>";
								addHtml += "<div class='board_like_box'>";
								
									if(lList.length == 0) {
										addHtml += "<div class='like'><i class='far fa-heart board_heart'></i></div>"
									} else {
										for(var k = 0; k < lList.length; k++) {
											var like = lList[k];
											if(b.bNo == like.bNo) {
												addHtml += "<div class='like'><i class='fas fa-heart board_heart'></i></div>";
												break;
											}
											if(k == lList.length - 1) {
												addHtml += "<div class='like'><i class='far fa-heart board_heart'></i></div>";
											}
										}
									}
								addHtml += "<span style='font-size: 16px;'>" + b.bLike + "</span>";
								addHtml += "</div>";
								addHtml += "</div>";
								addHtml += "<p class='p_sty list-content'>" + b.bTitle + "</p>";
								addHtml += "<span class='gray_font list-date'>" + b.bDate + "</span>";
								addHtml += "</div>";
								$('.content_box').append(addHtml);
									}
								}
							});

						});
					});
		
		
		$(document).on('click', '.content', function(){
			var bNo = $(this).children().eq(2).children().eq(0).val();
			location.href="<%= request.getContextPath() %>/selectBoardDetail.bo?bNo=" + bNo;
		
		
		});
		
	</script>
	
	
</body>
</html>