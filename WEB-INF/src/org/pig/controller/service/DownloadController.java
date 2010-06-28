package org.pig.controller.service;

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

import java.util.TimeZone;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Vector;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.text.DecimalFormat;

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
import org.pig.util.I18n;
import org.pig.util.UploadFile;

import org.pig.dbobj.mycozShared.DownloadType;
import org.pig.dbobj.mycozBranch.Download;
import org.pig.dbobj.MultiDBObject;
import org.pig.dbobj.util.IDGenerator;

import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

import org.pig.action.ActionServlet;
import org.pig.request.Input;

public class DownloadController  {

private static Log log = LogFactory.getLog(DownloadController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  try {
	Input in = new Input();
	long fileBit = 0;
	double fileK = 0.0;
	double fileM = 0.0;
	String uploadDirectory=request.getContextPath()+"/";
	String uploadPath = "/home/xpc/webapps/"+uploadDirectory;
	String value = null;
	String sql = null;
      File DownloadFile = null;

	DecimalFormat df = new DecimalFormat("###0.00");

	Download down = new Download();
	ResultSet rs = null;

	sql = "SELECT dl.ID,dt.Name,dl.Name,dl.Date,dl.Description,dl.DownloadPath,dl.ImagePath FROM Download AS dl,mycozShared.DownloadType AS dt WHERE dl.TypeID=dt.ID";

	value = request.getParameter("Key");
	if(value != null && !value.equals("")) {
		sql += " AND dt.ID="+value;
	   }

	rs = down.getResultSet(sql);
	int i = 0;
	while(rs.next()) {

		in.addValue(request,"ID"+i,rs.getString("dl.ID"));
		in.addValue(request,"Type"+i,rs.getString("dt.Name"));
		in.addValue(request,"Name"+i,rs.getString("dl.Name"));
		in.addValue(request,"Date"+i,rs.getString("dl.Date"));

		value = rs.getString("dl.DownloadPath");
		in.addValue(request,"DownloadPath"+i,uploadDirectory+value);

		DownloadFile = new File(uploadPath+value);
		if(!DownloadFile.exists())
			throw new IOException("Not File for "+uploadPath+value);

		fileBit = DownloadFile.length();
		fileK = fileBit/1024;
		fileM = fileK/1024;

		in.addValue(request,"FileLength"+i,df.format(fileM)+"M");

		i++;
             }

	in.addIterateValue(request,"List",i+"");

	
if (log.isDebugEnabled()) log.debug("SQL: " + sql);
     } catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     } catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     	}
}

public void promptAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
  try {	
	Input in = new Input();
	in.addValue(request,"ID",request.getParameter("ID"));
	in.addHashMapValues(request,"TypeID",new DownloadType().getValues(),request.getParameter("TypeID"));
	in.addValue(request,"Name",request.getParameter("Name"));
	in.addValue(request,"Date",request.getParameter("Date"));
	in.addValue(request,"DownloadPath",request.getParameter("DownloadPath"));
	in.addValue(request,"ImagePath",request.getParameter("ImagePath"));
	in.addValue(request,"Description",request.getParameter("Description"));
	
     } catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     } catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     	}
}

public void processAddStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	int id=0;
	String tmpName = "";
	String tmpValue ="";
	String uploadDirectory = "upload/";
	String uploadPath = request.getContextPath()+"/"+uploadDirectory;
	UploadFile uf = new UploadFile();
	uf.setRequest(request);
	uf.setUploadPath("/home/xpc/webapps"+uploadPath);
	uf.process();

	tmpValue = uf.getParameter("Name");
	if (tmpValue==null || tmpValue.equals("")) 
		throw new Exception("Input Name NULL");

	MultiDBObject myMutil = new MultiDBObject();
	myMutil.setChangeField("ID",IDGenerator.getNextID("Download"));
	myMutil.setChangeField("TypeID",uf.getParameter("TypeID"));
	myMutil.setChangeField("Name",uf.getParameter("Name"));

	Vector colName = new Vector();
	colName.add("DownloadPath");
	colName.add("ImagePath");

	Iterator fileList = uf.getFileIterator();
	int k=0;
	while(fileList.hasNext()){
		tmpName = (String)colName.get(k);
		tmpValue = (String)fileList.next();
		myMutil.setChangeField(tmpName,uploadDirectory+tmpValue.replace("'","."));
		k++;
		}

        TimeZone tz = TimeZone.getDefault();
        Calendar now = Calendar.getInstance(tz);
	String nowDate = now.get(Calendar.YEAR)+"-"+ (now.get(Calendar.MONTH)+1) +"-"+now.get(Calendar.DAY_OF_MONTH);

	tmpValue = uf.getParameter("Date");
	if(tmpValue !=null && !tmpValue.equals(""))
		myMutil.setChangeField("Date",tmpValue);
	else
		myMutil.setChangeField("Date",nowDate);

	myMutil.setChangeField("Description",uf.getParameter("Description"));
	myMutil.add("Download");
	response.sendRedirect(request.getContextPath()+"/Download.do?state=list");
	
     	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {

      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Download.do?state=promptAdd");
     	}
}

