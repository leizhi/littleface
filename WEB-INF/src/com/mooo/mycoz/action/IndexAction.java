package com.mooo.mycoz.action;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexAction  extends BaseSupport {

	private static Log log = LogFactory.getLog(IndexAction.class);

	public String promptIndex(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptIndexStateRun init: ");
			
			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");
			
			Integer userID = (Integer) session.getAttribute(USER_SESSION_KEY);
			boolean isAuthenticated = (null != userID);
			
			if (log.isDebugEnabled()) log.debug("isAuthenticated OK: "+isAuthenticated);

			if (!isAuthenticated) {
				return "promptLogin";
			}
			
			Locale locale = request.getLocale();
			request.setAttribute("locale", locale);

			if (log.isDebugEnabled()) log.debug("promptIndexStateRun OK: ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
}
