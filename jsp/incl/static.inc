<%@ page import="com.mooo.mycoz.response.SessionCounter" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ taglib uri="/WEB-INF/tld/nick-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/nick-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/nick-plugin.tld" prefix="plugin" %>
<jsp:useBean id="CheckLogin" class="com.mooo.mycoz.response.SessionCounter" scope="session" />
<html:input type="hidden" id="ContextPath" name="ContextPath" value="<%=request.getContextPath()%>"/>