<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
	Map<String, String> gbar = new HashMap<String, String>();
	gbar.put("Activity","Activity.do");
	gbar.put("File","");
	
	request.setAttribute("gbar", gbar);
%>