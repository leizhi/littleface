package com.mooo.mycoz.taglib.html;

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

import com.mooo.mycoz.util.I18n;

public class BoxListTag extends TagSupport {

    private static Log log = LogFactory.getLog(BoxListTag.class);

     private String property = null;

     private StringBuffer writeList = null;

     public void setProperty(String property){
             	this.property = property;
            }

     public int doStartTag() throws JspTagException {
	    	try{
			HashMap boxListField = null;
			List table = null;
			HashMap tmpRow =null;
			String value = null;
			String key = null;

			StringBuffer thead = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			writeList = new StringBuffer();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			if(property !=null) {

				boxListField = (HashMap)request.getAttribute("BoxListField"+property);

				thead.append("<thead>\n");
				thead.append("<tr>\n");
				for(Iterator itCol= boxListField.entrySet().iterator(); itCol.hasNext();){
					Map.Entry tmpCol = (Map.Entry)itCol.next();
					key = (String)tmpCol.getKey();
					thead.append("<td>"+key+"</td>\n");
				  }
				thead.append("</thead>\n");
				thead.append("</tr>\n");


				table = (List)request.getAttribute("BoxList"+property);

				tbody.append("<tbody>\n");
				for(Iterator itRow= table.iterator(); itRow.hasNext();){
					tmpRow = (HashMap)itRow.next();
					tbody.append("<tr>\n");
					for(Iterator itCol= boxListField.entrySet().iterator(); itCol.hasNext();){
						Map.Entry tmpCol = (Map.Entry)itCol.next();
						//key = (String)tmpCol.getKey();
						value = (String)tmpCol.getValue();
						tbody.append("<td>"+tmpRow.get(value)+"</td>\n");
				   	  }
					tbody.append("</tr>\n");
				    }
				tbody.append("</tbody>\n");
			} // end property

			writeList.append("<table>");
			writeList.append(thead);
			writeList.append(tbody);
			writeList.append("</table>");

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
