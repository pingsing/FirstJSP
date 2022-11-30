<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");	// 한글 깨짐 방지
	String id = request.getParameter("id");
	String sex = request.getParameter("sex");
	
	//checkbox는 배열형태로 전달된다.
	String favoStr ="";
	String[] favo = request.getParameterValues("favo");
	for(String fa : favo){
		favoStr += fa + ", ";
	}
	
	String intro = request.getParameter("intro");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><%=id %></li>
		<li><%=sex %></li>
		<li><%=favo %></li>
		<li><%=intro %></li>
	</ul>
</body>
</html>