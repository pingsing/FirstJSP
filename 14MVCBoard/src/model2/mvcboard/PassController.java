package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PassController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		request.setAttribute("mode", mode);
		request.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ű����� ����
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		// ��й�ȣ Ȯ��
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if(confirmed) {
			if (mode.equals("edit")) {	// �������
				HttpSession session = request.getSession();
				session.setAttribute("pass", pass);
				response.sendRedirect("/14MVCBoard/edit.do?idx=" + idx);
			} else if (mode.equals("delete")) {		// �������
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx);
				int result = dao.deletePost(idx);	// �Խù� ����
				dao.close();
				if (result == 1) {	// �Խù� ���� ���� �� ÷�����ϵ� ����
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(request, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(response, "�����Ǿ����ϴ�.", "/14MVCBoard/list.do");
			} 
		} else {	// ��й�ȣ ����ġ
			JSFunction.alertBack(response, "��й�ȣ ������ �����߽��ϴ�.");
		}
	}

}
