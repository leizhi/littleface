<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="File"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/File.do" var="listFile"/>

<c:url value="/File.do" var="promptUpload">
	<c:param name="method">promptUpload</c:param>
</c:url>

<c:url value="/File.do" var="processUpload">
	<c:param name="method">processUpload</c:param>
</c:url>

<c:url value="/File.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<form method="post" action="${listFile}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>
<div id="container">

<div style="text-align:left;">
	<table>
		<caption>Search File</caption>

		<tbody>
			<tr>
				<td style="text-align: right;">内容简要</td>
				<td><input name="" value="" /></td>
				<td style="text-align: right;">日期</td>
				<td><input name="" value="" /></td>
			</tr>

			<tr>
				<td colspan="4" style="text-align: center;">
					<input type="submit" value="<fmt:message key="List"/>"> 
					<input type="submit" value="<fmt:message key="Upload"/>" onclick="docommit('${promptUpload}')"/> 
					<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}')"/>
				</td>
			</tr>

		</tbody>

	</table>


<table>
<caption>List File</caption>

<thead>
<tr>
<th><fmt:message key="ID"/></th>
<th><fmt:message key="Type"/></th>
<th><fmt:message key="Name"/></th>
<th><fmt:message key="Size"/></th>
<th><fmt:message key="Download"/></th>
<th><fmt:message key="Date"/></th>
</tr>
</thead>

<tbody>
<c:forEach var="item" items="${files}" varStatus="status">
<tr>
<td><input type="checkbox" name="id" value="${item.id}"> </td>
<td><c:out value="${item.typename}"></c:out></td>
<td><c:out value="${item.name}"></c:out></td>
<td><c:out value="${item.size}"></c:out></td>
<c:url value="/File.do" var="download">
	<c:param name="method">download</c:param>
	<c:param name="fileName">${item.filepath}</c:param>
</c:url>
<td><a href="${download}"><img src="jsp/images/down.gif" border=0 alt=""/></a> </td>
<td><c:out value="${item.datetime}"></c:out></td>
</tr>
</c:forEach>
</tbody>

<tfoot>
<tr>
<!-- 分页 -->
<td colspan="6" style="width: 600px;">
<%@ include file="../incl/pageNavigation.jsp"%>
</td>
</tr>
</tfoot>

</table>
</div>

<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>