<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*,  donation.model.vo.Donation"%>
<%
	Board b = (Board)request.getAttribute("b");
	Donation d = (Donation)request.getAttribute("d");
	String[] imgChange = (String[])request.getAttribute("imgChange");
	String[] imgOrigin = (String[])request.getAttribute("imgOrigin");
	String[] imgNo = (String[])request.getAttribute("imgNo");
	String dPeriod = (String)request.getAttribute("dPeriod");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기 작성</title>
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/donation.css">
<link rel="stylesheet" href="css/0_main.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
</head>
<body>
    <header class="write_header">
        <div class="header-box">
            <div class="font_main_title header-title">후원요청 글수정</div>
            <button class="submitBtn btn" onclick="submit();">수정하기</button>
        </div>
    </header>
    <main>

        <div class="post-container">
            <div class="note-wrap">
                <div class="note-title">
                    <span class="main-color point">!</span><span class="font_main_title">후원요청 작성 시 유의해주세요.</p>
                </div>
                <div class="note-content">
                    <ul>
                        <li class="font_content">focUs는 반려동물의 보다 나은 미래와 행복을 추구하며, 올바른 반려동물 문화 형성에 앞장서겠습니다.</li>
                        <li class="font_content">올바른 제목과 내용으로 깨끗한 게시판이 될 수 있도록 유의해주세요.</li>
                        <li class="font_content">후원자가 신뢰할 수 있게 기부금 사용계획을 자세하게 입력해주세요.</li>
                        <li class="font_content">후원기간이 완료된 후에는 +7일 이내에 영수증을 사이트 아래 이메일로 첨부해주세요.</li>
                        <li class="font_content">후원받기를 중단하게 될 경우, 사이트 하단 이메일 또는 연락처로 취소접수가 가능합니다.</li>
                    </ul>
                </div>  
            </div>

            <form action="<%= request.getContextPath() %>/updateDonation.bo" method="post" encType="multipart/form-data">
                <div class="area-wrap">
                    <div class="OOTC">
                       <div class="input-container">
                           <div class="input-wrap">
                           		<input type="hidden" name="hiddenBNo" id="hiddenBNo" value="<%= b.getbNo()%>">
                               <input type="text" class="OOTC-input input-title" name="dTitle" value="<%= b.getbTitle() %>">
                               <select name="dCategory"  id="write_category">
                                   <option>카테고리</option>
                                   <option value="10" <%= d.getdCatename().equals("병원비용") ? "selected" : "" %>>병원비용</option>
                                   <option value="20" <%= d.getdCatename().equals("기관비용") ? "selected" : "" %>>기관비용</option>
                                   <option value="30" <%= d.getdCatename().equals("사료비용") ? "selected" : "" %>>사료비용</option>
                                   <option value="40" <%= d.getdCatename().equals("기타") ? "selected" : "" %>>기타</option>
                               </select>
                           </div>
                            <div class="input-wrap aaa">
                                <div class="dona_between dona_between1">
                                    <div class="dona_text">후원금</div>
                                    <input type="text" class="OOTC-input input-dona1" name="dPay" value="<%= d.getdPay() %>">
                                    <div class="dona_text">원</div>
                                </div>
                                <div class="dona_between dona_between2">
                                    <div class="dona_text">후원기간</div>
                                    <input type="date" class="OOTC-input input-period" value="<%= dPeriod %>" name="dPeriod" id="dPeriod">
                                    <div class="dona_text">까지</div>
                                </div>
