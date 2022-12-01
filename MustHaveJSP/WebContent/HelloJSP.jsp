<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	<!-- 지시어 -->
<!-- language : 스크립팅 언어는 자바를 사용합니다.
contentType : 문서의 타입, 즉 MIME 타입은 text/html이고, 캐릭터셋은 UTF-8입니다.
pageEncoding : 소스 코드의 인코딩 방식은 UTF-8입니다. -->
<%
String str1 = "JSP";
String str2 = "안녕하세요..";	// 스크립트 요소(선언부)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloJSP</title>
</head>
<body>
	<h2>처음 만들어보는 <%= str1 %></h2>		<!-- 스크립트 요소(표현식) -->
	<p>
		<%
		out.println(str2 + str1 + "입니다. 열공합시다^^*");	// 스크립트 요소(스크립틀릿)
		%>
	</p>
</body>
</html>