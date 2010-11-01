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

<c:url value="/My.do" var="updateMy">
	<c:param name="method">update</c:param>
</c:url>

<form method="post" action="${updateMy}">
<%@ include file="../incl/g_top.jsp" %>
<%@ include file="../incl/g_block.jsp" %>

<%@ include file="../incl/g_bar.jsp" %>

<div id="container">
<div class="box">
	<ul>
		<li class="title"><fmt:message key="General"/></li>
				
		<li class="left"><fmt:message key="Name" /></li>
		<li class="right"><input type="text" name="user.name" value="${user.name }"/></li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="Sex" /></li>
		<li class="right">
					<select name="userInfo.sexId">
						<c:forEach var="items" items="${sexs}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.sexId and items.id==userInfo.sexId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Height" /></li>
		<li class="right"><input type="text" name="userInfo.height" size="5" value="${userInfo.height }"/>
					<select name="userInfo.heightUnitId">
						<c:forEach var="items" items="${heightUnits}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.heightUnitId and items.id==userInfo.heightUnitId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Weight" /></li>
		<li class="right"><input type="text" name="userInfo.weight" size="5" value="${userInfo.weight }"/>
					<select name="userInfo.weightUnitId">
						<c:forEach var="items" items="${weightUnits}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.weightUnitId and items.id==userInfo.weightUnitId }">
								selected="selected"
							</c:if> 
							<c:if test="${!empty param.userInfo.weightUnitId and items.id==param.userInfo.weightUnitId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>		
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Birthday" /></li>
		<li class="right"><input type="text" name="userInfo.birthday" id="birthday" value="${userInfo.birthday }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Career" /></li>
		<li class="right">
					<select name="userInfo.careerId">
						<c:forEach var="items" items="${careers}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.careerId and items.id==userInfo.careerId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>	
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Education" /></li>
		<li class="right">
					<select name="userInfo.educationId">
						<c:forEach var="items" items="${educations}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.educationId and items.id==userInfo.educationId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>		
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Married" /></li>
		<li class="right">
					<select name="userInfo.marriedId">
						<c:forEach var="items" items="${marrieds}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty userInfo.marriedId and items.id==userInfo.marriedId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>		
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="QQ" /></li>
		<li class="right"><input type="text" name="userInfo.qq" value="${userInfo.qq }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Email" /></li>
		<li class="right"><input type="text" name="userInfo.email" value="${userInfo.email }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Secret" /></li>
		<li class="right">
					<select name="userInfo.secret">
						<c:forEach var="items" items="${secrets}" varStatus="s">
							<option value="${items}"
							<c:if test="${!empty userInfo.secret and items==userInfo.secret }">
								selected="selected"
							</c:if> >${items}</option>
						--</c:forEach>
					</select>			
		</li>
		<li style="clear: both;"/>
	</ul>
</div>

<div class="box">

	<ul>
		<li class="title"><fmt:message key="Address"/></li>
				
		<li class="left"><fmt:message key="Country" /></li>
		<li class="right">
					<select name="address.countryId">
						<c:forEach var="items" items="${countrys}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty address.countryId and items.id==address.countryId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>		
		</li>
		<li style="clear: both;"/>

		<li class="left"><fmt:message key="Language" /></li>
		<li class="right">
					<select name="address.languageId">
						<c:forEach var="items" items="${languages}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty address.languageId and items.id==address.languageId }">
								selected="selected"
							</c:if> >${items.description}</option>
						--</c:forEach>
					</select>
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="City" /></li>
		<li class="right">
					<select name="address.cityId">
						<c:forEach var="items" items="${citys}" varStatus="s">
							<option value="${items.id}"
							<c:if test="${!empty address.cityId and items.id==address.cityId }">
								selected="selected"
							</c:if> >${items.name}</option>
						--</c:forEach>
					</select>
		</li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="PostalCode" /></li>
		<li class="right"><input type="text" name="address.postalCode" value="${address.postalCode }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Address" /></li>
		<li class="right"><input type="text" name="address.address" value="${address.address }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="Tel" /></li>
		<li class="right"><input type="text" name="address.tel" value="${address.tel }"/></li>
		<li style="clear: both;"/>
		
		<li class="left"><fmt:message key="MobileNo" /></li>
		<li class="right"><input type="text" name="address.mobileNo" value="${address.mobileNo }"/></li>
		<li style="clear: both;"/>
	</ul>
</div>
<div style="clear: both;"></div>
<!-- one row end -->

<div class="box">

	<ul>
		<li class="title"><fmt:message key="Images"/></li>
				
		<li>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
			<img width="80px" height="60px" src=""/>
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
<input type="submit" value="Update" onclick="docommit('${updateMy}');return false;"/>
</div>
<jsp:include page="../incl/g_footer.jsp" />
</div>

</form>
</fmt:bundle>
</body>
</html>