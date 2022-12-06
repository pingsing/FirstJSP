package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;	// 데이터베이스와의 연결을 담당합니다.
	public Statement stmt;	// 인파라미터가 없는 정적 쿼리문을 실행할 때 사용됩니다.
	public PreparedStatement psmt;	// 인파라미터가 있는 동적 쿼리문을 실행할 때 사용됩니다.
	// 인파라미터는 쿼리문 작성 시 매개변수로 전달된 값을 설정할 때 사용합니다. ?(물음표)로 표현하는데,
	// 뒤에서 에제와 함께 한 번 더 설명하겠습니다.
	public ResultSet rs;	// SELECT 쿼리문의 결과를 저장할 때 사용됩니다.

	public JDBConnect() {
		// 기본 생성자
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB에 연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			// jdbc:oracle:thin : 오라클 프로토콜
			// localhost : 호스트명(혹은 IP 주소)
			// 1521 : 포트 번호
			// orcl : sid
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB에 연결
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		try {
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			// DB에 연결
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 연결 해제(자원 반납, 자원 절약을 위한 연결 해제)
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
