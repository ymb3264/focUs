<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, goods.model.vo.*" %>
<%
	ArrayList<GoodsBag> bagList= (ArrayList<GoodsBag>)session.getAttribute("bagList");
	int totalPrice = 0;
	for(int i = 0; i < bagList.size(); i++) {
		totalPrice += bagList.get(i).getPrice();
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>focUs</title>
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" href="css/pay.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
    
	<%@ include file="../common/header.jsp" %>

    <main class="goods_pay_main">
        <div class="payment_container">
        	<form action="<%= request.getContextPath() %>/goodsPay.sl" method="post">
            <div class="payment_wrap">
                <div class="payment_entry">
                    <p class="payment_title">주문 상세정보</p>
                    <div class="payment_box">
                        <div class="payment_box_wrap">
                            <div class="payment_box_header product_buyNum">주문번호</div>
                            <div class="payment_box_header product_info">상품정보</div>
                            <div class="payment_box_header">상품수량</div>
                            <div class="payment_box_header">상품금액</div>
                        </div>
                        <div class="payment_box_line"></div>
                        <% for(int i = 0; i < bagList.size(); i++) { %>
                        <% GoodsBag bag = bagList.get(i); %>
	                        <div class="payment_box_wrap">
	                            <div class="payment_box_content product_buyNum"><%= bag.getBuyNum() %></div>
	                            <div class="payment_box_content product_info2">
	                                <div class="payment_box_img"><img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= bag.getThumbnailImg() %>" class="payment_box_img"></div>
	                                <span class="payment_text"><%= bag.getTitle() %></span>
	                            </div>
	                            <div class="payment_box_content"><%= bag.getAmount() %>개</div>
	                            <div class="payment_box_content"><%= bag.getPrice() %>원</div>
	                        </div>
                        <% } %>
                    </div>

					<div class="buyer_info_container">
						<div class="buyer_info_wrap info_wrap1">
							<div class="buyer_info">이름</div>
							<div class="buyer_info">연락처</div>
							<div class="buyer_info">배송지</div>
						</div>
						<div class="buyer_info_wrap">
							<div class="buyer_info2"><%= loginUser.getmName() %></div>
							<div class="buyer_info2"><%= loginUser.getmPhone() %></div>
							<div class="buyer_info2"><%= loginUser.getmAddress() %> <%= loginUser.getmAddress2() %> (<%= loginUser.getmPost() %>)</div>
						</div>
					</div>

					<div class="payment_guide">교환 및 반품 안내</div>
					<div class="payment_guide_comment">- 상품 택(tag)제거 또는 개봉으로 상품 가치 훼손 시에는 상품수령 후 7일 이내라도 교환 및 반품이 불가능합니다.</div>
					<div class="payment_guide_comment">- 저단가 상품, 일부 특가 상품은 고객 변심에 의한 교환, 반품은 고객께서 배송비를 부담하셔야 합니다. (제품의 하자, 배송오류는 제외)</div>
					<div class="payment_guide_comment">- 일부 특가 상품의 경우, 인수 후에는 제품의 하자나 오배송의 경우를 제외한 고객님의 단순변심에 의한 교환, 반품처리는	 불가능합니다.</div>
                </div>
                <div class="payment_finally">
					<div class="payment_finally_wrap">
						<div class="payment_price_title">상품금액</div>
						<div class="payment_price"><%= totalPrice %>원</div>
					</div>
					<div class="payment_finally_wrap">
						<div class="payment_price_title">배송비</div>
						<div class="payment_price">2,500원</div>
					</div>
					<div class="payment_finally_wrap">
						<div class="payment_price_title">포인트사용</div>
						<div class="payment_price">0원</div>
					</div>
					<div class="payment_finally_wrap total_price">
						<div class="payment_price_title">결제금액</div>
						<div class="payment_price"><%= totalPrice+2500 %>원</div>
					</div>


					<div class="payment_finally_line"></div> 

					<input type="submit" class="payment_btn" value="결제하기">
				</div>
            </div>
			</form>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>

	<script>
		    
		    
	</script>

</body>
</html>