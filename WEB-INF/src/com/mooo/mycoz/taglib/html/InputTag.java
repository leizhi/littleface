package com.mooo.mycoz.taglib.html;

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

import com.mooo.mycoz.util.I18n;

public class InputTag extends TagSupport {

      private static Log log = LogFactory.getLog(InputTag.class);

      private String type = null;
      private String name = null;
      private String id = null;
      private String styles = null;
      private String property = null;
      private String value = null;
      private String size = null;
      private String maxlength = null;
      private String onclick = null;
      private String onchange = null;
      private String onkeyup = null;

      private String returnInput = null;

      public void setType(String type){
            	this.type = type;
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

      public void setValue(String value){
             	this.value = value;
            }

      public void setSize(String size){
             	this.size = size;
            }

      public void setMaxlength(String maxlength){
             	this.maxlength = maxlength;
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

		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String conPath = request.getContextPath()+"/";
		String controller = (String)request.getAttribute("SubmitController"+property);
		String state = (String)request.getAttribute("SubmitState"+property);

		returnInput = "<input ";
		if (type != null) returnInput += "type=\""+type+"\" ";
		if (name != null) returnInput += "name=\""+name+"\" ";
		if (id != null) returnInput += "id=\""+id+"\" ";
		if (styles != null) returnInput += "class=\""+styles+"\" ";

		if (property != null) {
				tmpValue = (String)request.getAttribute(property);
				if (tmpValue != null)
					returnInput += "value=\""+I18n.getValue((String)request.getAttribute(property))+"\" ";
				else if (value != null)
					returnInput += "value=\""+I18n.getValue(value)+"\" ";
				else
					returnInput += "value=\"\" ";
		} else if (value != null) {
			returnInput += "value=\""+I18n.getValue(value)+"\" ";
		} else {
			returnInput += "value=\"\" ";
		 }

		if (size != null) returnInput += "size=\""+size+"\" ";
		if (maxlength != null) returnInput += "maxlength=\""+maxlength+"\" ";

		if (onclick != null) returnInput += "onclick=\""+onclick+"\" ";
		else if (controller != null && state != null)  returnInput += "onclick=\"document.forms[0].action='"+conPath+controller+".do?state="+state+"&FowardName='"+"+this.name"+";document.forms[0].method='post';document.forms[0].submit();\"";

		if (onchange != null) returnInput += "onchange=\""+onchange+"\" ";
		if (onkeyup != null) returnInput += "onkeyup=\""+onkeyup+"\" ";

		returnInput += "/>";

            	return EVAL_BODY_INCLUDE;

	     } catch(Exception e) {
                 if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
                 return EVAL_PAGE;
             }
          }

      public int doEndTag() throws JspTagException {
           try {

		if(returnInput != null) pageContext.getOut().write(returnInput);
               
           }catch(Exception e) {
            if (log.isDebugEnabled()) log.error(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }

}
