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
color:#ffffff;
}
li {
background-color: #edf0f9;
color:#000;
}

</style>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/Activity.do" var="listAccount"/>

<c:url value="/Activity.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/Activity.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/Activity.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/Activity.do" var="processDownload">
	<c:param name="method">processDownload</c:param>
</c:url>

<form method="post" action="${listAccount}">
<%@ include file="../../incl/g_top.jsp" %>
<%@ include file="../../incl/g_block.jsp" %>
<%@ include file="config.jsp" %>
<%@ include file="../../incl/g_bar.jsp" %>

<div id="container">

<table>
<caption>Search Account</caption>
<tbody>
<tr>
	<td style="text-align: right;">名称</td>
	<td><input name="query_name" value="${query_name }"/></td>
	<td style="text-align: right;">性别</td>
	<td>
		<select name="query_sexId">
		<option value="">--All-</option>
			<c:forEach var="items" items="${sexs}" varStatus="s">
				<option value="${items.id}"
				<c:if test="${!empty query_sexId and items.id==query_sexId }">
					selected="selected"
				</c:if> >${items.name}</option>
			--</c:forEach>
		</select>
	</td>
</tr>

<tr>
	<td style="text-align: right;">地区</td>
	<td><input name="" value=""/></td>
	<td style="text-align: right;">年龄</td>
	<td><input name="" value=""/></td>
</tr>

<tr>
	<td colspan="4" style="text-align: center;">
			<input type="submit" value="<fmt:message key="List"/>">
	 </td>
</tr>
</tbody>
</table>

<div>
<div style="width: 100%;background-color: #e3e9ff;color:#000;margin:1px;text-align: left;">
<%@ include file="../../incl/pageNavigation.jsp"%>
</div>

<c:forEach var="item" items="${accounts}" varStatus="status">
<div style="float: left;width: 24.5%;text-align: left;font-size: 12px;border: 1px solid #ffffff;background-color: #edf0f9;">
<div style="width: 100%;height:13px;background-color: #e3e9ff;color:#000;margin:1px;">
<span>姓名:${item.user.name} &nbsp;</span>
</div>

<div style="width: 100%;margin-left:1px;">
<c:forEach var="iu" items="${item.user.userImages}" varStatus="su">
	<c:if test="${su.index < 3}">
		<img width="80px" height="60px" src="${uploadPath}${iu.filepath }"/>
	</c:if>
</c:forEach>
</div>

<div style="width: 100%;margin-left:1px;">
<ul style="width: 100%;margin-left:0px;">
<li>性别:<c:out value="${item.sex.name }"/></li>
<li>年龄:</li>
<li>生日:<fmt:formatDate value="${item.userInfo.birthday }" pattern="yyyy/MM/dd"/></li>
<li>国家:<c:out value="${item.country.name }"/></li>
<li>城市:<c:out value="${item.city.name }"/></li>
<li>地址:<c:out value="${item.addressBook.address }"/></li>
<li>爱好:</li>

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
</div>
<div style="clear: both;"></div>

<div style="width: 100%;background-color: #e3e9ff;color:#000;margin:1px;text-align: left;">
<%@ include file="../../incl/pageNavigation.jsp"%>
</div>

<jsp:include page="../../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>