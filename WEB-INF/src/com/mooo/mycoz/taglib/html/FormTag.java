package com.mooo.mycoz.taglib.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import javax.servlet.http.HttpServletRequest;

public class FormTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(FormTag.class);

	private String output = null;

	private String name = null;
	private String id = null;
	private String action = null;
	private String state = null;
	private String method = null;

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int doStartTag() throws JspTagException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			String conPath = request.getContextPath();

			output = "<form ";
			if (name != null)
				output += "name=\"" + name + "\" ";
			if (id != null)
				output += "id=\"" + id + "\" ";
			if (action != null)
				output += "action=\"" + conPath + action + "\" ";
			if (method != null)
				output += "method=\"" + method + "\" ";
			output += ">";
			/*
			  if (state != null && state.equals("")) output +=
			  "<input type=\"hidden\" id=\"state\" name=\"state\" value=\"" +
			  state + "\"/>"; else output +=
			  "<input type=\"hidden\" id=\"state\" name=\"state\" />";
			 */
			pageContext.getOut().write(output);

			return EVAL_BODY_INCLUDE;// count body content
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error(" Error in getting results" + e.toString());
			return EVAL_PAGE;
		}
	}

	public int doAfterBody() throws JspTagException {
		return SKIP_BODY; // break
		// return EVAL_BODY_AGAIN; //lookup
	}

	public int doEndTag() throws JspTagException {
		try {
			pageContext.getOut().write("</form>");
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error(" Error in getting results" + e.toString());
		}
		return EVAL_PAGE;
	}

}
