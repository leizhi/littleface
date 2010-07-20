package com.mooo.mycoz.ajax;

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

import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.BlogType;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.util.ActionServlet;
import com.mooo.mycoz.util.I18n;

import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class AutoComplete  {

private static Log log = LogFactory.getLog(AutoComplete.class);

public void listUserStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		String sql = "";
		String prefix = request.getParameter("prefix");
 
		User user = new User();
		ResultSet rs = null;
		sql += "SELECT UserName FROM User WHERE ID > 0 AND UserName LIKE '%" + prefix + "%' LIMIT 10";
		user.getResultSet(sql);
		rs = user.getResultSet(sql);

		if(rs.next()){
           		PrintWriter output = response.getWriter();
            		response.setContentType("text/xml");
            		response.setHeader("Cache-Control", "no-cache");
            		output.println("<response>");
                	output.println("<value>" + rs.getString("UserName")+ "</value>");
			while(rs.next()) {
                	output.println("<value>" + rs.getString("UserName")+ "</value>");
               		}
            		output.println("</response>");
            		output.close();
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
			if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		} catch (SQLException sqlEx) {
       			if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"ErrorCode: " + sqlEx.getErrorCode());
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
    }

public void listBlogTypeStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		String sql = "";
		String prefix = request.getParameter("prefix");
 
		BlogType bt = new BlogType();
		ResultSet rs = null;
		sql += "SELECT Name FROM BlogType WHERE ID > 0 AND Name LIKE '%" + prefix + "%' LIMIT 10";
		bt.getResultSet(sql);
		rs = bt.getResultSet(sql);

		if(rs.next()){
           		PrintWriter output = response.getWriter();
            		response.setContentType("text/xml");
            		response.setHeader("Cache-Control", "no-cache");
            		output.println("<response>");
                	output.println("<value>" + rs.getString("Name")+ "</value>");
			while(rs.next()) {
                	output.println("<value>" + rs.getString("Name")+ "</value>");
               		}
            		output.println("</response>");
            		output.close();
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
			if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		} catch (SQLException sqlEx) {
       			if (log.isDebugEnabled()) log.debug("SQLException: " + sqlEx.getMessage()+"SQLState: " + sqlEx.getSQLState()+"ErrorCode: " + sqlEx.getErrorCode());
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
    }
}
