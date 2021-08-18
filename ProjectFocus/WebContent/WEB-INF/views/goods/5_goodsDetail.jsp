<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, goods.model.vo.*, like.model.vo.Likey" %>
<%
	Board b = (Board)request.getAttribute("b");
	Goods g = (Goods)request.getAttribute("g");
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
	ArrayList<Reply> rList = (ArrayList<Reply>)request.getAttribute("rList");
	ArrayList<Image> rIList = (ArrayList<Image>)request.getAttribute("rIList");
	Likey like = (Likey)request.getAttribute("like");
	int checkmNo = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기 상세</title>
<link rel="stylesheet" href="css/goods.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body class="goods_detail_body">
	
	<%@ include file="../common/header.jsp" %>
	
	<main class="goodsDetail_main">
		<form action="<%= request.getContextPath() %>/goodsUpdate.go" method="post" id="detailForm">
			<div class="container">
				<% if(loginUser != null) { %>
					<% if(b.getmNo() == loginUser.getmNo()) { %>
					<% checkmNo = 1; %>
						<div class="board-btn-box">
							<div class="board-btn-wrap">
								<input type="submit" value="상품 수정" class="board-btn" id="goods_submit">
								<input type="button" value="상품 삭제" class="board-btn" onclick="deleteBoard();">
							</div>
						</div>
					<% } %>
				<% } %>
				<input type="submit" id="submit" style="display: none;">
				
				<div class="donation-content-wrap">
					<div class="donation-content-top">
						<div class="donation-content-image-box">
							<div class="donation-content-image">
								<img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= iList.get(0).getiChange() %>" alt="" class="main_image" id="main_image">
								
								<div id="left_btn" class="donation_detail_img_btn">
	                                <i class="fas fa-chevron-left"></i>
	                            </div>
	                            
	                            <div id="right_btn" class="donation_detail_img_btn">
	                                <i class="fas fa-chevron-right"></i>
	                            </div>

							</div>
							<div class="donation-content-image2">
								<div class="sub_image_wrap">
									<% for(int i = 0; i < iList.size(); i++) { %>
										<% Image img = iList.get(i); %>
										<% if(img.getiLevel() != 2) { %>
											<div class="sub_image_box">
												<img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= img.getiChange() %>" class="sub_image">
												<input type="hidden" name="imgChange" value="<%= img.getiChange() %>">
												<input type="hidden" name="imgOrigin" value="<%= img.getiOrigin() %>">
												<input type="hidden" name="imgNo" value="<%= img.getiNo() %>">
											</div>
<%-- 											<% if(i == 3) break; %> --%>
										<% } %>
									<% } %>
								</div>
							</div>
						</div>
						<div class="donation-content-donation">
							<div class="donation-content-donation-now">
								<p class="goods_title">
									<%= b.getbTitle() %>
									<input type="hidden" name="title" id="title" value="<%= b.getbTitle() %>">
									<input type="hidden" name="content" value="<%= b.getbContent() %>">
									<input type="hidden" name="price" value="<%= g.getgPrice() %>">
									<input type="hidden" name="amount" value="<%= g.getgAmount() %>">
									<input type="hidden" name="company" value="<%= g.getgCompany() %>">
									<input type="hidden" name="imgSize" value="<%= iList.size() %>">
									<input type="hidden" name="bNo" value="<%= b.getbNo() %>">
									<input type="hidden" name="thumbnailImg" id="thumbnailImg" value="<%= iList.get(0).getiChange() %>">
									<input type="hidden" name="totalPrice" id="totalPrice" value="<%= g.getgPrice() %>">
									<input type="hidden" name="goBag" id="goBag">
								</p>
								<span class="goods_totalprice1"><%= g.getgPrice() %>원</span><br>
								<div class="delivery_box">
									<div class="delivery_img"><i class="fas fa-truck fa-3x"></i></div>
									<div style="padding: 10px;">
										<span class="delivery_text1">오늘출발 상품</span><br>
										<div style="margin-top: 5px;">
											<span class="delivery_time">2:00시 이전 주문시 </span><span class="delivery_text1">오늘 바로 발송됩니다.</span>
										</div>								
									</div>
								</div>
								<div style="margin: 15px 0 3px 0;"><span class="delivery_text1">배송비: 3000원</span><span class="delivery_time">(주문시 결제)</span></div>
								<div><span class="delivery_text2">4개 이상 구매 시 무료배송</span></div>
								<div class="donation-line"></div>
									<div>
										<span class="goods_buy_amount">구매수량 : </span>
										<div style="float:right;">
											<input type="text" class="goods_amount_btn" id="minus_amount" value="-" readonly>
											<input type="text" class="goods_amount" id="goods_amount" name="goods_amount" value=1 readonly>
											<input type="text" class="goods_amount_btn" id="plus_amount" value="+" readonly>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<span class="goods_buy_amount">총 상품 금액 : </span>
										<span class="goods_totalprice2"><%= g.getgPrice() %>원</span>
									</div>
								<div style="border-top: 2px solid lightgray; margin: 30px 0 25px 0;"></div>
								<div style="display: flex; justify-content: space-between;">
									<div class="donation-btn goods_basket_btn" id="bag">장바구니</div>
									<div class="like_btn">
										<% if(like != null) { %>
											<div class="like"><i class="fas fa-heart goods_detail_heart"></i></div>
										<% } else { %>
											<div class="like"><i class="far fa-heart goods_detail_heart"></i></div>
										<% } %>
										<span style="font-size: 20px;">찜하기</span>
									</div>
								</div>
								<div class="donation-btn goods_buy_btn">구매하기</div>

							</div>
						</div>
					</div>
	
					<div class="goods-content-middle">
						<div class="goods_choose_view">
							<a href="#detailImg">
								<div class="goods_choose_view_btn" id="detailImgBtn"><p class="detail_btn">상세정보</p></div>
							</a>
							<div class="goods_choose_view_btn review"><p class="detail_btn">리뷰</p></div>
						</div>
						<table class="goods_product_table">
							<tr>
								<td class="goods_product_tr1">상품번호</td>
								<td class="goods_product_tr2"><%= g.getgNo() %></td>
								<td class="goods_product_tr1">브랜드</td>
								<td class="goods_product_tr2"><%= g.getgCompany() %></td>
							</tr>
							<tr>
								<td class="goods_product_tr1">제조사</td>
								<td class="goods_product_tr2">더마독</td>
								<td class="goods_product_tr1">모델명</td>
								<td class="goods_product_tr2">피부/모질 건강사료 2kg</td>
							</tr>
							<tr>
								<td class="goods_product_tr1">원산지</td>
								<td class="goods_product_tr2">국내산</td>
								<td class="goods_product_tr1">기능</td>
								<td class="goods_product_tr2">영양공급</td>
							</tr>
							<tr>
								<td class="goods_product_tr1">중량</td>
								<td class="goods_product_tr2">2kg</td>
								<td class="goods_product_tr1">상품상태</td>
								<td class="goods_product_tr2">신상품</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="product_detail_img_box" id="detailImg">
				<% for(int i = 0; i < iList.size(); i++) { %>
				<% Image img = iList.get(i); %>
					<% if(img.getiLevel() == 2) { %>
						<img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= img.getiChange() %>" class="product_detail_img">
					<% } %>
				<% } %>
			</div>
			
			<div class="goods_choose_view" style="margin: 20px auto;">
				<a href="#detailImg">
					<div class="goods_choose_view_btn"><p class="detail_btn">상세정보</p></div>
				</a>
				<div class="goods_choose_view_btn review"><p class="detail_btn">리뷰</p></div>
			</div>
		</form>
	</main>
	
	<div class="board_modal_main">
		<div class="board_modal_background"></div>
		<div class="board_modal" id="board_modal">
			<div class="board_modal_container">
				<div class="board_modal_wrap">
					<div>
						<% if(rIList.size() != 0) { %>
							<img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= rIList.get(0).getiChange() %>" class="modal_main_img">
						<% } else {%>
							<img src="" class="modal_main_img">
						<% } %>
					</div>
					<div class="board_modal_reply">
						<div id="selectReply">
						<%	for(int i = 0; i < rList.size(); i++){ %>
						<% Reply reply = rList.get(i); %>	 
							<div class="reply-box">
							<% for(int j = 0; j < rIList.size(); j++) { %>
							<% Image rImage = rIList.get(j); %>
								<% if(reply.getrNo() == rImage.getRNo()) { %>
									<div><img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= rImage.getiChange() %>" class="modal_sub_img"></div>
								<% } %>
							<% } %>
								<div class="reply_content">
									<div style="display: flex;">
										<div class="board_modal_reply_user_wrap">
											<i class="fas fa-user reply_user_icon"></i>
										</div>
										<div class="board_modal_reply_nick"><%= reply.getmNick() %></div>
										<div class="board_reply_date" id="replyDate"><%= reply.getDate() %></div>
									</div>
									<div class="board_reply_content" id="selectReplyContent"><%= reply.getContent() %></div>
								</div>
							</div>
							<% } %>
						</div>
					</div>						
				</div>
				<div class="board_modal_line"></div>
				<form class="board_modal_inputReply_box" action="<%= request.getContextPath() %>/insertGoods.sl" method="post" encType="multipart/form-data">
					<div class="board_reply_input_wrap">
						<input type="text" class="reply_input" placeholder="리뷰를 작성해주세요..."  id="boardReplyContent">
						<input type= "text" style= "display:none">
						<input type="file" style="display: none;">
						<div class="reply_img_box">
			            	<img class="reply_img" id="titleImg">
			            	<p class="reply_img_text" id="text1">이미지 등록</p>
			            	<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this, 1)" style="display: none;">
				        </div>
						<input type="button" class="board_reply_btn" id="insertReply" value="작성">
					</div>
					<input type="hidden" class="board_reply_submit">
				</form>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer.jsp" %>

	<script>
		var mainImage = document.getElementById("main_image");
		var subImage = document.getElementsByClassName("sub_image");
		var right = document.getElementById("right_btn");
		var left = document.getElementById("left_btn");
		var ga = Number($('#goods_amount').val());
		var totalPrice = <%= g.getgPrice() %>;
		
		$(function(){
			if(<%= checkmNo %> == 1) {
				$('.container').attr('style', 'margin-top: 90px');
			}
		});
		
		$('#plus_amount').on('click', function(){
			if(ga < <%= g.getgAmount() %>) {
				ga = ga + 1;			
				totalPrice += <%= g.getgPrice() %>;
				$('.goods_totalprice2').text(totalPrice + "원");
			}
			$('#goods_amount').val(ga);
			$('#totalPrice').val(totalPrice);
		});
		
		$('#minus_amount').on('click', function(){
			if(ga > 1) {		
				ga = ga - 1;
				totalPrice -= <%= g.getgPrice() %>;
				$('.goods_totalprice2').text(totalPrice + "원");
			}
			$('#goods_amount').val(ga);
			$('#totalPrice').val(totalPrice);
		});
		
		function deleteBoard() {
			var bool = confirm('정말 삭제하시겠습니까?');
			
			if(bool) {
				location.href='<%= request.getContextPath() %>/deleteGoods.sl?bNo=' + <%= b.getbNo() %>;
			}
		}
		
		$('#bag').on('click', function(){
			var bool = confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?");
			
			if(bool) {
				$('#detailForm').attr('action', '<%= request.getContextPath() %>/goGoodsBag.sl');
				$('#goBag').val(1);
				$('#submit').click();
			} else {
				$('#detailForm').attr('action', '<%= request.getContextPath() %>/goGoodsBag.sl');
				$('#goBag').val(0);
				$('#submit').click();
			}
		});
		
		$('.like').on('click', function(){
			var bNo = <%= b.getbNo() %>;
			
			if($(this).children().attr('class') == 'far fa-heart goods_detail_heart') {
				$.ajax({
					url: 'updatePlusLike.bo',
					data: {bNo:bNo},
					context: this,
					success: function(data) {
						if(data[0] == 1) {		
							$(this).children().attr('class', 'fas fa-heart goods_detail_heart');
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
							$(this).children().attr('class', 'far fa-heart goods_detail_heart');
						}
					}
				});
			}
		});
		
		replysubmit = function(e){
// 			var mNo = 0;
<%-- 			if(<%= loginUser %> != null) { --%>
			var	mNo ='<%= loginUser.getmNo() %>';
// 			}
			var bNo ='<%= b.getbNo() %>';
			var content =$('#boardReplyContent').val();
			var img = $('#thumbnailImg1')[0].files[0];
			
			var formData = new FormData();
			formData.append("mNo", mNo);
			formData.append("bNo", bNo);
			formData.append("content", content);
			formData.append("img", img);
			
			$.ajax({
				url:'insertGoodsReply.sl',
				data: formData,
				processData: false,
			    contentType: false,
			    method: 'post',
				success:function(data){
					var rList = data["rList"];
					var iList = data["iList"];
					
					$('#selectReply').html('');
					
					for(var i = 0; i < rList.length; i++){
						var reply= rList[i];
						var addHtml="";
						
						addHtml += "<div class='reply-box'>";
						for(var j = 0; j < iList.length; j++) {
							var img = iList[j];
							if(reply.rNo == img.rNo) {
								addHtml += "<div><img src='${pageContext.request.contextPath}/goods_uploadFiles/" + img.iChange + "'" +
								"class='modal_sub_img'></div>";
							}
						}
						addHtml += "<div class='reply_content'>";
						addHtml += "<div style='display: flex;'>";
						addHtml += "<div class='board_modal_reply_user_wrap'>";
						addHtml += "<i class='fas fa-user reply_user_icon'></i>";
						addHtml += "</div>"
						addHtml += "<div class='board_modal_reply_nick'>"+reply.mNick+"</div>";
						addHtml += "<div class='board_reply_date' id='replyDate'>"+reply.date+"</div>";
						addHtml += "</div>"
						addHtml += "<div class='board_reply_content' id='selectReplyContent'>"+reply.content+"</div>";
						addHtml += "</div>";
						addHtml += "</div>";
						
						$('#selectReply').append(addHtml);
					}
					$('#boardReplyContent').val('');
					$('#titleImg').removeAttr('src');				
				}
			});
		}
			
		$(document).on('click', '#insertReply', function() {
			replysubmit();
		});

		$(document).on('keydown', '#boardReplyContent', function(key) {
			if (key.keyCode == 13) {
				replysubmit();
			}
		});
		
		$(function() {
			$('.review').on('click', function() {
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
			
//	 		$('.board_reply_btn').on('click', function() {
//	 			$('.board_reply_submit').click();
//	 		})
		
		});
		
		$(function() {
        	$('#titleImg').click(function(){
        		$('#thumbnailImg1').click();
        	});
        });
        
        function LoadImg(value, num) {
        	if(value.files && value.files[0]) {
        		var reader = new FileReader();
        		
        		reader.onload = function(e) {
        			switch(num) {
        			case 1:
        				$('#titleImg').attr('src', e.target.result);
        				$('#text1').hide();
        				break;
        			}
        		}
        		reader.readAsDataURL(value.files[0]);
        	}
        }
        
        $(document).on('click', '.reply-box', function(){
        	var src = $(this).children().eq(0).children().attr('src');
        	$('.modal_main_img').attr('src', src);
        });

		left.onclick = function() {
			for (var i = 1; i < subImage.length; i++) {
				if (mainImage.src == subImage[i].src) {
					mainImage.src = subImage[i - 1].src
					break;
				}
			}
		}

		right.onclick = function() {
			for (var i = 0; i < subImage.length; i++) {
				if (mainImage.src == subImage[i].src) {
					mainImage.src = subImage[i + 1].src
					break;
				}
			}
		}

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
	</script>
</body>
</html>  