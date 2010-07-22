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
				<li><a href="<%=request.getContextPath()%>/BlogCategory.do"><fmt:message key="BlogCategory" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/BlogType.do"><fmt:message key="BlogType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountCategory.do"><fmt:message key="AccountCategory" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountType.do"><fmt:message key="AccountType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountGroup.do"><fmt:message key="AccountGroup" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/AccountExtension.do"><fmt:message key="AccountExtension" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Account.do"><fmt:message key="Account" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/NoteType.do"><fmt:message key="NoteType" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/OperatorUser.do"><fmt:message key="OperatorUser" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Country.do"><fmt:message key="Country" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Language.do"><fmt:message key="Language" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Currency.do"><fmt:message key="Currency" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/DownloadType.do"><fmt:message key="DownloadType" /></a></li>
			</ul>
			<!-- End content -->
		</div>		
	</div>

	<div class="dhtmlgoodies_panel">
		<div>
			<!-- Start content of pane -->
			<ul>
    				<li><a href="<%=request.getContextPath()%>/Financial.do"><fmt:message key="Financial" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Sale.do"><fmt:message key="Sale" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Investment.do"><fmt:message key="Investment" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Free.do"><fmt:message key="Free" /></a></li>
    				<li><a href="<%=request.getContextPath()%>/Accounting.do"><fmt:message key="Accounting" /></a></li>
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
