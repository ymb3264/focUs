<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="css/0_main.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
			<!-- 왼쪽 상단 로고 -->
			<div class="cate_logo" id="header_logo"></div>
			
			<script>
			$('#header_logo').on('click', function() {
				location.href="<%= request.getContextPath() %>";
			});
			</script>
			
			<!-- 카테고리 -->
			<div class="cate_category">
			   <a href="<%= request.getContextPath() %>/selectDonationList.bo"><div class="cate_item">후원하기</div></a>
			   <a href="<%= request.getContextPath() %>/selectServiceList.bo"><div class="cate_item">봉사하기</div></a>
			   <a href="<%= request.getContextPath() %>/selectBoardList.bo"><div class="cate_item">자유게시판</div></a>
			   <a href="<%= request.getContextPath() %>/selectGoodsList.sl"><div class="cate_item">굿즈</div></a>			   
			</div>
			<!-- 로그인으로 하이퍼링크 -->
			
			<% if(loginUser != null){ %>
				<div class="user_box">
					<i class="fas fa-user header-user"></i>
					<div class="mypage_text">마이페이지</div>
				</div>
				<div class="mypage_box">
					<div class="user_name"><span class="user_name_bold"><%= loginUser.getmName() %></span> 님</div>
					<div class="mypage_wrap">
						<i class="fas fa-user-circle"></i>
						<div class="mypage_opt header_profile">프로필</div>
					</div>
					<div class="mypage_wrap">
						<i class="fas fa-cog"></i>
						<div class="mypage_opt header_set">수정</div>
					</div>
					<div class="mypage_line"></div>
					<div class="mypage_wrap">
						<i class="fas fa-sign-out-alt"></i>
						<div class="mypage_opt header_logout">로그아웃</div>
					</div>
				</div>
			<% } else { %>
				<div class="login_box" onclick="location.href='<%= request.getContextPath() %>/login.go'">
					<i class="fas fa-sign-in-alt"></i>
					<div class="login_text">로그인</div>
				</div>
			<% } %>
		</header>
		
		<script>
        var myPage = document.querySelector('.user_box');
        var myPageBox = document.querySelector('.mypage_box');

        myPage.addEventListener('click', function() {
            myPageBox.classList.toggle('active');
        });
		
		$('.header_profile').on('click', function() {
			location.href="<%= request.getContextPath() %>/myPageSelect.me";
		});

		$('.header_set').on('click', function() {
			location.href="<%= request.getContextPath() %>/myPageUpdate.go";
		});
		
		$('.header_logout').on('click', function() {
			location.href="<%= request.getContextPath() %>/logout.me";
		});
    </script>
</body>
</html>