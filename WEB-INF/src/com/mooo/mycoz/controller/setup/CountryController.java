package com.mooo.mycoz.controller.setup;

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
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class CountryController  {

private static Log log = LogFactory.getLog(CountryController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		
		// list for this
		request.setAttribute("ID",request.getParameter("ID"));
		request.setAttribute("Code",request.getParameter("Code"));
		request.setAttribute("Name",request.getParameter("Name"));
		request.setAttribute("Description",request.getParameter("Description"));

		Country bt = new Country();
		ResultSet rs = null;

		String sql = "SELECT ID,Code,Name,Description FROM Country WHERE ID > 0";
		var = request.getParameter("ID");
		if(var != null && !var.equals(""))
		sql += " AND ID LIKE '%" + var + "%'";

		var = request.getParameter("Code");
		if(var != null && !var.equals(""))
		sql += " AND Code LIKE '%" + var + "%'";

		var = request.getParameter("Name");
		if(var != null && !var.equals(""))
		sql += " AND Name LIKE '%" + var + "%'";

		var = request.getParameter("Definition");
		if(var != null && !var.equals(""))
		sql += " AND Description LIKE '%" + var + "%'";

		rs = bt.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i,rs.getString("ID"));
			request.setAttribute("Code"+i,rs.getString("Code"));
			request.setAttribute("Name"+i,rs.getString("Name"));
			request.setAttribute("Description"+i,rs.getString("Description"));

			i++;
               	}

		in.addIterateValue(request,"List",i+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}


public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		
		request.setAttribute("Code",request.getParameter("Code"));
		request.setAttribute("Name",request.getParameter("Name"));
		request.setAttribute("Description",request.getParameter("Description"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String var = request.getParameter("Name");
	if ( var.equals("") ) 
		throw new Exception("Input Name NULL");

	if ( request.getParameter("Code").equals("") ) 
		throw new Exception("Input Code NULL");

	Country bt = new Country();
	id = bt.getNextID();

	String sql = "INSERT INTO Country(ID,Code,Name,Description) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("Code")+"',";
	sql += "'"+request.getParameter("Name")+"',";
	sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	bt.execute(sql);
	response.sendRedirect(request.getContextPath()+"/Country.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Country.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Country bt = new Country();
		ResultSet rs = null;
		String sql = "";
		
		if(key != null) {
			sql += "SELECT ID,Code,Name,Description FROM Country";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bt.getResultSet(sql);

			if(rs.first()) {
				request.setAttribute("ID",rs.getString("ID"));
				request.setAttribute("Code",rs.getString("Code"));
				request.setAttribute("Name",rs.getString("Name"));
				request.setAttribute("Description",rs.getString("Description"));
               		}

		}
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Country bt = new Country();
		ResultSet rs = null;

		String sql = "UPDATE Country SET ";
		sql += " Code = '" + request.getParameter("Code") + "',";
		sql += " Name = '" + request.getParameter("Name") + "',";
		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		bt.execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Country.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Country.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Country bt = new Country();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM Country";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Country.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Country.do?state=list");
     		}
	}

}
