package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuAction extends BaseSupport {

	private static Log log = LogFactory.getLog(MenuAction.class);

	public String show(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			Integer userID= (Integer) request.getSession().getAttribute(USER_SESSION_KEY);
			
			HttpSession session = request.getSession(true);
			Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
			if (log.isDebugEnabled()) log.debug("fmt locale: " + cobj);
			System.out.println("userID:"+userID);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
}
