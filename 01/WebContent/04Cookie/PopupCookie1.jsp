<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 쿠키 생성작업을 하는 서버임 -->

<%
   String chkVal = request.getParameter("inactiveToday");

   if(chkVal != null && chkVal.equals("1")){ 
   // 1. 쿠키 객체를 생성한다.
   Cookie cookie = new Cookie("PopupClose", "off");
   // 2. 쿠키의 생성 경로 설정
   cookie.setPath(request.getContextPath());
   // 3. 유지시간 - 1시간으로 설정
   cookie.setMaxAge(60*60*24);
   // 4. 응답 헤더에 쿠키 정보 저장
   response.addCookie(cookie);
   
   out.print("<h1>하루동안 창 열기 금지</h1>");
   }

%>