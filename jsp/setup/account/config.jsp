<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
	Map<String, String> gbar = new HashMap<String, String>();
	gbar.put("AccountElement","AccountElement.do");
	gbar.put("AccountCategory","AccountCategory.do");
	gbar.put("AccountType","AccountType.do");
	gbar.put("AccountGroup","AccountGroup.do");
	gbar.put("Account","Account.do");
	request.setAttribute("gbar", gbar);
%>