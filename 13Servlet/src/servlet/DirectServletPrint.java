package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DirectServletPrint.do")
public class DirectServletPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DirectServletPrint() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		writer.println("<html>");
		writer.println("<head><title>DirectServletPrint</title></heda>");
		writer.println("<body>");
		writer.println("<h2>�������� ���� ����մϴ�.</h2>");
		writer.println("<p>���̵� : " + id + "</p>");
		writer.println("<p>��й�ȣ : " + pwd + "</p>");
		writer.println("<p>jsp�� ������ ���� �ʽ��ϴ�.</p>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}

}
