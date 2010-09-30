<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="CodeType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>
<script type="text/javascript" src="jsp/js/mootools.js" ></script>

<link rel="stylesheet" href="../../jsp/styles/global.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../jsp/styles/formcheck.css" type="text/css" media="screen" />
<script type="text/javascript" src="../../jsp/js/mootools.js"> </script>
<script type="text/javascript" src="../../jsp/js/zh.js"> </script>
<script type="text/javascript" src="../../jsp/js/formcheck.js"> </script>
<script type="text/javascript" src="../../jsp/js/util.js"> </script>

<style type="text/css">
</style>
<script language="javascript">
function toSet(index){
	$$('input[type="checkbox"]').each(function(element){
		element.checked = false; // 全选
		});
	$('choose'+index).checked=true;
	
	$('id').value = $('choose'+index).value;
	$('name').value = $('name'+index).value;
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/CodeType.do" var="listCodeType"/>

<c:url value="/CodeType.do" var="processAddCode">
	<c:param name="method">processAddCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="processUpdateCode">
	<c:param name="method">processUpdateCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="processDeleteCode">
	<c:param name="method">processDeleteCode</c:param>
</c:url>

<c:url value="/CodeType.do" var="listCode">
	<c:param name="method">listCode</c:param>
</c:url>

<form method="post" action="${listCode}">
<div class="command">
<input type="submit" value="<fmt:message key="List"/>">
<input type="submit" value="<fmt:message key="Add"/>" onclick="docommit('${processAddCode}')">
<input type="submit" value="<fmt:message key="Update"/>" onclick="docommit('${processUpdateCode}')">
<input type="submit" value="<fmt:message key="Delete"/>" onclick="docommit('${processDeleteCode}')">
<input type="submit" value="<fmt:message key="Close"/>" onclick="javascript:window.close();">
</div>

<div>
<fmt:message key="Name"/>:
<input type="text" name="linearCode.name">
<input type="hidden" name="linearCode.typeId" value="${codeType.id}">
</div>

<div>
<c:out value="${codeType.name}"/>

<table>
<tr>
<td><input type="checkbox" onclick="isChecked();" name="chooseAll" id="chooseAll" /></td>
<td><fmt:message key="ID"/></td>
<td><fmt:message key="Name"/></td>
</tr>

<c:forEach var="item" items="${codes}" varStatus="status">
<tr>
<td><input type="checkbox" name="choose" id="choose${item.id}" onclick="toSet('${item.id}');" value="${item.id}"/></td>
<td><c:out value="${item.name}"/></td>
</tr>
</c:forEach>
</table>
</div>

</form>
</fmt:bundle>
<script type="text/javascript">
    new FormCheck('userForm');
</script>
</body>
</html>