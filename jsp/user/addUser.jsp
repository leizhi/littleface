<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/incl/static.inc" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="generator" content="HTML Tidy, see www.w3.org" />
<meta name="revision" content="$Id: addUser.jsp,v 1.3 2008/07/15 22:11:36 cvs Exp $" />
<title>W3C Technical Reports and Publications</title>
<link type="text/css" rel="stylesheet" href="../ststyles/slidedown.css" />
<script src="../js/slidedown.js" type="text/javascript" ></script>
	<script type="text/javascript" src="../js/form-submit.js"></script>
	<script type="text/javascript" src="../js/ajax.js"></script>
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
	.text {
		border-bottom:1px solid #005AA7;
		color:#005AA7;
		border-top:0px;
		border-left:0px;
		border-right:0px;
		background-color:transparent;
		}
	</style>
</head>

<body>


<div id="mainContainer" style="">
<fieldset>
<legend>welcome come </legend>

<form  action="<%=request.getContextPath()%>/User.do?state=processAdd" method="post">
<div id="formResponse">

<table align="left" style="font-size:0.9em;">

<tr>
<td>Register Name:</td>
<td><input type="text" id="UserName" name="UserName" /></td>
</tr>

<tr>
<tr>
<td>Password:</td>
<td><input type="password" id="Password" name="Password"/></td>
</tr>

<tr>
<td>Password (Again):</td>
<td><input type="password" id="Password2" name="Password2" property="Password2"/></td>
</tr>

<tr>
<td>From Country:</td>
<td><html:select id="Country" name="Country" property="Country"/></td>
</tr>

<tr>
<td>From City:</td>
<td><html:select id="City" name="City" property="City"/></td>
</tr>

<tr>
<td>From Address:</td>
<td><input type="text" id="Address" name="Address" property="Address"/></td>
</tr>

<tr>
<td>From Tel:</td>
<td><input type="text" id="Tel" name="Tel" property="Tel"/></td>
</tr>
<tr>
<td>From Zip:</td>
<td><input type="text" id="Zip" name="Zip" property="Zip"/></td>
</tr>
<tr>
<td>From Language:</td>
<td><html:select id="Language" name="Language" property="Language"/></td>
</tr>

<tr>
<td>E-Mail Address:</td>
<td><input type="text" id="Email" name="Email" property="Email"/></td>
</tr>
<tr>
<td>E-Mail Address (Again):</td>
<td><input type="text" id="Emailverify" name="Emailverify" property="Emailverify"/></td>
</tr>

<tr><td>Secret Question</td>
<td><select name="secretQuestion">
 <option value="Who was your childhood best friend?" selected>Who was your childhood best friend?</option>
 <option value="What is your favorite flavor of ice cream?">What is your favorite flavor of ice cream?</option>
 <option value="What is your favorite sports team?">What is your favorite sports team?</option>

 <option value="What is the last 4 digits of your library card?">What is the last 4 digits of your library card?</option>
 <option value="Who was your childhood crush?">Who was your childhood crush?</option>
</select></td></tr>

<tr><td>Secret Answer</td><td><input size="48" type="text" name="secretAnswer" value=""></td></tr>
<tr><td>How did you find us? </td>
<td><select name="findUs">
 <option value="none" selected>Please select one (Optional)</option>
 <option value="Word of mouth">Word of mouth</option>

 <option value="Search Engine">Search Engine</option>
 <option value="Ads">Advertisements</option>
 <option value="Other">Other</option>
</select></td></tr>

</table>
<br clear="all">
<input class="input" type="checkbox" name="agree"> I agree<br><br>
<input type="submit" id="mySubmit" class="formButton" value="Send">
<input type="reset" class="formButton" value="Reset">
	</div>		
	</form>
</div>

</body>
</html>

