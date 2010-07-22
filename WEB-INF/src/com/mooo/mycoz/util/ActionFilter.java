package com.mooo.mycoz.util;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;
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

public class ActionFilter implements Filter {

	public static final String USER_SESSION_KEY = "UserSessionKey";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {

			HttpServletRequest hRequest = (HttpServletRequest) request;
			HttpServletResponse hResponse = (HttpServletResponse) response;

			HttpSession session = hRequest.getSession();
			// String accessPath = hRequest.getContextPath();
			String url = hRequest.getRequestURI();

			Pattern p = Pattern.compile(".jsp");
			Matcher m = p.matcher(url);
			boolean isJsp = m.find();

			if (isJsp) {
				Integer userID = (Integer) session
						.getAttribute(USER_SESSION_KEY);

				boolean isAuthenticated = (null != userID);

				if (!isAuthenticated) {
					hResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					// hResponse.setHeader("Location", accessPath + "/login");
				}

			}
/*
			String queryString = hRequest.getQueryString();
			if (queryString != null
					&& (queryString.indexOf("lc=") >= 0 || queryString
							.indexOf("locale=") >= 0)) {
				String locale = request.getParameter("locale");
				if (locale == null) {
					locale = request.getParameter("lc");
				}
				
				if (locale != null && locale.length() > 0) {
					final Locale crtLocale = getloacle(locale, Locale.CHINA);
					request.setAttribute(
							"javax.servlet.jsp.jstl.fmt.locale.request",
							crtLocale); // 给jstl用
					request.setAttribute("org.apache.struts.action.LOCALE",
							crtLocale); // 给Struts 用
					chain.doFilter(
									new javax.servlet.http.HttpServletRequestWrapper(hRequest) {
										public Locale getLocale() {
											return crtLocale;
										}
									}, response);
					return;
				}
			}
			*/
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getInitParameter(USER_SESSION_KEY);
	}
	
	private static Locale getloacle(String lstr, Locale defaultLocale) {
		Locale locale = defaultLocale; // Locale.getDefault();
		if (lstr == null || lstr.length() < 1) {
			return locale;
		}
		try {
			StringTokenizer localeTokens = new StringTokenizer(lstr, "_");
			String lang = null;
			String country = null;
			if (localeTokens.hasMoreTokens()) {
				lang = localeTokens.nextToken();
			}
			if (localeTokens.hasMoreTokens()) {
				country = localeTokens.nextToken();
			}
			locale = new Locale(lang, country);
			Locale crtls[] = Locale.getAvailableLocales();
			for (int i = 0; i < crtls.length; i++) {
				if (crtls[i].equals(locale)) {
					return crtls[i];
				}
			}
		} catch (Throwable t) {
		}
		return locale;
	}
}
