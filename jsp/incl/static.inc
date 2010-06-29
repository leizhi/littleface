<%@ page import="com.mooo.mycoz.response.SessionCounter" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ taglib uri="/WEB-INF/tld/nick-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/nick-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/nick-plugin.tld" prefix="plugin" %>
<jsp:useBean id="CheckLogin" class="com.mooo.mycoz.response.SessionCounter" scope="session" />
<%--
<jsp:useBean id="taxbean" scope="page" class="com.mooo.mycoz.services.modle.TagRate"/>
<jsp:setProperty name="taxbean" property="*" />
<jsp:setProperty name="taxbean" property="product" value="fire"/>
<jsp:setProperty name="taxbean" property="rate" value="1.0"/>
<jsp:getProperty name="taxbean" property="product"/>
<jsp:getProperty name="taxbean" property="rate"/>
--%>
<html:input type="hidden" id="ContextPath" name="ContextPath" value="<%=request.getContextPath()%>"/>
