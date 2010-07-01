package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.mooo.mycoz.util.I18n;

public class WriteTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7580216280326519401L;

	private static Log log = LogFactory.getLog(WriteTag.class);

	private String property = null;
	private String value = null;

	public void setProperty(String property) {
		this.property = property;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspTagException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();

			if (property != null)
				value = I18n.getValue((String) request.getAttribute(property));
			else if (value != null)
				value = I18n.getValue(value);
			else
				value = "";

			return EVAL_BODY_INCLUDE;

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspTagException {
		try {

			if (value != null)
				pageContext.getOut().write(value);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
