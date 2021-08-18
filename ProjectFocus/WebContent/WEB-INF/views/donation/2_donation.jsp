<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, common.*, board.model.vo.*,  donation.model.vo.Donation"%>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	ArrayList<Donation> dList = (ArrayList<Donation>)request.getAttribute("dList");
	ArrayList<Image> IList = (ArrayList<Image>)request.getAttribute("IList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/donation.css">
<link rel="stylesheet" href="css/0_main.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
<style>
	.hover{background-color: rgba(182, 182, 182, 0.3);}
</style>
</head>
<body>
	  <%@ include file="../common/header.jsp" %> 
	 

	<div class="dv"></div>
	<div class="main_poster_container">
		<div class="board_main_image_box">
			<img style="width: 100%; height: 100%;" src="image/focus_donation.jpg">
		</div>
	</div>

	<main>
		<div class="hashtag_box1">
			<div class="hashtag_span_box">
				<div class="hashtag_span_box_1">
					<span style="color: #ffc34d; font-size: 35px;">#</span>
					<span class="hashtag_span">카테고리를 통해 원하는 곳에 후원하세요 !</span>
				</div>
<!-- 				<div class="hashtag_span_box_2"> -->
<!-- 				글쓰기 있던 곳 -->
<!-- 				</div> -->
			</div>
			<div class="hashtag_and_search">
				<div class="hashtag_btn_box">
					<input type="button" class="hashtag_btn" name="catename" value="전체">
					<input type="button" class="hashtag_btn" name="catename" value="병원비용">
					<input type="button" class="hashtag_btn" name="catename" value="기관비용">
					<input type="button" class="hashtag_btn" name="catename" value="사료비용">
					<input type="button" class="hashtag_btn" name="catename" value="기타">
				</div>
				
				
				<form>
					<select name="search" class="list_category">
					<option value="1">제목</option>
					<option value="2">작성자</option>
					<option value="3">내용</option>
					</select>
					<input type="text" placeholder="검색어를 입력하세요" class="input_search">
					<input type="button" value="검색" class="btn_sty" >
				</form>
			</div>
		</div>
		
			<section class="main_box" id="main_box">
					 <%	for(int i = 0; i < bList.size(); i++){	
							Board b = bList.get(i); 
							Donation d = dList.get(i); %>
					<div class="main_box_content">
					<input type="hidden" value="<%= b.getbNo() %>">
						<% for(int z = 0; z < IList.size(); z++){
								Image im = IList.get(z);
								if(b.getbNo() == im.getbNo()){ %>
									<div><img src="<%= request.getContextPath() %>/donation_uploadFiles/<%= im.getiChange() %>" class="main_img_sty1"></div>		
						<% } %>
						<% } %>
						<div class="main_box_top">
							<div class="main_box_top_top">
							<div class="main_box_top_period">
							<span class="main_writer"><%= b.getbDate() %></span> ~
							<span class="main_writer"><%= d.getdPeriod() %></span><span style="font-size:13px; margin-left:2px;">까지</span>
							</div>
							<span class="main_writer2"><%= d.getmNick() %></span>
							</div>
							<div class="main_box_top_likey">
								<div class="main_likey_shape"><i class="fas fa-heart donation_heart"></i></div>
								<div class="main_likey_total"><%= b.getbLike() %>개</div>
							</div>
						</div>
						
						<p class="main_title"><%= b.getbTitle() %></p>
						
						<div class="main_box_bottom">
							<div class="progress">
								<div class="progress-real" role="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width:<%= d.getPercent() %>%">
								<%= d.getPercent() %>%
								</div>
							</div>
						<div style="font-size:12px; margin-right:10px; margin-bottom:10px;"><span class="payComma" id="comma<%= i %>"><%= d.getdPay() %></span> 까지</div>


						</div>
				</div>
						<% } %> 
			</section>
			
			<div class="donation_writeBtn_wrap">
				<% if(loginUser != null){ %>
					<input type="button" class="donation_write_btn" value="글쓰기" onclick="location.href='<%= request.getContextPath() %>/DonationWrite.go'">
					<% } %>
			</div>
			
			<!-- 페이징처리 -->
<!-- 			<div class="donation_page_wrap" align="center"> -->
			
<%-- 			<button onclick="location.href='<%= request.getContextPath() %>/selectDonationList.bo?currentPage=1'"> &lt;&lt; </button> --%>
<%-- 			<button onclick="location.href='<%= request.getContextPath() %>/selectDonationList.bo?currentPage=<%=currentPage - 1%>'" id="beforeBtn"> &lt; </button> --%>
			
<!-- 			<Script> // 현재 페이지가 1보다 작으면 버튼클릭이 안되게 -->
<%-- 				if(<%= currentPage %> <= 1){ --%>
<!-- 					$('#beforeBtn').attr('disabled', 'true'); -->
<!-- 				} -->
<!-- 			</Script> -->
			<!-- 숫자 버튼 -->
<%-- 			<% for(int p = startPage; p <= endPage; p++){ %> --%>
<%-- 			<%		if(p == currentPage){ %> --%>
<%-- 						<button id="choosen" disabled> <%= p %></button> --%>
<%-- 			<%		} else { %> --%>
<%-- 						<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/selectDonationList.bo?currentPage=<%= p %>'"> <%= p %></button> --%>
<%-- 			<%		} %> --%>
<%-- 			<% 	} %> --%>
			
		
			<!-- 다음 페이지로 -->
<%-- 			<button onclick="location.href='<%= request.getContextPath() %>/selectDonationList.bo?currentPage=<%=currentPage + 1%>'" id="afterBtn"> &gt; </button> --%>
<!-- 			<script> // 현재 페이지가 maxPage보다 크면 버튼클릭이 안되게 -->
<%-- 				if(<%= currentPage %> >= <%= maxPage %>){ --%>
<!-- // 					$('#afterBtn').prop('disabled', true); -->
<!-- // 				} -->
<!-- 			</script> -->
			<!-- 맨 끝으로 -->
<%-- 			<button onclick="location.href='<%=request.getContextPath() %>/selectDonationList.bo?currentPage=<%= maxPage %>'"> &gt;&gt; </button> --%>
<!-- 		</div> -->
			
			

	</main>
	
	 <%@ include file="../common/footer.jsp" %>

	<script>
		
		
	
	
		window.onload=(function() {
			
				for(var i = 0; i < <%= bList.size() %>; i++){
					var x = Number($('#comma' + i).text()).toLocaleString();
					$('#comma' + i).text(x);
				}

		});
	
	
		$('.hashtag_btn').click(function(){
			console.log($(this).val());
			var catename = $(this).val();
			
			$.ajax({
				
	    		url: 'selectCatename.go',
	    		data: {catename:catename},
	    		success: function(data){
	    			
	    			var bList = data["bList"];
	    			var dList = data["dList"];
	    			var IList = data["IList"];
	    			var pi = data["pi"];
	    			
	    			$('#main_box').html('');
	    			
	    									for(var i = 0; i < bList.length; i++){
	    										var b =bList[i];
	    										var d =dList[i];
	    										var addHtml = "";
	    								
	    			 					addHtml += "<div class='main_box_content'>";
	    			 					addHtml += "<input type='hidden' value='" + b.bNo + "'>";
	    			 						for(var z = 0; z < IList.length; z++){
	    			 								var im = IList[z];
	    			  								if(b.bNo == im.bNo){ 
	    			  					addHtml += "<div><img src='${pageContext.request.contextPath}/donation_uploadFiles/" +  im.iChange + "'" + "class='main_img_sty1'></div>";		
	    			  								}
	    									}
	    										
	    				
	    									addHtml += "<div class='main_box_top'>";
	    									addHtml += "<div class='main_box_top_top'>";
	    									addHtml += "<div class='main_box_top_period'>";
	    									addHtml += "<span class='main_writer'>" + b.bDate + "</span> ~";
	    									addHtml += "<span class='main_writer'>" + d.dPeriod + "</span><span style='font-size:13px; margin-left:2px;'>까지</span>";
	    									addHtml += "</div>";
	    									addHtml += "<span class='main_writer2'>" + d.mNick + "</span>";
	    									addHtml += "</div>";
	    									addHtml += "<div class='main_box_top_likey'>";
	    									addHtml += "<div class='main_likey_shape'><i class='fas fa-heart donation_heart'></i></div>";
	    									addHtml += "<div class='main_likey_total' style='font-size:9px; margin-top:8px;'>" + b.bLike + "개</div>";
	    									addHtml += "</div>";
	    									addHtml += "</div>";
	    									
	    									addHtml += "<p class='main_title'>" + b.bTitle + "</p>";
	    									
	    									addHtml += "<div class='main_box_bottom'>";
	    									addHtml += "<div class='progress'>";
	    									addHtml += "<div class='progress-real' role='progressbar' aria-valuemin='0' aria-valuemax='100' style='width:" + d.percent + "%'>";
	    									addHtml += d.percent+"%";
	    									addHtml += "</div>";
	    									addHtml += "</div>";
	    									addHtml += "<div style='font-size:12px; margin-right:10px; margin-bottom:10px;'>" +d.dPay + "까지</div>";
	    									addHtml += "</div>";

	    			 						$('#main_box').append(addHtml);
	    			 						
	    									}
	    			 						
	    			 						/* -------- 페이징..*/
	    			 						
	    			 						$('.donation_page_wrap').html('');
	    			 						var addHtml = '';
	    			 						
											var pi = pi[0];	  
											var listCount = Number(pi.listCount);
    										var currentPage = Number(pi.currentPage);
    										var maxPage = Number(pi.maxPage);
    										var startPage = Number(pi.startPage);
    										var endPage = Number(pi.endPage);
    										console.log(endPage);
	    			 						addHtml += "<button onclick='location.href='${pageContext.request.contextPath}/selectDonationList.bo?currentPage=1''> &lt;&lt; </button> ";
	    			 						addHtml += "<button onclick='location.href='${pageContext.request.contextPath}/selectDonationList.bo?currentPage=" + (currentPage-1) + "'' id='beforeBtn'> &lt; </button>";
	    			 						
	    			 									if(currentPage<= 1){
// 	    			 						addHtml += $('#beforeBtn').attr('disabled', 'true');
	    			 									};
	    			 						
	    			 									for( var p = startPage; p <= endPage; p++){
	    			 									if(p == currentPage){ 
	    			 						addHtml += "<button id='choosen' disabled>" + p + "</button>";
	    			 									} else {
	    			 						addHtml += "<button id='numBtn' onclick='location.href='${pageContext.request.contextPath}/selectDonationList.bo?currentPage="+ p + "''>" + p + "</button>";
	    			 									}
	    			 									} 
	    			 						
	    			 					
	    			 						addHtml += "<button onclick='location.href='${pageContext.request.contextPath}/selectDonationList.bo?currentPage=" + (currentPage + 1) + "'' id='afterBtn'> &gt; </button>";
	    			 								if( currentPage >= maxPage){
	    			 						addHtml +=	"$('#afterBtn').prop('disabled', true);";
	    			 									}
	    			 								
	    			 						addHtml += "<button onclick='location.href='${pageContext.request.contextPath}/selectDonationList.bo?currentPage=maxPage" + "''> &gt;&gt; </button>";
	    			 						$('.donation_page_wrap').append(addHtml);
	    									
	    			 	    		
	    		}
			
 		});
 		});
		
		$('.btn_sty').click(function(){
			var search = $('.list_category').val();
			var searchString = $('.input_search').val();
			console.log(search);
			console.log(searchString);
			
			
				$.ajax({
					url: 'selectSearch.go',
					data: {search:search, searchString:searchString},
					success: function(data){
						
						var bList = data["bList"];
		    			var dList = data["dList"];
		    			var IList = data["IList"];
		    			
		    			$('#main_box').html('');
		    			
						for(var i = 0; i < bList.length; i++){
							var b =bList[i];
							var d =dList[i];
							var addHtml = "";
					
 					addHtml += "<div class='main_box_content'>";
 					addHtml += "<input type='hidden' value='" + b.bNo + "'>";
 						for(var z = 0; z < IList.length; z++){
 								var im = IList[z];
  								if(b.bNo == im.bNo){ 
  					addHtml += "<div><img src='${pageContext.request.contextPath}/donation_uploadFiles/" +  im.iChange + "'" + "class='main_img_sty1'></div>";		
  								}
						}
// 							var listCount = pi.listCount;
// 							var currentPage = pi.currentPage;
// 							var maxPage = pi.maxPage;
// 							var startPage = pi.startPage;
// 							var endPage = pi.endPage;
	
 						addHtml += "<div class='main_box_top'>";
						addHtml += "<div class='main_box_top_top'>";
						addHtml += "<div class='main_box_top_period'>";
						addHtml += "<span class='main_writer'>" + b.bDate + "</span> ~";
						addHtml += "<span class='main_writer'>" + d.dPeriod + "</span><span style='font-size:13px; margin-left:2px;'>까지</span>";
						addHtml += "</div>";
						addHtml += "<span class='main_writer2'>" + d.mNick + "</span>";
						addHtml += "</div>";
						addHtml += "<div class='main_box_top_likey'>";
						addHtml += "<div class='main_likey_shape'><i class='fas fa-heart donation_heart'></i></div>";
						addHtml += "<div class='main_likey_total' style='font-size:9px; margin-top:8px;'>" + b.bLike + "개</div>";
						addHtml += "</div>";
						addHtml += "</div>";
						
						addHtml += "<p class='main_title'>" + b.bTitle + "</p>";
						
						addHtml += "<div class='main_box_bottom'>";
						addHtml += "<div class='progress'>";
						addHtml += "<div class='progress-real' role='progressbar' aria-valuemin='0' aria-valuemax='100' style='width:" + d.percent + "%'>";
						addHtml += d.percent+"%";
						addHtml += "</div>";
						addHtml += "</div>";
						addHtml += "<div style='font-size:12px; margin-right:10px; margin-bottom:10px;'>" +d.dPay + "까지</div>";
						addHtml += "</div>";
						addHtml += "</div>";

 						$('#main_box').append(addHtml);
					}
					}	
					
				});
		});
		
// 		$('.hashtag_btn').hover(function(){
// 			$(this).addClass('hover');
// 		}, function(){
// 			$(this).removeClass('hover');
// 		});
		
		$('.content_box2').click(function(){
			$("#detail").css({"z-index":"10", "display":"block"});
		});
		
		$('#close').click(function(){
			$('#detail').css('display', 'none');
		});
		
		
		$(document).on('click', '.main_box_content', function() {
				var bNo = $(this).children().eq(0).val();
				location.href = "<%= request.getContextPath() %>/selectDonationDetail.bo?bNo=" + bNo;
		});
		
	
	</script>
</body>
</html> 