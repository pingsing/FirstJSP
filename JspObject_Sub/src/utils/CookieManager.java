package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	// ����� �̸�, ��, ���� �Ⱓ �������� ���ο� ��Ű�� �����մϴ�.
	public static void makeCookie(HttpServletResponse response, String cName,
								  String cValue, int cTime) {
		
		//1. ��Ű��ü�� �����Ѵ�.
		Cookie cookie = new Cookie(cName,cValue);
		//2. ��Ű�� ���� ��� ����
		cookie.setPath("/");
		//3. �����ð�
		cookie.setMaxAge(cTime);
		//4. ���� ����� ��Ű ������ ����
		response.addCookie(cookie);
	}
	
	// ����� �̸��� ��Ű�� ã�� �� ���� ��ȯ�մϴ�.
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue="";	// ��ȯ��
		
		//1. ��û ������� ��Ű���� ���
		Cookie[] cookies = request.getCookies();	
		//2. ��Ű������ �ִ��� Ȯ��
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		
		return cookieValue;
	}
	
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
	}
}
	
	

