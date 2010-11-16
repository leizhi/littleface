package com.mooo.mycoz.action.setup;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;



public class SetupAction extends BaseSupport {

private static Log log = LogFactory.getLog(SetupAction.class);

	public String menu(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.debug("SetupAction list");
		
		Map<String, String> gbar = new HashMap<String, String>();
		gbar.put("AccountElement","");
		gbar.put("AccountCategory","");
		gbar.put("AccountType","");
		gbar.put("AccountGroup","");
		gbar.put("Account","");
		request.setAttribute("gbar", gbar);

		return "success";
	}
}