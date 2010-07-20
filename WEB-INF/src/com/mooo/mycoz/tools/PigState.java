package com.mooo.mycoz.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PigState {

	private static Log log = LogFactory.getLog(PigState.class);

	public HttpServletRequest httpServletRequest;
	public HttpServletResponse httpServletResponse;

	public PigState() {
		httpServletRequest = null;
		httpServletResponse = null;
	}

	public PigState(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		setHttpServletRequest(httpServletRequest);
		setHttpServletResponse(httpServletResponse);
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}
}
