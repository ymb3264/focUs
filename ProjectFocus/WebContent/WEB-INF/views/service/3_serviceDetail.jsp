<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, member.model.vo.Member, service.model.vo.Service, like.model.vo.Likey, service.model.vo.Reply"%>
<!DOCTYPE html>
<% 
	Board b = (Board)request.getAttribute("b");
	Service s = (Service)request.getAttribute("s");
	Member m = (Member)request.getAttribute("m");
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
// 	ArrayList<Reply> rList = (ArrayList<Reply>)request.getAttribute("rList");
	Image titleImg = iList.get(0);
	Likey like = (Likey)request.getAttribute("like"); 
%>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기 상세</title>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<link rel="stylesheet" href="css/service.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">

</head>
<body class="serviceDetail_body">
	
	<%@ include file="../common/header.jsp" %>

	<main>
	<form action="<%= request.getContextPath() %>/serviceUpdate.go" method="post">
	<input type="hidden" name="bNo" id="bNo" value="<%= b.getbNo()%>">
	<input type="hidden" name="title" value="<%= b.getbTitle() %>">
 	<input type="hidden" name="content" value="<%= b.getbContent() %>">
 	<input type="hidden" name="time" value="<%= s.getsTime() %>">
	<input type="hidden" name="imgSize" value="<%= iList.size() %>">
	<input type="hidden" name="etc" id="note" value="<%= b.getbETC() %>">
	<input type="hidden" name="location" value="<%= s.getsLocation() %>">
	<input type="hidden" name="category" value="<%= s.getsCategory() %>">
	<input type="hidden" name="catename" value="<%= s.getsCatename() %>">
	<input type="hidden" name="xlocation" value="<%=b.getxAddress()%>">
	<input type="hidden" name="ylocation" value="<%=b.getyAddress()%>">
	<input type="hidden" name="mNick" value="<%=b.getmNick()%>">
	<input type="hidden" name="mPhone" value="<%=b.getmPhone()%>">
	<input type="hidden" name="writer" value="<%=b.getbWriter()%>">
	
	
		<div class="container">
		
			<div class="donation-title-wrap">
			
				<div class="donation-title-tag" name="catename"><%= s.getsCatename() %></div>
				
				<div class="donation-title-name font_main_title" name="title"><%= b.getbTitle() %></div>
			
			</div>

			<div class="donation-content-wrap">
				<div class="donation-content-top">
					<div class="donation-content-image-box">
						<div class="donation-content-image">
							<img src="<%= request.getContextPath() %>/service_uploadFiles/<%= iList.get(0).getiChange() %>" alt="" class="main_image" id="main_image">
							<div class="donation_detail_img_btn" id="left_btn">
                                    <i class="fas fa-chevron-left"></i>
                                </div>
                                <div class="donation_detail_img_btn" id="right_btn">
                                    <i class="fas fa-chevron-right"></i>
                              </div>
						</div>
						<div class="donation-content-image2">
							<div class="sub_image_wrap">
							<% for(int z = 0; z < iList.size(); z++){
									Image i = iList.get(z);
									if(b.getbNo() == i.getbNo()){ %>
								<div class="sub_image_box">
						
									<img src="<%= request.getContextPath() %>/service_uploadFiles/<%= i.getiChange() %>" class="sub_image">
								<% } %>
									<input type = "hidden" name = "iChange" value = "<%= i.getiChange() %>">
                                    <input type = "hidden" name = "iOrigin" value = "<%= i.getiOrigin() %>">
                                    <input type = "hidden" name = "iNo" value = "<%= i.getiNo() %>">
								
								</div>
							
							<% } %>
							</div>
		
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
						
								<div class="service_content_like">
									<% if(like != null) { %>
									<div class ="like"><i class="fas fa-heart service_heart"></i></div>
									<% } else { %>
									<div class ="like"><i class="far fa-heart service_heart"></i></div>
									<% } %>
									<span style ="font-size: 15px;"><%= b.getbLike() %></span>
								</div>
								<div class="service_content_comment">
									<div class="service_content_comment_wrap">
										<span class="service_content_comment_title">작성자</span>
										<span class="service_content_comment_value"><%= s.getmNick() %></span>
									</div>
									<div class="service_content_comment_wrap">
										<div class="service_content_comment_title">봉사시간</div>
										<div class="service_content_comment_value"><%= s.getsTime() %></div>
									</div>
									<div class="service_content_comment_wrap">
										<div class="service_content_comment_title">봉사장소</div>
										<div class="service_content_comment_value"><%= s.getsLocation() %></div>
									</div>
									<div class="service_content_comment_wrap">
										<div class="service_content_comment_title">특이사항</div>
										<div class="service_content_comment_value">
											<% if(b.getbETC() != null) { %>
												<%= b.getbETC() %>
											<% } else { %>
												-
											<% } %>
										</div>
									</div>
								</div>
