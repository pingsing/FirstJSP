package model2.mvcboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MVCBoardDAO extends DBConnPool {
	
	// �˻� ���ǿ� �´� �Խù��� ������ ��ȯ�մϴ�.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		// ������ �غ�
		String query = "SELECT COUNT(*) FROM mvcboard";
		// �˻� ������ �ִٸ� WHERE ���� �߰�
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") +"%'";
		}
		
		try {
			stmt = con.createStatement();	// ������ ����
			rs = stmt.executeQuery(query);	// ������ ����
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("�Խù� ī��Ʈ �� ���� �߻�");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// �˻� ���ǿ� �´� �Խù� ����� ��ȯ�մϴ�(����¡ ��� ����)
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new ArrayList<>();
		// ������ �غ�
		String query = " "
				+ "SELECT * FROM ( "
				+ "	SELECT Tb.*, ROWNUM rNUM FROM ( "
				+ "  SELECT * FROM mvcboard ";
		// �˻� ������ �ִٸ� WHERE���� �߰�
		if (map.get("searchWord") != null)
		{
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") +"%'";
				
		}
		
		query += "		ORDER BY idx DESC "
				+ "  ) Tb "
				+ " ) "
				+ " WHERE rNum BETWEEN ? AND ?";	// �Խù� ������ ���Ķ���ͷ�...
		
		try {
			psmt = con.prepareStatement(query);	// ���� ������ ����
			psmt.setString(1, map.get("start").toString()); // ���Ķ���� ����
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();	// ������ ����
			
			// ��ȯ�� �Խù� ����� List �÷��ǿ� �߰�
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("�Խù� ��ȸ �� ���� �߻�");
			e.printStackTrace();
		}
		return board;	// ��� ��ȯ
	}
}
