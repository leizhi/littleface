package com.mooo.mycoz.action.operation.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.Transaction;

public class ForumThreadAction extends BaseSupport{
	private static Log log = LogFactory.getLog(ForumThreadAction.class);

	
	public String promptCreateThread(HttpServletRequest request, HttpServletResponse response) {
		String threadTypeId = request.getParameter("threadTypeId");

		ForumThread forumThread = new ForumThread();
		forumThread.setTypeId(new Integer(threadTypeId));
		
		request.setAttribute("forumThread", forumThread);
		return "success";
	}
	
	public String processCreateThread(HttpServletRequest request, HttpServletResponse response) {
		Transaction tx = new Transaction();

		try {
			tx.start();

			ForumThread forumThread = new ForumThread();
			ParamUtil.bindData(request, forumThread,"forumThread");
			
			StringUtils.noNull(forumThread.getSubject());
			StringUtils.noNull(forumThread.getBody());
			
			forumThread.setId(IDGenerator.getNextID(tx.getConnection(),"ForumThread"));
			Date now = new Date();
			forumThread.setCreationDate(now);
			forumThread.setModifiedDate(now);
			forumThread.setRanking(0);
			
			request.setAttribute("threadId", forumThread.getId());

			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			forumThread.setUserId(new Integer(userId));
			forumThread.setReplyPrivateUserId(new Integer(userId));

			dbProcess.add(tx.getConnection(),forumThread);

			if (log.isDebugEnabled()) log.debug("userId="+userId);
			tx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			return "promptCreateThread";
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return "promptCreateThread";
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String listThread(HttpServletRequest request, HttpServletResponse response) {
		try {
			String threadTypeId = request.getParameter("threadTypeId");
			if (log.isDebugEnabled()) log.debug("threadTypeId="+threadTypeId);
			request.setAttribute("threadTypeId", threadTypeId);
			
			ForumThread forumThread = new ForumThread();
			forumThread.setTypeId(new Integer(threadTypeId));
			
			Page page = new Page();

			page.buildComponent(request, dbProcess.count(forumThread));
			dbProcess.refresh(forumThread);
			dbProcess.setRecord(page.getOffset(),page.getPageSize());
			List<?> forumThreadList = dbProcess.searchAndRetrieveList(forumThread,DbProcess.OPEN_QUERY);
			
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String createMessage(HttpServletRequest request, HttpServletResponse response) {
		Transaction tx = new Transaction();
		try {
			tx.start();
			
			String threadId = (String)request.getParameter("threadId");
			request.setAttribute("threadId", threadId);
			if (log.isDebugEnabled()) log.debug("threadId="+threadId);

			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			
			if (log.isDebugEnabled()) log.debug("userId="+userId);

			Message message = new Message();
			ParamUtil.bindData(request, message,"message");
			
			StringUtils.noNull(message.getSubject());
			StringUtils.noNull(message.getBody());

			message.setId(IDGenerator.getNextID("Message").intValue());
			message.setUserId(new Integer(userId));
			message.setReplyPrivateUserId(new Integer(userId));
			Date now = new Date();
			message.setCreationDate(now);
			message.setModifiedDate(now);
			message.setThreadId(new Integer(threadId));

			dbProcess.add(tx.getConnection(),message);
			
			ForumThread forumThread = new ForumThread();
			forumThread.setId(new Integer(threadId));
			forumThread.setReplyPrivateUserId(new Integer(userId));
			forumThread.setModifiedDate(new Date());

			dbProcess.update(tx.getConnection(),forumThread);
			
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally{
			tx.end();
		}
		return "listMessage";
	}
}
