package com.mooo.mycoz.controller.setup;

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
import java.util.HashSet;
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


import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.BlogCategory;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.Input;

import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class BlogCategoryController  {

private static Log log = LogFactory.getLog(BlogCategoryController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		Input in = new Input();
		BlogCategory bc = null;

		MultiDBObject myMutil = new MultiDBObject();
		myMutil.addTable("mycozShared.BlogCategory","bc");
		myMutil.setDistinctRetrieveField("bc","LayerID");
		myMutil.setDistinctRetrieveField("bc","LayerID");
		myMutil.addCustomer(" bc.ID > 0");
		List layer = myMutil.searchAndRetrieveList();

		in.addIterateValue(request,"Layer",layer.size()+"");
		List row = new ArrayList();
		HashMap hmTmp = null;
		List category = null;

		myMutil = new MultiDBObject();
		myMutil.addTable("mycozShared.BlogCategory","bc");
		String parentID=null;
		for (int i=0;i<layer.size();i++) {
			bc = new BlogCategory();
			if (i>0) {
				var = request.getParameter("BlogCategory"+(i-1));
				if (var != null && !var.equals("null")) {
					in.addHashMapValues(request,"BlogCategory"+i,bc.getValues(var),request.getParameter("BlogCategory"+i));
					parentID = var;
				}else{
					in.addHashMapValues(request,"BlogCategory"+i,bc.getValues(i),request.getParameter("BlogCategory"+i));
				  }
			}else{
				in.addHashMapValues(request,"BlogCategory"+i,bc.getValues(i),request.getParameter("BlogCategory"+i));
			}
		}

if (log.isDebugEnabled()) log.debug("ParentID=" + parentID);
			if (parentID == null || parentID.equals("null")) parentID="0";
			myMutil.setField("bc","ParentID",parentID);
			in.addValue(request,"ParentID",parentID);
			myMutil.setRetrieveField("bc","ID");
			myMutil.setRetrieveField("bc","Name");
			myMutil.setRetrieveField("bc","ParentID");

			category = myMutil.searchAndRetrieveList();
			HashMap rowRecord = null;
			int i=0;
			for(Iterator iterator= category.iterator(); iterator.hasNext();){
				rowRecord = (HashMap)iterator.next();
				in.addValue(request,"ID"+i,rowRecord.get("bc.ID").toString());
				in.addValue(request,"Name"+i,rowRecord.get("bc.Name").toString());
				in.addValue(request,"Parent"+i,bc.getParent(rowRecord.get("bc.ParentID").toString()));
				i++;
			   }
			in.addIterateValue(request,"List",i+"");
			//in.addBoxListField("bc","ID","ID");
			//in.addBoxListField("bc","Name","Name");
			//in.addBoxList(request,"Category",category);
			in.addSubmit(request,"Add","BlogCategory","promptAdd");
			in.addSubmit(request,"Delete","BlogCategory","processDelete");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}


public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();

		in.addValue(request,"Name",request.getParameter("Name"));
		if(request.getParameter("Parent") != null)
			in.addValue(request,"Parent",request.getParameter("Parent"));
		else 
			in.addValue(request,"Parent",new BlogCategory().getParent(request.getParameter("ParentID")));
		in.addValue(request,"Description",request.getParameter("Description"));
		in.addValue(request,"ParentID",request.getParameter("ParentID"));
		if (log.isDebugEnabled()) log.debug("ParentID=" + request.getParameter("ParentID"));

		in.addSubmit(request,"Add","BlogCategory","processAdd");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String value = null;
	BlogCategory bc = new BlogCategory();

	MultiDBObject myMutil = new MultiDBObject();
	myMutil.setChangeField("ID",IDGenerator.getNextID("mycozShared.BlogCategory"));
	myMutil.setChangeField("ParentID",request.getParameter("ParentID"));
	myMutil.setChangeField("LayerID",bc.getLayerID(request.getParameter("ParentID")));
	myMutil.setChangeField("Name",request.getParameter("Name"));
	myMutil.setChangeField("Description",request.getParameter("Description"));
	myMutil.add("mycozShared.BlogCategory");

	response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=list");
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		BlogCategory bt = new BlogCategory();
		BlogCategory bc = null;
 
		ResultSet rs = null;
		String sql = "";
		Input in = new Input();
		if (log.isDebugEnabled()) log.debug("Key:"+key);
		if(key != null) {
			sql += "SELECT ID,Name,Description,ParentID FROM BlogCategory";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bt.getResultSet(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		if (log.isDebugEnabled()) log.debug("rs.first():"+rs.first());
			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addValue(request,"Parent",new BlogCategory().getParent(rs.getString("ParentID")));
				in.addValue(request,"ParentID",rs.getString("ParentID"));
				in.addValue(request,"Name",rs.getString("Name"));
				in.addValue(request,"Description",rs.getString("Description"));
               		}

		}
		
		in.addSubmit(request,"Update","BlogCategory","processUpdate");
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {

	MultiDBObject myMutil = new MultiDBObject();
	myMutil.setField("ID",request.getParameter("ID"));
	//myMutil.setChangeField("ParentID",request.getParameter("ParentID"));
	//myMutil.setChangeField("LayerID",new BlogCategory().getLayerID(request.getParameter("ParentID")));
	myMutil.setChangeField("Name",request.getParameter("Name"));
	myMutil.setChangeField("Description",request.getParameter("Description"));
	myMutil.update("mycozShared.BlogCategory");

	response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=list");
 } catch (Exception e) {
     	if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
	response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=promptUpdate");
     	}
}


public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		BlogCategory bt = new BlogCategory();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM BlogCategory";
			sql += " WHERE ID = " + key;
			bt.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=list");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/BlogCategory.do?state=list");
     		}
	}

}
