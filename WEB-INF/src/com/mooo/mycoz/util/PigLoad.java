package com.mooo.mycoz.util;

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

import javax.servlet.ServletException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;

import com.mooo.mycoz.util.PigConfigNode;
import com.mooo.mycoz.util.PigMap;
import com.mooo.mycoz.util.PigNode;

public class PigLoad extends DefaultHandler {

	private static Log log = LogFactory.getLog(PigLoad.class);

	public String subNode = null;
 	public Locator locator = null;
	public String value = null;
	public PigNode pig = null;

public PigLoad() {
	super();
  	try{   
      	SAXParserFactory sf  = SAXParserFactory.newInstance();
      	SAXParser sp = sf.newSAXParser();
	     	sp.parse(new InputSource(PigConfigNode.getMvcPath()),this);

  	} catch(Exception e){
		if (log.isDebugEnabled()) log.debug("DBLoad Exception:" + e.getMessage());
   	    	e.printStackTrace();
  	   }
	}

public void startDocument( ) throws SAXException { 
	if (log.isDebugEnabled()) log.debug("PigLoad for startDocument");
}

public void endDocument() throws SAXException {
  	if (log.isDebugEnabled()) log.debug("PigLoad for endDocument"); 
 }

public void startElement(String uri, String localName, String qName, Attributes attrs) 
	throws SAXException {

    String attrName;
    String tmp = null;
    String var = null;
    for( int i = 0; i < attrs.getLength(); i ++ ){
	attrName = attrs.getQName(i);
	var = attrs.getValue(i);

	if(qName.equalsIgnoreCase("action") ) {
		if (pig==null) { 
			pig = new PigNode(); 
			subNode = qName;
		}

		if(attrName.equalsIgnoreCase("type"))
			pig.setType(var);
		if(attrName.equalsIgnoreCase("path"))
			pig.setPath(var);
		if(attrName.equalsIgnoreCase("name"))
			pig.setDefaultState(var);
	}

	if(qName.equalsIgnoreCase("forward")) {
		if(attrName.equalsIgnoreCase("name")) {
			tmp = var;
			}
		if(attrName.equalsIgnoreCase("path")) {
			pig.addForward(tmp,var);
			//if (log.isDebugEnabled()) log.debug("name:"+tmp + " path:"+var);
			tmp = null;
			}
	}

	} // end for
}
public void endElement(String uri, String localName, String qName){

   if (subNode!=null && qName.equals("action")) {
	PigMap.addNode(pig.getPath(),pig);
	subNode = null;
	pig = null;
    }
 }
public void fatalError(SAXParseException e){
	if (log.isDebugEnabled()) log.debug("SAXParseException:"+e.getMessage()+" At line " + locator.getLineNumber() + ",column " + locator.getColumnNumber());
 }

public void characters( char[] ch, int start, int length ) 
throws SAXException {
	value = new String(ch, start, length);
}

public void ignorableWhitespace(char[] ch,int start,int length)
       throws SAXException {
//if (log.isDebugEnabled()) log.debug("space" + new String(ch, start, length));
}

public void error(SAXParseException e){
	if (log.isDebugEnabled()) log.debug("Error:"+e.getMessage()+" At line " + locator.getLineNumber() + ",column " + locator.getColumnNumber());
 }

public void warning(SAXParseException e){
	if (log.isDebugEnabled()) log.debug("Warning:"+e.getMessage()+" At line " + locator.getLineNumber() + ",column " + locator.getColumnNumber());
 }
//Store the error locator object
public void setDocumentLocator(Locator lct){
  	locator = lct;
 	}
}
