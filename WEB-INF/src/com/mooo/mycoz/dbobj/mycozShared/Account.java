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
public class Account extends DBObject{

    private static Log log = LogFactory.getLog(Account.class);
    /**
     */
    public Account()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* AccountCategory() */

    public HashMap getValues()
        throws SQLException {

	return getValues(null);
    } /* getValues() */

    public HashMap getValues(String noteTypeID)
        throws SQLException {

	HashMap< String, String> lMap = new HashMap< String, String>();
	lMap.put(null,"--select--");

	ResultSet rs = null;
	String sql = "SELECT Account.ID,Account.Code FROM Account,AccountGroup,AccountType WHERE Account.GroupID=AccountGroup.ID AND  AccountGroup.TypeID=AccountType.ID";

	if(noteTypeID != null)
		sql += " AND AccountType.NoteTypeID="+noteTypeID;

	rs = getResultSet(sql);
	while(rs.next()) {
		lMap.put(rs.getString("Account.ID"),rs.getString("Account.Code"));
               }

	return lMap;
    } /* getValues(String) */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM Account";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public String getCode(String id)
        throws SQLException {
		String code = "";
		ResultSet rs = null;
		String sql = "SELECT Code FROM Account WHERE ID='"+id+"'";
		rs = getResultSet(sql);
		if(rs.first()) code = rs.getString("Code");

	return code;
    } /* getCode(String) */

    public String getID(String code)
        throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT ID FROM Account WHERE Code='"+code+"'";
		rs = getResultSet(sql);
		if(rs.first()) code = rs.getString("ID");

	return code;
    } /* getID(String) */

    public String getCurrency(String id)
        throws SQLException {
		String code = "";
		ResultSet rs = null;
		String sql = "SELECT Currency.ISOCode FROM Account,Currency WHERE Account.CurrencyID=Currency.ID AND Account.ID='"+id+"'";
		rs = getResultSet(sql);
		if(rs.first()) code = rs.getString("Currency.ISOCode");

	return code;
    } /* getCurrency(String) */

}
