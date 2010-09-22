package com.mooo.mycoz.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.db.DbAction;
import com.mooo.mycoz.db.DbOperation;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozBranch.AccessLog;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.http.HttpParamUtil;

public class LoginAction extends BaseSupport {

	private static Log log = LogFactory.getLog(LoginAction.class);

	public String promptLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptLogin");
			DbAction dbAction = new DbOperation();

			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("password", request.getParameter("password"));
			
			Locale locale = Locale.getDefault();
			//if (log.isDebugEnabled()) log.debug("request locale: " + request.getParameter("locale"));
	
			Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
			//if (log.isDebugEnabled()) log.debug("fmt locale: " + cobj);

			if (cobj != null && cobj instanceof Locale) {
				locale = (Locale) cobj;
			}

			Locale[] locales = java.text.NumberFormat.getAvailableLocales();
			request.setAttribute("locales", locales);
			request.setAttribute("locale", locale);

			if (log.isDebugEnabled()) log.debug("IP:"+getClinetIp(request));
			//DBSession dbSession = DBSession.getInstance();

			AccessLog al = new AccessLog();
			al.setId(IDGenerator.getNextID("AccessLog").intValue());
			al.setIp(getClinetIp(request));
			al.setStartdate(new Timestamp(new Date().getTime()));
			dbAction.add(al);
			
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
			HttpParamUtil.bindData(request, user, "User");

			if (log.isDebugEnabled())log.debug("name= " + user.getName());
			if (log.isDebugEnabled())log.debug("password= " + user.getPassword());

			if(!user.loginCheck()){
				return "promptLogin";
			} else {
				HttpSession hs = request.getSession(true);
				hs.setAttribute(USER_SESSION_KEY, user.getId());
			}
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptLogin";
		}
		return "success";

	}
	
	public String promptRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptRegister");

			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("password", request.getParameter("password"));
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptRegister";
		}
		return "success";
	}

	public String processRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptRegister");
			DBSession session = DBSession.getInstance();
			User user = new User();
			HttpParamUtil.bindData(request, user, "User");
			session.save(user);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptRegister";
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
