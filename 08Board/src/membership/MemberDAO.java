package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	// ����� �����ͺ��̽����� ������ �Ϸ�� MemberDAO ��ü�� �����մϴ�.
	public MemberDAO(String driver, String url, String id, String pwd) {
		super(driver, url, id, pwd);
	}
	
	// ����� ���̵�/�н������ ��ġ�ϴ� ȸ�� ������ ��ȯ�մϴ�.
	public MemberDTO getMemberDTO(String uid, String upass) {
		
		//1. ��ȸ�� �����͸� ���� ��ü ����
		MemberDTO dto = new MemberDTO();
		
		//2. ������ ����
		String query = "select * from member where id = ? and pass = ?";
		
		//3. ������ ����
		try {
			// ���� ����
			psmt = con.prepareStatement(query);	// ���� ������ �غ�
			psmt.setString(1, uid);		// �������� ù ��° ���Ķ���Ϳ� �� ����
			psmt.setString(2, upass);	// �������� �� ��° ���Ķ���Ϳ� �� ����
			rs = psmt.executeQuery();	// ������ ����
			
			// ��� ó��
			if (rs.next()) {
				// ���� ����� ���� ȸ�� ������ DTO ��ü�� ����
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;	// DTO ��ü ��ȯ
	}
}
