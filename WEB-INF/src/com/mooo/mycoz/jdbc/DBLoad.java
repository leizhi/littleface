package com.mooo.mycoz.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;

import com.mooo.mycoz.jdbc.DBMap;
import com.mooo.mycoz.jdbc.DBNode;

public class DBLoad extends DefaultHandler {

	private static Log log = LogFactory.getLog(DBLoad.class);

	public String subNode = null;
 	public Locator locator = null;
	public String value = null;
	public DBNode db = null;

public DBLoad() {
	super();
  	try{   
      	SAXParserFactory sf  = SAXParserFactory.newInstance();
      	SAXParser sp = sf.newSAXParser();
	     	sp.parse(new InputSource(""),this);
  	} catch(Exception e){
		if (log.isDebugEnabled()) log.debug("DBLoad Exception:" + e.getMessage());
   	    	e.printStackTrace();
  	   }
	}

public void startDocument( ) throws SAXException { 
	if (log.isDebugEnabled()) log.debug("DBLoad for startDocument");
}

public void endDocument() throws SAXException {
  	if (log.isDebugEnabled()) log.debug("DBLoad for endDocument"); 
 }

public void startElement(String uri, String localName, String qName, Attributes attrs) 
	throws SAXException {

	String attrName;
	String var = "";

	for( int i = 0; i < attrs.getLength(); i ++ ){
	attrName = attrs.getQName(i);
	var = attrs.getValue(i);

	if(qName.equalsIgnoreCase("context") ) {
		if (db==null) db = new DBNode();
		if(attrName.equalsIgnoreCase("name")) {
			db.setId(var);
			subNode = qName;
			}
	
	}

	if (subNode.equals("context") && qName.equalsIgnoreCase("jdbc")) {
		if(attrName.equalsIgnoreCase("driver"))
			db.setDriver(var);
		if(attrName.equalsIgnoreCase("url"))
			db.setUrl(var);
		if(attrName.equalsIgnoreCase("name"))
			db.setName(var);
		if(attrName.equalsIgnoreCase("password"))
			db.setPassword(var);
	  }

	} // end for
}
public void endElement(String uri, String localName, String qName){

   if (subNode!=null && qName.equals("context")) {
	DBMap.addNode(db.getId(),db);
	subNode = null;
	db = null;
    }

   if (subNode!=null && qName.equals("description")) {
	db.setDescription(value);
	value = null;
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
