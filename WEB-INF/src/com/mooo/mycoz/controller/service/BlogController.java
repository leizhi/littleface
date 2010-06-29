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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.TimeZone;
import java.util.Calendar;

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
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.Blog;
import com.mooo.mycoz.dbobj.mycozBranch.Message;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.BlogType;
import com.mooo.mycoz.dbobj.mycozShared.Country;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.util.IDGenerator;
import com.mooo.mycoz.request.Input;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PaginationComponent;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class BlogController  {

private static Log log = LogFactory.getLog(BlogController.class);

public void previewBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Input in = new Input();
		MultiDBObject myMulti = new MultiDBObject();
		myMulti.addTable("mycozShared.BlogCategory","bc");
		myMulti.setField("bc","ID",key);
		myMulti.setRetrieveField("bc","ID");
		myMulti.setRetrieveField("bc","Name");
		List category = myMulti.searchAndRetrieveList();
		in.setMenuListKey("bc","ID");
		in.setMenuListValue("bc","Name");
		in.addMenuList(request,"Category","Blog","listBlog",category);

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void listStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = null;
		Input in = new Input();
		MultiDBObject myMulti = null;

		ArrayList alType = IDGenerator.getBlogTypes();
		HashMap rowRecord = null;
		int recordMax=0;
		for(int i=0;i<alType.size();i++){
			var = alType.get(i).toString();
			myMulti = new MultiDBObject();
			myMulti.addTable("mycozBranch.Blog","bg");
			//myMulti.addTable("mycozBranch.Message","msg");
			myMulti.addTable("mycozBranch.User","user");
			myMulti.addTable("mycozShared.BlogCategory","bc");

			myMulti.setForeignKey("bg","CategoryID","bc","ID");
			myMulti.setForeignKey("bg","UserID","user","ID");

			myMulti.setRetrieveField("bg","ID");
			myMulti.setRetrieveField("bg","Title");
			myMulti.setRetrieveField("user","Name");
			myMulti.setRetrieveField("bg","Date");
			myMulti.setRetrieveField("bc","Name");
			myMulti.setRetrieveField("bg","LastDate");

			myMulti.setField("bc","ID",var);

			List subCategory = myMulti.searchAndRetrieveList();

			for(Iterator iterator= subCategory.iterator(); iterator.hasNext();){

				rowRecord = (HashMap)iterator.next();
				in.addValue(request,"ID"+recordMax,rowRecord.get("bg.ID").toString());
				in.addValue(request,"Title"+recordMax,rowRecord.get("bg.Title").toString());
				in.addValue(request,"Owner"+recordMax,rowRecord.get("user.Name").toString());
				in.addValue(request,"Date"+recordMax,rowRecord.get("bg.Date").toString());
				in.addValue(request,"Type"+recordMax,rowRecord.get("bc.Name").toString());
				in.addValue(request,"Popularity"+recordMax,IDGenerator.getBlogMessages(rowRecord.get("bg.ID").toString()));
				in.addValue(request,"LastDate"+recordMax,rowRecord.get("bg.LastDate").toString());

				recordMax++;
		   	}
		}

		in.addIterateValue(request,"ListBlog",recordMax+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	}

public void promptAddBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String tmp = null;
		Input in = new Input();
		HttpSession session = request.getSession(true);

      		if (log.isDebugEnabled()) log.debug("Owner=" + session.getAttribute(request.getSession().getId()));

		in.addValue(request,"Title",request.getParameter("Title"));

		tmp = (session.getAttribute(request.getSession().getId())).toString();
		in.addValue(request,"Owner",tmp);
		tmp = request.getParameter("Date");

        	TimeZone tz = TimeZone.getDefault();
        	Calendar now = Calendar.getInstance(tz);
		String nowDate = now.get(Calendar.YEAR)+"-"+ (now.get(Calendar.MONTH)+1) +"-"+now.get(Calendar.DAY_OF_MONTH);

		if (tmp == null) tmp = nowDate;
		in.addValue(request,"Date",tmp);

		in.addHashMapValues(request,"TypeID",IDGenerator.getBlogHashMapTypes(),request.getParameter("TypeID"));
		in.addValue(request,"Description",request.getParameter("Description"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String tmp = null;
		MultiDBObject myMutil = new MultiDBObject();
		myMutil.setChangeField("ID",IDGenerator.getNextID("Blog"));

		HttpSession session = request.getSession(true);
		tmp = (session.getAttribute(request.getSession().getId())).toString();
		myMutil.setChangeField("UserID",new User().getID(tmp));

		myMutil.setChangeField("Date",request.getParameter("Date"));
		myMutil.setChangeField("CategoryID",request.getParameter("TypeID"));
		myMutil.setChangeField("Title",request.getParameter("Title"));
		myMutil.setChangeField("Description",request.getParameter("Description"));
		myMutil.add("Blog");

		response.sendRedirect(request.getContextPath()+"/Blog.do?state=list");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Error.do");
     		}
	}

public void promptUpdateBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Input in = new Input();

		MultiDBObject myMulti = new MultiDBObject();
		myMulti.addTable("mycozBranch.Blog","bg");
		myMulti.addTable("mycozBranch.User","user");
		myMulti.addTable("mycozShared.BlogCategory","bc");

		myMulti.setForeignKey("bg","CategoryID","bc","ID");
		myMulti.setForeignKey("bg","UserID","user","ID");

		myMulti.setRetrieveField("bg","ID");
		myMulti.setRetrieveField("bg","Title");
		myMulti.setRetrieveField("user","Name");
		myMulti.setRetrieveField("bg","Date");
		myMulti.setRetrieveField("bc","ID");
		myMulti.setRetrieveField("bg","LastDate");
		myMulti.setRetrieveField("bg","Description");

		myMulti.setField("bg","ID",key);

		List blog = myMulti.searchAndRetrieveList();
		HashMap rowRecord = null;

		for(Iterator iterator= blog.iterator(); iterator.hasNext();){

			rowRecord = (HashMap)iterator.next();
			in.addValue(request,"ID",rowRecord.get("bg.ID").toString());
			in.addValue(request,"Title",rowRecord.get("bg.Title").toString());
			in.addValue(request,"Owner",rowRecord.get("user.Name").toString());
			in.addValue(request,"Date",rowRecord.get("bg.Date").toString());
			in.addHashMapValues(request,"TypeID",IDGenerator.getBlogHashMapTypes(),rowRecord.get("bc.ID").toString());
			in.addValue(request,"Description",rowRecord.get("bg.Description").toString());
		   }

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String tmp = null;
		MultiDBObject myMutil = new MultiDBObject();
if (log.isDebugEnabled()) log.debug("Date=" + request.getParameter("Date"));
		myMutil.setChangeField("Date",request.getParameter("Date"));
		myMutil.setChangeField("CategoryID",request.getParameter("TypeID"));
		myMutil.setChangeField("Title",request.getParameter("Title"));
		myMutil.setChangeField("Description",request.getParameter("Description"));

		myMutil.setField("ID",request.getParameter("ID"));
if (log.isDebugEnabled()) log.debug("ID=" + request.getParameter("ID"));
		HttpSession session = request.getSession(true);
		tmp = (session.getAttribute(request.getSession().getId())).toString();
if (log.isDebugEnabled()) log.debug("tmp=" + request.getParameter("Owner"));
		if (request.getParameter("Owner").equals(tmp))
			myMutil.update("Blog");

		response.sendRedirect(request.getContextPath()+"/Blog.do?state=list");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.error("Exception Load error of: " + e.getMessage());
     		}
	}

