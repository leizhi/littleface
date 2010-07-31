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
import com.mooo.mycoz.dbobj.mycozShared.LinearCode;
import com.mooo.mycoz.util.IDGenerator;
import com.mooo.mycoz.util.ParamUtil;

public class CodeTypeAction extends BaseSupport{
	private static Log log = LogFactory.getLog(CodeTypeAction.class);

	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (log.isDebugEnabled()) log.debug("list");
			String value;
			
			List<String> codeCategory = new ArrayList<String>();
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
			List<String> codeCategory = new ArrayList<String>();
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
			if (log.isDebugEnabled()) log.debug("listCode");
			String 	id = request.getParameter("LinearCode.typeid");
			if(id==null)
				id= request.getParameter("id");
			
			request.setAttribute("id",id);

			CodeType codeType = new CodeType();
			codeType.setCatalog("mycozShared");
			codeType.setId(new Integer (id));
			codeType.retrieve();
			request.setAttribute("codeType", codeType);
			
			//Map types = new HashMap();

			if(codeType.getCategory().equals("Linear")){
				
				//ct = new CodeType();
				//ct.setCatalog("mycozShared");
				//ct.setCategory("Linear");
				//List cts = ct.searchAndRetrieveList();
				//CodeType bean;
				//for(Iterator it = cts.iterator(); it.hasNext();){
				//	bean = (CodeType)it.next();
				//	types.put(bean.getId(), bean.getName());
				//}

				LinearCode lc = new LinearCode();
				lc.setCatalog("mycozShared");
				lc.setTypeid(codeType.getId());
				request.setAttribute("codes",lc.searchAndRetrieveList());
			} else {
				//TreeCode tc = new TreeCode();
			}
			
			//request.setAttribute("types",types);

			//ct.setField("id", id);
			
			//Integer[] ids =  request.getParameterValues("id");
			//String[] ids =  request.getParameterValues("id");
			
			//for(int i=0;i<ids.length;i++){
			//	if (log.isDebugEnabled()) log.debug("ids="+ids[i]);
			//}

			
			
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
			//ParamUtil.add(request,"mycozShared.LinearCode");

			DBSession session = DBSession.getInstance();
			session.setCatalog("mycozShared");
			
			LinearCode bean = new LinearCode();
			bean.setId(new Integer(IDGenerator.getNextID("mycozShared.LinearCode")));
			
			if(request.getParameter("LinearCode.name")==null || "".equals(request.getParameter("LinearCode.name"))){
				return "listCode";
			}
			
			ParamUtil.bindData(request, bean, "LinearCode");
			session.save(bean);
			
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
			//ParamUtil.add(request,"mycozShared.LinearCode");
			String id =  request.getParameter("id");

			//DBSession session = DBSession.getInstance();
			//session.setCatalog("mycozShared");
			
			LinearCode bean = new LinearCode();
			bean.setCatalog("mycozShared");
			bean.setId(new Integer(id));
			bean.setUpdate("name", request.getParameter("LinearCode.name"));
			
			if(request.getParameter("LinearCode.name")==null || "".equals(request.getParameter("LinearCode.name"))){
				return "listCode";
			}
			bean.update();
			
			//ParamUtil.bindData(request, bean, "LinearCode");
			//session.update(bean);
			
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
				bean.setCatalog("mycozShared");
				bean.setId( new Integer(ids[i]));
				bean.delete();
			}
		
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Exception Load error of: " + e.getMessage());
		}
		return "listCode";
	}	
}
