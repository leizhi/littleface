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
public class DownloadType extends DBObject{

    private static Log log = LogFactory.getLog(DownloadType.class);
    /**
     */
    public DownloadType()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* DownloadType() */

    public HashMap getValues()
        throws SQLException {

	HashMap lTable = new HashMap();
	lTable.put(null,"--select--");
	ResultSet rs = null;
	String sql = "SELECT ID,Name FROM DownloadType WHERE ID > 0";
	rs = getResultSet(sql);
	while(rs.next()) {
		lTable.put(rs.getString("ID"),rs.getString("Name"));
               }

	return lTable;
    } /* getValues() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM DownloadType";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public String getName(String id)
        throws SQLException {
		String name = "";
		ResultSet rs = null;
		String sql = "SELECT Name FROM DownloadType WHERE ID='"+id+"'";
		rs = getResultSet(sql);
		if(rs.first()) name = rs.getString("Name");

	return name;
    }
}
