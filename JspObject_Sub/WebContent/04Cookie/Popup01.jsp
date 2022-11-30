<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   String popupMode = "on";

   // 1. 요청 헤더의 모든 쿠키 얻기.
   Cookie[] cookies = request.getCookies(); // getCookies() -> 타입이 Cookie[] 
   // 2. 쿠키 정보가 있는지 확인
   if(cookies!=null){
      for(Cookie c : cookies){
         String cookieName = c.getName();
         String cookieValue = c.getValue();
         out.print(" 쿠키 이름 : " + cookieName);
         out.print(" 쿠키 값 : " + cookieValue);
         if(cookieName.equals("PopupClose")){
            popupMode = cookieValue;
         }      
      }
   }else{
      out.print("쿠키가 없습니다.");
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
   $(function() {
      $("#closeBtn").click(function() { // 닫기 버튼을 클릭하면
         $("#popup").hide(); // 팝업창이 숨겨진다.
         let chkVal = $("input:checkbox[id=inactiveToday]:checked").val(); // 최종 목적은 값 1을 가져오는 것이다.
         $.ajax({
            url:'./PopupCookie1.jsp',
            type:'get',
            data:{inactiveToday:chkVal}, // get방식으로 넘어가는 파라메터가 된다. 
            dataType:"text",
            success:function(resData){
               if(resData != ''){
                  location.reload(); // 브라우저의 새로고침 기능 
               }
            }
         });
      });
   });

</script>
<style type="text/css">
    div#popup{
        position: absolute; top:100px; left:100px; color:yellow;  
        width:300px; height:100px; background-color:gray;
    }
    div#popup>div{
        position: relative; background-color:#ffffff; top:0px;
        border:1px solid gray; padding:10px; color:black;
    }
</style>
</head>
<body>
   <h2>팝업 메인 페이지</h2>
   <%
      out.print("팝업창은" + popupMode + "상태입니다.");
   %>
   <%if(popupMode.equals("on")) {%>
   <div id="popup">
      <h2 align="center">공지사항 팝업입니다.</h2>
      <div align="right">
         <form name="popFrm">
            <input type="checkbox" id="inactiveToday" value="1" />
            하루 동안 열지 않음
            <input type="button" value="닫기" id="closeBtn" />
         </form>
      </div>
   </div>
   <% } %>
</body>
</html>