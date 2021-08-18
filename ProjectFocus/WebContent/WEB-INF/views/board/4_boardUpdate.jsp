<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, java.sql.Date " %>
<%
	Board b = (Board)request.getAttribute("b");
	String[] imgChange =(String[])request.getAttribute("imgChange");
	String[] imgOrigin = (String[])request.getAttribute("imgOrigin");
	String[] imgNo = (String[])request.getAttribute("imgNo");
	String[] tag = (String[])request.getAttribute("tag");
 	Date dat=(Date)request.getAttribute("dat");
 	String tagNames = "";
 	for(int i = 0; i < tag.length; i++) {
 		tagNames += ("#" + tag[i]);
 	} 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/board.css">
<title>게시판 글수정</title>
</head>
<style type="text/css">
@import "0_main.css";
</style>
<body>
 <header class="board_write_header">
        <div class="header-box">
            <div class="font_main_title header-title">게시판 글수정페이지</div>
            <button class="submitBtn btn" onclick="submit();">수정하기</button>
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

            <form action="<%= request.getContextPath() %>/updateBoard.bo" method="post" encType="multipart/form-data">
                <div class="area-wrap">
                    <input type="hidden" name="bNo" value="<%= b.getbNo() %>">
                    
                    <div class="OOTC">
                       <div class="input-container">
                           <div class="input-wrap">
                               <input type="text" class="OOTC-input input-title" name="title" placeholder="제목을 입력하세요." value="<%= b.getbTitle() %>"> 
                           </div>
                            <div class="input-wrap">
                                <input type="text" class="OOTC-input input-place" name="bw_place" value="<%= b.getbLocation() %>" readonly>
                                <input type="text" class="OOTC-input input-date" name="bw_date" value="<%= dat %>" readonly>
                            </div>
                            <div class="input-wrap input-wrap2">
                             <input type="text" id="note" class="OOTC-input input-insta" name="etc" placeholder="인스타주소를 입력하세요(선택)" value="<%= b.getbETC() %>">
                             <input type="text" id="note" class="OOTC-input input-youtube" name="youtube" placeholder="유튜브주소를 입력하세요(선택)" value="<%= b.getbYoutube() %>">
                            </div>
                       </div>
                    </div>
    
                    <div class="area">
                        <textarea name="content" class="board_content_area" placeholder="내용을 입력하세요." ><%= b.getbContent() %></textarea>
                        <div class="area-line"></div>
                        <div class="board_thumbnail_box">
			            	<div class="board_thumbnail_img_box">
			            		<img class="board_thumbnail_img" id="titleImg"
			            		src="<%= request.getContextPath() %>/board_uploadFiles/<%= imgChange[0] %>">
<!-- 			            		<p class="board_img_upload_text" id="img_text1">썸네일 이미지 등록</p> -->
				            </div>
				           	<div class="board_thumbnail_img_box">
				           		<img class="board_thumbnail_img" id="contentImg1"
				           		<% if(imgChange.length >= 2) { %>
				           			src="<%= request.getContextPath() %>/board_uploadFiles/<%= imgChange[1] %>"
				           		<%} %>>
				           		<% if(imgChange.length < 2) { %>
				           			<p class="board_img_upload_text" id="img_text2">이미지 등록</p>
				           		<% } %>
				           	</div>
				           	<div class="board_thumbnail_img_box">
				           		<img  class="board_thumbnail_img" id="contentImg2"
				           		<% if(imgChange.length >= 3) {%>
				           		src="<%= request.getContextPath() %>/board_uploadFiles/<%= imgChange[2] %>"
				           		<% } %>>
				           		<%if(imgChange.length < 3) { %>
				           		<p class="board_img_upload_text" id="img_text3">이미지 등록</p>
				           		<% } %>
				           	</div>
				           	<div class="board_thumbnail_img_box">
				           		<img class="board_thumbnail_img" id="contentImg3"
				           		<%if(imgChange.length >= 4 ) { %>
				           		src="<%= request.getContextPath() %>/board_uploadFiles/<%= imgChange[3] %>"
				           		<% } %>>
				           		<% if(imgChange.length < 4) { %>
				           		<p class="board_img_upload_text" id="img_text4">이미지 등록</p>
				           		<% } %>
				           	</div>
	        			</div>
				        <div id="fileArea">
				        	<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this, 1)">
			                <input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this, 2)">
			                <input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this, 3)">
			           		<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this, 4)">
			            	
			            	<input type="hidden" name="imgNo" value="<%= imgNo[0] %>">
			           		<% if(imgChange.length >= 2) { %>
			           			<input type="hidden" name="imgNo" value="<%= imgNo[1] %>">
			           		<% } %>
			           		<% if(imgChange.length >= 3) { %>
			           			<input type="hidden" name="imgNo" value="<%= imgNo[2] %>">
			           		<% } %>
			           		<% if(imgChange.length >= 4) { %>
			           			<input type="hidden" name="imgNo" value="<%= imgNo[3] %>">
			           		<% } %>
			            
			            </div>
                        <input type="text" id="note" class="OOTC-input input-tag" value="<%= tagNames %>">
                    </div>
                </div>
                <input type="submit" style="display:none;" id="write_submit">
            </form>
        </div>

    </main>
   
		<%@ include file="../common/footer.jsp" %>

    <script>
        window.addEventListener('scroll', function() {
            var scrollY = window.scrollY;
            var header = document.querySelector(".header");
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
        				console.log($('#thumbnailImg1').val());
        				$('#text1').hide();
        				break;
        			case 2:
        				$('#contentImg1').attr('src', e.target.result);
        				$('#text2').hide();
        				break;
        			case 3:
        				$('#contentImg2').attr('src', e.target.result);
        				$('#text3').hide();
        				break;
        			case 4:
        				$('#contentImg3').attr('src', e.target.result);
        				$('#text4').hide();
        				break;
        			}
        		}
        		
        		reader.readAsDataURL(value.files[0]);
        	}
        }
        
        function submit() {
        	$('#write_submit').click();
        }
    </script>

</body>
</html>