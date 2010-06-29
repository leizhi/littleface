package com.mooo.mycoz.action;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;

import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

import java.lang.reflect.Method;

public class ActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119522977670257605L;

	protected String configPig = "WEB-INF/config/mvc-config.xml";
	protected String configDb = "WEB-INF/config/pig-config.xml";

	/*
	 * public void destroy() {
	 * 
	 * }
	 */
	public void init() throws ServletException {
		super.init();
		// get web app real directory   
		String prefix = getServletContext().getRealPath("/");
		String confDir = getServletContext().getInitParameter("configDir");   

		PigConfigNode.setRootPath(prefix);
		PigConfigNode.setConfigPath(prefix + "/" + confDir +"/pig-config.xml");
		PigConfigNode.setMvcPath(prefix + "/" + confDir +"/mvc-config.xml");
		//load log4j configure file
		// read parameter from web.xml file to set log4j property   
		// set log4j   
		if (confDir != null) {   
			DOMConfigurator.configure(prefix + "/" + confDir+"/log4j.xml");
		}

		new DBLoad();
		new PigLoad();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		try {

			String execPath = request.getServletPath();
			if (execPath.indexOf(".") > 0) {
				execPath = execPath.substring(0, execPath.indexOf("."));
			}
			if (execPath == null)
				execPath = request.getParameter("Controller");

			String execController = "";
			String execJsp = null;
			String execState = request.getParameter("state");
			PigNode pNode = null;
			Hashtable<?, ?> forward = null;
			/* get mvc config */
			Hashtable<?, ?> mvcList = PigMap.getMvcList();
			/* set controller,execState for execPath */
			pNode = (PigNode) mvcList.get(execPath);
			execController = pNode.getType();
			if (execState == null)
				execState = pNode.getDefaultState();
			forward = pNode.getForward();
			/* set Transition jsp for execState */
			Object actionJsp = forward.get(execState);

			Object obj = Class.forName(execController).newInstance();
			Class<? extends Object> cls = obj.getClass();
			Class<?>[] paraTypes = new Class[] { HttpServletRequest.class,
					HttpServletResponse.class };
			Method method = cls.getMethod(execState + "StateRun", paraTypes);
			Object paraValues[] = new Object[] { request, response };
			method.invoke(obj, paraValues);

			if (actionJsp != null) {
				execJsp = (String) actionJsp;
				if (!execJsp.equals("")) {
					response.setContentType("text/html; charset=UTF-8");
					getServletConfig().getServletContext()
							.getRequestDispatcher(execJsp).forward(request,response);
					// response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					// response.setHeader("Location",request.getContextPath()+execJsp);
				}
			}

		} catch (Throwable e) {
			out.println("Throwable:" + e);
		}
	}

}
