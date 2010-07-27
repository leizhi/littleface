<!-- ------------------handling of pagination----------------------- -->

<%
   int current = Integer.parseInt((String)request.getAttribute("CurrentPage"));
   int total = Integer.parseInt((String)request.getAttribute("CountPerPage"));
   int count = Integer.parseInt((String)request.getAttribute("TotalPage"));

if (total > 0 ){
%>
<%= current %> of <%= total %>
 at <input type="text" name="CountPerPage"  property="CountPerPage" size="4"/>
 records per page
<%
} // end if 1

if(current > 1){
%>
<input type="submit" name="Previous" property="Previous" value="Previous"/>
<%
} // end if 2
%>

<%
if(current < total && total > 1){
%>
<input type="submit" name="Next" property="Next" value="Next"/>
<%
} // end if 3
%>

<%
if(total > 2){
%>
<input type="submit" name="JumpTo" property="JumpTo" value="JumpTo"/>
<input type="text" id="CurrentPage" name="CurrentPage"  property="CurrentPage" size="4"/>
 of <%= total+ "" %>
<%
} else if (total > 0) {
%>
<input type="text" name="CurrentPage"  type="hidden"/>
<%
} // end if 4
%>

<!-- ---------------------handling of pagination------------------------------------- -->
