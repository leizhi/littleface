package com.mooo.mycoz.action.profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.action.BaseSupport;
import com.mooo.mycoz.dbobj.mycozShared.CodeType;
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.StringUtils;
import com.mooo.mycoz.util.http.HttpParamUtil;

public class CodeTypeAction extends BaseSupport{
	private static Log log = LogFactory.getLog(CodeTypeAction.class);

	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("list");
			
			List<String> category = new ArrayList<String>();
			category.add("Linear");
			category.add("Tree");
			request.setAttribute("category", category);
			request.setAttribute("categoryDefault", request.getParameter("query.category"));

			CodeType codeType = new CodeType();
			HttpParamUtil.bindData(request, codeType, "query");
			
			List<?> codeTypes = dbProcess.searchAndRetrieveList(codeType);
			request.setAttribute("codeTypes", codeTypes);
			
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
			
			List<String> codeCategory = new ArrayList<String>();
			codeCategory.add("Linear");
			codeCategory.add("Tree");
			request.setAttribute("codeCategory", codeCategory);
			
			CodeType codeType = new CodeType();
			HttpParamUtil.bindData(request, codeType, "codeType");
			codeType.setId(IDGenerator.getNextID("mycozShared.CodeType").intValue());
			
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
			
			CodeType codeType = new CodeType();
			HttpParamUtil.bindData(request, codeType, "codeType");
			StringUtils.noNull(codeType.getName());
			dbProcess.add(codeType);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
			return "promptAdd";
		}
		return "list";
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
			CodeType bean = new CodeType();
			HttpParamUtil.bindData(request, bean, "CodeType");
			dbProcess.update(bean);
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "list";
	}
	
	public String processDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processDelete");
			
			String[] choose =  request.getParameterValues("choose");
			for(int i=0;i<choose.length;i++){
				if (log.isDebugEnabled()) log.debug("choose="+choose[i]);
				CodeType bean = new CodeType();
				bean.setId( new Integer(choose[i]));
				dbProcess.delete(bean);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "list";
	}
	
	public String listCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			if (log.isDebugEnabled()) log.debug("listCode");

			CodeType codeType = new CodeType();
			HttpParamUtil.bindData(request, codeType, "codeType");
			dbProcess.retrieve(codeType);
			
			request.setAttribute("codeType", codeType);
			
			if(codeType.getCategory().equals("Linear")){
				
				LinearCode lc = new LinearCode();
				lc.setTypeid(codeType.getId());
				request.setAttribute("codes",dbProcess.searchAndRetrieveList(lc));
				
			} else {
				//TreeCode tc = new TreeCode();
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
				if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		} 
		return "success";
	}
	
	public String processAddCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processAdd");
			
			LinearCode linearCode = new LinearCode();
			HttpParamUtil.bindData(request, linearCode, "LinearCode");
			linearCode.setId(IDGenerator.getNextID("mycozShared.LinearCode").intValue());
			
			dbProcess.add(linearCode);
			
		}catch (SQLException e) {
			e.printStackTrace();
			if(log.isDebugEnabled()) log.debug("SQLException:"+e.getMessage());
			System.out.println("SQLException:"+e.getMessage());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		} 
		
		return "listCode";
	}

	public String processUpdateCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processAdd");
			String id =  request.getParameter("id");

			LinearCode bean = new LinearCode();
			bean.setId(new Integer(id));
			
			if(request.getParameter("LinearCode.name")==null || "".equals(request.getParameter("LinearCode.name"))){
				return "listCode";
			}
			dbProcess.update(bean);
			
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "listCode";
	}
	
	public String processDeleteCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("processDeleteCode");

			String[] ids =  request.getParameterValues("id");
			
			for(int i=0;i<ids.length;i++){
				if (log.isDebugEnabled()) log.debug("ids="+ids[i]);
				LinearCode bean = new LinearCode();
				bean.setId( new Integer(ids[i]));
				dbProcess.delete(bean);
			}
		
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "listCode";
	}	
}
