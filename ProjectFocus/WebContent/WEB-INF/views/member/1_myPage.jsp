<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, goods.model.vo.*, service.model.vo.*" %>
<%
	ArrayList<GoodsBag> bagList = (ArrayList<GoodsBag>)request.getAttribute("bagList");
	Service s = (Service)request.getAttribute("s");	
	Reserve r = (Reserve)request.getAttribute("r");
	int count = (int)request.getAttribute("count");
	Member m = (Member)request.getAttribute("m");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>focUs || MyPage</title>
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main>
        <div class="myPage_container">
            <div class="section1">
                <div class="box_wrap">
                    <div class="myPage_box">
                        <div class="myPage_title font_sub_title">프로필</div>
                        <div class="myPage_content_wrap">
                            <div class="myPage_content">
                                <div class="myPage_bold font_content">닉네임</div>
                                <div class="myPage_info"><%= loginUser.getmNick() %></div>
                            </div>
                            <div class="myPage_content">
                                <div class="myPage_bold font_content">연락처</div>
                                <div class="myPage_info"><%= loginUser.getmPhone() %></div>
                            </div>
                            <div class="myPage_content">
                                <div class="myPage_bold font_content">주소</div>
                                <div class="myPage_info"><%= loginUser.getmAddress() %></div>
                            </div>
                            <div class="info_update_btn btn" id="updateMember_btn">개인정보수정</div>
                        </div>
                    </div>
                    <div class="myPage_box">
                        <div class="myPage_title font_sub_title">나의 후원</div>
                        <div class="myPage_content_wrap">
                            <div class="myPage_content">
                                <span class="myPage_point"><%= m.getmName() %></span><span class="myPage_comment">님, 후원해주셔서 감사합니다.</span>
                            </div>
                            <div class="myPage_content">
                                <span class="myPage_comment">현재까지&nbsp;&nbsp;</span><span class="myPage_point" id="comma"><%= m.getpMtotalpay() %></span><span class="myPage_comment">원 후원하셨습니다.</span>
                            </div>
                            <div class="info_update_btn btn" id="donation_payList">후원기록보기</div>
                        </div>
                    </div>
                </div>
                
                 <div class="box_wrap">
                    <div class="myPage_box">
                        <div class="myPage_title font_sub_title">봉사 예약 현황</div>
                       <% if(r == null && s == null) { %>
                           <div class="myPage_content_wrap">
                       			 <div class="myPage_service_no">예약한 봉사가 없습니다.</div>
                     		</div>
                        <% } else if (r == null && s != null) { %>
	                          <div class="myPage_content_wrap">
	                           	<div class="service_live_btn_wrap">
	                               <div class="service_live_y" id="service_people_List">예약명단</div>
	                               <div class="service_live_y" id="service_people_delete">봉사종료</div>
	                            </div>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">봉사 제목</div>
                                   <div class="myPage_info"><%= s.getbTitle() %></div>
                               </div>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">예약 날짜</div>
                                   <div class="myPage_info"><%= s.getsTime() %></div>
                               </div>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">위치</div>
                                   <div class="myPage_info"><%= s.getsLocation() %></div>
                               </div>
                              </div>
                               <% } else if (r != null && s == null) { %>
                           		<div class="myPage_content_wrap">
	                           		<% if(r.getrStatus().equals("N")) {%>
	                               <div class="service_live_n">예약대기</div>
	                               	<% } else{%>
	                               	<div class="service_live_y">예약확정</div>
	                               	<% }%>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">봉사 제목</div>
                                   <div class="myPage_info"><%= r.getrTitle() %></div>
                               </div>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">예약 날짜</div>
                                   <div class="myPage_info"><%= r.getrTime() %></div>
                               </div>
                               <div class="myPage_content">
                                   <div class="myPage_bold font_content">위치</div>
                                   <div class="myPage_info"><%= r.getrLocation() %></div>
                               </div>
                           </div>
                        <% } %>
                    </div>
                    
                    <div class="myPage_box">
                        <div class="myPage_title font_sub_title">나의 활동</div>
                        <div class="myPage_content_wrap">
                            <div class="myPage_content2">
                                <div class="myPage_bold2 font_content">내가 쓴 글 보러가기</div>
                                <div class="myPage_info"><a href="<%= request.getContextPath() %>/selectMyBoard.bo"><%= count %>개</a></div>
                            </div>
                            <div class="myPage_content2">
