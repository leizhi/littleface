package com.mooo.mycoz.action.profile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.DBSession;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;

public class CodeTypeAction extends BaseSupport{
	private static Log log = LogFactory.getLog(CodeTypeAction.class);

	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("list");
			String value;
			
			List codeCategory = new ArrayList();
			codeCategory.add("Linear");
			codeCategory.add("Tree");
			request.setAttribute("codeCategory", codeCategory);

			CodeType tt = new CodeType();
			tt.setCatalog("mycozShared");
			//tt.setCategory("Tree");
			if((value=request.getParameter("codeCategory")) != null){
				tt.setField("Category", value);
			}
			
			List<?> linearTypes = tt.searchAndRetrieveList();
			request.setAttribute("linearTypes", linearTypes);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String promptAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptAdd");
			List codeCategory = new ArrayList();
			codeCategory.add("Linear");
			codeCategory.add("Tree");
			request.setAttribute("codeCategory", codeCategory);
			
			CodeType codeType = new CodeType();
			codeType.setId(new Integer(IDGenerator.getNextID("mycozShared.CodeType")));
			codeType.setName(request.getParameter("CodeType.name"));
			codeType.setCategory("Linear");
			request.setAttribute("codeType", codeType);
			
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
			
			CodeType bean = new CodeType();
			if(request.getParameter("CodeType.name")==null || "".equals(request.getParameter("CodeType.name"))){
				return "promptAdd";
			}
			
			ParamUtil.bindData(request, bean, "CodeType");
			if (log.isDebugEnabled()) log.debug("name="+request.getParameter("CodeType.name"));
			if (log.isDebugEnabled()) log.debug("category="+request.getParameter("CodeType.category"));

			session.save(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
			return "promptAdd";
		}
		return "success";
	}
	
	public String promptUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("promptUpload");
			
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
			CodeType bean = new CodeType();
			ParamUtil.bindData(request, bean, "CodeType");
			session.update(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}
	
	public String listCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processUpload");
			DBSession session = DBSession.getInstance();
			CodeType bean = new CodeType();
			ParamUtil.bindData(request, bean, "CodeType");
			session.update(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "success";
	}	
	
}