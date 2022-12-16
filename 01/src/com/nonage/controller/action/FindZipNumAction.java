package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.AddressDAO;
import com.nonage.dto.AddressVO;

public class FindZipNumAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/findZipNum.jsp";
		String dong = request.getParameter("dong");
		
		// 1. DAO를 이용한 데이터 처리
		if (dong != null && dong.trim().equals("") == false) {
			AddressDAO addressDAO = AddressDAO.getInstance();
			ArrayList<AddressVO> addressList = addressDAO.selectAddressByDong(dong.trim());
			request.setAttribute("addressList", addressList);
		}
		// 2. view 페이지 지정(.jsp) 및 페이지 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
