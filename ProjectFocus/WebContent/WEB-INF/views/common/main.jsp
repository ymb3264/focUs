<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.*, goods.model.vo.*, java.util.ArrayList, like.model.vo.Likey" %>
<%
	ArrayList<Board> gBList = (ArrayList<Board>)request.getAttribute("gBList");
	ArrayList<Goods> gList = (ArrayList<Goods>)request.getAttribute("gList");
	ArrayList<Image> gIList = (ArrayList<Image>)request.getAttribute("gIList");
	ArrayList<Image> bIList = (ArrayList<Image>)request.getAttribute("bIList");
	ArrayList<Likey> lList = (ArrayList<Likey>)request.getAttribute("lList");
	ArrayList<Board> serviceBList = (ArrayList<Board>)request.getAttribute("serviceBList");
	
	Board board = (Board)request.getAttribute("board");
	
	double userX = 0;
	double userY = 0;
	if(session.getAttribute("userX") != null && session.getAttribute("userY") != null) {
		userX = (Double)session.getAttribute("userX");	
		userY = (Double)session.getAttribute("userY");	
	}
	
	int check = 0;
	if(request.getAttribute("check") != null) {
		check = (int)request.getAttribute("check");		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>focUs</title>
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00&libraries=services,clusterer,drawing"></script>
    <script>
    	var lat = 0;
    	var lon = 0;
    	
	    if(navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					lat = position.coords.latitude.toString();
					lon = position.coords.longitude.toString();
					
					$('#user_XLocation').val(lat.substr(0, 8));
					$('#user_YLocation').val(lon.substr(0, 8));
				});
			}
	    
	    $(function() {
    		if(<%= check %> == 0) {
    			lat = lat.substr(0, 8);
    			lon = lon.substr(0, 8);
    			
    			location.href="<%= request.getContextPath() %>/selectMain.bo?lat=" + lat + "&lon=" + lon;     			
   		    }
    	});
    </script>
</head>
<body>

	<%@ include file="header.jsp" %>
	
    <main>
    	<input type="hidden" id="user_XLocation" name="user_XLocation">
		<input type="hidden" id="user_YLocation" name="user_YLocation">
        <section class="main_section section1">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide main_slide1"><img style="width: 100%; height: 100%;" src="image/focus_main1.gif"></div>
                    <div class="swiper-slide main_slide2"><img style="width: 100%; height: 100%;" src="image/focus_main3.gif"></div>
                    <div class="swiper-slide main_slide3"><img style="width: 100%; height: 100%;" src="image/focus_main2.gif"></div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
                <div class="swiper-pagination"></div>
            </div>
        </section>
        
        <!-- 후원 -->
        <section class="main_section section2">
            <div class="section_container">
                <div class="sec2_img_box"><img style="width: 100%; height: 100%;" src="image/focus_main4.jpg"></div>
                <div class="sec2_text_box">
                    <div class="sec2_text_wrap">
                        <div class="sec2_text"><span class="sec2_point">당신</span>의 <span class="sec2_point">참여</span>가<br> 변화의 시작입니다.</div>
                        <div class="sec2_sub_text">오늘 <span class="bold_text">7,295</span>명이 <span class="bold_text">20,300,400원</span>을 기부하였습니다.</div>
                        <div class="sec2_btn_wrap">
                            <div class="sec2_btn sec2_btn1">기부소개</div>
                            <div class="sec2_btn sec2_btn2">진행중인 모금함</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 봉사 -->
        <section class="main_section section3">
<!--             <div class="section_container"> -->
            <div class="sec4_text_main">내 주위에 도움이 필요한 장소를 확인해보세요!</div>
			<div id="service_location">
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e3a65ece7f1f2b32cca4fd52d3a3b00"></script>
				<script>
					var container = document.getElementById('service_location');
					var options = {
						center: new kakao.maps.LatLng(<%= userX %>, <%= userY %>),
						level: 6
					};
					var service_location = new kakao.maps.Map(container, options);
					
					// 마커부분
					
					var markerPosition = null;
					var marker = null;
					<% if(serviceBList != null) { %>
						<% for(int i = 0; i < serviceBList.size(); i++) { %>
							<% Board b = serviceBList.get(i); %>
							<% if(i == 0) { %>
								var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',    
							    imageSize = new kakao.maps.Size(64, 69), 
							    imageOption = {offset: new kakao.maps.Point(27, 69)};
							
								var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
								markerPosition  = new kakao.maps.LatLng(<%= userX %>, <%= userY %>);
								
								marker = new kakao.maps.Marker({
								 	position: markerPosition,
								 	image: markerImage 
								});
								
								marker.setMap(service_location);
							<% } %>
							markerPosition  = new kakao.maps.LatLng(<%=b.getxAddress()%>, <%=b.getyAddress()%>);
							marker = new kakao.maps.Marker({
							 	position: markerPosition
							});
							
							marker.setMap(service_location);
						<% } %>
					<% } %>
 				</script>
			</div>
			<!--             </div> -->
        </section>

        <!-- 게시판 -->
        <section class="main_section section4">
            <div class="section_container">
                <div class="sec4_img_box">
