package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuAction extends BaseSupport {

	private static Log log = LogFactory.getLog(MenuAction.class);

	public String show(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			Integer userID= (Integer) request.getSession().getAttribute(USER_SESSION_KEY);

			System.out.println("userID:"+userID);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
}
