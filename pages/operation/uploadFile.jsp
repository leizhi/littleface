<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="MessageBundle">

<html>
<head>
<title><fmt:message key="UploadFile"/></title>
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
<c:url value="/File.do" var="file"/>
<c:url value="/File.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<form method="post" action="${processUpload}" enctype="multipart/form-data">

<div class="command">
<input type="submit" value="<fmt:message key="Black"/>" onclick="docommit('${file}')">
<input type="submit" value="<fmt:message key="OK"/>">
</div>

<div>
<fmt:message key="UploadFile"/>
<div id="formresponse">
<input type="hidden" id="id" name="id" />

<table align="center" style="font-size:0.9em;">

<tr>
<td><fmt:message key="Name" /></td>
<td><input type="text" id="name" name="name" size="15" maxlength="255"/></td>
</tr>

<tr>
<td><fmt:message key="Type" /></td>
<td><input id="typeid" name="typeid"/> </td>
</tr>

<tr>
<td><fmt:message key="File" /></td>
<td><input type="file" id="downloadpath" name="downloadpath"/></td>
</tr>

<tr>
<td><fmt:message key="Image" /></td>
<td><input type="file" id="imagepath" name="imagepath"/></td>
</tr>

<tr>
<td><fmt:message key="DownloadDate" /></td>
<td>
<input type="text" id="date" name="date" size="15" maxlength="255" readonly/> 
<img src="jsp/images/miniDate.gif" border=0 alt="<fmt:message key="choosedate"/>" onclick="displayCalendar(document.forms[0].date,'yyyy/mm/dd hh:ii',this,true)">
</td>
</tr>

<tr>
<td><fmt:message key="Description" /></td>
<td><input type="text" id="description" name="description" size="15" maxlength="255"/></td>
</tr>

</table>
</div>


</div>

</form>

</body>
</html>
</fmt:bundle>