<%@page import="oracle.net.aso.b"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, like.model.vo.*, donation.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("b");
	Donation d = (Donation)request.getAttribute("d");
	ArrayList<Image> IList = (ArrayList<Image>)request.getAttribute("IList");
	ArrayList<DReply> rList = (ArrayList<DReply>)request.getAttribute("rList");
	Likey ly = (Likey)request.getAttribute("ly");
	int totalLike = (int)request.getAttribute("totalLike");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기 상세</title>
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/donation.css">
<link rel="stylesheet" href="css/0_main.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>
<body>
	 <%@ include file="../common/header.jsp" %>
	 
	<main>
			<form action="<%= request.getContextPath() %>/DonationUpdateWrite.go" id="detailForm" method="post">
		<div class="container">
			<div class="donation-title-wrap">
				<input type="hidden" name="hiddenDPay" value="<%= d.getdPay() %>">				<input type="hidden" name="hiddenBNo" id="hiddenBNo" value="<%= b.getbNo()%>">
				<div class="donation-title-tag"><%= d.getdCatename() %></div>
				<input type="hidden" name="hiddenCatename" value="<%= d.getdCatename() %>">
				<div class="donation-title-btn-wrap">
					<div class="donation-title-name font_main_title"><%= b.getbTitle() %></div>
					<input type="hidden" name="hiddenBTitle" value="<%= b.getbTitle() %>">
				</div>
			
			</div>
		</div>
			

			<div class="donation-content-wrap">
				<div class="donation-content-top">
				
					<div class="donation-content-image-box">
						<div class="donation-content-image">
							<img src="<%= request.getContextPath() %>/donation_uploadFiles/<%= IList.get(0).getiChange() %>" alt="" class="main_image" id="main_image">
							
							<div id="left_btn" class="donation_detail_img_btn">
                                <i class="fas fa-chevron-left"></i>
                            </div>
                            
                            <div id="right_btn" class="donation_detail_img_btn">
                                <i class="fas fa-chevron-right"></i>
                            </div>
						</div>
						
							<div class="sub_image_wrap">
								<% for(int z = 0; z < IList.size(); z++){
								Image im = IList.get(z);
								if(b.getbNo() == im.getbNo()){ %>
									<div class="donation-content-image2">
								<div class="sub_image_box">
									<img src=<%= request.getContextPath() %>/donation_uploadFiles/<%= im.getiChange() %> class="sub_image">
					<% } %>
									<input type = "hidden" name = "imgChange" value = "<%= im.getiChange() %>">
									<input type = "hidden" name = "imgOrigin" value = "<%= im.getiOrigin() %>">
									<input type = "hidden" name = "imgNo" value = "<%= im.getiNo() %>">
								</div>
								
							</div>
					<% } %>
						</div>
					</div>
				
					<div class="donation-content-donation">
						<div class="donation-content-donation-now">
						
						<div class="donation_btn_box">
							<div class="donation_btn_wrap">
							
	 							<% if(b.getmNo() == loginUser.getmNo()){ %> 
							<div class="donation_btn donation_update">수정<input type ="submit" class="donation_detail_upde_btn" id="donation_detail_update" value="수정하기"></div>
							<div class="donation_btn donation_delete">삭제<input type ="button" class="donation_detail_upde_btn" id="donation_detail_delete" onclick ="deleteBoard();" value="삭제하기"></div>
								<% } %>
							<script>
								$('.donation_update').on('click', function() {
									$('#donation_detail_update').click();
								})
								
								$('.donation_delete').on('click', function() {
									$('#donation_detail_delete').click();
								})
							</script>
							</div>
						</div>
						
						
							<div class="donation-cotent-like">
								<% if(ly!=null){ %>
										<div class="like"><i class="fas fa-heart donation_heart donation_detail_heart"></i></div>
										<% } else{ %>
										<div class="like"><i class="far fa-heart donation_heart donation_detail_heart"></i></div>
										<% } %>									
									<div class="totalLike" style="font-size:15px;"><%= totalLike %></div>
									</div>
										
							<div class="donation-content-real">
								<p class="donation-now">지금까지</p>
								<span class="donation-totalprice" id="comma"><%= b.getpBtotalpay() %></span> <span class="donation-now">원의</span><br>
								<p class="donation-now">후원금이 모였습니다.</p>
								<div class="donation-goal">
									<p class="donation-nowgoal"><span id="comma2" class="donation_detail_goal_price"><%= d.getdPay() %></span>원 목표</p>
									<input type="hidden" name="hiddenDPeriod" value="<%= d.getdPeriod() %>">
								</div>
								<div class="donation_detail_btn" onclick="goDonationPay();" id="donationPay">후원하기</div>
									
								<div class="donation-line"></div>
								<p class="donation-exp">기부하신 금액은 수수료 없이</p>
								<span class="donation-expemp">100% 전달</span> <span class="donation-exp">됩니다.</span>
							</div>
						</div>
					</div>
				</div>

				<div class="donation-content-middle">
					<div class="donation-content-content">
