package com.manihot.xpc.cache;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JspFilter implements Filter {
	public static final String USER_SESSION_KEY = "UserSessionKey";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		HttpSession session = hRequest.getSession();
		String accessPath = hRequest.getContextPath();
		String url = hRequest.getRequestURI();

		Pattern p = Pattern.compile(".jsp");
		Matcher m = p.matcher(url);
		boolean isJsp = m.find();

		if (isJsp) {
			Integer userID = (Integer) session.getAttribute(USER_SESSION_KEY);
			boolean isAuthenticated = (null != userID);

			if (!isAuthenticated) {
				hResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				hResponse.setHeader("Location", accessPath + "/xpc/loginXpc");
			}

		}

		chain.doFilter(request, response);

		// request.setCharacterEncoding("UTF-8");
		// chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}