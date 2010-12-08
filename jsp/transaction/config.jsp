<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap "%>

<%
	Map<String, String> gbar = new LinkedHashMap<String, String>();
	//gbar.put("User","User.do");
	//gbar.put("UserGroup","UserGroup.do");
	//gbar.put("GroupMember","GroupMember.do");
	//gbar.put("AuthGroup","AuthGroup.do");

	request.setAttribute("gbar", gbar);
%>