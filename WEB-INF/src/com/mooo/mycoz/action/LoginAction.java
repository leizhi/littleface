package com.mooo.mycoz.action;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.AddressBook;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.Transaction;
import com.mooo.mycoz.util.http.HttpParamUtil;

public class LoginAction extends BaseSupport {

	private static Log log = LogFactory.getLog(LoginAction.class);

	public String promptLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptLogin");
			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			//Locale[] locales = java.text.NumberFormat.getAvailableLocales();
			Locale[] locales = new Locale[2];
			locales[0] = new Locale("zh","CN");
			locales[1] = new Locale("en","US");

			Locale locale = request.getLocale(); // Locale.getDefault();
			
//			if(locale == null){
//				locale = Locale.getDefault();
//			}
			
			Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");

			if (cobj != null && cobj instanceof Locale) {
				locale = (Locale) cobj;
			}
			
			request.setAttribute("locales", locales);
			request.setAttribute("locale", locale);

			if (log.isDebugEnabled()) log.debug("IP:"+getClinetIp(request));

			AccessLog al = new AccessLog();
			al.setId(IDGenerator.getNextID("AccessLog").intValue());
			al.setIp(getClinetIp(request));
			al.setStartdate(new Date());
			dbProcess.add(al);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptLogin";
		}
		return "success";
	}

	public String processLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			User user = new User();
			HttpParamUtil.bindData(request, user, "user");

			StringUtils.noNull(user.getName());
			StringUtils.noNull(user.getPassword());

			user.setPassword(StringUtils.hash(user.getPassword()));

			if (log.isDebugEnabled())log.debug("name= " + user.getName());
			if (log.isDebugEnabled())log.debug("password= " + user.getPassword());
			if (log.isDebugEnabled())log.debug("count= " + dbProcess.count(user));
			if (log.isDebugEnabled())log.debug("okkkkkkkkkkkkkkkkkkkkkkkkkk");

			if (dbProcess.count(user) < 1) {
				if (log.isDebugEnabled())log.debug("<1");

				return "promptLogin";
			} else {
				if (log.isDebugEnabled())log.debug("else");
				dbProcess.retrieve(user);
				
				HttpSession hs = request.getSession(true);
				hs.setAttribute(USER_SESSION_KEY, user.getId());
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
			HttpParamUtil.bindData(request, user, "user");
			
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
			dbProcess.add(tx.getConnection(),userInfo);
			
			AddressBook addressBook = new AddressBook();
			addressBook.setId(IDGenerator.getNextID("AddressBook").intValue());
			addressBook.setUserId(user.getId());
			dbProcess.add(tx.getConnection(),addressBook);

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
	
	public String processLogout(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				HttpSession session = request.getSession(true);
				session.removeAttribute(USER_SESSION_KEY);
				session.removeAttribute(request.getSession().getId());
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
