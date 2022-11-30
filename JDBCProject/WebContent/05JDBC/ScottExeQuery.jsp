<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="common.JDBConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scott</title>
</head>
<body>
	<h2>Scott 테이블 정보 조회</h2>
	
	<%	
		String driver = application.getInitParameter("OracleDriver");
	 	String url = application.getInitParameter("OracleURL");
    	
    	
		JDBConnect jdbc = new JDBConnect(driver, url, "scott", "tiger");
		
		/* String updatesql = "update emp01 set deptno = ? where deptno = ?";
		PreparedStatement psmt2 = jdbc.con.prepareStatement(updatesql);
		psmt2.setInt(1, 10);
		psmt2.setInt(2, 30);
		
		psmt2.executeUpdate(); */
		
		String sql = "select * from emp01";
		PreparedStatement psmt = jdbc.con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
	%>
	<table border="1">
		<th>empno</th>
		<th>ename</th>
		<th>job</th>
		<th>mgr</th>
		<th>hiredate</th>
		<th>sal</th>
		<th>comm</th>
		<th>deptno</th>
	<%
		while (rs.next()) {
			int empno = rs.getInt("empno");		// = rs.getString(1), 가능한한 컬럼명으로 작성할 것.
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int mgr = rs.getInt("mgr");
			java.sql.Date hiredate = rs.getDate("hiredate");
			int sal = rs.getInt("sal");
			int comm = rs.getInt("comm");
			int deptno = rs.getInt("deptno");
			%>
			<tr>
				<td><% out.println(String.format("%s", empno)); %></td>
				<td><% out.println(String.format("%s", ename)); %></td>
				<td><% out.println(String.format("%s", job)); %></td>
				<td><% out.println(String.format("%s", mgr)); %></td>
				<td><% out.println(String.format("%s", hiredate)); %></td>
				<td><% out.println(String.format("%s", sal)); %></td>
				<td><% out.println(String.format("%s", comm)); %></td>
				<td><% out.println(String.format("%s", deptno)); %></td>
			</tr>
			
		<% }
		jdbc.close();
	%>
	</table>
	
	
</body>
</html>