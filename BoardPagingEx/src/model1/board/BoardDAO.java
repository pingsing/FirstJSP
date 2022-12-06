package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {

	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// �˻� ���ǿ� �´� �Խù��� ������ ��ȯ�մϴ�.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;		//���(�Խù� ��)�� ���� ����
		
		// �Խù� ���� ������ ������ �ۼ�
		String query = "SELECT COUNT(*) FORM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();	// ������ ����
			rs = stmt.executeQuery(query);	// ���� ����
			rs.next();		// Ŀ���� ù ��° ������ �̵�
			totalCount = rs.getInt(1);		// ù ��° �÷� ���� ������
		} catch (Exception e) {
			System.out.println("�Խù� ���� ���ϴ� �� ���� �߻�");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// �˻� ���ǿ� �´� �Խù� ����� ��ȯ�մϴ�.
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();		// ���(�Խù� ���)�� ���� ����
		
		String query = "SELECT * FROM board ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField" + " ")
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		query += " ORDER BY num DESC ";
		
		try {
			stmt = con.createStatement();	// ������ ����
			rs = stmt.executeQuery(query);	// ���� ����
			
			while (rs.next()) {		// ����� ��ȭ�ϸ�...
				// �� ��(�Խù� �ϳ�)�� ������ DTO�� ����
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));			// �Ϸù�ȣ
				dto.setTitle(rs.getString("title"));			// ����
				dto.setContent(rs.getString("content"));		// ����
				dto.setPostdate(rs.getDate("postdate"));			// �ۼ���
				dto.setId(rs.getString("id"));				// �ۼ��� ���̵�
				dto.setVisitcount(rs.getString("visitcount"));		// ��ȸ��

				bbs.add(dto);	// ��� ��Ͽ� ����
				
			}
		} catch (Exception e) {
			System.out.println("�Խù� ��ȸ �� ���� �߻�");
			e.printStackTrace();
		}
		
		return bbs;
	}
}
