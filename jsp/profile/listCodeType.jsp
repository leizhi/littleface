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
<script language="javascript">
function toSet(obj){
	//alert(obj.value);
	
	if(obj.checked){
		$('id').value = obj.value;
	} else {
		$('id').value = null;
	}
	//alert($('id').value);
	
	//alert('ok');
}
</script>
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
	/*
	var edit = document.getElementById("Edit");
	var remove = document.getElementById("Delete");
	var codeManager= document.getElementById("CodeManager");
	if(count > 0){
		remove.style.display="inline";
	} else {
		remove.style.display="none";
	}
	
	if(count == 1){
		edit.style.display="inline";
		codeManager.style.display="inline";		
	} else {
		edit.style.display="none";
		codeManager.style.display="none";
	}
	alert("count="+count);
	*/
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

<c:url value="/CodeType.do" var="processAdd">
	<c:param name="method">processAdd</c:param>
</c:url>

<c:url value="/CodeType.do" var="promptUpdate">
	<c:param name="method">promptUpdate</c:param>
</c:url>

<c:url value="/CodeType.do" var="processUpdate">
	<c:param name="method">processUpdate</c:param>
</c:url>

<c:url value="/CodeType.do" var="processDelete">
	<c:param name="method">processDelete</c:param>
</c:url>

<c:url value="/CodeType.do" var="listCode">
	<c:param name="method">listCode</c:param>
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
			<select name="codeCategory">
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
		</td>
		<td></td>
		<td></td>
	</tr>
	
	<tr >
		<td colspan="4">
		<span>
			<input type="submit" id="List" value="<fmt:message key="List"/>">
			<input type="submit" id="Add" value="<fmt:message key="Add"/>" onclick="return docommit('${promptAdd}')">
			<input type="submit" id="Edit" value="<fmt:message key="Edit"/>" onclick="return edit('${promptUpdate}')">
			<input type="submit" id="Delete" value="<fmt:message key="Delete"/>" onclick="return subcommit('${processDelete}')">
		</span>
		 </td>
	</tr>
	</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_head.jsp">
<jsp:param value="list" name="title"/>
</jsp:include>

<table border="0" cellspacing="0" cellpadding="1" width="100%" bgcolor=#ffffff id=small>
<tr bgcolor="#649caa">
<td width="10px"> <input type="checkbox" onclick="isChecked();" name="chooseAll" id="chooseAll" /></td>
<td><fmt:message key="Name"/></td>
<td><fmt:message key="Category"/></td>
</tr>

<c:forEach var="item" items="${linearTypes}" varStatus="status">
<c:url value="/CodeType.do" var="editCode">
	<c:param name="method">listCode</c:param>
	<c:param name="id">${item.id}</c:param>
</c:url>

<tr>
<!-- 复选框 start -->
<td><input type="checkbox" name="choose" id="choose" value="${item.id}" onclick="toSet(this)"/></td>
<!-- 复选框 end-->
<td onclick="openLookup('${editCode}');"><c:out value="${item.name}"></c:out></td>
<td onclick="openLookup('${editCode}');"><fmt:message key="${item.category}"/></td>
</tr>
</c:forEach>
</table>
<jsp:include page="../incl/g_tail.jsp" />

<jsp:include page="../incl/g_footer.jsp" />
</form>
</fmt:bundle>
</body>
</html>