package com.mooo.mycoz.util;

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

	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getInitParameter(USER_SESSION_KEY);
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		try {

			if (request instanceof HttpServletRequest) {

				HttpServletRequest hRequest = (HttpServletRequest) request;
				HttpServletResponse hResponse = (HttpServletResponse) response;

				HttpSession session = hRequest.getSession();
				String contextPath = hRequest.getContextPath();
				String accessPath = hRequest.getServletPath();

				Pattern p = Pattern.compile("\\.jsp");
				Matcher m = p.matcher(accessPath);
				boolean isJsp = m.find();

				Integer userID = (Integer) session.getAttribute(USER_SESSION_KEY);
				boolean isAuthenticated = (null != userID);

				System.out.println("--------filter start-------------");
				System.out.println("filter contextPath:" + contextPath);
				System.out.println("filter accessPath:" + accessPath);
				System.out.println("filter execPath:" + ActionUtil.execPath(accessPath));
				System.out.println("filter isJsp:" + isJsp);
				System.out.println("--------filter end-------------");

				if (!isAuthenticated) {
					hResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					if (isJsp) {
						hResponse.setHeader("Location", contextPath + "/Login.do");
					}
				}

			}
			
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
	}
}