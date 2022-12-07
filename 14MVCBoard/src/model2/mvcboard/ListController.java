package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAO ����
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// �信 ������ �Ű����� ����� �� ����
		Map<String, Object> map = new HashMap<>();
		
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		if (searchWord != null) {
			// ������Ʈ������ ���޹��� �Ű����� �� �˻�� �ִٸ� map�� ����
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map);
		
		/* ������ ó�� start */
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		// ���� ������ Ȯ��
		int pageNum = 1;	// �⺻��;
		String pageTemp = request.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);	// ��û���� �������� ����
		}
		
		// ��Ͽ� ����� �Խù� ���� ���
		int start = (pageNum - 1) * pageSize + 1;	// ù �Խù� ��ȣ
		int end = pageNum * pageSize;	// ������ �Խù� ��ȣ
		map.put("start", start);
		map.put("end", end);
		/* ������ ó�� end */
		
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		// �Խù� ��� �ޱ�
		dao.close();
		
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../list.do");
		// �ٷΰ��� ���� HTML ���ڿ�
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// ������ �����͸� request ������ ���� �� List.jsp�� ������
		request.setAttribute("boardLists", boardLists);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/14MVCBoard/List.jsp").forward(request, response);
	}
}
