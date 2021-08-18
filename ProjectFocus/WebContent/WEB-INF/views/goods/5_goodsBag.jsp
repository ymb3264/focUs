<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, goods.model.vo.*" %>
<%
	ArrayList<GoodsBag> bagList= (ArrayList<GoodsBag>)session.getAttribute("bagList");
	int totalPay = 0;
	for(int i = 0; i < bagList.size(); i++) {
		totalPay += bagList.get(i).getPrice();
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/goods.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>

	<%@ include file="../common/header.jsp" %>	
	
	<main class="goods_pay_main">
		<form action="<%=request.getContextPath()%>/goodsPay.go" method="post">
			<div class="payment_box">
				<div class="payment_box_wrap">
					<div class="payment_box_header">주문번호</div>
					<div class="payment_box_header product_info">상품정보</div>
					<div class="payment_box_header">수량</div>
					<div class="payment_box_header">상품금액</div>
					<div class="payment_box_header">주문관리</div>
				</div>
				<div class="payment_box_line"></div>
				<div id="wrap">	
					<% for(int i = 0; i < bagList.size(); i++) { %>
					<% GoodsBag bag = bagList.get(i); %>
						<div class="payment_box_wrap product_font">
							<input type="hidden" value="<%= i %>" name="gNo">
							<div class="payment_box_content"><%= i+1 %></div>
							<div class="payment_box_content product_info2">
								<div class="payment_box_img"><img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= bag.getThumbnailImg() %>" class="payment_box_img"></div>
								<span class="payment_text"><%= bag.getTitle() %></span>
							</div>
							<div class="payment_box_content"><%= bag.getAmount() %>개</div>
							<div class="payment_box_content"><%= bag.getPrice() %>원</div>
							<div class="payment_box_content delete">삭제</div>
						</div>
					<% } %>
				</div>
				<div class="bag_payment_box">
					<div class="bag_line"></div>
					<div class="bag_payment_box_wrap">
						<div class="bag_payment_total"> 
							총 금액 : <%= totalPay %>원
						</div>
						<div class="bag_payment_btn">결제<input type="submit" id="bag_payment_go" class="hideBtn" value="결제하기"></div>
						<script>
							$('.bag_payment_btn').on('click', function() {
								$('#bag_payment_go').click();
							})
						</script>
					
					</div>
				</div>
			</div>
		</form>
	</main>
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
		$(document).on('click', '.delete', function(){
			$('#wrap').html('');
			var gNo = $(this).parent().children().eq(0).val();
			
			$.ajax({
				url: 'deleteGoodsBag.sl',
				data: {gNo:gNo},
				success: function(data) {
					for(var i = 0; i < data.length; i++) {
						var bag = data[i];
						var addHtml = "";
						addHtml += "<div class='payment_box_wrap product_font'>";
						addHtml += "<input type='hidden' value='" + i + "' name='gNo'>";
						addHtml += "<div class='payment_box_content'>" + (i+1) + "</div>";
						addHtml += "<div class='payment_box_content product_info2'>";
						addHtml += "<div class='payment_box_img'><img src='${pageContext.request.contextPath}/goods_uploadFiles/" +  bag.thumbnailImg + "'" +
						"class='payment_box_img'></div>";
						addHtml += "<span class='payment_text'>" + bag.title + "</span>";
						addHtml += "</div>";
						addHtml += "<div class='payment_box_content'>" + bag.amount + "개</div>";
						addHtml += "<div class='payment_box_content'>" + bag.price + "원</div>";
						addHtml += "<div class='payment_box_content delete'>삭제</div>";
						addHtml += "</div>";
						
						$('#wrap').append(addHtml);
					}
				}
			});
			
		});
		
	</script>
</body>
</html>











