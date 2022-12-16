package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.AddressVO;
import com.nonage.dto.ProductVO;

import util.DBManager;

public class AddressDAO {
	private AddressDAO() {
		
	}
	private static AddressDAO instance = new AddressDAO();
	
	public static AddressDAO getInstance() {
		return instance;
	}
	
	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		ArrayList<AddressVO> addressList = new ArrayList<AddressVO>();
		String sql = "SELECT * FROM address WHERE dong LIKE '%' || ? || '%'";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dong);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				AddressVO addressVO = new AddressVO();
				addressVO.setZip_num(rs.getString("zip_num"));
				addressVO.setSido(rs.getString("sido"));
				addressVO.setGugun(rs.getString("gugun"));
				addressVO.setDong(rs.getString("dong"));
				addressVO.setZip_code(rs.getString("zip_code"));
				addressVO.setBunji(rs.getString("bunji"));
				
				addressList.add(addressVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}
		
		return addressList;
	}
}