public void deleteBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");

		Message msg = new Message();
		Blog bg = new Blog();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "DELETE FROM Message";
			sql += " WHERE BlogID = " + key;
			msg.execute(sql);
		    }
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);

		sql += "DELETE FROM Blog";
		sql += " WHERE ID = " + key;
		bg.execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);

		response.sendRedirect(request.getContextPath()+"/BlogType.do?state=listBlogType");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void joinBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String sql = null;
		String key = request.getParameter("Key");
if (log.isDebugEnabled()) log.debug("key: " + key);
		Input in = new Input();
		in.addValue(request,"BlogID",key);

		Blog blog = new Blog();
		ResultSet rsBlog = null;
		sql = "SELECT bl.ID AS BlogID,user.Name AS BlogUser,bt.Name AS BlogType, bl.BlogDate AS BlogDate,bl.Title AS BlogTitle,bl.Description AS BlogDescription FROM Blog AS bl,User AS user,mycozShared.BlogType AS bt WHERE bl.UserID=user.ID AND bl.BlogTypeID=bt.ID AND bl.ID="+key;
		rsBlog = blog.getResultSet(sql);
		
		if(rsBlog.first()) {
			//in.addValue(request,"BlogID",rsBlog.getString("BlogID"));
			in.addValue(request,"BlogUser",rsBlog.getString("BlogUser"));
			in.addValue(request,"BlogType",rsBlog.getString("BlogType"));
			in.addValue(request,"BlogDate",rsBlog.getString("BlogDate"));
			in.addValue(request,"BlogTitle",rsBlog.getString("BlogTitle"));
			in.addValue(request,"BlogDescription",rsBlog.getString("BlogDescription"));
			}

		Message msg = new Message();
		ResultSet rsMsg = null;
		sql = "SELECT msg.ID AS MsgID,user.Name AS MsgUser, msg.MessageDate AS MessageDate,msg.Title AS MsgTitle,msg.Description AS MsgDescription FROM Message AS msg,User AS user WHERE msg.UserID=user.ID AND msg.BlogID="+key;

		rsMsg = msg.getResultSet(sql);
		int i = 0;
		while(rsMsg.next()) {

			//in.addValue(request,"MsgID"+i,rsMsg.getString("MsgID"));
			in.addValue(request,"MsgTitle"+i,rsMsg.getString("MsgTitle"));
			in.addValue(request,"MsgUser"+i,rsMsg.getString("MsgUser"));
			in.addValue(request,"MessageDate"+i,rsMsg.getString("MessageDate"));
			in.addValue(request,"MsgDescription"+i,rsMsg.getString("MsgDescription"));

			i++;
               	   }

		in.addIterateValue(request,"MsgList",i+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddMessageStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		int id=0;
		int userID=0;
		int blogTypeID=0;
		String nowDate = null;
		String var = null;
		String sql = null;

		HttpSession session = request.getSession(true);
		var = (java.lang.String )session.getAttribute("ID");
		if ( var!=null && !var.equals("Guest") && !var.equals("")) 
			userID = new User().getID(var);
		else
			response.sendRedirect(request.getContextPath()+"/User.do?state=promptLogin");

if (log.isDebugEnabled()) log.debug("User: " + var);

             	TimeZone tz = TimeZone.getDefault();
             	Calendar now = Calendar.getInstance(tz);
		nowDate = now.get(Calendar.YEAR)+"-"+ (now.get(Calendar.MONTH)+1) +"-"+now.get(Calendar.DAY_OF_MONTH);
		Message msg = new Message();
		id = msg.getNextID();

		sql = "INSERT INTO Message(ID,UserID,BlogID,MessageDate,Title,Description) VALUES(";
		sql += id+",";
		sql += userID+",";
		sql += request.getParameter("BlogID")+",";
		sql += "'"+nowDate+"',";
		sql += "'"+request.getParameter("AddTitle")+"',";
		sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		msg.execute(sql);
		response.sendRedirect(request.getContextPath()+"/Blog.do?state=listMessage&Key="+request.getParameter("BlogID"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Blog.do?state=listMessage&Key="+request.getParameter("BlogID"));
     		}
	}

public void promptUpdateMessageStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();
		in.addValue(request,"UserName",request.getParameter("UserName"));
		in.addValue(request,"Password",request.getParameter("Password"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateMessageStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();
		in.addValue(request,"UserName",request.getParameter("UserName"));
		in.addValue(request,"Password",request.getParameter("Password"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void deleteMessageStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();
		in.addValue(request,"UserName",request.getParameter("UserName"));
		in.addValue(request,"Password",request.getParameter("Password"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void previewAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String var = "";
		Input in = new Input();
		// list for this
		in.addValue(request,"Title",request.getParameter("Title"));
		in.addValue(request,"User",request.getParameter("User"));
		in.addValue(request,"BlogType",request.getParameter("BlogType"));
		in.addValue(request,"BlogDate",request.getParameter("BlogDate"));

		Message mg = new Message();
		ResultSet mgRs = null;

		Blog blog = new Blog();
		ResultSet rs = null;

		String sql = "SELECT bg.ID AS BlogID,user.Name AS User,bt.Name AS BlogType,bg.BlogDate AS BlogDate,bg.Title AS Title,bg.Description AS Description FROM Blog AS bg,User AS user,mycozShared.BlogType AS bt WHERE bg.ID > 0 AND bg.UserID=user.ID AND bg.BlogTypeID=bt.ID";
		var = request.getParameter("Title");
		if(var != null && !var.equals(""))
		sql += " AND Title LIKE '%" + var + "%'";

		var = request.getParameter("User");
		if(var != null && !var.equals(""))
		sql += " AND user.Name LIKE '%" + var + "%'";

		var = request.getParameter("BlogType");
		if(var != null && !var.equals(""))
		sql += " AND bt.Name LIKE '%" + var + "%'";

		var = request.getParameter("BlogDate");
		if(var != null && !var.equals(""))
		sql += " AND BlogDate LIKE '%" + var + "%'";

		rs = blog.getResultSet(sql);
		int i = 0;
		while(rs.next()) {
			sql = "SELECT COUNT(ID) AS MgCount FROM Message WHERE BlogID="+rs.getInt("BlogID");
			mgRs = mg.getResultSet(sql);
			if(mgRs.first())
			in.addValue(request,"Message"+i,mgRs.getString("MgCount"));

			in.addValue(request,"ID"+i,rs.getString("BlogID"));
			in.addValue(request,"Title"+i,rs.getString("Title"));
			in.addValue(request,"User"+i,rs.getString("User"));
			in.addValue(request,"BlogType"+i,rs.getString("BlogType"));
			in.addValue(request,"BlogDate"+i,rs.getString("BlogDate"));
			in.addValue(request,"Description"+i,rs.getString("Description"));

			i++;
               	   }

		in.addIterateValue(request,"List",i+"");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void promptAddAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		Input in = new Input();
		in.addValue(request,"Title",request.getParameter("Title"));
		in.addValue(request,"BlogType",request.getParameter("BlogType"));
		in.addValue(request,"User",request.getParameter("User"));
		in.addValue(request,"BlogDate",request.getParameter("BlogDate"));
		in.addValue(request,"Description",request.getParameter("Description"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processAddAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		int id=0;
		int userID=0;
		int blogTypeID=0;
		String var = null;

		var = request.getParameter("User");
		if ( var!=null && !var.equals("") ) 
			userID = new User().getID(var);
if (log.isDebugEnabled()) log.debug("User: " + var);
		var = request.getParameter("BlogType");
		if ( var!=null && !var.equals("") ) 
			blogTypeID = new BlogType().getID(var);
if (log.isDebugEnabled()) log.debug("BlogType: " + var);
		Blog blog = new Blog();
		id = blog.getNextID();

		String sql = "INSERT INTO Blog(ID,UserID,BlogTypeID,BlogDate,Title,Description) VALUES(";
		sql += id+",";
		sql += userID+",";
		sql += blogTypeID+",";
		sql += "'"+request.getParameter("BlogDate")+"',";
		sql += "'"+request.getParameter("Title")+"',";
		sql += "'"+request.getParameter("Description")+"')";

if (log.isDebugEnabled()) log.debug("SQL: " + sql);
		blog.execute(sql);
		response.sendRedirect(request.getContextPath()+"/Blog.do?state=previewAdminBlog");

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		response.sendRedirect(request.getContextPath()+"/Error.do");
     		}
	}

public void promptUpdateAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");
		Input in = new Input();
		User user = new User();
		BlogType bt = new BlogType();

		Blog bg = new Blog();
		ResultSet rs = null;
		String sql = "";

		if(key != null) {
			sql += "SELECT ID,Title,BlogTypeID,UserID,BlogDate,Description FROM Blog";
			sql += " WHERE ID = " + key + " LIMIT 1";
			rs = bg.getResultSet(sql);

			if(rs.first()) {
				in.addValue(request,"ID",rs.getString("ID"));
				in.addValue(request,"Title",rs.getString("Title"));
				in.addValue(request,"BlogType",bt.getName(rs.getInt("BlogTypeID")));
				in.addValue(request,"User",user.getName(rs.getInt("UserID")));
				in.addValue(request,"BlogDate",rs.getString("BlogDate"));
				in.addValue(request,"Description",rs.getString("Description"));
               		}

		}
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		} catch (SQLException se) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + se.getMessage()); 
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}

public void processUpdateAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");

		User user = new User();
		BlogType bt = new BlogType();

		Blog bg = new Blog();
		ResultSet rs = null;

		String sql = "UPDATE Blog SET ";
		sql += " Title = '" + request.getParameter("Title") + "',";
		sql += " UserID=" + user.getID(request.getParameter("User")) + ",";
		sql += " BlogTypeID=" + bt.getID(request.getParameter("BlogType")) + ",";
		sql += " BlogDate = '" + request.getParameter("BlogDate") + "',";
		sql += " Description = '" + request.getParameter("Description") + "'";
		sql += " WHERE ID = " + request.getParameter("ID");

		bg.execute(sql);

		if (log.isDebugEnabled()) log.debug("SQL:"+sql);
		response.sendRedirect(request.getContextPath()+"/Blog.do?state=previewAdminBlog");
		} catch (SQLException se) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + se.getMessage());
			response.sendRedirect(request.getContextPath()+"/Blog.do?state=promptUpdateAdminBlog");
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
			response.sendRedirect(request.getContextPath()+"/Blog.do?state=promptUpdateAdminBlog");
     		}
	}

public void deleteAdminBlogStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String key = request.getParameter("Key");

		Message msg = new Message();
		Blog bg = new Blog();
		ResultSet rs = null;
		String sql = null;

		if(key != null) {
			sql = "DELETE FROM Message";
			sql += " WHERE BlogID = " + key;
			msg.execute(sql);
		    }
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);

		sql = "DELETE FROM Blog";
		sql += " WHERE ID = " + key;
		bg.execute(sql);
		if (log.isDebugEnabled()) log.debug("SQL:"+sql);

		} catch (SQLException se) {
      			if (log.isDebugEnabled()) log.debug("SQLException: " + se.getMessage());
     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception: " + e.getMessage());
     		} finally {
			response.sendRedirect(request.getContextPath()+"/Blog.do?state=previewAdminBlog");
		}
	}
}