<!-- 						<div class="content"> -->
<%-- 						<% if(bIList != null && board != null) { %> --%>
<%-- 							<% for(int j =0; j< bIList.size(); j++){%> --%>
<%-- 								<%Image img = bIList.get(j); %> --%>
<%-- 							<% if(board.getbNo() == img.getbNo()){ %> --%>
<!-- 							<div> -->
<%-- 								<img src="<%= request.getContextPath() %>/board_uploadFiles/<%= img.getiChange() %>" class="img_sty1"> --%>
<!-- 							</div> -->
<%-- 								<% } %> --%>
<%-- 							<% } %> --%>
<!-- 							<div class="hashtag_box2"> -->
<%-- 								<% for(int k = 0; k < hList.size(); k++) { %> --%>
<%-- 									<% Hashtag h = hList.get(k); %> --%>
<%-- 									<% if(h.getbNo() == b.getbNo()) { %> --%>
<%-- 										<span>#<%= h.gettName() %></span> --%>
<%-- 									<% } %> --%>
<%-- 								<% } %> --%>
<!-- 							</div> -->
							
<!-- 							<div class= "board_titleLike_box"> -->
<%-- 								<input type="hidden" value="<%= board.getbNo()%>" id="bNo"> --%>
<%-- 								<span class="gray_font list-address"><%= board.getbLocation() %></span> --%>
<!-- 								<div class="board_like_box"> -->
<%-- 								<% if (lList == null || lList.size() == 0){ %> --%>
<!-- 									<div class="like"><i class="far fa-heart board_heart"></i></div> -->
<%-- 								<% }else{ %> --%>
								
<%-- 								<% for(int h = 0; h< lList.size(); h++) {%> --%>
<%-- 								<% Likey l = lList.get(h); %> --%>
<%-- 									<% if( board.getbNo() == l.getbNo()){ %> --%>
<!-- 										<div class="like"><i class="fas fa-heart board_heart"></i></div> -->
<%-- 									<% break; } %> --%>
<%-- 									<% if(h == lList.size()-1){ %> --%>
<!-- 										<div class="like"><i class="far fa-heart board_heart"></i></div> -->
<%-- 									<% } %> --%>
<%-- 									<% } %> --%>
<%-- 								<% } %> --%>
<%-- 									<span style="font-size: 25px;"><%= board.getbLike() %></span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 							<p class="p_sty list-content"><%= board.getbTitle() %></p> --%>
<%-- 							<span class="gray_font list-date"><%= board.getbDate() %></span> --%>
<%-- 							<% } %> --%>
								<img style="width: 100%; height: 100%;" src="image/focus_main5.gif">
