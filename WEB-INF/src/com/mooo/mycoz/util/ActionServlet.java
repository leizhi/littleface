package com.mooo.mycoz.util;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import java.lang.reflect.Method;

public class ActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119522977670257605L;
	private static Log log = LogFactory.getLog(ActionServlet.class);

	protected ConfigureUtil conf;
	
	public void destroy() {
	  
	}
	
	public void init() throws ServletException {
		
		super.init();
		
		// get web app real directory   
		String prefix = getServletContext().getRealPath("/");
		String confDir = getServletContext().getInitParameter("configDir");
		
		//load log4j configure file
		// read parameter from web.xml file to set log4j property   
		// set log4j
		if (confDir != null) {
			DOMConfigurator.configure(prefix + confDir+"/log4j.xml");
		}
		
		conf = ConfigureUtil.getInstance();
		conf.setCacheFile(prefix + confDir +"/myconfig.xml");
		conf.setMvcFile(prefix +  confDir +"/myconfig.xml");

		conf.conf();
		conf.confCache();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			String accessPath = request.getServletPath();
			
			if(log.isDebugEnabled())log.debug("accessPath:" + accessPath);
			
			String execPath = ActionUtil.execPath(accessPath);

			//if (execPath == null)
			//	execPath = request.getParameter("action");
			
			if(log.isDebugEnabled())log.debug("execPath:" + execPath);

			String execAction = "";
			String execResult = "";
			String execMethod = request.getParameter("method");
			
			ActionNode actionNode = null;
			Hashtable<String, String> results;

			// get mvc configure
			Map<String, ActionNode> actionMap = conf.getActionMap();
			// set controller,execState for execPath
			actionNode = (ActionNode) actionMap.get(execPath);

			execAction = actionNode.getCls();
			if (execMethod == null)
				execMethod = actionNode.getDefMethod();
			
			results = actionNode.getResults();
			
			if(log.isDebugEnabled())log.debug("========exec start=======");
			if(log.isDebugEnabled())log.debug("execAction="+execAction);
			if(log.isDebugEnabled())log.debug("execMethod="+execMethod);
			//check sample action
			
			// exec Controller request aciton
			Object obj = Class.forName(execAction).newInstance();
			
			Class<? extends Object> cls = obj.getClass();
			
			Class<?>[] paraTypes = new Class[] { HttpServletRequest.class,HttpServletResponse.class };
			
			Method method = cls.getMethod(execMethod, paraTypes);
			
			Object paraValues[] = new Object[] { request, response };
			
			// set Transition jsp for execMethod
			String actionResult = results.get(execMethod);
			
			String resultMethod = (String) method.invoke(obj, paraValues);
			
			if(log.isDebugEnabled())log.debug("========exec end=======");

			if(resultMethod != null)
				if(!resultMethod.equals("success"))
					actionResult = results.get(resultMethod);
			/*else {
				String returnAction= (String) request.getAttribute("action");
				String returnMethod= (String) request.getAttribute("method");
				
				if(returnAction != null){
					if(returnMethod !=null) {
						actionResult = "/"+returnAction+".do?method="+returnMethod;
					} else {
						actionResult = "/"+returnAction+".do";
					}
				} else{
					new Exception("RETURN STATE NULL");
				}
			}*/
			
			if(log.isDebugEnabled())log.debug("execAction:" + execAction);
			if(log.isDebugEnabled())log.debug("execResult:" + actionResult);

			Pattern p = Pattern.compile("\\.jsp");
			Matcher m;
			boolean isJsp = false;
			
			if (actionResult != null) {
				
				execResult = actionResult;
				if(execResult.indexOf("/") != 0)
					execResult = "/"+execResult;
				
				if(execResult.indexOf(".do") < 0 && execResult.indexOf(".jsp") < 0)
					execResult += ".do";

				m = p.matcher(execResult);
				isJsp = m.find();
				
				if (!execResult.equals("")) {
					if(isJsp){
						getServletContext().getRequestDispatcher(execResult).forward(request,response);
					}else{
						response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
						response.setHeader("Location",request.getContextPath()+execResult);
					}
				}
			}
			
		} catch (NullPointerException e) {
			if(log.isErrorEnabled()) log.error("NullPointerException:"+e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			if(log.isErrorEnabled()) log.error("RuntimeException:"+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			if(log.isErrorEnabled()) log.error("Exception:"+e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			if(log.isErrorEnabled()) log.error("Throwable:"+e.getMessage());
			e.printStackTrace();
		}finally{
				//getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request,response);
		}
	}

}
