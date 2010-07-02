package com.mooo.mycoz.taglib.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.util.I18n;

public class SubmitTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(SubmitTag.class);

	private String name = null;
	private String id = null;
	private String styles = null;
	private String action = null;
	private String state = null;
	private String value = null;
	private String size = null;
	private String maxlength = null;
	private String onclick = null;
	private String onchange = null;
	private String onkeyup = null;

	private String returnInput = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public int doStartTag() throws JspTagException {
		try {

			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String conPath = request.getContextPath() + "/";

			returnInput = "<input type=\"submit\" ";
			if (name != null)
				returnInput += "name=\"" + name + "\" ";
			if (id != null)
				returnInput += "id=\"" + id + "\" ";
			if (styles != null)
				returnInput += "class=\"" + styles + "\" ";

			if (value != null) {
				returnInput += "value=\"" + I18n.getValue(value) + "\" ";
			} else {
				returnInput += "value=\"\" ";
			}

			if (size != null)
				returnInput += "size=\"" + size + "\" ";
			if (maxlength != null)
				returnInput += "maxlength=\"" + maxlength + "\" ";

			if (onclick != null)
				returnInput += "onclick=\"" + onclick + "\" ";
			else if (state != null) {
				if (action != null)
					returnInput += "onclick=\"document.forms[0].action='"
							+ conPath
							+ action
							+ ".do?state="
							+ state
							+ "';"
							+ "document.forms[0].method='post';document.forms[0].submit();\"";
				else
					returnInput += "onclick=\"document.forms[0].action=document.forms[0].action+'?state="
						+ state
						+ "';"
						+ "document.forms[0].method='post';document.forms[0].submit();\"";
			}

			if (onchange != null)
				returnInput += "onchange=\"" + onchange + "\" ";
			if (onkeyup != null)
				returnInput += "onkeyup=\"" + onkeyup + "\" ";

			returnInput += "/>";

			return EVAL_BODY_INCLUDE;

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(" Error in getting results" + e.toString());
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspTagException {
		try {

			if (returnInput != null)
				pageContext.getOut().write(returnInput);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.error(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
