<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, like.model.vo.Likey" %>
 <%
 	Board b= (Board)request.getAttribute("board");
 	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("image");
 	Likey like = (Likey)request.getAttribute("like");
 	ArrayList<BReply> rList = (ArrayList<BReply>)request.getAttribute("rList");
 	ArrayList<Hashtag> hList = (ArrayList<Hashtag>)request.getAttribute("hList");
 %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="css/0_main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<title>게시판 글 상세조회</title>
</head>
<!-- <style type="text/css">
@import "0_main.css";
</style> -->
<body class="board_body">
		<%@ include file="../common/header.jsp" %>
	<main class="board-detail-main">
	<form action="<%= request.getContextPath() %>/updateBoard.go" method="post">
		<div class="board-container">
			<div class="board-title-wrap">
				<div class="board-title-name font_main_title"><%= b.getbTitle() %></div>
				<div><p class="board_createDate"><%= b.getbDate() %></p></div>
			</div>

			<div class="board-content-wrap">
				<div class="board-content-top">
					<div class="board-content-image-box">
						<div class="board-content-image">
						
							<img src="<%= request.getContextPath() %>/board_uploadFiles/<%= iList.get(0).getiChange() %>" class="main_image" id="main_image">
							
						
						<div class="board-content-image2">
							<div class="sub_image_wrap">
								<% for(int i = 0; i < iList.size(); i++) { %>
										<% Image img = iList.get(i); %>
								<div class="sub_image_box">
									<img src="<%= request.getContextPath() %>/board_uploadFiles/<%= img.getiChange()%>" class="sub_image">
									<input type="hidden" name="imgChange" value="<%= img.getiChange() %>">
									<input type="hidden" name="imgOrigin" value="<%= img.getiOrigin() %>">
									<input type="hidden" name="imgNo" value="<%= img.getiNo() %>">
								</div>
									<% } %>
							</div>
						</div>
					</div>
				</div>	
					<div class="board-content-donation">
						<div class="board-content-donation-now">
						<input type="hidden" name="title" value="<%= b.getbTitle() %>">
									<input type="hidden" name="content" value="<%= b.getbContent() %>">
									<input type="hidden" name="bNo" value="<%= b.getbNo() %>">
									
									<div class="board-btn-box">
										<div class="board-btn-wrap" >
										<% if(b.getmNo() == loginUser.getmNo()) { %>
										<input type="submit" value="수정" class="board-btn board-update" >
										<% } %>
										<% if(b.getmNo() == loginUser.getmNo()) { %>
										<input type="button" value="삭제" class="board-btn board-delete" onclick="deleteBoard();">
										<% } %>
										</div>
								</div>

						<div class="board-writer-box">	
							<div class="write_user_box">
								<i class="fas fa-user write_user"></i>
							</div>
							<div class="write_user_info">
								<div class="writer_nick"><%= b.getbWriter() %></div>
								<div class="writer_address"><%= b.getbLocation() %></div>
								<input type="hidden" name="location" value="<%= b.getbLocation() %>">
							</div>
							<div class="write_user_sns_box">
								<div class="insta">
								<% if(b.getbETC() != null){ %>
									<i class="fab fa-instagram user_sns active"></i>
									<input type="hidden" name="etc" value="<%= b.getbETC() %>">
	<%-- 								<a href="https://<%= b.getbETC() %>">dddd</a> --%>
								<% }else{ %>
									<i class="fab fa-instagram user_sns "></i>
								<% } %>
								</div>
								<div class="youtube">
								<% if(b.getbYoutube() != null){ %>
								<i class="fab fa-youtube user_sns active"></i>
								<input type="hidden" name="youtube" value="<%= b.getbYoutube() %>">
								<% }else{ %>
<%-- 								<input type="text" value="<%= b.getbYoutube() %>"> --%>
								<% } %>
								</div>
							</div>
						</div>
							
							<div class="board-goal">
							<div class="board-line"></div>
							<p class="board-now"></p><br>
							<div class="board-content-subcontent godic">
						
								<p class="board_subcontent"><%= b.getbContent() %></p>
								
								<div class="board_tag_box">
								<% for(int k = 0; k < hList.size(); k++) { %>
								<% Hashtag h = hList.get(k); %>
									<% if(h.getbNo() == b.getbNo()) { %>
										<div class="board-title-tag">#<%= h.gettName() %></div>
										<input type="hidden" name="tag" value="<%= h.gettName() %>">
									<% } %>
								<% } %>
								</div>
							</div>
						</div>
							
							<div class="board-line"></div>
								<div class="board-heart-box">
								<% if(like != null){ %>
								<div class="like"><i class="fas fa-heart board_heart"></i></div>
								<% }else{ %>
								<div class="like"><i class="far fa-heart board_heart"></i></div>
								<% } %>
								<i class="far fa-comment board_comment"></i>
								</div>
									<div class="board_likeCount_box">
									<span>좋아요</span>
									<span id="total-like"><%= b.getbLike() %></span>
									<span>개</span>
								</div>
						</div>
					</div>
				</div>			
			</div>
		</div>
		
		</form>
	</main>
	<div class="board_modal_main">
		<div class="board_modal_background"></div>
		<div class="board_modal" id="board_modal">
			<div class="board_modal_container">
				<div class="board_modal_wrap">
					<div class="board_modal_write_user">
						<div class="board_modal_write_user_box">
							<i class="fas fa-user write_user_icon"></i>
						</div>
						<div class="board_modal_write_nick"><%= b.getbWriter() %></div>
						<div class="board_modal_title"><%= b.getbTitle() %></div>
					</div>
					<div class="board_modal_content"><%= b.getbContent() %></div>
					<div class="board_modal_tag">
						<% for(int k = 0; k < hList.size(); k++) { %>
							<% Hashtag h = hList.get(k); %>
								<% if(h.getbNo() == b.getbNo()) { %>
									<div class="board-title-tag">#<%= h.gettName() %></div>
								<% } %>
						<% } %>
					</div>
					
					<div class="board_modal_line"></div>
					
					<!-- 댓글 이거에요 기능작업하실 때 참고하시면 될거같아요 -->
					<div class="board_modal_reply">
						<div id="selectReply">
						<%	for(int i = 0; i < rList.size(); i++){	
									BReply r = rList.get(i); 
									%>
							<div class="reply-box">
								<div class="board_modal_reply_user_wrap">
									<i class="fas fa-user reply_user_icon"></i>
								</div>
								<div class="board_modal_reply_nick"><%=loginUser.getmNick()%></div>
								<div class="board_reply_content" id="selectReplyContent"><%=r.getrContent()%></div>
								<div class="board_reply_date" id="replyDate"><%=r.getrDate()%></div>
							</div>
							<% } %>
					</div>
				</div>	
					
					
				</div>
				<form class="board_modal_inputReply_box">
					<div class="board_modal_reply_user">
						<i class="fas fa-user reply_user"></i>
					</div>
					<div class="board_reply_input_wrap">
						<input type="text" class="reply_input" placeholder="댓글을 입력해주세요..."  id="boardReplyContent">
						<input type= "text" style= "display:none">
						<input type="button" class="board_reply_btn" id="insertReply" value="작성">
					</div>
					<input type="hidden" class="board_reply_submit">
				</form>
			</div>
		</div>
	</div>


	<%@ include file="../common/footer.jsp" %>

	<script>
	console.log($('.ddd').val());
	
	function deleteBoard(){
		var bool = confirm("정말 삭제하시겠습니까?");
			if(bool){
				
				location.href='<%= request.getContextPath() %>/DeleteBoard.bo?bNo=' + <%= b.getbNo() %>;
			}
		
	};

	
	$(document).on('click', '.insta', function(){
			
		location.href='<%=b.getbETC() %>';

	});

	$(document).on('click', '.youtube', function(){
			
		location.href='<%=b.getbYoutube() %>';

	});
		
	
	var mainImage = document.getElementById("main_image");
	var subImage = document.getElementsByClassName("sub_image");
	var right = document.getElementById("right_btn");
	var left = document.getElementById("left_btn");
	
	$(function() {
		$('.board_comment').on('click', function() {
			$('.board_modal_background').addClass('active');
			$('.board_modal').addClass('active');
			$('.board_modal_container').addClass('active');
			$('.board_body').addClass('stop-scroll');
		})
		
		
		$('.board_modal_background').on('click', function() {
			$('.board_modal_background').removeClass('active');
			$('.board_modal').removeClass('active');
			$('.board_modal_container').removeClass('active');
			$('.board_body').removeClass('stop-scroll');
		})
		
	$('.like').on('click', function(){
		var bNo = <%= b.getbNo() %>;
		
		if($(this).children().attr('class') == 'far fa-heart board_heart') {
			$.ajax({
				url: 'updatePlusLike.bo',
				data: {bNo:bNo},
				context: this,
				success: function(data) {
					console.log(data[1]);
					if(data[0] == 1) {		
						$(this).children().attr('class', 'fas fa-heart board_heart');
						$('#total-like').text(data[1]);
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
						$(this).children().attr('class', 'far fa-heart board_heart');
						$('#total-like').text(data[1]);
					}
				}
			});
		}
	});

	replysubmit = function(e){
		var mNo ='<%= loginUser.getmNo() %>';
		var bNo ='<%= b.getbNo() %>';
		var content =$('#boardReplyContent').val();
		
		
	$.ajax({
		
		url:'insertBoardDetailReply.bo',
		data:{mNo:mNo, bNo:bNo, content:content },
		success:function(data){
			console.log(data);
			$('#selectReply').html('');
			
			for(var i in data){
				var reply= data[i];
				var addHtml="";
				
				addHtml += "<div class='reply-box'>";
				addHtml += "<div class='board_modal_reply_user_wrap'>";
				addHtml += "<i class='fas fa-user reply_user_icon'></i>";
				addHtml += "</div>"
				addHtml += "<div class='board_modal_reply_nick'>"+reply.mNick+"</div>";
				addHtml += "<div class='board_reply_content' id='selectReplyContent'>"+reply.rContent+"</div>";
				addHtml += "<div class='board_reply_date' id='replyDate'>"+reply.rDate+"</div>";
				addHtml += "</div>";
				
				$('#selectReply').append(addHtml);
				
			}
			

			$('#boardReplyContent').val('');
		}
		
	});
	
	}
	
	
		$(document).on('click', '#insertReply', function(){
			replysubmit();
		});
		
		$(document).on('keydown', '#boardReplyContent', function(key){
			if(key.keyCode == 13){
				replysubmit();
			}
		});
		
	
	/*  left.onclick = function() {
		for(var i = 1; i < subImage.length; i++) {
			if(mainImage.src == subImage[i].src) {
				mainImage.src = subImage[i-1].src
				break;
			}
		}
	}
	
	right.onclick = function() {
		for(var i = 0; i < subImage.length; i++) {
			if(mainImage.src == subImage[i].src) {
				mainImage.src = subImage[i+1].src
				break;
			}
		}
	}  */
	
	subImage[0].onmouseover = function() {
		mainImage.src = subImage[0].src;
	}
	subImage[1].onmouseover = function() {
		mainImage.src = subImage[1].src;
	}
	subImage[2].onmouseover = function() {
		mainImage.src = subImage[2].src;
	}
	subImage[3].onmouseover = function() {
		mainImage.src = subImage[3].src;
	}
	
	


	

	
	});
	</script>

</body>
</html>