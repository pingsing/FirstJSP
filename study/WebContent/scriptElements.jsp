<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	//선언문
	//변수나 메소드 정의
	
	int sum = 0;
	public int add(int x, int y){
		sum = x + y;
		return sum;
	}
%>

<%	
	//스크립틀릿
	//변수나 실행문 정의 (메소드 정의 불가)
	
	int result = add(10, 20);
%>
결과 : 
<%= result %>
<!-- 표현식(자바의 결과 출력) -->
<br>
결과 :
<%= add(50, 60) %>
</body>
</html>