package com.mooo.mycoz.dbobj.mycozBranch;

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
import com.mooo.mycoz.dbobj.DBObject;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

/**

 */
public class JobAccounting extends DBObject{
private static Log log = LogFactory.getLog(JobAccounting.class);
    /**
     */
    public JobAccounting() 
	throws SQLException{
	getConection();
	getStatement();
    } /* JobAccounting() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM JobAccounting";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public String getAmount(String JobNoteID) {

	double count = 0.0;
	String var = "";
	ResultSet rs = null;

	try {
		String sql = "SELECT ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol AS Symbol FROM JobAccounting AS ja,mycozShared.Currency AS cy WHERE ja.CurrencyID = cy.ID";
		sql += " AND ja.JobNoteID='" + JobNoteID + "'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);

		rs = getResultSet(sql);
		while(rs.next()) {
			count += rs.getDouble("Amount");
			var = rs.getString("Symbol");
		 }

	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException: " + se.getMessage());
     	} catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return (new Double(count)).toString() + var;

    }/* getAmount(String) */

    public String getBillAmount(String JobNoteID) {

	double count = 0.0;
	String var = "";
	ResultSet rs = null;

	try {
		String sql = "SELECT ja.ItemRate*ja.ItemQuantity AS Amount,cy.Symbol AS Symbol FROM JobAccounting AS ja,mycozShared.Currency AS cy WHERE ja.CurrencyID = cy.ID";
		sql += " AND ja.JobNoteID='" + JobNoteID + "'";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);

		rs = getResultSet(sql);
		while(rs.next()) {
			count += rs.getDouble("Amount");
			var = rs.getString("Symbol");
		 }

	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException: " + se.getMessage());
     	} catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return (new Double(count)).toString() + var;

    }/* getAmount(String) */
}
