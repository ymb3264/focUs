<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>focUs || SignUp</title>    
	<link rel="stylesheet" href="css/0_main.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
    <div class="signup_container">
        <div class="signUp_logo_wrap"></div>
        <div class="signUp_form_wrap">
            <form class="signUp_form" action="<%= request.getContextPath() %>/insert.me" method="post" onsubmit="return insertValidate() ">
                <div class="positioning_wrap">

                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="userId" required name="id">
                        <label for="userID" class="signUp_input_label">아이디</label>
                        <span class="signUp_check signUp_check_id" id="idResult" style="display: none;">사용 가능한 아이디입니다.</span>
                    </div>
                    <div class="signUp_positioning">
                        <input type="password"  class="join_input" id="userPw" required name="pw" onchange="validate();">
                        <label for="userPW" class="signUp_input_label">비밀번호 (영문대소문자와 숫자만 입력가능)</label>
                        <span class="signUp_check signUp_check_pw" id="pwResult1" style="display: none;">사용 가능한 비밀번호입니다.</span>
                    </div>
                    <div class="signUp_positioning">
                        <input type="password"  class="join_input" id="checkPw" required>
                        <label for="checkPW" class="signUp_input_label">비밀번호 확인</label>
                        <span class="signUp_check signUp_check_pw" id="pwResult2" style="display: none;">비밀번호가 일치합니다.</span>
                    </div>
                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="userName" required name="name">
                        <label for="userName" class="signUp_input_label">이름</label>
                    </div>
                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="nickName" required name="nick">
                        <label for="nickName" class="signUp_input_label">닉네임</label>
                        <span class="signUp_check signUp_check_nick" style="display: none;">사용 가능한 닉네임입니다.</span>
                    </div>
                    <div class="signUp_positioning">
                        <input type="tel"  class="join_input" id="phone" required name="phone">
                        <label for="userName" class="signUp_input_label">전화번호 ('-'제외하여 숫자만 입력해주세요.)</label>
                    </div>
                    <div class="signUp_positioning">
                        <input type="email"  class="join_input" id="email" required name="email">
                        <label for="userName" class="signUp_input_label">이메일</label>
                    </div>
                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="postcode" required name="postcode" onclick="findPostcode();" readonly>
                        <label for="userName" class="signUp_input_label">우편번호</label>
                    </div>
                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="address" required name="address">
                        <label for="userName" class="signUp_input_label">주소</label>
                    </div>
                    <div class="signUp_positioning">
                        <input type="text"  class="join_input" id="address2" required name="address2">
                        <label for="userName" class="signUp_input_label">상세주소</label>
                    </div>
                </div>
				<input type="hidden" id="xAddress" name="xAddress">
				<input type="hidden" id="yAddress" name="yAddress">				 
            	<input type="submit" class="btn join_btn" value="가입하기">
            </form>
        </div>
    </div>
	
	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
	<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>
    
    <script>
    	var isIdUsable = false;
    	var isIdChecked = false;
    	var isValidate = false;
    	var isValidateChecked = false;
    	var isPwUsable = false;
    	var isPwChecked = false;
    	var isNickUsable =false;
    	var isNickChecked = false;	
    	
    	$('#userId').on('change paste keyup', function(){
    		isIdChecked = false;
    	});   	
    	
    	$('#userId').on('change', function(){
    		var userId = $('#userId');
    		
    		$.ajax({
    			url: 'checkId.me',
    			data: {inputId:userId.val()},
    			success: function(data){
    				console.log(data);
    				
    				if(data == 'success'){
						$('#idResult').text('사용 가능합니다.');
						$('#idResult').css({'color':'green', 'display':'inline'});
						isIdUsable = true;
						isIdChecked = true;
					} else{
						$('#idResult').text('사용 불가능합니다.');
						$('#idResult').css({'color':'red', 'display':'inline'});
						userId.focus();
						isIdUsable = false;
						isIdChecked = false;
					}
    			}
    		});
    	});  
    	
    	function validate() {
			var regExp = /[a-zA-Z0-9]/;
			var pno = document.getElementById('userPw').value;
			
			if(regExp.test(pno)) {
				$('#pwResult1').text('사용 가능한 비밀번호입니다.');
				$('#pwResult1').css({'color':'green', 'display':'inline'});	
				isValidate = true;
		    	isValidateChecked = true;
			} else {
				$('#pwResult1').text('사용 불가능한 비밀번호입니다.');
				$('#pwResult1').css({'color':'red', 'display':'inline'});
				$('#userPw').focus();
				isValidate = false;
		    	isValidateChecked = false;
			}
		}
    	
    	$('#checkPw').on('change', function() {
	    	var userPw = $('#userPw').val();
    		var checkPw = $('#checkPw').val();
	    	
    		if(checkPw == '' || userPw == '') {    	
    			$('#pwResult2').css({'display':'none'});	
    		} else {
	    		if(userPw == checkPw) {
	    			$('#pwResult2').text('비밀번호가 일치합니다.');
					$('#pwResult2').css({'color':'green', 'display':'inline'});
					isPwUsable = true;
			    	isPwChecked = true;
	    		} else {
	    			$('#pwResult2').text('비밀번호가 일치하지 않습니다.');
					$('#pwResult2').css({'color':'red', 'display':'inline'});
					$('#checkPw').focus();
					isPwUsable = false;
			    	isPwChecked = false;
	    		}
    		}
    		
    	});
    	

		function insertValidate() {
			if(isIdUsable && isIdChecked && isValidate && isValidateChecked && isPwUsable && isPwChecked) {
				return true;
			} else {
				alert('회원가입에 실패하였습니다.');
				return false;
			}
	
			
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
	                document.getElementById("address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("address").focus();
	
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
	 
	    $('#address').on('focusout', function(){
			var address =  $(this).val(); 
			$.ajax({
				method: "GET",
				url: "https://dapi.kakao.com/v2/local/search/address.json",
				data: {query:address},
				headers: {Authorization: "KakaoAK c118dc0a038e50d48250a86bbf30e953" }
			})
			.done(function (msg) {
// 				console.log(msg.documents[0].address.x);
				var x = msg.documents[0].address.x;
				var y = msg.documents[0].address.y;
				$('#xAddress').val(x.substr(0, 8));
				$('#yAddress').val(y.substr(0, 8));
// 				console.log(x);
// 				console.log($('#xAddress').val());
			});
		});
	</script>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</body>
</html>