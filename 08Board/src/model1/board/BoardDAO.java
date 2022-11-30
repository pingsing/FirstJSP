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

	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;	// 결과(게시물 수)를 담을 변수
		
		// 게시물 수를 얻어오는 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();	// 쿼리문 생성
			rs = stmt.executeQuery(query);	// 쿼리 실행
			rs.next();	// 커서를 첫 번째 행으로 이동
			totalCount = rs.getInt(1);	// 첫 번째 컬럼 값을 가져옴
		} catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();	// 결과(게시물 목록)를 담을 변수
	
		String query = "SELECT * FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY num DESC ";
		
		try {
			stmt = con.createStatement();	// 쿼리문 생성
			rs = stmt.executeQuery(query);	// 쿼리 실행
			
			while (rs.next()) {	// 결과를 순화하며..
				// 한 행(게시물 하나)의 내용을 DTO에 저장
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
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	// 게시글 데이터를 받아 DB에 추가합니다.
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		// INSERT 쿼리문 작성
		String query = "INSERT INTO board (num, title, content, id, visitcount)"
				+ " VALUES (seq_board_num.NEXTVAL, ?, ?, ?, 0)";
		
		try {
			psmt = con.prepareStatement(query);	// 동적 쿼리
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 지정한 게시물을 찾아 내용을 반환합니다.
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		// 쿼리문 준비
		String query = "SELECT B.*, M.name "
					 + " FROM member M INNER JOIN board B "
					 + " ON M.id=B.id "
					 + " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);		// 인파라미터를 일련번호로 설정
			rs = psmt.executeQuery();	// 쿼리 실행
			
			// 결과 처리
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void updateVisitCount(String num) {
		
		String query = "UPDATE board SET "
					 + " visitcount=visitcount+1 "
					 + " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
}
