package org.pig.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.pig.util.I18n;

public class MessageTag extends TagSupport {

     private static Log log = LogFactory.getLog(MessageTag.class);

     private String key = null;
     private String value = null;

     public MessageTag(){

          }

      public void setKey(String key){
             this.key = key;
            }

      public int doStartTag() throws JspTagException {

		value = I18n.getValue(key);

            return EVAL_BODY_INCLUDE;
              }

      public int doEndTag() throws JspTagException {
           try {

		if(value != null)
			pageContext.getOut().write(value);
		else 
			pageContext.getOut().write(key);
               
           }catch(Exception e) {
            if (log.isDebugEnabled()) log.debug(" Error in getting results"+e.toString());
               }
             return EVAL_PAGE;
           }

}
