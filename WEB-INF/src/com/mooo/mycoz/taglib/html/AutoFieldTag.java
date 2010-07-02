package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.mooo.mycoz.util.I18n;
import com.mooo.mycoz.util.IDGenerator;

public class AutoFieldTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7580216280326519401L;

	private static Log log = LogFactory.getLog(AutoFieldTag.class);

	private String output = null;
	private String table = null;
	private String field = null;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int doStartTag() throws JspTagException {
		try {
			//HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String value="";
			
			output="<input type=\"hidden\"";
			//output+=" name=\""+field+"\"";//one table
			output+=" name=\"id\"";//one table
			output+=" value=\""+IDGenerator.getNextID(table)+"\"";
			output+="/>";
			
			return EVAL_BODY_INCLUDE;

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspTagException {
		try {

			if (output != null)
				pageContext.getOut().write(output);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
