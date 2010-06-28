package org.pig.controller;

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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
import org.pig.util.I18n;

import org.pig.dbobj.mycozShared.Language;
import org.pig.dbobj.MultiDBObject;
import org.pig.dbobj.util.IDGenerator;

import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

import org.pig.action.ActionServlet;
import org.pig.request.Input;

public class IndexController  {

private static Log log = LogFactory.getLog(IndexController.class);

public void promptIndexStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		if (log.isDebugEnabled()) log.debug("promptIndexStateRun init: ");
		String var = "";
		Language lg = null;

		lg = new Language();
		Input in = new Input();
		

		if((var=request.getParameter("Language")) != null){

			in.addHashMapValues(request,"Language",lg.getValues(),request.getParameter("Language"));

			var = lg.getName(var);
			if(var.equals("Chinese"))
				I18n.setMessages("zh","CN");
			else if (var.equals("Enlish"))
				I18n.setMessages("en","US");
		}

		HttpSession session = request.getSession(true);
		org.pig.response.SessionCounter.put(request.getSession().getId());
		session.setAttribute(request.getSession().getId(), "Guest");

		if (log.isDebugEnabled()) log.debug("promptIndexStateRun OK: ");
		} catch (SQLException sqlEx) {
       			if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"ErrorCode: " + sqlEx.getErrorCode());
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
    }
}
