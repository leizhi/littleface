package com.mooo.mycoz.action.profile;

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


import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.util.ActionServlet;


import com.mooo.mycoz.util.SAXParserConf;
import com.mooo.mycoz.util.ActionMap;


public class ProfileAction  {

private static Log log = LogFactory.getLog(ProfileAction.class);

public void viewMenuStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		
		request.setAttribute("UserName",request.getParameter("UserName"));
		request.setAttribute("Password",request.getParameter("Password"));

     		} catch (Exception e) {
      			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
     		}
	}
}
