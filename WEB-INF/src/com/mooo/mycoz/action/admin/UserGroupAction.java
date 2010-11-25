package com.mooo.mycoz.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozShared.UserGroup;

public class UserGroupAction extends BaseSupport{
	private static Log log = LogFactory.getLog(UserGroupAction.class);

	public String listUserGroup(HttpServletRequest request,HttpServletResponse response) {
		try {
			UserGroup userGroup = new UserGroup();
			
			Page page = new Page();
			page.buildComponent(request, dbProcess.count(userGroup));
			dbProcess.refresh(userGroup);
			dbProcess.setRecord(page.getOffset(),page.getPageSize());
			List<?> userGroups = dbProcess.searchAndRetrieveList(userGroup,DbProcess.OPEN_QUERY);
			
			request.setAttribute("userGroups", userGroups);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		
		return "success";
	}
}
