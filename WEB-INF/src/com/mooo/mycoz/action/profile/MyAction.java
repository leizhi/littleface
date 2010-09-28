package com.mooo.mycoz.action.profile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyAction  extends BaseSupport{

private static Log log = LogFactory.getLog(MyAction.class);

	public String general(HttpServletRequest request,HttpServletResponse response) {
		try {

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
