package com.mooo.mycoz.action.profile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;


public class ProfileAction extends BaseSupport {

private static Log log = LogFactory.getLog(ProfileAction.class);

	public String menu(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("index ");
		
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("My","");
		gbar.put("Logout","");

		request.setAttribute("gbar", gbar);
		return "success";
	}
}
