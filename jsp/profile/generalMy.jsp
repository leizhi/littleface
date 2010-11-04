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
.box {
	margin: 0;
	padding: 0;
	width: 49%;
	float: left;
	text-align: left;
	background-color: edf0f9;
	color: 000000;
	font-size: 12px;
	border: 1px solid #ffffff;
}

.box ul {
	width: 100%;
	margin: 0;
	padding: 0;
}

.box li {
	background-color: #edf0f9;
	float: left;
	display: block;
}

.box ul .tl {
	text-align:center;
	background-color:e3e9ff;
	width: 20%;
}

.box ul .tr {
	text-align:right;
	background-color:e3e9ff;
	width: 80%;
}

.box ul .title {
	text-align:left;
	background-color:e3e9ff;
	width: 100%;
}

.box ul .left {
	text-align:center;
	width: 20%;
}
.box ul .right {
	width: 80%;
}
</style>

<script type="text/javascript">
function docommit(url) {
	document.forms[0].action=url;
	document.forms[0].submit();
}
</script>
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

<div id="container">
	
<div class="box">
	<ul>
		<li class="title"><fmt:message key="General"/></li>
				
		<li class="left"><fmt:message key="Name" /></li>
		<li class="right"><c:out value="${user.name }" /></li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="Sex" /></li>
		<li class="right"><c:out value="${userInfo.sex.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Height" /></li>
		<li class="right"><c:out value="${userInfo.height }" />:<c:out value="${userInfo.heightUnit.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Weight" /></li>
		<li class="right"><c:out value="${userInfo.weight}" />:<c:out value="${userInfo.weightUnit.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Birthday" /></li>
		<li class="right"><fmt:formatDate value="${userInfo.birthday }" pattern="yyyy/MM/dd hh:mm:ss"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Career" /></li>
		<li class="right"><c:out value="${userInfo.career.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Education" /></li>
		<li class="right"><c:out value="${userInfo.education.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Married" /></li>
		<li class="right"><c:out value="${userInfo.married.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="QQ" /></li>
		<li class="right"><c:out value="${userInfo.qq }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Email" /></li>
		<li class="right"><c:out value="${userInfo.email }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Secret" /></li>
		<li class="right"><c:out value="${userInfo.secret }" /></li>
		<li style="clear: both;"/>
	</ul>
</div>

<div class="box">

	<ul>
		<li class="title"><fmt:message key="Address"/></li>
				
		<li class="left"><fmt:message key="Country" /></li>
		<li class="right"><c:out value="${address.country.name }" /></li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="City" /></li>
		<li class="right"><c:out value="${address.city.name }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="PostalCode" /></li>
		<li class="right"><c:out value="${address.postalCode }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Address" /></li>
		<li class="right"><c:out value="${address.address }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Tel" /></li>
		<li class="right"><c:out value="${address.tel }" /></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="MobileNo" /></li>
		<li class="right"><c:out value="${address.mobileNo }" /></li>
		<li style="clear: both;"/>
	</ul>
</div>
<div style="clear: both;"></div>
<!-- one row end -->

<div class="box">

	<ul>
		<li class="title"><fmt:message key="Images"/></li>
				
		<li>
			<c:forEach var="item" items="${userImages}" varStatus="status">
				<img width="80px" height="60px" src="${uploadPath}${item.filepath }"/>
			</c:forEach>
		</li>
	</ul>
</div>

<div class="box">

	<ul>
		<li class="title"><fmt:message key="Friend"/></li>
				
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
		<li>abc@163.com</li>
	</ul>
</div>
<div style="clear: both;"></div>

<div style="width: 100;">
<c:url value="/My.do" var="editMy">
	<c:param name="method">edit</c:param>
</c:url>

<input type="submit" value="Edit" onclick="docommit('${editMy}');return false;"/>
</div>
<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>