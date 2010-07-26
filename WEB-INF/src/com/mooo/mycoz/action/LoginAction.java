package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.ParamUtil;

public class LoginAction extends BaseSupport {

	private static Log log = LogFactory.getLog(LoginAction.class);

	public String promptLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptLogin");
			
			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("password", request.getParameter("password"));
			
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
			ParamUtil.bindData(request, user, "User");

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
}
