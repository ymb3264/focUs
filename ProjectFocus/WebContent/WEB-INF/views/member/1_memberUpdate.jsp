<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
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
</head>
<body>
    <div class="signup_container">
        <div class="signUp_logo_wrap"></div>
        <div class="signUp_form_wrap">
            <form class="signUp_form" action="<%= request.getContextPath() %>/updateMember.me" method="post">
                <div class="signUp_positioning">
                    <input type="text"  class="join_input" id="userID" required name="id" readonly value="<%= loginUser.getmId() %>">
                    <label for="userID" class="signUp_input_label input_read">아이디</label>
                </div>
                <div class="signUp_positioning">
                    <input type="text"  class="join_input" id="userName" required name="name" value="<%= loginUser.getmName() %>">
                    <label for="userName" class="signUp_input_label">이름</label>
                </div>
                <div class="signUp_positioning">
                    <input type="text"  class="join_input" id="nickName" required name="nick" value="<%= loginUser.getmNick() %>">
                    <label for="nickName" class="signUp_input_label">닉네임</label>
                </div>
                <div class="signUp_positioning">
                    <input type="tel"  class="join_input" id="phone" required name="phone" value="<%= loginUser.getmPhone() %>">
                    <label for="userName" class="signUp_input_label">전화번호 ('-'제외하여 숫자만 입력해주세요.)</label>
                </div>
                <div class="signUp_positioning">
                    <input type="email"  class="join_input" id="email" required name="email" value="<%= loginUser.getmEmail() %>">
                    <label for="userName" class="signUp_input_label">이메일</label>
                </div>
                <div class="signUp_positioning">
                    <input type="text"  class="join_input" id="address" required name="address" value="<%= loginUser.getmAddress() %>">
                    <label for="userName" class="signUp_input_label">주소</label>
                </div>

            	<input type="submit" class="btn join_btn" value="수정하기">
<!--                 <div class="signUp_btn_wrap"> -->
<!--         		</div> -->
            </form>
        </div>
        
    </div>
</body>
</html>