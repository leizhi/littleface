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
public class NoteType extends DBObject{

    private static Log log = LogFactory.getLog(NoteType.class);
    /**
     */
    public NoteType()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* Language() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM NoteType";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public HashMap getValues()
        throws SQLException {

	return getValues(null);
    } /* getValues() */

    public HashMap getValues(String category)
        throws SQLException {

	HashMap lTable = new HashMap();
	lTable.put(null,"--select--");

	ResultSet rs = null;
	String sql = "SELECT ID,Name FROM NoteType WHERE ID > 0";
	if(category != null)
		sql += " AND Category='"+category+"'";

	rs = getResultSet(sql);
	while(rs.next()) {
		lTable.put(rs.getString("ID"),rs.getString("Name"));
               }

	return lTable;
    } /* getValues(String) */

    public HashMap getDCValues() {

	HashMap lTable = new HashMap();
	lTable.put(null,"--select--");
	lTable.put("Credit","Credit");
	lTable.put("Debit","Debit");

	return lTable;
    } /* getDCValues() */

    public int getID(String code) {
	int id = 0;
	try{
		ResultSet rs = null;
		String sql = "SELECT ID FROM NoteType";
		sql += " WHERE Code='" + code+"'";

		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("ID");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return id;
    } /* getID(String) */

    public String getCode(String id) {
        String var = null;
	try{
		ResultSet rs = null;
		String sql = "SELECT Code FROM NoteType";
		sql += " WHERE ID=" + id ;

		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Code");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return var;
    } /* getCode(String) */

    public String getName(String id) {
        String var = null;
	try{
		ResultSet rs = null;
		String sql = "SELECT Name FROM NoteType";
		sql += " WHERE ID=" + id ;

		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Name");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return var;
    } /* getName(String) */

    public String getCategory(String id) {
        String var = null;
	try{
		ResultSet rs = null;
		String sql = "SELECT Category FROM NoteType";
		sql += " WHERE ID=" + id ;

		rs = getResultSet(sql);
		if(rs.first()) var = rs.getString("Category");

        } catch (SQLException sqlE) {
            	if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        } catch (Exception e) {
		if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
	}

	return var;
    } /* getName(String) */

}
