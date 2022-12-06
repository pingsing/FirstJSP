package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;	// �����ͺ��̽����� ������ ����մϴ�.
	public Statement stmt;	// ���Ķ���Ͱ� ���� ���� �������� ������ �� ���˴ϴ�.
	public PreparedStatement psmt;	// ���Ķ���Ͱ� �ִ� ���� �������� ������ �� ���˴ϴ�.
	// ���Ķ���ʹ� ������ �ۼ� �� �Ű������� ���޵� ���� ������ �� ����մϴ�. ?(����ǥ)�� ǥ���ϴµ�,
	// �ڿ��� ������ �Բ� �� �� �� �����ϰڽ��ϴ�.
	public ResultSet rs;	// SELECT �������� ����� ������ �� ���˴ϴ�.

	public JDBConnect() {
		// �⺻ ������
		try {
			// JDBC ����̹� �ε�
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB�� ����
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			// jdbc:oracle:thin : ����Ŭ ��������
			// localhost : ȣ��Ʈ��(Ȥ�� IP �ּ�)
			// 1521 : ��Ʈ ��ȣ
			// orcl : sid
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB ���� ����(�⺻ ������)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC ����̹� �ε�
			Class.forName(driver);
			
			// DB�� ����
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB ���� ����(�μ� ������ 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		try {
			// JDBC ����̹� �ε�
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			// DB�� ����
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB ���� ����(�μ� ������ 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ���� ����(�ڿ� �ݳ�, �ڿ� ������ ���� ���� ����)
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC �ڿ� ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}