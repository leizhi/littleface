package com.mooo.mycoz.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.mooo.mycoz.cache.Cache;
import com.mooo.mycoz.cache.CacheManager;

public class ConfigureUtil {

	private static Object initLock = new Object();
	private static ConfigureUtil factory = null;

	private static Log log = LogFactory.getLog(ConfigureUtil.class);

	private InputStream cacheStream = null;
	private InputStream mvcStream = null;

	private String cacheFile = "/myconfig.xml";
	private String mvcFile = "/myconfig.xml";
	//private String poolFile = "/mypool.xml";

	public static ConfigureUtil getInstance() {

		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					factory = new ConfigureUtil();
				}
			}
		}
		return factory;
	}

	private ConfigureUtil() {
		flush();
	}

	public void flush() {
		try {
			cacheStream = new FileInputStream(cacheFile);
			mvcStream = new FileInputStream(mvcFile);
		} catch (FileNotFoundException e) {
			cacheStream = getClass().getResourceAsStream(cacheFile);
			mvcStream = getClass().getResourceAsStream(mvcFile);
		}
	}

	public String getCacheFile() {
		return cacheFile;
	}

	public void setCacheFile(String cacheFile) {
		this.cacheFile = cacheFile;
	}

	public String getMvcFile() {
		return mvcFile;
	}

	public void setMvcFile(String mvcFile) {
		this.mvcFile = mvcFile;
	}

	public HashMap<String, Cache> confCache() {
		HashMap<String, Cache> caches = new HashMap<String, Cache>();
		try {
			flush();

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
			
			Document doc = saxReader.read(cacheStream);
			Element root = doc.getRootElement();

			Iterator<?> itrCache = root.selectNodes("package/cache").iterator();
			Element cacheNode;
			String value = "K";

			while (itrCache.hasNext()) {
				cacheNode = (Element) itrCache.next();

				int size = Integer.valueOf(cacheNode.attributeValue("size"))
						.intValue();
				value = cacheNode.attributeValue("sizeUnit");
				if (value.equals("K"))
					size = size * CacheManager.K;
				else if (value.equals("M"))
					size = size * CacheManager.M;

				int time = Integer.valueOf(cacheNode.attributeValue("time")).intValue();
				
				value = cacheNode.attributeValue("timeUnit");
				
				if (value.equals("SECOND"))
					time = time * CacheManager.SECOND;
				else if (value.equals("MINUTE"))
					time = time * CacheManager.MINUTE;
				else if (value.equals("HOUR"))
					time = time * CacheManager.HOUR;

				caches.put(cacheNode.attributeValue("name"), new Cache(size,time));
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return caches;
	}

	private Map<String, ActionNode> actionMap;

	public Map<String, ActionNode> getActionMap() {
		return actionMap;
	}

	public void conf() {
		try {
			if (log.isDebugEnabled())
				log.debug("ConfigureUtil conf loading..");
			if (log.isDebugEnabled())
				log.debug("ConfigureUtil conf patch:" + getMvcFile());

			flush();

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
			
			Document doc = saxReader.read(mvcStream);
			Element root = doc.getRootElement();

			// parse action methods
			actionMap = new HashMap<String, ActionNode>();
			// String clzName = null;
			Iterator<?> itrAction = root.selectNodes("package/action")
					.iterator();

			Element actionNode;
			Element methodNode;
			ActionNode aNode;
			String action;
			while (itrAction.hasNext()) {
				aNode = new ActionNode();

				actionNode = (Element) itrAction.next();
				// set filed
				action = actionNode.attributeValue("name");
				aNode.setName(action);
				aNode.setCls(actionNode.attributeValue("class"));
				aNode.setDefMethod(actionNode.attributeValue("default"));
				// System.out.println("----------"+action+"----------");
				for (Iterator<?> itrResult = actionNode.selectNodes("result")
						.iterator(); itrResult.hasNext();) {
					methodNode = (Element) itrResult.next();
					aNode.addResult(methodNode.attributeValue("name"),
							methodNode.getTextTrim());
					// String forwardName = methodNode.attributeValue("name");
					// System.out.println(methodNode.getTextTrim());
					// System.out.println(methodNode.getStringValue());
				}
				actionMap.put(action, aNode);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public void printConf() {
		try {
			Iterator<?> iterator;
			String key;
			ActionNode action;

			System.out.println(".............XML...........");

			iterator = actionMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				action = (ActionNode) actionMap.get(key);
				System.out.println("name=" + action.getName() + " class="
						+ action.getCls());
				Hashtable<String, String> results = action.getResults();
				Iterator<?> resultIterator = results.keySet().iterator();
				while (resultIterator.hasNext()) {
					key = resultIterator.next().toString();
					System.out.println("result key=" + key + " value="
							+ results.get(key));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
