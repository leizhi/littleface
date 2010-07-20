package com.mooo.mycoz.dbobj.mycozShared;

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




import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

import com.mooo.mycoz.dbobj.DBObject;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;

import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


/**

 */
public class Currency extends DBObject{

    private static Log log = LogFactory.getLog(Currency.class);
    /**
     */
    public Currency()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* Language() */

    public HashMap getValues()
        throws SQLException {

	HashMap lTable = new HashMap();
	lTable.put("0","--select--");

	ResultSet rs = null;
	String sql = "SELECT ID,ISOCode FROM Currency WHERE ID > 0";
	rs = getResultSet(sql);
	while(rs.next()) {
		lTable.put(rs.getString("ID"),rs.getString("ISOCode"));
               }

	return lTable;
    } /* getValues() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM Currency";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public String getID(String code)
        throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT ID FROM Currency WHERE ISOCode='"+code+"'";
		rs = getResultSet(sql);
		if(rs.first()) code = rs.getString("ID");

	return code;
    } /* getID(String) */

    public String getSymbol(String id)
        throws SQLException {

		String var = null;
		ResultSet rs = null;
		String sql = "SELECT Symbol FROM Currency WHERE ID="+id;
		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Symbol");

	return var;
    } /* getID(String) */

    public String getISOCode(String id)
        throws SQLException {

		String var = null;
		ResultSet rs = null;
		String sql = "SELECT ISOCode FROM Currency WHERE ID="+id;
		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("ISOCode");

	return var;
    } /* getID(String) */
}
