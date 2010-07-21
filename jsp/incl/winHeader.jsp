<div id="header">
<img src="images/xpcLogo.gif" />

<div id="mainMenu">
	<a>Products</a>
	<a>Blog</a>
	<a>Support</a>
	<a>Download</a>
</div>
<div id="submenu">
	<!-- The first sub menu -->
	<div id="submenu_1">
		<a href="">Product 1</a>
		<a href="">Product 2</a>
		<a href="">Product 3</a>
	</div>
	<!-- Second sub menu -->
	<plugin:boxa id="submenu_2"/>
	<%--<div id="submenu_2">
		<a href="">other</a>
		<a href="">other</a>
		<a href="">other</a>
	</div>--%>
	<!-- Third sub menu -->
	<div id="submenu_3">
		<a href="">History</a>
		<a href="">The team</a>
		<a href="">Contact us</a>
		<a href="">Visions</a>
	</div>
	<!-- Fourth sub menu -->
	<div id="submenu_4">
		<a href="">Sources</a>
		<a href="">Develop tools</a>
		<a href="<%=request.getContextPath()%>/Download.do?state=onList">Music</a>
		<a href="">Move</a>
		<a href="">Games</a>
		<a href="">Window scripts</a>
		<a href="">Games</a>
	</div>
</div>

<script type="text/javascript">
	window.onload = initMenu;
</script>

</div>

<div id="container">
<div id="left">
<form>
<div class="news">
<ul>
<li><html:message key="UserName" /><html:input type="text" id="userName_V" name="userName_V" styles="text"/></li>
<li><html:message key="Passsword" /><html:input type="password" id="password_V" name="password_V" styles="text"/></li>
<li>
<input type="submit" class="submit" name="Login" value="<html:message key="Login" />" onclick="document.forms[0].action='<%=request.getContextPath()%>/User.do?state=processLogin';document.forms[0].method='post';document.forms[0].submit();"/>
<input type="submit" class="submit" name="Login" value="<html:message key="Register" />" onclick="document.forms[0].action='<%=request.getContextPath()%>/User.do?state=promptAdd';document.forms[0].method='post';document.forms[0].submit();"/>
</li>
</ul></div>
</form>

<div class="news">
<ul> 
<li><a href="#"><%=com.mooo.mycoz.util.SessionCounter.getRealCount()%></a></li>
<li><a href="#"><html:message key="Test" /></a></li>
</ul></div>

<div class="news"><ul> 
<li><a href="#"><html:message key="Test" /></a></li>
<li><a href="#"><html:message key="Test" /></a></li>
</ul></div>

<div class="news"><ul> 
<li><a href="#"><html:message key="Test" /></a></li>
<li><a href="#"><html:message key="Test" /></a></li>
</ul></div>

<div class="news"><ul> 
<li><a href="#"><html:message key="Test" /></a></li>
<li><a href="#"><html:message key="Test" /></a></li>
</ul></div>
</div>
