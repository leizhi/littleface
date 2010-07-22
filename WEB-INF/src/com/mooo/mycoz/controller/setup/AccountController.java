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
import com.mooo.mycoz.dbobj.mycozShared.Account;
import com.mooo.mycoz.dbobj.mycozShared.AccountExtension;
import com.mooo.mycoz.dbobj.mycozShared.AccountGroup;
import com.mooo.mycoz.dbobj.mycozShared.AccountType;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Currency;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class AccountController  {

private static Log log = LogFactory.getLog(AccountController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		
		// list for this
		request.setAttribute("ID",request.getParameter("ID"));
		request.setAttribute("Code",request.getParameter("Code"));
		request.setAttribute("Group",request.getParameter("Group"));
		request.setAttribute("Extension",request.getParameter("Extension"));
		request.setAttribute("Currency",request.getParameter("Currency"));
		request.setAttribute("Description",request.getParameter("Description"));

		Account ag = new Account();
		ResultSet rs = null;

		String sql = "SELECT at.ID,at.Code,at.Description,ae.Code,ag.Code, cy.ISOCode FROM Account AS at,AccountExtension AS ae,AccountGroup AS ag, Currency AS cy WHERE at.ID > 0 AND at.GroupID=ag.ID AND at.ExtensionID=ae.ID AND at.CurrencyID=cy.ID";

		var = request.getParameter("ID");
		if(var != null && !var.equals(""))
		sql += " AND at.ID LIKE '%" + var + "%'";

		var = request.getParameter("Code");
		if(var != null && !var.equals(""))
		sql += " AND at.Code LIKE '%" + var + "%'";

		var = request.getParameter("Definition");
		if(var != null && !var.equals(""))
		sql += " AND at.Description LIKE '%" + var + "%'";

		var = request.getParameter("Extension");
		if(var != null && !var.equals(""))
		sql += " AND ae.Code LIKE '%" + var + "%'";

		var = request.getParameter("Group");
		if(var != null && !var.equals(""))
		sql += " AND ag.Code LIKE '%" + var + "%'";

		var = request.getParameter("Currency");
		if(var != null && !var.equals(""))
		sql += " AND cy.ISOCode LIKE '%" + var + "%'";

		if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		rs = ag.getResultSet(sql);
		int i = 0;
		while(rs.next()) {

			request.setAttribute("ID"+i,rs.getString("at.ID"));
			request.setAttribute("Code"+i,rs.getString("at.Code"));
			request.setAttribute("Description"+i,rs.getString("at.Description"));
			request.setAttribute("Extension"+i,rs.getString("ae.Code"));
			request.setAttribute("Group"+i,rs.getString("ag.Code"));
			request.setAttribute("Currency"+i,rs.getString("cy.ISOCode"));

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
		request.setAttribute("Description",request.getParameter("Description"));

		AccountExtension ae = new AccountExtension();
		in.addHashMapValues(request,"Extension",ae.getValues());

		AccountGroup ag = new AccountGroup();
		in.addHashMapValues(request,"Group",ag.getValues());

		Currency cy = new Currency();
		in.addHashMapValues(request,"Currency",cy.getValues());

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

	Account accout = new Account();
	id = accout.getNextID();

	String sql = "INSERT INTO Account(ID,Code,ExtensionID,GroupID,CurrencyID,Description) VALUES(";
	sql += id+",";
	sql += "'"+request.getParameter("Code")+"',";

	AccountExtension ae = new AccountExtension();
	sql += ae.getID(request.getParameter("Extension"))+",";

	AccountGroup ag = new AccountGroup();
	sql += ag.getID(request.getParameter("Group"))+",";

	Currency cy = new Currency();
	sql += cy.getID(request.getParameter("Currency"))+",";

	sql += "'"+request.getParameter("Description")+"')";

	if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	accout.execute(sql);
	response.sendRedirect(request.getContextPath()+"/Account.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Account.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		
		String key = request.getParameter("Key");
		Account ac = new Account();
		AccountExtension ae = new AccountExtension();
		AccountGroup ag = new AccountGroup();
		Currency cy = new Currency();

		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql +="SELECT ID,Code,ExtensionID,GroupID,CurrencyID,Description FROM Account";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = ac.getResultSet(sql);

			if(rs.first()) {
				request.setAttribute("ID",rs.getString("ID"));
				request.setAttribute("Code",rs.getString("Code"));

				in.addHashMapValues(request,"Extension",ae.getValues(),rs.getString("ExtensionID"));
				in.addHashMapValues(request,"Group",ag.getValues(),rs.getString("GroupID"));
				in.addHashMapValues(request,"Currency",cy.getValues(),rs.getString("CurrencyID"));

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
		Account ag = new Account();
		AccountType at = new AccountType();

		ResultSet rs = null;

		String sql = "UPDATE Account SET ";
		sql += " Code = '" + request.getParameter("Code") + "',";

		sql += " ExtensionID =" + request.getParameter("Extension") + ",";
		sql += " GroupID =" + request.getParameter("Group") + ",";
		sql += " CurrencyID =" + request.getParameter("Currency") + ",";

		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		ag.execute(sql);
		response.sendRedirect(request.getContextPath()+"/Account.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Account.do?state=promptUpdate");
     		}
	}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Account bt = new Account();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM Account";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Account.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Account.do?state=list");
     		}
	}

}
