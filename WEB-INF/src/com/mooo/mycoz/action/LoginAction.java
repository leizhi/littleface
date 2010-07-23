package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;

public class LoginAction {

	private static Log log = LogFactory.getLog(LoginAction.class);

	public String promptLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())log.debug("promptLogin");

			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			request.setAttribute("id", IDGenerator.getNextID("Example"));
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
			//Example ex = new Example();
			ParamUtil.add(request, "Example");
			//ParamUtil.bindData(request, ex);

			//if (log.isDebugEnabled())log.debug("Age= " + ex.getAge());
			//if (log.isDebugEnabled())log.debug("Name= " + ex.getName());
			//if (log.isDebugEnabled())log.debug("School= " + ex.getSchool());
			//if (log.isDebugEnabled())log.debug("id= " + ex.getId());

			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			//ex.add();
			if (log.isDebugEnabled())log.debug("request.getMethod= "+request.getMethod());
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			return "promptLogin";
		}
		return "success";

	}
	
}
