package com.mooo.mycoz.util;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import com.mooo.mycoz.cache.CacheManager;

import java.lang.reflect.Method;

public class ActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119522977670257605L;
	private static Log log = LogFactory.getLog(ActionServlet.class);
	
	public static final String USER_SESSION_KEY = "UserSessionKey";
	
	private static String execAction = "";
	private static String execResult = "";
	private static String execMethod = "";
	private static String resultMethod ="";
	
	protected ConfigureUtil conf;
	
	private static Map<String, ActionNode> actionMap;
	private static CacheManager cacheManager;
	
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
		
		// get mvc configure
		actionMap = conf.getActionMap();
		cacheManager = CacheManager.getInstance();
	}

	public void addCache(String key, Object object){
		cacheManager.add("action", key, object);
	}

	public Object getCache(String key){
		return cacheManager.get("action", key);
	}
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			String accessPath = request.getServletPath();
			
			if(log.isDebugEnabled())log.debug("accessPath:" + accessPath);
			
			String execPath = ActionUtil.execPath(accessPath);

			//check sample action
			HttpSession session = request.getSession(true);
			Integer userID = (Integer) session.getAttribute(USER_SESSION_KEY);
			boolean isAuthenticated = (null != userID);

			if (!isAuthenticated) {
				if(!execPath.equals("Login") || !execPath.equals("Index"))
					execPath="Login";
			}
			
			//if (execPath == null)
			//	execPath = request.getParameter("action");
			if(log.isDebugEnabled())log.debug("execPath:" + execPath);

			execMethod = request.getParameter("method");

			ActionNode actionNode = null;
			Hashtable<String, String> results;
			// set controller,execState for execPath
			actionNode = (ActionNode) actionMap.get(execPath);
			
			execAction = actionNode.getCls();
			if (execMethod == null)
				execMethod = actionNode.getDefMethod();
			
			results = actionNode.getResults();
			
			if(log.isDebugEnabled())log.debug("========exec start=======");
			if(log.isDebugEnabled())log.debug("execAction="+execAction);
			if(log.isDebugEnabled())log.debug("execMethod="+execMethod);
			
			// exec Controller request aciton
			Object obj = getCache(execAction);

			if(obj == null) {
				obj = Class.forName(execAction).newInstance();
				addCache(execAction, obj);
			}
			
			Class<? extends Object> cls = obj.getClass();
					
			Class<?>[] paraTypes = new Class[] { HttpServletRequest.class,HttpServletResponse.class };
			
			Method method = cls.getMethod(execMethod, paraTypes);
			
			Object paraValues[] = new Object[] { request, response };
			
			// set Transition jsp for execMethod
			resultMethod = (String) method.invoke(obj, paraValues);
			execResult = results.get(execMethod);

			if(log.isDebugEnabled())log.debug("========exec end=======");
			
			if(resultMethod != null){
				// not success then exec return method and fowward jsp
				if(!resultMethod.equals("success")) {
					
					execMethod=resultMethod;
					method = cls.getMethod(execMethod, paraTypes);
					resultMethod = (String) method.invoke(obj, paraValues);
					
					if(!resultMethod.equals("success"))
						execResult = results.get(resultMethod);
					else
						execResult = results.get(execMethod);
				}
				
				if(StringUtils.checkString(execResult, "\\.jsp")){// is jsp
					getServletContext().getRequestDispatcher(execResult).forward(request,response);
				} else if(StringUtils.checkString(execResult, "\\.do")){// is action
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location",request.getContextPath()+execResult);
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
