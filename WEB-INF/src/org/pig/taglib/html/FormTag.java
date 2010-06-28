package org.pig.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

public class FormTag extends TagSupport {

     private static Log log = LogFactory.getLog(FormTag.class);

     private HttpServletRequest request = null;
     private String returnForm = null;

     private String name = null;
     private String id = null;
     private String action = null;
     private String method = null;

     public FormTag(){

          }

      public void setAction(String action){
            this.action = action;
         }

      public void setName(String name){
             this.name = name;
            }

      public void setMethod(String method){
             this.method = method;
            }

      public int doStartTag() throws JspTagException {
	    	try{
			request = (HttpServletRequest)pageContext.getRequest();
			String var = request.getRequestURI();

			var=var.substring(1,var.indexOf("/",1));
			returnForm = "<form ";
			if (name != null) returnForm += "name=\""+name+"\" ";
			if (id != null) returnForm += "id=\""+id+"\" ";
			if (action != null) returnForm += "action=\"/"+var+action+"\" ";
			if (method != null) returnForm += "method=\""+method+"\" ";
			returnForm += ">";
			//returnFrom += "</from>";
			pageContext.getOut().write(returnForm);

            		return EVAL_BODY_INCLUDE;//count body content
	     	    } catch(Exception e) {
                 	if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
                 	return EVAL_PAGE;
               	      }
                }

      public int doAfterBody() throws JspTagException {
		return SKIP_BODY; //break
		//return EVAL_BODY_AGAIN; //lookup
		}

      public int doEndTag() throws JspTagException {
           try {

		pageContext.getOut().write("</form>");
               
           }catch(Exception e) {
            if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }

}
