package utils;

import javax.servlet.jsp.JspWriter;

public class JSFunction {
	// �޼���â ��� �� ������ �̵�
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""	// ������ �ڹ� ��ũ��Ʈ �ڵ�
							+ "<script>"
							+ "		alert('" + msg + "');		"
							+ "		location.href='" + url + "';"
							+ "</script>";
			out.println(script);
		}catch(Exception e) {
			
		}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""	// ������ �ڹ� ��ũ��Ʈ �ڵ�
							+ "<script>"
							+ "		alert('" + msg + "');		"
							+ "		history.back();"
							+ "</script>";
			out.println(script);
		}catch(Exception e) {
			
		}
	}
}
