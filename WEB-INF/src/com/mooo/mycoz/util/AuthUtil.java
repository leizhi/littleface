package com.mooo.mycoz.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.mooo.mycoz.dbobj.manager.Action;
import com.mooo.mycoz.dbobj.manager.Controller;

public class AuthUtil {

	private static Log log = LogFactory.getLog(AuthUtil.class);

	InputStream in = null;


	public AuthUtil() {
		in = getClass().getResourceAsStream("/xpc.xml");
		parseXML();
		parseDatabase();
		refreshController();
	}

	public void refreshController() {
		try {
			clearController();
			addController();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void clearController() {

		try {
			String action = "";
			String method = "";

			List<String> xmlActions;
			List<String> dataActions;

			query = session.createQuery("FROM Controller");
			Iterator<?> iterator = query.list().iterator();
			while (iterator.hasNext()) {
				Controller co = (Controller) iterator.next();
				action = co.getName();

				if (controllerXmlMap.containsKey(action)) {
					query = session
							.createQuery("FROM Action where controllerID=:controllerID");
					query.setParameter("controllerID", co.getId());

					dataActions = new ArrayList<String>();
					for (Iterator<?> it = query.list().iterator(); it.hasNext();) {
						Action ac = (Action) it.next();
						method = ac.getName();
						dataActions.add(method);
					}

					xmlActions = (List<String>) controllerXmlMap.get(action);

					for (Iterator<String> it = dataActions.iterator(); it
							.hasNext();) {
						method = it.next();

						if (!xmlActions.contains(method)) {
							query = session
									.createQuery("FROM Action where name=:name");
							query.setParameter("name", method);

							Action ac = (Action) query.list().iterator().next();

							session
									.createQuery(
											"delete AuthGroup where actionId=:actionId")
									.setInteger("actionId", ac.getId())
									.executeUpdate();

							session.createQuery(
									"delete AuthRole where actionID=:actionID")
									.setInteger("actionID", ac.getId())
									.executeUpdate();

							session.createQuery(
									"delete Action where name=:name")
									.setString("name", action).executeUpdate();

						}
					}
				} else {

					session
							.createQuery(
									"delete AuthGroup where controllerID=:controllerID")
							.setInteger("controllerID", co.getId())
							.executeUpdate();

					session.createQuery(
							"delete AuthRole where controllerID=:controllerID")
							.setInteger("controllerID", co.getId())
							.executeUpdate();

					session.createQuery(
							"delete Action where controllerID=:controllerID")
							.setInteger("controllerID", co.getId())
							.executeUpdate();

					session.createQuery("delete Controller where id=:id")
							.setInteger("id", co.getId()).executeUpdate();
				}

			}

			tx.commit();

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception:" + e.getMessage());
			e.printStackTrace();
			tx.rollback();

		}
	}

	public void addController() {
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance();
			tx = session.beginTransaction();
			Query query;

			String action = "";
			String method = "";

			List<String> xmlActions;
			List<String> dataActions;

			Iterator<?> iterator = controllerXmlMap.keySet().iterator();
			while (iterator.hasNext()) {
				action = iterator.next().toString();

				xmlActions = (List<String>) controllerXmlMap.get(action);

				if (controllerDababaseMap.containsKey(action)) {

					query = session.createQuery(
							"FROM Controller where name=:name").setString(
							"name", action);

					Controller co = (Controller) query.list().iterator().next();

					dataActions = (List<String>) controllerDababaseMap
							.get(action);

					for (Iterator<String> it = xmlActions.iterator(); it
							.hasNext();) {
						method = it.next();
						if (!dataActions.contains(method)) {
							Action ac = new Action();
							ac.setControllerID(co.getId());
							ac.setName(method);

							session.save(ac);

						}
					}

				} else {
					Controller co = new Controller();
					co.setName(action);
					co.setDescription("");
					session.save(co);

					for (Iterator<String> it = xmlActions.iterator(); it
							.hasNext();) {
						method = it.next();
						Action ac = new Action();
						ac.setControllerID(co.getId());
						ac.setName(method);

						session.save(ac);
					}
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error("Exception:" + e.getMessage());
			e.printStackTrace();
			tx.rollback();

		}
	}

	private Map<String, List<String>> controllerXmlMap;

	public void parseXML() {
		try {

			SAXReader saxReader = new SAXReader();
			
			saxReader.setEntityResolver(
					new EntityResolver() {
						public InputSource resolveEntity(String publicId,String systemId) {
							if (publicId.equals("-//Apache Software Foundation//DTD Struts Configuration 2.0//EN")) {
								InputStream in = getClass().getResourceAsStream("/struts-2.0.dtd");
								return new InputSource(in);
							}
							return null;
						}
					});
			
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();

			// parse action methods

			controllerXmlMap = new HashMap<String, List<String>>();
			String action = null;
			// String clzName = null;
			List<String> methods = null;

			Iterator<?> itrAction = root.selectNodes("package/action")
					.iterator();

			Element actionNode;
			Element methodNode;

			while (itrAction.hasNext()) {
				actionNode = (Element) itrAction.next();

				action = actionNode.attributeValue("name");

				// System.out.println("----------"+action+"----------");
				methods = new ArrayList<String>();

				for (Iterator<?> itrResult = actionNode.selectNodes("result")
						.iterator(); itrResult.hasNext();) {
					methodNode = (Element) itrResult.next();

					String forwardName = methodNode.attributeValue("name");
					// String forwardType = methodNode.attributeValue("type");
					// System.out.println("forwardName="+forwardName);

					methods.add(forwardName);

				}
				controllerXmlMap.put(action, methods);

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private Map<String, List<String>> controllerDababaseMap;

	public void parseDatabase() {
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance();
			tx = session.beginTransaction();
			Query query;

			controllerDababaseMap = new HashMap<String, List<String>>();
			List<String> dataActions;
			String action = null;
			String method = null;

			query = session.createQuery("FROM Controller");
			Iterator<?> iterator = query.list().iterator();

			while (iterator.hasNext()) {
				Controller co = (Controller) iterator.next();
				action = co.getName();

				query = session
						.createQuery("FROM Action where controllerID=:controllerID");
				query.setParameter("controllerID", co.getId());

				dataActions = new ArrayList<String>();
				for (Iterator<?> it = query.list().iterator(); it.hasNext();) {
					Action ac = (Action) it.next();
					method = ac.getName();
					dataActions.add(method);
				}

				controllerDababaseMap.put(action, dataActions);

			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();

		}
	}

	public void print() {
		try {
			Iterator<?> iterator;
			String key;
			List<String> actions;

			System.out.println(".............XML...........");

			iterator = controllerXmlMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				actions = (List<String>) controllerXmlMap.get(key);
				for (Iterator<String> it = actions.iterator(); it.hasNext();) {
					System.out.println(it.next());
					// it.next();
				}
			}

			System.out.println(".............Databases...........");

			iterator = controllerDababaseMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				actions = (List<String>) controllerXmlMap.get(key);
				for (Iterator<String> it = actions.iterator(); it.hasNext();) {
					System.out.println(it.next());
					// it.next();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

