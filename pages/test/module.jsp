<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc"%>
<c:url value="/Menu.do" var="urlMenu">
	<c:param name="method">accept</c:param>
</c:url>
<fmt:setLocale value="zh_CN"/>
<fmt:message key="TechnicalSupport"></fmt:message>
<form name="default" method="post" action="${urlMenu}">

<a href="${urlMenu}">link</a> <select name="classId" style="width: 140px">
	<c:forEach var="class" items="${classes}">
		<c:choose>
			<c:when test="${runReturncardForm.classId==class[1]}">
				<option value="${class[1]}" selected>
				${class[0]}|${class[2]}</option>
			</c:when>
			<c:otherwise>
				<option value="${class[1]}">${class[0]}|${class[2]}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>

<select name="factoryid" id="factoryid" style="width: 138px">
	<c:forEach var="factory" items="${language}">
		<option value="${factory.key}">${factory.value}</option>
	</c:forEach>
</select> 

<c:if test="returnCards">
<c:forEach var="returnCard" items="${returnCards}">
	<tr class="trbai">
		<td height="27" align="center" noWrap>${returnCard.returntime}</td>
		<td height="27" align="center" noWrap>
		<c:forEach var="returnerName" items="${returnerNameMap}">
			<c:if test="${returnCard.returner == returnerName.key}"> 
				${returnerName.value}  
            </c:if>
		</c:forEach></td>
		<td height="27" align="center" noWrap>${returnCard.cardcode}</td>
		<td height="27" align="center" noWrap>
			<a href="javascript:dispDetail('${returnCard.tid}');">查看详细</a>
		</td>
	</tr>
</c:forEach>
</c:if>
<input type="submit">
</form>