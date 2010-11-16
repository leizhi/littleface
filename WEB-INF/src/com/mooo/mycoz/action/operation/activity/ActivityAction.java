package com.mooo.mycoz.action.operation.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.Forum;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.dbobj.mycozShared.City;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.mycozShared.Sex;
import com.mooo.mycoz.util.ParamUtil;

public class ActivityAction extends BaseSupport{

	public String search(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		try {
			request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");
			request.setAttribute("query_name", request.getParameter("query_name"));
			
			List<?> sexs = dbProcess.searchAndRetrieveList(new Sex());

			request.setAttribute("sexs", sexs);
			request.setAttribute("query_sexId", request.getParameter("query_sexId"));

			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable(User.class, "user");
			mdb.addTable(UserInfo.class, "userInfo");
			mdb.addTable(Sex.class, "sex");

			mdb.addTable(AddressBook.class, "addressBook");
			mdb.addTable(Language.class, "language");
			mdb.addTable(Country.class, "country");
			mdb.addTable(City.class, "city");

			mdb.setForeignKey("userInfo", "userId", "user", "id");
			mdb.setForeignKey("userInfo", "sexId", "sex", "id");
			mdb.setForeignKey("addressBook", "userId", "user", "id");
			mdb.setForeignKey("addressBook", "countryId", "country", "id");
			mdb.setForeignKey("addressBook", "languageId", "language", "id");
			mdb.setForeignKey("addressBook", "cityId", "city", "id");

			mdb.setLike("user","name",request.getParameter("query_name"));
			mdb.setLike("userInfo","sexId",request.getParameter("query_sexId"));

			mdb.setRetrieveField("user", "id");
			mdb.setRetrieveField("user", "name");
			mdb.setRetrieveField("country", "name");
			mdb.setRetrieveField("language", "name");
			mdb.setRetrieveField("city", "name");
			mdb.setRetrieveField("userInfo", "birthday");
			
			mdb.setRetrieveField("sex", "name");
			mdb.setRetrieveField("addressBook", "address");
			
			mdb.setOrderBy("user","id");
			
			Page page = new Page();
			page.buildComponent(request, mdb.count());
			mdb.setRecord(page.getOffset(),page.getPageSize());
			List<Object> accounts = mdb.searchAndRetrieveList();
			request.setAttribute("accounts", accounts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("search account expends:   " + hours + ":" + minutes + ":" + seconds);
		
		return "success";
	}
	
	public String talk(HttpServletRequest request, HttpServletResponse response) {
		User account = new User();
		ParamUtil.bindData(request, account);
		//account.setId(new Integer(request.getParameter("id")));
		try {
			dbProcess.retrieve(account);
		} catch (SQLException e) {
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
			ForumThread forumThread = new ForumThread();
			
			List<?> forumThreadList = dbProcess.searchAndRetrieveList(forumThread);
			
			List<ForumThread> forumThreads = new ArrayList<ForumThread>();
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
