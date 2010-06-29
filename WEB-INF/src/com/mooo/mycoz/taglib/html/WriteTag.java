package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
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

import com.mooo.mycoz.util.I18n;

public class WriteTag extends TagSupport {

      private static Log log = LogFactory.getLog(WriteTag.class);

      private String property = null;
      private String value = null;

      public void setProperty(String property){
             	this.property = property;
            }

      public void setValue(String value){
             	this.value = value;
            }

      public int doStartTag() throws JspTagException {
	  try{
		int total = 0;
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

		if (property != null) 
			value = I18n.getValue((String)request.getAttribute(property));
		else if (value != null)
			value = I18n.getValue(value);
		else 
			value = "";

            	return EVAL_BODY_INCLUDE;

	     } catch(Exception e) {
                 if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
                 return EVAL_PAGE;
             }
          }

      public int doEndTag() throws JspTagException {
           try {

		if(value != null) pageContext.getOut().write(value);
               
           }catch(Exception e) {
            if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }

}
