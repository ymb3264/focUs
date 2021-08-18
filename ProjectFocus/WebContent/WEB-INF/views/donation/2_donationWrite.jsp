<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <div class="font_main_title header-title">후원요청 글작성</div>
            <button class="submitBtn btn" onclick="submit();">등록하기</button>
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

            <form action="<%= request.getContextPath() %>/insertDonation.bo" method="post" encType="multipart/form-data">
                <div class="area-wrap">
                    <div class="OOTC">
                       <div class="input-container">
                           <div class="input-wrap">
                               <input type="text" class="OOTC-input input-title" placeholder="제목을 입력하세요." name="dTitle">
                               <select name="dCategory"  id="write_category">
                                   <option>카테고리</option>
                                   <option value="10">병원비용</option>
                                   <option value="20">기관비용</option>
                                   <option value="30">사료비용</option>
                                   <option value="40">기타</option>
                               </select>
                           </div>
                            <div class="input-wrap aaa">
                                <div class="dona_between dona_between1">
                                    <div class="dona_text">후원금</div>
                                    <input type="text" class="OOTC-input input-dona1" placeholder="후원 금액을 입력하세요." name="dPay" onkeyup="inputNumberFormat(this)">
                                    <div class="dona_text">원</div>
                                </div>
                                <div class="dona_between dona_between2">
                                    <div class="dona_text">후원기간</div>
                                    <input type="date" class="OOTC-input input-period" placeholder="시간을 입력하세요." name="dPeriod">
                                    <div class="dona_text">까지</div>
                                </div>
<!--                                 <div class="dona_between dona_between3"> -->
<!--                                     <div class="dona_image_btn"></div> -->
<!--                                 </div> -->
                            </div>
                       </div>
                    </div>
    
                    <div class="area">
                        	<textarea  id="content-area" placeholder="내용을 입력하세요." name="dContent"></textarea>
	                        <div class="area-point-main">
		                        <div class="area-point-box">
		                        	<div class="area-point-title"><label>후원금 사용계획</label></div>
		                       		<input type="text" id="area-point-content" placeholder="ex) 이동차량: 50,000원 / 마취비용: 15,000원" name="dEtc1">
		                        </div>
		                        <div class="area-point-box">
		                       		<div class="area-point-title">후원대상</div>
		                       		<input type="text" id="area-point-content" placeholder="ex) 무럭형제 입양처(강원도 양양)으로 이동" name="dEtc2">
		                        </div>
	                        </div>
	                        
	                        
	                    <!-- 이미지등록 --> 
	                    
	                     <div class="area-line"></div>  
	                    <div class="donation_thumbnail_box">
			            	<div class="donation_thumbnail_img_box">
			            		<img class="donation_thumbnail_img" id="titleImg">
			            		<p class="donation_img_upload_text" id="img_text1">썸네일 이미지 등록</p>
				            </div>
				           	<div class="donation_thumbnail_img_box">
				           		<img class="donation_thumbnail_img" id="contentImg1">
				           		<p class="donation_img_upload_text" id="img_text2">이미지 등록</p>
				           	</div>
				           	<div class="donation_thumbnail_img_box">
				           		<img  class="donation_thumbnail_img" id="contentImg2">
				           		<p class="donation_img_upload_text" id="img_text3">이미지 등록</p>
				           	</div>
				           	<div class="donation_thumbnail_img_box">
				           		<img class="donation_thumbnail_img" id="contentImg3">
				           		<p class="donation_img_upload_text" id="img_text4">이미지 등록</p>
				           	</div>
	        			</div>
				        <div id="fileArea">
				        	<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this, 1)">
			                <input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this, 2)">
			                <input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this, 3)">
			           		<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this, 4)">
			            </div>    
	                        
	                        
	                
                    </div>
                </div>
                <input type="submit" style="display: none;" id="write_submit">
            </form>
        </div>

    </main>
    <%@ include file="../common/footer.jsp" %>

    <script>
    
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
        	
        	$('#img_text1').click(function(){
        		$('#thumbnailImg1').click();
        	});
        	$('#img_text2').click(function(){
        		$('#thumbnailImg2').click();
        	});
        	$('#img_text3').click(function(){
        		$('#thumbnailImg3').click();
        	});
        	$('#img_text4').click(function(){
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
        	$('#write_submit').click();
        }
    </script>
</body>
</html>