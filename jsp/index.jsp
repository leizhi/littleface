<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- jsp:forward page="/Index.do?state=promptIndex" / --%>
<% response.sendRedirect(request.getContextPath()+"/Index.do"); %>
