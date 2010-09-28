<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="pager.totalRows>pager.pageSize || pager.pagerMethod!='pagerMethod'">
<s:hidden name="pager.pagerMethod" id="pager.pagerMethod"/>

<s:label value="Total:" />
<s:property value="pager.totalRows" />
<s:hidden name="pager.totalRows"/>

<s:label value="Pages:" />
<s:property value="pager.currentPage" />
<s:label value="/" />
<s:property value="pager.totalPages" />
<s:hidden name="pager.totalPages"/>

<s:label value="PageSize:" />
<s:textfield name="pager.pageSize" label="pageSize" size="2"/>

<s:if test="pager.currentPage>1">
<s:submit key="first" value="<<" onclick="fiter(this)"/>
<s:submit key="previous" onclick="fiter(this)"/>
</s:if>
<s:if test="pager.totalPages>pager.currentPage">
<s:submit key="next" onclick="fiter(this)"/>
<s:submit key="last" value=">>" onclick="fiter(this)"/>
</s:if>
<s:submit key="jump" onclick="fiter(this)"/>

<s:textfield name="pager.currentPage" label="currentPage" size="2"/>
<s:label value="/" />
<s:property value="pager.totalPages" />

</s:if>