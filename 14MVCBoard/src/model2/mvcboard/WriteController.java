package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WriteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ���� ���ε� ó�� ====================
		// ���ε� ���͸��� ������ ��� Ȯ��
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
		// ÷�� ���� �ִ� �뷮
		int maxPostSize = 1024 * 1000;
		
		// ���� ���ε�
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		if (mr == null) {
			// ���� ���ε� ����
			JSFunction.alertLocation(response, "÷�� ������ ���� �뷮�� �ʰ��մϴ�.", "../write.do");
			
			return;
		}
		
		// 2. ���� ���ε� �� ó�� ====================
		// ������ DTO�� ����
		MVCBoardDTO dto = new MVCBoardDTO();
		
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		// ���� ���ϸ�� ����� ���� �̸� ����
		String fileName = mr.getFilesystemName("ofile");
		if (fileName != null) {
			// ÷�� ������ ���� ��� ���ϸ� ����
			// ���ο� ���ϸ� ����
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// ���ϸ� ����
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName);	// ���� ���� �̸�
			dto.setSfile(newFileName);	// ������ ����� ���� �̸�
		}
		
		// DAO�� ���� DB�� �Խ� ���� ����
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		// ���� or ����?
		if (result == 1) {	// �۾��� ����
			response.sendRedirect("/14MVCBoard/list.do");
		} else {	// �۾��� ����
			response.sendRedirect("/14MVCBoard/write.do");
		}
	}
}
