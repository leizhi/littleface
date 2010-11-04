package com.mooo.mycoz.action.profile;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.FileInfo;
import com.mooo.mycoz.dbobj.mycozBranch.FileTree;
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
import com.mooo.mycoz.util.FileUtil;
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
			
			User user = new User();
			user.setId(new Integer(userId));

			dbProcess.retrieve(user);
			request.setAttribute("user", user);
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(user.getId());

			dbProcess.retrieve(userInfo);
			request.setAttribute("userInfo", userInfo);

			UserImage userImage = new UserImage();
			userImage.setUserId(user.getId());
			List userImages = 	dbProcess.searchAndRetrieveList(userImage);
			request.setAttribute("userImages", userImages);

			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(user.getId());

			dbProcess.retrieve(addressBook);
			request.setAttribute("address", addressBook);
			
			List sexs = dbProcess.searchAndRetrieveList(new Sex());
			request.setAttribute("sexs", sexs);

			List heightUnits = dbProcess.searchAndRetrieveList(new HeightUnit());
			request.setAttribute("heightUnits", heightUnits);
			
			List weightUnits = dbProcess.searchAndRetrieveList(new WeightUnit());
			request.setAttribute("weightUnits", weightUnits);
			
			List careers = dbProcess.searchAndRetrieveList(new Career());
			request.setAttribute("careers", careers);
			

			List educations = dbProcess.searchAndRetrieveList(new Education());
			request.setAttribute("educations", educations);
			

			List marrieds = dbProcess.searchAndRetrieveList(new Married());
			request.setAttribute("marrieds", marrieds);
			
			List secrets = new ArrayList();
			secrets.add("Y");
			secrets.add("N");
			request.setAttribute("secrets", secrets);

			List countrys = dbProcess.searchAndRetrieveList(new Country());
			request.setAttribute("countrys", countrys);

			List languages = dbProcess.searchAndRetrieveList(new Language());
			request.setAttribute("languages", languages);

			List citys = dbProcess.searchAndRetrieveList(new City());
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
		String tmpDirectory = "tmp/";
		String uploadPath = request.getRealPath("/") + uploadDirectory;
		String tmpPath = request.getRealPath("/") + tmpDirectory;

		File tmpFile = new File(tmpPath);
		File uploadFile = new File(uploadPath);

		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}

		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		UploadFile uf = new UploadFile();
		uf.setRequest(request);
		uf.setUploadPath(tmpPath);
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
				
				FileUtil.copy(new File(tmpPath + filePath), new File(uploadPath	+ filePath), true);
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
