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


import com.mooo.mycoz.action.ActionServlet;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.BlogType;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class BlogTypeController  {

private static Log log = LogFactory.getLog(BlogTypeController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		Input in = new Input();
		// list for this
		in.addValue(request,"ID",request.getParameter("ID"));
		in.addValue(request,"Code",request.getParameter("Code"));
		in.addValue(request,"Name",request.getParameter("Name"));
		in.addValue(request,"Description",request.getParameter("Description"));

		BlogType bt = new BlogType();
		ResultSet rs = null;

		String sql = "SELECT ID,Code,Name,Description FROM BlogType WHERE ID > 0";
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

			in.addValue(request,"ID"+i,rs.getString("ID"));
			in.addValue(request,"Code"+i,rs.getString("Code"));
			in.addValue(request,"Name"+i,rs.getString("Name"));
			in.addValue(request,"Description"+i,rs.getString("Description"));

			i++;
               	}

		in.addIterateValue(request,"List",i+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}


public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();
		in.addValue(request,"Code",request.getParameter("Code"));
		in.addValue(request,"Name",request.getParameter("Name"));
		in.addValue(request,"Description",request.getParameter("Description"));

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

	BlogType bt = new BlogType();
	id = bt.getNextID();

	String sql = "INSERT INTO BlogType(ID,Code,Name,Description) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("Code")+"',";
	sql += "'"+request.getParameter("Name")+"',";
	sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	bt.execute(sql);
	response.sendRedirect(request.getContextPath()+"/BlogType.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/BlogType.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		BlogType bt = new BlogType();
		ResultSet rs = null;
		String sql = "";
		Input in = new Input();
		if(key != null) {
			sql += "SELECT ID,Code,Name,Description FROM BlogType";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bt.getResultSet(sql);

			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addValue(request,"Code",rs.getString("Code"));
				in.addValue(request,"Name",rs.getString("Name"));
				in.addValue(request,"Description",rs.getString("Description"));
               		}

		}
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		BlogType bt = new BlogType();
		ResultSet rs = null;

		String sql = "UPDATE BlogType SET ";
		sql += " Code = '" + request.getParameter("Code") + "',";
		sql += " Name = '" + request.getParameter("Name") + "',";
		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		bt.execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/BlogType.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/BlogType.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		BlogType bt = new BlogType();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM BlogType";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/BlogType.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/BlogType.do?state=list");
     		}
	}

}
