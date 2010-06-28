package org.pig.request;

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


import org.pig.util.PigConfigNode;
import org.pig.util.DBLoad;
import org.pig.util.DBNode;
import org.pig.util.DBMap;
import org.pig.util.PigLoad;
import org.pig.util.PigNode;
import org.pig.util.PigMap;
import org.pig.util.MysqlConnection;

import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

import org.pig.action.ActionServlet;

public class Input {

private static Log log = LogFactory.getLog(Input.class);

private HashMap boxListField = null;
private HashMap menuListField = null;

public Input () {
	boxListField = new HashMap();
	menuListField = new HashMap();
}
/* input select */
public void addHashtableValues(HttpServletRequest request,String property,Hashtable table) {
	addHashtableValues(request,property,table,null);
}

public void addHashtableValues(HttpServletRequest request,String property,Hashtable table,String defaultValue) {
	if (defaultValue!=null && property != null)
		request.setAttribute("SelectDeaultValue"+property,defaultValue);
	if (table != null && property != null)
		request.setAttribute("SelectHashtable"+property,table);
}

public void addHashMapValues(HttpServletRequest request,String property,HashMap hMap) {
	addHashMapValues(request,property,hMap,null);
}

public void addHashMapValues(HttpServletRequest request,String property,HashMap hMap,String defaultValue) {
	if (defaultValue!=null && property != null)
		request.setAttribute("SelectDeaultValue"+property,defaultValue);
	if (hMap != null && property != null)
		request.setAttribute("SelectHashMap"+property,hMap);
}
/* input boxlist */
public void addBoxListField(String name,String field,String key) {
		boxListField.put(key,name+"."+field);
}

public void addBoxList(HttpServletRequest request,String property,List list) {
	request.setAttribute(property,list.size());
	request.setAttribute("BoxListField"+property,boxListField);
	request.setAttribute("BoxList"+property,list);
}
/* input menulist */
public void setMenuListKey(String name,String field) {
		menuListField.put("Key",name+"."+field);
}

public void setMenuListValue(String name,String field) {
		menuListField.put("Value",name+"."+field);
}

public void addMenuList(HttpServletRequest request,String property,String controller,String state,List list) {
	if (state != null)
		request.setAttribute("MenuListForward"+property,controller+".do?state="+state);
	else
		request.setAttribute("MenuListForward"+property,controller+".do?");

	request.setAttribute("MenuListField"+property,menuListField);
	request.setAttribute("MenuList"+property,list);
}
public void addMenuList(HttpServletRequest request,String property,String controller,List list) {
	addMenuList(request,property,controller,list);
}
/* input text */
public void addValue(HttpServletRequest request,String property,String value) {
	if (value != null)
		request.setAttribute(property,value);
	else
		request.setAttribute(property,"");
}
public void addValue(HttpServletRequest request,String property,int value) {
	addValue(request,property,Integer.toString(value));
}
/* input loop */
public void addIterateValue(HttpServletRequest request,String property,String value) {
	request.setAttribute("Iterate"+property,value);
}
public void addIterateValue(HttpServletRequest request,String property,int value) {
	addValue(request,"Iterate"+property,Integer.toString(value));
}
/* input submit*/
public void addSubmitSate(HttpServletRequest request,String property,String state) {
	request.setAttribute("SubmitState"+property,state);
}
public void addSubmitController(HttpServletRequest request,String property,String controller) {
	request.setAttribute("SubmitController"+property,controller);
}
public void addSubmit(HttpServletRequest request,String property,String controller,String state) {
	addSubmitController(request,property,controller);
	addSubmitSate(request,property,state);
}
}
