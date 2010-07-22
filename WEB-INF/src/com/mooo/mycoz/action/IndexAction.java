package com.mooo.mycoz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.ParamUtil;

public class IndexAction {

	private static Log log = LogFactory.getLog(IndexAction.class);

	public String promptIndex(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptIndexStateRun init: ");
			String var = "";
			Language lg = null;

			lg = new Language();

			if ((var = request.getParameter("Language")) != null) {
				request.setAttribute("language", lg.getValues());

				var = lg.getName(var);
				if (var.equals("Chinese"))
					I18n.setMessages("zh", "CN");
				else if (var.equals("Enlish"))
					I18n.setMessages("en", "US");
			}

			request.setAttribute("name", "admin");
			
			HttpSession session = request.getSession(true);
			com.mooo.mycoz.util.SessionCounter.put(request.getSession().getId());
			session.setAttribute(request.getSession().getId(), "Guest");

			if (log.isDebugEnabled()) log.debug("promptIndexStateRun OK: ");
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

	public String accept(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Example ex = new Example();
			ParamUtil.add(request, "Example");
			ParamUtil.bindData(request, ex);

			//if (log.isDebugEnabled())log.debug("Age= " + ex.getAge());
			//if (log.isDebugEnabled())log.debug("Name= " + ex.getName());
			//if (log.isDebugEnabled())log.debug("School= " + ex.getSchool());
			//if (log.isDebugEnabled())log.debug("id= " + ex.getId());

			//if (log.isDebugEnabled())log.debug("SQL= " + ParamUtil.buildAddSQL(request, "Example"));
			//ex.add();
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "accept";

	}
	
	public String show(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setAttribute("name", "admin");
			//Language lg = new Language();

			//request.setAttribute("language", lg.getValues());
			//Locale locale = Locale.getDefault();
			Locale locale = new Locale("en_US");
			//ResourceBundle messageBundle = ResourceBundle.getBundle("MessageBundle",locale);
			//HttpSession session = request.getSession(true);
			//session.setAttribute("javax.servlet.jsp.jstl.fmt.locale.request",locale);
			request.setAttribute("javax.servlet.jsp.jstl.fmt.locale.request",locale);

			/*
			HttpSession session = request.getSession(true);

			Locale crtl = Locale.getDefault();
			Object cobj = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");

			if (cobj != null && cobj instanceof Locale) {
				crtl = (Locale) cobj;
			}

			Locale[] la = java.text.NumberFormat.getAvailableLocales();
			*/
			request.setAttribute("locale", locale);

		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		return "show";

	}
	
}
