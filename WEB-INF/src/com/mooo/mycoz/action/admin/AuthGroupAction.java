package com.mooo.mycoz.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozShared.Action;
import com.mooo.mycoz.dbobj.mycozShared.AuthGroup;
import com.mooo.mycoz.dbobj.mycozShared.Method;
import com.mooo.mycoz.dbobj.mycozShared.UserGroup;

public class AuthGroupAction extends BaseSupport{
	private static Log log = LogFactory.getLog(AuthGroupAction.class);
	
	public String listAuthGroup(HttpServletRequest request,HttpServletResponse response) {
		try {
			MultiDBObject mdb = new MultiDBObject();
			mdb.addTable(AuthGroup.class, "authGroup");
			mdb.addTable(UserGroup.class, "userGroup");
			mdb.addTable(Action.class, "action");
			mdb.addTable(Method.class, "method");

			mdb.setForeignKey("authGroup", "groupId", "userGroup", "id");
			mdb.setForeignKey("authGroup", "methodId", "method", "id");
			mdb.setForeignKey("method", "actionId", "action", "id");

			mdb.setLike("method","methodName",request.getParameter("query_method"));
			mdb.setLike("action","name",request.getParameter("query_action"));

			mdb.setRetrieveField("authGroup", "id");
			mdb.setRetrieveField("userGroup", "name");
			mdb.setRetrieveField("action", "name");
			mdb.setRetrieveField("method", "methodName");
			
			mdb.setOrderBy("authGroup","id");
			
			Page page = new Page();
			page.buildComponent(request, mdb.count());
			mdb.setRecord(page.getOffset(),page.getPageSize());
			
			List<Object> authGroups = mdb.searchAndRetrieveList();
			
			request.setAttribute("authGroups", authGroups);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		
		return "success";
	}
}
