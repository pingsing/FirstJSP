<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#check").click(function(){
			if($("#userid").val() == "") {
				$("#result").text("아이디를 입력하세요.");
				return;
			}
			
			query = {
				userid:$("#userid").val(),
				userpass:$("#userpass").val()
			}
			
			$.ajax({
				url: '../prac/ajaxServer_5.jsp',
				type: 'post',
				data: query
			}).done(function(data){
				obj = JSON.parse(data);
				if (obj.userid != undefined){
					$("#result").text(obj.userid + "/" + obj.userpass);
				} else {
					$("#result").text("존재하지 않는 아이디입니다.");
				}
			});
		});
	});
</script>
</head>
<body>
<label>아이디</label>
<input type="text" id="userid" /> <br/>
	
<label>비밀번호</label>
<input type="password" id="userpass" /> <br/>
	
<button type="submit" id="check">가입</button>
<p id="result"></p>
</body>
</html>