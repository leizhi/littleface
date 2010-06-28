package org.pig.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLClassLoader;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.pig.util.PigConfigNode;
import org.pig.util.DBLoad;
import org.pig.util.DBNode;
import org.pig.util.DBMap;
import org.pig.util.PigLoad;
import org.pig.util.PigNode;
import org.pig.util.PigMap;

import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

public class ActionServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(ActionServlet.class);

    	protected String configPig = "WEB-INF/config/mvc-config.xml";
    	protected String configDb = "WEB-INF/config/pig-config.xml";
/*
public void destroy() {
        
	}
*/
public void init()throws ServletException {
	super.init();
	String var = getServletContext().getRealPath("/");
	PigConfigNode.setRootPath(var);
	PigConfigNode.setConfigPath(var + "WEB-INF/config/pig-config.xml");
	PigConfigNode.setMvcPath(var + "WEB-INF/config/mvc-config.xml");

	new DBLoad();
	new PigLoad();
	}

protected void service(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

	PrintWriter out = response.getWriter();
      	request.setCharacterEncoding("utf-8");
      	response.setContentType("text/html; charset=UTF-8");

	try {

		String execPath = request.getServletPath();
		if (execPath.indexOf(".") > 0) {
		    execPath = execPath.substring(0,execPath.indexOf("."));
		   }
		if (execPath==null) execPath = request.getParameter("Controller");

		String execController = "";
		String execJsp = null;
		String execState = request.getParameter("state");
		String var = "";
		PigNode pNode = null;
		Hashtable forward = null;
		/* get mvc config */
		Hashtable mvcList = PigMap.getMvcList();
		/* set controller,execState for execPath */
		pNode = (PigNode)mvcList.get(execPath);
		execController = pNode.getType();
		if(execState==null) execState = pNode.getDefaultState();
		forward = pNode.getForward();
		/* set Transition jsp for execState */
		Object actionJsp = forward.get(execState);

		Object obj = Class.forName(execController).newInstance();
		Class cls = obj.getClass();
		Class[] paraTypes = new Class[]{HttpServletRequest.class, HttpServletResponse.class};
		Method method = cls.getMethod(execState+"StateRun", paraTypes);
 		Object paraValues[] = new Object[]{request,response};
		method.invoke(obj, paraValues);

		if(actionJsp != null ) {
			execJsp = (String) actionJsp;
			if(!execJsp.equals("")){
				response.setContentType("text/html; charset=UTF-8");
				getServletConfig().getServletContext().getRequestDispatcher(execJsp).forward(request,response);
				//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    				//response.setHeader("Location",request.getContextPath()+execJsp);
			}
		}

	} catch (Throwable e) {
            out.println("Throwable:"+e);
         }
}

}
