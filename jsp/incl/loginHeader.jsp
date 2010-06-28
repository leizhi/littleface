<div id="header">
<%-- <img src="images/xpcLogo.gif"/> --%>
</div>

<div id="container">
<div id="left">
<!-- START OF PANE CODE -->
<div id="dhtmlgoodies_xpPane">
	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			<b>Very Good Administrator</b><br>
			OnLine:<%=org.pig.response.SessionCounter.getRealCount()%><br><br>
			Date modified: Oct. 3rd 2008				
			<!-- End content -->
		</div>	
	</div>
	<div class="dhtmlgoodies_panel">
		<div>

			<!-- Start content of pane -->
			<ul>
				<li><a href="<%=request.getContextPath()%>/Blog.do">Blog</a></li>
				<li><a href="<%=request.getContextPath()%>/Download.do">File</a></li>
			</ul>
			<!-- End content -->
		</div>		
	</div>

	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			<ul>
				<li><a href="<%=request.getContextPath()%>/User.do">Perfile</a></li>
				<li><a href="<%=request.getContextPath()%>/User.do?state=processLogout">Logout</a></li>
			</ul>
			<!-- End content -->
		</div>		
	</div>

</div>
<script type="text/javascript">
initDhtmlgoodies_xpPane(Array('Welcom to here','OpertionMenu','MyAccount'),Array(true,true,true,false),Array('pane1','pane2','pane3'));
</script>
</div>
