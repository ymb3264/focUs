<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.*, java.sql.Date" %>
<%
	Board b= (Board)request.getAttribute("board");
	String mAdd= (String)request.getAttribute("mAdd");
	Date dat=(Date)request.getAttribute("dat");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/0_main.css">
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>게시판 글쓰기</title>

</head>
<body>
    <header class="board_header">
        <div class="header-box">
            <div class="font_main_title header-title">게시판 글작성하기 </div>
            <button class="submitBtn btn" onclick="submit();">등록하기</button>
        </div>
    </header>
    <main>

        <div class="post-container">
            <div class="note-wrap">
                <div class="note-title">
                    <span class="main-color point">!</span><span class="font_main_title">게시글 작성시 유의하여주세요.</p></span>
                </div>
                <div class="note-content">
                    <ul>
                        <li class="font_content">자유롭게 글을 쓰는 공간입니다.</li>
                        <li class="font_content">#태그를 적극 활용해보세요 </li>
                        <li class="font_content">우리집 반려견 자랑,간식나눔 등 자유롭게 글을 작성해주세요.</li>
                        <li class="font_content">지나친 광고 와 남을 비방하는 글은 삼가하여주세요 </li>
                        <li class="font_content">규반위정글은 삭제될 수 있습니다.</li>
                    </ul>
                </div>  
            </div>

            <form action="<%= request.getContextPath() %>/insertBoard.bo" method="post" encType="multipart/form-data">
                <div class="area-wrap">
                    <div class="OOTC">
                       <div class="input-container">
                           <div class="input-wrap">
                               <input type="text" class="OOTC-input input-title" name="title" placeholder="제목을 입력하세요.">
 
                           </div>
                            <div class="input-wrap">
                                <input type="text" class="OOTC-input input-place" id="location" name="location" placeholder="동네를 알려주세요." onclick="findPostcode();" readonly>
                                <input type="hidden" id="postcode" name="postcode">
                                <input type="date" class="OOTC-input input-date" name="bw_date" value="<%= dat%>" readonly>
                                <input type="hidden" id="xAddress" name="xAddress">
								<input type="hidden" id="yAddress" name="yAddress">
                            </div>
                            <div class="input-wrap input-wrap2">
                                <input type="text" id="note" class="OOTC-input input-insta" name="etc" placeholder="인스타주소를 입력하세요(선택)">
                                <input type="text" id="note" class="OOTC-input input-youtube" name="youtube" placeholder="유튜브주소를 입력하세요(선택)">
                            </div>
                       </div>
                    </div>
    
                    <div class="area">
                        <textarea name="content" class="board_content_area" placeholder="내용을 입력하세요."></textarea>
                        <div class="area-line"></div>
                        <div class="board_thumbnail_box">
			            	<div class="board_thumbnail_img_box">
			            		<img class="board_thumbnail_img" id="titleImg">
			            		<p class="board_img_upload_text" id="img_text1">썸네일 이미지 등록</p>
				            </div>
				           	<div class="board_thumbnail_img_box">
				           		<img class="board_thumbnail_img" id="contentImg1">
				           		<p class="board_img_upload_text" id="img_text2">이미지 등록</p>
				           	</div>
				           	<div class="board_thumbnail_img_box">
				           		<img  class="board_thumbnail_img" id="contentImg2">
				           		<p class="board_img_upload_text" id="img_text3">이미지 등록</p>
				           	</div>
				           	<div class="board_thumbnail_img_box">
				           		<img class="board_thumbnail_img" id="contentImg3">
				           		<p class="board_img_upload_text" id="img_text4">이미지 등록</p>
				           	</div>
	        			</div>
				        <div id="fileArea">
				        	<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this, 1)">
			                <input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this, 2)">
			                <input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this, 3)">
			           		<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this, 4)">
			            </div>
                        <input type="text" id="note" class="OOTC-input input-tag" placeholder="#태그" name="tag">
                    </div>
                </div>
                <input type="submit" style="display:none;" id="write_submit">
            </form>
        </div>
        
        <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
			<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		</div>

    </main>
    
   	<%@ include file="../common/footer.jsp" %>

    <script>
<%--     	console.log(<%= request.getSession().getAttribute("userX") %>); --%>
<%--     	console.log(<%= request.getSession().getAttribute("userY") %>); --%>
    	
        window.addEventListener('scroll', function() {
            var scrollY = window.scrollY;
            var header = document.querySelector(".board_header");
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
        		
        		reader.readAsDataURL(value.files[0]);
        	}
        	
        }
        	function submit(){
        		$('#write_submit').click();
        	}
    </script>
    
    <script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function findPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
//                     if(extraAddr !== ''){
//                         extraAddr = ' (' + extraAddr + ')';
//                     }
                    // 조합된 참고항목을 해당 필드에 넣는다.
//                     document.getElementById("sample2_extraAddress").value = extraAddr;
                
                } else {
//                     document.getElementById("sample2_extraAddress").value = '';
                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("location").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("note").focus();
	
	                // iframe을 넣은 element를 안보이게 한다.
	                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
	                element_layer.style.display = 'none';
	            },
	            width : '100%',
	            height : '100%',
	            maxSuggestItems : 5
	        }).embed(element_layer);
	
	        // iframe을 넣은 element를 보이게 한다.
	        element_layer.style.display = 'block';
	
	        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
	        initLayerPosition();
	    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
	    function initLayerPosition(){
	        var width = 300; //우편번호서비스가 들어갈 element의 width
	        var height = 400; //우편번호서비스가 들어갈 element의 height
	        var borderWidth = 5; //샘플에서 사용하는 border의 두께
	
	        // 위에서 선언한 값들을 실제 element에 넣는다.
	        element_layer.style.width = width + 'px';
	        element_layer.style.height = height + 'px';
	        element_layer.style.border = borderWidth + 'px solid';
	        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
	        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
	        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
	    }
    
	 // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
	    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
	    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
	    function initLayerPosition(){
	        var width = 300; //우편번호서비스가 들어갈 element의 width
	        var height = 400; //우편번호서비스가 들어갈 element의 height
	        var borderWidth = 5; //샘플에서 사용하는 border의 두께

	        // 위에서 선언한 값들을 실제 element에 넣는다.
	        element_layer.style.width = width + 'px';
	        element_layer.style.height = height + 'px';
	        element_layer.style.border = borderWidth + 'px solid';
	        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
	        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
	        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
	    }
	    
	</script>
	<script>
		$(document).on('focusout', '#note', function(){
			var address =  $('#location').val(); 
			$.ajax({
				method: "GET",
				url: "https://dapi.kakao.com/v2/local/search/address.json",
				data: {query:address},
				headers: {Authorization: "KakaoAK c118dc0a038e50d48250a86bbf30e953" }
			})
			.done(function (msg) {
				var x = msg.documents[0].address.x;
				var y = msg.documents[0].address.y;
				$('#xAddress').val(x.substr(0, 8));
				$('#yAddress').val(y.substr(0, 8));
			});
		});
	</script>
</body>
</html>