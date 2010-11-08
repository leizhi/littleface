package com.mooo.mycoz.action.operation;

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

public class AccountAction extends BaseSupport{

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
	
	/*
	public String search(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
//		Map params = new HashMap();
//		ParamUtil.bindData(request, params);
//	
//		Map user = new HashMap();
//		//user.put("id", "10");
//		//user.put("name", "admin");
//		
//		params.put("user", user);
//		
//		request.setAttribute("params", params);
//
//		DbMy dm = new DbMy();
//		try {
//			List accounts = dm.searchAndRetrieveList(null, null, "User", user);
//			
//			System.out.println("accounts->" + accounts.size());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Connection myConn = null;
		Statement stmt = null;
		try {
			request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");
			request.setAttribute("query_name", request.getParameter("query_name"));
			
			List<?> sexs = dbProcess.searchAndRetrieveList(new Sex());
			request.setAttribute("sexs", sexs);
			request.setAttribute("query_sexId", request.getParameter("query_sexId"));

//			DbMultiBulildSQL mdb = new DbMultiBulildSQL();
//			mdb.addTable(User.class, "user");
//			mdb.addTable(UserInfo.class, "userInfo");
//			mdb.addTable(Sex.class, "sex");
//
//			mdb.addTable(AddressBook.class, "addressBook");
//			mdb.addTable(Language.class, "language");
//			mdb.addTable(Country.class, "country");
//			mdb.addTable(City.class, "city");
//
//			//mdb.addTable(UserImage.class, "userImage");
//
//			mdb.setForeignKey("userInfo", "userId", "user", "id");
//			mdb.setForeignKey("userInfo", "sexId", "sex", "id");
//			mdb.setForeignKey("addressBook", "userId", "user", "id");
//			mdb.setForeignKey("addressBook", "countryId", "country", "id");
//			mdb.setForeignKey("addressBook", "languageId", "language", "id");
//			mdb.setForeignKey("addressBook", "cityId", "city", "id");
//
//			//mdb.setForeignKey("userImage", "userId", "user", "id");
//
//			//mdb.setField("ct.id", "1");
//			mdb.setLike("user","name",request.getParameter("query_name"));
//			mdb.setLike("userInfo","sexId",request.getParameter("query_sexId"));
//
//			//mdb.setGroupBy("user", "id");
//			
//			mdb.setRetrieveField("user", "*");
//			mdb.setRetrieveField("country", "*");
//			mdb.setRetrieveField("language", "*");
//			mdb.setRetrieveField("city", "*");
//			mdb.setRetrieveField("userInfo", "*");
//			mdb.setRetrieveField("sex", "*");
//			mdb.setRetrieveField("addressBook", "*");
			
			Page page = new Page();
//			page.buildComponent(request, mdb.count());
			
//			mdb.setRecord(page.getOffset(),page.getPageSize());

//			System.out.println("count SQL->"+mdb.buildCountSQL());
//			System.out.println("searchSQL SQL->"+mdb.searchSQL());
					
			List<Object> accounts = new ArrayList<Object>();
//			String doSql = mdb.searchSQL();
			String countSQL = "SELECT COUNT(*) FROM ";
			String querySQL = "SELECT * FROM ";
			String commSQL="";
			
			commSQL += "mycozShared.Sex sex," +
					"mycozShared.Married married," +
					"mycozShared.WeightUnit weightUnit," +
					"mycozShared.HeightUnit heightUnit," +
					"mycozShared.Career career," +
					"mycozBranch.AddressBook addressBook," +
					"mycozShared.Language language," +
					"mycozShared.Education education," +
					"mycozBranch.UserInfo userInfo," +
					"mycozBranch.User user," +
					"mycozShared.City city," +
					"mycozShared.Country country";
			commSQL += " WHERE userInfo.userId=user.id" +
					" AND userInfo.sexId=sex.id" +
					" AND userInfo.weightUnitId=weightUnit.id" +
					" AND userInfo.heightUnitId=heightUnit.id" +
					" AND userInfo.careerId=career.id" +
					" AND userInfo.educationId=education.id" +
					" AND userInfo.marriedId=married.id" +
					" AND addressBook.userId=user.id" +
					" AND addressBook.countryId=country.id" +
					" AND addressBook.languageId=language.id" +
					" AND addressBook.cityId=city.id";
			
			String value = request.getParameter("query_name");
			if (!StringUtils.isNull(value)) {
				commSQL += " AND user.name LIKE '%" + value + "%'";
			}

			value = request.getParameter("query_sexId");
			if (!StringUtils.isNull(value)) {
				commSQL += " AND sex.id =" + value;
			}
			
			myConn = DbConnectionManager.getConnection();
			stmt = myConn.createStatement();
			
//			ResultSet result = stmt.executeQuery(doSql);
			ResultSet result = stmt.executeQuery(countSQL+commSQL);
			result.first();
			int total = result.getInt(1);
			
			page.buildComponent(request, total);
			result = stmt.executeQuery(querySQL+commSQL+" LIMIT "+page.getOffset()+","+page.getPageSize());
			ResultSetMetaData rsmd = result.getMetaData();

			String table;
				
				while (result.next()) {
					Map<String, Object> allRow = new HashMap<String, Object>();
					
					allRow = new HashMap();
					
					Map user = new HashMap();
					Map country = new HashMap();
					Map language = new HashMap();
					Map city = new HashMap();
					Map userInfo = new HashMap();
					Map sex = new HashMap();
					Map addressBook = new HashMap();
					
					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
						//String catalog = rsmd.getCatalogName(i);
						table = rsmd.getTableName(i);
						String column = rsmd.getColumnName(i);
						int type = rsmd.getColumnType(i);

						if (table.equals(User.class.getSimpleName())) {
							if (type == Types.TIMESTAMP) {
								user.put(column, result.getTimestamp(i));
							} else {
								user.put(column, result.getString(i));
							}
						}
	
						if(table.equals(Country.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								country.put(column, result.getTimestamp(i));
							} else {
								country.put(column, result.getString(i));
							}
						}
						
						if(table.equals(Language.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								language.put(column, result.getTimestamp(i));
							} else {
								language.put(column, result.getString(i));
							}
						}
						
						if(table.equals(City.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								city.put(column, result.getTimestamp(i));
							} else {
								city.put(column, result.getString(i));
							}
						}
						
						if(table.equals(UserInfo.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								userInfo.put(column, result.getTimestamp(i));
							} else {
								userInfo.put(column, result.getString(i));
							}
						}
						
						if(table.equals(Sex.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								sex.put(column, result.getTimestamp(i));
							} else {
								sex.put(column, result.getString(i));
							}
						}
						
						if(table.equals(AddressBook.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								addressBook.put(column, result.getTimestamp(i));
							} else {
								addressBook.put(column, result.getString(i));
							}
						}
					}

					UserImage userImage = new UserImage();
					userImage.setUserId(result.getInt("user.Id"));
					List imges = dbProcess.searchAndRetrieveList(userImage);
					
					
					user.put("userImages",imges);
					allRow.put("user", user);
					
					allRow.put("country", country);
					allRow.put("language", language);
					allRow.put("city", city);
					allRow.put("userInfo", userInfo);
					allRow.put("sex", sex);
					allRow.put("addressBook", addressBook);
					
					accounts.add(allRow);
					
//					User user = new User();
//					Country country = new Country();
//					Language language = new Language();
//					City city = new City();
//					UserInfo userInfo = new UserInfo();
//					Sex sex = new Sex();
//					AddressBook addressBook = new AddressBook();
//					
//					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
//						table = rsmd.getTableName(i);
//						int type = rsmd.getColumnType(i);
//
//						if (table.equals(User.class.getSimpleName())) {
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(user,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(user,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//
//						if(table.equals(Country.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(country,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(country,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//						
//						if(table.equals(Language.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(language,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(language,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//						
//						if(table.equals(City.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(city,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(city,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//						
//						if(table.equals(UserInfo.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(userInfo,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(userInfo,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//						
//						if(table.equals(Sex.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(sex,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(sex,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//						
//						if(table.equals(AddressBook.class.getSimpleName())){
//							if (type == Types.TIMESTAMP) {
//								BeanUtil.bindProperty(addressBook,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
//							} else {
//								BeanUtil.bindProperty(addressBook,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
//							}
//						}
//					}
//
//					UserImage userImage = new UserImage();
//					userImage.setUserId(result.getInt("user.Id"));
//					List<?> imges = dbProcess.searchAndRetrieveList(userImage);
//					user.setUserImages(imges);
//					allRow.put("user", user);
//					
//					allRow.put("country", country);
//					allRow.put("language", language);
//					allRow.put("city", city);
//					allRow.put("userInfo", userInfo);
//					allRow.put("sex", sex);
//					allRow.put("addressBook", addressBook);
//					
//					accounts.add(allRow);
				}
				
				request.setAttribute("accounts", accounts);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					myConn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		long finishTime = System.currentTimeMillis();
		long hours = (finishTime - startTime) / 1000 / 60 / 60;
		long minutes = (finishTime - startTime) / 1000 / 60 - hours * 60;
		long seconds = (finishTime - startTime) / 1000 - hours * 60 * 60 - minutes * 60;
		
		System.out.println(finishTime - startTime);
		System.out.println("search account expends:   " + hours + ":" + minutes + ":" + seconds);
		return "success";
	}
	*/
	
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
