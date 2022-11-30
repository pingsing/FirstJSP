<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - param</title>
</head>
<body>
	<%-- <jsp:param>은 <jsp:include>나 <jsp:forward>를 사용할 때 다른 페이지에 값을 전달해주는 액션 태그이다. --%>
	<%-- 
	
	<jsp:forward page="ParamForward.jsp">
		<jsp:param name="param1" value="임꺽정" />
		<jsp:param name="param1" value="임꺽정"></jsp:param>
		
		<jsp:param name="param2" value="10" />
		<jsp:param name="param2" value="10"></jsp:param>
	</jsp:forward> 
	--%>
	
	<jsp:include page="inc/ParamInclude.jsp">
		<jsp:param value="강원도" name="Loc1" />
		<jsp:param value="부산" name="Loc2" />
	</jsp:include>
</body>
</html>