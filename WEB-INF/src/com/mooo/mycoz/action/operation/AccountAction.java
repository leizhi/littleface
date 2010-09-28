package com.mooo.mycoz.action.operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.User;

public class AccountAction extends BaseSupport{
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		
		List accounts = new ArrayList();
		try {
			accounts = dbProcess.searchAndRetrieveList(new User());
			request.setAttribute("accounts", accounts);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	public String talk(HttpServletRequest request, HttpServletResponse response) {
		return "search";
	}
	
	public String message(HttpServletRequest request, HttpServletResponse response) {
		return "search";
	}
	
	public String blog(HttpServletRequest request, HttpServletResponse response) {
		return "search";
	}
}
