package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect{
	
	
	public BoardDAO(ServletContext application) {
		super(application);
	}

	// �˻� ���ǿ� �´� �Խù��� ������ ��ȯ�մϴ�.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;	// ���(�Խù� ��)�� ���� ����
		
		// �Խù� ���� ������ ������ �ۼ�
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();	// ������ ����
			rs = stmt.executeQuery(query);	// ���� ����
			rs.next();	// Ŀ���� ù ��° ������ �̵�
			totalCount = rs.getInt(1);	// ù ��° �÷� ���� ������
		} catch(Exception e) {
			System.out.println("�Խù� ���� ���ϴ� �� ���� �߻�");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// �˻� ���ǿ� �´� �Խù� ����� ��ȯ�մϴ�.
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();	// ���(�Խù� ���)�� ���� ����
	
		String query = "SELECT * FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY num DESC ";
		
		try {
			stmt = con.createStatement();	// ������ ����
			rs = stmt.executeQuery(query);	// ���� ����
			
			while (rs.next()) {	// ����� ��ȭ�ϸ�..
				// �� ��(�Խù� �ϳ�)�� ������ DTO�� ����
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			} 
		} catch (Exception e) {
			System.out.println("�Խù� ��ȸ �� ���� �߻�");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	// �Խñ� �����͸� �޾� DB�� �߰��մϴ�.
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		// INSERT ������ �ۼ�
		String query = "INSERT INTO board (num, title, content, id, visitcount)"
				+ " VALUES (seq_board_num.NEXTVAL, ?, ?, ?, 0)";
		
		try {
			psmt = con.prepareStatement(query);	// ���� ����
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("�Խù� �Է� �� ���� �߻�");
			e.printStackTrace();
		}
		
		return result;
	}
}
