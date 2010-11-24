package com.mooo.mycoz.action.profile;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.db.pool.DbConnectionManager;
import com.mooo.mycoz.db.sql.DbMultiBulildSQL;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
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
import com.mooo.mycoz.util.BeanUtil;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.Transaction;
import com.mooo.mycoz.util.UploadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyAction  extends BaseSupport{

private static Log log = LogFactory.getLog(MyAction.class);

	public String general(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setAttribute("uploadPath", request.getContextPath()+"/"+ "upload/");

			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			
			DbMultiBulildSQL mdb = new DbMultiBulildSQL();
			mdb.addTable(User.class, "user");
			mdb.addTable(UserInfo.class, "userInfo");
			mdb.addTable(Sex.class, "sex");
			mdb.addTable(WeightUnit.class, "weightUnit");
			mdb.addTable(HeightUnit.class, "heightUnit");
			mdb.addTable(Career.class, "career");
			mdb.addTable(Education.class, "education");
			mdb.addTable(Married.class, "married");
			
			mdb.addTable(AddressBook.class, "addressBook");
			mdb.addTable(Language.class, "language");
			mdb.addTable(Country.class, "country");
			mdb.addTable(City.class, "city");

			mdb.setForeignKey("userInfo", "userId", "user", "id");
			mdb.setForeignKey("userInfo", "sexId", "sex", "id");
			
			mdb.setForeignKey("userInfo", "weightUnitId", "weightUnit", "id");
			mdb.setForeignKey("userInfo", "heightUnitId", "heightUnit", "id");
			mdb.setForeignKey("userInfo", "careerId", "career", "id");
			mdb.setForeignKey("userInfo", "educationId", "education", "id");
			mdb.setForeignKey("userInfo", "marriedId", "married", "id");
			
			mdb.setForeignKey("addressBook", "userId", "user", "id");
			mdb.setForeignKey("addressBook", "countryId", "country", "id");
			mdb.setForeignKey("addressBook", "languageId", "language", "id");
			mdb.setForeignKey("addressBook", "cityId", "city", "id");

			mdb.setField("user.id",userId);

			//mdb.setGroupBy("user", "id");
			
			mdb.setRetrieveField("user", "*");
			mdb.setRetrieveField("country", "*");
			mdb.setRetrieveField("language", "*");
			mdb.setRetrieveField("city", "*");
			mdb.setRetrieveField("userInfo", "*");
			mdb.setRetrieveField("sex", "*");
			mdb.setRetrieveField("addressBook", "*");
			mdb.setRetrieveField("weightUnit", "*");
			mdb.setRetrieveField("heightUnit", "*");
			mdb.setRetrieveField("career", "*");
			mdb.setRetrieveField("education", "*");
			mdb.setRetrieveField("married", "*");
			
			List<Object> accounts = new ArrayList<Object>();
			String doSql = mdb.searchSQL();
			
			Connection myConn = null;
			boolean isClose = true;
			
			Statement stmt = null;
			ResultSet result = null;
			ResultSetMetaData rsmd = null;

			try {
				myConn = DbConnectionManager.getConnection();
				
				stmt = myConn.createStatement();
				result = stmt.executeQuery(doSql);
				
				rsmd = result.getMetaData();
	
				String table;
				
				while (result.next()) {
					Map<String, Object> allRow = new HashMap<String, Object>();
					
					User user = new User();
					Country country = new Country();
					Language language = new Language();
					City city = new City();
					UserInfo userInfo = new UserInfo();
					AddressBook addressBook = new AddressBook();
					
					Career career = new Career();
					Education education = new Education();
					Married married = new Married();
					Sex sex = new Sex();

					for (int i=1; i < rsmd.getColumnCount()+1; i++) {
						table = rsmd.getTableName(i);
						int type = rsmd.getColumnType(i);

						if (table.equals(User.class.getSimpleName())) {
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(user,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(user,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(UserInfo.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(userInfo,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(userInfo,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(AddressBook.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(addressBook,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(addressBook,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Country.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(country,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(country,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Language.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(language,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(language,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(City.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(city,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(city,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Career.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(career,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(career,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Education.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(education,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(education,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Married.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(married,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(married,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
						
						if(table.equals(Sex.class.getSimpleName())){
							if (type == Types.TIMESTAMP) {
								BeanUtil.bindProperty(sex,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getTimestamp(i));
							} else {
								BeanUtil.bindProperty(sex,StringUtils.prefixToUpper(rsmd.getColumnName(i),null),result.getString(i));
							}
						}
					}

					UserImage userImage = new UserImage();
					userImage.setUserId(result.getInt("user.Id"));
					List<?> imges = dbProcess.searchAndRetrieveList(userImage);
					user.setUserImages(imges);
					
					allRow.put("user", user);
					allRow.put("userInfo", userInfo);
					allRow.put("addressBook", addressBook);
					allRow.put("country", country);
					allRow.put("language", language);
					allRow.put("city", city);
				
					allRow.put("career", career);
					allRow.put("education", education);
					allRow.put("married", married);
					allRow.put("sex", sex);

					request.setAttribute("user", user);
					request.setAttribute("userInfo", userInfo);
					request.setAttribute("address", addressBook);
					
					accounts.add(allRow);
				}
				request.setAttribute("accounts", accounts);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (result != null)
						result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if(isClose)
						myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			
			////////////////////////////////
			List<?> sexs = dbProcess.searchAndRetrieveList(new Sex());
			request.setAttribute("sexs", sexs);

			List<?> heightUnits = dbProcess.searchAndRetrieveList(new HeightUnit());
			request.setAttribute("heightUnits", heightUnits);
			
			List<?> weightUnits = dbProcess.searchAndRetrieveList(new WeightUnit());
			request.setAttribute("weightUnits", weightUnits);
			
			List<?> careers = dbProcess.searchAndRetrieveList(new Career());
			request.setAttribute("careers", careers);
			

			List<?> educations = dbProcess.searchAndRetrieveList(new Education());
			request.setAttribute("educations", educations);
			

			List<?> marrieds = dbProcess.searchAndRetrieveList(new Married());
			request.setAttribute("marrieds", marrieds);
			
			List<String> secrets = new ArrayList<String>();
			secrets.add("Y");
			secrets.add("N");
			request.setAttribute("secrets", secrets);

			List<?> countrys = dbProcess.searchAndRetrieveList(new Country());
			request.setAttribute("countrys", countrys);

			List<?> languages = dbProcess.searchAndRetrieveList(new Language());
			request.setAttribute("languages", languages);

			List<?> citys = dbProcess.searchAndRetrieveList(new City());
			request.setAttribute("citys", citys);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String promptChangePassword(HttpServletRequest request,HttpServletResponse response) {
		try {

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String processChangePassword(HttpServletRequest request,HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("processChangePassword");
			
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String doublePassword = request.getParameter("doublePassword");

			StringUtils.noNull(oldPassword);
			StringUtils.noNull(newPassword);
			StringUtils.noNull(doublePassword);
			
			HttpSession hs = request.getSession(true);
			String userId = hs.getAttribute(USER_SESSION_KEY).toString();
			
			User user = new User();
			user.setId(new Integer(userId));
			user.setPassword(StringUtils.hash(oldPassword));

			if(dbProcess.count(user) > 0){
				if(newPassword.equals(doublePassword)){
					user.setPassword(StringUtils.hash(newPassword));

					dbProcess.update(user);
				}
			}
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "general";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) {
		return general(request,response);
	}
	
	public String update(HttpServletRequest request,HttpServletResponse response) {
		HttpSession hs = request.getSession(true);
		String userId = hs.getAttribute(USER_SESSION_KEY).toString();
		try {
			User user = new User();
			user.setId(new Integer(userId));

			dbProcess.retrieve(user);
			ParamUtil.bindData(request, user,"user");

			dbProcess.update(user);

			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(user.getId());

			dbProcess.retrieve(userInfo);
			ParamUtil.bindData(request, userInfo,"userInfo");
			System.out.println("getBirthday="+userInfo.getBirthday());
			System.out.println("getBirthday="+request.getParameter("userInfo.birthday"));

			dbProcess.update(userInfo);
			
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(user.getId());

			dbProcess.retrieve(addressBook);
			ParamUtil.bindData(request, addressBook,"address");

			dbProcess.update(addressBook);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "general";
	}
	
	public String promptUploadImages(HttpServletRequest request,HttpServletResponse response) {
		return "success";
	}
	
	public String processUploadImages(HttpServletRequest request,HttpServletResponse response) {

		String uploadDirectory = "upload/";
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + uploadDirectory;
		
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		UploadFile uf = new UploadFile();
		uf.setRequest(request);
		uf.setUploadPath(uploadPath);
		uf.process();

		HttpSession hs = request.getSession(true);
		String userId = hs.getAttribute(USER_SESSION_KEY).toString();
		// Finally, delete the forum itself and all permissions and
		// properties
		// associated with it.
		Transaction tx = new Transaction();

		try {
			tx.start();
			
			for(String filePath: uf.getFileList()){
				filePath = filePath.trim();
				
				UserImage userImage = new UserImage();
				userImage.setId(IDGenerator.getNextID(tx.getConnection(), "UserImage"));
				userImage.setUserId(new Integer(userId));
				userImage.setFilepath(filePath);
				dbProcess.add(tx.getConnection(), userImage);
			}

			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			tx.rollback();
		} finally {
			tx.end();
		}
		
		return "success";
	}
	
}
