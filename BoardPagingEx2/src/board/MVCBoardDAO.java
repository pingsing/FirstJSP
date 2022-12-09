package board;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	// �Խù� �ۼ�
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		
		// ������
		String query = "INSERT INTO mvcboard ( "
					+ " idx, name, title, content, ofile, sfile, pass) "
					+ " VALUES ( "
					+ " seq_board_num.NEXTVAL, ? ,? ,? ,? ,? ,?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("�Խù� �Է� �� ���� �߻�");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//�󼼺���
	public MVCBoardDTO selectView (String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		// ������
		String query = "SELECT * FROM mvcboard WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
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
			} 
		} catch (Exception e) {
			System.out.println("�Խù� �󼼺��� �� ���� �߻�");
			e.printStackTrace();
		}
		return dto;
	}
	
	// ��ȸ��
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET"
					+ " visitcount=visitcount+1 "
					+ " WHERE idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("�Խù� ��ȸ�� ���� �� ���� �߻�");
			e.printStackTrace();
		}
		
	}
}
