package com.mooo.mycoz.util;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;
import java.lang.reflect.Method;

public class ActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119522977670257605L;

	protected ConfigureUtil conf;
	
	public void destroy() {
	  
	}
	
	public void init() throws ServletException {
		
		super.init();
		
		// get web app real directory   
		String prefix = getServletContext().getRealPath("/");
		String confDir = getServletContext().getInitParameter("configDir");
		
		//load log4j configure file
		// read parameter from web.xml file to set log4j property   
		// set log4j
		if (confDir != null) {
			DOMConfigurator.configure(prefix + confDir+"/log4j.xml");
		}
		
		conf = ConfigureUtil.getInstance();
		conf.setCacheFile(prefix + confDir +"/myconfig.xml");
		conf.setMvcFile(prefix +  confDir +"/myconfig.xml");

		conf.conf();
		conf.confCache();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=UTF-8");
		
		out.println("locale:"+request.getLocale());
		//javax.servlet.jsp.jstl.fmt.LocaleSupport.getLocalizedMessage(pageContext, key);
		
		try {
			out.println("Start Servlet:");

			String execPath = request.getServletPath();

			if (execPath.indexOf(".") > 0) {
				//execPath = execPath.substring(0, execPath.indexOf("."));
				execPath = execPath.substring(1, execPath.indexOf("."));
			}

			if (execPath == null)
				execPath = request.getParameter("Controller");
			
			out.println("execPath:" + execPath);

			String execAction = "";
			String execResult = "";
			String execMethod = request.getParameter("state");
			
			ActionNode actionNode = null;
			Hashtable<String, String> results;

			// get mvc configure
			Map<String, ActionNode> actionMap = conf.getActionMap();
			// set controller,execState for execPath
			actionNode = (ActionNode) actionMap.get(execPath);
			out.println("actionNode:" + actionNode);

			execAction = actionNode.getCls();
			if (execMethod == null)
				execMethod = actionNode.getDefMethod();
			
			results = actionNode.getResults();
			// exec Controller request aciton
			Object obj = Class.forName(execAction).newInstance();
			
			Class<? extends Object> cls = obj.getClass();
			
			Class<?>[] paraTypes = new Class[] { HttpServletRequest.class,HttpServletResponse.class };
			
			Method method = cls.getMethod(execMethod, paraTypes);
			
			Object paraValues[] = new Object[] { request, response };
			
			// set Transition jsp for execMethod
			Object actionJsp = results.get(execMethod);
			
			String resultMethod = (String) method.invoke(obj, paraValues);
			
			if(resultMethod != null)
				if(!resultMethod.equals("success"))
					actionJsp = results.get(resultMethod);
			else
				new Exception("RETURN STATE NULL");
			
			if (actionJsp != null) {
				execResult = (String) actionJsp;
				if (!execResult.equals("")) {
					getServletConfig().getServletContext()
							.getRequestDispatcher(execResult).forward(request,response);
					 //response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					 //response.setHeader("Location",request.getContextPath()+execResult);
				}
			}
			out.println("End Servlet:");
		} catch (Exception ex) {
			out.println("Exception:" + ex.getMessage());
			ex.printStackTrace();
		} catch (Throwable e) {
			out.println("Throwable:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
