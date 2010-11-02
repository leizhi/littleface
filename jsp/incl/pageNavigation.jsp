<!-- ------------------handling of pagination----------------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<c:if test="${page.totalRows >0 }">
	<c:if test="${page.currentPage >1 }">
		<input type="submit" name="page.forward" value="first" />
		<input type="submit" name="page.forward" value="previous" />
	</c:if>
	
	<c:if test="${page.currentPage < page.totalPages }">
		<input type="submit" name="page.forward" value="next" />
		<input type="submit" name="page.forward" value="last" />
	</c:if>
	totalRows:<c:out value="${page.totalRows }"></c:out>
	pageSize:<input type="text" name="page.pageSize" value="${page.pageSize }" size="2"/>
	
	<c:out value="${page.currentPage }"/> / <c:out value="${page.totalPages }"/>
	
	<input type="submit" name="page.forward" value="jump" /><input type="text" name="page.currentPage" value="${page.currentPage }" size="2"/>
</c:if>
<!-- ---------------------handling of pagination------------------------------------- -->
