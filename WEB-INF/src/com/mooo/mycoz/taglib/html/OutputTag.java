package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.mooo.mycoz.util.I18n;

public class OutputTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8179074332133526401L;

	private static Log log = LogFactory.getLog(OutputTag.class);

	private String name = null;
	private String id = null;
	private String value = null;
	private String property = null;
	private String onclick = null;

	private String returnOutput = null;

	public OutputTag() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public int doStartTag() throws JspTagException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			String tmpValue = null;
			returnOutput = "<label ";
			if (name != null)
				returnOutput += "name=\"" + name + "\" ";
			if (id != null)
				returnOutput += "id=\"" + id + "\" ";
			if (onclick != null)
				returnOutput += "onclick=\"" + onclick + "\" ";

			if (property != null) {
				tmpValue = (String) request.getAttribute(property);
				returnOutput += ">" + I18n.getValue(tmpValue);
			} else if (value != null)
				returnOutput += ">" + I18n.getValue(value);

			returnOutput += "</label>";

			return EVAL_BODY_INCLUDE;
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspTagException {
		try {

			if (returnOutput != null)
				pageContext.getOut().write(returnOutput);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
