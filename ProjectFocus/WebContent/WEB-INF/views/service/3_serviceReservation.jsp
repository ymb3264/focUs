<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.*, service.model.vo.Service, member.model.vo.Member"%>
<%

	Board b = (Board)request.getAttribute("b");
	Service s = (Service)request.getAttribute("s");
	Member m = (Member)request.getAttribute("m");
	ArrayList<Image> iList = (ArrayList<Image>)request.getAttribute("iList");
	Board br = (Board)request.getAttribute("br");
	Service sr = (Service)request.getAttribute("sr");

 	System.out.println(b.getbWriter());
// 	System.out.println(m.getmNick());
// 	System.out.println(b.getbNo());
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>focUs</title>
    <link rel="stylesheet" href="css/service.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumBarunGothic@1.0/nanumbarungothicsubset.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
    
<%@ include file="../common/header.jsp" %>
    <main class="serviceReservation_main">
     
        <div class="reservation_container">
            <div class="reservation_wrap">
                <div class="reservation_box">
                    <div class="reservation_img_box">
                        <img src="<%=request.getContextPath() %>/service_uploadFiles/<%= iList.get(0).getiChange() %>" alt="" class="reservation_img">
                    </div>
                    <div class="reservation_title" name="title">[<%= s.getsCatename() %>] <%= b.getbTitle() %></div>
                </div>
                <div class="reservation_box">
                    <div class="reservation_guide">봉사예약내역</div>
                    <div class="reservation_info_wrap">
                        <div class="reservation_info_box">
                            <div class="reservation_info reservation_info1">예약자</div>
                            <div class="reservation_info reservation_info1">봉사일</div>
                            <div class="reservation_info reservation_info1">장소</div>
                        </div>
                        <div class="reservation_info_box reservation_info_box2">
                            <div class="reservation_info reservation_info2"><%= loginUser.getmName() %></div>
                            <div class="reservation_info reservation_info2" name="time"><%= s.getsTime() %></div>
                            <div class="reservation_info reservation_info2" name="location"><%= s.getsLocation() %></div>
                        </div>
                    </div>
                    
                  
                    <div class="reservation_info_wrap reservation_info_wrap2">
                    	<div class="reservation_info_box">
							<div class="reservation_info reservation_info1">봉사기관 / 개인</div>
							<div class="reservation_info reservation_info1">연락처</div>                    		
                    	</div>
                    	<div class="reservation_info_box reservation_info_box2">
							<div class="reservation_info reservation_info2" name="mNick"><%= m.getmNick() %></div>
							<div class="reservation_info reservation_info2" name="mPhone"><%= m.getmPhone() %></div>                    		
                    		
                    	</div>
                    </div>
                    
                    <div class="reservation_note_wrap">
                        <div class="reservation_note_box note_box">
                            봉사에 신청한 인원이 정원보다 초과하거나, 변경사항이 생길 경우, 자동으로 예약이 취소될 수 있습니다.
                        </div>
                        <div class="reservation_note_box note_box">
                            봉사예약에 대한 자세한 문의는 focUs로 문의해주시기 바랍니다.
                        </div>
                        <div class="reservation_note_box note_box2">
                            <input type="checkbox" name="check" id="check"><span class="reservation_note">위 사항을 인지 후, 선택하신 봉사가 맞는지 다시한번 확인해주시기 바랍니다.</span>
                        </div>
                   <form action="<%= request.getContextPath() %>/reserveSuccess.bo" method="post">
                   			<input type="hidden" name="bNo" id="bNo" value="<%= b.getbNo() %>">
							<input type="hidden" name="title" value="<%= b.getbTitle() %>">
							<input type="hidden" name="writer" value="<%= b.getbWriter() %>">
						 	<input type="hidden" name="location" value="<%= s.getsLocation() %>">
						 	<input type="hidden" name="time" value="<%= s.getsTime() %>">
						 	<input type="hidden" name="catename" value="<%= s.getsCatename() %>">
						 	
						 	<input type="submit" class="reserve_rsSubmit">
						
                        </form>     
                        <div class="reservation_btn" id="reservation_btn">예약하기
                        </div>
                    </div>
                </div>
            </div>
        </div>
  
    </main>
    
    
		<%@ include file="../common/footer.jsp" %>
    
    
      <script>
        var check = document.querySelector('#check');
        var btn = document.getElementById('reservation_btn');
		
        
        
        window.onload = function() {
        	
            check.addEventListener('click', function() {
                if (check.checked == true) {
                    btn.classList.add('active');
                    btn.addEventListener('click', function() {
		    		document.querySelector('.reserve_rsSubmit').click();
                        
                    })
                } else {
                    btn.classList.remove('active');
                }
                
            })
	        
        }
 
        
    </script>
    
    <script>
    	
    </script>
</body>
</html>