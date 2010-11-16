<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--ArborText, Inc., 1988-2000, v.4002-->
<fmt:bundle basename="MessageBundle">
<html xmlns="http://www.w3.org/1999/xhtml" lang="EN">
<head>
<title><fmt:message key="UploadFile"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" rel="stylesheet" href="skin/office/default/layout.css" />
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
	
<script type="text/javascript">
	function change(obj){
		//alert(obj);
		if (navigator.userAgent.indexOf("MSIE")!=-1) {
			var arr = obj.value.split("\\");
			document.getElementById('name').value=arr[arr.length-1];
		}else if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Mozilla")!=-1) {
	    		document.getElementById('name').value=obj.files.item(0).fileName;
		}else {
	         alert("Not IE or Firefox (userAgent=" + navigator.userAgent + ")");
		}
	}
</script>

</head>

<body>
<c:url value="/File.do" var="file"/>
<c:url value="/File.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<form method="post" action="${processUpload}" enctype="multipart/form-data">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>

<div id="container">

<div style="text-align:center;">

<table>
<caption>UploadFile</caption>

<tbody>
<tr>
<td style="text-align:right;width:40%;"><fmt:message key="Name" /></td>
<td><input type="text" id="name" name="name" size="15" maxlength="255"/></td>
</tr>

<tr>
<td style="text-align:right;"><fmt:message key="Type" /></td>
<td>					
<select name="typeid">
	<c:forEach var="items" items="${fileTypes}" varStatus="s">
		<option value="${items.key}"

		<c:if test="${items.key==param.typeid}">
			selected="selected"
		</c:if>			
			>
		${items.value}
		</option>
	</c:forEach>
</select>
</td>
</tr>

<tr>
<td style="text-align:right;"><fmt:message key="File" /></td>
<td><input type="file" id="file" name="file" onchange="change(this)"/></td>
</tr>

<tr>
<td style="text-align:right;"><fmt:message key="Date" /></td>
<td>
<input type="text" id="date" name="date" size="15" maxlength="255" readonly/>
<img src="jsp/images/miniDate.gif" border=0 alt="<fmt:message key="choosedate"/>" onclick="displayCalendar(document.forms[0].date,'yyyy/mm/dd hh:ii',this,true)"/>
</td>
</tr>

</tbody>

<tfoot>
<tr>
<td colspan="2">
<input type="submit" value="<fmt:message key="Black"/>" onclick="docommit('${file}')"/>
<input type="submit" value="<fmt:message key="OK"/>"/>
</td>
</tr>
</tfoot>
</table>

</div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</body>
</html>
</fmt:bundle>