<!--                                 <div class="dona_between dona_between3"> -->
<!--                                     <div class="dona_image_btn"></div> -->
<!--                                 </div> -->
                            </div>
                       </div>
                    </div>
    
                    <div class="area">
                    
                        	<textarea  id="content-area" name="dContent"><%= b.getbContent() %></textarea>
	                        <div class="area-point-main">
		                        <div class="area-point-box">
		                        	<div class="area-point-title"><label>후원금 사용계획</label></div>
		                       		<input type="text" id="area-point-content" name="dEtc1" value="<%= d.getdEtc1() %>">
		                        </div>
		                        <div class="area-point-box">
		                       		<div class="area-point-title">후원대상</div>
		                       		<input type="text" id="area-point-content" name="dEtc2" value="<%= d.getdEtc2() %>">
		                        </div>
	                        </div>
	                        
	                        
	                    <!-- 이미지등록 --> 
	                    
	                     <div class="area-line"></div>  
	                    <div class="donation_thumbnail_box">
			            	<div class="donation_thumbnail_img_box">
			            		<img class="donation_thumbnail_img" id="titleImg"
			            		src="<%= request.getContextPath()%>/donation_uploadFiles/<%= imgChange[0] %>">
			            		<p class="donation_img_upload_text" id="img_text1">썸네일 이미지 등록</p>
				            </div>
				           	<div class="donation_thumbnail_img_box">
				           		<img class="donation_thumbnail_img" id="contentImg1"
				           		<% if(imgChange.length >=2) { %>
				           			src="<%= request.getContextPath()%>/donation_uploadFiles/<%= imgChange[1] %>"
				           		<% } %>>
				           		<% if(imgChange.length < 2) { %>
				           			<p class="donation_img_upload_text" id="img_text2">이미지 등록</p>
				           		<% } %>
				           	</div>
				           	<div class="donation_thumbnail_img_box">
				           		<img  class="donation_thumbnail_img" id="contentImg2"
				           		<% if(imgChange.length >=3) { %>
				           			src="<%= request.getContextPath()%>/donation_uploadFiles/<%= imgChange[2] %>"
				           		<% } %>>
				           		<% if(imgChange.length < 3) { %>
				           			<p class="donation_img_upload_text" id="img_text3">이미지 등록</p>
				           		<% } %>
				           	</div>
				           	<div class="donation_thumbnail_img_box">
				           		<img class="donation_thumbnail_img" id="contentImg3"
				           		<% if(imgChange.length >=4) { %>
				           			src="<%= request.getContextPath()%>/donation_uploadFiles/<%= imgChange[3] %>"
				           		<% } %>>
				           		<% if(imgChange.length < 4) { %>
				           			<p class="donation_img_upload_text" id="img_text4">이미지 등록</p>
				           		<% } %>
				           	</div>
	        			</div>
				        <div id="fileArea">
				        	<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this, 1)">
			                <input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this, 2)">
			                <input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this, 3)">
			           		<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this, 4)">
			           		<input type="hidden" name = "imgNo" value ="<%= imgNo[0] %>">
			           		<% if(imgChange.length >= 2) { %>
			           			<input type="hidden" name ="imgNo" value="<%= imgNo[1] %>">
			           		<% } %>
			           		<% if(imgChange.length >= 3) { %>
			           			<input type="hidden" name ="imgNo" value="<%= imgNo[2] %>">
			           		<% } %>
			           		<% if(imgChange.length >= 4) { %>
			           			<input type="hidden" name ="imgNo" value="<%= imgNo[3] %>">
			           		<% } %>
			            </div>    
                    </div>
                </div>
                <input type="submit" style="display: none;" id="update_submit">
            </form>
        </div>

    </main>
    <%@ include file="../common/footer.jsp" %>

    <script>
        window.addEventListener('scroll', function() {
            var scrollY = window.scrollY;
            var header = document.querySelector(".write_header");
            console.log(scrollY);
            if (scrollY > 101) {
                header.classList.add("active");
            } else {
                header.classList.remove("active");
            }
        });
        
        $(function() {
        	$('#fileArea').hide();
        	
        	$('#titleImg').click(function(){
        		$('#thumbnailImg1').click();
        	});
        	$('#contentImg1').click(function(){
        		$('#thumbnailImg2').click();
        	});
        	$('#contentImg2').click(function(){
        		$('#thumbnailImg3').click();
        	});
        	$('#contentImg3').click(function(){
        		$('#thumbnailImg4').click();
        	});
        });
        
        function LoadImg(value, num) {
        	if(value.files && value.files[0]) {
        		var reader = new FileReader();
        		
        		reader.onload = function(e) {
        			switch(num) {
        			case 1:
        				$('#titleImg').attr('src', e.target.result);
                        $('#img_text1').css({'color' : 'rgba(0,0,0,.3)'});
        				break;
        			case 2:
        				$('#contentImg1').attr('src', e.target.result);
                        $('#img_text2').hide();
        				break;
        			case 3:
        				$('#contentImg2').attr('src', e.target.result);
                        $('#img_text3').hide();
        				break;
        			case 4:
        				$('#contentImg3').attr('src', e.target.result);
                        $('#img_text4').hide();
        				break;
        			}
        		}
        		}
        		
        		reader.readAsDataURL(value.files[0]);
        	}
        
        function submit(){
        	$('#update_submit').click();
        }
        
		$('#dPeriod').on('change',function(){
			console.log($(this).val());
		});   	
		
    </script>
</body>
</html>