package com.mooo.mycoz.action.setup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccountElementAction {
	private static Log log = LogFactory.getLog(AccountElementAction.class);

	public String list(HttpServletRequest request, HttpServletResponse response) {
		if(log.isDebugEnabled()) log.debug("AccountElementAction list");
		return "success";
	}
}