<!--                                 <div class="myPage_bold2 font_content">내가 쓴 댓글 보러가기</div> -->
<!--                                 <div class="myPage_info"><a href="#">200개</a></div> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="myPage_line"></div>
            <div class="section2">
				<div class="payment_box_wrap">
					<div class="payment_box_header">주문번호/주문날짜</div>
					<div class="payment_box_header product_info">상품정보</div>
					<div class="payment_box_header">상품수량/상품금액</div>
					<div class="payment_box_header">배송정보</div>
				</div>
				<div class="payment_box_line"></div>
				<div id="wrap">	
				<% if(bagList.size() == 0) { %>
					<div style="text-align: center; margin-top: 10px;">구매한 상품이 없습니다.</div>
				<% } else { %>
					<% for(int i = 0; i < bagList.size(); i++) { %>
					<% GoodsBag bag = bagList.get(i); %>
						<div class="payment_box_wrap product_font">
							<input type="hidden" value="<%= i %>" name="gNo">
							<div class="payment_box_content2">
								<div><%= bag.getBuyNum() %></div>
								<div style="margin-top: 10px;"><%= bag.getDate() %></div>
							</div>
							<div class="payment_box_content1 product_info2">
								<div class="payment_box_img"><img src="<%= request.getContextPath() %>/goods_uploadFiles/<%= bag.getThumbnailImg() %>" class="payment_box_img"></div>
								<span class="payment_text"><%= bag.getTitle() %></span>
							</div>
							<div class="payment_box_content2">
								<div><%= bag.getAmount() %>개</div>
								<div style="margin-top: 10px;"><%= bag.getPrice() %>원</div>
							</div>
							<div class="payment_box_content2">
								<div>주문확인중</div>
							</div>
						</div>
					<% } %>
				<% } %>
				</div>
            </div>

        </div>
    </main>
    
      <!-- donationPayList -->
	<div class="myPage_modal">
		<div class="myPage_modal_background"></div>
		<div class="dpl-main-box">
			<div class="dpl-head">
				<div class="dpl-head-head">
					<div class="dpl-head-ni"><%= loginUser.getmNick() %></div>
					<div class="dpl-head-ck">님의 후원내역</div>
				</div>
				<div class="dpl-head-body">
					<div class="dpl-body">
						<div class="dpl-body-title">
							<div class=dpl-pNo>후원번호</div>
							<div class=dpl-pDate>후원날짜</div>
							<div class=dpl-pCate>카테고리</div>
							<div class=dpl-pTitle>글제목</div>
							<div class=dpl-pWriter>후원기관/개인</div>
							<div class=dpl-pPay>후원금액</div>
						</div>
						<div class="dpl-body-content">
							<div class=dpl-No></div>
							<div class=dpl-Date></div>
							<div class=dpl-Cate></div>
							<div class=dpl-Title></div>
							<div class=dpl-Writer></div>
							<div class=dpl-Pay></div>
						</div>
					</div>
				</div>
			</div>
			<div class="dpl-foot">
				총 <span style="font-weight: bold;" id="com"><%= m.getpMtotalpay() %></span>
				원 후원하셨습니다.
			</div>
		</div>
	</div>
	
	<!-- ServicePeopleList -->
	<div class="myPage_modal">
		<div class="myPage_modal_background"></div>
		<div class="sp-main-box">
			<div class="sp-head">
				<div class="sp-head-head">
					<div class="sp-head-ni"><%= loginUser.getmNick() %></div>
					<div class="sp-head-ck">님의 예약자명단</div>
				</div>
				<div class="sp-head-body">
					<div class="sp-body">
						<div class="sp-body-title">
							<div class=sp-rNo>예약번호</div>
							<div class=sp-rName>예약자명</div>
							<div class=sp-rPhone>연락처</div>
							<div class=sp-rEmail>이메일</div>
							<div class=sp-rButton>예약현황</div>
						</div>
						<div class="sp-body-content">
							<div class=sp-No></div>
							<div class=sp-Name></div>
							<div class=sp-Phone></div>
							<div class=sp-Email></div>
							<div class=sp-button></div>
						</div>
					</div>
				</div>
			</div>
			<div class="sp-foot">
			</div>
		</div>
	</div>
       


	<%@ include file="../common/footer.jsp" %>
	
	<script>
	 $('#updateMember_btn').click(function() {
			location.href="<%= request.getContextPath() %>/myPageUpdate.go";
		});
		
		$('#donation_payList').click(function(){
         $('.myPage_modal_background').addClass('active');
         $('.dpl-main-box').addClass('active');

         var mNo = '<%= loginUser.getmNo() %>';
         var mNick = '<%= loginUser.getmNick() %>';
         var pMtotalpay = <%= loginUser.getpMtotalpay() %>;
         
         $.ajax({
             url:'donationPayList.go',
             data: {mNo:mNo},
             success: function(data){

                 console.log(data);
                 var pList = data;
                 console.log(pList);

                 $('.dpl-body-content').html('');

                 for(var i = 0; i < pList.length; i++){
                     var p = pList[i];
                     var addHtml = "";

                     addHtml += "<div class= 'dpl-body-content'>";
                     addHtml += "<div class= 'dpl-No'>"+ p.pNo +"</div>";
                     addHtml += "<div class= 'dpl-Date'>"+ p.pDate +"</div>";
                     addHtml += "<div class= 'dpl-Cate'>"+ p.dCatename +"</div>";
                     addHtml += "<div class= 'dpl-Title'>"+ p.pTitle +"</div>";
                     addHtml += "<div class= 'dpl-Writer'>"+ p.bWriter +"</div>";
                     addHtml += "<div class= 'dpl-Pay' id='comma" + i + "'>"+ p.pPayKakao +"</div>";
                     addHtml += "</div>";

                     $('.dpl-body').append(addHtml);
                     }

                     for(var i = 0; i < pList.length; i++){
                         var p = pList[i];
                     var x = Number($('#comma' + i).text()).toLocaleString();
                     $('#comma' + i).text(x);
                     }
                     
             }
    		 }); 

       });
		
		
		$('.myPage_modal_background').click(function() {
			$('.myPage_modal_background').removeClass('active');
			$('.dpl-main-box').removeClass('active');
		});
		
		// ServicePeopleList
		
		$('.myPage_modal_background').click(function() {
			$('.myPage_modal_background').removeClass('active');
			$('.sp-main-box').removeClass('active');
		});
		
		$('#service_people_List').click(function(){
         $('.myPage_modal_background').addClass('active');
         $('.sp-main-box').addClass('active');
		
         <% if(s != null) { %>
         	var bNo = <%= s.getbNo() %>;     
     	 <% } %>
         
         $.ajax({
             url:'servicePeopleList.go',
             data: {bNo:bNo},
             success: function(data){
                 var rList = data;

                 $('.sp-body-content').html('');

                 for(var i = 0; i < rList.length; i++){
                     var r = rList[i];
                     var addHtml = "";
		
                     addHtml += "<div class= 'sp-body-content'>";
                     addHtml += "<div class= 'sp-No'>"+ r.rNo +"</div>";
                     addHtml += "<div class= 'sp-Name'>"+ r.rName +"</div>";
                     addHtml += "<div class= 'sp-Phone'>"+ r.rPhone +"</div>";
                     addHtml += "<div class= 'sp-Email'>"+ r.rEmail +"</div>";
                     if(r.rStatus == 'N'){
                     addHtml += "<div class='sp-Button'><input  id='sp-Button-yes' type=hidden value='"+ r.rNo + "'><button class='sp-modal-btn-n' readonly>예약대기</button></div>";
                     } else{
                     	addHtml += "<div class='sp-Button'><button class='sp-modal-btn-y'>예약확정</button></div>";
                     }
                     addHtml += "</div>";

                     $('.sp-body').append(addHtml);
                     }
             }
    		 }); 

       });
		
		$(document).on('click', '.sp-Button', function(){
			var bool = confirm("예약확정은 취소가 불가능합니다. 예약확정 하시겠습니까?");
			var rNo = $(this).children().eq(0).val();
			
			console.log(rNo);
			if(bool){
				$.ajax({
					 url:'updateSpButtonYes.go?rNo='+rNo,
		             data: {rNo:rNo},
		             context: this,
		             success: function(data){
		                	console.log(data);
		                	
		                	var r = data;
		                    var addHtml = "";
		                        
		                	$(this).html('');
		                	
		                	 if(r.rStatus == 'Y'){
		                         	addHtml += "<div style='width: 100%;'><button class='sp-modal-btn-y' readonly>예약확정</button></div>";
		                         } else{
		                         addHtml += "<div class= 'sp-Button'><input  id='sp-Button-yes' type=hidden value='"+ r.rNo + "'><button>예약대기</button></div>";
		                         }
		                	 
		                	 $(this).append(addHtml);
		                }
				});
			}
			
		});
		
		// 봉사종료버튼
		$(document).on('click', '#service_people_delete', function(){
			var bool = confirm("봉사종료를 하게되면 게시글도 삭제됩니다. 봉사종료 하시겠습니까?");
			 <% if(s != null) { %>
	         	var bNo = <%= s.getbNo() %>;     
	     	 <% } %>
			if(bool){
				$.ajax({
					 url:'deleteServiceList.go?bNo='+bNo,
		             data: {bNo:bNo},
		             context: this,
		             success: function(data){
	                        
		                 $(this).parent().parent().html('');
		            	 
	                     var addHtml = "예약한 봉사가 없습니다.";
	                     
	                     $(this).parent().parent().append(addHtml);
		             }
			});
			}
			});
		
		

		window.onload = (function() {

			var x = Number($('#comma').text()).toLocaleString();
			$('#comma').text(x);
			
			var x = Number($('#com').text()).toLocaleString();
            $('#com').text(x);
	});
		
	</script>
	
</body>
</html>