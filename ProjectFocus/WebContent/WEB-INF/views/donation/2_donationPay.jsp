<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, donation.model.vo.Donation"%>
<% 
	Member m = (Member)request.getAttribute("m");
	int bNo = (int)request.getAttribute("bNo");
	String title = (String)request.getAttribute("title");
	String dPay = (String)request.getAttribute("dPay");
	String bWriter = (String)request.getAttribute("bWriter");
	int morePay = (int)request.getAttribute("morePay");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>focUs</title>
    <link rel="stylesheet" href="css/donation.css">
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
    
    <%@ include file="../common/header.jsp" %>

    <main class="donation_payment_main">
        <div class="payment_container">
             <form action="<%= request.getContextPath() %>/donationKakaoPay.go" method="post">
            <div class="payment_wrap">
                <div class="payment_entry">
                    <p class="payment_title">후원 상세정보</p>
                    <div class="payment_box">
                        <div class="payment_box_wrap">
                            <div class="payment_box_header">후원기관/개인</div>
                            <div class="payment_box_header product_info">후원정보</div>
                            <div class="payment_box_header" >후원요청금액</div>
                        </div>
                        <div class="payment_box_line"></div>
                        <div class="payment_box_wrap">
                            <div class="payment_box_content"><%= bWriter %></div>
                            <input type ="hidden" name ="bNo" value="<%= bNo %>">
                            <div class="payment_box_content product_info">
                                <span class="payment_text"> <%= title %></span>
                                <input type ="hidden" name ="title" value="<%= title %>">
                            </div>
                            <div class="payment_box_content"><span id='comma'><%= dPay %></span>원</div>
                        </div>
                    </div>

					<div class="buyer_info_container">
						<div class="buyer_info_wrap info_wrap1">
							<div class="buyer_info">이름</div>
							<div class="buyer_info">연락처</div>
							<div class="buyer_info">이메일</div>
						</div>
						<div class="buyer_info_wrap">
							<div class="buyer_info2"><%= m.getmName() %></div>
							<div class="buyer_info2"><%= m.getmPhone() %></div>
							<div class="buyer_info2"><%= m.getmEmail() %></div>
						</div>
					</div>

					<div class="payment_guide">후원 시 유의사항</div>
					<div class="payment_guide_comment">- 후원 내역은 focUs 홈페이지 > 마이페이지 > 후원 내역에서 확인하실 수 있습니다.</div>
					<div class="payment_guide_comment">- 후원 취소/환불을 원하시는 회원은 7일 이내에 문의하여 취소하시기 바랍니다. (다만 후원 상황에 따른 환불 가능 여부가 달라집니다.)</div>
					<div class="payment_guide_comment">- 기타 후원 문의사항은 focUs로 연락주시기 바랍니다. 02-0000-0000</div>
                </div>
                <div class="payment_finally">
					<div class="payment_finally_wrap">
						<div class="payment_price_title">남은 후원금액</div>
						<div class="payment_price_box">
							<div class="payment_price" id="comma2"><%= morePay %></div>
							<span>원</span>
						</div>
					</div>
<!-- 					<div class="payment_finally_wrap"> -->
<!-- 						<div class="payment_price_title">후원 적립포인트</div> -->
<!-- 						<div class="payment_price">0원</div> -->
<!-- 					</div> -->
					<div class="payment_finally_wrap total_price">
						<div class="payment_price_title">결제금액</div>
						<div class="payment_price">
							<input type="text" name="dPay" onkeyup="inputNumberFormat(this)" class="payment_howmuch">
							<span class="payment_price_won">원</span>
						</div>
					</div>

					<div class="payment_finally_line"></div> 

					<div class="payment_btn" id="donationKakaoPay">후원하기<input type="submit" id="payment_btn_click" class="hideBtn"></div>
					<script>
						$('.payment_btn').on('click', function() {
							$('#payment_btn_click').click();
						})
					</script>
				</div>
            </div>
					</form>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>

</body>

	<script>
		
	window.onload=(function() {
		
		var x = Number($('#comma').text()).toLocaleString();
		var y = Number($('#comma2').text()).toLocaleString();
		$('#comma').text(x);
		$('#comma2').text(y);

	});
	
	   function inputNumberFormat(obj) {
	        obj.value = comma(uncomma(obj.value));
	    }

	    function comma(str) {
	        str = String(str);
	        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	    }

	    function uncomma(str) {
	        str = String(str);
	        return str.replace(/[^\d]+/g, '');
	    }
	
	
// 		$('#donationKakaoPay').on('click', function(){
				
// 				var IMP = window.IMP;
// 				IMP.init('imp25877186');
				
// 				IMP.request_pay({
// 				    pg : 'kakao', // version 1.1.0부터 지원.
// 				    pay_method : 'card',
// 				    merchant_uid : 'merchant_' + new Date().getTime(),
// 				    name : '주문명:결제테스트',
// 				    amount : 14000,
// 				    buyer_email : 'iamport@siot.do',
// 				    buyer_name : '구매자이름',
// 				    buyer_tel : '010-1234-5678',
// 				    buyer_addr : '서울특별시 강남구 삼성동',
// 				    buyer_postcode : '123-456',
// 				   // m_redirect_url : 'https://www.yourdomain.com/payments/complete'
// 				}, function(rsp) {
// 				    if ( rsp.success ) {
// 				        var msg = '결제가 완료되었습니다.';
// 				        msg += '고유ID : ' + rsp.imp_uid;
// 				        msg += '상점 거래ID : ' + rsp.merchant_uid;
// 				        msg += '결제 금액 : ' + rsp.paid_amount;
// 				        msg += '카드 승인번호 : ' + rsp.apply_num;
// 				    } else {
// 				        var msg = '결제에 실패하였습니다.';
// 				        msg += '에러내용 : ' + rsp.error_msg;
// 				    }
// 				    alert(msg);
// 				});
// 		});
	</script>
</html>