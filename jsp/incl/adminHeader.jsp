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
			File folder<br><br>
			Date modified: Oct. 3rd 2008				
			<!-- End content -->
		</div>	
	</div>
	<div class="dhtmlgoodies_panel">
		<div>

			<!-- Start content of pane -->
			<ul>
				<li><a href="<%=request.getContextPath()%>/BlogCategory.do"><html:message key="BlogCategory" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/BlogType.do"><html:message key="BlogType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountCategory.do"><html:message key="AccountCategory" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountType.do"><html:message key="AccountType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountGroup.do"><html:message key="AccountGroup" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountExtension.do"><html:message key="AccountExtension" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Account.do"><html:message key="Account" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/NoteType.do"><html:message key="NoteType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/OperatorUser.do"><html:message key="OperatorUser" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Country.do"><html:message key="Country" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Language.do"><html:message key="Language" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Currency.do"><html:message key="Currency" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/DownloadType.do"><html:message key="DownloadType" /></a></li>
			</ul>
			<!-- End content -->
		</div>		
	</div>

	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			<ul>
    				<li><a href="<%=request.getContextPath()%>/Financial.do"><html:message key="Financial" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Sale.do"><html:message key="Sale" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Investment.do"><html:message key="Investment" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Free.do"><html:message key="Free" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Accounting.do"><html:message key="Accounting" /></a></li>
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
initDhtmlgoodies_xpPane(Array('Welcom to here Root','Setup','Service','MyAccount'),Array(true,true,true,false),Array('pane1','pane2','pane3','pane4'));
</script>
</div>
