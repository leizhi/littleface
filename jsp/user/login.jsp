<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src=""></script>
	<script type="text/javascript" src=""></script>
	<style type="text/css">
	body{
		margin:0px;
		font-size:0.8em;
		font-family:Trebuchet MS
	}
	#mainContainer{
		width:840px;	
		margin:5px;
	}
	table,tr,td{
		vertical-align:top;
	}
	.textInput{
		width:300px;
	}
	html{
		margin:0px;
	}
	.formButton{
		width:75px;
	}
	textarea,input,select{
		font-family:Trebuchet MS;
	}
	i{
		font-size:0.9em;
	}
	</style>
</head>

<body>
<div id="mainContainer" style="">
<fieldset>
<legend>welcome come </legend>

<form  action="<%=request.getContextPath()%>/User.do?state=processLogin" method="post" >
<div id="formResponse">
 
<table style="font-size:0.9em;">
<tr><td>UserName:</td><td><html:input type="text" id="userName_V" name="userName_V" property="userName_V"/></td></tr>
<tr><td>Password:</td><td><html:input type="password" id="password_V" name="password_V" property="password_V"/></td></tr>

<tr><td></td>

<td>
<html:input type="submit" value="<html:message key="Login" />" >
<input type="reset" class="formButton" value="Reset"><a href="?state=forgot">Forgot Password?</a>
</td>
</tr>

</table>

</div>		
</form>
</div>

</body>
</html>
