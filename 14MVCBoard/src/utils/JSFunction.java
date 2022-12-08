package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
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
	
	// �޼��� �˸�â�� ��� �� ����� URL�� �̵��մϴ�.
	public static void alertLocation(HttpServletResponse response, String msg,
					String url) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String script = ""
						+ "<script>"
						+ "	alert('" + msg + "');"
						+ "	location.href='" + url + "';"
						+ "</script>";
			writer.print(script);
		} catch (Exception e) {}
	}
	
	// �޼��� �˸�â�� ��� �� ���� �������� ���ư��ϴ�.
	public static void alertLocation(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String script = ""
						+ "<script>"
						+ "	alert('" + msg + "');"
						+ "	history.back();"
						+ "</script>";
			writer.print(script);
		} catch (Exception e) {}
	}
}
