package com.mooo.mycoz.action.operation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.http.HttpParamUtil;

public class ForumThreadAction extends BaseSupport{
	private static Log log = LogFactory.getLog(ForumThreadAction.class);

	
	public String promptCreateThread(HttpServletRequest request, HttpServletResponse response) {
		ForumThread forumThread = new ForumThread();
		request.setAttribute("forumThread", forumThread);
		return "success";
	}
	
	public String processCreateThread(HttpServletRequest request, HttpServletResponse response) {
		try {			
			ForumThread forumThread = new ForumThread();
			HttpParamUtil.bindData(request, forumThread,"forumThread");

			forumThread.setId(IDGenerator.getNextID("ForumThread").intValue());
			Date now = new Date();
			forumThread.setCreationDate(now);
			forumThread.setModifiedDate(now);
			forumThread.setRanking(0);
			
			request.setAttribute("threadId", forumThread.getId());

			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			forumThread.setUserId(new Integer(userId));
			forumThread.setReplyPrivateUserId(new Integer(userId));
			dbProcess.add(forumThread);

			if (log.isDebugEnabled()) log.debug("userId="+userId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "listMessage";
	}
	
	public String listMessage(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("listMessage");

			String threadId = request.getParameter("threadId");
			if(threadId==null)
				threadId = request.getAttribute("threadId").toString();
			
			request.setAttribute("threadId", threadId);
			
			ForumThread forumThread = new ForumThread();
			forumThread.setId(new Integer(threadId) );
			dbProcess.retrieve(forumThread);
			
			User user = new User();
			user.setId(forumThread.getUserId());
			dbProcess.retrieve(user);
			forumThread.setUser(user);
			
			request.setAttribute("forumThread", forumThread);

			Message message = new Message();
			if(threadId!=null && !threadId.equals("")){
				message.setThreadId(new Integer(threadId) );
			} else {
				message.setThreadId(0);
			}
			
			List<?> messages = dbProcess.searchAndRetrieveList(message);
			request.setAttribute("messages", messages);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String createMessage(HttpServletRequest request, HttpServletResponse response) {
		try {			
			String threadId = (String)request.getParameter("threadId");
			request.setAttribute("threadId", threadId);
			if (log.isDebugEnabled()) log.debug("threadId="+threadId);

			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			
			if (log.isDebugEnabled()) log.debug("userId="+userId);

			Message message = new Message();
			HttpParamUtil.bindData(request, message,"message");
			
			message.setId(IDGenerator.getNextID("Message").intValue());
			message.setUserId(new Integer(userId));
			message.setReplyPrivateUserId(new Integer(userId));
			Date now = new Date();
			message.setCreationDate(now);
			message.setModifiedDate(now);
			message.setThreadId(new Integer(threadId));
			dbProcess.add(message);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "listMessage";
	}
}
