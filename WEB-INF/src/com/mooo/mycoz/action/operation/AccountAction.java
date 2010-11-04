package com.mooo.mycoz.action.operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.Forum;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserImage;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.dbobj.mycozShared.Sex;
import com.mooo.mycoz.util.ParamUtil;

public class AccountAction extends BaseSupport{
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");

		List<User> accounts = new ArrayList<User>();
		try {
			request.setAttribute("query_name", request.getParameter("query_name"));
			
			List sexs = dbProcess.searchAndRetrieveList(new Sex());
			request.setAttribute("sexs", sexs);
			request.setAttribute("query_sexId", request.getParameter("query_sexId"));

/*			
			dbProcess.refresh(user);
			dbProcess.setLike("name");
			
			page.buildComponent(request, dbProcess.count(user,DbProcess.OPEN_QUERY));
			dbProcess.setRecord(page.getOffset(),page.getPageSize());
			accounts = dbProcess.searchAndRetrieveList(user,DbProcess.OPEN_QUERY);
			
			request.setAttribute("accounts", accounts);
			request.setAttribute("user", user);
*/
			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable(User.class, "user");
			mdb.addTable(UserInfo.class, "userInfo");
			mdb.addTable(AddressBook.class, "addressBook");
			mdb.addTable(UserImage.class, "userImage");

			mdb.setForeignKey("userInfo", "userId", "user", "id");
			mdb.setForeignKey("addressBook", "userId", "user", "id");
			mdb.setForeignKey("userImage", "userId", "user", "id");

			//mdb.setField("ct.id", "1");
			mdb.setLike("user","name",request.getParameter("query_name"));
			mdb.setLike("userInfo","sexId",request.getParameter("query_sexId"));

			mdb.setGroupBy("user", "id");
			
			mdb.setRetrieveField("user", "*");
			//mdb.setRetrieveField("userInfo", "*");
			//mdb.setRetrieveField("addressBook", "*");
			//mdb.setRetrieveField("userImage", "*");
			Page page = new Page();
			page.setPageSize(8);
			mdb.setRecord(page.getOffset(),page.getPageSize());
			page.buildComponent(request, mdb.count());

			List<Map> examples = mdb.searchAndRetrieveList();

			User bean;

			for(Map map:examples){
				System.out.println("search cccccccccc");

				bean = (User)map.get("user");
				//UserInfo userInfo = (UserInfo)map.get("userInfo");
				accounts.add(bean);
				
				System.out.println("user name"+bean.getName());
			}
			
			request.setAttribute("accounts", accounts);
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
