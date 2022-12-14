<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type = "text/javascript">
$(function(){
	$("#buttonSumbit").on("click",function(){
		$.ajax({
			url:'./ajaxServer3.jsp',
			type :'get',
			dataType:'json',
			success:function(data){	
				$.each(data,function(i,d){
					
					$("#info").before("<p>"+d.nickName+"</p>");
					$("#info").before("<p>"+d.ph_number+"</p>");
					$("#info").before("<p>"+d.address`+"</p>");
					
					//$("#info").before("<p>"+data[i].nickName+"</p>");
					//$("#info").before("<p>"+data[i].ph_number+"</p>");
					//$("#info").before("<p>"+data[i].address`+"</p>");
				});
			}
		});
	});
});
</script>
</head>
<body>
	<input id="buttonSumbit" type="button" value="제출">
	<div id="info"></div>
</body>
</html>