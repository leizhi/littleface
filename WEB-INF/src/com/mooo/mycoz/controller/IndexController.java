package com.mooo.mycoz.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.tools.ParamUtil;
import com.mooo.mycoz.util.I18n;

public class IndexController {

	private static Log log = LogFactory.getLog(IndexController.class);

	public String promptIndexStateRun(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled())
				log.debug("promptIndexStateRun init: ");
			String var = "";
			Language lg = null;

			lg = new Language();
			Input in = new Input();

			if ((var = request.getParameter("Language")) != null) {

				in.addHashMapValues(request, "Language", lg.getValues(),
						request.getParameter("Language"));

				var = lg.getName(var);
				if (var.equals("Chinese"))
					I18n.setMessages("zh", "CN");
				else if (var.equals("Enlish"))
					I18n.setMessages("en", "US");
			}

			HttpSession session = request.getSession(true);
			com.mooo.mycoz.response.SessionCounter.put(request.getSession()
					.getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			if (log.isDebugEnabled())
				log.debug("promptIndexStateRun OK: ");
		} catch (SQLException sqlEx) {
			if (log.isDebugEnabled())
				log.debug("SQLException: " + sqlEx.getMessage() + "SQLState: "
						+ sqlEx.getSQLState() + "ErrorCode: "
						+ sqlEx.getErrorCode());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String acceptStateRun(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//Example ex = new Example();
			ParamUtil.add(request, "example");
			
			//if (log.isDebugEnabled())log.debug("Age= " + ex.getAge());
			//if (log.isDebugEnabled())log.debug("Name= " + ex.getName());
			//if (log.isDebugEnabled())log.debug("School= " + ex.getSchool());
			//if (log.isDebugEnabled())log.debug("id= " + ex.getId());

			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			//ex.add();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "accept";

	}
}
