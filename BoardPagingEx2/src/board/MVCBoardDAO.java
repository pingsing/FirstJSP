package board;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	// 게시물 작성
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		
		// 쿼리문
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
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//상세보기
	public MVCBoardDTO selectView (String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		// 쿼리문
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
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 조회수
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET"
					+ " visitcount=visitcount+1 "
					+ " WHERE idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
		
	}
}
