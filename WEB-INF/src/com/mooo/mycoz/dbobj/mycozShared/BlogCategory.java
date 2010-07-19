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

import com.mooo.mycoz.action.ActionServlet;
import com.mooo.mycoz.dbobj.DBObject;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

/**

 */
public class BlogCategory extends DBObject{

    private static Log log = LogFactory.getLog(BlogCategory.class);
    /**
     */
    public BlogCategory()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* BlogCategory() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM BlogCategory";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public int getID(String name) {
	int id = 0;
	try{
		ResultSet rs = null;
		String sql = "SELECT ID FROM BlogCategory";
		sql += " WHERE Name='" + name+"'";

		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("ID");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return id;
    } /* getID(String) */

    public String getName(int id) {
        String var = null;
	try{
		ResultSet rs = null;
		String sql = "SELECT Name FROM BlogCategory";
		sql += " WHERE ID=" + id ;

		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Name");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return var;
    } /* getName(int) */

    public String getParent(String parentID) {
        String var = null;
	try{
		ResultSet rs = null;
		String sql = "SELECT Name FROM BlogCategory";
		sql += " WHERE ID=" + parentID ;

		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Name");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return var;
    } /*getParent(String parentID) */

    public String getParent(int parentID) {

	return getParent(Integer.toString(parentID));
    } /*getParent(String parentID) */

    public HashMap getValues(String parentID)
        throws SQLException {

	HashMap< String, String> lMap = new HashMap< String, String>();
	ResultSet rs = null;
	String sql = "SELECT ID,Name FROM BlogCategory WHERE ID > 0" + " AND ParentID="+parentID;
if (log.isDebugEnabled()) log.debug("sql=" + sql);
	rs = getResultSet(sql);
	lMap.put(null,"--select--");
	while(rs.next()) {
		lMap.put(rs.getString("ID"),rs.getString("Name"));
               }

	return lMap;
    } /* getValues(String parentID) */

    public HashMap getValues(int parentID)
        throws SQLException {

	return getValues(Integer.toString(parentID));

    } /* getValues(int parentID) */

    public int getLayerID(int parentID) {
       int  layerID = 0;
	try{
		ResultSet rs = null;
		String sql = "SELECT LayerID FROM BlogCategory";
		sql += " WHERE ID=" + parentID ;

		rs = getResultSet(sql);
		if(rs.first()) layerID = rs.getInt("LayerID");
		

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return ++layerID;
    } /*getLayerID(int parentID) */
 public int getLayerID(String parentID) {

	return getLayerID(Integer.valueOf(parentID).intValue());
	}
}