<!-- 								<table class="table1"> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">작성일자</th> -->
<%-- 									<td class="td1"><%= b.getbDate()%></td> --%>
<!-- 								</tr> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">작성자</th> -->
<%-- 									<td class="td1"><%= s.getmNick() %></td> --%>
<!-- 								</tr> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">봉사시간</th> -->
<%-- 									<td class="td1" name="time"><%= s.getsTime() %></td> --%>
<!-- 								</tr> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">카테고리</th> -->
<%-- 									<td class="td1" name="catename"><%= s.getsCatename() %></td> --%>
<!-- 								</tr> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">봉사장소</th> -->
<%-- 									<td class="td1" name="location"><%= s.getsLocation() %></td> --%>
<!-- 								</tr> -->
<!-- 								<tr class="tr1"> -->
<!-- 									<th class="th1">특이사항</th> -->
<%-- 									<td class="td1" name="etc" id="note"><%= b.getbETC() %></td> --%>
<!-- 								</tr> -->
<!-- 							</table> -->
								<div class="donation-line"></div>
								<div class="donation-btn">예약하기</div>
							</div>
							
						</div>
						
					</div>
			
				<div class="donation-content-middle">
					<div class="donation-content-content">
						<div class="donation-content-subtitle">
							<%= b.getbTitle() %>
						</div>
						<div class="donation-content-subcontent godic">
							<%= b.getbContent()%>
						</div>
						
						<div id="service_location">
							<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00"></script>
						   <script>
							   var container = document.getElementById('service_location');
							   var options = {
								   center: new kakao.maps.LatLng(<%=b.getxAddress()%>, <%=b.getyAddress()%>),
								   level: 3
							   };
							   var service_location = new kakao.maps.Map(container, options);
							   
							   var markerPosition  = new kakao.maps.LatLng(<%=b.getxAddress()%>, <%=b.getyAddress()%>);

								var marker = new kakao.maps.Marker({
								    position: markerPosition
								});
								
								marker.setMap(service_location);
						    </script>
						    	
						   </div>
						  
					</div>
				
				</div>
			</div>
			</div>
			</form>
			
			<form action="<%= request.getContextPath() %>/serviceReserve.go" method="post">
				<input type="hidden" name="bNo" id="bNo" value="<%= b.getbNo() %>">
				<input type="hidden" name="title" value="<%= b.getbTitle() %>">
			 	<input type="hidden" name="content" value="<%= b.getbContent() %>">
			 	<input type="hidden" name="time" value="<%= s.getsTime() %>">
				<input type="hidden" name="imgSize" value="<%= iList.size() %>"><!--  -->
				<input type="hidden" name="etc" id="note" value="<%= b.getbETC() %>"><!--  -->
				<input type="hidden" name="location" value="<%= s.getsLocation() %>">
				<input type="hidden" name="category" value="<%= s.getsCategory() %>"> <!--  -->
				<input type="hidden" name="catename" value="<%= s.getsCatename() %>">
				<input type="hidden" name="xlocation" value="<%=b.getyAddress() %>"><!--  -->
				<input type="hidden" name="ylocation" value="<%=b.getxAddress() %>"><!--  -->
				<input type="hidden" name="sNo" value="<%= s.getsNo() %>">
				<input type="hidden" name="mNick" value="<%= b.getmNick() %>">
				<input type="hidden" name="mPhone" value="<%= b.getmPhone() %>">
				<input type="hidden" name="writer" value="<%= b.getbWriter() %>">
				<input type="submit" class="reserveBtn">
			</form>
	</main>
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
	
	var mainImage = document.getElementById("main_image");
	var subImage = document.getElementsByClassName("sub_image");
    var right = document.getElementById("right_btn");
    var left = document.getElementById("left_btn");


    <%-- function deleteBoard() {
        var bool = confirm('정말 삭제하시겠습니까?');

        if(bool) {
            location.href='<%= request.getContextPath() %>/deleteGoods.sl?bNo=' + <%= b.getbNo() %>;
        }
    } --%>
    

    
    $('.like').on('click', function(){
		var bNo = <%= b.getbNo() %>;
		
		if($(this).children().attr('class') == 'far fa-heart service_heart') {
			$.ajax({
				url: 'updatePlusLike.bo',
				data: {bNo:bNo},
				context: this,
				success: function(data) {
					if(data[0] == 1) {		
						$(this).children().attr('class', 'fas fa-heart service_heart');
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
						$(this).children().attr('class', 'far fa-heart service_heart');
						$(this).parent().children().eq(1).text(data[1]);
					}
				}
			});
		}
	});

    left.onclick = function() {
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
    }

    subImage[0].onclick = function() {
        mainImage.src = subImage[0].src;
    }
    subImage[1].onclick = function() {
        mainImage.src = subImage[1].src;
    }
    subImage[2].onclick = function() {
        mainImage.src = subImage[2].src;
    }
    subImage[3].onclick = function() {
        mainImage.src = subImage[3].src;
    }
	

	</script>
	
	<script>
		$('#donation_detail_delete').on('click',function(){
			var bool = confirm('정말 삭제하시겠습니까?');
			
			if(bool){
				location.href='<%= request.getContextPath()%>/deleteService.bo?bNo=' + <%= b.getbNo()%>;
			}
		});
		
		$('.donation-btn').on('click', function() {
			$('.reserveBtn').click();
		})
	</script>
	
	
	
	
</body>
</html> 