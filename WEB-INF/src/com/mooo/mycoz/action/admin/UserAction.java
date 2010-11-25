package com.mooo.mycoz.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.component.Page;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.mycozBranch.User;

public class UserAction extends BaseSupport {

	private static Log log = LogFactory.getLog(UserAction.class);

	public String listUser(HttpServletRequest request,HttpServletResponse response) {
		try {
			User user = new User();
			
			Page page = new Page();
			page.buildComponent(request, dbProcess.count(user));
			dbProcess.refresh(user);
			dbProcess.setRecord(page.getOffset(),page.getPageSize());
			List<?> users = dbProcess.searchAndRetrieveList(user,DbProcess.OPEN_QUERY);
			
			request.setAttribute("users", users);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Exception Load error of: " + e.getMessage());
		}
		
		return "success";
	}
}