<!-- 						</div> -->
                </div>
                <div class="sec4_text_box">
                    <div class="sec4_text_wrap">
                        <div class="sec4_text">이웃과 함께 하는<br>동네 생활</div>
                        <div class="sec4_sub_text">우리 동네의 다양한 이야기를 이웃과 함께 나누어요.</div>
                        <div class="sec4_icon_wrap">
                            <div class="sec4_icon_set">
                                <div class="sec4_icon sec4_icon1"><i class="fas fa-home"></i></div>
                                <div class="sec4_icon_title">우리동네질문</div>
                                <div class="sec4_icon_comment">궁금한 게 있을 땐<br>이웃에게 물어보세요.</div>
                            </div>
                            <div class="sec4_icon_set">
                                <div class="sec4_icon sec4_icon2"><i class="fas fa-scroll"></i></div>
                                <div class="sec4_icon_title">동네분실센터</div>
                                <div class="sec4_icon_comment">무언가 잃어버렸을 때<br>함께 찾아요.</div>
                            </div>
                            <div class="sec4_icon_set">
                                <div class="sec4_icon sec4_icon3"><i class="fas fa-comments"></i></div>
                                <div class="sec4_icon_title">동네모임</div>
                                <div class="sec4_icon_comment">관심사가 비슷한<br>이웃과 만나요.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 굿즈 -->
        <section class="main_section section5">
            <div class="section_container section_container5">
                <div class="sec5_title">우리가 만든 굿즈</div>
                <div class="sec5_goods_container">
                <% if(gBList != null) { %>
                	<% for(int i = 0; i < gBList.size(); i++) { %>
                	<% Board b = gBList.get(i); Goods g = gList.get(i); %>
	                    <div class="sec5_goods_box">
	                    	<% for(int j = 0; j < gIList.size(); j++) { %>
	                    	<% Image img = gIList.get(j); %>
	                    		<% if(b.getbNo() == img.getbNo()) { %>
			                        <div class="sec5_goods_img">
			                        	<img style="width: 100%; height: 186px; border-radius: 10px;" 
			                        	src="<%= request.getContextPath() %>/goods_uploadFiles/<%= img.getiChange() %>">
			                        </div>
		                        <% } %>
	                        <% } %>
	                        <div class="sec5_goods_title"><%= b.getbTitle() %></div>
	                        <div class="sec5_goods_price"><%= g.getgPrice() %></div>
	                        <div class="sec5_goods_brand"><%= g.getgCompany() %></div>
	                        <div class="sec5_goods_like">관심 123</div>
	                    </div>
	                    <% if(i == 9) break; %>
                    <% } %>
                <% } %>
                </div>
            </div>
        </section>
    </main>
    
    <%@ include file="footer.jsp" %>
     <script>
//      	window.onload = function() {
<%--      		if(<%= check %> == 0) { --%>
<%-- 	     		location.href="<%= request.getContextPath() %>/selectMain.bo";     			 --%>
//      		}
//      	}

        window.addEventListener('scroll', function() {
            var scrollY = window.scrollY;
            var sec2Img = document.querySelector('.sec2_img_box');
            var sec2Text = document.querySelector('.sec2_text_wrap');
            var sec4Img = document.querySelector('.sec4_img_box');
            var sec4Text = document.querySelector('.sec4_text_wrap');
            var sec5Title = document.querySelector('.sec5_title');
            var sec5Goods = document.querySelector('.sec5_goods_container');

//             console.log(scrollY);
            
            if (scrollY > 400) {
                sec2Text.style.transform = 'translate(-50%, -50%)';
                sec2Text.style.opacity = '1';
            } else {
                sec2Text.style.transform = 'translate(-50%, 0)';
                sec2Text.style.opacity = '0';
            }

            if (scrollY > 600) {
                sec2Img.style.transform = "translate(0, 0)";
                sec2Img.style.opacity = '1';
            } else {
                sec2Img.style.transform = 'translate(0, 50%)';
                sec2Img.style.opacity = '0';
            }

            if (scrollY > 1900) {
                sec4Text.style.transform = 'translate(-50%, -50%)';
                sec4Text.style.opacity = '1';
            } else {
                sec4Text.style.transform = 'translate(-50%, 0)';
                sec4Text.style.opacity = '0';
            }
            if (scrollY > 2100) {
                sec4Img.style.transform = "translate(0, 0)";
                sec4Img.style.opacity = '1';
            } else {
                sec4Img.style.transform = 'translate(0, 50%)';
                sec4Img.style.opacity = '0';
            }
            
            if(scrollY > 2600) {
                sec5Title.style.opacity = '1';
            } else {
                sec5Title.style.opacity = '0';
            }

            if(scrollY > 2800) {
                sec5Goods.style.transform = 'translate(0, 0)';
                sec5Goods.style.opacity = '1';
            } else {
                sec5Goods.style.transform = 'translate(0, 30%)';
                sec5Goods.style.opacity = '0';
            }
        });

        var swiper = new Swiper('.swiper-container', {
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            }, pagination: {
                el: ".swiper-pagination",
            }, autoplay: {
            	delay: 2500,
            	disableOnInteraction: false,
            }
        });

    </script>
</body>
</html>