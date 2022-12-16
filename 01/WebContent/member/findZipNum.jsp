<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{   
   background-color:#B96DB5;
   font-family: Verdana;
}
#popup{   
   padding: 0 10px;
}
#popup h1 {
   font-family: "Times New Roman", Times, serif;
   font-size: 45px;
   color: #CCC;
   font-weight: normal;
}

table#zipcode {
    border-collapse:collapse;    /* border 사이의 간격 없앰 */   
    border-top: 3px solid  #fff;  
    border-bottom: 3px solid  #fff;
    width: 100%;  
    margin-top: 15px; 
}
table#zipcode th, table#zipcode td{   
   text-align: center;
   border-bottom: 1px dotted  #fff;  
   color:#FFF;   
}
table th, td{
  padding: 10px;
}
table#zipcode  a{
   display:block; 
    height:20px;
    text-decoration:none;
    color:#fff;
    padding: 10px;
}
table#zipcode a:hover{
    color: #F90;
    font-weight: bold;
}
</style>
<script>
	function result(zipNum, sido, gugun, dong) {
		opener.document.form.zipNum.value = zipNum;
		opener.document.form.addr1.value = sido + " " + gugun + " " + dong;
		self.close();
	}
</script>
</head>
<body>
	<div id="popup">
		<h1>우편번호 검색</h1>
		<form method = "post" name="form" action="NonageServlet?command=find_zip_num">
		동 이름 : <input type="text" name="dong" />
		<input type="submit" value="찾기" class="submit" />
		</form>
		<table id="zipcode">
			<tr>
				<th>우편번호</th>
				<th>주소</th>
			</tr>
			<c:forEach items="${ addressList }" var="addressVO">
			<tr>
				<td>${ addressVO.zip_num }</td>
				<td>
					<a href="#" onclick="return result(
					'${ addressVO.zip_num }', '${ addressVO.sido }', '${ addressVO.gugun }', '${ addressVO.dong }'
					)">
						${ addressVO.sido }${ addressVO.gugun }${ addressVO.dong }
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>