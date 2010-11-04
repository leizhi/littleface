package com.mooo.mycoz.action.operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozBranch.Forum;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.ParamUtil;

public class AccountAction extends BaseSupport{
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");

		List<?> accounts = new ArrayList<Object>();
		try {
			User user = new User();
			ParamUtil.bindData(request, user,"user");

			Page page = new Page();
			page.setPageSize(8);
			
			dbProcess.refresh(user);
			dbProcess.setLike("name");
			
			page.buildComponent(request, dbProcess.count(user,DbProcess.OPEN_QUERY));
			dbProcess.setRecord(page.getOffset(),page.getPageSize());
			accounts = dbProcess.searchAndRetrieveList(user,DbProcess.OPEN_QUERY);
			
			request.setAttribute("accounts", accounts);
			request.setAttribute("user", user);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	public String talk(HttpServletRequest request, HttpServletResponse response) {
		User account = new User();
		ParamUtil.bindData(request, account);
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
			
			List<?> forumThreadList = dbProcess.searchAndRetrieveList(forumThread);
			
			List forumThreads = new ArrayList<ForumThread>();
			User user = null;
			Message message = new Message();

			for (Iterator<?> it = forumThreadList.iterator(); it.hasNext();) {
				forumThread = (ForumThread) it.next();
				
				user = new User();
				user.setId(forumThread.getUserId());
				
				dbProcess.retrieve(user);
				
				forumThread.setUser(user);
				
				user = new User();
				user.setId(forumThread.getReplyPrivateUserId());
				
				dbProcess.retrieve(user);
				forumThread.setReplyPrivateUser(user);
				
				message.setThreadId(forumThread.getId());
				
				forumThread.setReply(dbProcess.count(message));
				
				forumThreads.add(forumThread);
			}
			
			request.setAttribute("forumThreads", forumThreads);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "blog";
	}
	
	public String forum(HttpServletRequest request, HttpServletResponse response) {
		try {
			Forum forum = new Forum();
			List<?> forums = dbProcess.searchAndRetrieveList(forum);
			request.setAttribute("forums", forums);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
