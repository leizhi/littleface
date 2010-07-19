package com.mooo.mycoz.controller.user;

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
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.City;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.error.DbError;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class UserController extends DbError {

private static Log log = LogFactory.getLog(UserController.class);

public void viewMenuStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  try {	
	Input in = new Input();
	HttpSession session = request.getSession(true);
	String chUser = (String) session.getAttribute(request.getSession().getId());
	if (chUser == null) {
    		request.setAttribute("Error" , "Session has ended. Please login." );
		response.sendRedirect(request.getContextPath()+"/User.do?state=promptLogin");
	}

	in.addValue(request,"UserName",request.getParameter("UserName"));
	in.addValue(request,"Password",request.getParameter("Password"));

	

     } catch (Exception e) {
      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     	}
}

public void promptLoginStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	Input in = new Input();
	in.addValue(request,"userName_V",request.getParameter("userName_V"));
	in.addValue(request,"password_V",request.getParameter("password_V"));
	
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     	}
}

public void processLoginStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(true);

	try {
	String chUser = request.getParameter("userName_V");
	String chPassword = request.getParameter("password_V");
	com.mooo.mycoz.response.SessionCounter.put(request.getSession().getId());
	session.setAttribute(request.getSession().getId(), chUser);

	User cu = new User();
	if(cu.loginAdminCheck(chUser,chPassword)) {
		response.sendRedirect(request.getContextPath()+"/Admin.do");
	} else if (cu.loginUserCheck(chUser,chPassword)) {
		response.sendRedirect(request.getContextPath()+"/User.do?state=viewMenu");
	} else {
		session.removeAttribute(request.getSession().getId());
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/Index.do");
	}

	//request.getRemoteAddr();
     } catch (SQLException sqlEx) {
       if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"VendorError: " + sqlEx.getErrorCode());
		//session.removeAttribute(request.getSession().getId());
		//session.invalidate();
		response.sendRedirect(request.getContextPath()+"/Index.do");
     } catch (Exception e) {
      if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		///session.removeAttribute(request.getSession().getId());
		//session.invalidate();
		response.sendRedirect(request.getContextPath()+"/Index.do");
     }
 }

public void processLogoutStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	String var = "";
	Language lg = null;
	Input in = new Input();

	lg = new Language();
	if((var=request.getParameter("Language")) != null){

		var = lg.getName(var);
		if(var.equals("Chinese"))
			I18n.setMessages("zh","CN");
		else if (var.equals("Enlish"))
			I18n.setMessages("en","US");
	}

	in.addHashMapValues(request,"Language",lg.getValues(),request.getParameter("Language"));
	

	HttpSession session = request.getSession(true);
	session.removeAttribute(request.getSession().getId());
	session.invalidate();
    
     } catch (Exception e) {
      if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     }
}

public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	Input in = new Input();
	in.addValue(request,"UserName",request.getParameter("UserName"));
	in.addValue(request,"Password",request.getParameter("Password"));
	in.addValue(request,"Password2",request.getParameter("Password2"));

	Country ct = new Country();
	in.addHashMapValues(request,"Country",ct.getValues(),request.getParameter("Country"));

	City ci = new City();
	in.addHashMapValues(request,"City",ci.getValues(),request.getParameter("City"));

	in.addValue(request,"Address",request.getParameter("Address"));
	in.addValue(request,"Email",request.getParameter("Email"));
	in.addValue(request,"Emailverify",request.getParameter("Emailverify"));
	in.addValue(request,"Tel",request.getParameter("Tel"));
	in.addValue(request,"Zip",request.getParameter("Zip"));

	Language lg = new Language();
	in.addHashMapValues(request,"Language",lg.getValues(),request.getParameter("Language"));
	

	} catch (SQLException sqlEx) {
       	if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"ErrorCode: " + sqlEx.getErrorCode());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     	}
}

public void processAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String var = request.getParameter("Password");
	if(!var.equals(request.getParameter("Password2"))) throw new Exception("check two input password");
	else if ((request.getParameter("Password")).equals("") || (request.getParameter("Password2")).equals("")) 
		throw new Exception("Input password NULL");
	User user = new User();
	id = user.getNextID();

	String sql = "INSERT INTO User(ID,Name,Password,CountryID,CityID,Address,Email,Tel,Zip,LanguageID,StateID) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("UserName")+"',";
	sql += "Password('"+request.getParameter("Password")+"'),";
	sql += "'"+request.getParameter("Country")+"',";
	sql += "'"+request.getParameter("City")+"',";
	sql += "'"+request.getParameter("Address")+"',";
	sql += "'"+request.getParameter("Email")+"',";
	sql += "'"+request.getParameter("Tel")+"',";
	sql += "'"+request.getParameter("Zip")+"',";
	sql += "'"+request.getParameter("Language")+"',";
	sql += "1)";

	user.execute(sql);

	response.sendRedirect(request.getContextPath()+"/User.do");
     } catch (SQLException sqlEx) {
		if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"VendorError: " + sqlEx.getErrorCode());
     } catch (Exception e) {
		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			setMessage(e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Error.do");
     }
	}
public void addUserSucceedStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {

     } catch (Exception e) {
		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		setMessage(e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Error.do");
     }
	}
}
