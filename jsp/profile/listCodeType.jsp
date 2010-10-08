<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<html>
<head>
<title><fmt:message key="CodeType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/office/default/layout.css" type="text/css" rel="stylesheet"/>
<link href="skin/office/default/presentation.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="jsp/js/util.js"></script>
<script type="text/javascript" src="jsp/js/mootools.js" ></script>
<script type="text/javascript" src="jsp/js/util.js" ></script>

<script type="text/javascript">
function checkbox(){
	var selectedObj = document.all["id"];
	var count = 0;
	for(var i=0;i<selectedObj.length;i++){
 		if(selectedObj[i].checked){
  			count = count + 1;
 		}
	}
	return count;
}


function remove(url){
	if(checkbox()<1){
		alert("请选择至少一条记录");
		return false;
	}
	
	return docommit(url);
}

function edit(url){
	
	if(checkbox()==1){
		return docommit(url);
	}
	alert("请选择一条记录");
	return false;
}
</script>
</head>

<body>
<fmt:bundle basename="MessageBundle">

<c:url value="/CodeType.do" var="list"/>

<c:url value="/CodeType.do" var="promptAdd">
	<c:param name="method">promptAdd</c:param>
</c:url>

<c:url value="/CodeType.do" var="promptUpdate">
	<c:param name="method">promptUpdate</c:param>
</c:url>

<c:url value="/CodeType.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>


<form method="post" action="${list}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>
<%@ include file="../incl/g_bar.jsp" %>

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="CodeType" name="title"/>
</jsp:include>

	<table align="center">
	<tr>
		<td>码表类型</td>
		<td>
			<select name="query.category">
				<c:forEach var="items" items="${category}" varStatus="s">
					
					<option value="${items}"
					<c:if test="${items==categoryDefault}">
						selected="selected"
					</c:if>
						>
					<fmt:message key="${items}"/>
					</option>
				</c:forEach>
			</select>
		</td>
				
		<td></td>
		<td></td>
	</tr>
	
	<tr >
		<td colspan="4">
		<span>
			<input type="submit" id="List" value="<fmt:message key="List"/>">
			<input type="submit" id="Add" value="<fmt:message key="Add"/>" onclick="docommit('${promptAdd}');return false;">
			<input type="submit" id="Edit" value="<fmt:message key="Edit"/>" onclick="docommit('${promptUpdate}');return false;">
			<input type="submit" id="Delete" value="<fmt:message key="Delete"/>" onclick="docommit('${processDelete}');return false;">
		</span>
		 </td>
	</tr>
	</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="list" name="title"/>
</jsp:include>

<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor=#edf0f9 id=small>
<tr bgcolor="#e3e9ff">
<td width="10px"> <input type="checkbox" onclick="isChecked();" name="chooseAll" id="chooseAll" /></td>
<td><fmt:message key="Name"/></td>
<td><fmt:message key="Category"/></td>
</tr>

<c:forEach var="item" items="${codeTypes}" varStatus="status">
<c:url value="/CodeType.do" var="editCode">
	<c:param name="method">listCode</c:param>
	<c:param name="codeType.id">${item.id}</c:param>
</c:url>

<tr>
<!-- 复选框 start -->
<td><input type="checkbox" name="choose" id="choose" value="${item.id}"/></td>
<!-- 复选框 end-->
<td onclick="openLookup('${editCode}');"> <c:out value="${item.name}"/> </td>
<td onclick="openLookup('${editCode}');"> <c:out value="${item.category}"/> </td>
</tr>
</c:forEach>
</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />
</form>
</fmt:bundle>
</body>
</html>