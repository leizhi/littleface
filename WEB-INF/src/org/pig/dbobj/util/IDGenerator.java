package org.pig.dbobj.util;

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
import java.util.Calendar;
import java.util.TimeZone;

import org.pig.util.PigConfigNode;
import org.pig.util.DBLoad;
import org.pig.util.DBNode;
import org.pig.util.DBMap;
import org.pig.util.PigLoad;
import org.pig.util.PigNode;
import org.pig.util.PigMap;
import org.pig.util.MysqlConnection;

import org.pig.dbobj.mycozShared.Language;
import org.pig.dbobj.mycozShared.Country;
import org.pig.dbobj.mycozShared.BlogType;
import org.pig.dbobj.mycozBranch.User;
import org.pig.dbobj.mycozBranch.Blog;
import org.pig.dbobj.mycozBranch.Message;
import org.pig.dbobj.MultiDBObject;

public class IDGenerator {

private static Log log = LogFactory.getLog(IDGenerator.class);

    public static HashMap getReportTypes() {

	HashMap< String, String> lMap = new HashMap< String, String>();
	lMap.put(null,"--select--");
	lMap.put("Debit","Debit");
	lMap.put("Credit","Credit");
	lMap.put("Receipt","Receipt");
	lMap.put("Defrayment","Defrayment");

	return lMap;
    } /* getDCValues() */

    public static String getNextID(String table) 
			throws Exception,SQLException {
	String nextID="";
	try{
		int id = 0;
	 	Connection DBConection = MysqlConnection.getConection();
    	 	Statement stmt = DBConection.createStatement();
    	 	ResultSet rs = null;
    	 	String sql = "SELECT MAX(ID) AS MaxId FROM "+table;
		rs = stmt.executeQuery(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

		nextID=Integer.toString(id);

   		} catch (SQLException sqlE) {
            		if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        	} catch (Exception e) {
			if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
		}

	return nextID;
    } /* getNextID(String table) */

    public static int getBlogMessages(String blogID) throws IOException{
	int count = 0;

	try {
		MultiDBObject myMulti = new MultiDBObject();
		myMulti.addTable("mycozBranch.Message","msg");
		myMulti.setField("msg","BlogID",blogID);

		count = myMulti.count();

     	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	return count;
    } /* getBlogMessages(String blogID) */

    public static int getBlogMessages(int blogID) throws SQLException,IOException{
		return getBlogMessages(String.valueOf(blogID));
    } /* getBlogMessages(int blogID) */

    public static ArrayList getBlogTypes() throws IOException{
	ArrayList alType = null;
	String var = null;
	try {
	alType = new ArrayList();

	MultiDBObject myMulti = new MultiDBObject();
	myMulti.addTable("mycozShared.BlogCategory","bc");
	myMulti.setRetrieveField("bc","ID");

	List category = myMulti.searchAndRetrieveList();
	HashMap rowRecord = null;
	for(Iterator iterator= category.iterator(); iterator.hasNext();){
		rowRecord = (HashMap)iterator.next();
		var = rowRecord.get("bc.ID").toString();

		myMulti = new MultiDBObject();
		myMulti.addTable("mycozShared.BlogCategory","bc");
		myMulti.setField("bc","ParentID",var);

		if (myMulti.count()<1) alType.add(rowRecord.get("bc.ID").toString());
	   }

     	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	return alType;
    } /* getBlogTypes() */

    public static HashMap getBlogHashMapTypes() throws IOException{
	HashMap alType = null;
	String var = null;
	try {
	alType = new HashMap();
	alType.put(null,"--select--");

	MultiDBObject myMulti = new MultiDBObject();
	myMulti.addTable("mycozShared.BlogCategory","bc");
	myMulti.setRetrieveField("bc","ID");
	myMulti.setRetrieveField("bc","Name");

	List category = myMulti.searchAndRetrieveList();
	HashMap rowRecord = null;
	for(Iterator iterator= category.iterator(); iterator.hasNext();){
		rowRecord = (HashMap)iterator.next();
		var = rowRecord.get("bc.ID").toString();

		myMulti = new MultiDBObject();
		myMulti.addTable("mycozShared.BlogCategory","bc");
		myMulti.setField("bc","ParentID",var);

		if (myMulti.count()<1) alType.put(rowRecord.get("bc.ID").toString(),rowRecord.get("bc.Name").toString());
	   }

     	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	return alType;
    } /* getBlogTypes() */

    public static String getLastMonthToday(){

	// get default date in yyyy-mm-dd format
        TimeZone tz = TimeZone.getDefault();
        // tz.setID("Asia/Jakarta");
	Calendar now = Calendar.getInstance(tz);
        String prefix ;
        int yy = now.get(Calendar.YEAR);
        int tmp = now.get(Calendar.MONTH);
        if (tmp==0) {
          prefix = (yy-1) + "";
          tmp = 12;
        } else {
          prefix = yy + "";
        }
        if(tmp < 10)
            prefix += "-0"+tmp;
        else
            prefix += "-"+tmp;

	tmp = now.get(Calendar.DATE);
	  if(tmp < 10)
            prefix += "-0"+tmp;
        else
            prefix += "-"+tmp;

        return prefix;
    } /* getLastMonthToday() */

    public static String getToday(){

	// get default date in yyyy-mm-dd format
        TimeZone tz = TimeZone.getDefault();
	Calendar now = Calendar.getInstance(tz);
        String prefix = now.toString();
        int tmp = now.get(Calendar.YEAR);
        prefix = tmp + "";
        tmp = now.get(Calendar.MONTH)+1;
        if(tmp < 10)
            prefix += "-0"+tmp;
        else
            prefix += "-"+tmp;

	tmp = now.get(Calendar.DATE);
        if(tmp < 10)
            prefix += "-0"+tmp;
        else
            prefix += "-"+tmp;

        return prefix;
    } /* getToday() */
}
