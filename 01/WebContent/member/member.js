function go_save() {

}

function idcheck() {
	if(document.form.id.value == "") {
		alert("아이디를 입력하세요.")
		document.form.id.focus();
		return;
	} 
	
	var url = "NonageServlet?command=id_check_form&id=" + document.form.id.value;
	window.open(url, "_blank_1", "width=430, height=200, top=300, left=300");
}

function post_zip() {
	var url = "NonageServlet?command=find_zip_num";
	window.open(url, "_blank_1", "width=550, height=300, top=300, left=300");
}

// 약관동의 처리
function go_next() {
	if(document.form.okon1[0].checked == true) {
		document.form.action = "NonageServlet?command=join_form";
		document.form.submit();
	} else if (document.form.okon1[1].checked == true) {
		alert("약관에 동의하셔야합니다.")
	}
}