public void promptUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
		String key = request.getParameter("Key");
		Download bt = new Download();
		ResultSet rs = null;
		String sql = "";
		Input in = new Input();

		if(key != null) {
			sql += "SELECT ID,TypeID,Name,Date,DownloadPath,ImagePath,Description FROM Download";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bt.getResultSet(sql);

			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addHashMapValues(request,"TypeID",new DownloadType().getValues(),rs.getString("TypeID"));
				in.addValue(request,"Name",rs.getString("Name"));
				in.addValue(request,"Date",rs.getString("Date"));
				in.addValue(request,"DownloadPath",rs.getString("DownloadPath"));
				in.addValue(request,"ImagePath",rs.getString("ImagePath"));
				in.addValue(request,"Description",rs.getString("Description"));
               		}

		}
		

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	String value ="";
	String uploadDirectory = "upload/";
	String uploadPath = request.getContextPath()+"/"+uploadDirectory;

	UploadFile uf = new UploadFile();
	uf.setRequest(request);
	uf.setUploadPath("/home/xpc/webapps"+uploadPath);
	uf.process();

	value = uf.getParameter("Name");
	if (value==null || value.equals("")) 
		throw new Exception("Input Name NULL");

	Download dl = new Download();

	String sql = "UPDATE Download SET";
	sql += " TypeID="+uf.getParameter("TypeID").trim()+",";
	sql += " Name='"+uf.getParameter("Name").trim()+"',";
	sql += " Date='"+uf.getParameter("Date").trim()+"',";
	sql += " Description='"+uf.getParameter("Description").trim()+"'";

	value = "";
	Iterator fileList = uf.getFileIterator();
	while(fileList.hasNext()){
		value += (String)fileList.next()+",";
		}

	if(!(value.indexOf(",")<0)) {
	value = value.substring(0,value.length()-1);
	sql += ", DownloadPath='"+uploadDirectory+(value.substring(0,value.indexOf(","))).trim()+"',";
	sql += " ImagePath='"+uploadDirectory+(value.substring(value.indexOf(",")+1)).trim()+"'";
	}

	sql += " WHERE ID="+uf.getParameter("ID").trim();

	if (log.isDebugEnabled()) log.debug("SQL: " + sql);
	dl.execute(sql);
	response.sendRedirect(request.getContextPath()+"/Download.do");
	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Download.do");
     		}
	}
public void processDeleteStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
		String key = request.getParameter("Key");
		Download dl = new Download();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM Download";
			sql += " WHERE ID = " + key;
			dl.execute(sql);
		    }

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Download.do?state=list");
	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Download.do?state=list");
     		}
	}

public void onListStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	Input in = new Input();
	long fileBit = 0;
	double fileK = 0.0;
	double fileM = 0.0;
	String uploadPath = "/home/xpc/webapps";
	String value = null;
	String sql = null;
      File DownloadFile = null;

	DecimalFormat df = new DecimalFormat("###0.00");

	Download down = new Download();
	ResultSet rs = null;

	sql = "SELECT dl.ID,dt.Name,dl.Name,dl.Date,dl.Description,dl.DownloadPath,dl.ImagePath FROM Download AS dl,mycozShared.DownloadType AS dt WHERE dl.TypeID=dt.ID";

	value = request.getParameter("Key");
	if(value != null && !value.equals("")) {
		sql += " AND dt.ID="+value;
	   }

	rs = down.getResultSet(sql);
	int i = 0;
	while(rs.next()) {

		in.addValue(request,"ID"+i,rs.getString("dl.ID"));
		in.addValue(request,"DownloadType"+i,rs.getString("dt.Name"));
		in.addValue(request,"SongName"+i,rs.getString("dl.Name"));
		in.addValue(request,"Date"+i,rs.getString("dl.Date"));

		value = rs.getString("dl.DownloadPath");
		in.addValue(request,"DownloadPath"+i,value);

		DownloadFile = new File(uploadPath+value);
		if(!DownloadFile.exists())
			throw new IOException("Not File for "+uploadPath+value);

		fileBit = DownloadFile.length();
		fileK = fileBit/1024;
		fileM = fileK/1024;

		in.addValue(request,"FileLength"+i,df.format(fileM)+"M");

//if (log.isDebugEnabled()) log.debug("File "+value + " size:" + fileBit + "Bit");
//if (log.isDebugEnabled()) log.debug("File "+value + " size:" + df.format(fileK)+"K");
//if (log.isDebugEnabled()) log.debug("File "+value + " size:" + df.format(fileM)+"M");
		i++;
             }
	in.addIterateValue(request,"List",i+"");
	
	//Iterate.setValue("List",i+"");

	//if (log.isDebugEnabled()) log.debug("SQL:"+sql);
	//response.sendRedirect(request.getContextPath()+"/Download.do?state=list");
	} catch (SQLException se) {
      		if (log.isDebugEnabled()) log.error("SQLException Load error of: " + se.getMessage());
     	} catch (Exception e) {
      		if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		//response.sendRedirect(request.getContextPath()+"/Download.do?state=list");
     		}
	}

}
