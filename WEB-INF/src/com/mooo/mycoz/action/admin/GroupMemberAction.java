package com.mooo.mycoz.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.GroupMember;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.UserGroup;

public class GroupMemberAction extends BaseSupport {
	private static Log log = LogFactory.getLog(GroupMemberAction.class);

	public String listGroupMember(HttpServletRequest request,HttpServletResponse response) {
		try {
			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable(GroupMember.class, "groupMember");
			mdb.addTable(User.class, "user");
			mdb.addTable(UserGroup.class, "userGroup");

			mdb.setForeignKey("groupMember", "userId", "user", "id");
			mdb.setForeignKey("groupMember", "groupId", "userGroup", "id");

			mdb.setLike("user","name",request.getParameter("query_user"));
			mdb.setLike("userGroup","name",request.getParameter("query_userGroup"));

			mdb.setRetrieveField("groupMember", "id");
			mdb.setRetrieveField("user", "name");
			mdb.setRetrieveField("userGroup", "name");
			
			mdb.setOrderBy("user","id");
			
			Page page = new Page();
			page.buildComponent(request, mdb.count());
			mdb.setRecord(page.getOffset(),page.getPageSize());
			
			List<Object> groupMembers = mdb.searchAndRetrieveList();
			
			request.setAttribute("groupMembers", groupMembers);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		
		return "success";
	}
}
