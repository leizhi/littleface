package com.mooo.mycoz.dbobj;

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
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public abstract class DBObject {

    	private static Log log = LogFactory.getLog(DBObject.class);
	private Connection DBConection = null;
    	private Statement stmt = null;
    	private ResultSet rs = null;
    	private String sql = null;

    	/**
     	 */
    	public DBObject()
             throws SQLException {
		getConection();
		getStatement();
    	} /* DBObject() */

	public void getConection() 
             throws SQLException {
		DBConection = MysqlConnection.getConection();
    	   } /* getConection() */

	public void getConection(String db)
             throws SQLException {
		DBConection = MysqlConnection.getConection(db);
    	   } /* getConection(String) */

	public void getStatement()
             throws SQLException {
		stmt = DBConection.createStatement();
    	   } /* getStatement() */

	public ResultSet getResultSet(String sql)
             throws SQLException {

		if (DBConection != null && stmt!=null )
			rs = stmt.executeQuery(sql);

		return rs;
    	   } /* getResultSet(String) */

	public void execute(String sql)

             throws SQLException {
		if (DBConection != null && stmt!=null )
			stmt.execute(sql);

    	   } /* execute(String) */

	public void prepareCall(String sql)

             throws SQLException {

		if (DBConection != null)
			DBConection.prepareCall(sql);

    	   } /* prepareCall(String) */

}