<!-- 						<div class="donation-content-subtitle"> -->
<!-- 							길고양이들의 더 나은 내일을 위해 도와주세요. -->
<!-- 						</div> -->
						<div class="donation-content-subcontent godic">
							<%= b.getbContent() %>
							<input type="hidden" name="hiddenBContent" value="<%= b.getbContent() %>">
						</div>
					</div>
					<div class="donation-content-plan">
						<div class="donation-content-plan1">
							<div class="donation-content-plan-title">
								<p class='bold600'>우리는 투명하게 이런 곳에 쓰겠습니다!</p>
								<div class="donation-content-plan-table">
									<%= d.getdEtc1() %>
									<input type="hidden" name="hiddenDEtc1" value="<%= d.getdEtc1() %>">
								</div>
							</div>
						</div>
						<div class="donation-content-plan2">
							<div class="donation-content-plan-title">
								<p class='bold600'>우리는 이 친구들에게 도움이 됩니다!</p>
								<div class="donation-content-plan-table">
									<%= d.getdEtc2() %>
									<input type="hidden" name="hiddenDEtc2" value="<%= d.getdEtc2() %>">
								</div>
							</div>
						</div>
					</div>
				</div>
					</form>
					
					<!-- 후원하기form -->
					<form action="<%= request.getContextPath() %>/donationPay.go" method="post">
					<input type="hidden" name="hiddenBNo" id="hiddenBNo" value="<%= b.getbNo()%>">
					<input type="hidden" name="hiddenBTitle" value="<%= b.getbTitle() %>">
					<input type="hidden" name="hiddenDPay" value="<%= d.getdPay() %>">
					<input type="hidden" name="hiddenBWriter" value="<%= b.getbWriter() %>">
					<input type="submit" style="display:none;" class="donationPayGo">
					</form>


					<!-- 댓글  -->
					<div class="donation-content-bottom"> 
					<div class="donation-content-bottom-line"></div>
					<div class="donation-content-bottom-flex">
						<div class="donation_detail_reply_user_box">							
							<i class="fas fa-user donation_detail_reply_user"></i>
						</div>
						<div class="donation_detail_reply_wrap">
							<input type="text" class="donation-content-bottom-input" id="replyContent">
							<input type="button" value="작성" class="donation-content-bottom-submit" id="donation-Reply">
						</div>
					</div>

					<div class="donation-content-bottom" id="replyaaa">
						<%	for(int i = 0; i < rList.size(); i++){	
									DReply r = rList.get(i); 
									%>
							<div class="reply-box">
								<div class="donation-reply-user">					
									<i class="fas fa-user donation_reply_user_icon"></i>
								</div>
								<div class="donation-reply-nick"><%=r.getmNick()%></div>
								<div class="donation-reply-content" id="selectReplyContent"><%=r.getrContent()%></div>
								<div class="donation-reply-date" id="replyDate"><%=r.getrDate()%></div>
								<div class="donation-reply-update"><i class="fas fa-pen"></i></div>
								<div class="donation-reply-delete"><i class="fas fa-trash"></i></div>
							</div>
							<% } %>
					</div>
					</div>	
					
				</div>
			</div>
		</div>

	</main>

 	<%@ include file="../common/footer.jsp" %>

	<script>
	
		window.onload=(function() {
		
			var x = Number($('#comma').text()).toLocaleString();
			var y = Number($('#comma2').text()).toLocaleString();
			$('#comma').text(x);
			$('#comma2').text(y);
	
		});

        // 댓글 부분
        $('#donation-Reply').on('click', function(){
        	
        	var mNo = '<%= loginUser.getmNo() %>';
        	var bNo =  <%= b.getbNo() %>;
        	var content =  $('#replyContent').val();
        		
        	$.ajax({
        		url: 'insertReply.bo',
        		data: {mNo:mNo, bNo:bNo, content:content},
        		success: function(data){
        			
        			
        			$('#replyaaa').html('');
        			
						for(var i = 0; i < data.length; i++){	
							var r = data[i]; 
							var addHtml = "";
					
						addHtml += "<div class='reply-box'>";
						addHtml += "<div class='donation-reply-user'><i class='fas fa-user donation_reply_user_icon'></i></div>";
						addHtml += "<div class='donation-reply-nick'>" + r.mNick + "</div>";
						addHtml += "<div class='donation-reply-content' id='selectReplyContent'>" + r.rContent + "</div>";
						addHtml += "<div class='donation-reply-date' id='replyDate'>" + r.rDate + "</div>";
						addHtml += "<div class='donation-reply-update'><i class='fas fa-pen'></i></div>";
						addHtml += "<div class='donation-reply-delete'><i class='fas fa-trash'></i></div>";
						addHtml += "</div>";
						
						$('#replyaaa').append(addHtml);
						$('#replyContent').val('');
					 			}
        		}
        	});
        });
        
		function deleteBoard(){
			var bool = confirm("게시글을 정말 삭제하시겠어요?");
			
			if(bool){
				$('#detailForm').attr('action', 'deleteDonation.bo');
				$('#detailForm').submit();
			}
		}
		
		 
		 $(function(){
				$('#donationPay').click(function(){
					$('.donationPayGo').click();
				});
			});
		
		// 좋아요부분
		$('.like').on('click', function(){
			var bNo = <%= b.getbNo() %>;
			
			if($(this).children().attr('class') == 'far fa-heart donation_heart donation_detail_heart') {
				$.ajax({
					url: 'updatePlusLike.bo',
					data: {bNo:bNo},
					context: this,
			  		success: function(data) {
						if(data[0] == 1) {		
							$(this).children().attr('class', 'fas fa-heart donation_heart donation_detail_heart');
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
							$(this).children().attr('class', 'far fa-heart donation_heart donation_detail_heart');
							$(this).parent().children().eq(1).text(data[1]);
						}
					}
				});
			}
		});


	  
		var mainImage = document.getElementById("main_image");
		var subImage = document.getElementsByClassName("sub_image");
		var left = document.getElementById("left_btn");
		var right = document.getElementById("right_btn");
		
		
		left.onclick = function() {
          for(var i = 1; i < subImage.length; i++) {
              if(mainImage.src == subImage[i].src) {
                  mainImage.src = subImage[i-1].src
                  break;
              }
          }
      }

      right.onclick = function() {
          for(var i = 0; i < 3; i++) {
              if(mainImage.src == subImage[i].src) {
                  mainImage.src = subImage[i+1].src
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