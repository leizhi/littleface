package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainAction extends BaseSupport {

	private static Log log = LogFactory.getLog(MainAction.class);

	public String menu(HttpServletRequest request,HttpServletResponse response) {
		try {
			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			Integer userID= (Integer) request.getSession().getAttribute(USER_SESSION_KEY);
			
			HttpSession session = request.getSession(true);
			Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
			if (log.isDebugEnabled()) log.debug("fmt locale: " + cobj);
			System.out.println("userID:"+userID);
			
			request.setAttribute("Setup", true);
			request.setAttribute("Accounting", true);
			request.setAttribute("Security", true);
			
			request.setAttribute("File", true);
			
			request.setAttribute("Activity", true);
			request.setAttribute("Search", true);
			request.setAttribute("Forum", true);
			
			request.setAttribute("Transaction", true);
			request.setAttribute("Buy", true);
			request.setAttribute("Sale", true);
			request.setAttribute("Accounting", true);
			request.setAttribute("Reports", true);

			request.setAttribute("Profile", true);
			request.setAttribute("General", true);

//			List<String> setup = new ArrayList<String>();
//			setup.add("Account");
//			setup.add("Security");
//			request.setAttribute("Setup", setup);
//			
//			List<String> operation = new ArrayList<String>();
//			operation.add("Account");
//			request.setAttribute("Operation", operation);
//			
//			List<String> profile = new ArrayList<String>();
//			profile.add("Account");
//			request.setAttribute("Profile", profile);
//			
//			List<String> file = new ArrayList<String>();
//			file.add("Account");
//			request.setAttribute("File", file);
//			
//			List<String> activity = new ArrayList<String>();
//			activity.add("Account");
//			request.setAttribute("Activity", activity);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
}
