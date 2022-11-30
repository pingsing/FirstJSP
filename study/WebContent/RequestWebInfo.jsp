<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");	//한글 깨짐 방지
	String eng = request.getParameter("eng");		//클라이언트가 보낸 파라메터 정보를 받는 메소드.
	String han = request.getParameter("han");		//받아온 요청정보를 변수처리 할 수 있다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1. 클라이언트와 서버의 환경정보 읽기</h2>
	<ul>
		<li><%=eng %></li>
		<li><%=han %></li>
	</ul>
</body>
</html>