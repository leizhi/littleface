package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class MenuListTag extends TagSupport {

    private static Log log = LogFactory.getLog(MenuListTag.class);

     private String property = null;

     private StringBuffer writeList = null;

     public void setProperty(String property){
             	this.property = property;
            }

     public int doStartTag() throws JspTagException {
	    	try{
	    		
	    		/*
			String forward = null;
			HashMap menuListField = null;
			List boxA = null;
			HashMap rowA =null;
			String value = null;
			String key = null;

			writeList = new StringBuffer();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			if(property !=null) {
				forward = request.getContextPath()+"/"+request.getAttribute("MenuListForward"+property)+"&Key=";
				menuListField = (HashMap)request.getAttribute("MenuListField"+property);
				boxA = (List)request.getAttribute("MenuList"+property);

				writeList.append("<ul>\n");
				for(Iterator itA= boxA.iterator(); itA.hasNext();){
					rowA = (HashMap)itA.next();
					writeList.append("<li><a href=\"");
					for(Iterator itCol= menuListField.entrySet().iterator(); itCol.hasNext();){
						Map.Entry tmpCol = (Map.Entry)itCol.next();
						key = (String)tmpCol.getKey();
						value = (String)tmpCol.getValue();
						if (key.equals("Key"))
							writeList.append(forward+rowA.get(value)+"\">");
						else if (key.equals("Value"))
							writeList.append(rowA.get(value));
				   	  }
					writeList.append("</a></li>\n");
				    }
				writeList.append("</ul>\n");
				
			} // end property
                 	if (log.isDebugEnabled()) log.debug(" writeList="+writeList);
                 	*/
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
