<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="Account"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>

<style type="text/css">
.title {
background-color:#464646;
color:blue;
}
li {
background-color:#c0c0c0;
color:blue;
}

</style>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/Account.do" var="listAccount"/>

<c:url value="/Account.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/Account.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/Account.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listAccount}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="Search Account" name="title"/>
</jsp:include>

	<table align="center">
	<tr>
		<td>年龄</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>性别</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr>
		<td>地区</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
		<td>名称</td>
		<td><input name="" value="" style="font-size:10px; border:solid 1px #7aaebd;"/></td>
	</tr>
	
	<tr >
		<td colspan="4" align="center">
		<span>
				<input type="submit" value="<fmt:message key="List"/>">
		</span>
		 </td>
	</tr>
	</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="List Account" name="title"/>
</jsp:include>

<c:forEach var="item" items="${accounts}" varStatus="status">
<div style="float: left;width: 24.5%;text-align: left;background-color: #fff;color: #add2da;font-size: 12px;border: 1px solid #ffffff;">

<div style="width: 100%;height:13px;background-color: #464646;margin:1px;">
<span>姓名:${item.name} &nbsp;</span>
</div>

<div style="width: 100%;margin-left:1px;">
<span><img width="80px" height="60px" src=""/></span>
<span><img width="80px" height="60px" src=""/></span>
<span><img width="80px" height="60px" src=""/></span>
</div>

<div style="width: 100%;margin-left:1px;">
<ul style="width: 100%;margin-left:0px;">
<li>性别:男</li>
<li>年龄:29</li>
<li>生日:xxxxxxxx29</li>
<li>地址:下xxxxxxxxxxxxxxxxxx</li>
<li>爱好:下xxxxxxxxxxxxxxxxxx </li>

<c:url value="/Account.do" var="talk">
	<c:param name="method">talk</c:param>
	<c:param name="id">${item.id}</c:param>
</c:url>

<li><span><a href="">详细</a></span>
<span><a onclick="javascript:openLookup('${talk}');">交谈</a></span>
<span><a href="">发消息</a></span>
<span><a href="">加为好友</a></span>
</li>
</ul>
</div>

</div>
</c:forEach>
 
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />

</form>
</fmt:bundle>
</body>
</html>