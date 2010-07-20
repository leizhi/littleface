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
import com.mooo.mycoz.dbobj.mycozShared.AccountCategory;
import com.mooo.mycoz.dbobj.mycozShared.AccountType;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.mycozShared.NoteType;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.ActionServlet;

import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class AccountTypeController  {

private static Log log = LogFactory.getLog(AccountTypeController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		Input in = new Input();
		// list for this
		in.addValue(request,"ID",request.getParameter("ID"));
		in.addValue(request,"Code",request.getParameter("Code"));
		in.addValue(request,"NoteType",request.getParameter("NoteType"));
		in.addValue(request,"Category",request.getParameter("Category"));
		in.addValue(request,"Description",request.getParameter("Description"));

		AccountType bt = new AccountType();
		ResultSet rs = null;

		String sql = "SELECT at.ID,at.Code,nt.Name,at.Description,ac.Code FROM AccountType AS at,AccountCategory AS ac,NoteType AS nt WHERE at.ID > 0 AND at.CategoryID=ac.ID AND at.NoteTypeID=nt.ID";
		var = request.getParameter("ID");
		if(var != null && !var.equals(""))
		sql += " AND at.ID LIKE '%" + var + "%'";

		var = request.getParameter("Code");
		if(var != null && !var.equals(""))
		sql += " AND at.Code LIKE '%" + var + "%'";

		var = request.getParameter("NoteType");
		if(var != null && !var.equals(""))
		sql += " AND nt.Name LIKE '%" + var + "%'";

		var = request.getParameter("Category");
		if(var != null && !var.equals(""))
		sql += " AND ac.Code LIKE '%" + var + "%'";

		var = request.getParameter("Definition");
		if(var != null && !var.equals(""))
		sql += " AND at.Description LIKE '%" + var + "%'";

		sql += " ORDER BY at.ID DESC" ;

		rs = bt.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			in.addValue(request,"ID"+i,rs.getString("at.ID"));
			in.addValue(request,"Code"+i,rs.getString("at.Code"));
			in.addValue(request,"NoteType"+i,rs.getString("nt.Name"));
			in.addValue(request,"Category"+i,rs.getString("ac.Code"));
			in.addValue(request,"Description"+i,rs.getString("at.Description"));

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

		NoteType nt = new NoteType();
		in.addHashMapValues(request,"NoteType",nt.getValues());

		AccountCategory ac = new AccountCategory();
		in.addHashMapValues(request,"Category",ac.getValues());

		in.addValue(request,"Description",request.getParameter("Description"));

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

	AccountType bt = new AccountType();
	id = bt.getNextID();

	String sql = "INSERT INTO AccountType(ID,Code,CategoryID,NoteTypeID,Description) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("Code")+"',";

	AccountCategory ac = new AccountCategory();
	sql += ac.getID(request.getParameter("Category"))+",";

	sql += request.getParameter("NoteType")+",";

	sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	bt.execute(sql);
	response.sendRedirect(request.getContextPath()+"/AccountType.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/AccountType.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		NoteType nt = new NoteType();
		AccountCategory ac = new AccountCategory();
		AccountType at = new AccountType();
		ResultSet rs = null;
		String sql = "";
		Input in = new Input();
		if(key != null) {
			sql +="SELECT ID,Code,NoteTypeID,CategoryID,Description FROM AccountType";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = at.getResultSet(sql);

			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addValue(request,"Code",rs.getString("Code"));
				in.addHashMapValues(request,"NoteType",nt.getValues(),rs.getString("NoteTypeID"));
				in.addHashMapValues(request,"Category",ac.getValues(),rs.getString("CategoryID"));
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
		AccountType at = new AccountType();
		AccountCategory ac = new AccountCategory();

		ResultSet rs = null;

		String sql = "UPDATE AccountType SET ";
		sql += " Code = '" + request.getParameter("Code") + "',";
		sql += " NoteTypeID = " + request.getParameter("NoteType") + ",";

		sql += " CategoryID =" + request.getParameter("Category") + ",";

		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		at.execute(sql);
		response.sendRedirect(request.getContextPath()+"/AccountType.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/AccountType.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		AccountType bt = new AccountType();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM AccountType";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/AccountType.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/AccountType.do?state=list");
     		}
	}

}
