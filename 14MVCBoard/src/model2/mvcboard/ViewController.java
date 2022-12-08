package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewController() {
        super();
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // �Խù� �ҷ�����
		 MVCBoardDAO dao = new MVCBoardDAO();
		 String idx = request.getParameter("idx");
		 dao.updateVisitCount(idx);	// ��ȸ�� 1 ����
		 MVCBoardDTO dto = dao.selectView(idx);
		 dao.close();
		 
		 // �ٹٲ� ó��
		 dto.setContent(dto.getContent().replaceAll("\r\n", "<br />"));
		 
		 // �Խù�(dto) ���� �� ��� ������
		 request.setAttribute("dto", dto);
		 request.getRequestDispatcher("/14MVCBoard/View.jsp").forward(request, response);
	}

}
