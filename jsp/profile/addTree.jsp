<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="MessageBundle">

<html>
<head>
<title><fmt:message key="AddTree"/></title>
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
<c:url value="/Tree.do" var="tree"/>
<c:url value="/Tree.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<form method="post" action="${processAdd}">

<div class="command">
<input type="submit" value="Black" onclick="docommit('${tree}')">
<input type="submit" value="OK"">
</div>

<div>
<fmt:message key="AddTree"/>
<div id="formresponse">
<input type="hidden" id="TreeType.id" name="TreeType.id" value="${TreeType.id}"/>

<table align="center" style="font-size:0.9em;">

<tr>
<td><fmt:message key="Name" /></td>
<td><input type="text" id="TreeType.name" name="TreeType.name" value="${TreeType.name}" size="15" maxlength="255"/></td>
</tr>

</table>
</div>


</div>

</form>

</body>
</html>
</fmt:bundle>