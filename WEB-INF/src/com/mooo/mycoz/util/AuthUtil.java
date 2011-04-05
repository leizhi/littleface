package com.mooo.mycoz.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mooo.mycoz.db.DbFactory;
import com.mooo.mycoz.db.DbProcess;
import com.mooo.mycoz.dbobj.MultiDBObject;
import com.mooo.mycoz.dbobj.mycozBranch.GroupMember;
import com.mooo.mycoz.dbobj.mycozBranch.RoleMember;
import com.mooo.mycoz.dbobj.mycozBranch.User;
import com.mooo.mycoz.dbobj.mycozShared.Action;
import com.mooo.mycoz.dbobj.mycozShared.AuthGroup;
import com.mooo.mycoz.dbobj.mycozShared.AuthRole;
import com.mooo.mycoz.dbobj.mycozShared.Method;
import com.mooo.mycoz.dbobj.mycozShared.UserGroup;
import com.mooo.mycoz.dbobj.mycozShared.UserRole;

public class AuthUtil {

	private static Log log = LogFactory.getLog(AuthUtil.class);

	private static Object initLock = new Object();
	private static AuthUtil factory = null;
	
	public static AuthUtil getInstance() {

		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					factory = new AuthUtil();
				}
			}
		}
		return factory;
	}
	
	private boolean enable = false;
	private ConfigureUtil conf = ConfigureUtil.getInstance();

	private AuthUtil() {
		enable = conf.isEnableAuth();
		
		if(enable){
			parseXML();
			parseDatabase();
			refresh();
		}
	}

	private Map<String, ActionNode> xmlMap;
	private Map<String, ActionNode> dababaseMap;
	
	public Map<String, ActionNode> getXmlMap() {
		return xmlMap;
	}

	public void setXmlMap(Map<String, ActionNode> xmlMap) {
		this.xmlMap = xmlMap;
	}

	public Map<String, ActionNode> getDababaseMap() {
		return dababaseMap;
	}

	public void setDababaseMap(Map<String, ActionNode> dababaseMap) {
		this.dababaseMap = dababaseMap;
	}

	public void parseXML() {
		xmlMap = ConfigureUtil.getInstance().getActionMap();
	}
	
	public void parseDatabase() {
		Transaction tx = new Transaction();
		try {
			tx.start();
			
			dababaseMap = new HashMap<String, ActionNode>();
			
			DbProcess dbProcess = DbFactory.getInstance();
			
			Action action = new Action();
			List<?> actions = dbProcess.searchAndRetrieveList(tx.getConnection(),action);
			
			for(Object a:actions){
				ActionNode actionNode = new ActionNode();

				action = (Action) a;
				
				actionNode.setName(action.getName());
				
				Method method = new Method();
				method.setActionId(action.getId());
				
				List<?> methods = dbProcess.searchAndRetrieveList(tx.getConnection(),method);
				
				for(Object m:methods){
					method = (Method) m;
					actionNode.addResult(method.getMethodName(), "");
				}
				
				dababaseMap.put(actionNode.getName(), actionNode);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally{
			tx.end();
		}
	}
	
	public void refresh() {
		try {
			clearAction();
			addAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearAction() {
		Transaction tx = new Transaction();

		try {
			tx.start();
			
			DbProcess dbProcess = DbFactory.getInstance();
			
			Action action = new Action();
			List<?> actions = dbProcess.searchAndRetrieveList(tx.getConnection(),action);
			
			List<String> xmlMethods;
			List<String> dataMethods;

			Iterator<?> iterator = actions.iterator();
			
			while (iterator.hasNext()) {
				action = (Action) iterator.next();
				String actionName = action.getName();

				Method method = new Method();
				method.setActionId(action.getId());
				
				List<?> methods = dbProcess.searchAndRetrieveList(tx.getConnection(),method);
				
				if (xmlMap.containsKey(actionName)) {
					
					// database Methods
					dataMethods = new ArrayList<String>();

					for(Object obj:methods){
						method = (Method) obj;
						dataMethods.add(method.getMethodName());
					}
					
					// XML file Methods		
					xmlMethods = new ArrayList<String>();
					Hashtable<String, String> results = xmlMap.get(actionName).getResults();
					
					for(Entry<String, String> entry:results.entrySet()){
						xmlMethods.add(entry.getKey());
					}

					for(String methodName:dataMethods){
						
						if (!xmlMethods.contains(methodName)) {
							Method methodBean = new Method();
							methodBean.setMethodName(methodName);
							dbProcess.retrieve(tx.getConnection(), methodBean);
							
							AuthGroup authGroup = new AuthGroup();
							authGroup.setMethodId(methodBean.getId());
							dbProcess.delete(tx.getConnection(), authGroup);
							
							AuthRole authRole = new AuthRole();
							authRole.setMethodId(methodBean.getId());
							dbProcess.delete(tx.getConnection(), authRole);

							dbProcess.delete(tx.getConnection(), methodBean);
						}
					}
				} else {

					for(Object obj:methods){
						method = (Method) obj;
						
						AuthGroup authGroup = new AuthGroup();
						authGroup.setMethodId(method.getId());
						dbProcess.delete(tx.getConnection(), authGroup);
						
						AuthRole authRole = new AuthRole();
						authRole.setMethodId(method.getId());
						dbProcess.delete(tx.getConnection(), authRole);

						dbProcess.delete(tx.getConnection(), method);
					}
					dbProcess.delete(tx.getConnection(), action);
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (log.isErrorEnabled()) log.error("Exception:" + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.end();
		}
	}

	public void addAction() {
		Transaction tx = new Transaction();
		try {
			tx.start();
			DbProcess dbProcess = DbFactory.getInstance();

			String actionName;
			String methodName;

			for(Entry<String, ActionNode> entry:xmlMap.entrySet()){
				actionName = entry.getKey();
				
				if (dababaseMap.containsKey(actionName)) {
					
					Action action = new Action();
					action.setName(actionName);
					dbProcess.retrieve(tx.getConnection(), action);
					
					Method method = new Method();
					method.setActionId(action.getId());
					
					List<?> methods = dbProcess.searchAndRetrieveList(tx.getConnection(),method);
					List<String> dataMethods = new ArrayList<String>();

					for(Object obj:methods){
						method = (Method)obj;
						methodName = method.getMethodName();
						dataMethods.add(methodName);
					}
					
					Hashtable<String, String> results = xmlMap.get(actionName).getResults();
					
					for(Entry<String, String> e:results.entrySet()){
						methodName = e.getKey();

						if(!dataMethods.contains(methodName)){
							method = new Method();
							method.setId(IDGenerator.getNextID(tx.getConnection(), "mycozShared.Method"));
							method.setActionId(action.getId());
							method.setMethodName(methodName);
							method.setDescription(methodName);
							
							dbProcess.add(tx.getConnection(), method);
						}
					}
					
				} else {
					Action action = new Action();
					action.setId(IDGenerator.getNextID(tx.getConnection(), "mycozShared.Action"));
					action.setName(actionName);
					action.setDescription(actionName);
					dbProcess.add(tx.getConnection(), action);
					
					Hashtable<String, String> results = xmlMap.get(actionName).getResults();
					
					for(Entry<String, String> e:results.entrySet()){
						methodName = e.getKey();

						Method method = new Method();
						method.setId(IDGenerator.getNextID(tx.getConnection(), "mycozShared.Method"));
						method.setActionId(action.getId());
						method.setMethodName(methodName);
						method.setDescription(methodName);

						dbProcess.add(tx.getConnection(), method);
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception:" + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		} finally{
			tx.end();
		}
	}
	
	public Boolean checkAuth(Integer userId,String action,String method) {
		try {

			if ( !enable || userId == 1)
				return true;

			MultiDBObject mdobject = new MultiDBObject();
			mdobject.addTable(User.class, "user");
			mdobject.addTable(UserGroup.class, "userGroup");
			mdobject.addTable(GroupMember.class, "groupMember");
			mdobject.addTable(Action.class, "action");
			mdobject.addTable(Method.class, "method");
			mdobject.addTable(AuthGroup.class, "authGroup");

			mdobject.setForeignKey("user", "id", "groupMember", "userId");
			mdobject.setForeignKey("userGroup", "id", "groupMember", "groupId");
			mdobject.setForeignKey("authGroup", "groupId", "userGroup", "id");
			mdobject.setForeignKey("authGroup", "methodId", "method", "id");

			mdobject.setField("user", "id", userId);
			mdobject.setField("action", "name", action);
			mdobject.setField("method", "methodName", method);

			if(mdobject.count() >0)
				return true;
			
			mdobject = new MultiDBObject();
			mdobject.addTable(User.class, "user");
			mdobject.addTable(UserRole.class, "userRole");
			mdobject.addTable(RoleMember.class, "roleMember");
			mdobject.addTable(Action.class, "action");
			mdobject.addTable(Method.class, "method");
			mdobject.addTable(AuthRole.class, "authRole");

			mdobject.setForeignKey("user", "id", "roleMember", "userId");
			mdobject.setForeignKey("userRole", "id", "roleMember", "roleId");
			mdobject.setForeignKey("authRole", "roleId", "userRole", "id");
			mdobject.setForeignKey("authRole", "methodId", "method", "id");

			mdobject.setField("user", "id", userId);
			mdobject.setField("action", "name", action);
			mdobject.setField("method", "methodName", method);
			
			if(mdobject.count() >0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return true; // default return
	}
	
/*
	public void print() {
		try {
			Iterator<?> iterator;
			String key;
			List<String> actions;

			System.out.println(".............XML...........");

			iterator = xmlMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				actions = (List<String>) xmlMap.get(key);
				for (Iterator<String> it = actions.iterator(); it.hasNext();) {
					System.out.println(it.next());
					// it.next();
				}
			}

			System.out.println(".............Databases...........");

			iterator = dababaseMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				actions = (List<String>) xmlMap.get(key);
				for (Iterator<String> it = actions.iterator(); it.hasNext();) {
					System.out.println(it.next());
					// it.next();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printDatabase() {
		try {
			Iterator<?> iterator;
			String key;
			ActionNode action;

			System.out.println(".............XML...........");

			iterator = dababaseMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				action = (ActionNode) dababaseMap.get(key);
				System.out.println("name=" + action.getName() + " class="+ action.getCls());
				Hashtable<String, String> results = action.getResults();
				Iterator<?> resultIterator = results.keySet().iterator();
				while (resultIterator.hasNext()) {
					key = resultIterator.next().toString();
					System.out.println("result key=" + key + " value="+ results.get(key));
					if(log.isDebugEnabled()) log.debug("result key=" + key + " value="+ results.get(key));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printConf() {
		try {
			Iterator<?> iterator;
			String key;
			ActionNode action;

			System.out.println(".............XML...........");

			iterator = xmlMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				action = (ActionNode) xmlMap.get(key);
				System.out.println("name=" + action.getName() + " class="+ action.getCls());
				Hashtable<String, String> results = action.getResults();
				Iterator<?> resultIterator = results.keySet().iterator();
				while (resultIterator.hasNext()) {
					key = resultIterator.next().toString();
					System.out.println("result key=" + key + " value="+ results.get(key));
					if(log.isDebugEnabled()) log.debug("result key=" + key + " value="+ results.get(key));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}

