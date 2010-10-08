package com.mooo.mycoz.action.profile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozBranch.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyAction  extends BaseSupport{

private static Log log = LogFactory.getLog(MyAction.class);

	public String general(HttpServletRequest request,HttpServletResponse response) {
		try {
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

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String password(HttpServletRequest request,HttpServletResponse response) {
		try {

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
}
