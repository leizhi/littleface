package com.mooo.mycoz.services.html;

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

import com.mooo.mycoz.action.ActionServlet;
import com.mooo.mycoz.util.DBLoad;
import com.mooo.mycoz.util.DBMap;
import com.mooo.mycoz.util.DBNode;
import com.mooo.mycoz.util.MysqlConnection;
import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigLoad;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class Input  {

private static Log log = LogFactory.getLog(Input.class);

     private static Hashtable defaultValues = null;
     private static Hashtable inputValues = null;
     private static Hashtable onClickValues = null;

     public static void setDefault(String property,String key){
		 if (defaultValues==null) 
			defaultValues = new Hashtable();

		 if ( key != null)
		 	defaultValues.put(property,key);

	}

     public static String getDefault(String key) {

		String value = null;
		try {
			if (defaultValues==null) 
				defaultValues = new Hashtable();

			if (defaultValues.containsKey(key))
				value = (String)defaultValues.get(key);
		}catch(Exception e) {
			if (log.isDebugEnabled()) log.error("Exception:"+e.toString());
		}

		return value;
        }

     public static void setValue(String name,String value) {

		if (inputValues==null) inputValues = new Hashtable();

		if(value != null)
			inputValues.put(name,value);
		else
			inputValues.put(name,"");
        }

     public static void setValue(String name,int value) {

		if (inputValues==null) inputValues = new Hashtable();

		inputValues.put(name,Integer.toString(value));
        }

     public static String getValue(String name) {
		return (String)inputValues.get(name);
        }

     public static void setValues(String name,Hashtable value) {
		if (inputValues==null) inputValues = new Hashtable();
		inputValues.put(name,value);
        }

     public static Hashtable getValues(String name) {
		return (Hashtable)inputValues.get(name);
        }

     public static void setOnClick(String name,String url) {

		if (onClickValues==null) 
			onClickValues = new Hashtable();
		if(url != null)
			onClickValues.put(name,url);
        }

     public static String getOnClick(String key) {
		String value = null;
		try {
			if (onClickValues==null) 
				onClickValues = new Hashtable();

			if(key == null) 
				return null;
			else 
				value = (String)onClickValues.get(key);
		}catch(Exception e) {
			if (log.isDebugEnabled()) log.error("Exception ddd:"+e.toString());
		}

		return value;
        }

     public static void clean(){
		defaultValues = null;
		inputValues = null;
	}

}
