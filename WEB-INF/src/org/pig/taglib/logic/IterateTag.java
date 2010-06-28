package org.pig.taglib.logic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class IterateTag extends TagSupport {

     private static Log log = LogFactory.getLog(IterateTag.class);

     private int total = 0;
     private int count = 0;
     private String property = null;

     public void setCount(int count){
            this.count = count;
         }

     public void setTotal(int total){
            this.total = total;
         }

      public void setProperty(String property){
             	this.property = property;
            }

     public int doStartTag() throws JspTagException {
	    	try{
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String iterateMax = null;

			if(total == 0 && property != null) {
				iterateMax = (String)request.getAttribute("Iterate"+property);
				if (iterateMax != null)
					total = new Integer(iterateMax).intValue();
				else
					total = 0;	
			}

			if( total > 0)
				return EVAL_BODY_INCLUDE;//count body content
			else 
				return SKIP_BODY;

	     	    } catch(Exception e) {
                 	if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
                 	return EVAL_PAGE;
               	      }
                }

      public int doAfterBody() throws JspTagException {
		try {

			count++;
			if(count < total) {
				return EVAL_BODY_AGAIN;//lookup
			} else {
				count = 0;
				return SKIP_BODY; //break
			}
		  } catch(Exception e) {
                 	if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
                 	return EVAL_PAGE;
               	      }
		}

      public int doEndTag() throws JspTagException {
     		total = 0;
     		count = 0;
     		property = null;
             return EVAL_PAGE;
           }

}
