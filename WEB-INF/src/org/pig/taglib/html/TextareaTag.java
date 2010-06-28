package org.pig.taglib.html;

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

import org.pig.util.I18n;

public class TextareaTag extends TagSupport {

      private static Log log = LogFactory.getLog(TextareaTag.class);

      private String cols = null;
      private String rows = null;
      private String name = null;
      private String id = null;
      private String styles = null;
      private String property = null;
      private String onclick = null;
      private String onchange = null;
      private String onkeyup = null;

      private StringBuffer writeTextarea = null;

      public void setCols(String cols){
             	this.cols = cols;
            }

      public void setRows(String rows){
             	this.rows = rows;
            }

      public void setName(String name){
             	this.name = name;
            }

      public void setId(String id){
             	this.id = id;
            }

      public void setStyles(String styles){
             	this.styles = styles;
            }

      public void setProperty(String property){
             	this.property = property;
            }

      public void setOnclick(String onclick){
            	this.onclick = onclick;
            }

      public void setOnchange(String onchange){
            	this.onchange = onchange;
            }

      public void setOnkeyup(String onkeyup){
            	this.onkeyup = onkeyup;
            }

      public int doStartTag() throws JspTagException {
	  try{
		String tmpValue = null;
		writeTextarea = new StringBuffer();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		//String conPath = request.getContextPath()+"/";
		//String controller = (String)request.getAttribute("SubmitController"+property);
		//String state = (String)request.getAttribute("SubmitState"+property);

		writeTextarea.append("<textarea ");
                 if (log.isDebugEnabled()) log.debug("writeTextarea="+writeTextarea);
		if (cols != null) writeTextarea.append("cols=\""+cols+"\" ");
		if (rows != null) writeTextarea.append("rows=\""+rows+"\" ");
		if (name != null) writeTextarea.append("name=\""+name+"\" ");
		if (id != null) writeTextarea.append("id=\""+id+"\" ");
		if (styles != null) writeTextarea.append("class=\""+styles+"\" ");
		if (onclick != null) writeTextarea.append("onclick=\""+onclick+"\" ");
		//else if (controller != null && state != null)  writeTextarea.append("onclick=\"document.forms[0].action='"+conPath+controller+".do?state="+state+"&FowardName='"+"+this.name"+";document.forms[0].method='post';document.forms[0].submit();\"";

		if (onchange != null) writeTextarea.append("onchange=\""+onchange+"\" ");
		if (onkeyup != null) writeTextarea.append("onkeyup=\""+onkeyup+"\" ");
		writeTextarea.append(">\n");

		if (property != null) {
				tmpValue = (String)request.getAttribute(property);
				if (tmpValue != null)
					writeTextarea.append(I18n.getValue(tmpValue));
		}

 		writeTextarea.append("</textarea> \n");

                 if (log.isDebugEnabled()) log.debug("writeTextarea="+writeTextarea);
            	return EVAL_BODY_INCLUDE;

	     } catch(Exception e) {
                 if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
                 return EVAL_PAGE;
             }
          }

      public int doEndTag() throws JspTagException {
           try {

		if(writeTextarea != null) pageContext.getOut().write(writeTextarea.toString());
               
           }catch(Exception e) {
            if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }

}
