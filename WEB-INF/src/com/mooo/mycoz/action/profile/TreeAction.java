package com.mooo.mycoz.action.profile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozShared.TreeType;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;

public class TreeAction extends BaseSupport{
	private static Log log = LogFactory.getLog(TreeAction.class);

	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");
			TreeType tt = new TreeType();
			tt.setCatalog("mycozShared");
			
			List<?> treeTypes = tt.searchAndRetrieveList();
			request.setAttribute("treeTypes", treeTypes);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String promptAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");
			TreeType treeType = new TreeType();
			treeType.setId(new Integer(IDGenerator.getNextID("mycozShared.TreeType")));
			treeType.setName(request.getParameter("TreeType.name"));
			
			request.setAttribute("TreeType", treeType);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}

	public String processAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processAdd");
			DBSession session = DBSession.getInstance();
			session.setCatalog("mycozShared");
			
			TreeType bean = new TreeType();
			if(request.getParameter("TreeType.name")==null || "".equals(request.getParameter("TreeType.name"))){
				return "promptAdd";
			}
			
			ParamUtil.bindData(request, bean, "TreeType");
			if (log.isDebugEnabled()) log.debug("name="+request.getParameter("TreeType.name"));

			session.save(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String promptUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");
			
			//request.setAttribute("TreeType.id", request.getParameter("id"));
			//request.setAttribute("TreeType.name", request.getParameter("TreeType.name"));
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String processUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processUpload");
			DBSession session = DBSession.getInstance();
			TreeType bean = new TreeType();
			ParamUtil.bindData(request, bean, "TreeType");
			session.update(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
}
