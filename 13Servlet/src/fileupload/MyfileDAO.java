package fileupload;

import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class MyfileDAO extends DBConnPool {
	// ���ο� �Խù��� �Է��մϴ�.
	public int insertFile(MyfileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile ( "
					+ " idx, name, title, cate, ofile, sfile) "
					+ " VALUES ( "
					+ " seq_board_num.nextval, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getCate());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("INSERT �� ���� �߻�");
			e.printStackTrace();
		}
		return applyResult;
		
	}
	
	public List<MyfileDTO> myFileList() {
		List<MyfileDTO> fileList = new ArrayList<MyfileDTO>();
		
		// ������ �ۼ�
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		
		try {
			psmt = con.prepareStatement(query);	// ���� �غ�
			rs = psmt.executeQuery();	// ���� ����
			
			while(rs.next()) {	// ��� ���� ���� ����ŭ �ݺ�
				// DTO�� ����
				MyfileDTO dto = new MyfileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPostdate(rs.getString(7));
				
				fileList.add(dto);	// ��Ͽ� �߰�
			}
		} catch (Exception e) {
			System.out.println("SELECT �� ���� �߻�");
			e.printStackTrace();
		}
		return fileList;
	}
}
