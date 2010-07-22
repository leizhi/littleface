package com.mooo.mycoz.controller.service;

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




import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;


import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.Download;
import com.mooo.mycoz.dbobj.mycozShared.DownloadType;
import com.mooo.mycoz.jdbc.DBLoad;
import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;
import com.mooo.mycoz.jdbc.MysqlConnection;
import com.mooo.mycoz.util.ActionServlet;
import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.IDGenerator;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;

import com.mooo.mycoz.util.UploadFile;

public class DownloadController  {

private static Log log = LogFactory.getLog(DownloadController.class);

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  try {
	
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

		request.setAttribute("ID"+i,rs.getString("dl.ID"));
		request.setAttribute("Type"+i,rs.getString("dt.Name"));
		request.setAttribute("Name"+i,rs.getString("dl.Name"));
		request.setAttribute("Date"+i,rs.getString("dl.Date"));

		value = rs.getString("dl.DownloadPath");
		request.setAttribute("DownloadPath"+i,uploadDirectory+value);

		DownloadFile = new File(uploadPath+value);
		if(!DownloadFile.exists())
			throw new IOException("Not File for "+uploadPath+value);

		fileBit = DownloadFile.length();
		fileK = fileBit/1024;
		fileM = fileK/1024;

		request.setAttribute("FileLength"+i,df.format(fileM)+"M");

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
	
	request.setAttribute("ID");
	request.setAttribute("TypeID",new DownloadType().getValues());
	request.setAttribute("Name");
	request.setAttribute("Date");
	request.setAttribute("DownloadPath");
	request.setAttribute("ImagePath");
	request.setAttribute("Description");
	
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
		

		if(key != null) {
			sql += "SELECT ID,TypeID,Name,Date,DownloadPath,ImagePath,Description FROM Download";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bt.getResultSet(sql);

			if(rs.first()) {
				request.setAttribute("ID",rs.getString("ID"));
				request.setAttribute("TypeID",new DownloadType().getValues(),rs.getString("TypeID"));
				request.setAttribute("Name",rs.getString("Name"));
				request.setAttribute("Date",rs.getString("Date"));
				request.setAttribute("DownloadPath",rs.getString("DownloadPath"));
				request.setAttribute("ImagePath",rs.getString("ImagePath"));
				request.setAttribute("Description",rs.getString("Description"));
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

		request.setAttribute("ID"+i,rs.getString("dl.ID"));
		request.setAttribute("DownloadType"+i,rs.getString("dt.Name"));
		request.setAttribute("SongName"+i,rs.getString("dl.Name"));
		request.setAttribute("Date"+i,rs.getString("dl.Date"));

		value = rs.getString("dl.DownloadPath");
		request.setAttribute("DownloadPath"+i,value);

		DownloadFile = new File(uploadPath+value);
		if(!DownloadFile.exists())
			throw new IOException("Not File for "+uploadPath+value);

		fileBit = DownloadFile.length();
		fileK = fileBit/1024;
		fileM = fileK/1024;

		request.setAttribute("FileLength"+i,df.format(fileM)+"M");

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
