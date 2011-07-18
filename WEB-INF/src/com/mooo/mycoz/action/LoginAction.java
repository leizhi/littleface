package com.mooo.mycoz.action;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.common.StringUtils;
import com.mooo.mycoz.db.Transaction;
import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.GroupMember;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;
import com.mooo.mycoz.util.SessionCounter;

public class LoginAction extends BaseSupport {

	private static Log log = LogFactory.getLog(LoginAction.class);
	
	public String promptLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptLogin");
			//HttpSession session = request.getSession(true);
			//session.setAttribute(USER_SESSION_KEY, -1);

			//Locale[] locales = java.text.NumberFormat.getAvailableLocales();
			Locale[] locales = new Locale[2];
			locales[0] = new Locale("en","US");
			locales[1] = new Locale("zh","CN");
			request.setAttribute("locales", locales);

			if (log.isDebugEnabled()) log.debug("IP:"+getClinetIp(request));
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptLogin";
		}
		return "success";
	}

	public String processLogin(HttpServletRequest request,HttpServletResponse response) {
		try {
			
			User user = new User();
			ParamUtil.bindData(request, user, "user");

			StringUtils.noNull(user.getName());
			StringUtils.noNull(user.getPassword());

			user.setPassword(StringUtils.hash(user.getPassword()));
			
			if (dbProcess.count(user) < 1) {
				if (log.isDebugEnabled())log.debug("<1");

				return "promptLogin";
			} else {
				if (log.isDebugEnabled())log.debug("else");
				dbProcess.retrieve(user);
				
				HttpSession session = request.getSession(true);
				session.setAttribute(USER_SESSION_KEY, user.getId());
				session.setAttribute(IP, getClinetIp(request));
				
				AccessLog al = new AccessLog();
				al.setId(IDGenerator.getNextID("AccessLog").intValue());
				al.setIp(getClinetIp(request));
				al.setStartdate(new Date(session.getCreationTime()));
				
				dbProcess.add(al);
				
				SessionCounter.login();
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptLogin";
		}
		return "success";

	}
	
	public String promptRegister(HttpServletRequest request,HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptRegister");
			User user = new User();
			user.setId(IDGenerator.getNextID("User").intValue());
			request.setAttribute("user", user);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptRegister";
		}
		return "success";
	}

	public String processRegister(HttpServletRequest request,HttpServletResponse response) {
		Transaction tx = new Transaction();
		try {
			tx.start();

			if (log.isDebugEnabled())log.debug("promptRegister");
			String value="";
			
			User user = new User();
			ParamUtil.bindData(request, user, "user");
			
			StringUtils.noNull(user.getName());
			StringUtils.noNull(user.getPassword());
			
			value = request.getParameter("dpassword");
			
			if (log.isDebugEnabled()) log.debug("dpassword=" + value);
			if (log.isDebugEnabled()) log.debug("password=" + user.getPassword());

			if(!value.equals(user.getPassword())){
				if (log.isDebugEnabled()) log.debug("not as same password");
				throw new Exception("not as same password");
			}
			
			user.setPassword(StringUtils.hash(user.getPassword()));
			dbProcess.add(tx.getConnection(),user);
			
			UserInfo userInfo = new UserInfo();
			userInfo.setId(IDGenerator.getNextID("UserInfo").intValue());
			userInfo.setUserId(user.getId());
			userInfo.setJoinTime(new Date());
			
			dbProcess.add(tx.getConnection(),userInfo);
			
			AddressBook addressBook = new AddressBook();
			addressBook.setId(IDGenerator.getNextID("AddressBook").intValue());
			addressBook.setUserId(user.getId());
			
			dbProcess.add(tx.getConnection(),addressBook);

			GroupMember groupMember = new GroupMember();
			groupMember.setId(IDGenerator.getNextID("GroupMember").intValue());
			groupMember.setGroupId(1);
			groupMember.setUserId(user.getId());
			dbProcess.add(tx.getConnection(),groupMember);

			tx.commit();
	} catch (Exception e) {
		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		tx.rollback();
		return "promptRegister";
	} finally {
		tx.end();
	}
		return "success";
	}
	
	public String processLogout(HttpServletRequest request,HttpServletResponse response) {
		try {
				HttpSession session = request.getSession(true);
				if (log.isDebugEnabled()) log.debug("online datatime = " + (session.getLastAccessedTime() - session.getCreationTime()) );
				session.invalidate();
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String getClinetIp(HttpServletRequest request) {   
	     String ip = request.getHeader("x-forwarded-for");   
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	         ip = request.getHeader("Proxy-Client-IP");   
	     }   
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	         ip = request.getHeader("WL-Proxy-Client-IP");   
	     }   
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	         ip = request.getRemoteAddr();   
	     }   
	     return ip;   
	} 
}
