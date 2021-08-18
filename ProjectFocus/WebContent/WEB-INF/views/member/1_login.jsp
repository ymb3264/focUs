<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fail = (String)request.getAttribute("fail");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>focUs || login</title>
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<body>

    <div class="login_container">
        <div class="login_wrap">
            <div class="login_logo_wrap">
                <div class="login_logo"></div>
            </div>
            <div class="login_form_wrap">
                <form class="login_box" action="<%= request.getContextPath() %>/selectLogin.me" method="post">
                    <div class="login_input_wrap">
                        <div class="login_positioning">
                            <input type="text" class="login_input" id="userId" name="userId" required>
                            <label for="userID" class="login_input_label">아이디</label>
                        </div>
                        <div class="login_positioning">
                            <input type="password" class="login_input password_input" id="userPW" name="userPw" required>
                            <label for="userPW" class="login_input_label">비밀번호</label>
                        </div>
                    </div>
	                <input type="submit" class="login_btn btn" value="로그인">
	                
	                <div class="click_login">
	                    <a class='enter signUp'>가입하기</a>
	                    <a class='enter'>아이디 찾기</a>
	                    <a class='enter'>비밀번호 찾기</a>
	                </div>
	                <input type="hidden" id="user_XLocation" name="user_XLocation">
				    <input type="hidden" id="user_YLocation" name="user_YLocation">
                </form>
            </div>
        </div>
    </div>
	<script>
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude.toString();
				var lon = position.coords.longitude.toString();
				$('#user_XLocation').val(lat.substr(0, 8));
				$('#user_YLocation').val(lon.substr(0, 8));
			});
		}
		
		$('.signUp').on('click', function() {
			location.href="<%= request.getContextPath() %>/joinMembership.go";
		});

		$(function(){
			<% if(fail != null) { %>
				alert("<%= fail %>");
			<% } %>
		});
		
		$('.login_logo').on('click', function(){
			location.href="<%= request.getContextPath() %>";
		});
		
		
	</script>
</body>
</html>