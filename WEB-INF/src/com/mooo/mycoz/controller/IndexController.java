package com.mooo.mycoz.controller;

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



import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;


import com.mooo.mycoz.action.ActionServlet;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.Example;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.util.IDGenerator;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.tools.ParamUtil;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

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
		com.mooo.mycoz.response.SessionCounter.put(request.getSession().getId());
		session.setAttribute(request.getSession().getId(), "Guest");

		if (log.isDebugEnabled()) log.debug("promptIndexStateRun OK: ");
		} catch (SQLException sqlEx) {
       			if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"ErrorCode: " + sqlEx.getErrorCode());
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
    }
public void acceptStateRun(HttpServletRequest request, HttpServletResponse response){
	try {
		Example ex = new Example();
		ParamUtil.bindData(request,ex);
		ex.add();
	} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
    }

}
