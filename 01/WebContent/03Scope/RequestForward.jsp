<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person"%>
<%
	String requestString = (String)request.getAttribute("requestString");
	Person person = (Person)request.getAttribute("requestPerson");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=requestString %>
	<br />
	<%=person.getName() %>
	<%=person.getAge() %>
</body>
</html>