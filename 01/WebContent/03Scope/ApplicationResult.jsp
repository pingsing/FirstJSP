<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="common.Person" %>
<%
	Map<String, Person> maps = (Map<String, Person>)application.getAttribute("maps");
	Set<String> keys = maps.keySet();
	
	for(String key : keys){
		Person person = maps.get(key);
		out.print(person.getName());
		out.print(person.getAge());
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>