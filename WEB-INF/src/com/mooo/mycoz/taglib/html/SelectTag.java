package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class SelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5909599868008410981L;

	private static Log log = LogFactory.getLog(SelectTag.class);

	private String name = null;
	private String id = null;
	private String styles = null;
	private String property = null;
	private String size = null;
	private String onclick = null;
	private String onchange = null;

	private StringBuffer writeSelect = null;

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public int doStartTag() throws JspTagException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		writeSelect = new StringBuffer();
		writeSelect.append("<select ");

		if (name != null)
			writeSelect.append("name=\"" + name + "\" ");
		if (id != null)
			writeSelect.append("id=\"" + id + "\" ");
		if (styles != null)
			writeSelect.append("class=\"" + styles + "\" ");
		if (size != null)
			writeSelect.append("size=\"" + size + "\" ");
		if (onclick != null)
			writeSelect.append("onclick=\"" + onclick + "\" ");
		if (onchange != null)
			writeSelect.append("onchange=\"" + onchange + "\" ");

		writeSelect.append(">");
		if (property != null) {
			Collection selectColl = null;
			
			String defaultKey = "";
			String key = "", value = "";

			defaultKey = (String) request.getAttribute(name);
			selectColl = (Collection)request.getAttribute(property);
			
			/*
			if (log.isDebugEnabled())log.debug("selectColl=" + selectColl);
			if (selectColl.contains(defaultKey)) {
				key = defaultKey;
				value = (String) selectColl.get(key);
				writeSelect.append("<option value=\"" + key + "\" >" + value
						+ "</option>");
				selectColl.remove(key);
			} else {
				key = null;
				value = (String) selectColl.get(key);
				writeSelect.append("<option value=\"" + key + "\" >" + value
						+ "</option>");
				selectColl.remove(key);
			}
			*/
			for (Iterator<?> it = selectColl.iterator(); it.hasNext();) {
				Map.Entry<?, ?> tmpMap = (Map.Entry<?, ?>) it.next();
				key = (String) tmpMap.getKey();
				value = (String) tmpMap.getValue();
				writeSelect.append("<option value=\"" + key + "\" >" + value + "</option>");
			}
		} // end property

		writeSelect.append("</select>");

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspTagException {
		try {

			if (writeSelect != null)
				pageContext.getOut().write(writeSelect.toString());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.error(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
