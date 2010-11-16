<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="gbar" style="width: 100%;height: 20px;text-align: left;background-color: #edf0f9;color: #add2da;font-size: 12px;">
	<c:if test="${!empty gbar}">
		<c:forEach var="item" items="${gbar}" varStatus="status">
			<span><a href="${item.value}"><fmt:message key="${item.key}" /></a>|</span>
		</c:forEach>
	</c:if>
</div>