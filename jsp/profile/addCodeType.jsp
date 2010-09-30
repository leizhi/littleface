<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="MessageBundle">

<html>
<head>
<title><fmt:message key="AddCodeType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
<link type="text/css" rel="stylesheet" href="skin/office/default/presentation.css" />
<link type="text/css" rel="stylesheet" href="skin/office/default/dhtmlgoodies_calendar.css?random=20100901" media="screen" />
<script type="text/javascript" src="jsp/js/util.js"></script>
<script type="text/javascript" src="jsp/js/dhtmlgoodies_calendar.js?random=20101018"></script>
	<style type="text/css">
	body{
		/*
		You can remove these four options 
		
		*/
		background-repeat:no-repeat;
		font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;
		margin:0px;
		

	}
	#ad{
		padding-top:220px;
		padding-left:10px;
	}
	</style>
</head>

<body>
<c:url value="/CodeType.do" var="linearCode"/>

<c:url value="/CodeType.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<form method="post" action="${processAdd}">

<div class="command">
<input type="submit" value="<fmt:message key="Black"/>" onclick="docommit('${linearCode}')">
<input type="submit" value="<fmt:message key="OK"/>">
</div>

<div>
<fmt:message key="AddCodeType"/>
<div id="formresponse">
<input type="hidden" id="codeType.id" name="codeType.id" value="${codeType.id}"/>

<table align="center" style="font-size:0.9em;">

<tr>
<td><fmt:message key="Category" /></td>
<td>
<select id="codeType.category" name="codeType.category">
	<c:forEach var="items" items="${codeCategory}" varStatus="s">
	<option value="${items}"
	<c:if test="${items==param.codeCategory}">
	selected="selected"
	</c:if>
	>
	<fmt:message key="${items}"/>
	</option>
	</c:forEach>
</select>
</tr>

<tr>
<td><fmt:message key="Name" /></td>
<td><input type="text" id="codeType.name" name="codeType.name" value="${codeType.name}" size="15" maxlength="255"/></td>
</tr>

</table>
</div>


</div>

</form>

</body>
</html>
</fmt:bundle>