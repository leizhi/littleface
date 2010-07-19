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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Vector;
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
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class MultiDBObject extends DBObject {

    	private static Log log = LogFactory.getLog(MultiDBObject.class);

	private String distinctRetrieveFild=null;
	private Vector retrieveFild=null;
	private String searchTable=null;
	private String foreignKey=null;
	private String customer=null;
	private int offset;
	private int rowcount;
	private Hashtable oneTable=null;
    	/**
     	 */
    	public MultiDBObject()
             throws SQLException {
		super.getConection();
		super.getStatement();
		distinctRetrieveFild = "";
		retrieveFild = new Vector();;
		searchTable="";
		foreignKey="";
		customer="";
		offset=0;
		rowcount=0;
		oneTable = new Hashtable();

    	} /* MultiDBObject() */

	public void setOffsetRecord(int offset) 
             throws SQLException {
		this.offset = offset;

    	   } /* setOffsetRecord(int offset) */

	public void setMaxRecords(int rowcount) 
             throws SQLException {
		this.rowcount = rowcount;

    	   } /* void setMaxRecords(int rowcount)  */

	public void addTable(String table) 
             throws SQLException {
		if (searchTable.length() >0)
			searchTable += ","+table;
		else
			searchTable += " "+table;
    	   } /* addTable(String table) */

	public void addTable(String table,String name) 
             throws SQLException {
		if (searchTable.length() >0)
			searchTable += ","+table+" AS " + name;
		else
			searchTable += " "+table+" AS " + name;
    	   } /* addTable(String table,String name) */

	public void setForeignKey(String sourceName,String sourceFild,String objectiveName,String objectiveFild) 
             throws SQLException {
		if(foreignKey.length() >0)
			foreignKey += " AND "+sourceName+"."+sourceFild+"="+objectiveName+"."+objectiveFild;
		else
			foreignKey += " "+sourceName+"."+sourceFild+"="+objectiveName+"."+objectiveFild;
    	   } /* addTable(String sourceName,String sourceFild,String objectiveName,String objectiveFild) */
	public void addCustomer(String sql) 
             throws SQLException {
			customer += sql;

    	   } /* addTable(String sql) */

	public void setField(String name,String field,String value) 
             throws SQLException {

		if (value != null && !value.equals("")) {
			if (foreignKey.length() >0)
				foreignKey += " AND "+name+"." + field+"='"+value+"'";
			else
				foreignKey += " "+name+"." + field+"='"+value+"'";
		}

    	   } /* setField(String name,String field,String value) */

	public void setField(String name,String field,int value) 
             throws SQLException {
			setField(name,field,Integer.toString(value));
    	   } /* setField(String name,String field,String value) */

	public void setField(String field,String value) 
             throws SQLException {
		if (value != null && !value.equals("")) {
			if (foreignKey.length() >0)
				foreignKey += " AND " + field+"='"+value+"'";
			else
				foreignKey += " " + field+"='"+value+"'";
		}
    	   } /* setField(String name,String field,String value) */

	public void setField(String field,int value) 
             throws SQLException {
			setField(field,Integer.toString(value));
    	   } /* setField(String name,String field,int value) */

	public void setRetrieveField(String name,String field) 
             throws SQLException {

			retrieveFild.add(name.trim()+"."+field.trim());

    	   } /* setRetrieveField(String name,String field) */

	public void setRetrieveField(String name,String field,String rName) 
             throws SQLException {

			retrieveFild.add(name.trim()+"."+field.trim()+" AS " + rName);

    	   } /* setRetrieveField(String name,String field) */

	public void setDistinctRetrieveField(String name,String field) 
             throws SQLException {

			distinctRetrieveFild=name.trim()+"."+field.trim();

    	   } /* setRetrieveField(String name,String field) */
	public List searchAndRetrieveList(String order) 
             throws Exception,SQLException {
		ArrayList retrieveList= null;
		int page = 0;
		try{
			String value = "";
			String tmpKey="";
			String tmpValue="";
			retrieveList= new ArrayList();
			ResultSet rs = null;
			Enumeration  enumeration = null;

			String sql = "SELECT ";

			value = "";
			if (distinctRetrieveFild!=null && !distinctRetrieveFild.equals("")) {
				value += " DISTINCT "+distinctRetrieveFild;
			}

              	for (int i=0; i<retrieveFild.size(); i++) {
                  		tmpValue = (String)retrieveFild.get(i);
				if (value.length() >0)
					value += ",";
				else
					value += " ";

				value += tmpValue;
			}

			sql += value;

			sql += " FROM "+searchTable;

			if(foreignKey!=null && !foreignKey.equals("") && !foreignKey.equals("null"))
				sql += " WHERE "+foreignKey;

			if(order!=null && !order.equals(""))
				sql += " ORDER BY "+order;

			if(customer!=null && !customer.equals(""))
				if (foreignKey!=null && !foreignKey.equals(""))
					sql += " "+customer;
				else
					sql += " WHERE "+customer;

			if(rowcount > 0)
				sql += " LIMIT "+offset+","+rowcount;

		if (log.isDebugEnabled()) log.debug("SQL=" + sql);
			HashMap rowRecord = null;

			rs = getResultSet(sql);
			while(rs.next()) {
				rowRecord = new HashMap();

				if (distinctRetrieveFild!=null && !distinctRetrieveFild.equals("")) {
					rowRecord.put(distinctRetrieveFild,rs.getString(distinctRetrieveFild));
					}

              		for (int i=0; i<retrieveFild.size(); i++) {
                  			tmpKey = (String)retrieveFild.get(i);
					tmpValue = rs.getString(tmpKey);
					if (tmpValue != null)
						rowRecord.put(tmpKey,tmpValue);
					else
						rowRecord.put(tmpKey,"");
				}
				retrieveList.add(rowRecord);
               		   }

        	} catch (SQLException sqlE) {
            		if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        	} catch (Exception e) {
			if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
		}

		return (List)retrieveList;

    	   } /* searchAndRetrieveList(String order) */

	public List searchAndRetrieveList() 
             throws Exception,SQLException {

		return searchAndRetrieveList(null);

    	   } /* searchAndRetrieveList() */

	public int count() 
             throws Exception,SQLException {
		int count=0;
		try{
			String value = "";
			String tmpKey="";
			String tmpValue="";
	
			ResultSet rs = null;

			String sql = "SELECT ";

			value = "";
			if (distinctRetrieveFild!=null && !distinctRetrieveFild.equals("")) {
				value += " DISTINCT "+distinctRetrieveFild;
			}
			value +=" COUNT(*) ";
			sql += value;
			sql += " FROM "+searchTable;

			if(foreignKey!=null && !foreignKey.equals("") && !foreignKey.equals("null"))
				sql += " WHERE "+foreignKey;

			if(customer!=null && !customer.equals(""))
				if (foreignKey!=null && !foreignKey.equals(""))
					sql += " "+customer;
				else
					sql += " WHERE "+customer;
            		if (log.isDebugEnabled()) log.debug("sql = " + sql);
			rs = getResultSet(sql);
			while(rs.next()) {
				count = rs.getInt("COUNT(*)");
               		   }

        	} catch (SQLException sqlE) {
            		if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        	} catch (Exception e) {
			if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
		}

		return count;

    	   } /* count() */

	public void setChangeField(String field,String value) 
             throws SQLException {
		oneTable.put(field,"'"+value.trim()+"'");

    	   } /* setChangeField(String field,String value) */

	public void setChangeField(String field,int value) 
             throws SQLException {
		setChangeField(field,Integer.toString(value));

    	   } /* setChangeField(String field,int value)  */

	public void add(String table) 
             throws Exception,SQLException {

		try{
			String key = "",value="";
			String sql = "";
			String orderFild="",orderValue="";

			 for(Iterator iterator=oneTable.keySet().iterator(); iterator.hasNext();){   
                          key = (String)iterator.next();   
                          value= (String)oneTable.get(key);
					if (orderFild.length()>0)
				  		orderFild +=","+key;
					else
						orderFild +=key;
					if (orderValue.length()>0)
				  		orderValue +=","+value;
					else
						orderValue +=value;
                 	   }
			sql += "INSERT INTO "+table+"("+orderFild;
			sql += ") VALUES ("+orderValue+")";

			execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL=" + sql);

        	} catch (SQLException sqlE) {
            		if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        	} catch (Exception e) {
			if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
		}

    	   } /* setRetrieveField(String name,String field) */

	public void update(String table) 
             throws Exception,SQLException {

		try{
			String key = "",value="";
			String sql = "";
			String updateValue="";

			 for(Iterator iterator=oneTable.keySet().iterator(); iterator.hasNext();){   
                          key = (String)iterator.next();   
                          value= (String)oneTable.get(key);
					if (updateValue.length()>0)
				  		updateValue +=","+key+"="+value;
					else
						updateValue +=" "+key+"="+value;
                 	   }

			sql += "UPDATE "+table+" SET "+updateValue +" WHERE " +foreignKey;
			if(foreignKey!=null && foreignKey.length()>0)
				execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL=" + sql);

        	} catch (SQLException sqlE) {
            		if (log.isDebugEnabled()) log.error("SQLException:" + sqlE.getMessage());
        	} catch (Exception e) {
			if (log.isDebugEnabled()) log.error("Exception: " + e.getMessage());
		}

    	   } /* setRetrieveField(String name,String field) */



}
