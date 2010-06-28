package org.pig.dbobj.mycozShared;

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
import java.util.HashMap;
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


import org.pig.util.PigConfigNode;
import org.pig.util.DBLoad;
import org.pig.util.DBNode;
import org.pig.util.DBMap;
import org.pig.util.PigLoad;
import org.pig.util.PigNode;
import org.pig.util.PigMap;
import org.pig.util.MysqlConnection;

import org.pig.dbobj.DBObject;

import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

import org.pig.action.ActionServlet;

/**

 */
public class OperatorUser extends DBObject{

    private static Log log = LogFactory.getLog(OperatorUser.class);
    /**
     */
    public OperatorUser()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* OperatorUser() */

    public HashMap getValues()
        throws SQLException {

	HashMap< String, String> lMap = new HashMap< String, String>();
	lMap.put(null,"--select--");

	ResultSet rs = null;
	String sql = "SELECT ID,UserName FROM OperatorUser WHERE ID > 0";
	rs = getResultSet(sql);
	while(rs.next()) {
		lMap.put(rs.getString("ID"),rs.getString("UserName"));
               }

	return lMap;
    } /* getValues() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM OperatorUser";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public int getID(String userName) {
	int id = 0;
	try{
		ResultSet rs = null;
		String sql = "SELECT ID FROM OperatorUser";
		sql += " WHERE UserName='" + userName+"'";

		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("ID");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return id;
    } /* getID(String) */
}
