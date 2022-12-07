package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet(urlPatterns = {"/13Servlet/MemberAuth.mvc"}, 
initParams = {@WebInitParam(name="admin_id", value="nakja")})
public class MemberAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO dao;
	
    public MemberAuth() {
        super();
    }

	public void init() throws ServletException {
		// application ���� ��ü�� �����´�.
		ServletContext application = this.getServletContext();
		
		// web.xml�� ����� Context �ʱ�ȭ �Ű����� �� DB ���� �������� �д´�.
		String driver = application.getInitParameter("OracleDriver");
		String url = application.getInitParameter("OracleURL");
		String oid = application.getInitParameter("OracleId");
		String opass = application.getInitParameter("OraclePwd");
		
		dao = new MemberDAO(driver, url, oid, opass);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���� �ʱ�ȭ �Ű��������� ������ ID �ޱ�
		String admin_id = this.getInitParameter("admin_id");
		
		// ������ ��û�� ID/�н�����
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// ȸ�� ���̺��� ���� ��û�� ID/�н����忡 �ش��ϴ� ȸ�� ã��
		MemberDTO memberDTO = dao.getMemberDTO(id, pass);
		
		// ã�� ȸ���� �̸��� ���� ó��
		String memberName = memberDTO.getName();
		if (memberName != null) {	// ��ġ�ϴ� ȸ�� ã��
			request.setAttribute("authMessage", memberName + " ȸ���� �������^^*");
		} else {	// ��ġ�ϴ� ȸ�� ����
			if (admin_id.equals(id)) {	// ������
				request.setAttribute("authMessage", admin_id + "�� �ְ� �������Դϴ�.");
			} else {
				request.setAttribute("authMessage", "���ϴ� ȸ���� �ƴմϴ�.");
			}
		}
		request.getRequestDispatcher("/13Servlet/MemberAuth.jsp").forward(request, response);
	}
	
	public void destroy() {
		dao.close();
	}

}
