package com.mooo.mycoz.action.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.dbaccess.DBStatement;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbMy;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.Forum;
import com.mooo.mycoz.dbobj.mycozBranch.ForumThread;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserImage;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.dbobj.mycozShared.Career;
import com.mooo.mycoz.dbobj.mycozShared.City;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Education;
import com.mooo.mycoz.dbobj.mycozShared.HeightUnit;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.mycozShared.Married;
import com.mooo.mycoz.dbobj.mycozShared.Sex;
import com.mooo.mycoz.dbobj.mycozShared.WeightUnit;
import com.mooo.mycoz.util.DbUtil;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.StringUtils;

public class AccountAction extends BaseSupport{
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();

		request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");
		/*
		Map params = new HashMap();
		ParamUtil.bindData(request, params);
	
		Map user = new HashMap();
		//user.put("id", "10");
		//user.put("name", "admin");
		
		params.put("user", user);
		
		request.setAttribute("params", params);

		DbMy dm = new DbMy();
		try {
			List accounts = dm.searchAndRetrieveList(null, null, "User", user);
			
			System.out.println("accounts->" + accounts.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//List<User> accounts = new ArrayList<User>();
		try {
			request.setAttribute("query_name", request.getParameter("query_name"));
			
			List sexs = dbProcess.searchAndRetrieveList(new Sex());
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

			//mdb.addTable(UserImage.class, "userImage");

			mdb.setForeignKey("userInfo", "userId", "user", "id");
			mdb.setForeignKey("userInfo", "sexId", "sex", "id");
			mdb.setForeignKey("addressBook", "userId", "user", "id");
			mdb.setForeignKey("addressBook", "countryId", "country", "id");
			mdb.setForeignKey("addressBook", "languageId", "language", "id");
			mdb.setForeignKey("addressBook", "cityId", "city", "id");

			//mdb.setForeignKey("userImage", "userId", "user", "id");

			//mdb.setField("ct.id", "1");
			mdb.setLike("user","name",request.getParameter("query_name"));
			mdb.setLike("userInfo","sexId",request.getParameter("query_sexId"));

			//mdb.setGroupBy("user", "id");
			
			mdb.setRetrieveField("user", "*");
			mdb.setRetrieveField("country", "*");
			mdb.setRetrieveField("language", "*");
			mdb.setRetrieveField("city", "*");
			mdb.setRetrieveField("userInfo", "*");
			mdb.setRetrieveField("sex", "*");
			mdb.setRetrieveField("addressBook", "*");

			//mdb.setRetrieveField("userImage", "*");
			Page page = new Page();
			//page.setPageSize(request.ge);
			
			page.buildComponent(request, mdb.count());
			
			mdb.setRecord(page.getOffset(),page.getPageSize());

			System.out.println("count SQL->"+mdb.buildCountSQL());
			System.out.println("searchSQL SQL->"+mdb.searchSQL());
			
			//List<Map> accounts = mdb.searchAndRetrieveList();
			List<Map> accounts = mdb.searchAndRetrieveList();

			request.setAttribute("accounts", accounts);

//			List<Object> retrieveList = null;
//			String doSql = mdb.searchSQL();
//			
//			Connection myConn = null;
//			boolean isClose = true;
//			
//			Statement stmt = null;
//			ResultSet result = null;
//			ResultSetMetaData rsmd = null;
//
//			try {
//				retrieveList = new ArrayList<Object>();
//				
//				myConn = DbConnectionManager.getConnection();
//				
//				stmt = myConn.createStatement();
//				result = stmt.executeQuery(doSql);
//				
//				rsmd = result.getMetaData();
//				Map re;
//	
//				String catalog,table,column;
//	
//				while (result.next()) {
//					re = new HashMap();
//					
//					Map user = new HashMap();
//					Map country = new HashMap();
//					Map language = new HashMap();
//					Map city = new HashMap();
//					Map userInfo = new HashMap();
//					Map sex = new HashMap();
//					Map addressBook = new HashMap();
//					
//					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
//						catalog = rsmd.getCatalogName(i);
//						table = rsmd.getTableName(i);
//						column = rsmd.getColumnName(i);
//						int type = rsmd.getColumnType(i);
//						//int type = DbUtil.type(null,catalog,table,StringUtils.upperToPrefix(column,null));
//
//						if (table.equals(User.class.getSimpleName())) {
//							if (type == Types.TIMESTAMP) {
//								user.put(column, result.getTimestamp(i));
//							} else {
//								user.put(column, result.getString(i));
//							}
//						}
//	
//						if(table.equals(Country.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								country.put(column, result.getTimestamp(i));
//							} else {
//								country.put(column, result.getString(i));
//							}
//						}
//						
//						if(table.equals(Language.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								language.put(column, result.getTimestamp(i));
//							} else {
//								language.put(column, result.getString(i));
//							}
//						}
//						
//						if(table.equals(City.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								city.put(column, result.getTimestamp(i));
//							} else {
//								city.put(column, result.getString(i));
//							}
//						}
//						
//						if(table.equals(UserInfo.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								userInfo.put(column, result.getTimestamp(i));
//							} else {
//								userInfo.put(column, result.getString(i));
//							}
//						}
//						
//						if(table.equals(Sex.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								sex.put(column, result.getTimestamp(i));
//							} else {
//								sex.put(column, result.getString(i));
//							}
//						}
//						
//						if(table.equals(AddressBook.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								addressBook.put(column, result.getTimestamp(i));
//							} else {
//								addressBook.put(column, result.getString(i));
//							}
//						}
//					}
//
//					UserImage userImage = new UserImage();
//					userImage.setUserId(result.getInt("user.Id"));
//					List imges = dbProcess.searchAndRetrieveList(userImage);
//					user.put("userImages",imges);
//					re.put("user", user);
//					
//					re.put("country", country);
//					re.put("language", language);
//					re.put("city", city);
//					re.put("userInfo", userInfo);
//					re.put("sex", sex);
//					re.put("addressBook", addressBook);
//					
//					retrieveList.add(re);
//				}
//				
//				request.setAttribute("accounts", retrieveList);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//
//				try {
//					if (result != null)
//						result.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//				try {
//					if (stmt != null)
//						stmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//				try {
//					if(isClose)
//						myConn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//			}
//			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		
//		long finishTime = System.currentTimeMillis();
//		long hours = (finishTime - startTime) / 1000 / 60 / 60;
//		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
//		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
//		
//		System.out.println(finishTime - startTime);
//		System.out.println("search account expends:   " + hours + ":" + minutes + ":" + seconds);
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
