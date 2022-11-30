<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="common.JDBConnect" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "insert into member values(?, ?, ?, sysdate)";		// 동적 쿼리문
	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	psmt.setString(1, "test1");
	psmt.setString(2, "1111");
	psmt.setString(3, "회원1");
	
	int result = psmt.executeUpdate();	// 쿼리문 실행
	if(result != 0){
		out.print("쿼리문 실행 완료");
	}else {
		out.print("실패");
	}
	
	jdbc.close();
	%>
</body>
</html>