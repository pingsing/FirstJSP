<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Date today = new Date();
	int hour = today.getHours();
	//out.print(hour);
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
	out.print(sf.format(today));
%>
</body>
</html>