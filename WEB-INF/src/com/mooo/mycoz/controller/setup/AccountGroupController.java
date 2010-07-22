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
import com.mooo.mycoz.dbobj.mycozShared.AccountGroup;
import com.mooo.mycoz.dbobj.mycozShared.AccountType;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class AccountGroupController  {

private static Log log = LogFactory.getLog(AccountGroupController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		
		// list for this
		request.setAttribute("ID",request.getParameter("ID"));
		request.setAttribute("Code",request.getParameter("Code"));
		request.setAttribute("Type",request.getParameter("Type"));
		request.setAttribute("Description",request.getParameter("Description"));

		AccountGroup ag = new AccountGroup();
		ResultSet rs = null;

		String sql = "SELECT ag.ID,ag.Code,ag.Description,at.Code FROM AccountGroup AS ag,AccountType AS at WHERE ag.ID > 0 AND ag.TypeID=at.ID";

		var = request.getParameter("ID");
		if(var != null && !var.equals(""))
		sql += " AND ag.ID LIKE '%" + var + "%'";

		var = request.getParameter("Code");
		if(var != null && !var.equals(""))
		sql += " AND ag.Code LIKE '%" + var + "%'";

		var = request.getParameter("Type");
		if(var != null && !var.equals(""))
		sql += " AND at.Code LIKE '%" + var + "%'";

		var = request.getParameter("Definition");
		if(var != null && !var.equals(""))
		sql += " AND ag.Description LIKE '%" + var + "%'";

		if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ag.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i,rs.getString("ag.ID"));
			request.setAttribute("Code"+i,rs.getString("ag.Code"));
			request.setAttribute("Type"+i,rs.getString("at.Code"));
			request.setAttribute("Description"+i,rs.getString("ag.Description"));

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

		AccountType at = new AccountType();
		in.addHashMapValues(request,"Type",at.getValues());

		request.setAttribute("Description",request.getParameter("Description"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String var = request.getParameter("Code");

	if ( request.getParameter("Code").equals("") ) 
		throw new Exception("Input Code NULL");

	AccountGroup ag = new AccountGroup();
	id = ag.getNextID();

	String sql = "INSERT INTO AccountGroup(ID,Code,TypeID,Description) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("Code")+"',";

	AccountType at = new AccountType();
	sql += at.getID(request.getParameter("Type"))+",";

	sql += "'"+request.getParameter("Description")+"')";

	if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	ag.execute(sql);
	response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		AccountGroup ag = new AccountGroup();
		AccountType at = new AccountType();
		ResultSet rs = null;
		String sql = "";
		
		if(key != null) {
			sql +="SELECT ID,Code,TypeID,Description FROM AccountGroup";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = ag.getResultSet(sql);

			if(rs.first()) {
				request.setAttribute("ID",rs.getString("ID"));
				request.setAttribute("Code",rs.getString("Code"));

				in.addHashMapValues(request,"Type",at.getValues(),rs.getString("TypeID"));

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
		AccountGroup ag = new AccountGroup();
		AccountType at = new AccountType();

		ResultSet rs = null;

		String sql = "UPDATE AccountGroup SET ";
		sql += " Code = '" + request.getParameter("Code") + "',";

		sql += " TypeID =" + request.getParameter("Type") + ",";

		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		ag.execute(sql);
		response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		AccountGroup bt = new AccountGroup();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM AccountGroup";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/AccountGroup.do?state=list");
     		}
	}

}
