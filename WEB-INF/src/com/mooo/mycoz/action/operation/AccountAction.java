package com.mooo.mycoz.action.operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;
import com.mooo.mycoz.util.http.HttpParamUtil;

public class AccountAction extends BaseSupport{
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		
		List<?> accounts = new ArrayList<Object>();
		try {
			accounts = dbProcess.searchAndRetrieveList(new User());
			request.setAttribute("accounts", accounts);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	public String talk(HttpServletRequest request, HttpServletResponse response) {
		User account = new User();
		HttpParamUtil.bindData(request, account);

		//account.setId(new Integer(request.getParameter("id")));

		try {
			dbProcess.retrieve(account);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("account", account);

		return "talk";
	}
	
	public String message(HttpServletRequest request, HttpServletResponse response) {
		return "message";
	}
	
	public String blog(HttpServletRequest request, HttpServletResponse response) {
		try {
			/*LinearCode linearCode = new LinearCode();
			linearCode.setTypeId(2);
			
			forums = dbProcess.searchAndRetrieveList(linearCode);
			request.setAttribute("forums", forums);
			*/
			ForumThread forumThread = new ForumThread();
			
			List<?> forumThreads = dbProcess.searchAndRetrieveList(forumThread);
			request.setAttribute("forumThreads", forumThreads);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "blog";
	}
}
