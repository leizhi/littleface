package com.mooo.mycoz.taglib.plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozShared.Language;
import com.mooo.mycoz.dbobj.util.IDGenerator;
import com.mooo.mycoz.util.I18n;

public class BoxATag extends TagSupport {

     private static Log log = LogFactory.getLog(BoxATag.class);

     private String id = null;
     private String name = null;
     private String styles = null;

     private StringBuffer writeList = null;

     public void setId(String id){
             	this.id = id;
            }

     public void setName(String name){
             	this.name = name;
            }

     public void setStyles(String styles){
             	this.styles = styles;
            }

     public int doStartTag() throws JspTagException {
	    	try{
			String forward = null;
			HashMap menuListField = new HashMap();
			List boxA = null;
			HashMap rowA =null;
			String value = null;
			String key = null;

			writeList = new StringBuffer();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

			MultiDBObject myMutil = new MultiDBObject();
			myMutil.addTable("mycozShared.BlogCategory","bc");
			myMutil.setField("bc","LayerID",1);
			myMutil.setRetrieveField("bc","ID");
			myMutil.setRetrieveField("bc","Name");
			boxA = myMutil.searchAndRetrieveList();

			menuListField.put("Key","bc.ID");
			menuListField.put("Value","bc.Name");

			forward = request.getContextPath()+"/"+"Blog"+".do?state="+"listBlog"+"&Key=";

			writeList.append("<div ");
			if (id != null) writeList.append("id=\""+id+"\" ");
			if (name != null) writeList.append("name=\""+name+"\" ");
			if (styles != null) writeList.append("class=\""+styles+"\" ");
			writeList.append(">\n");
			for(Iterator itA= boxA.iterator(); itA.hasNext();){
				rowA = (HashMap)itA.next();
				writeList.append("<a href=\"");
				for(Iterator itCol= menuListField.entrySet().iterator(); itCol.hasNext();){
					Map.Entry tmpCol = (Map.Entry)itCol.next();
					key = (String)tmpCol.getKey();
					value = (String)tmpCol.getValue();
					if (key.equals("Key"))
						writeList.append(forward+rowA.get(value)+"\">");
					else if (key.equals("Value"))
						writeList.append(rowA.get(value));
			   	  }
				writeList.append("</a>\n");
			    }
				writeList.append("</div>\n");

			return EVAL_BODY_INCLUDE;

	     	    } catch(Exception e) {
                 	if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
                 	return EVAL_PAGE;
               	      }
                }

      public int doEndTag() throws JspTagException {
           try {

		if(writeList != null) pageContext.getOut().write(writeList.toString());

           }catch(Exception e) {
            	if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }
}
