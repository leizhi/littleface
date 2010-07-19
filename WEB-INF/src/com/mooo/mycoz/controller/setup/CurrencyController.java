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
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Currency;
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

public class CurrencyController  {

private static Log log = LogFactory.getLog(CurrencyController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		Input in = new Input();
		// list for this
		in.addValue(request,"ID",request.getParameter("ID"));
		in.addValue(request,"ISOCode",request.getParameter("Code"));
		in.addValue(request,"Name",request.getParameter("Name"));
		in.addValue(request,"Country",request.getParameter("Country"));
		in.addValue(request,"Symbol",request.getParameter("Symbol"));

		Currency currency = new Currency();
		ResultSet rs = null;

		String sql = "SELECT Currency.ID,Currency.ISOCode,Currency.Symbol,Currency.Name,Country.Code FROM Currency, Country WHERE Currency.ID > 0 AND Currency.CountryID=Country.ID";
		var = request.getParameter("ID");
		if(var != null && !var.equals(""))
		sql += " AND Currency.ID LIKE '%" + var + "%'";

		var = request.getParameter("ISOCode");
		if(var != null && !var.equals(""))
		sql += " AND Currency.ISOCode LIKE '%" + var + "%'";

		var = request.getParameter("Name");
		if(var != null && !var.equals(""))
		sql += " AND Currency.Name LIKE '%" + var + "%'";

		var = request.getParameter("Country");
		if(var != null && !var.equals(""))
		sql += " AND Country.Code LIKE '%" + var + "%'";

		rs = currency.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			in.addValue(request,"ID"+i,rs.getString("Currency.ID"));
			in.addValue(request,"ISOCode"+i,rs.getString("Currency.ISOCode"));
			in.addValue(request,"Name"+i,rs.getString("Currency.Name"));
			in.addValue(request,"Country"+i,rs.getString("Country.Code"));
			in.addValue(request,"Symbol"+i,rs.getString("Currency.Symbol"));

			i++;
               	}

		in.addIterateValue(request,"List",i+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}


public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Country country = new Country();
		Input in = new Input();
		in.addValue(request,"ISOCode",request.getParameter("ISOCode"));
		in.addValue(request,"Name",request.getParameter("Name"));

		in.addHashMapValues(request,"Country",country.getValues());

		in.addValue(request,"Symbol",request.getParameter("Symbol"));

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

	if ( request.getParameter("ISOCode").equals("") ) 
		throw new Exception("Input ISOCode NULL");

	Currency bt = new Currency();
	id = bt.getNextID();

	String sql = "INSERT INTO Currency(ID,ISOCode,Name,Symbol,CountryID) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("ISOCode")+"',";
	sql += "'"+request.getParameter("Name")+"',";
	sql += "'"+request.getParameter("Symbol")+"',";

	Country country = new Country();
	sql += country.getID(request.getParameter("Country"))+")";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	bt.execute(sql);
	response.sendRedirect(request.getContextPath()+"/Currency.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Currency.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Country country = new Country();
		Currency currency = new Currency();
		ResultSet rs = null;
		String sql = "";
		Input in = new Input();
		if(key != null) {
			sql += "SELECT ID,ISOCode,Name,Symbol,CountryID FROM Currency";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = currency.getResultSet(sql);

			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addValue(request,"ISOCode",rs.getString("ISOCode"));
				in.addValue(request,"Name",rs.getString("Name"));
				in.addValue(request,"Symbol",rs.getString("Symbol"));

				in.addHashMapValues(request,"Country",country.getValues(),rs.getString("CountryID"));
               		}

		}
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Currency bt = new Currency();
		ResultSet rs = null;

		String sql = "UPDATE Currency SET ";
		sql += " ISOCode = '" + request.getParameter("ISOCode") + "',";
		sql += " Name = '" + request.getParameter("Name") + "',";
		sql += " Symbol = '" + request.getParameter("Symbol") + "',";
		sql += " CountryID = '" + request.getParameter("Country") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		bt.execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Currency.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Currency.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Currency bt = new Currency();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM Currency";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Currency.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Currency.do?state=list");
     		}
	}

}
