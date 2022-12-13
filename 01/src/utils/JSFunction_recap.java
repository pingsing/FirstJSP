package utils;

import javax.servlet.jsp.JspWriter;

public class JSFunction_recap {
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""	// 삽입할 자바스크립트 코드
						  + "<script>"
						  + "	alert('" + msg + "');"
						  + "	location.herf='" + url + "';"
						  + "</script>";
			out.println(script);	// 자바스크립트 코드를 out 내장 객체로 출력(삽입)
		}catch (Exception e) {
			
		}
	}
	
	// 메세지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
					      + "<script>"
					      + "	alert('" + msg + "');"
					      + "	histroy.back();"
					      + "</script>";
			out.println(script);
		}catch (Exception e) {
			
		}
	}
}
